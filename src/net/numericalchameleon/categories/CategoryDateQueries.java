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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import jonelo.sugar.datetime.CalendarTools;
import jonelo.sugar.datetime.ExtendedGregorianCalendar;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.util.calendar.ChineseYearInfo;
import net.numericalchameleon.util.calendar.DateQueries;
import net.numericalchameleon.util.calendar.DateYMD;

public class CategoryDateQueries extends CategoryObject {

    private final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // sourceprivate final static int // source
            cDATE = 100,
            // target
            cLEAPYEAR = 101,
            cDECADE = 102,
            cCENTURY = 103,
            cMILLENNIUM = 104,
            cIDENTICALYEARFUTURE = 105,
            cIDENTICALYEARPAST = 106,
            cDAY_OF_WEEK = 107,
            cDAY_OF_YEAR = 108,
            cWEEK_OF_YEAR = 109,
            cMONTH = 110,
            cWEEK_OF_MONTH = 111,
            cDAYS_IN_MONTH = 112,
            cAGE = 113,
            cAGE_IN_DAYS = 114,
            cZODIACSIGN = 115,
            cSUNSIGNS = 116,
            cSEASON_DE = 117,
            cDAYS_REMAINING_UNTIL_END_OF_YEAR = 118,
            cDAYS_REMAINING_UNTIL_END_OF_MONTH = 119,
            cCHINESE_YEAR = 120,
            _TRANSFER = -1;
    private final String DATE, YES, NO, INVALID;
    private final ArrayList<Unit> sourceUnits, targetUnits;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    public CategoryDateQueries(CategoryInterface clusterInterface) {
        // localization
        rb = clusterInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        YES = localize("GUI.General.Yes", "Yes");
        NO = localize("GUI.General.No", "No");
        DATE = localize("Calendar.date", "Date");
       
        // init source units
        sourceUnits = new ArrayList<>();        
        sourceUnits.add(new Unit(cDATE, DATE, "int"));        

        // init target units
        targetUnits = new ArrayList<Unit>();
        targetUnits.add(new Unit(cDAY_OF_WEEK,
                localize("Calendar.dayOfWeek", "Day of week"), "int"));
        targetUnits.add(new Unit(cDAY_OF_YEAR,
                localize("Calendar.dayOfYear", "Day of year"), "int"));
        targetUnits.add(new Unit(cDAYS_REMAINING_UNTIL_END_OF_YEAR,
                localize("Calendar.daysRemainingUntilEndOfYear", "Days remaining until end of year"), "int"));
        targetUnits.add(new Unit(cDAYS_REMAINING_UNTIL_END_OF_MONTH,
                localize("Calendar.daysRemainingUntilEndOfMonth", "Days remaining until end of month"), "int"));
        targetUnits.add(new Unit(cWEEK_OF_YEAR,
                localize("Calendar.weekOfYear", "Week of year"), "int"));
        targetUnits.add(new Unit(cWEEK_OF_MONTH,
                localize("Calendar.weekOfMonth", "Week of month"), "int"));
        targetUnits.add(new Unit(cDAYS_IN_MONTH,
                localize("Calendar.daysInMonth", "Days in month"), "int"));
        targetUnits.add(new Unit(cMONTH,
                localize("Calendar.month", "Month"), "int"));
        targetUnits.add(new Unit(cLEAPYEAR,
                localize("Calendar.leapyear", "Leapyear"), "int"));
        targetUnits.add(new Unit(cDECADE,
                localize("Calendar.decade", "Decade"), "int"));
        targetUnits.add(new Unit(cCENTURY,
                localize("Calendar.century", "Century"), "int"));
        targetUnits.add(new Unit(cMILLENNIUM,
                localize("Calendar.millennium", "Millennium"), "int"));
        targetUnits.add(new Unit(cIDENTICALYEARPAST,
                localize("Calendar.identicalYearPast", "Identical year in the past (weekdays)"), "int"));
        targetUnits.add(new Unit(cIDENTICALYEARFUTURE,
                localize("Calendar.identicalYearFuture", "Identical year in the future (weekdays)"), "int"));
        targetUnits.add(new Unit(cAGE,
                localize("Calendar.age", "Age"), "int"));
        targetUnits.add(new Unit(cAGE_IN_DAYS,
                localize("Calendar.ageInDays", "Age in days"), "int"));
        targetUnits.add(new Unit(cCHINESE_YEAR,
                localize("Calendar.chineseYear", "Chinese year"), "int"));
        targetUnits.add(new Unit(cZODIACSIGN,
                localize("Calendar.zodiacSign", "Zodiac sign"), "int"));
        targetUnits.add(new Unit(cSEASON_DE,
                localize("Calendar.season", "Jahreszeit"), "de"));

        // init transfer unit
        transferUnit = new Unit(_TRANSFER);

        // init default units
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
    public boolean isOneway() {
        return true;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = sourceUnits.get(s);
        Unit targetUnit = targetUnits.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        GregorianCalendar gc = new GregorianCalendar();
        gc.setLenient(false);

        switch (sid) {

            case _TRANSFER:
                // YYYYMMDD
                try {
                    int value = Integer.parseInt(input);
                    int year = value / 10000;
                    int month = (value / 100) - (year * 100);
                    int day = value % 100;
                    gc.set(year, month - 1, day);
                } catch (NumberFormatException e) {
                    throw new Exception(INVALID);
                }
                break;

            case cDATE:
                try {
                    DateFormat df = DateFormat.getDateInstance();
                    gc.setTime(df.parse(input));
                } catch (ParseException e) {
                    throw new Exception(INVALID);
                }
                break;
        }

        switch (tid) {

            case _TRANSFER: {
                return String.format("%04d%02d%02d",
                        gc.get(Calendar.YEAR),
                        gc.get(Calendar.MONTH) + 1,
                        gc.get(Calendar.DATE));
            }

            case cDATE: {
                DateFormat df = DateFormat.getDateInstance();
                return df.format(gc.getTime());
            }

            case cLEAPYEAR: {
                return DateQueries.isLeapYear(gc.get(Calendar.YEAR)) ? YES : NO;
            }

            case cDECADE: {
                return DateQueries.decade(gc.get(Calendar.YEAR)) + ".";
            }

            case cCENTURY: {
                return DateQueries.century(gc.get(Calendar.YEAR)) + ".";
            }

            case cMILLENNIUM: {
                return DateQueries.millennium(gc.get(Calendar.YEAR)) + ".";
            }

            case cIDENTICALYEARFUTURE: {
                return Integer.toString(DateQueries.identicalYearInTheFuture(gc.get(Calendar.YEAR)));
            }

            case cIDENTICALYEARPAST: {
                return Integer.toString(DateQueries.identicalYearInThePast(gc.get(Calendar.YEAR)));
            }

            case cWEEK_OF_YEAR: {
                return Integer.toString(gc.get(Calendar.WEEK_OF_YEAR));
            }

            case cWEEK_OF_MONTH: {
                return Integer.toString(gc.get(Calendar.WEEK_OF_MONTH));
            }

            case cDAY_OF_YEAR: {
                return Integer.toString(gc.get(Calendar.DAY_OF_YEAR));
            }

            case cDAYS_REMAINING_UNTIL_END_OF_YEAR: {
                int leapYear = gc.isLeapYear(gc.get(Calendar.YEAR)) ? 1 : 0;
                return Integer.toString(365 + leapYear - gc.get(Calendar.DAY_OF_YEAR));
            }

            case cDAYS_REMAINING_UNTIL_END_OF_MONTH: {
                return Integer.toString(CalendarTools.daysInMonth(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH)) - gc.get(Calendar.DAY_OF_MONTH));
            }

            case cDAYS_IN_MONTH: {
                return Integer.toString(gc.getActualMaximum(Calendar.DAY_OF_MONTH));
            }

            case cDAY_OF_WEEK: {
                SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
                return formatter.format(gc.getTime());
            }

            case cMONTH: {
                SimpleDateFormat formatter = new SimpleDateFormat("MMMM");
                return formatter.format(gc.getTime());
            }

            case cAGE: {
                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - gc.get(Calendar.YEAR);
                gc.add(Calendar.YEAR, age);
                if (today.before(gc)) {
                    age--;
                }
                return Integer.toString(age);
            }

            case cAGE_IN_DAYS: {
                Calendar today = Calendar.getInstance();
                return Integer.toString(ExtendedGregorianCalendar.getDaysBetween(gc, today));
            }

            case cSEASON_DE: {
                String[] seasons = {
                    localize("Calendar.seasons.Spring", "Frühling"),
                    localize("Calendar.seasons.Summer", "Sommer"),
                    localize("Calendar.seasons.Autumn", "Herbst"),
                    localize("Calendar.seasons.Winter", "Winter")
                };
                String timeZone = "Europe/Berlin";
                return DateQueries.season(gc, seasons, timeZone, true);
            }

            case cZODIACSIGN: {
                String[] STERNZEICHEN = {
                    localize("Calendar.zodiacSigns.Aries", "Aries"),
                    localize("Calendar.zodiacSigns.Taurus", "Taurus"),
                    localize("Calendar.zodiacSigns.Gemini", "Gemini"),
                    localize("Calendar.zodiacSigns.Cancer", "Cancer"),
                    localize("Calendar.zodiacSigns.Leo", "Leo"),
                    localize("Calendar.zodiacSigns.Vigro", "Vigro"),
                    localize("Calendar.zodiacSigns.Libra", "Libra"),
                    localize("Calendar.zodiacSigns.Scorpio", "Scorpio"),
                    localize("Calendar.zodiacSigns.Sagittarius", "Sagittarius"),
                    localize("Calendar.zodiacSigns.Capricorn", "Capricorn"),
                    localize("Calendar.zodiacSigns.Aquarius", "Aquarius"),
                    localize("Calendar.zodiacSigns.Pisces", "Pisces")
                };
                return DateQueries.westernZodiacSign(gc.get(Calendar.MONTH), gc.get(Calendar.DATE), STERNZEICHEN);
            }
            
            case cCHINESE_YEAR: {
                String[] EARTHLY_BRANCHES = {
                    localize("Calendar.chineseYear.earthlyBranch.Rat", "Rat"),
                    localize("Calendar.chineseYear.earthlyBranch.Ox", "Ox"),
                    localize("Calendar.chineseYear.earthlyBranch.Tiger", "Tiger"),
                    localize("Calendar.chineseYear.earthlyBranch.Rabbit", "Rabbit"),
                    localize("Calendar.chineseYear.earthlyBranch.Dragon", "Dragon"),
                    localize("Calendar.chineseYear.earthlyBranch.Snake", "Snake"),
                    localize("Calendar.chineseYear.earthlyBranch.Horse", "Horse"),
                    localize("Calendar.chineseYEar.earthlyBranch.Goat", "Goat"),
                    localize("Calendar.chineseYear.earthlyBranch.Monkey", "Monkey"),
                    localize("Calendar.chineseYear.earthlyBranch.Rooster", "Rooster"),
                    localize("Calendar.chineseYear.earthlyBranch.Dog", "Dog"),
                    localize("Calendar.chineseYear.earthlyBranch.Pig", "Pig")
                };
                String[] ELEMENTS = {
                    localize("Calendar.chineseYear.heavenlyStems.Wood", "Wood"),
                    localize("Calendar.chineseYear.heavenlyStems.Fire", "Fire"),
                    localize("Calendar.chineseYear.heavenlyStems.Earth", "Earth"),
                    localize("Calendar.chineseYear.heavenlyStems.Metal", "Metal"),
                    localize("Calendar.chineseYear.heavenlyStems.Water", "Water")
                };
                String[] YING_AND_YANG = {
                    localize("Calendar.chineseYear.YingYang.Ying","Ying"),
                    localize("Calendar.chineseYear.YingYang.Yang","Yang")
                };
                
                try {
                    ChineseYearInfo cy = new ChineseYearInfo(gc);
                    return String.format("%s (%s, %s)",
                            EARTHLY_BRANCHES[cy.getEarthlyBranch()],
                            ELEMENTS[cy.getHeavenlyStem()],
                            YING_AND_YANG[cy.getYingYang()]
                    );

                } catch (IllegalArgumentException e) {
                    System.err.println(e);
                    throw new Exception(INVALID);
                }

            }
        }
        return "";
    }

    @Override
    public String getInitialValue() {
        DateFormat df = DateFormat.getDateInstance();
        return df.format(new Date());
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceindex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = getDefaultTransferValue();
            input = getOutput(transferUnit, sourceUnit);
        }
        // get the value from source in normalized form
        String temp = getOutput(sourceUnit, transferUnit);
        try {
            DateYMD date = new DateYMD(temp);
            date.addDays(plus.intValue());
            input = date.toString();
            return getOutput(transferUnit, sourceUnit);
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);
        return setValue(big, sourceUnit);
    }

    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(transferUnit, sourceUnit);
    }

    // the default value for the defaultSourceUnit
    public static String getDefaultTransferValue() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(new Date());
    }

    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);
        return getTransferValue(sourceUnit);
    }

    @Override
    public String getTransferValue(Unit source) throws Exception {
        return getOutput(source, transferUnit);
    }

    @Override
    public String getCard() {
        return "calCard";
    }

}
