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
package net.numericalchameleon.util.misc;

import java.util.Map;
import javax.swing.UIManager;
import jonelo.sugar.util.GeneralProgram;
import net.numericalchameleon.info.ProgInfo;

public class SupportNC {

    public static void addProgramPropertiesToMap(Map<String, String> map) {
        map.put("application.name", ProgInfo.getInstance().getProgramName());
        map.put("application.version", ProgInfo.getInstance().getVersion());
        map.put("application.patch.level", ProgInfo.getInstance().getPatchLevel());

        map.put("system.lnf", UIManager.getSystemLookAndFeelClassName());
        try {
            map.put("tzdata.version", GeneralProgram.getTimeZoneDataVersion());
        } catch (Exception e) {
            map.put("tzdata.version", e.getMessage());
        }
    }
}