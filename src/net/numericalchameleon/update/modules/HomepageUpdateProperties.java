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
package net.numericalchameleon.update.modules;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLConnection;
import java.util.Properties;

import net.numericalchameleon.info.ProgInfo;

/**
 * A singleton in order to keep the update.properties from the NC homepage
 *
 * @author jonelo
 */
public class HomepageUpdateProperties {

    private static HomepageUpdateProperties instance;

    private Properties props = null;
    private final static String PROPS_URL = ProgInfo.getInstance().getFullHomepage() + "/download/update.properties";
    private static String userAgent = String.format("NC/%s (%s; %s; Java/%s; %s)",
            ProgInfo.getInstance().getVersion(),
            System.getProperty("os.name"),
            System.getProperty("os.arch"),
            System.getProperty("java.version"),
            System.getProperty("user.timezone")
    );

    public static synchronized HomepageUpdateProperties getInstance() {
        if (HomepageUpdateProperties.instance == null) {
            HomepageUpdateProperties.instance = new HomepageUpdateProperties();
        }
        return HomepageUpdateProperties.instance;
    }

    private HomepageUpdateProperties() {
    }

    public void load() throws MalformedURLException, IOException {
        if (props == null) {
            URL url = null;
            try {
                props = new Properties();

                url = new URL(PROPS_URL);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();                

                urlConnection.setRequestProperty("User-Agent", userAgent);
                //props.load(new URL(PROPS_URL).openStream());
                props.load(urlConnection.getInputStream());

                // props.list(System.out);

                
            } catch (IOException ioe) {
                props = null;                
                throw new IOException("Cannot connect to " + url.getHost());
            }
        }
    }

    public Properties getProperties() {
        return props;
    }

    public String whatIsTheLastestProgramVersion() {
        return props.getProperty("latest_version",
                AppUpdateModule.LATEST_KNOWN_VERSION_VALUE);
    }

    public String whatIsTheLastestL10NVersion() {
        return props.getProperty("latest_l10n_version",
                L10nUpdateModule.LATEST_KNOWN_VERSION_VALUE);
    }

}
