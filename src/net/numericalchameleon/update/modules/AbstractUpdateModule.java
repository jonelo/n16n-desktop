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

import net.numericalchameleon.update.UpdateStatus;

abstract public class AbstractUpdateModule {

    protected String name;
    protected String vendor;
    protected String installedVersion;
    protected String latestKnownVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public UpdateStatus getStatus() {
        String a = getInstalledVersion();
        String b = getLatestKnownVersion();

        if (a == null || b == null
                || a.equals("unknown") || b.equals("unknown")) {
            return UpdateStatus.UNKNOWN;
        }
        return (a.compareToIgnoreCase(b) < 0) ? UpdateStatus.OUTDATED : UpdateStatus.UPTODATE;
    }

    public boolean isItOutdated() {
        return getStatus().equals(UpdateStatus.OUTDATED);
    }

    public String getInstalledVersion() {
        return installedVersion;
    }

    public void setInstalledVersion(String installedVersion) {
        this.installedVersion = installedVersion;
    }

    public String getLatestKnownVersion() {
        return latestKnownVersion;
    }

    public void setLatestKnownVersion(String version) {
        latestKnownVersion = version;
    }
}
