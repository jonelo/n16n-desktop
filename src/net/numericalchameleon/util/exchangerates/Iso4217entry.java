/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.numericalchameleon.util.exchangerates;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import jonelo.sugar.util.GeneralString;

/**
 *
 * @author Johann
 */
public class Iso4217entry {

    private String isoCode;
    private String description;
    private String flag;
    private boolean validity = true;
    private String sourceTarget = "";
    private ResourceBundle iso4217rb;

    public Iso4217entry() {
        iso4217rb = ResourceBundle.getBundle("data.lang.iso4217");
    }
    
    public Iso4217entry(String line) throws Exception {
        this();
        parse(line);
    }

    public void parse(String line) throws Exception {

        // Format:
        // isoCode:Description:flag:true/false:source/target
        StringTokenizer stringTokenizer = new StringTokenizer(line, ":");
        isoCode = stringTokenizer.nextToken();
        description = stringTokenizer.nextToken();

        try {
            description = GeneralString.encodeUnicode(iso4217rb.getString(isoCode.replaceAll(" ", "_")));
        } catch (MissingResourceException mre) {
        }

        if (stringTokenizer.hasMoreTokens()) {
            flag = stringTokenizer.nextToken().toLowerCase(Locale.US);
            if (stringTokenizer.hasMoreTokens()) {
                validity = stringTokenizer.nextToken().toLowerCase(Locale.US).equals("true");
                if (stringTokenizer.hasMoreTokens()) {
                    sourceTarget = stringTokenizer.nextToken().toLowerCase(Locale.US);
                }
            }
        }

    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the validity
     */
    public boolean isValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    /**
     * @return the sourceTarget
     */
    public String getSourceTarget() {
        return sourceTarget;
    }

    /**
     * @param sourceTarget the sourceTarget to set
     */
    public void setSourceTarget(String sourceTarget) {
        this.sourceTarget = sourceTarget;
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
     * @return the isoCode
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @param isoCode the isoCode to set
     */
    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

}
