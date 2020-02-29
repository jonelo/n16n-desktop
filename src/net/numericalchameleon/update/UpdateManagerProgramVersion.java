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
package net.numericalchameleon.update;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import net.numericalchameleon.info.ProgInfo;
import net.numericalchameleon.update.modules.HomepageUpdateProperties;
import net.numericalchameleon.update.modules.AppUpdateModule;

public class UpdateManagerProgramVersion {

    private final static String CURRENT_VERSION = ProgInfo.getInstance().getVersion();
    private final static String KEY_VERSION_OUTDATED = "version." + CURRENT_VERSION + ".outdated";
    private final static String KEY_VERSION_LASTCHECKED = "version." + CURRENT_VERSION + ".lastChecked";
    private final static String KEY_VERSION_IGNORE_LASTCHECKED = "version.ignoreLastChecked";
    private final static String CHECK_FOR_NEW_PROGRAM_VERSION = "version.updateCheckAllowed";
    
    Properties props;
    

    public UpdateManagerProgramVersion(Properties props) {
        this.props = props;
    }

    public boolean isCurrentVersionOutdatedForSure() {
        return props.getProperty(KEY_VERSION_OUTDATED, "false").equals("true");
    }

    public boolean isCheckForNewProgramVersionAllowed() {
        return props.getProperty(CHECK_FOR_NEW_PROGRAM_VERSION, "true").equals("true");
    }
    
    public boolean isIgnoreLastChecked() {
        return props.getProperty(KEY_VERSION_IGNORE_LASTCHECKED, "false").equals("true");
    }
            
    // checks for a new program version only once a day
    public void checkForNewProgramVersion() {
        if (!isCheckForNewProgramVersionAllowed()) return;
        String todayAsString = new SimpleDateFormat("yyyyMMdd").format(new Date());
        boolean alreadyCheckedToday = props.getProperty(KEY_VERSION_LASTCHECKED, "").equals(todayAsString);
        if (isIgnoreLastChecked()) alreadyCheckedToday = false;
        if (!isCurrentVersionOutdatedForSure() && !alreadyCheckedToday) {
            // ... we query the network
            if (updateStatus().equals(UpdateStatus.OUTDATED)) {
                props.setProperty(KEY_VERSION_OUTDATED, "true");
                props.remove(KEY_VERSION_LASTCHECKED);
            } else {
                props.setProperty(KEY_VERSION_OUTDATED, "false");
                props.setProperty(KEY_VERSION_LASTCHECKED, todayAsString);
            }
        }
    }
    
    // query the network and determine update status of the program
    private UpdateStatus updateStatus() {
         System.out.println("Checking for new version ...");

        HomepageUpdateProperties homepageUpdateProperties = HomepageUpdateProperties.getInstance();
        try {
            homepageUpdateProperties.load();
            AppUpdateModule pvm = new AppUpdateModule();
            pvm.setLatestKnownVersion(homepageUpdateProperties.whatIsTheLastestProgramVersion());
            return pvm.getStatus();
        } catch (IOException ioex) {
            System.err.println(ioex.getMessage());
        }

        return UpdateStatus.UNKNOWN;
    }

}
