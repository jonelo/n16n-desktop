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
package net.numericalchameleon.cli;

import net.numericalchameleon.info.ProgConstants;

import java.io.File;

public class CLIResetter {

    public static void removeUserProps() {
        // let's remove the user properties
        System.out.print("Removing user properties ... ");
        File file = new File(System.getProperty("user.home") + File.separator + ProgConstants.FILE_USER_PROPERTIES);

        try {
            if (file.exists()) {
                file.delete();
            }
            System.out.println("OK.");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
