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
package net.numericalchameleon.util.datediff.zinstage;

import java.util.Calendar;
import jonelo.sugar.datetime.CalendarTools;

public class Zinstage {

    protected boolean verzinsungErsterTag;
    protected boolean verzinsungLetzterTag;
    protected int zinsjahr;
    protected int zinsmonat;
    protected int zinsmonatFeb;
    protected Calendar c1;
    protected Calendar c2;
    protected String name;
    public final static int KALENDERECHT = -1;

    /** Creates a new instance of Zinstage */
    public Zinstage() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setVerzinsungErsterTag(boolean verzinsung) {
        this.verzinsungErsterTag = verzinsung;
    }

    public boolean getVerzinsungErsterTag() {
        return verzinsungErsterTag;
    }

    public void setVerzinsungLetzterTag(boolean verzinsung) {
        this.verzinsungLetzterTag = verzinsung;
    }

    public boolean getVerzinsungLetzterTag() {
        return verzinsungLetzterTag;
    }

    public void setZinsjahr(int tage) {
        this.zinsjahr = tage;
    }

    public int getZinsjahr() {
        return zinsjahr;
    }

    public void setZinsmonat(int tage) {
        this.zinsmonat = tage;
    }

    public void setZinsmonatFeb(int tage) {
        this.zinsmonatFeb = tage;
    }

    public int getZinsmonat() {
        return zinsmonat;
    }

    public int getZinsmonatFeb() {
        return zinsmonatFeb;
    }

    public void setStart(Calendar c1) {
        this.c1 = c1;
    }

    public Calendar getStart() {
        return c1;
    }

    public void setEnd(Calendar c2) {
        this.c2 = c2;
    }

    public Calendar getEnd() {
        return c2;
    }

    private int zinstageInMonth(int month, int year) {
        int zinsmonatRef = 0;
        int daysMonths = 0;
        if (month == Calendar.FEBRUARY) {
            zinsmonatRef = zinsmonatFeb;
        } else {
            zinsmonatRef = zinsmonat;
        }
        if (zinsmonatRef == KALENDERECHT) {
            daysMonths = CalendarTools.daysInMonth(year, month);
        } else {
            daysMonths = zinsmonatRef;
        }
        return daysMonths;
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0));
    }

    @Override
    public String toString() {
        return name;
    }

    // zinstage berechnen
    public int calc() {
        Calendar start, end;
        if (c1.before(c2)) {
            start = (Calendar) c1.clone();
            end = (Calendar) c2.clone();
        } else {
            start = (Calendar) c2.clone();
            end = (Calendar) c1.clone();
        }
        int sum = 0;


        // a special for US NASD
        // If date A is the last day of the month, replace it with the 30th of the month.
        // If date B is the 31st of the month, and date A is before the 30th of the month, then date B becomes the 1st of the next month, else date B becomes the 30th of the month.

        if (this instanceof ZinstageNASD) {

            if (start.get(Calendar.DATE) == CalendarTools.daysInMonth(start.get(Calendar.YEAR), start.get(Calendar.MONTH))) {
                start.add(Calendar.DATE, 1);
            }

            if ((end.get(Calendar.DATE) == 31) && (start.get(Calendar.DATE) < 30)) {
                end.add(Calendar.DATE, 1);
            }
        }

        // zinsmonatRef berechnen für "only a few days" und "Tage des 1. Monats"
        int zinsmonatRef = 0;
        if (start.get(Calendar.MONTH) == Calendar.FEBRUARY) {
            zinsmonatRef = zinsmonatFeb;
        } else {
            zinsmonatRef = zinsmonat;
        }

        // only a few days (in zinsmonatRef)
        if ((start.get(Calendar.MONTH) == end.get(Calendar.MONTH))
                && (start.get(Calendar.YEAR) == end.get(Calendar.YEAR))) {

            if (zinsmonatRef != KALENDERECHT
                    && end.get(Calendar.DAY_OF_MONTH) > zinsmonatRef) {
                end.set(Calendar.DAY_OF_MONTH, zinsmonatRef);
            }

            int diff = end.get(Calendar.DATE) - start.get(Calendar.DATE);
            if (verzinsungErsterTag) {
                diff++;
            }
            if (!verzinsungLetzterTag) {
                diff--;
            }
            return diff + sum;
        }

        // daysBegin (Tage des 1. Monats (zinsmonatRef))
        int daysfirstMonth = 0;
        if (zinsmonatRef == KALENDERECHT) {
            daysfirstMonth = CalendarTools.daysInMonth(start.get(Calendar.YEAR), start.get(Calendar.MONTH));
        } else {
            daysfirstMonth = zinsmonatRef;
            if (start.get(Calendar.DAY_OF_MONTH) > zinsmonatRef) {
                start.set(Calendar.DAY_OF_MONTH, zinsmonatRef);
            }
        }
        int daysBegin = daysfirstMonth - start.get(Calendar.DAY_OF_MONTH);
        if (verzinsungErsterTag) {
            daysBegin++;
        }
        sum += daysBegin;


        // daysEnd (Tage des letzten Monates)
        int daysEnd = 0;
        if (end.get(Calendar.MONTH) == Calendar.FEBRUARY) { // February
            zinsmonatRef = zinsmonatFeb;
        } else {
            zinsmonatRef = zinsmonat;
        }

        if (zinsmonatRef == KALENDERECHT) {
            daysEnd = end.get(Calendar.DAY_OF_MONTH);
        } else {
            if (end.get(Calendar.DAY_OF_MONTH) > zinsmonatRef) {
                daysEnd = zinsmonatRef;
            } else {
                daysEnd = end.get(Calendar.DAY_OF_MONTH);
            }
        }
        if (!verzinsungLetzterTag) {
            daysEnd--;
        }
        sum += daysEnd;

        // years
        int daysYears = 0;
        int years = end.get(Calendar.YEAR) - start.get(Calendar.YEAR) - 1;
        if (years > 0) {

            if (zinsjahr == KALENDERECHT) {
                daysYears = years * 365;
                // add leapyears
                for (int i = 0; i < years; i++) {
                    daysYears += (isLeapYear(start.get(Calendar.YEAR) + 1 + i) ? 1 : 0);
                }
            } else {
                daysYears += (years * zinsjahr);
            }
        }
        sum += daysYears;

        // months
        int daysMonths = 0;
        if (end.get(Calendar.YEAR) == start.get(Calendar.YEAR)) { // Anfang- und Endedatum sind im gleichen Jahr
            // months=end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
            for (int m = start.get(Calendar.MONTH) + 1; m < end.get(Calendar.MONTH); m++) {
                daysMonths += zinstageInMonth(m, start.get(Calendar.YEAR));
            }
        }

        if (end.get(Calendar.YEAR) != start.get(Calendar.YEAR)) { // Anfang- und Endedatum sind in unterschiedlichen Jahren
            // Vom Anfangsmonat bis zum Jahresende
            for (int m = start.get(Calendar.MONTH) + 1; m < 12; m++) {
                daysMonths += zinstageInMonth(m, start.get(Calendar.YEAR));
            }

            // Vom Jahresanfang bis zum Endemonat
            for (int m = 0; m < end.get(Calendar.MONTH); m++) {
                daysMonths += zinstageInMonth(m, end.get(Calendar.YEAR));
            }
        }
        sum += daysMonths;
        return sum;
    }
}
