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
import static net.numericalchameleon.data.properties.PropertiesManager.isVerbose;
import net.numericalchameleon.info.ProgConstants;

public class GlobalProperties extends AlphaProperties {

    @Override
    public String getFilename() {
        return "../data/" + ProgConstants.FILE_SYSTEM_PROPERTIES;
    }
    
    @Override
    public void loadProperties() {
        if (properties != null) {
            return;
        }
        if (isVerbose()) {
            System.out.print("Loading system properties ... ");
        }
        properties = new Properties();

        properties.setProperty("rates_date", "not defined");
        properties.setProperty("rates_name", "not defined");
        properties.setProperty("rates_logic", "false");
        properties.setProperty("rates_preferred", "ecb.europa.eu.filter");

        try {
            properties.load(getClass().getResourceAsStream("/data/" + ProgConstants.FILE_SYSTEM_PROPERTIES));
            if (isVerbose()) {
                System.out.print("data" + File.separator + ProgConstants.FILE_SYSTEM_PROPERTIES);
                System.out.println(" OK.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
