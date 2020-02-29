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

import java.net.URL;
import javax.swing.ImageIcon;

/**
 * The Group.
 */
public class Group {

    private String id;
    private String description;
    private ImageIcon icon;

    /** Creates new Group */
    public Group() {
    }

    /**
     * Creates new Group
     * @param id the id
     * @param description the description
     * @param icon the icon
     */
    public Group(String id, String description, String icon) {
        this.id = id;
        this.description = description;
        setIconPrivate(icon);
    }

    private void setIconPrivate(String icon) {
        this.icon = null;
        try {
            URL url = getClass().getResource("/data/icons/groups/" + icon + ".png");
            this.icon = new ImageIcon(url);
        } catch (Exception e) {
        }
    }

    /**
     * Sets the icon.
     * @param icon to be set
     */
    public void setIcon(String icon) {
        setIconPrivate(icon);
    }

    /**
     * Gets the icon.
     * @return the icon
     */
    public ImageIcon getIcon() {
        return icon;
    }

    /**
     * Gets the id.
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the description.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
