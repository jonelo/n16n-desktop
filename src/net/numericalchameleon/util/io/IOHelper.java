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
package net.numericalchameleon.util.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import jonelo.sugar.io.TeeStream;

public class IOHelper {

    /**
     * Tees the standard output channel
     *
     * @param filename
     * @throws java.io.FileNotFoundException
     */
    public static void teeStdout(String filename) throws FileNotFoundException {
        // Tee the standard output channel
        PrintStream out = new PrintStream(new FileOutputStream(filename));
        PrintStream outTee = new TeeStream(System.out, out);
        System.setOut(outTee);
    }

    /**
     * Tees the standard error channel
     *
     * @param filename
     * @throws java.io.FileNotFoundException
     */
    public static void teeStderr(String filename) throws FileNotFoundException {
        PrintStream err = new PrintStream(new FileOutputStream(filename));
        PrintStream errTee = new TeeStream(System.err, err);
        System.setErr(errTee);
    }

    // Tees both standard input and standard output channel
    public static void teeBothStdoutStderr(String fileOut, String fileErr) throws FileNotFoundException {
        teeStdout(fileOut);
        teeStderr(fileErr);
    }

    public static String findJRE_file(String filename) {
        String prefix = System.getProperty("java.home") + File.separator;
        String jre = "jre" + File.separator;

        String test1 = prefix + jre + filename;
        File file1 = new File(test1);
        if (file1.isFile()) {
            return test1;
        } else {
            String test2 = prefix + filename;
            File file2 = new File(test2);
            if (file2.isFile()) {
                return test2;
            }
        }
        return null;
    }

    public static String findJRE_java() {
        boolean isWindows = System.getProperty("os.name").toLowerCase(Locale.US).startsWith("windows");
        String filename = "bin" + File.separator;
        if (isWindows) {
            filename += "javaw.exe";
        } else {
            filename += "java";
        }
        String prefix = System.getProperty("java.home") + File.separator;
        String jre = "jre" + File.separator;
        // JRE
        String test1 = prefix + filename;
        File file1 = new File(test1);
        if (file1.canExecute()) {
            return test1;
        } else {
            // JDK
            String test2 = prefix + jre + filename;
            File file2 = new File(test2);
            if (file2.canExecute()) {
                return test2;
            }
        }
        return null;
    }

    public static File createTempFile(String name, String suffix) throws IOException {
        File file = File.createTempFile(name, "." + suffix);
        file.deleteOnExit();
        return file;
    }

}
