/**
 *
 * NumericalChameleon - more than an unit converter - a NumericalChameleon
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
package net.numericalchameleon.launchers;

import net.numericalchameleon.gui.main.Main;

/**
 * MainGUIPortable is a class that can be used to invoke the program in a portable
 way.
 */
public class MainGUIPortable {

    private final static String USER_HOME = System.getProperty("user.home");

    /**
     * The main method.
     *
     * @param args all program arguments
     */
    public static void main(String[] args) {
        // we overwrite the user.home property to the current working dir
        System.setProperty("user.home", ".");
        Main.main(args);
    }

    /**
     * Returns the value of the user.home property before it was modified
     *
     * @return the value of the user.home property before it was modified
     */
    public static String getUserHomeBackup() {
        return USER_HOME;
    }
}
