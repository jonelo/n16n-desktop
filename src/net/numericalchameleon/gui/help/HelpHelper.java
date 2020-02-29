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
package net.numericalchameleon.gui.help;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jonelo.sugar.gui.GUIHelper;
import net.numericalchameleon.gui.main.Main;
import net.numericalchameleon.info.ProgInfo;

/**
 *
 * @author Johann Loefflmann
 */
public class HelpHelper {
    
    HelpInterface helpInterface;

    public HelpHelper(HelpInterface helpInterface) {
        this.helpInterface = helpInterface;
    }


    // opens a page in the browser and page is relative to the NC installation folder
    private void openBrowserOfflinePage(String path) {
        String prefix = System.getProperty("user.dir") + "/..";
        try {
            GUIHelper.openLocalFileInBrowser(prefix + "/" + path);
        } catch (IOException | URISyntaxException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // opens a page in the browser and page is relative to the NC homepage
    public void openBrowserOnlinePage(String path) {
        String prefix = ProgInfo.getInstance().getFullHomepage().toLowerCase(Locale.US);
        try {
            GUIHelper.openInBrowser(prefix + "/" + path);
        } catch (IOException | URISyntaxException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void openHelp(String path) {
        String location = "help/" + helpInterface.preferredHelpLanguage() + "/" + path;
        if (helpInterface.isOnlineHelpPreferred()) {
            openBrowserOnlinePage(location);
        } else {
            openBrowserOfflinePage(location);
        }
    }
    
    public void gotoWebpage(String path) {
        try {
            // The NC website is only supported in German and English
            String websiteLanguage = helpInterface.preferredHelpLanguage().equals("de") ? "de" : "en";
            String url
                    = ProgInfo.getInstance().getFullHomepage().toLowerCase(Locale.US)
                    + "/"
                    + websiteLanguage
                    + "/" + path;
            GUIHelper.openInBrowser(url);
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
    }

    public void openHelpForCategory(String category) {

        String language = helpInterface.preferredHelpLanguage();
        String postfix = "help/" + language + "/categories/" + category + ".html";
        String filename = System.getProperty("user.dir") + "/../" + postfix;
        // we don't want a 404, so determine the existance by looking on the local filesystem,
        // because we have the helpsystem online and offline available
        if (new File(filename).exists()) {

            if (helpInterface.isOnlineHelpPreferred()) {
                openBrowserOnlinePage(postfix);
            } else {
                try {
                    GUIHelper.openLocalFileInBrowser(filename);
                } catch (IOException | URISyntaxException e) {
                    System.out.println(e);
                }
            }
        } else {
            JOptionPane.showMessageDialog(helpInterface.getFrame(), "No Help available for this category in this language.\n" + filename);
        }
    }


}
