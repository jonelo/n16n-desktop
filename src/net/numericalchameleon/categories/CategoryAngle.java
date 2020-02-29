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

public class CategoryAngle extends CategoryObject {

    public final static String PI
            = // 1024 significant figures
            "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989380952572010654858632788";

    private final static int DEGREES = 0,
            minDEGREES = 1,
            secDEGREES = 2,
            RADIANS = 3,
            mRADIANS = 33,
            microRADIANS = 36,
            nanoRADIANS = 39,
            GON = 4,
            cGON = 5,
            mGON = 6,
            PERCENT = 7,
            PROMILLE = 70,
            MIL_NATO = 8,
            MIL_6000 = 6000,
            MIL_6300 = 6300,
            TURN = 9,
            MILLITURNS = 10,
            CENTITURNS = 11,
            NAUTICAL_POINT = 12,
            ZEITMASS = 13,
            ZEITMASS_MINUTES = 14,
            ZEITMASS_SECONDS = 15,
            RIGHT_ANGLE = 16;

    private final static BigDecimal _9 = BigDecimal.valueOf(9),
            _10 = BigDecimal.valueOf(10),
            _60 = BigDecimal.valueOf(60),
            _180 = BigDecimal.valueOf(180),
            _PI = new BigDecimal(CategoryAngle.PI);

    private static String INVALID;

    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    /**
     * Creates new CategoryAngle
     *
     * @param categoryInterface the instance that implements the
     * CategoryInterface
     */
    public CategoryAngle(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");

        units = new ArrayList<>();
        units.add(new Unit(RADIANS,
                localize("Angle.radians", "Radians (rad)"), "siu"));
        units.add(new Unit(mRADIANS,
                localize("Angle.milliRadians", "Milliradians (mrad)"), "siu"));
        units.add(new Unit(microRADIANS,
                "Microradians (µrad)", "siu"));
        units.add(new Unit(nanoRADIANS,
                "Nanoradians (nrad)", "siu"));

        units.add(new Unit(DEGREES,
                localize("Angle.degrees", "Degrees (deg)"), "int"));
        units.add(new Unit(minDEGREES,
                localize("Angle.degreesMinutes", "Minute of arc (')"), "int"));
        units.add(new Unit(secDEGREES,
                localize("Angle.degreesSeconds", "Second of arc ('')"), "int"));

        units.add(new Unit(ZEITMASS,
                localize("Angle.zeitmassHours", "Zeitmaß, Stundenmaß (in h)"), "int"));
        units.add(new Unit(ZEITMASS_MINUTES,
                localize("Angle.zeitmassMinutes", "Zeitmaß, Stundenmaß (in min)"), "int"));
        units.add(new Unit(ZEITMASS_SECONDS,
                localize("Angle.zeitmassSeconds", "Zeitmaß, Stundenmaß (in s)"), "int"));

        units.add(new Unit(GON,
                localize("Angle.gon", "Gon (gon)"), "int"));
        units.add(new Unit(cGON,
                localize("Angle.centiGon", "Centigon (cgon)"), "int"));
        units.add(new Unit(mGON,
                localize("Angle.milliGon", "Milligon (mgon)"), "int"));

        units.add(new Unit(TURN,
                localize("Angle.turn", "Turn"), "int"));
        units.add(new Unit(CENTITURNS,
                localize("Angle.centiTurns", "Centiturns"), "int"));
        units.add(new Unit(MILLITURNS,
                localize("Angle.milliTurns", "Milliturns"), "int"));
        units.add(new Unit(RIGHT_ANGLE,
                localize("Angle.rightAngle", "Right angle"), "int"));

        units.add(new Unit(PERCENT,
                localize("Angle.circleInPercent", "Circle in percent (%)"), "int"));
        units.add(new Unit(PROMILLE,
                localize("Angle.circleInPromille", "Circle in promille (\u2030)"), "int"));

        units.add(new Unit(NAUTICAL_POINT,
                localize("Angle.nauticalPoint", "Nautical Point (\u00AF)"), "int"));

        units.add(new Unit(MIL_NATO,
                localize("Angle.mil", "Angular mil"), "int"));
        units.add(new Unit(MIL_NATO,
                "Artilleriepromille A\u2030", "ch"));

        units.add(new Unit(MIL_6000,
                localize("Angle.mil", "Angular mil"), "su_historic", false));
        units.add(new Unit(MIL_6000,
                localize("Angle.mil", "Angular mil"), "fi", false));
        units.add(new Unit(MIL_6300,
                localize("Angle.mil", "Angular mil") + " (Streck)", "se", false));

        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(4);
        transferUnit = units.get(4);

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
        return 0;
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
    public int getPreferredPrecision() {
        return 12;
    }

    @Override
    public boolean isScientificSupported() {
        return true;
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

        try {
            x = new BigDecimal(input.replace(decsep, '.'));
        } catch (Exception e) {
            throw new Exception(INVALID);
        }

        if (sid != tid) {
            switch (sid) { // anything to degrees (Gradmass)
                case DEGREES:
                    break; // Gradmass (deg)
                case minDEGREES:
                    x = x.divide(_60, getPrecision() + PRECISION, RoundingMode.HALF_UP);
                    break;
                case secDEGREES:
                    x = x.divide(_60.multiply(_60), getPrecision() + PRECISION, RoundingMode.HALF_UP);
                    break;
                case RADIANS: // Bogenmass (rad)
                    x = (_180.divide(_PI, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    break;
                case mRADIANS:
                    x = (_180.divide(_PI, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointLeft(3);
                    break;
                case microRADIANS:
                    x = (_180.divide(_PI, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointLeft(6);
                    break;
                case nanoRADIANS:
                    x = (_180.divide(_PI, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointLeft(9);
                    break;
                // gon2deg (deg = gon*360/400)
                case GON:
                    x = x.multiply(new BigDecimal("0.9")); // 360/400=0.9
                    break;
                case cGON:
                    x = x.multiply(new BigDecimal("0.009"));
                    break;
                case mGON:
                    x = x.multiply(new BigDecimal("0.0009"));
                    break;
                case PERCENT:
                    x = x.multiply(new BigDecimal("3.6")); // 360/100=3.6
                    break;
                case PROMILLE:
                    x = x.multiply(new BigDecimal("0.36")); // 360/1000=0.36
                    break;
                case TURN:
                    x = x.multiply(new BigDecimal("360"));
                    break;
                case MILLITURNS:
                    x = x.multiply(new BigDecimal("0.360"));
                    break;
                case CENTITURNS:
                    x = x.multiply(new BigDecimal("3.60"));
                    break;
                case RIGHT_ANGLE:
                    x = x.multiply(new BigDecimal("90"));
                    break;
                case MIL_NATO:
                    x = x.multiply(new BigDecimal("0.05625")); // 360/6400=9/160=0.05625
                    break;
                case NAUTICAL_POINT:
                    x = x.multiply(BigDecimal.valueOf(11.25));  // 360/32=11.25
                    break;
                case MIL_6000:
                    x = x.multiply(new BigDecimal("0.06")); // 360/6000
                    break;
                case MIL_6300:
                    x = multiplyBy(x, 2, 35); // 360/6300=2/35
                    break;
                case ZEITMASS:
                    x = x.multiply(BigDecimal.valueOf(15)); // 360/24
                    break;
                case ZEITMASS_MINUTES:
                    x = x.multiply(BigDecimal.valueOf(0.25)); // 360/(24*60)=1/4
                    break;
                case ZEITMASS_SECONDS:
                    x = multiplyBy(x, 1, 240); // 360/(24*60*60)=1/240
                    break;
            }

            switch (tid) { // degrees to anything
                case DEGREES:
                    break;
                case minDEGREES:
                    x = x.multiply(_60);
                    break;
                case secDEGREES:
                    x = x.multiply(_60.multiply(_60));
                    break;
                case RADIANS:
                    // deg2rad
                    x = (_PI.divide(_180, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    break;
                case mRADIANS:
                    x = (_PI.divide(_180, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointRight(3);
                    break;
                case microRADIANS:
                    x = (_PI.divide(_180, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointRight(6);
                    break;
                case nanoRADIANS:
                    x = (_PI.divide(_180, getPrecision() + PRECISION, RoundingMode.HALF_UP)).multiply(x);
                    x = x.movePointRight(9);
                    break;
                // deg2gon
                case GON:
                    x = x.multiply(_10.divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case cGON:
                    x = x.multiply((_10.multiply(_10).multiply(_10)).divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case mGON:
                    x = x.multiply((_10.multiply(_10).multiply(_10).multiply(_10)).divide(_9, getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case PERCENT:
                    x = x.multiply(_10.divide(BigDecimal.valueOf(36), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case PROMILLE:
                    x = x.multiply(_10.divide(BigDecimal.valueOf(3.6), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case MIL_NATO:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(0.05625), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case MIL_6000:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(0.06), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case MIL_6300:
                    x = x.multiply(BigDecimal.valueOf(17.5)); // 6300/360=17.5
                    break;
                case NAUTICAL_POINT:
                    x = x.multiply(BigDecimal.valueOf(32).divide(BigDecimal.valueOf(360), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case TURN:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(360), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case MILLITURNS:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(0.360), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case CENTITURNS:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(3.60), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case RIGHT_ANGLE:
                    x = x.multiply(BigDecimal.ONE.divide(BigDecimal.valueOf(90), getPrecision() + PRECISION, RoundingMode.HALF_UP));
                    break;
                case ZEITMASS:
                    x = multiplyBy(x, 1, 15);
                    break;
                case ZEITMASS_MINUTES:
                    x = x.multiply(BigDecimal.valueOf(4));
                    break;
                case ZEITMASS_SECONDS:
                    x = x.multiply(BigDecimal.valueOf(240));
                    break;
            }
        }

        if (getScientific()) {
            return GeneralMath.decimal2Scientific(x.toPlainString(), getPrecision()).replace('.', decsep);
        } else {
            return x.setScale(getPrecision(), RoundingMode.HALF_UP).toPlainString().replace('.', decsep);
        }
    }

    private BigDecimal multiplyBy(BigDecimal input, long z, long n) {
        return input.multiply(BigDecimal.valueOf(z).divide(BigDecimal.valueOf(n), getPrecision() + PRECISION, RoundingMode.HALF_UP));
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = units.get(sourceindex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) { // empty string
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
