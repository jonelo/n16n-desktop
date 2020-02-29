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
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.util.spokennumbers.*;

public class CategorySpokenNumbers extends CategoryObject {

    public final static String CODE = "SPOKENNUMBERS";
    
    private final static int _TRANSFER = -1;
    private static String INVALID, DECIMAL;
    
    private final ArrayList<Unit> sourceUnits, targetUnits;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;
    private final ArrayList<SpokenNumber> modules;    
    private SpokenNumber currentModule;

    /** Creates new CategorySpokenNumbers
     * @param clusterInterface
     */
    public CategorySpokenNumbers(CategoryInterface clusterInterface) {
        rb = clusterInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        DECIMAL = localize("Moduls.decimalName", "Decimal");

        setOneway(true);       
        modules = new ArrayList<SpokenNumber>();
        
        // transfer
        transferUnit = new Unit(_TRANSFER);

        // source
        sourceUnits = new ArrayList<>();
        sourceUnits.add(new Unit(0, DECIMAL, "int"));
        
        defaultSourceUnit = sourceUnits.get(0);

        // target
        targetUnits = new ArrayList<>();
        // Java provides a method called getDisplayLanguage() in class Locale, but
        // 1. only if you have a localized JRE and
        // 2. only for some languages like english, german, italian,
        // but not swedish or portuguese or other interesting languages
        // So we use our own language resource files ;-)

        int index = 0;
        targetUnits.add(new Unit(index++,
                localize("Language.us", "English (US)"), "us"));
        modules.add(new USEnglishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.gb", "English (UK)"), "gb"));
        modules.add(new UKEnglishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.fr", "French (France)"), "fr"));
        modules.add(new FrenchNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.es", "Spanish"), "es"));
        modules.add(new SpanishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.de", "German"), "de"));
        modules.add(new GermanNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.de_CH", "German (Switzerland)"), "ch"));
        modules.add(new GermanSwitzerlandNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.fr_CH", "French (Switzerland)"), "ch"));
        modules.add(new FrenchSwitzerlandNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.it", "Italian"), "it"));
        modules.add(new ItalianNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.fi", "Finnish"), "fi"));
        modules.add(new FinnishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.in_hindi", "Hindi"), "in"));
        modules.add(new IndianHindiNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.in_tamil", "Tamil"), "in"));
        modules.add(new IndianTamilNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.hu", "Hungarian"), "hu"));
        modules.add(new HungarianNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.se", "Swedish"), "se"));
        modules.add(new SwedishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.tr", "Turkish"), "tr"));
        modules.add(new TurkishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.pl", "Polish"), "pl"));
        modules.add(new PolishNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.br", "Portuguese (Brazil)"), "br"));
        modules.add(new PortugueseBrazilNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.pt", "Portuguese"), "pt"));
        modules.add(new PortugueseNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.ro", "Romanian"), "ro"));
        modules.add(new RomanianNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.ru", "Russian"), "ru"));
        modules.add(new RussianNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.jp", "Japanese"), "jp"));
        modules.add(new JapaneseNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.nl", "Dutch"), "nl"));
        modules.add(new DutchNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.fr_BE", "French (Belgium)"), "be"));
        modules.add(new FrenchBelgiumNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.to", "Tongan"), "to"));
        modules.add(new TonganNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.esp", "Esperanto"), "esp"));
        modules.add(new EsperantoNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.klingon", "Klingon"), "startrek"));
        modules.add(new KlingonNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.lat", "Latin"), "int"));
        modules.add(new LatinNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.morsecode", "Morsecode"), "int"));
        modules.add(new MorseCodeNumber());

        targetUnits.add(new Unit(index++,
                localize("Language.telephone", "Telephone"), "int"));
        modules.add(new TouchtoneNumber());

        defaultTargetUnit = targetUnits.get(0);
    }

    @Override
    public String getInitialValue() {
        return "0";
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

    @Override
    public void stopPlaying() {
        if (currentModule != null) {
            currentModule.stopPlaying();
        }
    }
    
    @Override
    public void setNumberType(int numberType) {
        for (SpokenNumber module : modules) {
            module.setNumberType(numberType);
        }
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }
    
    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = sourceUnits.get(s);
        Unit targetUnit = targetUnits.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int tid = targetUnit.getId(); 
        
        // is input a valid (big) integer?        
        try {
            BigInteger big = new BigDecimal(input).toBigInteger();
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        // sid is always 0
        currentModule = modules.get(tid);

        currentModule.setNumber(input);
        return currentModule.toString();
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceindex);
        return addValue(plus, sourceUnit);
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            input = "0";
        }

        BigInteger big;
        try {
            big = new BigDecimal(input).toBigInteger();
        } catch (Exception e) {
            throw new Exception(INVALID);
        }

        big = big.add(plus.toBigInteger());
        return big.toString();
    }

    @Override
    public String setValue(BigDecimal big, int sourceindex) throws Exception {
        setInput(big.toPlainString());
        return input;
    }


    @Override
    public String getTransferValue(int s) throws Exception {
        Unit sourceUnit = sourceUnits.get(s);
        return getTransferValue(sourceUnit);
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        BigInteger big;
        try {
            big = new BigDecimal(input).toBigInteger();
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        return big.toString();        
    }
    
    @Override
    public String getCard() {
        return "soundCard";
    }

}
