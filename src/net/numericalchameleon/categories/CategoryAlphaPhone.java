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
import net.numericalchameleon.data.Unit;

public class CategoryAlphaPhone extends CategoryObject {

    private final ArrayList<Unit> sourceUnits, targetUnits;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;
    private static final int TRANSFER = -1;
    
    public CategoryAlphaPhone(CategoryInterface clusterInterface) {
        rb = clusterInterface.getResourceBundle();
        sourceUnits = new ArrayList<>();
        sourceUnits.add(new Unit(0, localize("AlphaPhone.alphaName", "Phone# (alphanumerical)"), "int"));

        targetUnits = new ArrayList<>();
        targetUnits.add(new Unit(0, localize("AlphaPhone.numName", "Phone# (numerical)"), "int"));
        
        transferUnit = new Unit(TRANSFER);
        defaultSourceUnit = sourceUnits.get(0);
        defaultTargetUnit = targetUnits.get(0);
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return sourceUnits;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return targetUnits;
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
        return 0;
    }
    
    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
    }

    @Override
    public boolean isOneway() {
        return true;
    }

    @Override
    public boolean isPlusMinusSupported() {
        return false;
    }
    
    @Override
    public String getOutput(int sourceIndex, int targetIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);        
        Unit targetUnit = targetUnits.get(targetIndex);
        return getOutput(sourceUnit, targetUnit);
    }
    
    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        // int sid = sourceUnit.getId();
        int tid = targetUnit.getId();
        
        StringBuilder sb = new StringBuilder();
        String inputlc = input.toLowerCase();
        for (int i = 0; i < inputlc.length(); i++) {
            char c = inputlc.charAt(i);
            switch (c) {
                case 'a':
                case 'b':
                case 'c':
                    sb.append('2');
                    break;
                case 'd':
                case 'e':
                case 'f':
                    sb.append('3');
                    break;
                case 'g':
                case 'h':
                case 'i':
                    sb.append('4');
                    break;
                case 'j':
                case 'k':
                case 'l':
                    sb.append('5');
                    break;
                case 'm':
                case 'n':
                case 'o':
                    sb.append('6');
                    break;
                case 'p':
                case 'q':
                case 'r':
                case 's':
                    sb.append('7');
                    break;
                case 't':
                case 'u':
                case 'v':
                    sb.append('8');
                    break;
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    sb.append('9');
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    sb.append(c);
                    break;
                default:
                    if (tid != TRANSFER) {
                        sb.append(input.charAt(i));
                    }
            }
        }
        return sb.toString();
    }


    @Override
    public String getInitialValue() {
        return "555-HELP";
    }


    @Override
    public String getTransferValue(int sourceindex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceindex);
        return getOutput(sourceUnit, transferUnit);
    }
    
    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, transferUnit);
    }

    @Override
    public String getCard() {
        return "emptyCard";
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }

}
