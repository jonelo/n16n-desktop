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

import java.util.ResourceBundle;
import net.numericalchameleon.gui.common.interfaces.I18NInterface;

public class UpdateStatus {
    
    public static enum Status {
        UPTODATE, OUTDATED, UNKNOWN, OLDER;
    }

    public static final UpdateStatus UPTODATE = new UpdateStatus(Status.UPTODATE);
    public static final UpdateStatus UNKNOWN = new UpdateStatus(Status.UNKNOWN);
    public static final UpdateStatus OUTDATED = new UpdateStatus(Status.OUTDATED);
    public static final UpdateStatus OLDER = new UpdateStatus(Status.OLDER);

    private final Status status;    

    private UpdateStatus(Status status) {
        this.status = status;
    }
    
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        switch (status) {
            case UPTODATE:
                return "up to date";
            case OUTDATED:
                return "outdated";
            case UNKNOWN:
                return "unknown";
            case OLDER:
                return "older";
            default:
                return "unknown";
        }
    }
    
    public String toString(I18NInterface i18nInterface) {
        ResourceBundle rb = i18nInterface.getResourceBundle();
        switch (status) {
            case UPTODATE:
                return rb.getString("GUI.UpdateCenter.UpdateStatus.UpToDate");
            case OUTDATED:
                return rb.getString("GUI.UpdateCenter.UpdateStatus.Outdated");
            case UNKNOWN:
                return rb.getString("GUI.UpdateCenter.UpdateStatus.Unknown");
            case OLDER:
                return rb.getString("GUI.UpdateCenter.UpdateStatus.Older");
            default:
                return rb.getString("GUI.UpdateCenter.UpdateStatus.Unknown");
        }
    }

    public boolean equals(UpdateStatus status) {
        return (this.status == status.getStatus());

    }
}
