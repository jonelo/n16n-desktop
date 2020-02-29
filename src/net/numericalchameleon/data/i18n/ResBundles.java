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
package net.numericalchameleon.data.i18n;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResBundles {

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    private final List<ResBundleRecord> myList;
    private String version;

    /**
     * Creates a new instance of ResBundles
     */
    public ResBundles() {
        myList = new ArrayList<>();
        _read();
    }
    
    private void _read() {
        read();
    }

    public void read() {
        myList.clear();
        try {
            try (InputStream is = ResBundles.class.getResourceAsStream("/data/lists/language.txt"); 
                 BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
                String thisLine;
                
                String versionPrefix="version=";
                while ((thisLine = br.readLine()) != null) {
                    // ignore empty lines and comments
                    if (!(thisLine.length() == 0 || thisLine.startsWith("#"))) {
                        if (thisLine.startsWith(versionPrefix)) {
                            version = thisLine.substring(versionPrefix.length());
                        } else {
                            myList.add(new ResBundleRecord(thisLine));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            myList.clear();
            myList.add(new ResBundleRecord("3.0.0;US:nobody;en;GB;en;English (US)"));
        }
    }

    
    
    public boolean isLanguageSupported(String language) {
        for (ResBundleRecord resBundleRecord : myList) {
            if (resBundleRecord.getLanguage().equals(language)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHelpSupported(String help) {
        for (ResBundleRecord resBundleRecord : myList) {
            if (resBundleRecord.getHelp().equals(help)) {
                return true;
            }
        }
        return false;
    }

    public ResBundleRecord getSupportedResBundleByLanguageID(String language) throws Exception {
        for (ResBundleRecord record : myList) {
            if (record.getLanguage().equals(language)) {
                return record;
            }            
        }
        throw new Exception("Language is not supported.");
    }

    public List<ResBundleRecord> getList() {
        return myList;
    }
    
    /*
    public Vector getVector() {
        Vector v = new Vector();
        Collections.copy(v, myList);
        return v;
    }*/
}
