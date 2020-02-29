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
 *******************************************************************************
 */
package net.numericalchameleon.categories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;
import net.numericalchameleon.data.Unit;

public class CategoryDialCodes extends CategoryObject {

    private ArrayList<Unit> units;
    private HashMap<String,String> hashtableFrom;
    private HashMap<String,String> hashtableTo;
    private Unit defaultSourceUnit;
    private Unit defaultTargetUnit;
    private ResourceBundle iso3166;

    public CategoryDialCodes(CategoryInterface categoryInterface) {
        rb = categoryInterface.getResourceBundle();
        iso3166 = categoryInterface.getISO3166ResourceBundle();
        units = new ArrayList<>();
        hashtableFrom = new HashMap<>();
        hashtableTo = new HashMap<>();
        try {
            // read file
            InputStream is = getClass().getResourceAsStream("/data/lists/intphone.list");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String thisLine;
            StringTokenizer st;
            String to, from;
            int id = 0;
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.startsWith("#") && !thisLine.equals("")) {
                    // build hashtable and vector
                    st = new StringTokenizer(thisLine, ":");
                    // we take the countrycode in order to do the lowercase as normal ISO-8859-1 (Turkey fix)
                    String countrycode = st.nextToken().toLowerCase(Locale.US);
                    Unit unit = new Unit(id, (String) iso3166.getString(countrycode), countrycode);
                    units.add(unit);
                    if (countrycode.equals("de")) {
                        defaultSourceUnit = unit;
                    }
                    if (countrycode.equals("us")) {
                        defaultTargetUnit = unit;
                    }
                    if (st.hasMoreTokens()) {
                        to = st.nextToken();
                    } else {
                        to = "";
                    }
                    if (st.hasMoreTokens()) {
                        from = st.nextToken();
                    } else {
                        from = "";
                    }

                    hashtableFrom.put(countrycode, from);
                    hashtableTo.put(countrycode, to);
                    id++;
                }
            }
            br.close();

            // sortieren            
            Collections.sort(units, new Comparator<Unit>() {

                @Override
                public int compare(Unit u1, Unit u2) {
                    return u1.getString().compareTo(u2.getString());
                }

            });

        } catch (IOException e) {
            System.err.println(e);
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
        return units.indexOf(defaultSourceUnit);
    }

    @Override
    public int getTargetDefault() {
        return units.indexOf(defaultTargetUnit);
    }
    
    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
    }

    @Override
    public boolean acceptEmptyStrings() {
        return true;
    }

    @Override
    public boolean isPlusMinusSupported() {
        return false;
    }
    
    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);        
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {    
        
        String codeSource = sourceUnit.getIcon();
        String codeTarget = targetUnit.getIcon();

        // "Keine Verbindung unter dieser Vorwahl"
        if (codeSource.equals(codeTarget)) {
            return "";
        }

        return String.format("%s %s", hashtableFrom.get(codeSource), hashtableTo.get(codeTarget));
    }

    @Override
    public String getInitialValue() {
        return "";
    }

    @Override
    public String addValue(BigDecimal plus, int sourceindex) throws Exception {
        throw new UnsupportedOperationException();
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
         throw new UnsupportedOperationException();
    }

    @Override
    public String setValue(BigDecimal big, int sourceindex) throws Exception {
         throw new UnsupportedOperationException();
    }
    
    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
         throw new UnsupportedOperationException();
    }

    @Override
    public String getTransferValue(int s) throws Exception {
        return null;
    }
    
    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return null;
    }
    
    @Override
    public String getCard() {
        return "emptyCard";
    }

}
