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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateYMD {

    private Calendar cal;
    private static SimpleDateFormat formatter;

    public DateYMD(String data) throws Exception {
        this();
        setTime(formatter.parse(data));
    }

    public DateYMD() {
        cal = new GregorianCalendar();
        if (formatter == null) {
            formatter = new SimpleDateFormat("yyyyMMdd");
        }
    }

    @Override
    public String toString() {
        return formatter.format(cal.getTime());
    }

    public void setTime(Date date) {
        cal.setTime(date);
    }

    public void setTime(int year, int month, int day) {
        cal.set(year, month, day);
    }

    public void addDays(int days) {
        cal.add(Calendar.DATE, days);
    }

    public static DateYMD parse(String input) throws Exception {
        DateYMD date = new DateYMD();
        int value = Integer.parseInt(input);
        int year = value / 10000;
        int month = (value / 100) - (year * 100);
        int day = value % 100;
        date.setTime(year, month - 1, day);

        //date.setTime(formatter.parse(value));
        return date;
    }
}