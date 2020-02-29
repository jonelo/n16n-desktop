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
package net.numericalchameleon.util.exchangerates;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import net.numericalchameleon.info.ProgConstants;

public class ExchangeRates {

    private final ArrayList<String> arrayList;
    private String date;
    private boolean logic;
    private String preferredFilter;
    private String preferredDir;
    private boolean append = false;
    private String name;
    private Properties propertiesForAllUsers;

    /**
     * Creates new ExchangeRates
     */
    public ExchangeRates() {
        arrayList = new ArrayList<>();
    }

    public void clear() {
        arrayList.clear();
    }

    public void add(String string) {
        arrayList.add(string);
    }

    public int count() {
        return (arrayList.size());
    }

    public boolean success() {
        return (arrayList.size() > 0);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setLogic(boolean logic) {
        this.logic = logic;
    }

    public boolean getLogic() {
        return logic;
    }

    public void setPreferredFilter(String filter) {
        this.preferredFilter = filter;
    }

    public String getPreferredFilter() {
        return preferredFilter;
    }

    public void setPreferredDirectory(String dir) {
        this.preferredDir = dir;
    }

    public String getPreferredDirectory() {
        return preferredDir;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public boolean isAppend() {
        return append;
    }

    public void writeToDisk() throws Exception {

        File file = new File("../data/units/exchange_rates.list");
        if (!file.exists()) {
            file = new File("../../data/units/exchange_rates.list");
        }
        StringBuilder backup = null;
        if (append) {
            backup = new StringBuilder();
            String thisLine;
            InputStream is = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, ProgConstants.DATAFILE_ENCODING));
            while ((thisLine = br.readLine()) != null) {
                backup.append(thisLine);
                backup.append("\n");
            }
            br.close();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos, ProgConstants.DATAFILE_ENCODING));

        if (backup != null) {
            bw.write(backup.toString());
        }
        // sort
        for (String str : arrayList) {
            bw.write(str + "\n");
        }
        bw.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the propertiesForAllUsers
     */
    public Properties getPropertiesForAllUsers() {
        return propertiesForAllUsers;
    }

    /**
     * @param propertiesForAllUsers the propertiesForAllUsers to set
     */
    public void setGlobalProperties(Properties propertiesForAllUsers) {
        this.propertiesForAllUsers = propertiesForAllUsers;
    }

    public Properties getGlobalProperties() {
        Properties props = new Properties();
        props.setProperty("rates_filter." + getPreferredFilter() + ".supportedRates", Integer.toString(count()));
        props.setProperty("rates_date", getDate());
        props.setProperty("rates_name", getName());
        props.setProperty("rates_logic", getLogic() ? "true" : "false");
        if (getPreferredFilter() != null) {
            props.setProperty("rates_preferred", getPreferredFilter());
        }
        if (getPreferredDirectory() != null) {
            props.setProperty("rates_preferred_dir", getPreferredDirectory());
        }
        return props;
    }

    public Properties getUpdateProperties() {
        Properties props = new Properties();
        // update properties
        props.setProperty("rates_updated", Long.toString(System.currentTimeMillis()));
        // for the vendor information of the rates in the update center
        if (getPreferredFilter() != null) {
            props.setProperty("rates_preferred", getPreferredFilter());
        }
        props.setProperty("rates_vendor", getName());
        return props;
    }
}
