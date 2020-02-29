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

import fi.joensuu.joyds1.calendar.Calendar;
import fi.joensuu.joyds1.calendar.GregorianCalendar;
import fi.joensuu.joyds1.calendar.IslamicCalendar;
import fi.joensuu.joyds1.calendar.MayanCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.numericalchameleon.data.Unit;

public class CategoryCalendarSystems extends CategoryObject {

    private final static int _TRANSFER = -1,
            _GREGORIAN = 0,
            _MAYAN = 1,
            _ISLAMIC = 2;
    private static String INVALID;
    private static Pattern patternGreg, patternMaya, patternIslamic;
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit, internalCalcUnit;

    /**
     * Creates new CategoryCalendarSystems
     * @param categoryInterface
     */
    public CategoryCalendarSystems(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");

        units = new ArrayList<>();
        units.add(new Unit(_GREGORIAN, localize("Calendars.gregorianCalendar", "Gregorian Calendar"), "int"));
        units.add(new Unit(_MAYAN, localize("Calendars.mayaCalendarLongCount", "Maya Calendar (long count)"), "int"));
        units.add(new Unit(_ISLAMIC, localize("Calendars.islamicCalendar", "Islamic Calendar"), "int"));

        transferUnit = new Unit(_TRANSFER);
        defaultSourceUnit = units.get(0);
        internalCalcUnit = units.get(0);
        defaultTargetUnit = units.get(2);
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return units;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return units;
    }

    @Override
    public int getSourceDefault() {
        return _GREGORIAN;
    }

    @Override
    public int getTargetDefault() {
        return _ISLAMIC;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    // defaultValue is transferValue
    // is there any special transverValue ?
    public boolean isSpecialDefaultValue() {
        return true;
    }

    @Override
    public String getInitialValue() {
        return new GregorianCalendar().toString();
    }

    @Override
    public boolean isScientificSupported() {
        return false;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
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

        Matcher matcher;
        Calendar calendar = new GregorianCalendar();

        switch (sid) { // anything to GregGalendar

            case _TRANSFER:
                // YYYYMMDD
                int value = Integer.parseInt(input);
                int year = value / 10000;
                int month = (value / 100) - (year * 100);
                int day = value % 100;
                calendar.set(year, month, day);
                //set(calendar, String.format("%04d-%02d-%02d", year, month, day));
                break;

            case _GREGORIAN:
                // YYYY-MM-DD
                set(calendar, input);
                break;

            case _MAYAN:
                // 99.99.99.99.99
                if (patternMaya == null) {
                    patternMaya = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
                }
                matcher = patternMaya.matcher(input);
                if (matcher.find()) {
                    int[] longCount = new int[5];
                    for (int i = 0; i < 5; i++) {
                        longCount[i] = Integer.parseInt(matcher.group(i + 1));
                    }
                    Calendar maya = new MayanCalendar(longCount[0], longCount[1], longCount[2],
                            longCount[3], longCount[4]);
                    calendar.set(maya);
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case _ISLAMIC:
                if (patternIslamic == null) {
                    patternIslamic = Pattern.compile("^(\\d+)-(\\d+)-(\\d+)$");
                }
                matcher = patternIslamic.matcher(input);
                if (matcher.find()) {
                    int[] tokens = new int[3];
                    for (int i = 0; i < 3; i++) {
                        tokens[i] = Integer.parseInt(matcher.group(i + 1));
                    }
                    Calendar islamic = new IslamicCalendar(tokens[0], tokens[1], tokens[2]);
                    calendar.set(islamic);
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

        }

        switch (tid) { // GregCalendar to anything

            case _TRANSFER:
                return String.format("%04d%02d%02d",
                        calendar.getYear(), calendar.getMonth(), calendar.getDay());

            case _GREGORIAN:
                return calendar.toString();

            case _MAYAN:
                try {
                    Calendar maya = new MayanCalendar(calendar);
                    return maya.toString();
                } catch (Exception e) {
                    throw new IllegalArgumentException(INVALID);
                }
            case _ISLAMIC:
                try {
                    Calendar islamic = new IslamicCalendar(calendar);
                    return islamic.toString();
                } catch (Exception e) {
                    throw new IllegalArgumentException(INVALID);
                }
        }

        return null;
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = units.get(sourceindex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = getInitialValue();
            input = getOutput(internalCalcUnit, sourceUnit);
        }
        Calendar cal = new GregorianCalendar();
        set(cal, getOutput(sourceUnit, internalCalcUnit));
        // 0.1 days makes no sense, so round it up in any case
        int addDays = plus.toBigInteger().intValue();
        if (addDays == 0) { addDays = 1; }
        cal.addDays(addDays);
        setInput(cal.toString());
        return getOutput(internalCalcUnit, sourceUnit);
    }

    private static void set(Calendar calendar, String input) throws IllegalArgumentException {
        if (patternGreg == null) {
            patternGreg = Pattern.compile("^([-]??\\d+)\\-(\\d+)\\-(\\d+)$");
        }
        Matcher matcher = patternGreg.matcher(input);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            int day = Integer.parseInt(matcher.group(3));
            calendar.set(year, month, day);
        } else {
            throw new IllegalArgumentException(INVALID);
        }
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
