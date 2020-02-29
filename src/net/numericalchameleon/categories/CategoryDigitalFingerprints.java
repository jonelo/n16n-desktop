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
import java.security.NoSuchAlgorithmException;
import java.util.*;
import jonelo.jacksum.JacksumAPI;
import jonelo.jacksum.algorithm.AbstractChecksum;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.Unit;

public class CategoryDigitalFingerprints extends CategoryObject {

    private ArrayList<Unit> sourceUnits, targetUnits;
    private ArrayList<AbstractChecksum> modules;

    public static String INVALID;

    private final static int TXT_ID = 0, HEX_ID = 1, DEC_ID = 2;

    /**
     * Creates new ClusterSpokenNumbers
     *
     * @param categoryInterface
     */
    public CategoryDigitalFingerprints(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");

        setOneway(true);
        modules = new ArrayList<>();

        sourceUnits = new ArrayList<>();
        sourceUnits.add(new Unit(TXT_ID, localize("Moduls.Text", "Text"), "int"));
        sourceUnits.add(new Unit(HEX_ID, localize("Moduls.hexadecimal", "Hexadecimal"), "int"));
        sourceUnits.add(new Unit(DEC_ID, localize("Moduls.decimalName", "Decimal"), "int"));

        targetUnits = new ArrayList<>();
        try {
            Map map = JacksumAPI.getAvailableAlgorithms();
            Iterator iterator = map.entrySet().iterator();
            int index = 0;
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                targetUnits.add(new Unit(index++, (String) entry.getValue(), "int"));
                AbstractChecksum checksum = JacksumAPI.getChecksumInstance((String) entry.getKey());
                modules.add(checksum);
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
    }

    public static Vector<String[]> getEncodings() {
        Vector<String[]> encodings = new Vector<String[]>();
        try {
            Map map = JacksumAPI.getAvailableEncodings();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                String[] array = new String[2];
                array[0] = (String) entry.getKey();
                array[1] = (String) entry.getValue();
                encodings.add(array);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return encodings;
    }

    public void setNumberType(Object encoding) {
        for (AbstractChecksum module : modules) {
            String enc = ((String[]) encoding)[0];
            if (enc.length() == 0) {
                enc = null;
            }
            module.setEncoding(enc);
        }
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
    public String getInitialValue() {
        return "0123456789";
    }

    @Override
    public boolean acceptEmptyStrings() {
        return true;
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

        // convert to decimal values
        byte[] bytearr = null;

        switch (sid) {
            case TXT_ID:
                bytearr = input.getBytes();
                break;
            case DEC_ID:
                if (input.length() == 0) {
                    bytearr = input.getBytes();
                } else {

                    int count = GeneralString.countChar(input, ',');
                    bytearr = new byte[count + 1];

                    StringTokenizer st = new StringTokenizer(input, ",");
                    int x = 0;
                    while (st.hasMoreTokens()) {
                        int temp = 0;
                        String stemp = null;
                        try {
                            stemp = st.nextToken();
                            temp = Integer.parseInt(stemp);
                        } catch (NumberFormatException nfe) {
                            throw new NumberFormatException(stemp + " is not a decimal number.");
                        }
                        if (temp < 0 || temp > 255) {
                            throw new NumberFormatException("The number " + temp + " is out of range.");
                        }
                        bytearr[x++] = (byte) temp;
                    } // while
                }
                break;
            case HEX_ID:
                // default, a hex sequence is expected
                if ((input.length() % 2) == 1) {
                    throw new NumberFormatException("An even number of nibbles was expected.");
                }
                try {
                    bytearr = new byte[input.length() / 2];
                    int x = 0;
                    for (int i = 0; i < input.length();) {
                        String str = input.substring(i, i += 2);
                        bytearr[x++] = (byte) Integer.parseInt(str, 16);
                    }
                } catch (NumberFormatException nfe) {
                    throw new NumberFormatException("Not a hex number. " + nfe.getMessage());
                }
                break;
            default:
                break;
        }

        // now we have the decimal value,
        // convert it to the target
        AbstractChecksum checksum = (AbstractChecksum) modules.get(tid);
        checksum.reset();
        checksum.update(bytearr);

        return checksum.format("#CHECKSUM");
    }

    @Override
    public String getTransferValue(int s) throws Exception {
        BigInteger big = null;
        BigDecimal bigd = null;
        try {
            bigd = new BigDecimal(input);
            big = bigd.toBigInteger();
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        return big.toString();
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {

        if (sourceUnit.getId() == TXT_ID) {
            return input;
        }
        int sid = sourceUnit.getId();

        if ((input == null) || input.length() == 0) {
            input = "0";
        }

        if (sid == DEC_ID) {
            BigInteger big = null;
            try {
                big = new BigInteger(input, 10);
                big = big.add(plus.toBigInteger());
            } catch (Exception e) {
                
                throw new Exception(INVALID);
            }
            return big.toString(10).toUpperCase();
        }
        
        if (sid == HEX_ID) {
            BigInteger big = null;
            try {
                big = new BigInteger(input, 16);
                big = big.add(plus.toBigInteger());
            } catch (Exception e) {
                throw new Exception(INVALID);
            }
            String result = big.toString(16).toUpperCase();
            if ((result.length() % 2) == 1) {
                result = "0"+result;
            }
            return result;
        }
        return null;

    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        Unit sourceUnit = sourceUnits.get(sourceIndex);
        return addValue(plus, sourceUnit);
    }

    @Override
    public String setValue(BigDecimal big, int sourceindex) throws Exception {
        setInput(big.toString());
        return input;
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCard() {
        return "checksumCard";
    }

}
