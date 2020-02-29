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

import net.numericalchameleon.info.ProgInfo;

public class CLIVersion {

    public static enum Type {
        SHORT("%s %s, patchlevel %s\n"),
        LONG("Program:    %s\n" +
                "Version:    %s\n" +
                "Patchlevel: %s\n");

        private final String format;

        Type(String format) {
            this.format = format;
        }

        public String format() {
            return format;
        }
    }

    private static Type currentType;

    public static void setVerbose(boolean verbose) {
        if (verbose) {
            currentType = Type.LONG;
        } else {
            currentType = Type.SHORT;
        }
    }

    /**
     * Prints the version on standard output.
     */
    public static void printVersion() {
        printVersion(currentType);
    }

    private static void printVersion(Type format) {
        System.out.printf(format.format(),
                ProgInfo.getInstance().getProgramName(),
                ProgInfo.getInstance().getVersion(),
                ProgInfo.getInstance().getPatchLevel());
    }

}
