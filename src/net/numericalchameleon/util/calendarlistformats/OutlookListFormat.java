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
package net.numericalchameleon.util.calendarlistformats;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.info.ProgInfo;

public class OutlookListFormat extends ListFormat {

    private final Format formatter;
    private int count;

    /**
     * Creates a new instance of OutlookListFormat
     */
    public OutlookListFormat() {
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        count = 0;
    }

    @Override
    public String getHeader() {
        return "["
                + ProgInfo.getInstance().getProgramName()
                + "] "
                + count
                + " \n";
    }

    // no footer necessary
    @Override
    public String getFooter() {
        return "";
    }

    @Override
    public String getFormat() {
        return "$TUNIT, $TVALUE";
    }

    @Override
    public String reformatDate(int dateformat, String date) throws ParseException {
        Date newdate = DateFormat.getDateInstance(dateformat).parse(date);
        count++;
        return formatter.format(newdate);
    }

    @Override
    public String reformatUnit(String unit) {
        String temp = GeneralString.replaceChar(unit, ',', ';');
        temp = GeneralString.replaceChar(temp, ']', ')');
        return GeneralString.replaceChar(temp, '[', '(');
    }

}
