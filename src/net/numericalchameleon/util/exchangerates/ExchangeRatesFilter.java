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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Properties;

public class ExchangeRatesFilter {

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * @return the replaceJSONCodesBeforeParsing
     */
    public boolean isReplaceJSONCodesBeforeParsing() {
        return replaceJSONCodesBeforeParsing;
    }

    /**
     * @param replaceJSONCodesBeforeParsing the replaceJSONCodesBeforeParsing to
     * set
     */
    public void setReplaceJSONCodesBeforeParsing(boolean replaceJSONCodesBeforeParsing) {
        this.replaceJSONCodesBeforeParsing = replaceJSONCodesBeforeParsing;
    }

    private Properties props;

    // properties for the GUI
    private String name;
    private String provider;
    private String country;
    private String description;
    private String supportedRates;
    private String instruction;
    private String[] websites;
    private String[] filetypes;
    private boolean allowAppend;
    private boolean directDownloadAllowed;
    private boolean enabled;
    private String useragent;
    private String filename;
    private String continent;

    // properties for a exchange rate parsing action
    private boolean initForParsingDone = false;
    private String date;
    private String beforeIso;
    private String afterIso;
    private String before_iso_inv;
    private String after_iso_inv;
    private char decimalSeparator;
    private char decimalPoint;
    private boolean logic;
    private String base;
    private boolean removeTags;
    private boolean removeWhitespaces;
    private boolean replaceCodes;
    private boolean removeCommentBeforeParsing;
    private boolean removeScriptsBeforeParsing;
    private boolean removeIframesBeforeParsing;
    private String searchOnlyFor;
    private String cutHead;
    private int cutHeadCount;
    private String cutBottom;
    private boolean removeTagsBeforeParsing;
    private int valuepos;
    private int factorpos;
    private int factorForAll;
    private boolean replaceJSONCodesBeforeParsing;
    private String nonIsoCodesInFile;
    private boolean useNonIsoCodesOnly;
    private String charset;
    

    /**
     * Initializes the filter with the data from a exchange rate filter file.
     * Attention: due to performance reasons, the constructor initializes only
     * for GUI usage, if you want to use the filter for parsing, call
     * initForParsing() explicitly.
     *
     * @param filenameFilter the filename of the exchange rates filter, expected
     * in UTF-8 charset
     * @throws IOException if there is a problem with the file
     */
    public ExchangeRatesFilter(String filenameFilter) throws IOException {

        this.filename = filenameFilter;
        InputStream inputStream = getClass().getResourceAsStream("/data/rates/" + filenameFilter + ".filter");
        if (inputStream == null) {
            throw new IOException(String.format("Filter called \"%s\" is not supported.", filenameFilter));
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

        props = new Properties();
        props.load(inputStreamReader);

        enabled = props.getProperty("enabled", "true").equals("true");
        if (!enabled) {
            return;
        }

        String filetype = props.getProperty("filechooser", "");
        if (filetype.length() > 0) {
            filetypes = filetype.split(",");
        }

        name = props.getProperty("name", "name not set");
        provider = props.getProperty("provider", "provider not set");
        country = props.getProperty("country", "country not set");
        continent = props.getProperty("continent", "continent not set");
        supportedRates = props.getProperty("supportedRates", "unknown");
        String langdefault = Locale.getDefault().getLanguage();
        description = props.getProperty("description_" + langdefault,
                props.getProperty("description", ""));
        instruction = props.getProperty("instruction_" + langdefault,
                props.getProperty("instruction", ""));

        allowAppend = props.getProperty("allowAppend", "false").equals("true");
        directDownloadAllowed = props.getProperty("directDownload", "false").equals("true");

        String visit = props.getProperty("visit", "");
        int visitadditional = Integer.parseInt(props.getProperty("visit.additional", "0"));

        if (visitadditional > 0) {
            websites = new String[1 + visitadditional];
            websites[0] = visit; // the default
            for (int i = 0; i < visitadditional; i++) {
                int index = i + 1;
                websites[index] = props.getProperty("visit." + index, "");
            }
        } else {
            websites = new String[1];
            websites[0] = visit;
        }

        useragent = props.getProperty("user-agent");
                
    }

    // late (separate) initialization, because we don't need to init the properties that are only used
    // if we actually perform parsing
    public void initForParsing() {
        if (initForParsingDone) {
            return;
        }
        setCharset(props.getProperty("charset", "UTF-8"));
        setDate(props.getProperty("date", ""));
        setBefore_iso(props.getProperty("before_iso", ""));
        setAfter_iso(props.getProperty("after_iso", ""));
        setBefore_iso_inv(props.getProperty("before_iso_inv", ""));
        setAfter_iso_inv(props.getProperty("after_iso_inv", ""));
        setDecimalSeparator(props.getProperty("decimalSeparator", ",").charAt(0));
        setDecimalPoint(props.getProperty("decimalPoint", ".").charAt(0));
        setLogic(props.getProperty("logic", "false").equals("true"));
        setBase(props.getProperty("base", ""));
        setRemoveTags(props.getProperty("removeTags", "false").equals("true"));
        setRemoveWhitespaces(props.getProperty("removeWhitespaces", "false").equals("true"));
        setReplaceCodes(props.getProperty("replaceCodes", "false").equals("true"));
        setRemoveCommentBeforeParsing(props.getProperty("removeCommentBeforeParsing", "false").equals("true"));
        setRemoveScriptsBeforeParsing(props.getProperty("removeScriptsBeforeParsing", "false").equals("true"));
        setRemoveIframesBeforeParsing(props.getProperty("removeIFramesBeforeParsing", "false").equals("true"));
        setSearchOnlyFor(props.getProperty("searchOnlyFor", ""));
        setCutHead(props.getProperty("cutHead", ""));
        setCutHeadCount(Integer.parseInt(props.getProperty("cutHead.count", "1")));
        setCutBottom(props.getProperty("cutBottom", ""));
        setRemoveTagsBeforeParsing(props.getProperty("removeTagsBeforeParsing", "false").equals("true"));
        setReplaceJSONCodesBeforeParsing(props.getProperty("replaceJSONCodesBeforeParsing", "false").equals("true"));
        setNonIsoCodesInFile(props.getProperty("nonIsoCodesInFile", null));
        setUseNonIsoCodesOnly(props.getProperty("useNonIsoCodesOnly", "false").equals("true"));
        

        valuepos = 1;
        try {
            valuepos = Integer.valueOf(props.getProperty("valuepos", "1"));
        } catch (NumberFormatException e) {
        }

        factorpos = 0;
        try {
            factorpos = Integer.valueOf(props.getProperty("factorpos", "0"));
        } catch (NumberFormatException e) {
        }

        factorForAll = 0;
        try {
            factorForAll = Integer.valueOf(props.getProperty("factorForAll", "0"));
        } catch (NumberFormatException e) {
        }

        initForParsingDone = true;
    }

    /**
     * @return the logic
     */
    public boolean isLogic() {
        return logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(boolean logic) {
        this.logic = logic;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the beforeIso
     */
    public String getBefore_iso() {
        return beforeIso;
    }

    /**
     * @param before_iso the beforeIso to set
     */
    public void setBefore_iso(String before_iso) {
        this.beforeIso = before_iso;
    }

    /**
     * @return the afterIso
     */
    public String getAfter_iso() {
        return afterIso;
    }

    /**
     * @param after_iso the afterIso to set
     */
    public void setAfter_iso(String after_iso) {
        this.afterIso = after_iso;
    }

    /**
     * @return the before_iso_inv
     */
    public String getBefore_iso_inv() {
        return before_iso_inv;
    }

    /**
     * @param before_iso_inv the before_iso_inv to set
     */
    public void setBefore_iso_inv(String before_iso_inv) {
        this.before_iso_inv = before_iso_inv;
    }

    /**
     * @return the after_iso_inv
     */
    public String getAfter_iso_inv() {
        return after_iso_inv;
    }

    /**
     * @param after_iso_inv the after_iso_inv to set
     */
    public void setAfter_iso_inv(String after_iso_inv) {
        this.after_iso_inv = after_iso_inv;
    }

    /**
     * @return the decimalSeparator
     */
    public char getDecimalSeparator() {
        return decimalSeparator;
    }

    /**
     * @param decimalSeparator the decimalSeparator to set
     */
    public void setDecimalSeparator(char decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    /**
     * @return the decimalPoint
     */
    public char getDecimalPoint() {
        return decimalPoint;
    }

    /**
     * @param decimalPoint the decimalPoint to set
     */
    public void setDecimalPoint(char decimalPoint) {
        this.decimalPoint = decimalPoint;
    }

    /**
     * @return the base
     */
    public String getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * @return the removeTags
     */
    public boolean isRemoveTags() {
        return removeTags;
    }

    /**
     * @param removeTags the removeTags to set
     */
    public void setRemoveTags(boolean removeTags) {
        this.removeTags = removeTags;
    }

    /**
     * @return the removeWhitespaces
     */
    public boolean isRemoveWhitespaces() {
        return removeWhitespaces;
    }

    /**
     * @param removeWhitespaces the removeWhitespaces to set
     */
    public void setRemoveWhitespaces(boolean removeWhitespaces) {
        this.removeWhitespaces = removeWhitespaces;
    }

    /**
     * @return the replaceCodes
     */
    public boolean isReplaceCodes() {
        return replaceCodes;
    }

    /**
     * @param replaceCodes the replaceCodes to set
     */
    public void setReplaceCodes(boolean replaceCodes) {
        this.replaceCodes = replaceCodes;
    }

    /**
     * @return the removeCommentBeforeParsing
     */
    public boolean isRemoveCommentBeforeParsing() {
        return removeCommentBeforeParsing;
    }

    /**
     * @param removeCommentBeforeParsing the removeCommentBeforeParsing to set
     */
    public void setRemoveCommentBeforeParsing(boolean removeCommentBeforeParsing) {
        this.removeCommentBeforeParsing = removeCommentBeforeParsing;
    }

    /**
     * @return the removeScriptsBeforeParsing
     */
    public boolean isRemoveScriptsBeforeParsing() {
        return removeScriptsBeforeParsing;
    }

    /**
     * @param removeScriptsBeforeParsing the removeScriptsBeforeParsing to set
     */
    public void setRemoveScriptsBeforeParsing(boolean removeScriptsBeforeParsing) {
        this.removeScriptsBeforeParsing = removeScriptsBeforeParsing;
    }

    /**
     * @return the removeIframesBeforeParsing
     */
    public boolean isRemoveIframesBeforeParsing() {
        return removeIframesBeforeParsing;
    }

    /**
     * @param removeIframesBeforeParsing the removeIframesBeforeParsing to set
     */
    public void setRemoveIframesBeforeParsing(boolean removeIframesBeforeParsing) {
        this.removeIframesBeforeParsing = removeIframesBeforeParsing;
    }

    /**
     * @return the searchOnlyFor
     */
    public String getSearchOnlyFor() {
        return searchOnlyFor;
    }

    /**
     * @param searchOnlyFor the searchOnlyFor to set
     */
    public void setSearchOnlyFor(String searchOnlyFor) {
        this.searchOnlyFor = searchOnlyFor;
    }

    /**
     * @return the cutHead
     */
    public String getCutHead() {
        return cutHead;
    }

    /**
     * @param cutHead the cutHead to set
     */
    public void setCutHead(String cutHead) {
        this.cutHead = cutHead;
    }

    /**
     * @return the cutHeadCount
     */
    public int getCutHeadCount() {
        return cutHeadCount;
    }

    /**
     * @param cutHeadCount the cutHeadCount to set
     */
    public void setCutHeadCount(int cutHeadCount) {
        this.cutHeadCount = cutHeadCount;
    }

    /**
     * @return the cutBottom
     */
    public String getCutBottom() {
        return cutBottom;
    }

    /**
     * @param cutBottom the cutBottom to set
     */
    public void setCutBottom(String cutBottom) {
        this.cutBottom = cutBottom;
    }

    /**
     * @return the removeTagsBeforeParsing
     */
    public boolean isRemoveTagsBeforeParsing() {
        return removeTagsBeforeParsing;
    }

    /**
     * @param removeTagsBeforeParsing the removeTagsBeforeParsing to set
     */
    public void setRemoveTagsBeforeParsing(boolean removeTagsBeforeParsing) {
        this.removeTagsBeforeParsing = removeTagsBeforeParsing;
    }

    /**
     * @return the valuepos
     */
    public int getValuepos() {
        return valuepos;
    }

    /**
     * @param valuepos the valuepos to set
     */
    public void setValuepos(int valuepos) {
        this.valuepos = valuepos;
    }

    /**
     * @return the factorpos
     */
    public int getFactorpos() {
        return factorpos;
    }

    /**
     * @param factorpos the factorpos to set
     */
    public void setFactorpos(int factorpos) {
        this.factorpos = factorpos;
    }

    /**
     * @return the factorForAll
     */
    public int getFactorForAll() {
        return factorForAll;
    }

    /**
     * @param factorForAll the factorForAll to set
     */
    public void setFactorForAll(int factorForAll) {
        this.factorForAll = factorForAll;
    }

    public ExchangeRatesFilter() {
    }

    /**
     * @return the supportedRates
     */
    public String getSupportedRates() {
        return supportedRates;
    }

    /**
     * @param supportedRates the supportedRates to set
     */
    public void setSupportedRates(String supportedRates) {
        this.supportedRates = supportedRates;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * @param instruction the instruction to set
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * @return the filetypes
     */
    public String[] getFiletypes() {
        return filetypes;
    }

    /**
     * @param filetypes the filetypes to set
     */
    public void setFiletypes(String[] filetypes) {
        this.filetypes = filetypes;
    }

    /**
     * @return the allowAppend
     */
    public boolean isAllowAppend() {
        return allowAppend;
    }

    /**
     * @param allowAppend the allowAppend to set
     */
    public void setAllowAppend(boolean allowAppend) {
        this.allowAppend = allowAppend;
    }

    /**
     * @return the directDownloadAllowed
     */
    public boolean isDirectDownloadAllowed() {
        return directDownloadAllowed;
    }

    /**
     * @param directDownloadAllowed the directDownloadAllowed to set
     */
    public void setDirectDownloadAllowed(boolean directDownloadAllowed) {
        this.directDownloadAllowed = directDownloadAllowed;
    }

    /**
     * @return the websites
     */
    public String[] getWebsites() {
        return websites;
    }

    /**
     * @param websites the websites to set
     */
    public void setWebsites(String[] websites) {
        this.websites = websites;
    }

    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the useragent
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * @param useragent the useragent to set
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    @Override
    public String toString() {
        return getFilename();
    }

    /**
     * @return the nonIsoCodesInFile
     */
    public String getNonIsoCodesInFile() {
        return nonIsoCodesInFile;
    }

    /**
     * @param nonIsoCodesInFile the nonIsoCodesInFile to set
     */
    public void setNonIsoCodesInFile(String nonIsoCodesInFile) {
        this.nonIsoCodesInFile = nonIsoCodesInFile;
    }

    /**
     * @return the useNonIsoCodesOnly
     */
    public boolean isUseNonIsoCodesOnly() {
        return useNonIsoCodesOnly;
    }

    /**
     * @param useNonIsoCodesOnly the useNonIsoCodesOnly to set
     */
    public void setUseNonIsoCodesOnly(boolean useNonIsoCodesOnly) {
        this.useNonIsoCodesOnly = useNonIsoCodesOnly;
    }
}
