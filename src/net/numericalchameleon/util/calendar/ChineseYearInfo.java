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

public class ChineseYearInfo {

    private GregorianCalendar date;
    private int earthlyBranch;
    private int heavenlyStem;
    private int yingYang;

    /**
     * Constructs the ChineseYearInfo object
     * @param date the date in the Gregorian calendar
     */
    public ChineseYearInfo(GregorianCalendar date) {
        this.date = date;
        int[] index = ChineseCalendarCalculations.chineseYearInfo(date);
        earthlyBranch = index[0];
        heavenlyStem = index[1];
        yingYang = index[2];
    }

    /**
     * Constructs the ChineseYearInfo object
     * @param year the year in the Gregorian calendar
     * @param month the month in the Gregorian calendar (1..12)
     * @param day the day in the Gregorian calendar
     */
    public ChineseYearInfo(int year, int month, int day) {
        this(new GregorianCalendar(year, month - 1, day));
    }

    /**
     * @return the birthday
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     * @return the earthlyBranch
     */
    public int getEarthlyBranch() {
        return earthlyBranch;
    }

    /**
     * @return the heavenlyStems
     */
    public int getHeavenlyStem() {
        return heavenlyStem;
    }

    /**
     * @return the ying or yang
     */
    public int getYingYang() {
        return yingYang;
    }
    
    /**
     * Returns the start date of the Chinese New Year
     * @return the start date of the Chinese New Year
     */
    public GregorianCalendar chineseNewYearStartDate() {
        return ChineseCalendarCalculations.chineseNewYearEndDate(date.get(Calendar.YEAR));
    }
    
    /**
     * Returns the end date of the Chinese New Year
     * @return the end date of the Chinese New Year
     */
    public GregorianCalendar chineseNewYearEndDate() {
        return ChineseCalendarCalculations.chineseNewYearEndDate(date.get(Calendar.YEAR));
    } 

}
