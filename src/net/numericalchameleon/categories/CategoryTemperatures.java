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


public class CategoryTemperatures extends CategoryObject {
      
    private final static int
            CKELVIN = 0,
            CCELSIUS = 1,
            CPLANCK = 2,
            CFAHRENHEIT = 3,
            CRANKINE = 4,
            CNEWTON = 5,
            CDELISLE = 6,
            CREAUMUR = 7,
            CROMER = 8,
            CGASMARK = 9;
            //CWEDGWOOD = 10;

    private final static BigDecimal
            _5 = BigDecimal.valueOf(5),
            _4 = BigDecimal.valueOf(4),
            _9 = BigDecimal.valueOf(9),
            _32 = BigDecimal.valueOf(32),
            _273 = new BigDecimal("273.15"),
            _PLANCK = new BigDecimal("1.416785e32");

    private static String
            ABSOLUTEZERO, INVALID;
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    /**
     * Creates new CategoryTemperatures
     * @param categoryInterface
     */
    public CategoryTemperatures(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();

        INVALID = localize("Moduls.sourceInvalid", "source invalid");
        ABSOLUTEZERO = localize("Temperature.CannotBeColderThanAbsoluteZero",
                "It cannot be colder than Absolute Zero (0 Kelvin)");

        // 1954, redefiniert 1967, benannt nach Lord Kelvin
        String KELVIN = localize("Temperature.kelvin", "Kelvin (K)");
        // 1742, Anders Celsius (1701-1744), schwedischer Astronom
        String CENTIGRADE = localize("Temperature.centigrade", "Centigrade (°C)");
        // 1709, Daniel Gabriel Fahrenheit (1686-1736), deutscher Physiker
        String FAHRENHEIT = localize("Temperature.fahrenheit", "Fahrenheit (°F)");
        // 1859, William John Macquorn Rankine (1820-1872), schottischer Ingenieur und Physiker
        String RANKINE = localize("Temperature.rankine", "Rankine (°Ra, °R)");
        // 1730, René Antoine Ferchault de Réaumur (1683-1757), französischer Naturforscher
        String REAUMUR = localize("Temperature.reaumur",  "Réaumur (°Ré, °Re)");
        // 1732, Joseph Nicolas Delisle (1688-1768), französischer Astronomen
        String DELISLE = localize("Temperature.delisle", "Delisle (°De, °D)");
        // ~1700, Isaac Newton (1643-1727), englischer Physiker
        String NEWTON = localize("Temperature.newton", "Newton (°N)");
        // 1701, Ole Christensen Rømer (1644-1710), dänischer Astronom
        String ROMER = localize("Temperature.romer","Rømer (°Rø)");
        // Max Karl Ernst Ludwig Planck (1858-1947), deutscher Physiker
        String PLANCK = localize("Temperature.planck", "Planck-Temperatur (Tp)");
        // https://en.wikipedia.org/wiki/Gas_Mark
        String GASMARK = localize("Temperature.gasmark", "Gas mark");
        // Josiah Wedgwood (12.07.1730-03.01.1795), englischer Unternehmer
        //String WEDGWOOD = localize("Temperature.wedgwood", "Wedgwood scale (°W)");

        units = new ArrayList<>();
        units.add(new Unit(CKELVIN, KELVIN, "siu"));
        units.add(new Unit(CCELSIUS, CENTIGRADE, "int"));
        units.add(new Unit(CPLANCK, PLANCK, "int"));
        units.add(new Unit(CFAHRENHEIT, FAHRENHEIT, "us"));
        units.add(new Unit(CRANKINE, RANKINE, "us"));
        units.add(new Unit(CFAHRENHEIT, FAHRENHEIT, "ca"));
        units.add(new Unit(CRANKINE, RANKINE, "ca"));
        units.add(new Unit(CFAHRENHEIT, FAHRENHEIT, "bz"));
        units.add(new Unit(CNEWTON, NEWTON, "gb", false));
        units.add(new Unit(CGASMARK, GASMARK, "gb", true));
        //units.add(new Unit(CWEDGWOOD, WEDGWOOD, "gb", false));
        units.add(new Unit(CDELISLE, DELISLE, "ru", false));
        units.add(new Unit(CREAUMUR, REAUMUR, "ru", false));
        units.add(new Unit(CREAUMUR, REAUMUR, "fr", false));
        units.add(new Unit(CREAUMUR, REAUMUR, "de", false));
        units.add(new Unit(CROMER, ROMER, "dk", false));
        
        transferUnit = units.get(0);
        defaultSourceUnit = units.get(1);
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
        return CKELVIN;
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return CCELSIUS;
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
        
        BigDecimal x;

        try {
            x = new BigDecimal(input.replace(decsep, '.'));
        } catch (Exception e) {
            throw new Exception(INVALID);
        }

        if (sid != tid) {

            switch (sid) { // anything to Kelvin
            
                case CKELVIN:
                    break;
                case CCELSIUS:
                    x = x.add(_273);
                    break;
                case CPLANCK:
                    x = _PLANCK.multiply(x);
                    break;
                case CFAHRENHEIT:
                    x = (_5.divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x.subtract(_32)).add(_273);
                    break;
                case CREAUMUR:
                    x = (_5.divide(_4, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x).add(_273);
                    break;
                case CRANKINE:
                    x = (_5.divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    break;

                // (x - 7,5) · 40/21 + 273,15
                case CROMER:
                    x = (x.subtract(new BigDecimal("7.5"))).multiply(BigDecimal.valueOf(40).divide(BigDecimal.valueOf(21), getPrecision() + PRECISION, RoundingMode.HALF_UP)).add(_273);
                    break;
                // 373,15 ? x · 2/3
                case CDELISLE:
                    x = new BigDecimal("373.15").subtract(
                            x.multiply(
                            BigDecimal.valueOf(2).divide(BigDecimal.valueOf(3), getPrecision() + PRECISION, RoundingMode.HALF_UP)));
                    break;

                // x · 100/33 + 273,15
                case CNEWTON:
                    x = x.multiply(
                            BigDecimal.valueOf(100).divide(BigDecimal.valueOf(33), getPrecision() + PRECISION, RoundingMode.HALF_UP)).add(_273);
                    break;
                    
                // case CWEDGWOOD:
                // break;
                    
                case CGASMARK:
                    if (x.compareTo(BigDecimal.ONE) < 0) {
                        throw new Exception(INVALID);
                    }
                    // gas mark to fahrenheit
                    // f = (g-1)*25 + 275
                    x = (x.subtract(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(25))).add(BigDecimal.valueOf(275));
                    // fahrenheit to kelvin
                    x = (_5.divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x.subtract(_32)).add(_273);
                    break;
                    /*
                    // gas mark to degrees
                    // D = (G * 14) + 121
                    x = x.multiply(BigDecimal.valueOf(14));
                    x = x.add(BigDecimal.valueOf(121));
                    // degrees to kelvin
                    x = x.add(_273);
                    break;
                    */
            }

            // check whether Kelvin is smaller than Absolute Zero (absoluter Nullpunkt)
            if (x.compareTo(BigDecimal.ZERO) < 0) {
                throw new Exception(ABSOLUTEZERO);
            }

            switch (tid) // kelvin to anything
            {
                case CKELVIN:
                    break;
                case CCELSIUS:
                    x = x.subtract(_273);
                    break;
                case CFAHRENHEIT:
                    x = (_9.divide(_5, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x.subtract(_273)).add(_32);
                    break;
                case CPLANCK:
                    x = x.divide(_PLANCK, getPrecision() + PRECISION, RoundingMode.HALF_UP);
                    break;
                case CREAUMUR:
                    x = (_4.divide(_5, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x.subtract(_273));
                    break;
                case CRANKINE:
                    x = (_9.divide(_5, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    break;

                // (TK - 273,15) · 21/40 + 7,5
                case CROMER:
                    x = (x.subtract(_273)).multiply(BigDecimal.valueOf(21).divide(BigDecimal.valueOf(40), getPrecision() + PRECISION, RoundingMode.HALF_UP)).add(new BigDecimal("7.5"));
                    break;
                // (373,15 - TK) · 1,5
                case CDELISLE:
                    x = new BigDecimal("373.15").subtract(x).multiply(new BigDecimal("1.5"));
                    break;

                // (x - 273,15) · 0,33
                case CNEWTON:
                    x = (x.subtract(_273)).multiply(new BigDecimal("0.33"));
                    break;

                case CGASMARK:
                    // weniger als 275 Grad Fahrenheit (408.15 Kelvin) würde ein Gas Mark unterhalb von 1 ergeben
                    // z. Zt. werden nur Gas Mark >= 1 unterstützt
                    if (x.compareTo(BigDecimal.valueOf(408.15)) < 0) {
                        throw new Exception(INVALID);
                    }
                    // kelvin to fahrenheit
                    x = (_9.divide(_5, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x.subtract(_273)).add(_32);
                    
                    // fahrenheit to gas mark
                    // g = (f-250) / 25
                    x = (x.subtract(BigDecimal.valueOf(250))).divide(BigDecimal.valueOf(25), getPrecision()+PRECISION, RoundingMode.HALF_UP);
                    break;
            }
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
        BigDecimal big = null;
        try {
           big = new BigDecimal(input.replace(decsep, '.'));
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
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
