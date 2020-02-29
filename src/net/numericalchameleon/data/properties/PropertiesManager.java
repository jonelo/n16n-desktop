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
package net.numericalchameleon.data.properties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    
    public static enum PropertiesType {
        GLOBAL, USER, UPDATE
    }

    private static boolean verbose;

    private final AlphaProperties globalProperties;
    private final AlphaProperties userProperties;
    private final AlphaProperties updateProperties;
    
    public PropertiesManager(){
        globalProperties = new GlobalProperties();
        userProperties = new UserProperties();
        updateProperties = new UpdateProperties();
    }
    
    public void init() {
        loadGlobalProperties();
        // user properties overrules the "Global Properties"
        loadUserProperties();
    }
    
    public Properties getGlobalProperties() {
        return globalProperties.getProperties();
    }
    
    public Properties getUserProperties() {
        return userProperties.getProperties();
    }

    public Properties getUpdateProperties() {
        return updateProperties.getProperties();
    }
    
    
    public void saveGlobalProperties() {
        globalProperties.saveProperties();
    }
    
    public void saveUserProperties() {
        userProperties.saveProperties();
    }
    
    public void saveUpdateProperties() {
        updateProperties.saveProperties();
    }
    
    
    public void updateGlobalProperties(Properties props) {
        globalProperties.updateProperties(props);
    }
    
    public void updateUserProperties(Properties props) {
        userProperties.updateProperties(props);
    }

    public void updateUpdateProperties(Properties props) {    
        updateProperties.updateProperties(props);
    }
    
    
    private void loadGlobalProperties() {
        globalProperties.loadProperties();
    }

    private void loadUserProperties() {
        userProperties.loadProperties();
    }
    
    private void loadUpdateProperties() {
        updateProperties.loadProperties();
    }


    /**
     * @return the verbose
     */
    public static boolean isVerbose() {
        return verbose;
    }

    /**
     * @param verbose
     */
    public static void setVerbose(boolean verbose) {
        PropertiesManager.verbose = verbose;
    }
    
    public static String getPropertyDefault(Properties properties, String code, String defaultString) {
        if (properties.containsKey(code)) {
            return properties.getProperty(code);
        } else {
            return defaultString;
        }
    }

    /**
     * Are the global properties writable?
     * @return whether global properties are writable
     */
    public boolean isGlobalPropertiesWritable() {
        // return new File("../data/" + ProgConstants.FILE_SYSTEM_PROPERTIES).canWrite();
        // does not work as expected on Windows, see also bug
        // https://bugs.openjdk.java.net/browse/JDK-6203387
        // since we still want compatible with JDK 6, we have to workaround the issue
        try {
            File.createTempFile(".check", null, new File("../data/")).delete();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
