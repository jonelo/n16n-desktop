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

import fi.joensuu.joyds1.calendar.ChineseCalendar;
import fi.joensuu.joyds1.calendar.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChineseCalendarCalculations {

    public static final int RAT = 0;
    public static final int OX = 1;
    public static final int TIGER = 2;
    public static final int RABBIT = 3;
    public static final int DRAGON = 4;
    public static final int SNAKE = 5;
    public static final int HORSE = 6;
    public static final int GOAT = 7;
    public static final int MONKEY = 8;
    public static final int ROOSTER = 9;
    public static final int DOG = 10;
    public static final int PIG = 11;

    private static final int[] EARTHLY_BRANCHES = {
        // order in this array is important, the modulo operation in the algorithm relies on it
        MONKEY, ROOSTER, DOG, PIG, RAT, OX, TIGER, RABBIT, DRAGON, SNAKE, HORSE, GOAT
    };

    public static final int WOOD = 0;
    public static final int FIRE = 1;
    public static final int EARTH = 2;
    public static final int METAL = 3;
    public static final int WATER = 4;

    private static final int[] HEAVENLY_STEMS = {
        // order in this array is important, the modulo operation in the algorithm relies on it
        METAL, METAL, WATER, WATER, WOOD, WOOD, FIRE, FIRE, EARTH, EARTH
    };

    /*
    public static enum YingYang {
        YING(0), YANG(1);
        private final int value;
        private YingYang(int value) {
            this.value = value;
        }
    }
     */
    public static final int YING = 0;
    public static final int YANG = 1;

    private static final int[] YING_YANG = {
        // order in this array is important, the modulo operation in the algorithm relies on it
        YANG, YING
    };

    /**
     * Returns the start date of the Chinese New Year of a given year
     *
     * @param year the year in the Gregorian calendar
     * @return the start date of the Chinese New Year of a given year
     */
    public static java.util.GregorianCalendar chineseNewYearStartDate(int year) {
        return chineseNewYearStartDate_OffsetImpl(year);
    }

    // we only need 697 bytes in order to improve the performance significantly
    private static final byte[] chineseNewYearOffsets = {
        26, 15, 4, 21, 11, 0, 19, 8, 27, 16, 5, 23, 12, 2, 21, 9, 28, 18, 7,
        25, 14, 3, 22, 11, 0, 19, 9, 27, 16, 5, 24, 12, 2, 21, 10, 28, 17, 6,
        25, 13, 3, 22, 12, 0, 19, 8, 27, 15, 4, 23, 13, 2, 21, 10, 29, 18, 7,
        26, 15, 4, 23, 13, 2, 20, 9, 27, 17, 5, 24, 14, 3, 21, 10, 29, 18, 7,
        26, 15, 5, 23, 12, 1, 20, 8, 27, 17, 6, 24, 14, 3, 22, 10, 29, 18, 8,
        26, 15, 5, 23, 11, 1, 19, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28, 18, 8,
        27, 15, 4, 23, 12, 0, 19, 9, 28, 17, 6, 25, 14, 2, 21, 10, 29, 18, 7,
        26, 15, 3, 22, 12, 1, 19, 9, 28, 17, 5, 24, 13, 3, 21, 10, 0, 19, 7,
        26, 15, 4, 23, 13, 2, 21, 10, 28, 17, 7, 24, 14, 4, 23, 11, 0, 19, 8,
        26, 15, 5, 24, 13, 2, 21, 10, 28, 17, 6, 25, 14, 4, 23, 12, 30, 19, 8,
        27, 15, 5, 24, 13, 2, 20, 9, 28, 17, 6, 25, 15, 3, 22, 11, 30, 18, 8,
        27, 16, 5, 24, 13, 2, 20, 9, 28, 18, 6, 25, 15, 4, 21, 10, 29, 19, 8,
        27, 16, 5, 23, 12, 1, 20, 9, 28, 18, 7, 25, 14, 3, 22, 10, 0, 19, 9,
        27, 16, 5, 23, 12, 1, 20, 10, 29, 18, 8, 26, 14, 4, 23, 12, 1, 20, 9,
        28, 16, 5, 24, 13, 2, 21, 11, 30, 18, 7, 26, 15, 3, 23, 12, 2, 20, 9,
        27, 16, 5, 24, 14, 3, 21, 10, 29, 18, 6, 25, 15, 4, 23, 12, 1, 20, 8,
        27, 16, 6, 24, 13, 3, 22, 10, 28, 18, 7, 25, 15, 4, 23, 12, 0, 19, 9,
        27, 16, 6, 25, 13, 2, 21, 10, 28, 17, 7, 26, 15, 4, 23, 12, 30, 19, 8,
        27, 16, 6, 25, 14, 2, 20, 10, 29, 17, 7, 26, 15, 3, 22, 11, 1, 19, 8,
        28, 17, 5, 24, 13, 2, 20, 10, 29, 18, 7, 26, 15, 4, 22, 11, 1, 20, 8,
        27, 16, 5, 23, 13, 2, 21, 10, 29, 18, 7, 25, 14, 3, 22, 11, 1, 20, 9,
        27, 16, 5, 24, 12, 2, 21, 11, 29, 18, 7, 25, 14, 3, 22, 12, 0, 19, 8,
        27, 15, 5, 24, 13, 2, 21, 10, 29, 17, 6, 25, 15, 3, 22, 12, 1, 19, 8,
        27, 16, 5, 24, 13, 3, 20, 9, 28, 17, 6, 25, 15, 4, 22, 11, 0, 19, 8,
        27, 17, 7, 25, 14, 3, 22, 10, 29, 18, 8, 26, 16, 5, 24, 12, 1, 20, 9,
        27, 17, 6, 25, 13, 2, 21, 11, 29, 18, 8, 27, 15, 4, 23, 12, 1, 20, 9,
        28, 17, 6, 25, 14, 2, 21, 11, 30, 18, 8, 26, 15, 4, 22, 12, 2, 20, 9,
        28, 17, 5, 24, 13, 3, 21, 11, 30, 19, 7, 26, 15, 4, 22, 12, 2, 21, 9,
        28, 17, 6, 24, 13, 3, 22, 10, 0, 18, 7, 25, 15, 4, 23, 12, 1, 20, 9,
        27, 16, 6, 25, 14, 4, 23, 12, 30, 19, 8, 27, 16, 5, 24, 14, 2, 20, 9,
        28, 17, 7, 26, 15, 3, 22, 11, 30, 18, 8, 27, 17, 5, 24, 13, 2, 20, 9,
        28, 18, 7, 26, 15, 4, 22, 11, 30, 19, 8, 27, 16, 6, 23, 12, 2, 21, 9,
        28, 18, 7, 25, 14, 3, 22, 11, 0, 19, 9, 27, 16, 5, 24, 12, 1, 21, 10,
        28, 18, 7, 25, 13, 3, 22, 12, 0, 19, 8, 27, 15, 4, 23, 13, 1, 21, 10,
        29, 17, 6, 25, 14, 3, 22, 11, 1, 20, 9, 27, 17, 5, 24, 14, 3, 21, 11,
        29, 18, 7, 26, 15, 5, 23, 12, 31, 20, 8, 27, 16, 6, 24, 14, 3, 22, 10,
        29, 18, 7, 26, 15, 5, 24, 12, 1, 19, 9, 27, 16
    };

    private static final byte[] lunisolarMonth2 = {
        26, 15, 4, 22, 11, 30, 20, 9, 28, 17, 6, 24, 13, 2, 21, 10, 29, 19, 8,
        26, 15, 4, 23, 11, 1, 20, 9, 27, 16, 5, 24, 13, 2, 21, 11, 29, 18, 7,
        26, 14, 3, 22, 12, 1, 20, 9, 28, 16, 5, 24, 13, 2, 21, 11, 30, 19, 8,
        26, 16, 4, 24, 13, 3, 20, 9, 28, 17, 6, 25, 14, 4, 22, 11, 30, 19, 7,
        26, 16, 5, 23, 13, 2, 21, 9, 28, 17, 7, 25, 14, 4, 22, 10, 29, 19, 8,
        26, 16, 5, 24, 12, 1, 20, 9, 27, 17, 7, 26, 14, 3, 22, 11, 29, 18, 8,
        27, 16, 5, 24, 13, 1, 20, 9, 28, 17, 6, 25, 14, 2, 21, 11, 30, 18, 8,
        27, 16, 4, 23, 12, 2, 20, 9, 28, 18, 6, 25, 14, 3, 21, 11, 0, 19, 8,
        26, 15, 5, 23, 13, 3, 22, 10, 29, 18, 7, 25, 14, 4, 23, 12, 1, 20, 9,
        27, 16, 5, 24, 13, 3, 22, 11, 29, 18, 7, 26, 14, 4, 23, 12, 30, 19, 8,
        27, 16, 5, 24, 14, 2, 21, 10, 29, 17, 7, 26, 15, 4, 23, 12, 31, 19, 8,
        27, 17, 5, 24, 14, 3, 20, 10, 28, 18, 7, 26, 15, 4, 22, 11, 30, 19, 8,
        27, 17, 6, 24, 13, 2, 21, 9, 28, 18, 8, 26, 15, 4, 23, 11, 0, 19, 9,
        27, 16, 6, 24, 12, 2, 21, 10, 29, 19, 8, 27, 15, 4, 23, 13, 1, 20, 10,
        29, 17, 6, 25, 14, 3, 22, 11, 30, 19, 8, 26, 15, 4, 23, 13, 2, 20, 9,
        28, 17, 5, 24, 14, 4, 22, 11, 30, 19, 7, 26, 15, 5, 23, 13, 2, 21, 9,
        27, 17, 6, 24, 14, 3, 22, 11, 29, 18, 8, 26, 15, 5, 24, 12, 1, 20, 9,
        27, 17, 6, 25, 14, 3, 22, 11, 29, 18, 8, 27, 15, 5, 24, 13, 30, 19, 9,
        28, 17, 6, 25, 14, 2, 21, 10, 29, 18, 8, 27, 16, 4, 23, 12, 1, 19, 9,
        28, 18, 6, 25, 14, 3, 21, 10, 29, 19, 7, 26, 16, 4, 22, 12, 1, 20, 9,
        28, 17, 6, 24, 13, 2, 22, 10, 29, 19, 8, 26, 15, 4, 23, 11, 1, 20, 10,
        28, 17, 6, 24, 13, 2, 22, 11, 29, 18, 7, 26, 14, 4, 23, 13, 1, 20, 9,
        28, 16, 5, 24, 14, 2, 21, 11, 30, 18, 7, 26, 15, 4, 23, 12, 2, 19, 8,
        27, 17, 5, 24, 14, 3, 21, 10, 29, 18, 6, 25, 15, 5, 23, 12, 1, 20, 9,
        28, 17, 7, 25, 15, 4, 23, 11, 29, 19, 8, 27, 16, 6, 24, 12, 1, 20, 10,
        28, 17, 7, 26, 14, 3, 22, 11, 29, 19, 8, 27, 16, 5, 24, 13, 1, 20, 10,
        29, 17, 7, 25, 14, 3, 21, 11, 30, 19, 8, 27, 16, 4, 23, 12, 2, 20, 10,
        29, 18, 6, 25, 14, 3, 21, 11, 30, 20, 8, 27, 16, 5, 23, 12, 2, 21, 9,
        28, 17, 6, 24, 14, 3, 22, 11, 0, 19, 8, 26, 15, 5, 24, 12, 2, 21, 10,
        28, 17, 6, 25, 15, 4, 23, 13, 30, 19, 9, 27, 16, 6, 25, 14, 2, 21, 10,
        29, 17, 7, 26, 16, 4, 23, 12, 31, 19, 8, 27, 17, 6, 25, 14, 3, 21, 10,
        29, 18, 7, 26, 15, 5, 22, 11, 30, 20, 8, 27, 17, 6, 24, 13, 2, 21, 10,
        29, 18, 8, 26, 15, 4, 23, 11, 30, 20, 9, 27, 17, 6, 24, 13, 2, 21, 11,
        29, 18, 7, 26, 14, 3, 22, 12, 1, 20, 9, 28, 16, 5, 24, 13, 2, 21, 11,
        30, 18, 7, 25, 15, 3, 22, 12, 1, 20, 10, 28, 17, 6, 25, 14, 4, 22, 11,
        30, 19, 7, 26, 16, 5, 23, 13, 32, 21, 9, 28, 17, 7, 25, 14, 4, 23, 11,
        29, 18, 8, 26, 16, 5, 24, 12, 1, 20, 9, 27, 17
    };

    private static final byte[] lunisolarMonth3 = {
        26, 15, 3, 22, 11, 30, 18, 8, 27, 17, 5, 24, 13, 2, 20, 9, 28, 18, 6,
        25, 14, 3, 21, 11, 30, 19, 8, 27, 16, 5, 23, 12, 2, 21, 9, 28, 18, 7,
        25, 14, 3, 22, 11, 0, 19, 9, 26, 15, 5, 23, 12, 2, 21, 10, 29, 18, 7,
        26, 14, 4, 23, 13, 1, 20, 9, 28, 16, 5, 24, 14, 3, 22, 11, 30, 18, 7,
        26, 15, 4, 23, 12, 31, 19, 8, 27, 17, 5, 24, 14, 3, 21, 10, 29, 18, 7,
        26, 15, 5, 23, 12, 1, 20, 8, 27, 17, 6, 24, 14, 3, 21, 10, 28, 18, 7,
        26, 15, 4, 23, 11, 30, 19, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28, 18, 7,
        26, 15, 4, 23, 12, 0, 19, 9, 28, 16, 6, 24, 13, 2, 21, 10, 29, 18, 7,
        26, 15, 4, 23, 12, 31, 20, 10, 29, 18, 6, 25, 14, 3, 21, 11, 30, 20, 8,
        26, 15, 5, 23, 12, 2, 21, 9, 28, 17, 6, 24, 14, 3, 22, 11, 30, 19, 8,
        26, 15, 5, 24, 12, 2, 21, 10, 28, 17, 6, 25, 14, 3, 22, 12, 29, 18, 8,
        26, 15, 5, 24, 13, 1, 20, 9, 28, 16, 6, 25, 15, 3, 22, 11, 30, 18, 7,
        26, 16, 5, 24, 13, 2, 19, 9, 28, 17, 6, 25, 14, 3, 21, 10, 29, 19, 7,
        26, 16, 5, 23, 12, 1, 20, 10, 29, 18, 8, 26, 15, 4, 23, 11, 30, 20, 9,
        27, 17, 6, 24, 13, 30, 21, 11, 29, 18, 7, 26, 14, 3, 22, 12, 30, 20, 9,
        28, 16, 5, 24, 13, 2, 21, 11, 30, 17, 7, 25, 14, 3, 21, 12, 30, 19, 8,
        27, 16, 5, 24, 13, 3, 21, 10, 29, 18, 6, 25, 15, 4, 22, 12, 1, 20, 8,
        27, 16, 6, 24, 13, 3, 22, 10, 28, 17, 7, 24, 14, 4, 22, 10, 29, 18, 8,
        25, 15, 5, 24, 12, 2, 20, 9, 27, 16, 7, 25, 14, 4, 22, 11, 28, 18, 7,
        26, 15, 6, 23, 12, 1, 19, 9, 28, 16, 6, 25, 14, 3, 21, 10, 29, 18, 8,
        26, 16, 5, 23, 12, 2, 19, 9, 28, 17, 7, 25, 14, 4, 20, 10, 29, 19, 7,
        26, 15, 5, 22, 11, 2, 20, 10, 28, 17, 7, 24, 13, 3, 21, 10, 1, 19, 8,
        26, 15, 5, 23, 11, 2, 20, 9, 27, 16, 5, 24, 13, 3, 21, 11, 0, 18, 8,
        26, 14, 4, 23, 12, 2, 20, 9, 28, 16, 6, 24, 14, 3, 21, 11, 29, 19, 8,
        26, 16, 6, 24, 13, 4, 20, 10, 28, 17, 6, 25, 15, 5, 22, 11, 1, 19, 8,
        26, 16, 7, 23, 13, 3, 21, 9, 28, 17, 7, 25, 14, 5, 22, 10, 29, 19, 8,
        26, 16, 5, 24, 12, 2, 20, 9, 28, 17, 8, 26, 14, 4, 22, 11, 0, 18, 8,
        27, 16, 6, 23, 13, 2, 20, 9, 29, 17, 6, 25, 14, 3, 21, 11, 1, 19, 9,
        27, 16, 5, 23, 12, 3, 20, 9, 28, 18, 7, 25, 14, 4, 21, 11, 1, 19, 8,
        26, 15, 6, 23, 13, 4, 22, 10, 29, 18, 8, 25, 14, 4, 23, 12, 2, 20, 9,
        27, 16, 6, 24, 13, 4, 22, 11, 28, 17, 7, 26, 14, 5, 23, 12, 2, 19, 9,
        27, 16, 5, 24, 14, 3, 21, 10, 29, 17, 8, 26, 15, 5, 23, 12, 2, 19, 8,
        27, 17, 6, 24, 14, 4, 20, 10, 28, 18, 7, 26, 15, 5, 22, 11, 1, 19, 9,
        27, 17, 7, 24, 13, 3, 21, 9, 28, 18, 8, 26, 15, 5, 22, 11, 1, 19, 9,
        27, 16, 5, 24, 12, 3, 21, 10, 29, 19, 9, 27, 15, 5, 23, 13, 2, 20, 10,
        29, 17, 7, 25, 14, 4, 22, 11, 30, 19, 8, 26, 15, 5, 23, 13, 3, 20, 10,
        28, 17, 6, 24, 14, 5, 22, 11, 1, 19, 8, 26, 15
    };

    private static final byte[] lunisolarMonth4 = {};

    private static final byte[] lunisolarMonth5 = {
        21, 11, 29, 18, 7, 26, 14, 4, 23, 12, 1, 20, 9, 28, 16, 5, 24, 14, 2,
        21, 11, 29, 17, 7, 25, 15, 4, 23, 12, 2, 19, 8, 27, 16, 5, 24, 14, 3,
        21, 10, 29, 18, 6, 25, 15, 5, 22, 12, 1, 20, 8, 27, 16, 6, 25, 14, 4,
        22, 10, 29, 19, 8, 26, 16, 5, 24, 12, 1, 20, 10, 28, 17, 7, 26, 14, 3,
        22, 11, 29, 19, 8, 27, 16, 5, 23, 12, 1, 20, 10, 29, 17, 6, 25, 14, 2,
        21, 11, 30, 19, 8, 27, 16, 4, 23, 12, 2, 20, 10, 28, 18, 6, 24, 14, 3,
        21, 11, 1, 19, 8, 26, 15, 5, 23, 12, 2, 21, 9, 28, 17, 6, 24, 13, 3,
        22, 11, 0, 19, 8, 26, 15, 4, 23, 12, 2, 21, 10, 27, 16, 6, 25, 13, 3,
        22, 11, 30, 19, 8, 27, 16, 6, 25, 14, 2, 21, 10, 29, 17, 7, 26, 15, 4,
        23, 12, 30, 19, 8, 27, 17, 5, 24, 14, 3, 20, 10, 28, 18, 7, 26, 15, 4,
        22, 11, 30, 19, 8, 27, 17, 6, 24, 13, 2, 21, 9, 28, 18, 8, 26, 15, 4,
        22, 11, 0, 19, 9, 27, 16, 5, 24, 12, 2, 21, 10, 28, 18, 7, 26, 14, 3,
        22, 12, 0, 19, 9, 28, 16, 5, 24, 13, 2, 21, 10, 29, 18, 7, 25, 15, 3,
        22, 12, 1, 19, 8, 27, 16, 5, 24, 14, 4, 22, 11, 30, 19, 7, 26, 15, 5,
        23, 13, 2, 21, 9, 27, 17, 6, 24, 14, 4, 22, 10, 29, 18, 8, 26, 15, 5,
        24, 12, 1, 20, 9, 27, 17, 6, 25, 14, 3, 22, 11, 29, 17, 8, 27, 15, 5,
        23, 13, 1, 19, 9, 28, 17, 6, 25, 14, 2, 21, 10, 29, 18, 8, 27, 16, 4,
        23, 12, 1, 19, 9, 28, 18, 6, 25, 14, 3, 21, 10, 29, 19, 8, 26, 15, 4,
        22, 12, 1, 20, 9, 28, 17, 6, 24, 13, 3, 22, 10, 29, 19, 8, 26, 15, 4,
        23, 12, 1, 20, 10, 27, 16, 6, 24, 13, 3, 22, 11, 29, 18, 7, 26, 14, 4,
        23, 13, 1, 20, 9, 28, 16, 5, 24, 14, 3, 22, 11, 0, 18, 7, 26, 15, 4,
        23, 12, 2, 19, 8, 27, 17, 5, 24, 14, 3, 21, 10, 29, 18, 7, 26, 15, 5,
        23, 12, 1, 20, 8, 27, 17, 6, 24, 14, 3, 21, 10, 28, 18, 8, 26, 15, 5,
        23, 11, 0, 19, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28, 18, 8, 27, 16, 5,
        24, 13, 1, 20, 10, 29, 17, 7, 25, 14, 3, 22, 11, 30, 19, 8, 27, 16, 4,
        23, 13, 2, 20, 10, 29, 18, 6, 25, 14, 4, 22, 11, 1, 20, 8, 26, 16, 5,
        23, 13, 2, 21, 10, 28, 17, 6, 24, 14, 4, 23, 11, 0, 19, 8, 26, 15, 5,
        24, 13, 2, 21, 10, 28, 17, 6, 25, 14, 3, 22, 12, 0, 18, 8, 26, 15, 5,
        24, 13, 1, 20, 9, 28, 16, 6, 25, 15, 3, 22, 11, 0, 18, 7, 26, 16, 5,
        24, 13, 2, 20, 10, 29, 18, 7, 26, 15, 5, 22, 11, 30, 20, 8, 27, 17, 6,
        24, 13, 2, 21, 10, 29, 18, 8, 26, 15, 4, 23, 11, 1, 20, 9, 27, 17, 6,
        24, 13, 2, 21, 11, 29, 18, 7, 26, 14, 3, 22, 12, 1, 20, 9, 28, 16, 5,
        24, 13, 2, 21, 11, 0, 18, 7, 25, 15, 3, 22, 12, 2, 19, 8, 27, 16, 5,
        24, 13, 3, 21, 10, 29, 18, 6, 25, 15, 4, 22, 12, 1, 20, 8, 27, 16, 6,
        24, 13, 3, 22, 10, 28, 17, 7, 26, 16, 5, 24, 12, 1, 20, 9, 27, 17, 7,
        26, 14, 3, 22, 11, 29, 18, 8, 27, 16, 5, 24, 13, 1, 20, 9, 28, 17, 7,
        25, 14, 3, 21, 11, 30, 18, 8, 27, 16, 4, 23, 12
    };

    private static final byte[] lunisolarMonth6 = {};

    private static final byte[] lunisolarMonth7 = {
        18, 8, 26, 15, 4, 23, 11, 30, 19, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28,
        18, 8, 26, 15, 4, 22, 12, 0, 19, 9, 28, 16, 6, 24, 13, 2, 21, 10, 29,
        18, 7, 26, 15, 3, 22, 12, 1, 19, 9, 28, 17, 5, 24, 13, 3, 22, 11, 30,
        20, 8, 26, 15, 5, 23, 13, 2, 21, 9, 28, 17, 6, 24, 14, 4, 23, 11, 30,
        19, 8, 26, 15, 5, 24, 12, 2, 21, 10, 27, 17, 6, 25, 14, 3, 22, 11, 29,
        18, 8, 26, 15, 5, 24, 13, 1, 20, 9, 28, 16, 6, 25, 15, 3, 22, 11, 30,
        18, 7, 26, 16, 5, 23, 13, 2, 19, 9, 28, 17, 6, 25, 14, 3, 21, 10, 29,
        19, 7, 26, 16, 5, 23, 12, 1, 20, 9, 28, 17, 7, 25, 14, 3, 22, 10, 29,
        19, 8, 27, 16, 5, 24, 12, 2, 21, 11, 29, 18, 7, 26, 14, 3, 22, 12, 30,
        20, 9, 28, 16, 5, 24, 13, 2, 21, 11, 29, 18, 7, 25, 15, 3, 22, 12, 31,
        19, 8, 27, 16, 5, 24, 13, 3, 21, 10, 29, 18, 6, 25, 15, 4, 22, 12, 1,
        20, 8, 27, 16, 6, 24, 13, 3, 21, 9, 28, 17, 7, 25, 15, 4, 23, 11, 30,
        19, 8, 26, 16, 6, 25, 13, 2, 21, 10, 28, 17, 7, 26, 15, 4, 23, 12, 29,
        19, 8, 27, 16, 5, 24, 13, 2, 21, 11, 30, 18, 8, 27, 16, 4, 23, 12, 31,
        20, 9, 28, 18, 6, 25, 14, 3, 21, 11, 30, 19, 8, 26, 15, 5, 22, 12, 31,
        21, 9, 28, 17, 6, 24, 13, 3, 22, 11, 30, 19, 8, 26, 15, 4, 23, 12, 2,
        21, 10, 27, 17, 6, 25, 13, 3, 22, 11, 29, 18, 7, 26, 15, 4, 23, 13, 1,
        20, 9, 28, 16, 6, 25, 14, 3, 22, 11, 30, 18, 7, 26, 16, 4, 23, 13, 31,
        19, 9, 27, 17, 6, 25, 14, 3, 21, 10, 29, 18, 7, 26, 16, 5, 23, 12, 1,
        20, 8, 27, 17, 7, 24, 14, 3, 21, 10, 29, 18, 8, 26, 15, 5, 23, 11, 30,
        20, 9, 27, 17, 6, 25, 13, 2, 21, 11, 29, 18, 8, 27, 15, 4, 23, 12, 1,
        20, 9, 28, 17, 6, 24, 13, 2, 21, 11, 30, 18, 7, 26, 15, 3, 22, 12, 2,
        20, 9, 28, 17, 5, 24, 13, 3, 21, 11, 29, 19, 7, 25, 15, 4, 22, 12, 1,
        20, 9, 27, 16, 6, 24, 13, 3, 22, 10, 29, 18, 7, 25, 15, 4, 23, 13, 2,
        21, 10, 28, 17, 7, 26, 14, 4, 23, 12, 29, 18, 8, 27, 16, 5, 24, 13, 1,
        20, 9, 28, 17, 7, 26, 15, 3, 22, 11, 30, 18, 8, 27, 16, 5, 24, 13, 2,
        20, 9, 28, 18, 6, 25, 14, 4, 21, 11, 29, 19, 8, 27, 16, 5, 23, 12, 1,
        20, 9, 28, 18, 7, 25, 14, 3, 22, 10, 29, 19, 9, 26, 16, 5, 23, 12, 1,
        20, 10, 28, 17, 6, 25, 13, 3, 22, 11, 29, 19, 8, 27, 15, 4, 23, 13, 1,
        20, 10, 29, 18, 7, 26, 15, 4, 23, 12, 31, 19, 8, 27, 16, 5, 24, 14, 3,
        21, 10, 29, 18, 6, 25, 15, 5, 23, 12, 1, 20, 8, 27, 16, 6, 24, 14, 3,
        22, 10, 28, 18, 7, 25, 15, 5, 23, 11, 30, 19, 9, 27, 16, 6, 25, 13, 2,
        21, 10, 28, 18, 7, 26, 15, 4, 23, 12, 0, 19, 9, 28, 16, 6, 24, 13, 2,
        20, 10, 29, 18, 7, 26, 15, 3, 22, 11, 30, 19, 9, 28, 17, 5, 24, 13, 2,
        20, 10, 29, 19, 7, 26, 15, 4, 23, 12, 31, 21, 9, 28, 17, 6, 24, 14, 3,
        22, 11, 30, 19, 8, 26, 15, 5, 24, 12, 2, 21, 10, 28, 17, 6, 25, 14, 3,
        22, 12, 29, 18, 8, 26, 15, 5, 24, 13, 2, 20, 9
    };

    private static final byte[] lunisolarMonth8 = {
        16, 6, 24, 14, 3, 22, 10, 28, 18, 7, 25, 15, 5, 23, 12, 1, 19, 9, 27,
        16, 6, 25, 13, 2, 21, 10, 28, 18, 7, 26, 15, 4, 23, 12, 0, 19, 9, 28,
        16, 6, 25, 14, 2, 20, 10, 29, 18, 7, 26, 15, 3, 22, 11, 30, 20, 10, 29,
        18, 6, 25, 14, 3, 21, 11, 30, 20, 8, 27, 16, 5, 23, 12, 2, 21, 9, 28,
        18, 7, 24, 14, 3, 22, 11, 30, 19, 8, 26, 15, 5, 23, 12, 2, 21, 10, 28,
        17, 6, 25, 13, 3, 22, 12, 30, 19, 8, 26, 15, 4, 23, 13, 2, 20, 9, 28,
        16, 6, 25, 14, 3, 22, 11, 30, 18, 7, 26, 16, 4, 23, 13, 2, 20, 9, 28,
        17, 6, 25, 14, 4, 22, 11, 29, 19, 7, 26, 16, 5, 23, 12, 1, 20, 8, 27,
        17, 7, 26, 15, 4, 23, 11, 30, 19, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28,
        18, 8, 26, 14, 3, 22, 12, 30, 19, 9, 28, 16, 5, 24, 13, 2, 21, 10, 29,
        18, 7, 26, 15, 3, 22, 12, 31, 19, 9, 27, 16, 5, 23, 13, 3, 21, 10, 29,
        18, 6, 25, 14, 4, 22, 12, 1, 20, 8, 27, 16, 5, 23, 13, 3, 22, 10, 29,
        18, 7, 25, 14, 4, 23, 12, 30, 19, 8, 26, 16, 5, 24, 13, 2, 21, 10, 28,
        17, 7, 26, 14, 4, 23, 12, 1, 20, 9, 28, 17, 6, 25, 15, 3, 21, 11, 29,
        18, 8, 27, 16, 5, 23, 12, 31, 19, 9, 28, 18, 6, 25, 14, 3, 21, 10, 29,
        19, 8, 27, 16, 5, 23, 12, 31, 20, 9, 28, 17, 7, 24, 13, 3, 22, 10, 29,
        19, 8, 26, 15, 4, 23, 12, 1, 20, 10, 28, 17, 6, 25, 13, 3, 22, 11, 29,
        19, 8, 26, 15, 4, 23, 13, 1, 20, 10, 28, 16, 5, 24, 14, 3, 22, 11, 30,
        18, 7, 26, 15, 4, 23, 13, 2, 20, 9, 28, 17, 5, 24, 14, 4, 21, 11, 29,
        18, 7, 26, 15, 5, 23, 12, 1, 20, 8, 27, 17, 6, 24, 14, 3, 22, 10, 29,
        18, 8, 26, 15, 5, 24, 12, 1, 20, 9, 27, 17, 6, 25, 13, 3, 21, 10, 28,
        18, 8, 27, 15, 4, 23, 12, 0, 19, 9, 28, 17, 6, 25, 14, 2, 21, 10, 29,
        18, 8, 26, 16, 4, 22, 12, 1, 19, 9, 28, 17, 5, 24, 13, 3, 21, 10, 29,
        19, 7, 26, 15, 4, 22, 12, 1, 20, 9, 28, 17, 6, 23, 13, 2, 22, 11, 30,
        19, 8, 26, 15, 5, 24, 13, 2, 21, 10, 28, 17, 6, 25, 14, 4, 23, 12, 30,
        19, 8, 27, 15, 5, 24, 13, 2, 21, 10, 28, 17, 6, 25, 15, 3, 22, 11, 30,
        18, 8, 26, 16, 5, 24, 13, 2, 20, 9, 28, 17, 6, 25, 15, 4, 22, 11, 29,
        19, 7, 26, 16, 6, 23, 12, 2, 20, 9, 28, 17, 7, 25, 14, 3, 22, 10, 29,
        19, 8, 26, 16, 5, 24, 12, 1, 20, 10, 28, 17, 7, 26, 14, 3, 22, 11, 29,
        19, 8, 27, 16, 5, 24, 13, 2, 21, 11, 30, 18, 7, 26, 15, 3, 22, 12, 31,
        20, 9, 28, 17, 5, 24, 13, 3, 21, 11, 29, 18, 6, 25, 15, 4, 22, 12, 1,
        20, 8, 27, 16, 6, 24, 13, 3, 22, 10, 29, 18, 7, 25, 15, 4, 23, 12, 30,
        20, 9, 26, 16, 6, 25, 13, 3, 21, 10, 28, 17, 7, 26, 15, 4, 23, 12, 0,
        19, 8, 27, 16, 6, 25, 14, 2, 21, 10, 29, 17, 7, 26, 16, 4, 22, 11, 30,
        19, 8, 27, 17, 5, 24, 13, 2, 21, 11, 30, 19, 8, 27, 16, 5, 23, 12, 31,
        21, 9, 28, 18, 7, 24, 14, 3, 22, 11, 30, 19, 9, 26, 15, 4, 23, 12, 2,
        21, 10, 28, 17, 6, 25, 13, 3, 22, 12, 30, 19, 8
    };

    private static final byte[] lunisolarMonth9 = {
        16, 5, 23, 13, 3, 21, 9, 28, 17, 7, 25, 14, 4, 23, 11, 30, 19, 8, 26,
        16, 5, 24, 13, 2, 21, 10, 28, 17, 7, 26, 14, 4, 23, 12, 29, 18, 8, 27,
        16, 5, 24, 13, 1, 20, 9, 28, 17, 7, 26, 15, 3, 22, 11, 30, 19, 9, 28,
        18, 6, 25, 14, 3, 21, 10, 29, 19, 7, 26, 15, 4, 22, 12, 31, 20, 9, 28,
        17, 6, 24, 13, 3, 22, 10, 29, 19, 8, 26, 15, 4, 23, 12, 1, 20, 10, 28,
        17, 6, 24, 13, 3, 22, 11, 29, 18, 7, 26, 14, 4, 23, 13, 1, 20, 9, 28,
        16, 5, 24, 14, 3, 22, 11, 30, 18, 7, 26, 15, 4, 23, 12, 2, 19, 8, 27,
        17, 5, 24, 14, 3, 21, 10, 29, 18, 6, 25, 15, 5, 23, 12, 1, 20, 8, 27,
        16, 6, 25, 15, 4, 23, 11, 29, 19, 8, 27, 16, 6, 24, 12, 1, 20, 10, 28,
        17, 7, 26, 14, 3, 22, 11, 29, 19, 8, 27, 16, 5, 24, 13, 1, 20, 10, 29,
        17, 7, 25, 14, 3, 22, 11, 30, 19, 8, 27, 16, 4, 23, 12, 31, 20, 10, 29,
        18, 6, 25, 14, 3, 21, 11, 30, 20, 8, 27, 16, 5, 23, 12, 2, 21, 9, 28,
        17, 6, 24, 14, 3, 22, 11, 30, 19, 8, 26, 15, 5, 24, 12, 2, 21, 10, 28,
        17, 6, 25, 14, 3, 22, 12, 30, 19, 9, 27, 16, 6, 25, 14, 2, 21, 10, 29,
        17, 7, 26, 16, 4, 23, 12, 31, 19, 8, 27, 17, 6, 25, 14, 3, 20, 10, 29,
        18, 7, 26, 15, 5, 22, 11, 30, 20, 8, 27, 17, 6, 24, 13, 2, 21, 10, 29,
        18, 8, 26, 15, 4, 23, 11, 30, 20, 9, 27, 17, 6, 24, 13, 2, 21, 11, 29,
        18, 7, 26, 14, 3, 22, 12, 30, 20, 9, 28, 16, 5, 24, 13, 2, 21, 11, 30,
        18, 7, 25, 15, 3, 22, 12, 31, 19, 9, 27, 16, 5, 24, 13, 3, 21, 10, 29,
        18, 6, 25, 15, 4, 22, 12, 1, 20, 8, 27, 16, 6, 24, 13, 3, 22, 10, 28,
        17, 7, 25, 15, 4, 23, 11, 0, 19, 8, 26, 16, 6, 25, 13, 2, 21, 10, 28,
        17, 7, 26, 15, 4, 23, 12, 29, 19, 8, 27, 16, 6, 24, 13, 1, 20, 10, 29,
        17, 7, 26, 15, 3, 22, 11, 30, 19, 8, 27, 17, 5, 24, 13, 2, 20, 10, 29,
        18, 7, 26, 15, 4, 21, 11, 30, 20, 8, 27, 16, 5, 23, 12, 2, 21, 11, 30,
        19, 8, 26, 15, 4, 23, 12, 2, 21, 10, 28, 17, 6, 25, 13, 3, 22, 11, 29,
        18, 7, 26, 15, 4, 23, 13, 1, 20, 9, 28, 16, 6, 25, 14, 3, 22, 11, 30,
        18, 7, 26, 15, 4, 23, 13, 2, 20, 9, 27, 17, 5, 25, 14, 4, 21, 10, 29,
        18, 7, 26, 16, 5, 23, 12, 1, 20, 8, 27, 17, 6, 25, 14, 3, 22, 10, 29,
        18, 8, 26, 15, 5, 23, 11, 1, 20, 9, 27, 17, 6, 25, 13, 2, 21, 10, 28,
        18, 8, 27, 16, 5, 24, 13, 1, 20, 10, 29, 18, 7, 26, 15, 3, 22, 11, 30,
        19, 9, 27, 16, 4, 23, 13, 2, 20, 10, 29, 18, 6, 25, 14, 4, 22, 11, 30,
        20, 8, 27, 16, 5, 23, 13, 2, 21, 10, 28, 17, 7, 24, 14, 4, 23, 11, 30,
        19, 8, 26, 15, 5, 24, 13, 2, 21, 10, 28, 17, 6, 25, 14, 4, 23, 12, 30,
        19, 8, 27, 15, 5, 24, 13, 2, 20, 9, 28, 17, 6, 25, 15, 3, 22, 11, 30,
        18, 8, 27, 16, 5, 24, 13, 2, 21, 10, 29, 19, 7, 26, 16, 5, 22, 12, 30,
        20, 9, 28, 17, 6, 24, 13, 2, 21, 10, 29, 19, 8, 26, 15, 4, 23, 11, 30,
        20, 10, 28, 17, 6, 25, 13, 2, 21, 11, 29, 18, 8
    };

    private static final byte[] lunisolarMonth10 = {
        15, 5, 23, 12, 2, 21, 9, 28, 17, 6, 24, 14, 3, 22, 11, 30, 19, 8, 26,
        15, 5, 24, 12, 2, 21, 10, 27, 16, 6, 25, 14, 3, 22, 11, 29, 18, 7, 26,
        15, 5, 24, 13, 1, 20, 9, 28, 16, 6, 25, 15, 3, 22, 11, 29, 19, 8, 27,
        17, 5, 24, 13, 2, 20, 10, 29, 18, 7, 26, 15, 4, 22, 11, 30, 20, 8, 27,
        17, 6, 24, 13, 2, 21, 10, 29, 18, 8, 26, 15, 4, 22, 11, 30, 20, 9, 27,
        16, 5, 24, 12, 2, 21, 11, 29, 18, 7, 26, 14, 3, 22, 12, 30, 20, 9, 28,
        16, 5, 24, 13, 2, 21, 10, 29, 17, 6, 25, 15, 3, 22, 12, 1, 19, 8, 27,
        16, 5, 24, 13, 3, 21, 10, 29, 18, 6, 25, 15, 4, 22, 12, 1, 19, 8, 26,
        16, 6, 25, 14, 4, 22, 10, 29, 18, 8, 26, 16, 5, 24, 12, 1, 20, 9, 27,
        17, 7, 26, 14, 3, 22, 11, 29, 18, 8, 27, 15, 5, 23, 12, 30, 20, 9, 28,
        17, 6, 25, 14, 2, 21, 10, 30, 18, 8, 27, 16, 4, 23, 12, 31, 20, 9, 28,
        18, 6, 24, 14, 3, 21, 10, 30, 19, 7, 26, 15, 4, 22, 12, 1, 21, 9, 28,
        17, 6, 24, 13, 3, 22, 10, 30, 19, 8, 26, 15, 4, 23, 12, 1, 20, 10, 27,
        16, 6, 25, 13, 3, 22, 11, 30, 19, 8, 27, 15, 5, 24, 14, 2, 21, 10, 29,
        17, 6, 25, 15, 4, 23, 12, 30, 18, 8, 27, 16, 5, 24, 13, 2, 20, 9, 28,
        18, 6, 26, 15, 4, 22, 11, 30, 19, 8, 27, 16, 6, 24, 13, 2, 21, 9, 28,
        18, 7, 25, 15, 4, 22, 11, 30, 19, 9, 27, 16, 5, 24, 12, 1, 20, 10, 28,
        18, 7, 26, 14, 3, 22, 11, 29, 19, 9, 28, 16, 5, 23, 13, 1, 20, 10, 29,
        17, 6, 25, 14, 3, 22, 11, 30, 19, 8, 27, 16, 4, 23, 13, 2, 20, 10, 29,
        18, 6, 25, 14, 4, 22, 11, 30, 20, 8, 26, 16, 5, 23, 13, 2, 21, 9, 28,
        17, 6, 24, 14, 4, 23, 11, 0, 19, 8, 26, 15, 5, 24, 13, 2, 21, 10, 27,
        17, 6, 25, 14, 4, 22, 11, 29, 18, 8, 27, 15, 5, 24, 13, 1, 20, 9, 28,
        17, 6, 25, 15, 3, 22, 11, 30, 18, 8, 27, 16, 5, 24, 13, 2, 19, 9, 28,
        18, 6, 25, 14, 3, 21, 10, 29, 19, 8, 27, 16, 5, 23, 12, 1, 20, 10, 29,
        19, 8, 26, 15, 4, 23, 11, 30, 20, 9, 27, 16, 5, 24, 13, 2, 21, 11, 29,
        18, 7, 26, 14, 4, 23, 12, 1, 20, 9, 28, 16, 5, 24, 14, 2, 21, 11, 30,
        18, 7, 25, 15, 4, 23, 12, 2, 19, 8, 27, 16, 5, 24, 14, 3, 21, 10, 29,
        18, 6, 25, 15, 5, 23, 12, 1, 20, 8, 27, 16, 6, 24, 13, 3, 21, 9, 28,
        18, 7, 25, 15, 4, 23, 11, 0, 19, 9, 27, 16, 6, 25, 13, 2, 21, 10, 28,
        18, 7, 26, 16, 5, 24, 13, 30, 20, 10, 29, 17, 6, 25, 14, 2, 21, 11, 30,
        19, 8, 27, 16, 4, 23, 12, 31, 20, 10, 29, 18, 6, 25, 14, 3, 21, 11, 30,
        19, 8, 26, 15, 5, 23, 12, 2, 21, 9, 28, 17, 6, 24, 13, 3, 22, 11, 30,
        19, 8, 26, 15, 4, 24, 12, 2, 21, 10, 27, 17, 6, 25, 13, 3, 22, 11, 29,
        18, 7, 26, 15, 4, 24, 13, 1, 20, 9, 28, 16, 6, 25, 14, 3, 22, 11, 30,
        18, 7, 26, 16, 4, 23, 13, 2, 20, 10, 29, 18, 7, 26, 15, 4, 22, 11, 30,
        19, 8, 27, 17, 6, 24, 13, 2, 21, 9, 28, 18, 8, 26, 15, 4, 22, 11, 30,
        19, 9, 27, 16, 5, 24, 12, 2, 21, 10, 28, 18, 7
    };

    private static final byte[] lunisolarMonth11 = {};

    private static final byte[] lunisolarMonth12 = {
        15, 4, 22, 11, 1, 20, 9, 27, 17, 6, 23, 13, 2, 21, 10, 29, 18, 7, 25,
        14, 4, 23, 11, 1, 20, 9, 27, 16, 5, 24, 13, 2, 21, 11, 29, 18, 7, 26,
        14, 4, 23, 12, 1, 19, 8, 27, 15, 5, 24, 14, 2, 21, 10, 29, 18, 7, 26,
        16, 5, 24, 13, 2, 20, 9, 28, 17, 6, 25, 15, 4, 22, 11, 29, 19, 7, 26,
        16, 5, 23, 12, 1, 20, 9, 28, 17, 7, 25, 14, 3, 22, 10, 29, 19, 8, 26,
        16, 5, 24, 12, 1, 20, 10, 28, 17, 7, 25, 13, 2, 21, 11, 29, 19, 8, 27,
        15, 4, 23, 12, 1, 20, 10, 29, 17, 6, 25, 14, 2, 21, 11, 30, 18, 8, 27,
        16, 4, 23, 12, 2, 20, 9, 28, 17, 5, 24, 14, 3, 21, 11, 0, 19, 7, 26,
        15, 4, 24, 13, 3, 22, 10, 29, 18, 7, 25, 14, 4, 23, 12, 1, 19, 8, 26,
        16, 5, 25, 13, 2, 21, 10, 28, 17, 7, 26, 14, 4, 23, 12, 30, 19, 8, 27,
        16, 5, 24, 14, 2, 21, 10, 29, 17, 7, 26, 15, 4, 22, 11, 30, 18, 8, 27,
        17, 5, 24, 13, 2, 20, 9, 28, 18, 7, 26, 15, 4, 22, 11, 30, 19, 8, 27,
        17, 6, 23, 12, 2, 21, 9, 29, 18, 7, 25, 14, 3, 22, 11, 0, 19, 9, 27,
        16, 5, 24, 12, 2, 21, 10, 29, 19, 8, 26, 15, 4, 23, 13, 1, 20, 10, 28,
        16, 5, 24, 14, 3, 22, 11, 30, 18, 7, 26, 15, 4, 23, 13, 2, 20, 9, 28,
        17, 5, 24, 14, 4, 22, 11, 29, 18, 7, 26, 15, 5, 23, 12, 1, 20, 8, 27,
        17, 6, 24, 14, 3, 22, 10, 29, 18, 8, 26, 15, 5, 24, 12, 1, 20, 9, 27,
        17, 6, 25, 13, 2, 21, 10, 28, 18, 8, 27, 15, 4, 23, 12, 30, 19, 9, 28,
        17, 6, 25, 14, 2, 21, 10, 29, 18, 8, 26, 16, 4, 22, 12, 1, 19, 9, 28,
        17, 5, 24, 13, 3, 21, 10, 29, 19, 7, 26, 15, 4, 22, 12, 1, 20, 9, 28,
        17, 6, 24, 13, 3, 22, 10, 29, 18, 7, 25, 14, 4, 23, 12, 1, 20, 9, 27,
        16, 5, 24, 13, 3, 22, 11, 29, 18, 7, 26, 14, 4, 23, 13, 1, 20, 9, 27,
        16, 5, 24, 14, 2, 21, 10, 29, 17, 7, 26, 15, 4, 23, 12, 1, 19, 8, 27,
        17, 5, 24, 14, 3, 21, 10, 29, 18, 7, 26, 15, 5, 22, 11, 0, 19, 9, 28,
        18, 7, 25, 14, 3, 22, 10, 29, 19, 9, 27, 16, 5, 24, 12, 1, 20, 10, 28,
        17, 7, 26, 14, 3, 22, 11, 29, 19, 8, 27, 15, 4, 23, 13, 1, 20, 10, 29,
        17, 6, 25, 14, 2, 22, 11, 30, 19, 8, 27, 16, 4, 23, 12, 2, 20, 10, 28,
        17, 5, 24, 14, 3, 22, 11, 30, 19, 7, 26, 15, 5, 23, 12, 2, 21, 9, 28,
        17, 6, 24, 14, 3, 22, 11, 0, 19, 8, 26, 15, 5, 24, 12, 2, 20, 9, 27, 
        16, 6, 25, 15, 4, 23, 12, 30, 19, 8, 27, 16, 6, 25, 14, 2, 21, 10, 29,
        17, 7, 26, 16, 4, 22, 11, 30, 19, 8, 28, 17, 5, 24, 13, 2, 20, 10, 29,
        18, 7, 26, 15, 4, 22, 11, 30, 20, 8, 27, 17, 6, 23, 13, 2, 21, 10, 29,
        18, 8, 25, 14, 3, 22, 11, 1, 20, 9, 27, 16, 5, 24, 12, 2, 21, 11, 29,
        18, 7, 26, 14, 3, 22, 12, 1, 20, 9, 27, 15, 5, 24, 13, 2, 21, 10, 29,
        17, 6, 25, 15, 3, 22, 12, 1, 20, 9, 28, 17, 6, 25, 14, 4, 22, 11, 29,
        19, 7, 26, 16, 5, 23, 12, 31, 20, 8, 27, 17, 7, 25, 14, 3, 22, 10, 29,
        18, 8, 26, 16, 5, 24, 12, 1, 20, 9, 27, 17, 7
    };

    private static final byte[][] lunisolarMonth = {
        chineseNewYearOffsets, // 1st lunisolar month
        lunisolarMonth2,
        lunisolarMonth3,
        lunisolarMonth4,
        lunisolarMonth5,
        lunisolarMonth6,
        lunisolarMonth7,
        lunisolarMonth8,
        lunisolarMonth9,
        lunisolarMonth10,
        lunisolarMonth11,
        lunisolarMonth12
    };

    private static final int[][] offsetStartDates = {
        {Calendar.JANUARY, 21}, // earliest day of the 1st lunisolar month
        {Calendar.FEBRUARY, 19}, // earliest day of the 2nd lunisolar month
        {Calendar.MARCH, 21}, // earliest day of the 3rd lunisolar month
        null, // 4th lunisolar month (FIX IT!)
        {Calendar.MAY, 23}, // 5th lunisolar month
        null, // 6th lunisolar month (FIX IT!)
        {Calendar.JULY, 24}, // 7th lunisolar month
        {Calendar.AUGUST, 24}, // 8th lunisolar month
        {Calendar.SEPTEMBER, 23}, // earliest day of the 9th lunisolar month
        {Calendar.OCTOBER, 23}, // 10th lunisolar month (FIX IT!)
        null, // 11th lunisolar month (FIX IT!)
        {Calendar.DECEMBER, 22}, // 12th lunisolar month (FIX IT!)
    };

    // Fast implementation of calculating the Chinese New Year by using precalculated offsets
    private static java.util.GregorianCalendar chineseNewYearStartDate_OffsetImpl(int year) {
        if (year >= 2343 || year < 1646) {
            throw new IllegalArgumentException("year has to be >= 1646 and < 2343 in order to produce correct results");
        }
        // Chinese New Year is always between Jan 21 and Feb 21,
        // so we start on Jan 21 and add a (precalculated) offset to get the actual start of Chinese New Year
        java.util.GregorianCalendar startDate = new java.util.GregorianCalendar(year, Calendar.JANUARY, 21, 0, 0, 0);
        startDate.add(Calendar.DATE, chineseNewYearOffsets[year - 1646]);
        return startDate;
    }

    private static java.util.GregorianCalendar chineseLunisolarStartDate_OffsetImpl(int year, int lunisolarmonth) {
        if (year >= 2343 || year < 1646) {
            throw new IllegalArgumentException("year has to be >= 1646 and < 2343 in order to produce correct results");
        }
        // Chinese New Year is always between Jan 21 and Feb 21,
        // so we start on Jan 21 and add a (precalculated) offset to get the actual start of Chinese New Year
        java.util.GregorianCalendar startDate = new java.util.GregorianCalendar(year, offsetStartDates[lunisolarmonth - 1][0], offsetStartDates[lunisolarmonth - 1][1], 0, 0, 0);
        startDate.add(Calendar.DATE, lunisolarMonth[lunisolarmonth - 1][year - 1646]);
        return startDate;
    }

    public static java.util.GregorianCalendar chineseLunisolarStartDate(int year, int lunisolarmonth) {
        return chineseLunisolarStartDate_OffsetImpl(year, lunisolarmonth);
    }

    // This is the implementation of the Chinese New Year by using the GPLed fi.joensuu.joyds1.calendar.ChineseCalendar API
    // The implementation by using the API is not very fast
    // year has to be > 1646 and < 2343 in order to produce correct results
    // according to the implementation of fi.joensuu
    private static java.util.GregorianCalendar chineseNewYearStartDate_APIImpl(int year) {
        if (year >= 2343 || year < 1646) {
            throw new IllegalArgumentException("year has to be > 1646 and < 2343 in order to produce correct results");
        }
        // start date is a date that is surely after chinese new year
        fi.joensuu.joyds1.calendar.Calendar afterNewYear = new GregorianCalendar(year, fi.joensuu.joyds1.calendar.Calendar.MAY, 1);
        fi.joensuu.joyds1.calendar.Calendar chineseCalendar = new ChineseCalendar(afterNewYear);
        // new year of the chinese calendar is the 1st day in the 1st month
        chineseCalendar.set(chineseCalendar.getCycle(), chineseCalendar.getYear(), 1, 1, false);
        GregorianCalendar chineseNewYearAsGregorianCalendar = new GregorianCalendar();
        chineseNewYearAsGregorianCalendar.set(chineseCalendar);
        return chineseNewYearAsGregorianCalendar.toJavaUtilGregorianCalendar();
    }

    private static java.util.GregorianCalendar chineseNewYearStartDate_APIImpl(int year, int lunisolarMonth) {
        if (year >= 2343 || year < 1646) {
            throw new IllegalArgumentException("year has to be > 1646 and < 2343 in order to produce correct results");
        }
        // start date is a date that is surely after chinese new year
        fi.joensuu.joyds1.calendar.Calendar afterNewYear = new GregorianCalendar(year, fi.joensuu.joyds1.calendar.Calendar.MAY, 1);
        fi.joensuu.joyds1.calendar.Calendar chineseCalendar = new ChineseCalendar(afterNewYear);
        // new year of the chinese calendar is the 1st day in the 1st month
        chineseCalendar.set(chineseCalendar.getCycle(), chineseCalendar.getYear(), lunisolarMonth, 1, false);
        GregorianCalendar chineseNewYearAsGregorianCalendar = new GregorianCalendar();
        chineseNewYearAsGregorianCalendar.set(chineseCalendar);
        return chineseNewYearAsGregorianCalendar.toJavaUtilGregorianCalendar();
    }

    public static void main(String[] args) {

        // calculate table as shown at https://en.wikipedia.org/wiki/Public_holidays_in_China
        // -----------------------------------------------------------------------------------
        java.util.GregorianCalendar cal;
        /*
        // Spring Festival
        for (int year = 2014; year <= 2019; year++) {
            cal = chineseLunisolarStartDate_OffsetImpl(year, 1);
            System.out.print(formattedDate(cal) + ", ");
        }
        System.out.println();

        // Dragon Boat Festival
        for (int year = 2014; year <= 2019; year++) {
            cal = chineseLunisolarStartDate_OffsetImpl(year, 5);
            cal.add(Calendar.DATE, 4);
            System.out.print(formattedDate(cal)+", ");
        }
        System.out.println();

        // Mid-Autumn Festival
        for (int year = 2014; year <= 2019; year++) {
            cal = chineseLunisolarStartDate_OffsetImpl(year, 8);
            cal.add(Calendar.DATE, 14);
            System.out.print(formattedDate(cal) + ", ");
        }
        System.out.println();
         */

        // determine the earliest Date for the offset start (so that we can generate postive offsets and not negative)
        // -----------------------------------------------------------------------------------------------------------
        /*
        int earliest = 9999;
        for (int year = 1646; year < 2343; year++) {
            java.util.GregorianCalendar chineseDate = chineseNewYearStartDate_APIImpl(year, 12);
 
                        
            int now = (chineseDate.get(java.util.Calendar.MONTH)+1)*100 + chineseDate.get(java.util.Calendar.DATE);
            if (now < earliest && now > 1200) {
                earliest = now;
            }
            
            System.out.println(formattedDate(chineseDate));
        }
        System.out.println(earliest);
         */
        // /*
        // Precalculate the offsets for a lunisolar lookup table
        int lunisolarMonth = 3;
        for (int year = 1646; year < 2343; year++) {
            java.util.GregorianCalendar chineseDate = chineseNewYearStartDate_APIImpl(year, lunisolarMonth);
            java.util.GregorianCalendar refDate = new java.util.GregorianCalendar(year, Calendar.MARCH, 21, 0, 0, 0);

            Date startDate = refDate.getTime();
            Date endDate = chineseDate.getTime();
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long diffTime = endTime - startTime;
            long diffDays = diffTime / (1000 * 60 * 60 * 24);
            System.out.print(diffDays + ",");
        }

//        */
        System.out.println();
    }

    /**
     * Retruns the end date of the Chinese New Year of a given year
     *
     * @param year the year in the Gregorian calendar
     * @return the end date of the Chinese New Year of a given year
     */
    public static java.util.GregorianCalendar chineseNewYearEndDate(int year) {
        java.util.GregorianCalendar chineseNewYearEnd = ChineseCalendarCalculations.chineseNewYearStartDate(year + 1);
        chineseNewYearEnd.add(Calendar.DATE, -1);
        return chineseNewYearEnd;
    }

    // month has to be specified as 1..12
    public static int[] chineseYearInfo(int year, int month, int day) {
        return chineseYearInfo(new java.util.GregorianCalendar(year, month - 1, day));
    }

    public static int[] chineseYearInfo(java.util.GregorianCalendar birthday) {
        int[] index = new int[3];
        // System.out.println("Geburtstag: " + formattedDate(birthday));
        Calendar chineseNewYear = chineseNewYearStartDate(birthday.get(Calendar.YEAR));
        //System.out.println("Chinesesisches Neuejahr: " + formattedDate(chineseNewYear));
        int refyear = birthday.get(Calendar.YEAR);
        if (birthday.before(chineseNewYear)) {
            refyear--;
        }
        index[0] = EARTHLY_BRANCHES[refyear % 12];
        index[1] = HEAVENLY_STEMS[refyear % 10];
        index[2] = YING_YANG[refyear % 2];
        return index;
    }

    private static String formattedDate(java.util.Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = format.format(new Date(calendar.getTimeInMillis()));
        return formattedDate;
    }
}
