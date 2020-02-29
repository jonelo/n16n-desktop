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
package net.numericalchameleon.data;

import java.math.BigDecimal;
import java.net.URL;
import javax.swing.ImageIcon;
import net.numericalchameleon.info.ProgConstants;

public class Category {

    private String string;
    private ImageIcon icon;
    private String name;
    private int figures = -1; // -1 = n/a
    private boolean logic;
    private boolean hasHelp;

    /** Creates new Category */
    public Category() {
    }

    public Category(String string, String icon) {
        this.string = string;
        _setIcon(icon);
        this.name = null;
        this.logic = true;
        this.hasHelp = false;
    }

    public Category(String string, BigDecimal factor) {
        this.string = string;
        this.icon = null;
        this.name = null;
        this.logic = true;
        this.hasHelp = false;
    }

    public Category(String string, BigDecimal factor, String icon) {
        this.string = string;
        _setIcon(icon);
        this.name = null;
        this.logic = true;
        this.hasHelp = false;
    }

    public void setIcon(String icon) {
        _setIcon(icon);
    }
    
    private void _setIcon(String icon) {
        this.icon = null;
        try {
            URL url = getClass().getResource(icon);
            if (url==null) {
                url = getClass().getResource(ProgConstants.ICONS_CATEGORIES + "notfound.png");
            }
            this.icon = new ImageIcon(url);
        } catch (Exception e) {            
        }
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setPreferredPrecision(int figures) {
        this.figures = figures;
    }

    public int getPreferredPrecision() {
        return figures;
    }

    public void setLogic(boolean logic) {
        this.logic = logic;
    }

    public boolean getLogic() {
        return logic;
    }

    public String getText() {
        return string;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * @return the hasHelp
     */
    public boolean hasHelp() {
        return hasHelp;
    }

    /**
     * @param hasHelp the hasHelp to set
     */
    public void setHasHelp(boolean hasHelp) {
        this.hasHelp = hasHelp;
    }
}
