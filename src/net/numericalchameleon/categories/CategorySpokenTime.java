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
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.util.spokentime.EnglishTime;
import net.numericalchameleon.util.spokentime.GermanTime;
import net.numericalchameleon.util.spokentime.MorseCodeTime;
import net.numericalchameleon.util.spokentime.SpokenTime;
import net.numericalchameleon.util.spokentime.Time;

public class CategorySpokenTime extends CategoryObject {

    private final static int _TRANSFER = -1, _TIME24H = -24, _TIME12H = -12;
    private static String INVALID, TIME24H, TIME12H; 
    private final ArrayList<Unit> sourceUnits, targetUnits;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;
    private final ArrayList<SpokenTime> modules;
    private SpokenTime currentModule;

    /**
     * Creates new ClusterSpokenNumbers
     * @param categoryInterface
     */
    public CategorySpokenTime(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        TIME24H = localize("Moduls.time24h", "Time (24h) [HH:MM]");
        TIME12H = localize("Moduls.time12h", "Time (12h) [HH:MM am/pm]");

        setOneway(true);
        modules = new ArrayList<>();

        // transfer
        transferUnit = new Unit(_TRANSFER);
        
        // source
        sourceUnits = new ArrayList<Unit>();
        sourceUnits.add(new Unit(_TIME24H, TIME24H, "int"));
        sourceUnits.add(new Unit(_TIME12H, TIME12H, "int"));
        
        defaultSourceUnit = sourceUnits.get(0);
        
        // target
        targetUnits = new ArrayList<>();
        // Java provides a method called getDisplayLanguage() in class Locale, but
        // 1. only if you have a localized JRE and
        // 2. only for some languages such as English, German, Italian,
        // but not for Swedish or Portuguese or some other interesting languages
        // So we use our own language resource files :-)

        int index = 0;
        targetUnits.add(new Unit(index++,
                localize("Language.us", "English") + " (12h, colloquial)", "us"));
        modules.add(new EnglishTime(EnglishTime.HourclockType.HOURCLOCK12_COLLOQUIAL_US));

        targetUnits.add(new Unit(index++,
                localize("Language.us", "English") + " (24h, military)", "us"));
        modules.add(new EnglishTime(EnglishTime.HourclockType.HOURCLOCK24_MILITARY_US));

        targetUnits.add(new Unit(index++,
                localize("Language.gb", "English") + " (12h, colloquial)", "gb"));
        modules.add(new EnglishTime(EnglishTime.HourclockType.HOURCLOCK12_COLLOQUIAL_UK));

        targetUnits.add(new Unit(index++,
                localize("Language.gb", "English") + " (24h, military)", "gb"));
        modules.add(new EnglishTime(EnglishTime.HourclockType.HOURCLOCK24_MILITARY_UK));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich, Variante 1)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT1_DE));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich, Variante 2)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT2_DE));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich, Variante 3)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT3_DE));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich, Variante 4)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT4_DE));
        
        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich, Variante 5)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT0_DE));
        
        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, Standard)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_DE));
        
        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, förmlich)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_FORMAL_DE));
        
        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, militärisch)", "de"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_MILITARY_DE));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (12h, umgangssprachlich)", "ch"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK12_VARIANT1_CH));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, Standard)", "ch"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_CH));

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, förmlich)", "ch"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_FORMAL_CH));
        
        targetUnits.add(new Unit(index++,
                localize("Language.de", "German") + " (24h, militärisch)", "ch"));
        modules.add(new GermanTime(GermanTime.HourclockType.HOURCLOCK24_MILITARY_CH));
              
        targetUnits.add(new Unit(index++,
                localize("Language.morsecode.time.classic", "Morsecode (classic)"), "int"));
        modules.add(new MorseCodeTime(MorseCodeTime.ClockType.CLASSIC));

        targetUnits.add(new Unit(index++,
                localize("Language.morsecode.time.simple", "Morsecode (simple)"), "int"));
        modules.add(new MorseCodeTime(MorseCodeTime.ClockType.SIMPLE));
        
        defaultTargetUnit = targetUnits.get(0);

    }

    @Override
    public String getInitialValue() {
        GregorianCalendar gc = new GregorianCalendar();
        int hours = gc.get(Calendar.HOUR_OF_DAY);
        int minutes = gc.get(Calendar.MINUTE);
        
        return String.format("%02d:%02d", hours, minutes);
    }
    
    @Override
    public ArrayList<Unit> getSourceUnits() {
        return sourceUnits;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return targetUnits;
    }
    
    @Override
    public int getSourceDefault() {
        return 0;
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return 0;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }
    
    @Override
    public void play(Unit unit) throws Exception {
        int index = unit.getId();
        if (!(input == null || input.length() == 0)) {
            currentModule = modules.get(index);
            String dir = currentModule.getSoundDir();
            if (dir != null) {
                URL url = getClass().getResource("/data/sounds/" + dir);
                if (url == null) {
                    throw new Exception("not supported");
                }
            } else {
                throw new Exception("not supported");
            }
            currentModule.play();
        }
    }
/*
    @Override
    public void setNumberType(int numberType) {
        for (SpokenTime module : modules) {
            module.setNumberType(numberType);
        }
    }
*/
    @Override
    public void stopPlaying() {
        if (currentModule != null) {
            currentModule.stopPlaying();
        }
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = sourceUnits.get(s);        
        Unit targetUnit = targetUnits.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        Time time;

        switch (sid) { // anything to hours and minutes (in 24 h syntax)

            case _TRANSFER:
                try {
                    time = Time.parse(input, Time.FORMATDEC);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case _TIME24H:
                try {
                    time = Time.parse(input, Time.FORMAT24H);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            case _TIME12H:
                try {
                    time = Time.parse(input, Time.FORMAT12H);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException(INVALID);
                }
                break;

            default:
                throw new IllegalArgumentException("internal error");
        }

        switch (tid) {
            case _TRANSFER:
                return Time.format(time, Time.FORMATDEC);

            case _TIME24H:
                return Time.format(time, Time.FORMAT24H);

            case _TIME12H:
                return Time.format(time, Time.FORMAT12H);

            default:
                currentModule = modules.get(tid);
                currentModule.setTime(time.getHours(), time.getMinutes());
                return currentModule.timeInWords();
        }
    }


    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceindex);
        return addValue(plus, sourceUnit);
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = "0";
            input = getOutput(transferUnit, sourceUnit);
        }
        String temp = getOutput(sourceUnit, transferUnit);
        try {
            Time time = Time.parse(temp, Time.FORMATDEC);
            time.addMinutes(plus.intValue());
            input = Time.format(time, Time.FORMATDEC);
            return getOutput(transferUnit, sourceUnit);
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);
        return setValue(big, sourceUnit);
    }

    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(transferUnit, sourceUnit);
    }
    
    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);
        return getOutput(sourceUnit, transferUnit);
    }
    
    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, transferUnit);
    }
    
    @Override
    public String getCard() {
        return "timeCard";
    }
}
