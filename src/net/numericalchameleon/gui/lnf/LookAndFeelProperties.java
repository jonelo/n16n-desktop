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
package net.numericalchameleon.gui.lnf;

public class LookAndFeelProperties {
    private String preferredLnF;
    private String preferredTheme;
    private boolean javaFrameDecorationWanted;
    private boolean boldFontWanted;
    private boolean audioFeedbackWanted;


    /**
     * @return the boldFontWanted
     */
    public boolean isBoldFontWanted() {
        return boldFontWanted;
    }

    /**
     * @param boldFont the boldFontWanted to set
     */
    public void setBoldFont(boolean boldFont) {
        this.boldFontWanted = boldFont;
    }

    /**
     * @return the audioFeedbackWanted
     */
    public boolean isAudioFeedbackWanted() {
        return audioFeedbackWanted;
    }

    /**
     * @param audioFeedback the audioFeedbackWanted to set
     */
    public void setAudioFeedback(boolean audioFeedback) {
        this.audioFeedbackWanted = audioFeedback;
    }

    /**
     * @return the preferredLnF
     */
    public String getPreferredLnF() {
        return preferredLnF;
    }

    /**
     * @return the preferredTheme
     */
    public String getPreferredTheme() {
        return preferredTheme;
    }

    /**
     * @return the nativeFrameDecorationWanted
     */
    public boolean isJavaFrameDecorationWanted() {
        return javaFrameDecorationWanted;
    }

    /**
     * @param preferredLnF the preferredLnF to set
     */
    public void setPreferredLnF(String preferredLnF) {
        this.preferredLnF = preferredLnF;
    }

    /**
     * @param preferredTheme the preferredTheme to set
     */
    public void setPreferredTheme(String preferredTheme) {
        this.preferredTheme = preferredTheme;
    }

    /**
     * @param javaFrameDecorationWanted
     */
    public void setJavaFrameDecorationWanted(boolean javaFrameDecorationWanted) {
        this.javaFrameDecorationWanted = javaFrameDecorationWanted;
    }

    /**
     * @return the javaFrameDecorationSupported
     */
    public boolean isJavaFrameDecorationSupported() {
        // Java based Look and Feels
        return (preferredLnF.equals("nimbus")
            || preferredLnF.equals("metal_ocean")
            || preferredLnF.equals("metal")
            || preferredLnF.equals("metal_default")
            || preferredLnF.equals("nimrod")
            || preferredLnF.equals("jtattoo"));
    }
    
}
