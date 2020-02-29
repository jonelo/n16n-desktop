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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import net.numericalchameleon.data.i18n.ResBundleRecord;
import net.numericalchameleon.data.i18n.ResBundles;
import static net.numericalchameleon.data.properties.PropertiesManager.isVerbose;
import net.numericalchameleon.info.ProgConstants;
import net.numericalchameleon.util.misc.Constants;

public class UserProperties extends AlphaProperties {

    @Override
    public String getFilename() {
        return System.getProperty("user.home") + File.separator + ProgConstants.FILE_USER_PROPERTIES;
    }

    private void legacyProperties() {
        // don't do anything if we have version 3 of the properties
        // but modify the props in case of older versions
        if (!properties.getProperty("props.version", "0").equals("3")) {

            // set defaults
            Locale defaultLocale = Locale.getDefault();
            String deflang = defaultLocale.getLanguage();
            String defcountry = defaultLocale.getCountry();
            String defhelp = "en";

            // get supported languages
            ResBundles languages = new ResBundles();

            ResBundleRecord language = null;
            try {
                language = languages.getSupportedResBundleByLanguageID(deflang);
                defhelp = language.getHelp();
            } catch (Exception e) {
                // if language is not yet supported by NC, fallback to english
                deflang = "en";
                defhelp = "en";
            }

            // set default properties
            properties.setProperty("view", "0");
            properties.setProperty("language", deflang);
            properties.setProperty("help", defhelp);
            properties.setProperty("country", defcountry);
            properties.setProperty("theme", "Default.theme");
            properties.setProperty("fontsize", "12.0");
            properties.setProperty("props.version", "1");

            try {
                File f = new File(
                        System.getProperty("user.home") + File.separator + ProgConstants.DIR_USER_PROPERTIES);
                if (!f.exists()) {
                    f.mkdir();
                }

                // load user properties (use any properties from the past)
                try {
                    properties.load(new FileInputStream(
                            System.getProperty("user.home") + File.separator + ProgConstants.FILE_USER_PROPERTIES));
                } catch (FileNotFoundException fnfe) {
                    System.out.println(fnfe);
                }

                // props.version 1 is obsoleted, upgrade to props.version 2
                if (properties.getProperty("props.version").equals("1")) {
                    // begin bugfix (for NC 1.5.0 users only);
                    // NC will not start if you are not english, german, french or spain
                    // therefore overwrite the settings with defaults again
                    properties.setProperty("language", deflang);
                    properties.setProperty("categories", deflang);
                    properties.setProperty("country", defcountry);
                    // end bugfix

                    // now we have props.version 2
                    properties.setProperty("props.version", "2");
                }
                // props.version 2 is obsoleted, upgrade to props.version 3
                if (properties.getProperty("props.version").equals("2")) {
                    properties.setProperty("help", defhelp);
                    // remove any old props
                    properties.remove("locationX");
                    properties.remove("locationY");
                    properties.remove("width");
                    properties.remove("height");

                    // begin bugfix (for NC 1.5.0+ users)
                    // problems with Kunststoff LnF on Mac OS X
                    if (System.getProperty("os.name").equals(Constants.MAC_OS_X)) {
                        properties.setProperty("lnf", "system");
                    }
                    // end bugfix

                    // now we have props.version 3
                    properties.setProperty("props.version", "3");
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    @Override
    public void loadProperties() {
        if (isVerbose()) {
            System.out.print("Loading user properties ... ");
        }

        properties = new Properties();
        // load the user properties
        try {
            // the NC user directory
            File f = new File(getFilename());
            if (!f.exists()) {
                f.mkdir();
            }

            // load user properties
            properties.load(new FileInputStream(getFilename()));
        } catch (IOException e) {
            System.err.println(e);
        }

        legacyProperties();

        if (isVerbose()) {
            System.out.print(getFilename());
        }
        Locale.setDefault(new Locale(
                properties.getProperty("language"), properties.getProperty("country")));
        if (isVerbose()) {
            System.out.println(" OK.");
        }
    }
}
