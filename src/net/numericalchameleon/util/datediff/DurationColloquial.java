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
package net.numericalchameleon.util.datediff;

import java.util.Calendar;
import jonelo.sugar.datetime.CalendarTools;

/**
 * DurationColloquial
 */
public class DurationColloquial implements Cloneable {

    private double centuries = 0;
    private double decades = 0;
    private double years = 0;
    private double quarters = 0;
    private double months = 0;
    private double weeks = 0;
    private double days = 0;

    /**
     * Creates a new instance of DurationColloquial
     *
     * @param months
     * @param days
     */
    public DurationColloquial(int months, int days) {
        this.months = months;
        this.days = days;
    }

    public DurationColloquial() {
    }

    public static DurationColloquial getDiff(Calendar start, Calendar end) {
        int months = 0;
        int days;

        Calendar tstart, tend;
        // swap if required
        if (start.before(end)) {
            tstart = (Calendar) start.clone();
            tend = (Calendar) end.clone();
        } else {
            tstart = (Calendar) end.clone();
            tend = (Calendar) start.clone();
        }
        int daysInMonth = CalendarTools.daysInMonth(tstart.get(Calendar.YEAR), tstart.get(Calendar.MONTH));
        boolean MMYYYYequal
                = (tstart.get(Calendar.MONTH) == tend.get(Calendar.MONTH))
                && (tstart.get(Calendar.YEAR) == tend.get(Calendar.YEAR));

        if (MMYYYYequal) {
            days = tend.get(Calendar.DATE) - tstart.get(Calendar.DATE);
        } else {
            days = daysInMonth
                    - tstart.get(Calendar.DATE)
                    + tend.get(Calendar.DATE);
        }

        if (days >= daysInMonth) {
            days -= daysInMonth;
            months++;
        }

        months += ((tend.get(Calendar.YEAR) - tstart.get(Calendar.YEAR)) * 12)
                + (tend.get(Calendar.MONTH) + 1) - (tstart.get(Calendar.MONTH) + 1)
                - (MMYYYYequal ? 0 : 1);

        return new DurationColloquial(months, days);
    }

    // rearranges days and months into months, quarters, years, weeks and days
    // takes the durationControl into account
    public void rearrange(DurationControl control) {

        long t = (long) ((centuries * 1200) + (decades * 120) + (years * 12) + (quarters * 3) + months);

        if (t > 0) {
            this.months = t % 3L;
            t /= 3L;  // 3 months = 1 quarter
        }
        if (t > 0) {
            this.quarters = t % 4L;
            t /= 4L; // 4 quarter = 1 year
        }
        if (t > 0) {
            this.years = t % 10L;
            t /= 10L; // 10 years = 1 decade
        }
        if (t > 0) {
            this.decades = t % 10L;
            t /= 10L; // 10 decades = 1 century
        }
        centuries = t;

        if (!control.isCenturiesWanted()) {
            // add centuries to decades again
            decades += centuries * 10;
            centuries = 0;
        }
        if (!control.isDecadesWanted()) {
            // add decades to years again
            years += decades * 10;
            decades = 0;
        }
        if (!control.isYearsWanted()) {
            // add the years to quarters again
            quarters += years * 4;
            years = 0;
        }
        if (!control.isQuartersWanted()) {
            // add the quarters to months again
            months += quarters * 3;
            quarters = 0;
        }
        if (!control.isMonthsWanted()) {
            if (control.isQuartersWanted()) {
                quarters += months / 3;
            } else if (control.isYearsWanted()) {
                years += months / 12;
            } else if (control.isDecadesWanted()) {
                decades += months / 120;
            } else if (control.isCenturiesWanted()) {
                centuries += months / 1200;
            }
            this.months = 0;
        }

        t = (long) days;
        if (t > 0) {
            days = t % 7L;
            t /= 7L; // 7 d = 1 week
        }
        weeks = t;

        if (!control.isWeeksWanted()) {
            // add weeks to days again
            days += weeks * 7;
            weeks = 0;
        }

        if (!control.isDaysWanted()) {
            if (control.isWeeksWanted()) {
                weeks = days / 7;
            }
            days = 0;
        }
    }

    @Override
    public DurationColloquial clone() {
        try {
            return (DurationColloquial) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    /**
     * @return the centuries
     */
    public double getCenturies() {
        return centuries;
    }

    /**
     * @param centuries the centuries to set
     */
    public void setCenturies(double centuries) {
        this.centuries = centuries;
    }   
    
    /**
     * @return the decades
     */
    public double getDecades() {
        return decades;
    }

    /**
     * @param decades the decades to set
     */
    public void setDecades(double decades) {
        this.decades = decades;
    }    
    
    public double getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public double getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getQuarters() {
        return quarters;
    }

    public void setQuarters(double quarters) {
        this.quarters = quarters;
    }

    public double getWeeks() {
        return weeks;
    }

    public void setWeeks(double weeks) {
        this.weeks = weeks;
    }

}
