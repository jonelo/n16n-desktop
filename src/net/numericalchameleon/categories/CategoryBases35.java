/**
 *
 * NumericalChameleon 3.0.0 - more than an unit converter - a NumericalChameleon
 * Copyright (c) 2001-2020 Dipl.-Inf. (FH) Johann Nepomuk Loefflmann, All Rights
 * Reserved, <http://www.numericalchameleon.net>.
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package net.numericalchameleon.categories;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import net.numericalchameleon.data.Unit;

/**
 * Cluster for Radix-Calculation 2 to 36
 */
public class CategoryBases35 extends CategoryObject {

    private static String INVALID;
    private ArrayList<Unit> units;
    private Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    /**
     * Creates new ClusterBase35
     * @param categoryInterface
     */
    public CategoryBases35(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        units = new ArrayList<>();

        //String name;
        for (int i = 2; i < 37; i++) {
            String brackets;
            switch (i) {
                case 2:
                    brackets = String.format(" (%s)",
                            localize("Radix.dual", "Dual"));
                    break;
                case 8:
                    brackets = String.format(" (%s)",
                            localize("Radix.octal", "Octal"));
                    break;
                case 10:
                    brackets = String.format(" (%s)",
                            localize("Moduls.decimalName", "Decimal"));
                    break;
                case 16:
                    brackets = String.format(" (%s)",
                            localize("Radix.hex", "Hexadecimal"));
                    break;
                default:
                    brackets = "";
            }
            String name = String.format("%s %d%s",
                    localize("Radix.radix", "Radix"), i, brackets);

            Unit unit = new Unit(i, name, "int");

            if (i == 10) {
                defaultSourceUnit = unit;
                transferUnit = unit;
            } else if (i == 16) {
                defaultTargetUnit = unit;
            }
            units.add(unit);
        }
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return units;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return units;
    }

    @Override
    public int getSourceDefault() {
        return 8; // base 10 (index 10-2)
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return 14; // base 16 (index 16-2)
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
    }

    @Override
    public String getOutput(int sourceIndex, int targetIndex) throws Exception {
        return getOutput(units.get(sourceIndex), units.get(targetIndex));
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        BigInteger big = null;
        try {
            big = new BigInteger(input, sid);
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        return big.toString(tid).toUpperCase();
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = units.get(sourceindex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = "0";
        }
        BigDecimal big = new BigDecimal(getOutput(sourceUnit, transferUnit));
        big = big.add(plus);
        return setValue(big, sourceUnit);
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return setValue(big, sourceUnit);
    }

    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(transferUnit, sourceUnit);
    }

    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return getTransferValue(sourceUnit);
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, transferUnit);
    }

    @Override
    public String getCard() {
        return "emptyCard";
    }
}
