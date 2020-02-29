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

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class VCalendarListFormat extends ListFormat {

    private DateFormat formatter;
    /** Creates a new instance of VCardListFormat */
    public VCalendarListFormat() {
       // ISO 8601
       formatter = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
       formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public String getFooter() {
       return "END:VCALENDAR";
    }

    @Override
    public String getFormat() {
       return "BEGIN:VEVENT\n$TVALUE\nCLASS:PUBLIC\nSUMMARY;ENCODING=QUOTED-PRINTABLE:$TUNIT\nEND:VEVENT";
    }

    @Override
    public String getHeader() {
       return "BEGIN:VCALENDAR\nVERSION:1.0\n";
    }

    @Override
    public String reformatDate(int dateformat, String date) throws java.text.ParseException {
       Date newdate = DateFormat.getDateInstance(dateformat).parse(date);

       Calendar local = new GregorianCalendar();
       local.setTime(newdate);
       local.add(Calendar.HOUR, 24);

       String zulubegin=formatter.format(newdate);
       String zuluend=formatter.format(local.getTime());
       return "DTSTART:"+zulubegin+"\nDTEND:"+zuluend;
    }

    @Override
    public String reformatUnit(String unit) {
        return unicode2quotedPrintable(unit,"ISO8859_1");
    }

  /*
   * unicode2quotedPrintable is a method derived from the the
   * project called DateLook - have a look on it!
   * URL:          http://www.rr-e.de/dl
   * Copyright:    Copyright (c) 2004
   * Author:       Rene Ewald
   * License:      GPL
   * Source:       Converter.java
   * Method:       unicode2quodedPrintable
   */
  //converts an unicode string into another unicode string BUT
  //the output string only contains US-ASCII characters and
  //all characters in the origin string that are not present in
  //US-ASCII-coding are translated to US-ASCII according to the rules
  //of quoted-printable e.g. german "ü" -> "=FC"
  public static String unicode2quotedPrintable(String s, String encoding) {
    StringBuilder output = new StringBuilder(80);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      OutputStreamWriter osw = new OutputStreamWriter(baos, encoding);
      osw.write(s, 0, s.length());
      osw.close();
    } catch (Exception e) {
      if (e.toString().indexOf("UnsupportedEncoding") != 0) {
        return unicode2quotedPrintable(s, "US-ASCII"); // retry with US-ASCII
      }
      else e.printStackTrace();
    }
    byte[] input = baos.toByteArray(); // converts the unicode characters to bytes according to the
                                       // active encoding of the used operating system (e.g. ISO-8859-15)
    output.append("=\n"); // starts with soft new line, because here the length of first part of line
                            // (SUMMARY;......: is unknown
    int k = 65; // position to fold the line
    int o = 1;  // length of output line

    for (int i = 0; i < input.length;i++) {
      if (input[i] < 0 || input[i] == 0x3d) {  // 0x3d = '='
        int ch = (int) input[i];
        if (input[i] < 0) ch = ch + 256;
        output.append("=" + (Integer.toHexString(ch)).toUpperCase()); //(Integer.toHexString((int) input[i]))
        o = o + 3;
      }
      else if (input[i] == 13 || input[i] == 10) {
        output.append("=0D=0A");
        byte ch2;
        if (i + 1 < input.length) ch2 = input[i+1];
        else ch2 = 0x20; // 0x20 is a space
        if (ch2 == 13 | ch2 == 10) i++; // skip next character
        o = o + 6;
      }
      else {
        output.append((char) input[i]);
        o++;
      }
      if (o > k - 1) {
        output.append("=\n");  // fold line
        k = k + 65;
      }
    }
    return output.toString();
  }

}
