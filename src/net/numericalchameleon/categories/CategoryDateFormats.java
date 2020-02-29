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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.numericalchameleon.data.Unit;

public class CategoryDateFormats extends CategoryObject {

    private final static int // source
            cUTC = 0,
            cUNIXTIME = 1,
            cNUMBER = 2,
            cJULIAN = 3;
    private final static String ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private final static String NUMBERFORMAT = "yyyyMMddHHmmss";

    private static String INVALID, INVALIDTARGET;
            
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;
    private final TimeZone timeZoneUTC = TimeZone.getTimeZone("UTC");
    private final HashMap<Integer, Locale> localesHash;
    private int style = DateFormat.MEDIUM;

    public CategoryDateFormats(CategoryInterface clusterInterface) {
        rb = clusterInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        INVALIDTARGET = localize("Moduls.targetInvalid", "Target is invalid");

        units = new ArrayList<>();
        units.add(new Unit(cUTC, "UTC (ISO 8601)", "int"));
        units.add(new Unit(cUNIXTIME, "Unix Time (POSIX Time)", "int"));
        units.add(new Unit(cNUMBER, "UTC (yyyyMMddHHmmss)", "int"));
        units.add(new Unit(cJULIAN, "Julian Day Number (JDN)", "int"));

        transferUnit = units.get(2); // Unix time
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(1);
        
        Locale[] locales = Locale.getAvailableLocales();

        LocaleComparator comparator = new LocaleComparator();
        Arrays.sort(locales, comparator);
        localesHash = new HashMap<>();
        int id = 1000; // a relatively large offset in order to not to interfear with
        for (Locale locale : locales) {
            String locinfo = locale.getCountry();
            if (locinfo != null && locinfo.length() > 0) {
                String langinfo = locale.getLanguage();
                String variant = locale.getVariant();
                String info;
                if (variant != null && variant.length() > 0) {
                    info = locale.getDisplayLanguage()
                            + "/"
                            + locale.getDisplayCountry()
                            + "/"
                            + locale.getDisplayVariant()
                            + " ["
                            + langinfo
                            + "_"
                            + locinfo
                            + "_"
                            + variant
                            + "]";
                } else {
                    info = locale.getDisplayLanguage()
                            + "/"
                            + locale.getDisplayCountry()
                            + " ["
                            + langinfo
                            + "_"
                            + locinfo
                            + "]";
                }
                units.add(new Unit(id, "UTC (" + info + ")", locinfo.toLowerCase(Locale.US)));
                localesHash.put(id, locale);
                id++;
            }
        }
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
        return cUTC;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }
    
    @Override
    public void setNumberType(int numberType) {
        switch (numberType) {
            case 0:
                style = DateFormat.SHORT;
                break;
            case 1:
                style = DateFormat.MEDIUM;
                break;
            case 2:
                style = DateFormat.LONG;
                break;
            case 3:
                style = DateFormat.FULL;
                break;
            default:
                style = DateFormat.MEDIUM;
                break;
        }
    }

    @Override
    public int getTargetDefault() {
        return cUNIXTIME;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }
    
    @Override
    public boolean isOneway() {
        return false;
    }

    
    private GregorianCalendar asSourceUnit(Unit sourceUnit) throws Exception {
        int sid = sourceUnit.getId();
        GregorianCalendar gc = new GregorianCalendar(timeZoneUTC);
        gc.setLenient(false);

        switch (sid) {

            case cUTC:
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601);
                    sdf.setTimeZone(timeZoneUTC);
                    gc.setTime(sdf.parse(input));
                } catch (ParseException e) {
                    throw new Exception(INVALID);
                }
                break;

            case cUNIXTIME:
                try {
                    gc.setTimeInMillis(Long.parseLong(input) * 1000);
                } catch (NumberFormatException e) {
                    throw new Exception(INVALID);
                }
                break;

            case cNUMBER:
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(NUMBERFORMAT);
                    sdf.setTimeZone(timeZoneUTC);
                    gc.setTime(sdf.parse(input));
                } catch (ParseException e) {
                    throw new Exception(INVALID);
                }
                break;

            case cJULIAN:
                try {
                    //fi.joensuu.joyds1.calendar.GregorianCalendar cal = new fi.joensuu.joyds1.calendar.GregorianCalendar(gc);
                    int jdn = Integer.parseInt(input);
                    fi.joensuu.joyds1.calendar.Calendar cal = new fi.joensuu.joyds1.calendar.GregorianCalendar(jdn);
                    GregorianCalendar gctemp = cal.toJavaUtilGregorianCalendar();
                    gctemp.setTimeZone(timeZoneUTC);
                    gc.setTime(gctemp.getTime());
                } catch (NumberFormatException e) {
                    throw new Exception(INVALID);
                }
                break;
            default:
                try {
                    setGC(gc, input, localesHash.get(sid));
                } catch (Exception e) {
                    throw new Exception(INVALID);
                }
                break;
        }
        return gc;
    }

    private String asTargetUnit(GregorianCalendar gc, Unit targetUnit) throws Exception {
        int tid = targetUnit.getId();
        switch (tid) {

            case cUTC: {
                SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601);
                sdf.setTimeZone(timeZoneUTC);
                return sdf.format(gc.getTime());
            }

            case cUNIXTIME:
                return Long.toString(gc.getTimeInMillis() / 1000);

            case cNUMBER: {
                SimpleDateFormat sdf = new SimpleDateFormat(NUMBERFORMAT);
                sdf.setTimeZone(timeZoneUTC);
                return sdf.format(gc.getTime());
            }
            case cJULIAN: {
                fi.joensuu.joyds1.calendar.GregorianCalendar cal = new fi.joensuu.joyds1.calendar.GregorianCalendar(gc);
                return Integer.toString(cal.getJulianDayNumber());
            }

            default:
                try {
                    return formatGC(gc, localesHash.get(tid));
                } catch (Exception e) {
                    throw new Exception(INVALIDTARGET);
                }
        }
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);        
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {        
        GregorianCalendar gc = asSourceUnit(sourceUnit);
        return asTargetUnit(gc, targetUnit);
    }

    private void setGC(GregorianCalendar gc, String input, Locale locale) throws Exception {
        DateFormat df = DateFormat.getDateTimeInstance(style, style, locale);
        SimpleDateFormat sdf = (SimpleDateFormat) df;
        String BC = sdf.getDateFormatSymbols().getEras()[0];
        if (input.contains(BC)) {
            throw new IllegalArgumentException("Before Christ values are not supported for this source unit");
        }
        df.setTimeZone(timeZoneUTC);
        gc.setTime(df.parse(input));
    }

    private String formatGC(GregorianCalendar gc, Locale locale) {
        DateFormat df = DateFormat.getDateTimeInstance(style, style, locale);
        df.setTimeZone(timeZoneUTC);
        String append = "";
        // we append the BC (before christ) string indicator if date's era is BC
        if (gc.get(Calendar.ERA) == GregorianCalendar.BC) {
            SimpleDateFormat sdf = new SimpleDateFormat("G", locale);
            append = " " + sdf.format(gc.getTime());
        }
        return df.format(gc.getTime()) + append;
    }

    @Override
    public String getInitialValue() {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_8601);
        sdf.setTimeZone(timeZoneUTC);
        return sdf.format(new Date());
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return addValue(plus, sourceUnit);
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = getInitialValue();
            input = getOutput(defaultSourceUnit, sourceUnit);
        }
        int count = Integer.parseInt(plus.toPlainString());
        GregorianCalendar gc = asSourceUnit(sourceUnit);
        gc.add(Calendar.DATE, count);
        setInput(asTargetUnit(gc, sourceUnit));
        return input;        
    }

    
    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return setValue(big, sourceUnit);
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(transferUnit, sourceUnit);
    }

    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return getTransferValue(sourceUnit);
    }
    
    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
         return getOutput(sourceUnit, transferUnit);
    }
    
    @Override
    public String getCard() {
        return "dateCard";
    }

}

class LocaleComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (!(o1 instanceof Locale) || !(o2 instanceof Locale)) {
            throw new ClassCastException("A Locale object was expected.");
        }
        Locale c1 = (Locale) o1;
        Locale c2 = (Locale) o2;
        int i = c1.getCountry().compareToIgnoreCase(c2.getCountry());
        // if unequal return result of the comparision above, otherwise check also language
        return (i != 0) ? i : c1.getLanguage().compareToIgnoreCase(c2.getLanguage());
    }
}
