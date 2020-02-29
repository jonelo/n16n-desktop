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
package net.numericalchameleon.categories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import net.numericalchameleon.data.Unit;
import uk.me.jstott.jcoord.LatLng;

public class CategoryTimezones extends CategoryObject {

    private static String INVALID;
    private String infoConversion;
    private final ArrayList<Unit> units;
    private final ArrayList<String> timezoneList;
    private final DateFormat dateFormat;
    private TimeZone defaultTimeZone;
    private final static String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private HashMap<String, LatLng> coordsHash;
    private final DecimalFormat formatHour;
    private final DecimalFormat formatLatLng;
    private final ResourceBundle iso3166;
    private final Map<String, Boolean> tzIDsAvailableInJRE;

    public String getTzId(int index) {
        return timezoneList.get(index);
    }

    public CategoryTimezones(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        iso3166 = categoryInterface.getISO3166ResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");

        dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        formatHour = new DecimalFormat("##.##");
        formatLatLng = new DecimalFormat("#,###,###.##");

        units = new ArrayList<>();
        timezoneList = new ArrayList<>();
        
        // only take available Timezone IDs into account
        String[] availableIDs = TimeZone.getAvailableIDs();
        tzIDsAvailableInJRE = new HashMap<>();
        for (String availableID : availableIDs) {
            tzIDsAvailableInJRE.put(availableID, Boolean.TRUE);
        } 

        readTimezoneList();
        readZone1970tab();        
    }

    
    private void readTimezoneList() {
        // read the timezone.list
        try {
            InputStream is = getClass().getResourceAsStream("/data/lists/timezones.list");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String thisLine;

            int index = 0;
            defaultTimeZone = TimeZone.getDefault();
            String timeZoneDefault = defaultTimeZone.getID();
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.startsWith("#") && thisLine.length() > 0) {

                    StringTokenizer st = new StringTokenizer(thisLine, ":");
                    String countrycode = st.nextToken();
                    String timezonecode = st.nextToken();
                    boolean active = true;
                    if (st.hasMoreTokens()) {
                        active = !st.nextToken().equals("false");
                    }
                    st = null;
                    if (tzIDsAvailableInJRE.get(timezonecode) != null) {
                        timezoneList.add(timezonecode);
                        StringBuilder sbt = new StringBuilder(96);
                        sbt.append(timezonecode);
                        if (!timezonecode.contains("/")) {
                            String desc = TimeZone.getTimeZone(timezonecode).getDisplayName(Locale.US);
                            sbt.append(" - ").append(desc);
                        }
                        sbt.append(" (");
                        sbt.append(iso3166.getString(countrycode));
                        sbt.append(")");

                        units.add(new Unit(index, sbt.toString(), countrycode, active));

                        // ignoreCase due to performance reasons
                        if (timezonecode.equalsIgnoreCase(timeZoneDefault)) {
                            sourceDefault = index;
                        }
                        if (timezonecode.equalsIgnoreCase("America/New_York")) {
                            targetDefault = index;
                        }
                        index++;
                    } else {
                        System.out.println("Timezone " + timezonecode + " not found.");
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    
    private void readZone1970tab() {    
        // read the zone1970.tab file (longitude, latitude)
        try {
            InputStream is = getClass().getResourceAsStream("/data/lists/zone1970.tab");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String thisLine;
            coordsHash = new HashMap<>();
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.startsWith("#") && thisLine.length() > 0) {
                    StringTokenizer st = new StringTokenizer(thisLine, "\t");
                    String countrycodes = st.nextToken();
                    String coordinates = st.nextToken();
                    String tzid = st.nextToken();
                    st = null;

                    try {
                        if (tzIDsAvailableInJRE.get(tzid) != null) {
                            // +-DDMM+-DDDMM is shortformat, example +4230+00131
                            // +-DDMMSS+-DDDMMSS is longformat, example -690022+0393524
                            boolean longformat = coordinates.length() == 15;
                            int offset = longformat ? 2 : 0;

                            LatLng latLng = new LatLng(
                                    // latitude
                                    Integer.parseInt(coordinates.substring(1, 3)),
                                    Integer.parseInt(coordinates.substring(3, 5)),
                                    (longformat ? (double) Integer.parseInt(coordinates.substring(5, 7)) : 0.0),
                                    coordinates.charAt(0) == '+' ? LatLng.NORTH : LatLng.SOUTH,
                                    // longitude
                                    Integer.parseInt(coordinates.substring(6 + offset, 9 + offset)),
                                    Integer.parseInt(coordinates.substring(9 + offset, 11 + offset)),
                                    (longformat ? (double) Integer.parseInt(coordinates.substring(13, 15)) : 0.0),
                                    coordinates.charAt(5 + offset) == '+' ? LatLng.EAST : LatLng.WEST);
                            coordsHash.put(tzid, latLng);
                        }

                    } catch (IllegalArgumentException iae) {
                        System.err.println(iae + " " + tzid + " " + coordinates);
                    } // try
                } // if
            } // while
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    @Override
    public String getInfoConversion() {
        return infoConversion;
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return units;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return units;
    }

    @Override
    public int getSourceDefault() {
        return sourceDefault;
    }

    @Override
    public int getTargetDefault() {
        return targetDefault;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);        
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId(); 
        
        /*
        see http://javaalmanac.com/egs/java.util/GetTimeOtherZone2.html
        There is a convenient setTimeZone() method in the Calendar object.
        However, it doesn't always return the correct results when used after
        a calendar field is set. This example demonstrates a more reliable
        way to convert a specific time from one time zone to another. It
        involves creating two Calendar instances and transfering the
        UTC (Coordinate Universal Time) from one to the other. The UTC is
        a representation of time and date that is independent of time zones.
         */

        // Create a Calendar object with the source time zone
        TimeZone fromTimeZone = TimeZone.getTimeZone(timezoneList.get(sid));
        Calendar fromCal = new GregorianCalendar(fromTimeZone);
        //fromCal.setLenient(false);
        dateFormat.setTimeZone(fromTimeZone);
        dateFormat.setLenient(false);
        fromCal.setTime(dateFormat.parse(input));

        long ms = fromCal.getTimeInMillis();

        // Create an instance using target's time zone and set it with the UTC
        TimeZone toTimeZone = TimeZone.getTimeZone((String) timezoneList.get(tid));
        Calendar toCal = new GregorianCalendar(toTimeZone);
        dateFormat.setTimeZone(toTimeZone);
        // setTimeInMillis is protected in 1.3 and public starting with 1.4
        // 1.4+:
        toCal.setTimeInMillis(fromCal.getTimeInMillis());
        // 1.3:
        // toCal.setTime(fromCal.getTime());

        // Ergebnis
        String result = dateFormat.format(toCal.getTime());

        // --------------------------
        // delta in Stunden berechnen
        dateFormat.setTimeZone(fromTimeZone);
        fromCal.setTime(dateFormat.parse(result));

        double d = (fromCal.getTimeInMillis() - ms) / 3600000.0;
        infoConversion = (d > 0.0 ? "+" : "") + formatHour.format(d) + " h";

        // ----------------------------
        // distance in km berechnen
        //
        // many users change the target more often than the source
        // so start with the target condition
        LatLng latLngt = (LatLng) coordsHash.get(timezoneList.get(tid));
        LatLng latLngs = null;
        if (latLngt != null) {
            latLngs = (LatLng) coordsHash.get(timezoneList.get(sid));
        }

        if (latLngt != null && latLngs != null) {
            infoConversion += (" / " + formatLatLng.format(latLngs.distance(latLngt)) + " km");
        }
        return result;
    }

    @Override
    public String getInitialValue() {
        dateFormat.setTimeZone(defaultTimeZone);
        return dateFormat.format(new Date());
    }

    public static String getInternationalTimeValue() {
        SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMddHHmmss);
        return formatter.format(new Date());
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        // Create a Calendar object with source "time zone"
        TimeZone fromTimeZone = TimeZone.getTimeZone(timezoneList.get(sourceIndex));
        Calendar fromCal = new GregorianCalendar(fromTimeZone);

        // set the internal value "input" for the calendar
        dateFormat.setTimeZone(fromTimeZone);
        fromCal.setTime(dateFormat.parse(input));

        // add the value "plus" to the calendar object
        int count = Integer.parseInt(plus.toString());
        fromCal.add(Calendar.HOUR, count);

        // set the value internally and return it
        String temp = dateFormat.format(fromCal.getTime());
        setInput(temp);
        return (temp);

    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {

        // Create a calendar object with the source-timezone
        TimeZone fromTimeZone = TimeZone.getTimeZone(timezoneList.get(sourceIndex));
        Calendar fromCal = new GregorianCalendar(fromTimeZone);

        // set the value "big" for the calendar
        DateFormat standardDateFormat = new SimpleDateFormat(yyyyMMddHHmmss);
        fromCal.setTime(standardDateFormat.parse(big.toString()));

        // convert the "big" value in the form which is used internally
        dateFormat.setTimeZone(fromTimeZone);
        String temp = dateFormat.format(fromCal.getTime());
        setInput(temp);
        return (temp);
    }

    @Override
    public String getTransferValue(int s) throws Exception {

        // Create a calendar object with the source-timezone
        TimeZone fromTimeZone = TimeZone.getTimeZone(timezoneList.get(s));
        Calendar fromCal = new GregorianCalendar(fromTimeZone);

        // set the internal value "input" for the calendar
        dateFormat.setTimeZone(fromTimeZone);
        fromCal.setTime(dateFormat.parse(input));

        // convert the "input" value in the form which can be used externally
        // (the standard decimal date format yyyyMMddHHmmss)
        DateFormat standardDateFormat = new SimpleDateFormat(yyyyMMddHHmmss);
        return standardDateFormat.format(fromCal.getTime());
    }

    @Override
    public String getCard() {
        return "tzCard";
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
