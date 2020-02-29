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

/**
 * Duration
 */
public class Duration {

    private long seconds;
    private double minutes;
    private double hours;
    private double days;
    private double weeks;
    

    /** Creates a new instance of Duration */
    public Duration() {
        _clear();
    }

    @Override
    public String toString() {
       return days + " d, " + minutes + " m, " + hours + " h, " + seconds + " s";
    }

    private void _clear() {
        seconds = 0;
        minutes = 0;
        hours = 0;
        days = 0;
        weeks = 0;        
    }
    
    public void clear() {
        _clear();
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    // Takes the seconds and rearranges them into days, hours, minutes, and seconds
    // takes the durationControl into account
    public void rearrange(DurationControl control) {
        long t = (long) getAsSecondsOnly();
        
        if (t > 0) {
            this.seconds  = t % 60L; t /= 60L;  // 60 s = 1 m
        }
        if (t > 0) {
            this.minutes = t % 60L; t /= 60L;  // 60 m = 1 h
        }
        if (t > 0) {
            this.hours = t % 24L; t /= 24L; // 24 h = 1 d
        }
        if (t > 0) {
            this.days = t % 7L; t /= 7L; // 7 d = 1 week
        }
        this.weeks = t;
       
        if (!control.isWeeksWanted()) {
            // add weeks to days again
            this.days += weeks * 7;
            this.weeks = 0;
        }
        if (!control.isDaysWanted()) {
            // add the days to hours again
            this.hours += days * 24L;
            this.days = 0;
        }
        if (!control.isHoursWanted()) {
            // add the hours to minutes again
            this.minutes += hours * 60L;
            this.hours = 0;
        }
        if (!control.isMinutesWanted()) {
            this.seconds += minutes * 60L;
            this.minutes = 0;
        }
        if (!control.isSecondsWanted()) {
            if (control.isMinutesWanted()) {                
                this.minutes += (this.seconds / 60.0);
            } else
            if (control.isHoursWanted()) {
                this.hours += (this.seconds / 3600.0);                
            } else
            if (control.isDaysWanted()) {
                this.days += (this.seconds / 86400.0);
            } else
            if (control.isWeeksWanted()) {
                this.weeks += (this.seconds / 604800.0);
            }
            this.seconds = 0;
        }
        
    }

    public long getSeconds() {
        return seconds;
    }

    public double getAsSecondsOnly() {
        // performance: pre-calculate
        // 24*60*60 = 86400
        // 60*60 = 3600
        return (days * 86400) + (hours * 3600) + (minutes * 60) + seconds;
    }

    public void setHours(long hours) {
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setDays(long days) {
        this.days = days;
    }

    public double getDays() {
        return days;
    }
    
    public static Duration getDiff(Calendar start, Calendar end) {
        return getDiff(start, end, false);
    }
    
    public static Duration getDiff(Calendar start, Calendar end, boolean includingLeapSeconds) {
        Duration d = new Duration();
        d.setSeconds(Math.abs (calcSecs(end) - calcSecs(start)));
        if (includingLeapSeconds) {
            d.setSeconds(d.getSeconds() + LeapSeconds.getLeapSeconds(start, end));
        }
        return d;
    }
    
    /*
    public static long getDiffInSecondsOLD(Calendar c1, Calendar c2) {
         Calendar start, end;
         if (c1.before(c2)) {
           start = (Calendar) c1.clone();
           end = (Calendar) c2.clone();
         } else {
           start = (Calendar) c2.clone();
           end = (Calendar) c1.clone();
         }
         long startSecs = calcSecs(start) + (start.get(Calendar.DST_OFFSET) / 1000L);
         long endSecs = calcSecs(end) + (end.get(Calendar.DST_OFFSET) / 1000L);
         return Math.abs(endSecs - startSecs);
    }
*/
    private static long calcSecs(Calendar c) {
        long secsInCurrentYear
              = c.get(Calendar.DAY_OF_YEAR) * 86400L
              + c.get(Calendar.HOUR_OF_DAY) * 3600L
              + c.get(Calendar.MINUTE) * 60L
              + c.get(Calendar.SECOND);

        int fullyears = c.get(Calendar.YEAR)-1;
        long secsInPastYears =
             ((365L * fullyears) + countLeapYears(fullyears)) * 86400L;
        return secsInCurrentYear + secsInPastYears;
    }

    private static int countLeapYears(int year) {
        int count4 = year / 4;
        int count100 = year / 100;
        int count400 = year / 400;
        int countLeaps = count4 - count100 + count400;
        return countLeaps;
    }

    public double getWeeks() {
        return weeks;
    }

    public void setWeeks(long weeks) {
        this.weeks = weeks;
    }

}
