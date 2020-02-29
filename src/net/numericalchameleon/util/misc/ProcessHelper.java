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

import net.numericalchameleon.util.io.IOHelper;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProcessHelper {
    public static void startJarApplication(String[] JVMoptions, final Class mainClass, String[] args) throws Exception {
        // Find the java executable
        String javaExecutable = IOHelper.findJRE_java();

        if (javaExecutable == null) {
            throw new Exception("java executable not found.");
        }

        // Find the application
        File currentJar = null;
        try {
            // http://stackoverflow.com/questions/4159802/how-can-i-restart-a-java-application
            currentJar = new File(mainClass.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            throw e;
        }
        // Only .jar files can be passed to java/javaw
        if (!currentJar.getName().endsWith(".jar")) {
            throw new Exception(currentJar.getName() + "is not a .jar file");
        }

        // Build a command to start the app: java -jar application.jar
        final ArrayList<String> command = new ArrayList<>();
        command.add(javaExecutable);
        if (JVMoptions != null) {
            command.addAll(Arrays.asList(JVMoptions));
        }
        command.add("-jar");
        command.add(currentJar.getPath());
        if (args != null) {
            command.addAll(Arrays.asList(args));
        }        

        // Execute it
        final ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
    }

}
