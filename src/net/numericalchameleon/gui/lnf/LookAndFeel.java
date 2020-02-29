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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import jonelo.sugar.gui.GeneralGUI;
import jonelo.sugar.gui.PropertiesMetalTheme;
import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;

public class LookAndFeel {

    private static MetalTheme oceanTheme = null;
    private static DefaultMetalTheme defaultMetalTheme = null;
    private static com.nilo.plaf.nimrod.NimRODLookAndFeel nlnf = null;

    static {
        defaultMetalTheme = new DefaultMetalTheme();
        oceanTheme = new OceanTheme();
    }

    /**
     * Creates a new instance of LookAndFeel
     */
    public LookAndFeel() {
    }

    public static MetalTheme getOceanTheme() {
        return oceanTheme;
    }

    public static DefaultMetalTheme getDefaultMetalTheme() {
        return defaultMetalTheme;
    }

    public static void setLookAndFeel(String lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println(e);
        }
    }

    public static void setLookAndFeelBasedOnPreferences(LookAndFeelProperties props) {

        setBoldFont(props.isBoldFontWanted());
        setAudioFeedback(props.isAudioFeedbackWanted());

        String lnf = props.getPreferredLnF();
        String theme = props.getPreferredTheme();

        switch (lnf) {
            case "metal":
                LookAndFeel.setMetalTheme(new PropertiesMetalTheme(LookAndFeel.class.getResourceAsStream("/data/themes/" + theme)));
                break;
            case "nimrod":
                LookAndFeel.setNimrodTheme(new PropertiesMetalTheme(LookAndFeel.class.getResourceAsStream("/data/themes/" + theme)));
                break;
            case "mac":
                LookAndFeel.setLookAndFeel(GeneralGUI.LAF_MAC);
                break;
            case "aqua":
                LookAndFeel.setLookAndFeel(GeneralGUI.LAF_AQUA);
                break;
            case "motif":
                LookAndFeel.setLookAndFeel(GeneralGUI.LAF_MOTIF);
                break;
            case "windows":
                LookAndFeel.setLookAndFeel(GeneralGUI.LAF_WINDOWS);
                break;
            case "metal_default":
                LookAndFeel.setMetalTheme(LookAndFeel.getDefaultMetalTheme());
                break;
            case "metal_ocean":
                LookAndFeel.setMetalTheme(LookAndFeel.getOceanTheme());
                break;
            case "nimbus":
                LookAndFeel.setNimbusLookAndFeel();
                break;
            case "system":
                LookAndFeel.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                break;
            case "jtattoo":
                LookAndFeel.setJTattooLnF("com.jtattoo.plaf.texture.TextureLookAndFeel", theme);
                break;
            default:
                break;
        }
    }

    public static void setJTattooLnF(String type, String theme) {
        try {
            try {
                // ignore type for now
                TextureLookAndFeel.setTheme(theme);

                /*
                 Properties props = new Properties();
                 props.put("logoString", "\u2008");
                 TextureLookAndFeel.setCurrentTheme(props);
                */
              
                UIManager.setLookAndFeel(type);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(LookAndFeel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e);
        }        
    }
    
    public static void setNimbusLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println(e);
        }
    }

    public static com.nilo.plaf.nimrod.NimRODLookAndFeel getNimrodLookAndFeel() {
        if (nlnf == null) {
            nlnf = new com.nilo.plaf.nimrod.NimRODLookAndFeel();
        }
        return nlnf;
    }

    public static void setNimrodLnf() {
        try {
            NimRODLookAndFeel.setCurrentTheme(new com.nilo.plaf.nimrod.NimRODTheme());
            UIManager.setLookAndFeel(getNimrodLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e);
        }
    }

    // http://java.sun.com/j2se/1.5.0/docs/guide/swing/SwingChanges.html
    public static void setAudioFeedback(boolean b) {
        UIManager.put("AuditoryCues.playList", b ? UIManager.get("AuditoryCues.allAuditoryCues") : UIManager.get("AuditoryCues.noAuditoryCues"));
    }

    public static void setBoldFont(boolean b) {
        UIManager.put("swing.boldMetal", b);
    }
    /*
     InfoNode LnF, GPL
     public static net.infonode.gui.laf.InfoNodeLookAndFeel getInfonodeLookAndFeel() {
     if (ilnf==null) ilnf = new net.infonode.gui.laf.InfoNodeLookAndFeel();
     return ilnf;
     }
     public static void setInfonodeTheme(MetalTheme theme) {
     try {
     getInfonodeLookAndFeel().setCurrentTheme(theme);
     UIManager.setLookAndFeel(getInfonodeLookAndFeel());
     } catch (Exception e) {}
     }
     */

    public static void setNimrodTheme(MetalTheme theme) {
        try {
            NimRODLookAndFeel.setCurrentTheme(theme);
            UIManager.setLookAndFeel(getNimrodLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e);
        }
    }

    public static void setMetalTheme(MetalTheme theme) {
        try {
            MetalLookAndFeel.setCurrentTheme(theme);
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println(e);
        }
    }

    public static boolean isJavaLookAndFeel(String lookAndFeel) {
        // lnf is not native
        return (!lookAndFeel.equals("native"));
    }

    public static boolean isJavaThemeSupported(String lookAndFeel) {
        return ( lookAndFeel.equals("metal")
                || lookAndFeel.equals("metal_default")
                || lookAndFeel.equals("nimrod")
                );
    }
}
