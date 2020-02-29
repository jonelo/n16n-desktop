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
import java.util.ArrayList;
import java.util.Properties;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.util.romannumerals.RomanNumeral;
import net.numericalchameleon.util.romannumerals.RomanNumeralException;

public class CategoryRomanNumerals extends CategoryObject {

    private final int _DEC = 0, _ROM = 1;
    private static String INVALID;
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    /**
     * Creates new CategoryRomanNumerals
     * @param categoryInterface the category
     */
    public CategoryRomanNumerals(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Roman.decimalInvalid", "Value is invalid.");
        units = new ArrayList<>();
        units.add(new Unit(_DEC, localize("Moduls.decimalName", "Decimal"), "int"));
        units.add(new Unit(_ROM, localize("Roman.roman", "Roman"), "int"));

        transferUnit = units.get(0);
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(1);

        Properties romanl10n = new Properties();
        romanl10n.put(RomanNumeral.Errors.ISEMPTY.name(), localize("Roman.isEmpty", ""));
        romanl10n.put(RomanNumeral.Errors.INVALIDCHAR.name(), localize("Roman.invalidChar", ""));
        romanl10n.put(RomanNumeral.Errors.CHARONLYONCE.name(), localize("Roman.charOnlyOnce", ""));
        romanl10n.put(RomanNumeral.Errors.CHAR3TIMES.name(), localize("Roman.char3times", ""));
        romanl10n.put(RomanNumeral.Errors.CHAR4TIMES.name(), localize("Roman.char4times", ""));
        romanl10n.put(RomanNumeral.Errors.OUTOFRANGE.name(), localize("Roman.decimalRange", ""));
        RomanNumeral.setL10NProperties(romanl10n);
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
        return 0;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return 1;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        RomanNumeral roman;

        switch (sid) {
            case _DEC:
                try {
                    roman = new RomanNumeral(Integer.parseInt(input));
                } catch (NumberFormatException | RomanNumeralException e) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case _ROM:
                roman = new RomanNumeral(input);
                break;
            default:
                throw new IllegalArgumentException("inernal error");
        }

        switch (tid) {
            case _DEC:
                return Long.toString(roman.toLong());
            case _ROM:
                return roman.toRoman();
            default:
                throw new IllegalArgumentException("inernal error");
        }
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = units.get(sourceindex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = "1";
            input = getOutput(transferUnit, sourceUnit);
        }
        String temp = getOutput(sourceUnit, transferUnit);
        BigDecimal big = new BigDecimal(temp);
        big = big.add(plus);
        input = big.toPlainString();
        return getOutput(transferUnit, sourceUnit);
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
    
    @Override
    public int getPreferredPrecision() {
        return -1;
    }
}
