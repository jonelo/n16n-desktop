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
package net.numericalchameleon.info;

public class ProgInfo implements ProgName, ProgVersion, ProgVendor, ProgSupport, ProgHomepage {

    private final static String
            HOMEPAGEPREFIX = "http://",
            PROGRAMNAME = "NumericalChameleon",
            VERSION = "3.0.0",
            PATCHLEVEL = "0",
            HOMEPAGE = "www.NumericalChameleon.net",
            FULLHOMEPAGE = HOMEPAGEPREFIX + HOMEPAGE,
            VENDOR = "Johann N. Löfflmann",
            SUPPORTEMAIL = "support@numericalchameleon.net",
            SUPPORTPAGE = HOMEPAGE + "/de/support.html",
            FULLSUPPORTPAGE = HOMEPAGEPREFIX + SUPPORTPAGE;

    // private inner class, initialized by the classloader
    private static final class InstanceHolder {
        // implicit synchronization
        static final ProgInfo INSTANCE = new ProgInfo();
    }

    // A empty constructor avoids the creation of objects (singleton pattern)
    private ProgInfo() {
    }

    /**
     * Returns the singleton instance
     * @return the singleton instance
     */
    public static ProgInfo getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getProgramName() {
        return PROGRAMNAME;
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public String getFullVersion() {
        return VERSION + "." + PATCHLEVEL;
    }

    @Override
    public String getPatchLevel() {
        return PATCHLEVEL;
    }

    @Override
    public String getHomepage() {
        return HOMEPAGE;
    }
    
    @Override
    public String getFullHomepage() {
        return FULLHOMEPAGE;
    }

    @Override
    public String getVendor() {
        return VENDOR;
    }

    @Override
    public String getSupportEmail() {
        return SUPPORTEMAIL;
    }

    @Override
    public String getSupportPage() {
        return SUPPORTPAGE;
    }
    
    @Override
    public String getFullSupportPage() {
        return FULLSUPPORTPAGE;
    }

}
