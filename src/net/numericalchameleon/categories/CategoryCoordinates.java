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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.numericalchameleon.data.Unit;
import uk.me.jstott.jcoord.ECEFRef;
import uk.me.jstott.jcoord.LatLng;
import uk.me.jstott.jcoord.MGRSRef;
import uk.me.jstott.jcoord.UTMRef;

public class CategoryCoordinates extends CategoryObject {

    public final static int
            cLATLNGDEC = 0,
            cLATLNGDMS = 1,
            cLATLNGDM = 2,
            cUTM = 3,
            cMAIDENHEAD = 4,
            cMGRS = 5,
            cECEF = 6;
    public static String
            LATLNGDEC,
            LATLNGDM,
            LATLNGDMS,
            UTM,
            LATITUDE,
            LONGITUDE,
            MAIDENHEAD_LOCATOR,
            MGRS,
            ECEF,
            INVALID;
    private final ArrayList<Unit> units;

    /**
     * Creates new ClusterTemperature
     * @param categoryInterface the category
     */
    public CategoryCoordinates(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        LATITUDE = localize("GUI.General.Latitude", "Latitude");
        LONGITUDE = localize("GUI.General.Longitude", "Longitude");
        LATLNGDEC = LATITUDE+"/"+LONGITUDE+" (WGS 84) [9.9, 9.9]";
        LATLNGDM = LATITUDE+"/"+LONGITUDE+" (WGS 84) [9° 9.9' N, 9° 9.9' E]";
        LATLNGDMS = LATITUDE+"/"+LONGITUDE+" (WGS 84) [9° 9' 9.9\" N, 9° 9' 9.9\" E]";
        MAIDENHEAD_LOCATOR = "Maidenhead Locator (6 characters)";
        UTM = "Universal Transverse Mercator (UTM) [9U 9.9 9.9]";
        MGRS = "Military Grid Reference System (MGRS)";
        ECEF = "Earth-Centred, Earth-Fixed Cartesian Co-Ordinate (ECEF)";
        units = new ArrayList<>();

        units.add(new Unit(cLATLNGDEC, LATLNGDEC, "int"));
        units.add(new Unit(cLATLNGDM, LATLNGDM, "int"));
        units.add(new Unit(cLATLNGDMS, LATLNGDMS, "int"));
        units.add(new Unit(cUTM, UTM, "int"));
        units.add(new Unit(cMGRS, MGRS, "int"));        
        units.add(new Unit(cMAIDENHEAD, MAIDENHEAD_LOCATOR, "int"));        
        units.add(new Unit(cECEF, ECEF, "int"));
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
        return cLATLNGDEC;
    }

    @Override
    public int getTargetDefault() {
        return cLATLNGDMS;
    }

    // defaultValue is transferValue
    // is there any special transverValue ?
    public boolean isSpecialDefaultValue() {
        return false;
    }

    @Override
    public boolean isScientificSupported() {
        return false;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
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
                
        LatLng latLng = new LatLng(0.0, 0.0);
        String output = "";
        Pattern pattern;
        Matcher matcher;
        final String ADOUBLE = "([+-]*\\d+[.,]\\d*)";
        final String AINT = "([+-]*\\d+)";

        switch (sid) { // anything to LatLngDec
        
            case cLATLNGDEC:
                // parse input as LatLngDec
                // 0.0, 0.0
                pattern = Pattern.compile(ADOUBLE + ", " + ADOUBLE, Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    double a = Double.parseDouble(matcher.group(1).replace(',', '.'));
                    double b = Double.parseDouble(matcher.group(2).replace(',', '.'));
                    latLng = new LatLng(a, b);
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case cLATLNGDMS:
                // 0° 0' 0.0" N, 0° 0' 0.0" E
                // parse input as LatLngDMS

                final String DMSCOORD = AINT + "° " + AINT + "' " + ADOUBLE + "\"";
                pattern = Pattern.compile(DMSCOORD + " ([NS]), " + DMSCOORD + " ([EW])", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    int a = Integer.parseInt(matcher.group(1));
                    int b = Integer.parseInt(matcher.group(2));
                    double c = Double.parseDouble(matcher.group(3).replace(',', '.'));
                    int ns = matcher.group(4).equals("N") ? LatLng.NORTH : LatLng.SOUTH;

                    int d = Integer.parseInt(matcher.group(5));
                    int e = Integer.parseInt(matcher.group(6));
                    double f = Double.parseDouble(matcher.group(7).replace(',', '.'));
                    int ew = matcher.group(8).equals("E") ? LatLng.EAST : LatLng.WEST;

                    latLng = new LatLng(a, b, c, ns, d, e, f, ew);
                } else {
                    throw new IllegalArgumentException(INVALID);
                }

                break;

            case cLATLNGDM:
                // 0° 0.0' N, 0° 0.0' E
                // parse input as LatLngDMS
                final String DMCOORD = AINT + "° " + ADOUBLE + "' ";
                pattern = Pattern.compile(DMCOORD + "([NS]), " + DMCOORD + "([EW])", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    int a = Integer.parseInt(matcher.group(1));
                    double b = Double.parseDouble(matcher.group(2).replace(',', '.'));
                    int ns = matcher.group(3).equals("N") ? LatLng.NORTH : LatLng.SOUTH;
                    int latmin = (int)Math.floor(b);
                    double latsecs = (b-latmin)*60.0;

                    int d = Integer.parseInt(matcher.group(4));
                    double e = Double.parseDouble(matcher.group(5).replace(',', '.'));
                    int ew = matcher.group(6).equals("E") ? LatLng.EAST : LatLng.WEST;
                    int lngmin = (int)Math.floor(e);
                    double lngsecs = (e-lngmin)*60.0;

                    latLng = new LatLng(a, latmin, latsecs, ns, d, lngmin, lngsecs, ew);
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
                
            case cUTM:
                // 32U 691412.5188777333 5334902.7385507
                pattern = Pattern.compile(AINT + "([A-Z]) " + ADOUBLE + " " + ADOUBLE , Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    int a = Integer.parseInt(matcher.group(1));
                    char b = matcher.group(2).charAt(0);
                    double c = Double.parseDouble(matcher.group(3).replace(',', '.'));
                    double d = Double.parseDouble(matcher.group(4).replace(',', '.'));
                    UTMRef utm = new UTMRef(a, b, c, d);
                    latLng = utm.toLatLng();
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
                
            case cMAIDENHEAD:
                pattern = Pattern.compile("([A-R]{2})([0-9]{2})([A-X]{2})", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    latLng = maidenhead6ToLatLng(input.toUpperCase(Locale.US));
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
                
            case cMGRS:
                try {
                   MGRSRef mgrs = new MGRSRef(input);
                   latLng = mgrs.toLatLng();
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
                
            case cECEF:
                pattern = Pattern.compile("\\(\\s*"+ADOUBLE + "\\s*,\\s*" + ADOUBLE + "\\s*,\\s*" + ADOUBLE+ "\\s*\\)", Pattern.CASE_INSENSITIVE);
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    double x = Double.parseDouble(matcher.group(1).replace(',', '.'));
                    double y = Double.parseDouble(matcher.group(2).replace(',', '.'));
                    double z = Double.parseDouble(matcher.group(3).replace(',', '.'));
                    ECEFRef ecef = new ECEFRef(x, y, z);
                    latLng = ecef.toLatLng();
                } else {
                    throw new IllegalArgumentException(INVALID);
                }
                break;
        }


        // LatLngDec to anything
        switch (tid) {
            case cLATLNGDEC:
                output = latLng.toString();
                break;
            case cLATLNGDMS:
                output = latLng.toDMSString();
                break;
            case cLATLNGDM:
                output = latLng.toDMString();
                break;
            case cUTM:
                output = latLng.toUTMRef().toString();
                break;
            case cMAIDENHEAD:
                output = toSixCharacterMaidenHeadLocatorString(latLng);
                break;
            case cMGRS:
                output = latLng.toMGRSRef().toString();
                break;
            case cECEF:
                ECEFRef ecef = new ECEFRef(latLng);
                output = ecef.toString();
                break;
        }
        return output;

    }
    

    private String toSixCharacterMaidenHeadLocatorString(LatLng latLng) {       
        double longitude = latLng.getLongitude() + 180;
        double latitude = latLng.getLatitude() + 90;

        StringBuilder sb = new StringBuilder();
        longitude /= 2;
        sb.append((char) ('A' + (longitude / 10)));
        sb.append((char) ('A' + (latitude / 10)));

        sb.append((char) ('0' + longitude % 10));
        sb.append((char) ('0' + latitude % 10));

        sb.append((char) ('A' + (longitude % 1) * 24));
        sb.append((char) ('A' + (latitude % 1) * 24));
        return sb.toString();
    }
    
    private LatLng maidenhead6ToLatLng(String input) {
        // Character pairs encode longitude first, and then latitude
        double longitude = 
            // In order to avoid negative numbers in the input data,
            // the system specifies a false easting of 180°
            - 180
            // a field represents 360°/18=20° of longitude
            + ((input.charAt(0) - 'A') * 20.0)
            // a square represents 20°/10=2° of longitude
            + ((input.charAt(2) - '0') * 2.0)
            // a subsquares represents 2°/24=1°/12 of longitude
            + ((input.charAt(4) - 'A') * (1.0 / 12))
            // move to center of a subsquare (longitude)
            + 0.5 / 12;
        double latitude =
            // In order to avoid negative numbers in the input data,
            // the system specifies a false northing of 90°
            // (measured from the South Pole to the North Pole)
            - 90
            // a field represents 180°/18=10° of latitude
            + ((input.charAt(1) - 'A') * 10)
            // a square represents 10°/10=1° of latitude
            + ((input.charAt(3) - '0') * 1.0)
            // a subsquares represents 1°/24 of latitude
            + ((input.charAt(5) - 'A') * (1.0 / 24))
            // move to center of a subsquare (latitude)
            + 0.5 / 24;
        // Constructor uses latitude first, and then longitude
	return new LatLng(latitude, longitude);
    }
    
    @Override
    public String getInitialValue() {
        return "48.13856021337852, 11.572996973991394";
    }


    @Override
    public boolean isPlusMinusSupported() {
        return false;
    }
    
    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public String setValue(BigDecimal big, int sourceindex) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }

    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public String getTransferValue(int s) throws Exception {
        return "";
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return "";
    }
    
    @Override
    public String getCard() {
        return "coordCard";
    }

}
