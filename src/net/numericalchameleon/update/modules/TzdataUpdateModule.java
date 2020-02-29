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

import java.util.Properties;
import jonelo.sugar.util.GeneralProgram;
import net.numericalchameleon.update.UpdateStatus;

public class TzdataUpdateModule extends AbstractUpdateModule {

    public final static String LATEST_KNOWN_VERSION_PROPERTY_NAME = "latest_known_tzdata";
    public final static String LATEST_KNOWN_VERSION = "tzdata2019c";
    public final static String DOWNLOAD_URL = 
            // for Developers
            "http://www.oracle.com/technetwork/java/javase/downloads/index.html";
            // for Consumers
            //"http://www.java.com/download/";


    public TzdataUpdateModule() {
        name = "Time Zone Database";
        vendor = System.getProperty("java.vendor");
        installedVersion = null;
        latestKnownVersion = LATEST_KNOWN_VERSION;
    }

    @Override
    public String getInstalledVersion() {
        if (installedVersion == null) {
            String temp;
            try {
                temp = GeneralProgram.getTimeZoneDataVersion();
            } catch (Exception e) {
                temp = UpdateStatus.UNKNOWN.toString();
                // System.err.println(e);
            }
            installedVersion = temp;
        }
        return installedVersion;
    }

    public void setLatestKnownVersion(Properties props) {
        latestKnownVersion = props.getProperty(
                LATEST_KNOWN_VERSION_PROPERTY_NAME,
                LATEST_KNOWN_VERSION);
    }
    
    @Override
    public UpdateStatus getStatus() {
        String a = getInstalledVersion();
        String b = getLatestKnownVersion();

        if (a == null || b == null
                || a.equals("unknown") || b.equals("unknown")) {
            return UpdateStatus.UNKNOWN;
        }
        UpdateStatus status = (a.compareToIgnoreCase(b) < 0) ? UpdateStatus.OUTDATED : UpdateStatus.UPTODATE;
        if (status == UpdateStatus.OUTDATED) {
            // Relax the tzdata check a bit again
            // version is outdated, but maybe at least the year is identical ?
            if (a.regionMatches(0, b, 0, 10)) { // first 10 chars, e.g. tzdata2013
                return UpdateStatus.OLDER;
            }            
        }
        return status;
    }
}
