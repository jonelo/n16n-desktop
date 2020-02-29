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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * LeapSeconds
 */
public class LeapSeconds {
    
    private static ArrayList<Calendar> list;
    
    /** Creates a new instance of LeapSeconds */
    public LeapSeconds() {
    }

    public static void readFile() {
        list = new ArrayList<>();
        try {
            InputStream is = LeapSeconds.class.getResourceAsStream("/data/lists/leapseconds");
            BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String str;
            while ((str = in.readLine()) != null) {
                if (str.length() == 0) continue;
                if (str.startsWith("#")) continue;
                Calendar cal = process(str);
                list.add(cal);
            }
            in.close();
        } catch (IOException e) {
        }        
    }
    
    public static int getLeapSeconds(Calendar start, Calendar end) {
        Calendar temp;
        if (start.after(end)) {
            // swap
            temp = start;
            start = end;
            end = temp;
        }
        if (list == null) readFile();
        int secs = 0;
        for (Calendar leap : list) {
            if (start.before(leap) && (end.after(leap) || (end.equals(leap)))) {
                secs++;
            }
        }
        return secs;
    }
    
    // Leap 1972 Jun 30 23:59:60 + S
    
    public static int getMonth(String month) {
        // small performance improvement:
        // months December and June are the most likely candidates
        if (month.equalsIgnoreCase("Dec")) return Calendar.DECEMBER;
        if (month.equalsIgnoreCase("Jun")) return Calendar.JUNE;
        // unlikely but possible
        if (month.equalsIgnoreCase("Jan")) return Calendar.JANUARY;
        if (month.equalsIgnoreCase("Feb")) return Calendar.FEBRUARY;
        if (month.equalsIgnoreCase("Mar")) return Calendar.MARCH;
        if (month.equalsIgnoreCase("Apr")) return Calendar.APRIL;
        if (month.equalsIgnoreCase("May")) return Calendar.MAY;
        if (month.equalsIgnoreCase("Jul")) return Calendar.JULY;
        if (month.equalsIgnoreCase("Aug")) return Calendar.AUGUST;
        if (month.equalsIgnoreCase("Sep")) return Calendar.SEPTEMBER;
        if (month.equalsIgnoreCase("Oct")) return Calendar.OCTOBER;
        if (month.equalsIgnoreCase("Nov")) return Calendar.NOVEMBER;
        return -1;
    }
    
    public static Calendar process(String str) {
        try {
            String[] arr = str.split("\t");
            if (!arr[0].equalsIgnoreCase("Leap")) return null;
            int year = Integer.parseInt(arr[1]);
            int month = getMonth(arr[2]);
            int day = Integer.parseInt(arr[3]);
            Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month);
            cal.set(Calendar.DATE, day);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 60);                    
            cal.set(Calendar.MILLISECOND, 0);
            return cal;
        } catch (NumberFormatException e) {
            System.err.println(e);
            return null;
        }
    }
    
}
