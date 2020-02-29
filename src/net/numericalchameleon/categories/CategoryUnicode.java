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
import java.util.StringTokenizer;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.Unit;

public class CategoryUnicode extends CategoryObject {

    private final static int _DEC = 0,
            _HEX = 1,
            _UNI = 2;
    private static String INVALID;
    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    /**
     * Creates new ClusterBase35
     * @param categoryInterface
     */
    public CategoryUnicode(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "source invalid");
        units = new ArrayList<>();

        units.add(new Unit(_DEC, localize("Moduls.decimalName", "Decimal"), "int"));
        units.add(new Unit(_HEX, localize("Moduls.hexadecimal", "Hexadecimal"), "int"));
        units.add(new Unit(_UNI, localize("Moduls.unicodeCharacter", "Unicode\u00AE Character"), "int"));

        transferUnit = units.get(0);
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(2);
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
        return 0;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return 2;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
    }

    @Override
    public String getInitialValue() {
        return "169"; // the copyright sign
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

        long[] longArray = null;
        // convert to decimal values

        switch (sid) {
            case _HEX: {
                if ((input.length() > 4) && (input.length() % 4 != 0)) {
                    throw new Exception(INVALID);
                }

                try {
                    if (input.length() <= 4) {
                        longArray = new long[1];
                        longArray[0] = Long.valueOf(input, 16);
                        if (longArray[0] < 0) {
                            throw new Exception();
                        }
                    } else {
                        longArray = new long[input.length() / 4];
                        for (int i = 0; i < longArray.length; i++) {
                            longArray[i] = Long.valueOf(input.substring(i * 4, (i + 1) * 4), 16);
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(INVALID);
                }

            }
            break;

            case _UNI: {
                if (input.length() == 0) {
                    return "";
                }
                longArray = new long[input.length()];
                for (int i = 0; i < input.length(); i++) {
                    longArray[i] = (int) input.charAt(i);
                }

            }
            break;

            case _DEC: {
                try {
                    if (input.length() == 0) {
                        return "";
                    }
                    int count = GeneralString.countChar(input, ',');
                    if (count == 0) {
                        long l = Integer.valueOf(input, 10).longValue();
                        if ((l < 0) || (l > 0xFFFFl)) {
                            throw new Exception();
                        }
                        longArray = new long[1];
                        longArray[0] = l;
                    } else {
                        longArray = new long[count + 1]; // 999,123 => count==1, e. g. 2 tokens
                        StringTokenizer st = new StringTokenizer(input, ",");
                        int i = 0;
                        while (st.hasMoreTokens()) {
                            String token = st.nextToken();
                            if (token.equals("")) {
                                longArray[i] = 0;
                            } else {
                                long l = Integer.valueOf(token, 10).longValue();
                                if ((l < 0) || (l > 0xFFFFl)) {
                                    throw new Exception();
                                }
                                longArray[i] = l;
                            }
                            i++;
                        }
                    }

                } catch (Exception e) {
                    throw new Exception(INVALID);
                }

            }
            break;
        }

        // now we have the decimal value,
        // let's convert it to the target

        switch (tid) {
            case _HEX: {
                if (longArray.length == 1) {
                    return Long.toString(longArray[0], 16).toUpperCase();
                }

                StringBuilder sb = new StringBuilder(longArray.length * 4);
                for (int i = 0; i < longArray.length; i++) {
                    sb.append(hexformat(longArray[i], 4).toUpperCase());
                }
                return sb.toString();
            }

            case _UNI: {
                StringBuilder sb = new StringBuilder(longArray.length);
                for (int i = 0; i < longArray.length; i++) {
                    sb.append((char) longArray[i]);
                }
                return sb.toString();
            }

            case _DEC: {
                StringBuilder sb = new StringBuilder(longArray.length * 4); // "999,"
                for (int i = 0; i < longArray.length; i++) {
                    sb.append(longArray[i]);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.toString().length() - 1);
                return sb.toString();
            }
        }
        return null;
    }

    private static String hexformat(long value, int nibbles) {
        StringBuilder sb = new StringBuilder(Long.toHexString(value));
        while (sb.length() < nibbles) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        if (input == null || input.equals("")) {
            input = "0";
            input = getOutput(0, sourceIndex);
        }
        // addValue is supported for one token only
        String temp = getTransferValue(sourceIndex); // decimal
        if (!temp.contains(",")) { // there are more tokens
           BigDecimal big = new BigDecimal(temp);
           big = big.add(plus);
           return setValue(big, sourceIndex);
        }
        return input;
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.equals("")) {
            input = "0";
            input = getOutput(transferUnit, sourceUnit);
        }
        // addValue is supported for one token only
        String temp = getTransferValue(sourceUnit); // decimal
        if (!temp.contains(",")) { // there are more tokens
           BigDecimal big = new BigDecimal(temp);
           big = big.add(plus);
           return setValue(big, sourceUnit);
        }
        return input;
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        setInput(big.toString());
        return getOutput(getSourceDefault(), sourceIndex);
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toString());
        return getOutput(transferUnit, sourceUnit);
    }
    
    @Override
    public String getTransferValue(int s) throws Exception {
        return getOutput(s, getSourceDefault());
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, transferUnit);
    }

    @Override
    public String getCard() {
        return "unicodeCard";
    }
    
}
