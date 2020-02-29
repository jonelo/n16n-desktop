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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.StringTokenizer;
import jonelo.sugar.math.GeneralMath;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.info.ProgConstants;

public class CategoryGeneric extends CategoryObject {

    private static String INVALID;
    private final ArrayList<Unit> units;
    private final boolean logic;
    private final String filename;
    
    private final Unit transferUnit;
    private final static int _TRANSFER = -1;


    /** Creates new CategoryGeneric
     * @param name
     * @param logic
     * @param categoryInterface
     */
    public CategoryGeneric(String name, boolean logic, CategoryInterface categoryInterface) {

        rb = categoryInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        this.logic = logic;
        this.filename = name;
        units = readTextFromJar("/data/units/" + name + ".list");
        transferUnit = new Unit(_TRANSFER);
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return units;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return units;
    }

    public boolean isSpecialDefaultValue() {
        return false;
    }

    @Override
    public boolean isScientificSupported() {
        return true;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);        
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }
        
    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        BigDecimal x = null;
        try {
            // allow also decimal comma
            x = new BigDecimal(input.replace(decsep, '.'));
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();

        if ((x.compareTo(BigDecimal.ZERO)) != 0) { // only if not zero (avoid a division by zero)
            if ((sourceUnit.isInvert())) {
                x = BigDecimal.valueOf(1).divide(x, getPrecision() + PRECISION, RoundingMode.HALF_UP);
            }

            if (sid != tid) {
                if (logic) {
                    // anything to target
                    x = x.multiply(sourceUnit.getFactor());
                    x = x.divide(targetUnit.getFactor(), getPrecision() + PRECISION, RoundingMode.HALF_UP);
                } else {
                    // target to anything
                    x = x.divide(sourceUnit.getFactor(), getPrecision() + PRECISION, RoundingMode.HALF_UP);
                    x = x.multiply(targetUnit.getFactor());
                }
            }

            if ((targetUnit.isInvert())) {
                x = BigDecimal.ONE.divide(x, getPrecision() + PRECISION, RoundingMode.HALF_UP);
            }
        }

        if (getScientific()) {
            return GeneralMath.decimal2Scientific(x.toPlainString(), getPrecision()).replace('.', decsep);
        } else {
            return x.setScale(getPrecision(), RoundingMode.HALF_UP).toPlainString().replace('.', decsep);
        }
    }

    private ArrayList<Unit> readTextFromJar(String filename) {
        ArrayList<Unit> units = null;
        try {
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, ProgConstants.DATAFILE_ENCODING));
            BigDecimal bigdecimal;
            BigDecimal fraction1, fraction2;
            StringTokenizer st;
            String description;
            units = new ArrayList<>();
            String thisLine;
            int id = 0;
            while ((thisLine = br.readLine()) != null) {
                if (thisLine.length() > 0 && !thisLine.startsWith("#")) {
//System.out.println(thisLine);
                    st = new StringTokenizer(thisLine, ":");
                    description = GeneralString.decodeEncodedUnicode(st.nextToken());

                    String strzahl = st.nextToken();
                    boolean invert = false;
                    if (strzahl.startsWith("!")) {
                        strzahl = strzahl.substring(1);
                        invert = true;
                    }
                    int pos = strzahl.indexOf("/");
                    if (pos == -1) // no fraction
                    {
                        bigdecimal = new BigDecimal(strzahl);
                    } else {
                        fraction1 = new BigDecimal(strzahl.substring(0, pos));
                        fraction2 = new BigDecimal(strzahl.substring(pos + 1, strzahl.length()));
                        bigdecimal = fraction1.divide(fraction2, 1000, RoundingMode.HALF_UP);
                    }
                    String flag = null;
                    if (st.hasMoreTokens()) {
                        flag = st.nextToken().toLowerCase();
                    }
                    Unit ncr = new Unit(id, description, bigdecimal, flag);
                    ncr.setInvert(invert);
                    String temp;
                    if (st.hasMoreTokens()) {
                        temp = st.nextToken();
                        if (temp.equals("false")) {
                            ncr.setActive(false);
                        }

                        if (st.hasMoreTokens()) {
                            temp = st.nextToken();
                            if (temp.equals("source")) {
                                setSourceDefault(id);
                            }
                            if (temp.equals("target")) {
                                setTargetDefault(id);
                            }
                            if (temp.equals("source,target") || temp.equals("target,source")) {
                                setSourceDefault(id);
                                setTargetDefault(id);
                            }
                        }
                    }
                    units.add(ncr);
                    id++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return units;
    }

    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return addValue(plus, sourceUnit);
    }

    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if ((input == null) || (input.length() == 0)) {
            input = "0";
        }
        BigDecimal big = null;
        try {
            big = new BigDecimal(input.replace(decsep, '.'));
        } catch (Exception e) {
            throw new Exception(INVALID);
        }
        big = big.add(plus);
        return big.toString().replace('.', decsep);
    }

    @Override
    public String getTransferValue(int s) throws Exception {
        return getOutput(s, s).replace(decsep, '.');
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        setInput(big.toString());
        return input.replace('.', decsep);
    }

    @Override
    public String getCard() {
        return "sciCard";
    }


}
