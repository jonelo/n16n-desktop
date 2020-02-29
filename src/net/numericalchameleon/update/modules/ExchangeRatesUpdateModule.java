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

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;
import net.numericalchameleon.update.UpdateStatus;

public class ExchangeRatesUpdateModule extends AbstractUpdateModule {

    public final static String LATEST_KNOWN_VERSION_PROPERTY_NAME = "latest_known_rates_version";

    private final static String VENDOR_PROPERTY_NAME = "rates_vendor";
    private final static String VENDOR_UNKNOWN = "unknown";

    public ExchangeRatesUpdateModule() {
        name = "Exchange Rates";
        vendor = "?";
        installedVersion = "";
        setLatestKnownVersion();
    }

    public void setVendor(Properties props) {
        vendor = props.getProperty(
                VENDOR_PROPERTY_NAME,
                VENDOR_UNKNOWN);
    }

    public void setInstalledVersion(Properties props) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(Long.parseLong(props.getProperty("rates_updated")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(gc.getTime());
        setInstalledVersion(date);
    }

    private void setLatestKnownVersion() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(gc.getTime());
        setLatestKnownVersion(date);
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
        // maybe outdated, but probably not that old ?
        if (status == UpdateStatus.OUTDATED) {
            if (a.regionMatches(0, b, 0, 6)) { // at least year and month are equal ?
                return UpdateStatus.OLDER;
            }            
        }
        return status;
    }
}
