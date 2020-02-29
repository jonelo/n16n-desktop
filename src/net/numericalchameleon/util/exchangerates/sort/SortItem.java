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
package net.numericalchameleon.util.exchangerates.sort;

import javax.swing.ImageIcon;

public class SortItem {

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    private final String id;
    private final String iconCode;
    private final String i18nID;
    
    public SortItem(String id, String iconCode, String i18nID) {
        this.id=id;
        this.iconCode=iconCode;
        this.i18nID=i18nID;
    }

    /**
     * @return the iconCode
     */
    public String getIconCode() {
        return iconCode;
    }
    
    public ImageIcon getImageIcon() {
        return new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/"+getIconCode()));
    }
    
    public String geti18nID() {
        return i18nID;
    }
    
//            goButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-next.png"))); // NOI18N
//        goButton.setText(rb.getString("GUI.Bookmarks.GoTo")); // NOI18N

}
