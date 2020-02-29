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
import jonelo.sugar.math.Rational;
import net.numericalchameleon.data.Unit;

public class CategoryRationalNumbers extends CategoryObject {

    private final static int
            cTRANSFER = -1,
            cBRUCH = 0,
            cBRUCH_GEMISCHT = 1,
            cDECIMAL_ROUNDED = 2,
            cDECIMAL_EXACT = 3,
            cPROZENT = 4,
            cPROMILLE = 5,
            cPERMYRIAD = 6;

    private static String INVALID;

    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, decimalTransferUnit, internalCalcUnit;

    public CategoryRationalNumbers(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        units = new ArrayList<>();

        units.add(new Unit(cBRUCH,
            localize("Fractions.fractionCommon", "Bruch (gemein)"), "int"));
        units.add(new Unit(cBRUCH_GEMISCHT,
            localize("Fractions.fractionMixed", "Bruch (gemischt)"), "int"));
        units.add(new Unit(cDECIMAL_ROUNDED,
            localize("Fractions.decimalRounded", "Decimal (rounded)"), "int"));
        units.add(new Unit(cDECIMAL_EXACT,
            localize("Fractions.decimalExact", "Decimal (exact)"), "int"));
        units.add(new Unit(cPROZENT,
            localize("Fractions.decimalPercent", "Prozent %"), "int"));
        units.add(new Unit(cPROMILLE,
            localize("Fractions.decimalPromille", "Promille (\u2030)"), "int"));
        units.add(new Unit(cPERMYRIAD,
            localize("Fractions.decimalPermyriad", "Permyriad (\u2031)"), "int"));
        
        decimalTransferUnit = new Unit(cTRANSFER);
        internalCalcUnit = new Unit(cBRUCH);
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(0);
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
        return cBRUCH;
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return cBRUCH;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }
    
    @Override
    public boolean isScientificSupported() {
        return true;
    }

    @Override
    public int getPreferredPrecision() {
        return 12;
    }

    @Override
    public String getOutput(int sourceIndex, int targetIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);        
        Unit targetUnit = units.get(targetIndex);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {

        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();
        Rational rational = null;

        switch (sid) { // anything to Rational
        
            case cBRUCH: // fallthrouth
            case cBRUCH_GEMISCHT:
                try {
                    rational = new Rational(input);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case cTRANSFER: // fallthrough
            case cDECIMAL_ROUNDED:                
                try {
                    BigDecimal dec = new BigDecimal(input.replace(decsep, '.'));
                    rational = new Rational(dec);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case cDECIMAL_EXACT:
                try {
                    rational = new Rational(input.replace(decsep, '.'));
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case cPROZENT:                
                try {
                    BigDecimal dec2 = new BigDecimal(input.replace(decsep, '.'));
                    dec2 = dec2.movePointLeft(2);
                    rational = new Rational(dec2);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
            case cPROMILLE:                
                try {
                    BigDecimal dec3 = new BigDecimal(input.replace(decsep, '.'));
                    dec3 = dec3.movePointLeft(3);
                    rational = new Rational(dec3);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
            case cPERMYRIAD:                
                try {
                    BigDecimal dec4 = new BigDecimal(input.replace(decsep, '.'));
                    dec4 = dec4.movePointLeft(4);
                    rational = new Rational(dec4);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
            default: rational = new Rational();
        }

        switch (tid) { // Rational to anything
        
            case cBRUCH:
                return rational.toString();
                
            case cBRUCH_GEMISCHT:
                return rational.toMixedString();

            case cDECIMAL_ROUNDED:
                return decSciFormatted(rational);

            case cTRANSFER:
                return rational.toDecStringRounded(getPrecision());
            
            case cDECIMAL_EXACT:
                return rational.toDecStringExact().replace('.', decsep);

            case cPROZENT:
                rational = rational.multiply(new Rational(100, 1));
                return decSciFormatted(rational);

            case cPROMILLE:
                rational = rational.multiply(new Rational(1000, 1));
                return decSciFormatted(rational);

            case cPERMYRIAD:
                rational = rational.multiply(new Rational(10000, 1));
                return decSciFormatted(rational);
        }
        return null;
    }

    // returns a well formatted decimal resp. scientific String representation of the rational
    private String decSciFormatted(Rational rational) {
        String temp = getScientific() ?
               rational.toSciStringRounded(getPrecision()):
               rational.toDecStringRounded(getPrecision());
        return temp.replace('.', decsep);
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = "0";
            input = getOutput(internalCalcUnit, sourceUnit);
        }
        Rational rational = new Rational(getOutput(sourceUnit, internalCalcUnit));
        rational = rational.add(new Rational(plus));
        input = rational.toString();
        return getOutput(internalCalcUnit, sourceUnit);
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {       
        Unit sourceUnit = units.get(sourceIndex);
        return setValue(big, sourceUnit);
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(decimalTransferUnit, sourceUnit);
    }

    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return getOutput(sourceUnit, decimalTransferUnit);
    }
    
    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, decimalTransferUnit);
    }
    
    @Override
    public String getCard() {
        return "sciCard";
    }
    
}
