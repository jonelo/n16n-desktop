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
import jonelo.sugar.util.Version;
import net.numericalchameleon.info.ProgInfo;
import net.numericalchameleon.update.UpdateStatus;

public class AppUpdateModule extends AbstractUpdateModule {

    public final static String LATEST_KNOWN_VERSION_PROPERTY_NAME = "latest_known_nc_version";
    public final static String LATEST_KNOWN_VERSION_VALUE = ProgInfo.getInstance().getVersion();

    public AppUpdateModule() {
        name = ProgInfo.getInstance().getProgramName();
        vendor = ProgInfo.getInstance().getVendor();
        installedVersion = LATEST_KNOWN_VERSION_VALUE;
        latestKnownVersion = LATEST_KNOWN_VERSION_VALUE;
    }

    public void setLatestKnownVersion(Properties props) {
        latestKnownVersion = props.getProperty(
                LATEST_KNOWN_VERSION_PROPERTY_NAME,
                LATEST_KNOWN_VERSION_VALUE);
    }

    @Override
    public UpdateStatus getStatus() {
        String a = getInstalledVersion();
        String b = getLatestKnownVersion();

        if (a == null || b == null || a.equals("unknown") || b.equals("unknown")) {
            return UpdateStatus.UNKNOWN;
        }

        return (new Version(a).compareTo(new Version(b)) < 0) ? UpdateStatus.OUTDATED : UpdateStatus.UPTODATE;
    }
}
