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
package net.numericalchameleon.util.exchangerates;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextArea;
import jonelo.sugar.util.Counter;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.util.misc.Strings;
import net.numericalchameleon.util.calendar.TimeTools;

public class CurrencyConverter {

    private static boolean verbose = false;

    public static void setVerbose(boolean flag) {
        verbose = flag;
    }

    private List<Iso4217entry> readIsoFile(List<Iso4217entry> listExisting, String filename, String base) throws IOException {
        List<Iso4217entry> list = new ArrayList<>();
        
        if (listExisting != null) {
            listExisting.forEach((entry) -> {
                list.add(entry);
            });
        }
        
        InputStream is = getClass().getResourceAsStream(filename);
        try ( BufferedReader myInput = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = myInput.readLine()) != null) {
                if (!line.startsWith("#") && !line.equals("")) {
                    try {
                        Iso4217entry entry = new Iso4217entry(line);
                        // parse each line and add it to the list
                        if (base == null || entry.getIsoCode().equals(base)) {
                            list.add(entry);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return list;
    }

    // read the entire iso file
    private List<Iso4217entry> readIsoFile(String filename) throws IOException {
        return readIsoFile(null, filename, null);
    }

    // read only the base from the isofile
    private List<Iso4217entry> readIsoFile(String filename, String base) throws IOException {
        return readIsoFile(null, filename, base);
    }

    private StringBuilder readRatesFile(String filename, String charset) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        // read the rates file you have downloaded
        try ( FileInputStream fis = new FileInputStream(filename);
              InputStreamReader is = new InputStreamReader(fis, charset);
              BufferedReader br = new BufferedReader(is)) {

            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                sb.append(thisLine);
                sb.append("\n");
            }
        }
        return sb;
    }

    // filenameFilter = filter, e. g. pacific filter
    // inputFilename = e. g. rates.html
    public CurrencyConverter(ExchangeRatesFilter filter, String inputFilename, Counter current, JTextArea textArea, ExchangeRates exchangeRates, ResourceBundle iso3166)
            throws Exception {
        try {
            long begin = System.currentTimeMillis();
            // read the iso 4217 resource bundle
            //ResourceBundle iso4217 = ResourceBundle.getBundle("data.lang.iso4217");

            // useful variables
            int count; // each entry
            int real; // real currency codes

            String temp = null;

            // init
            if (textArea != null) {
                textArea.setCaretPosition(0);
                textArea.setText("");
            }

            exchangeRates.setName(filter.getName());

            filter.initForParsing();
            exchangeRates.setLogic(filter.isLogic());

            StringBuilder sb = readRatesFile(inputFilename, "UTF-8");
            // TODO: determine the charset by reading the first kb of the actual HTML document (if it is a HTML)
            // see also https://www.w3.org/International/questions/qa-html-encoding-declarations

            if (filter.isRemoveCommentBeforeParsing()) {
                Strings.removeAllComments(sb);
            }

            if (filter.isReplaceJSONCodesBeforeParsing()) {
                Strings.replaceJavaScriptJSONCodes(sb);
            }

            if (filter.isRemoveScriptsBeforeParsing()) {
                Strings.removeAllOccurrences(sb, "<script>", "</script>");
            }

            if (filter.isRemoveIframesBeforeParsing()) {
                Strings.removeAllOccurrences(sb, "<iframe>", "</iframe>");
            }

            String allText = sb.toString();

            // read iso file
            count = 0;
            real = 0;

            List<Iso4217entry> iso4217list;

            if (!filter.isUseNonIsoCodesOnly()) {
                iso4217list = readIsoFile("/data/lists/iso4217.list");
            } else {
                // let's read only the base record from the iso file
                iso4217list = readIsoFile("/data/lists/iso4217.list", filter.getBase());               
            }

            if (filter.getNonIsoCodesInFile() != null) {
                iso4217list = readIsoFile(iso4217list, "/data/rates/" + filter.getNonIsoCodesInFile(), null);
            }

            Pattern pattern = null;

            // search for date in rates file
            // and store it with exchangeRates.setDate()
            if (filter.getDate().length() == 0) {
                temp = "n/a";
            } else {
                if (filter.getDate().equals("today")) {
                    temp = new Date().toString();
                } else {
                    try {
                        pattern = Pattern.compile(filter.getDate(), Pattern.CASE_INSENSITIVE);
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                    Matcher matcher = pattern.matcher(allText);

                    if (matcher.find()) {
                        temp = matcher.group(1);
                    } else {
                        temp = "n/a";
                    }

                }
            }
            exchangeRates.setDate(temp);

            for (int i = 0; i < filter.getCutHeadCount(); i++) {
                // performance improvement
                if (filter.getCutHead().length() > 0) {
                    int found = allText.indexOf(filter.getCutHead());
                    if (found > 0) {
                        allText = allText.substring(found + filter.getCutHead().length());
                    }
                }
            }

            if (filter.getCutBottom().length() > 0) {
                int found = allText.indexOf(filter.getCutBottom());
                if (found > 0) {
                    allText = allText.substring(0, found);
                }
            }

            if (filter.isRemoveTagsBeforeParsing()) {
                allText = Strings.removeTags(allText);
            }

            // write date to textArea and stdout
            String out = "Exchange rates from: " + temp + "\n";
            if (verbose) {
                System.out.print(out);
            }
            if (textArea != null) {
                textArea.append(out);
            }

            String value = null;
            String last_isocode = "---";

            boolean found = false;
            Counter counter2 = new Counter(0);
            //String expr_template = filter.getBefore_iso() + "   " + filter.getAfter_iso();
            //  String expr_template_inv = filter.getBefore_iso_inv() + "   " + filter.getAfter_iso_inv();
            String expr = null;
            String country = null;

            // System.out.println(allText);
            for (Iso4217entry iso4217entry : iso4217list) {

                // user cancellation?
                current.inc();
                counter2.inc();
                if (current.get() != counter2.get()) {
                    throw new Exception("User cancellation.");
                }
                String isocode = iso4217entry.getIsoCode();

                // do we really need to go through all this?
                if ((filter.getSearchOnlyFor().length() == 0)
                        || (filter.getSearchOnlyFor().length() > 0 && filter.getSearchOnlyFor().contains(isocode))
                        || (filter.getBase().length() > 0 && filter.getBase().equals(isocode))) {

                    if (isocode.equals(filter.getBase())) {
                        last_isocode = filter.getBase();
                        value = "1";
                        found = true;
                    }

                    if (!isocode.equals(last_isocode)) {
                        // begin
                        //expr = GeneralString.replaceString(expr_template, filter.getBefore_iso().length(), isocode);
                        // this has better performance than
                        // but is actually wrong since we also have not only three character codes
                        expr = filter.getBefore_iso() + isocode + filter.getAfter_iso();

                        // search for expression (search for decimal value)
                        try {
                            pattern = Pattern.compile(expr, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                        } catch (Exception e) {
                            System.err.println(e.toString() + ": " + expr);
                        }

                        Matcher matcher = pattern.matcher(allText);

                        if (matcher.find()) {
                            value = matcher.group(filter.getValuepos());
                            String factor = null;

                            if (filter.getFactorForAll() > 1) {
                                factor = filter.getFactorForAll() + "";
                            }

                            if (filter.getFactorpos() > 0) {
                                factor = matcher.group(filter.getFactorpos());
                            }

                            if (factor == null || factor.length() == 0) {
                                factor = "1";
                            }

                            // remove all HTML-Tags if desired
                            if (filter.isRemoveTags()) {
                                value = Strings.removeTags(value);
                            }

                            if (filter.isRemoveWhitespaces()) {
                                value = Strings.removeWhitespaces(value);
                            }

                            // replace all HTML-Codes if desired
                            if (filter.isReplaceCodes()) {
                                value = Strings.replaceHTMLCodesRepresentingNumbers(value);
                            }

                            // remove separator
                            value = GeneralString.removeChar(value, filter.getDecimalSeparator());
                            if (filter.getDecimalPoint() != '.') {
                                // replace decimalPoint with .
                                value = GeneralString.replaceChar(value, filter.getDecimalPoint(), '.');
                            }

                            // calculate value*factor
                            if (filter.getFactorpos() > 0 || filter.getFactorForAll() > 1) {
                                BigDecimal bd = new BigDecimal(value);
                                bd = bd.divide(new BigDecimal(factor), 10, RoundingMode.HALF_UP);
                                value = bd.toString();
                            }

                            if (!value.equals("0")) {

                                // output
                                found = true;
                                real++;

                                count++;
                                country = iso3166.getString(iso4217entry.getFlag());
                                if (country != null) {
                                    country = GeneralString.encodeUnicode(country);
                                }
                                if (country == null) {
                                    country = "";
                                } else {
                                    country = " - " + country;
                                }
                                out = isocode
                                        + " ("
                                        + iso4217entry.getDescription()
                                        + country
                                        + "):"
                                        + value
                                        + ":"
                                        + iso4217entry.getFlag()
                                        + ":"
                                        + iso4217entry.isValidity()
                                        + ":"
                                        + iso4217entry.getSourceTarget();
                                exchangeRates.add(out);
                            } else {
                                found = false;
                                out = isocode + " value is zero";
                            }
                        } else {

                            // bloomberg fix (or any other website which mixes value and 1/values)
                            if (!(filter.getBefore_iso_inv().equals("") && (filter.getAfter_iso_inv().equals("")))) {
                                // begin
                                //expr = GeneralString.replaceString(expr_template_inv, filter.getBefore_iso_inv().length(), isocode);
                                expr = filter.getBefore_iso_inv() + isocode + filter.getAfter_iso_inv();
                                // search for expression (search for decimal value)
                                try {
                                    pattern = Pattern.compile(expr, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
                                } catch (Exception e) {
                                    System.err.println(e.toString() + ": " + expr);
                                }

                                matcher = pattern.matcher(allText);
                                if (matcher.find()) {

                                    value = matcher.group(filter.getValuepos());
                                    String factor = null;
                                    if (filter.getFactorForAll() > 1) {
                                        factor = filter.getFactorForAll() + "";
                                    }

                                    if (filter.getFactorpos() > 0) {
                                        factor = matcher.group(filter.getFactorpos());
                                    }
                                    // if regexp is (\\d*) for factor for example,
                                    // factor can become an empty string
                                    if (factor == null || factor.length() == 0) {
                                        factor = "1";
                                    }

                                    // modify the value you found
                                    // remove separator
                                    value = GeneralString.removeChar(value, filter.getDecimalSeparator());
                                    if (filter.getDecimalPoint() != '.') {
                                        // replace decimalPoint with .
                                        value = GeneralString.replaceChar(value, filter.getDecimalPoint(), '.');
                                    }

                                    BigDecimal bd = new BigDecimal(value);
                                    // calculate value*factor
                                    if (filter.getFactorpos() > 0 || filter.getFactorForAll() > 1) {
                                        bd = bd.divide(new BigDecimal(factor), 10, RoundingMode.HALF_UP);
                                    }
                                    // calculate value = 1/value
                                    bd = BigDecimal.ONE.divide(bd, 10, RoundingMode.HALF_UP);
                                    value = bd.toString();

                                    if (!value.equals("0")) {
                                        // output
                                        found = true;
                                        real++;

                                        count++;
                                        country = iso3166.getString(iso4217entry.getFlag());
                                        if (country != null) {
                                            country = GeneralString.encodeUnicode(country);
                                        }
                                        if (country == null) {
                                            country = "";
                                        } else {
                                            country = " - " + country;
                                        }
                                        out = isocode + " (" + iso4217entry.getDescription() + country + "):" + value + ":" + iso4217entry.getFlag() + ":" + iso4217entry.isValidity() + ":" + iso4217entry.getSourceTarget();
                                        exchangeRates.add(out);
                                    } else {
                                        found = false;
                                        out = isocode + " value is zero";
                                    }
                                } else {
                                    found = false;
                                    out = isocode + " not found";
                                }
                            } else {
                                found = false;
                                out = isocode + " not found";
                            }
                        } // end bloomberg fix
                        last_isocode = isocode;
                    } else // value already parsed
                    {

                        if (found) {
                            count++;
                            country = iso3166.getString(iso4217entry.getFlag());
                            if (country != null) {
                                country = GeneralString.encodeUnicode(country);
                            }
                            if (country == null) {
                                country = "";
                            } else {
                                country = " - " + country;
                            }
                            sb = new StringBuilder(
                                    isocode.length()
                                    + iso4217entry.getDescription().length()
                                    + country.length()
                                    + value.length()
                                    + iso4217entry.getFlag().length()
                                    + (iso4217entry.isValidity() ? "true" : "false").length()
                                    + iso4217entry.getSourceTarget().length() + 7);
                            sb.append(isocode)
                                    .append(" (")
                                    .append(iso4217entry.getDescription())
                                    .append(country)
                                    .append("):")
                                    .append(value)
                                    .append(":")
                                    .append(iso4217entry.getFlag())
                                    .append(":")
                                    .append(iso4217entry.isValidity() ? "true" : "false")
                                    .append(":")
                                    .append(iso4217entry.getSourceTarget());
                            out = sb.toString();
                            exchangeRates.add(out);
                        } else {
                            out = isocode + " not found";
                        }
                    }
                } else {
                    out = isocode + " ignored";
                }
                if (verbose) {
                    System.out.println(out);
                }
                if (textArea != null) {
                    textArea.append(out + "\n");
                    textArea.setCaretPosition(textArea.getDocument().getLength());
                }

            }

            int tempi = current.get();
            if (tempi != counter2.get()) {
                throw new Exception("User cancellation.");
            }
            double d = (count * 100.0 / tempi);
            java.math.BigDecimal bd = new java.math.BigDecimal(d);
            bd = bd.setScale(2, java.math.RoundingMode.HALF_UP);
            d = bd.doubleValue();
            out = count + " of " + tempi + " possible exchange rate codes found for that input file (" + d + " %), [" + real + " unique]\n"
                    + TimeTools.ms2HumanReadableString(System.currentTimeMillis() - begin) + "\n";

            if (verbose) {
                System.out.print(out);
            }
            if (textArea != null) {
                textArea.append(out);
            }
            if (count == 0) {
                exchangeRates.clear();
            }

        } catch (Exception e) {
            exchangeRates.clear();
            System.err.println(e.toString());
            throw e;
        }
    }

}
