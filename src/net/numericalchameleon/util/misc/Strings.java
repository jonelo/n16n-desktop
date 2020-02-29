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

import jonelo.sugar.util.GeneralString;

public class Strings {

    public static void removeAllOccurrences(StringBuilder source, String startString, String endString) {
        int begin = source.indexOf(startString);
        int end = source.indexOf(endString);
        while ((begin > -1) && (end > -1) && (end > begin)) {
            source.delete(begin, end + endString.length());
            begin = source.indexOf(startString);
            end = source.indexOf(endString);
        }
    }

    public static String removeAllOccurrences(String source, String startString, String endString) {
        StringBuilder sbuilder = new StringBuilder(source);
        removeAllOccurrences(sbuilder, startString, endString);
        return sbuilder.toString();
    }

    public static void removeAllComments(StringBuilder source) {
        removeAllOccurrences(source, "<!--", "-->");
    }

    public static String removeWhitespaces(String string) {
        StringBuilder sbuf = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if ((string.charAt(i) != ' ') && (string.charAt(i) != '\t')) {
                sbuf.append(string.charAt(i));
            }
        }
        return sbuf.toString();
    }

    /**
     * Replaces all HTML codes that represents character of numbers in String
     * string (this method cares only about characters that are digits, e, E, +
     * or -, dot and comma)
     *
     * @param string the String
     * @return string without any HTML codes
     */
    public static String replaceHTMLCodesRepresentingNumbers(String string) {
        // digits
        for (int i = 0; i <= 9; i++) {
            string = GeneralString.replaceAllStrings(string, "&#" + (i + 48) + ";", String.valueOf(i));
            string = GeneralString.replaceAllStrings(string, "&#x" + Integer.toHexString(i + 48).toLowerCase() + ";", String.valueOf(i));
            string = GeneralString.replaceAllStrings(string, "&#x" + Integer.toHexString(i + 48).toUpperCase() + ";", String.valueOf(i));
        }
        string = GeneralString.replaceAllStrings(string, "&#69;", "E");
        string = GeneralString.replaceAllStrings(string, "&#x45;", "E");
        string = GeneralString.replaceAllStrings(string, "&#101;", "e");
        string = GeneralString.replaceAllStrings(string, "&#x65;", "e");
        string = GeneralString.replaceAllStrings(string, "&#43;", "+");
        string = GeneralString.replaceAllStrings(string, "&#x2B;", "+");
        string = GeneralString.replaceAllStrings(string, "&#x2b;", "+");
        string = GeneralString.replaceAllStrings(string, "&#44;", ",");
        string = GeneralString.replaceAllStrings(string, "&#x2C;", ",");
        string = GeneralString.replaceAllStrings(string, "&#x2c;", ",");
        string = GeneralString.replaceAllStrings(string, "&#45;", "-");
        string = GeneralString.replaceAllStrings(string, "&#x2D;", "-");
        string = GeneralString.replaceAllStrings(string, "&#x2d;", "-");
        string = GeneralString.replaceAllStrings(string, "&#46;", ".");
        string = GeneralString.replaceAllStrings(string, "&#x2E;", ".");
        string = GeneralString.replaceAllStrings(string, "&#x2e;", ".");
        return string.toString();
    }

    // e.g. JSON.parse('\x7B\x22responseCode\x22\x3A\x22SUCCESS\x22,\x22currencyList\x22\x3A\x5B\x7B\x22countryCode\x22\x3A\x22HR\x22,\x22countryName\x22\x3A\x22C
    public static void replaceJavaScriptJSONCodes(StringBuilder buffer) {
        GeneralString.replaceAllStrings(buffer, "\\x7B", "{");
        GeneralString.replaceAllStrings(buffer, "\\x7b", "{");
        GeneralString.replaceAllStrings(buffer, "\\x7D", "}");
        GeneralString.replaceAllStrings(buffer, "\\x7b", "}");
        GeneralString.replaceAllStrings(buffer, "\\x22", "\"");
        GeneralString.replaceAllStrings(buffer, "\\x3A", ":");
        GeneralString.replaceAllStrings(buffer, "\\x3a", ":");
        GeneralString.replaceAllStrings(buffer, "\\x5B", "[");
        GeneralString.replaceAllStrings(buffer, "\\x5b", "[");
        GeneralString.replaceAllStrings(buffer, "\\x5D", "]");
        GeneralString.replaceAllStrings(buffer, "\\x5d", "]");
    }

    /**
     * Removes any HTML Tags in string
     *
     * @param string the String
     * @return string without any HTML tags
     */
    public static String removeTags(String string) {
        StringBuilder sbuf = new StringBuilder();
        boolean tag = false;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '<') {
                tag = true;
            } else {
                if (string.charAt(i) == '>') {
                    tag = false;
                } else {
                    if (!tag) {
                        sbuf.append(string.charAt(i));
                    }
                }
            }
        }
        return sbuf.toString();
    }

}
