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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import net.numericalchameleon.info.ProgInfo;
import jonelo.sugar.util.GeneralString;
import java.util.UUID;

public class ICalendarListFormat extends ListFormat {

    private final DateFormat formatter;

    private static int counter;
    private final String UIDprefix;
    private final String DTSTAMP;
    
    // https://tools.ietf.org/html/rfc5545#page-8
    // "Content lines are delimited by a line break,
    // which is a ENDL sequence (CR character followed by LF character)."
    // Actual endline will be replaced later with \r\n by the ics save handler
    private final static String ENDL = "\n";

    /**
     * Creates a new instance of VCardListFormat
     */
    public ICalendarListFormat() {
        formatter = new SimpleDateFormat("yyyyMMdd");
        counter = 0;
      
        Date now = new Date();
        UIDprefix = new SimpleDateFormat("yyyyMMddHHmmss").format(now)
                +"-"+UUID.randomUUID().toString()+"-";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));        
        DTSTAMP = sdf.format(now);
    }

    @Override
    public String getFormat() {
        return "BEGIN:VEVENT" + ENDL
                + "$TVALUE" + ENDL
                + "CLASS:PUBLIC" + ENDL
                + "SUMMARY:$TUNIT" + ENDL
                + "CATEGORIES:HOLIDAY,Feiertag" + ENDL
                + "END:VEVENT";
    }

    @Override
    public String getHeader() {
        return "BEGIN:VCALENDAR" + ENDL
                + "VERSION:2.0" + ENDL
                + "PRODID:-//" + ProgInfo.getInstance().getHomepage()
                + "//NONSGML " + ProgInfo.getInstance().getProgramName()
                + " "
                + ProgInfo.getInstance().getVersion()
                + "//EN" + ENDL
                + "CALSCALE:GREGORIAN" + ENDL;
    }

    @Override
    public String getFooter() {
        return "END:VCALENDAR";
    }

    @Override
    public String reformatDate(int dateformat, String date) throws java.text.ParseException {
        Date newdate = DateFormat.getDateInstance(dateformat).parse(date);

        Calendar local = new GregorianCalendar();
        local.setTime(newdate);
        local.add(Calendar.HOUR, 24);

        String zulubegin = formatter.format(newdate);
        String zuluend = formatter.format(local.getTime());
        String UID = UIDprefix + (counter++);
        return "DTSTART;VALUE=DATE:" + zulubegin + ENDL +
               "DTEND;VALUE=DATE:" + zuluend + ENDL +
               "DTSTAMP:" + DTSTAMP + ENDL +
               "UID:" + UID + "@numericalchameleon.net" + ENDL +
               "SEQUENCE:0" + ENDL +                
               "STATUS:CONFIRMED" + ENDL +
               "TRANSP:TRANSPARENT";
    }

    @Override
    public String reformatUnit(String unit) {
        // https://tools.ietf.org/html/rfc5545#section-3.3.11
        // ESCAPED-CHAR = ("\\" / "\;" / "\," / "\N" / "\n")
        // ; \\ encodes \, \N or \n encodes newline
        // ; \; encodes ;, \, encodes ,

        String
              // \ in Java is expressed as "\\" and \\ is expressed as "\\\\"
        tmp = GeneralString.replaceAllStrings(unit, "\\", "\\\\");
        tmp = GeneralString.replaceAllStrings(tmp, ";", "\\;");
        return GeneralString.replaceAllStrings(tmp, ",", "\\,");   
    }

}
