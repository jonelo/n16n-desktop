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
package net.numericalchameleon.util.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import jonelo.sugar.datetime.ExtendedGregorianCalendar;

public class DateQueries {

    private static int yearView(int year, int factor) {
        int major = year / factor;
        int minor = year % factor;
        if (minor > 0) {
            major++;
        }
        return major;
    }

    /**
     * Returns the decade of a given year
     *
     * @param year
     * @return the decade of a given year
     */
    public static int decade(int year) {
        return yearView(year, 10);
    }

    /**
     * Returns the century of a given year
     *
     * @param year
     * @return the century of a given year
     */
    public static int century(int year) {
        return yearView(year, 100);
    }

    /**
     * Returns the millennium of a given year
     *
     * @param year
     * @return the millennium of a given year
     */
    public static int millennium(int year) {
        return yearView(year, 1000);
    }

    /**
     * Returns the season of a given date
     *
     * @param gc the date
     * @param seasons a four item String array that contains the names of the
     * seasons
     * @param timeZone the time zone for which the season should be calculated
     * @return
     */
    public static String season(GregorianCalendar gc, String[] seasons, String timeZone, boolean north) {

        String season = null;
        int year = gc.get(Calendar.YEAR);
        ExtendedGregorianCalendar egc = new ExtendedGregorianCalendar();
        egc.setTimeZone(TimeZone.getTimeZone(timeZone));
        int index = 0;
        switch (gc.get(Calendar.MONTH)) {
            case Calendar.APRIL:
            case Calendar.MAY:
                index = 0; // Frühling
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
                index = 1; // Sommer
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
                index = 2; // Herbst
                break;
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
                index = 3; // Winter
                break;

            case Calendar.MARCH:
                egc.setEquinox(ExtendedGregorianCalendar.EQUINOX_MARCH, year);
                if (gc.get(Calendar.DATE) >= egc.get(Calendar.DATE)) {
                    index = 0; // Frühling
                } else {
                    index = 3; // Winter
                }
                break;
            case Calendar.JUNE:
                egc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_JUNE, year);
                if (gc.get(Calendar.DATE) >= egc.get(Calendar.DATE)) {
                    index = 1; // Sommer
                } else {
                    index = 0; // Frühling
                }
                break;
            case Calendar.SEPTEMBER:
                egc.setEquinox(ExtendedGregorianCalendar.EQUINOX_SEPTEMBER, year);
                if (gc.get(Calendar.DATE) >= egc.get(Calendar.DATE)) {
                    index = 2; // Herbst
                } else {
                    index = 1; // Sommer
                }
                break;
            case Calendar.DECEMBER:
                egc.setSolstice(ExtendedGregorianCalendar.SOLSTICE_DECEMBER, year);
                if (gc.get(Calendar.DATE) >= egc.get(Calendar.DATE)) {
                    index = 3; // Winter
                } else {
                    index = 2; // Sommer
                }
                break;
        }
        // in the southern hemisphere it is the opposite of the north
        if (!north) {
            switch (index) {
                case 0:
                    return seasons[2];
                case 1:
                    return seasons[3];
                case 2:
                    return seasons[0];
                case 3:
                    return seasons[1];
            }
        }
        return seasons[index];
    }

    /**
     * Returns the western zodiac sign of a given day
     *
     * @param month the month
     * @param day the day
     * @param names a String array containing 12 names for each sign
     * @return
     */
    public static String westernZodiacSign(int month, int day, String[] names) {

        if (names.length < 12) {
            throw new IllegalArgumentException("12 zodiac sign names expected");
        }
        int ref = (month + 1) * 100 + day;

        // Widder
        if ((ref >= 321) && (ref <= 420)) {
            return names[0];
        }
        // Stier
        if ((ref >= 421) && (ref <= 520)) {
            return names[1];
        }
        // Zwillinge
        if ((ref >= 521) && (ref <= 621)) {
            return names[2];
        }
        // Krebs
        if ((ref >= 622) && (ref <= 723)) {
            return names[3];
        }
        // Löwe
        if ((ref >= 724) && (ref <= 823)) {
            return names[4];
        }
        // Jungfrau
        if ((ref >= 824) && (ref <= 923)) {
            return names[5];
        }
        // Waage
        if ((ref >= 924) && (ref <= 1023)) {
            return names[6];
        }
        // Skorpion
        if ((ref >= 1024) && (ref <= 1122)) {
            return names[7];
        }
        // Schütze
        if ((ref >= 1123) && (ref <= 1221)) {
            return names[8];
        }
        // Steinbock
        if ((ref >= 1222) || (ref <= 120)) {
            return names[9];
        }
        // Wassermann
        if ((ref >= 121) && (ref <= 218)) {
            return names[10];
        }
        // Fische
        if ((ref >= 219) && (ref <= 320)) {
            return names[11];
        }
        return null;
    }

    private static int identicalYear(int year, boolean inTheFuture) {
        ExtendedGregorianCalendar egc = new ExtendedGregorianCalendar();
        egc.set(year, Calendar.JANUARY, 1);
        int dow = egc.get(Calendar.DAY_OF_WEEK);
        boolean leap = egc.isLeapYear();
        do {
            egc.set(inTheFuture ? ++year : --year, Calendar.JANUARY, 1);
        } while (egc.get(Calendar.DAY_OF_WEEK) != dow || egc.isLeapYear() != leap);
        return year;
    }

    public static int identicalYearInTheFuture(int year) {
        return identicalYear(year, true);
    }

    public static int identicalYearInThePast(int year) {
        return identicalYear(year, false);
    }

    public static boolean isLeapYear(int year) {
        ExtendedGregorianCalendar egc = new ExtendedGregorianCalendar();
        egc.set(year, 0, 1);
        return egc.isLeapYear();
    }

}
