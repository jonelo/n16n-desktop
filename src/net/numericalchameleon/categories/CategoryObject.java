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
package net.numericalchameleon.categories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ResourceBundle;
import net.numericalchameleon.data.Unit;

abstract public class CategoryObject {

    String input;
    boolean oneway, scientificNotationSupported, plusMinusSupported, scientific;
    int precision, preferredPrecision;
    int sourceDefault, targetDefault;
    protected static char decsep;
    protected ResourceBundle rb;
    protected final static int PRECISION = 1000; // precision for internal calculation

    /** Creates new CategoryObject */
    public CategoryObject() {
        oneway = false;
        scientificNotationSupported = false;
        plusMinusSupported = true;
        scientific = false;
        precision = -1;
        preferredPrecision = -1;
        sourceDefault = 0;
        targetDefault = 0;
    }

    abstract public ArrayList<Unit> getSourceUnits();

    abstract public ArrayList<Unit> getTargetUnits();

    public String getInfoConversion() {
        return "";
    }

    public static void setDecSep(char c) {
        decsep = c;
    }

    public static char getDecSep() {
        return decsep;
    }

    // get the default index for source
    public int getSourceDefault() {
        return sourceDefault;
    }

    // get the default index for target
    public int getTargetDefault() {
        return targetDefault;
    }

    // set the default index for source
    public void setSourceDefault(int i) {
        sourceDefault = i;
    }

    // set the default index for target
    public void setTargetDefault(int i) {
        targetDefault = i;
    }

    // set the input for the Object
    public void setInput(String s) {
        input = s;
    }

    // get the input of the Object
    public String getInput() {
        return input;
    }

    // get the output (the conversion)
    abstract public String getOutput(int s, int t) throws Exception;

    // get the output (the conversion)
    abstract public String getOutput(Unit s, Unit t) throws Exception;

    
    // get the precision
    public int getPrecision() {
        return precision;
    }

    // set the precision
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    // get the preferred precision
    public int getPreferredPrecision() {
        return preferredPrecision;
    }

    // set the preferred precision
    public void setPreferredPrecision(int preferredPrecision) {
        this.preferredPrecision = preferredPrecision;
    }

    // is scientific format wanted?
    public void setScientific(boolean bool) {
        scientific = bool;
    }

    // set scientific format if wanted
    public boolean getScientific() {
        return scientific;
    }

    // is scientific format supported?
    public boolean isScientificSupported() {
        return scientificNotationSupported;
    }

    // set scientific format if supported
    public void setScientificSupported(boolean bool) {
        scientificNotationSupported = bool;
    }

    // is it a Oneway conversion?
    // it can disable the button switch
    public boolean isOneway() {
        return oneway;
    }

    // set the oneway flag
    public void setOneway(boolean b) {
        oneway = b;
    }

    public void play(Unit unit) throws Exception {
    }

    public void setNumberType(int numberType) {
    }

    public void stopPlaying() {
    }

    // do you also accept empty Strings for input?
    // e. g. ClusterDialCode
    public boolean acceptEmptyStrings() {
        return false;
    }

    // is there a value that should be set after
    // the category was changed? If this method returns
    // null, the cluster will be set with a transferValue
    // from previous category
    public String getInitialValue() {
        return null;
    }

    abstract public String getTransferValue(int index) throws Exception;
    
    abstract public String getTransferValue(Unit sourceUnit) throws Exception;

    abstract public String addValue(BigDecimal plus, int sourceindex) throws Exception;
    //abstract public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception;

    abstract public String setValue(BigDecimal big, int sourceindex) throws Exception;    
    //abstract public String setValue(BigDecimal big, Unit sourceUnit) throws Exception;

    protected String localize(String key, String fallback) {
        String value = rb.getString(key);
        return (value == null) ? fallback : value;
    }
    
    abstract public String getCard();

    /**
     * @return the plusMinusSupported
     */
    public boolean isPlusMinusSupported() {
        return plusMinusSupported;
    }

    /**
     * @param plusMinusSupported the plusMinusSupported to set
     */
    public void setPlusMinusSupported(boolean plusMinusSupported) {
        this.plusMinusSupported = plusMinusSupported;
    }
}
