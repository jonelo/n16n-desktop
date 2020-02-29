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
import net.numericalchameleon.info.ProgInfo;

// http://tools.ietf.org/html/draft-royer-calsch-xcal-03
public class XCalendarListFormat extends ListFormat {
    
    private final DateFormat formatter;

    /** Creates a new instance of VCardListFormat */
    public XCalendarListFormat() {
        formatter = new SimpleDateFormat("yyyyMMdd");
    }
    
    @Override
    public String getFormat() {
     return   "  <vevent>\n"+
              "    $TVALUE\n"+
              "    <class>PUBLIC</class>\n"+
              "    <summary>$TUNIT</summary>\n"+
              "    <categories>HOLIDAY,Feiertag</categories>\n"+
              "  </vevent>";
    }
    
    @Override
    public String getHeader() {
       return "<?xml version=\"1.0\"?>\n"+
              "<iCalendar xmlns:xCal=\"urn:ietf:params:xml:ns:xcal\">\n"+
              "  <vcalendar>\n"+
              "    <version>2.0</version>\n"+
              "    <prodid>-//"
               + ProgInfo.getInstance().getHomepage()
               + "//"
               + ProgInfo.getInstance().getProgramName()
               + " "
               + ProgInfo.getInstance().getVersion()
               + "//EN</prodid>\n";
    }
    
    @Override
    public String getFooter() {
        return "  </vcalendar>\n"+
               "</iCalendar>";
    }
    
    @Override
    public String reformatDate(int dateformat, String date) throws java.text.ParseException {
        Date newdate = DateFormat.getDateInstance(dateformat).parse(date);
        
        Calendar local = new GregorianCalendar();
        local.setTime(newdate);
        local.add(Calendar.HOUR, 24);
        
        String zulubegin = formatter.format(newdate);
        String zuluend = formatter.format(local.getTime());
        return     "<dtstart value=\"DATE\">" + zulubegin + "</dtstart>\n"+
               "    <dtend value=\"DATE\">" + zuluend + "</dtend>";
    }
    
    @Override
    public String reformatUnit(String unit) {
        return unit;
    }
    
}
