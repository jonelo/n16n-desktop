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
import java.math.RoundingMode;
import java.util.ArrayList;
import jonelo.sugar.math.GeneralMath;
import net.numericalchameleon.data.Unit;

public class CategorySlope extends CategoryObject {

    private final static int 
            _STEIGUNG = 0,
            _STEIGUNG_PROMILLE = 1,
            _NEIGUNGSWINKEL = 2,
            _VERHAELTNIS = 3;
    private final static BigDecimal _180 = BigDecimal.valueOf(180),
            _PI = new BigDecimal(CategoryAngle.PI);
    private final static String UNENDLICH = "\u221E";  // liegende Acht

    private static String INVALID;
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit;

    /**
     * Creates new ClusterTemperature
     * @param categoryInterface
     */
    public CategorySlope(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");

        units = new ArrayList<>();
        units.add(new Unit(_STEIGUNG, localize("Slope.slope", "Steigung in Prozent (%)"), "int"));
        units.add(new Unit(_STEIGUNG_PROMILLE, localize("Slope.slopePromille", "Steigung in Promille (\u2030)"), "int"));
        units.add(new Unit(_NEIGUNGSWINKEL, localize("Slope.angle", "Neigungswinkel in °"), "int"));
        units.add(new Unit(_VERHAELTNIS, localize("Slope.1tox", "Verhältnis (1:x)"), "int"));
        
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(2);
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
        return _STEIGUNG;
    }

    @Override
    public int getTargetDefault() {
        return _NEIGUNGSWINKEL;
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }
    
    // defaultValue is transferValue
    // is there any special transverValue ?
    public boolean isSpecialDefaultValue() {
        return false;
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
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);        
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId(); 

        BigDecimal x = BigDecimal.ZERO;
        boolean unendlich = false;
        boolean minusUnendlich = false;

        double d = 0.0;

        switch (sid) { // anything to Steigung (in Prozent)

            case _STEIGUNG:
                if (input.equals(UNENDLICH)) {
                    unendlich = true;
                } else if (input.equals("-" + UNENDLICH)) {
                    minusUnendlich = true;
                } else {
                    try {
                        x = new BigDecimal(input.replace(decsep, '.'));
                    } catch (Exception e) {
                        throw new Exception(INVALID);
                    }
                }
                break;
            case _STEIGUNG_PROMILLE:
                if (input.equals(UNENDLICH)) {
                    unendlich = true;
                } else if (input.equals("-" + UNENDLICH)) {
                    minusUnendlich = true;
                } else {
                    try {
                        x = new BigDecimal(input.replace(decsep, '.')).divide(BigDecimal.TEN);
                    } catch (Exception e) {
                        throw new Exception(INVALID);
                    }
                }
                break;
            case _VERHAELTNIS:
                if (input.equals(UNENDLICH) || input.equals("-" + UNENDLICH)) {
                    x = BigDecimal.ZERO;
                } else {
                    x = BigDecimal.ONE.divide(
                            new BigDecimal(input.replace(decsep, '.')).divide(BigDecimal.valueOf(100L), getPrecision() + PRECISION, RoundingMode.HALF_UP), getPrecision() + PRECISION, RoundingMode.HALF_UP);
                }
                break;
            case _NEIGUNGSWINKEL:
                try {
                    x = new BigDecimal(input.replace(decsep, '.'));
                } catch (Exception e) {
                    throw new Exception(INVALID);
                }

                if (x.abs().compareTo(BigDecimal.valueOf(90)) > 0) {
                    throw new Exception(INVALID);
                }
                if (x.abs().compareTo(BigDecimal.valueOf(90)) == 0) {
                    if (x.signum() == -1) {
                        return "-" + UNENDLICH;
                    }
                    return UNENDLICH;
                }
                if (!x.equals(BigDecimal.ZERO.setScale(x.scale()))) {
                    // deg2rad
                    x = (_PI.divide(_180, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    d = x.doubleValue();
                    d = Math.tan(d) * 100.0;
                    x = BigDecimal.valueOf(d);
                }
                break;
        }

        switch (tid) { // steigung (in Prozent) to anything

            case _STEIGUNG:
                break;
            case _STEIGUNG_PROMILLE:
                x = BigDecimal.TEN.multiply(x);
                break;
            case _VERHAELTNIS:
                // is x zero ?
                if (x.equals(BigDecimal.ZERO.setScale(x.scale()))) {
                    return UNENDLICH;
                } else {
                    x = BigDecimal.ONE.divide(
                            x.divide(BigDecimal.valueOf(100L), getPrecision() + PRECISION, RoundingMode.HALF_UP), getPrecision() + PRECISION, RoundingMode.HALF_UP);
                }
                break;
            case _NEIGUNGSWINKEL:
                if (unendlich) {
                    return "90";
                }
                if (minusUnendlich) {
                    return "-90";
                }
                d = x.doubleValue();
                d = Math.atan(d / 100.0);
                x = BigDecimal.valueOf(d);
                // rad2deg
                x = (_180.divide(_PI, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                break;
        }


        if (getScientific()) {
            return GeneralMath.decimal2Scientific(x.toPlainString(), getPrecision()).replace('.', decsep);
        } else {
            return x.setScale(getPrecision(), RoundingMode.HALF_UP).toPlainString().replace('.', decsep);
        }
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
        BigDecimal big = new BigDecimal(input.replace(decsep, '.'));
        big = big.add(plus);
        return big.toPlainString().replace('.', decsep);
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return setValue(big, sourceUnit);
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return input.replace('.', decsep);
    }
    
    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return getTransferValue(sourceUnit);
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, sourceUnit).replace(decsep, '.');
    }

    @Override
    public String getCard() {
        return "sciCard";
    }
    
}
