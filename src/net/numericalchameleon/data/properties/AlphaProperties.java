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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import static net.numericalchameleon.data.properties.PropertiesManager.isVerbose;
import net.numericalchameleon.util.misc.PropertyTools;

public abstract class AlphaProperties {
    
    protected Properties properties;

    abstract public String getFilename();    
    abstract public void loadProperties();

    public void saveProperties() {
        if (isVerbose()) {
            System.out.printf("Saving properties %s ... \n", getFilename());
        }
        try {
            FileOutputStream out = new FileOutputStream(getFilename());
            properties.store(out, "/* properties updated */");
            if (isVerbose()) {
                System.out.println("OK.");
            }
        } catch (IOException ioe) {
            System.err.println(ioe.toString());
        }
    }
        
    public Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }
    
    public void updateProperties(Properties props) {
        if (properties == null) {
            loadProperties();
        }
        PropertyTools.updateProperties(properties, props);
        saveProperties();
    }

}
