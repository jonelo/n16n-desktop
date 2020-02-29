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
package net.numericalchameleon.gui.common.renderer;

import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import net.numericalchameleon.data.Unit;
import net.numericalchameleon.info.ProgConstants;

public class UnitRecordCellRenderer extends DefaultListCellRenderer {

    private final ResourceBundle iso3166;
    private final static Color LIGHTGRAYDARKER = new Color(0x868686); // Color.lightGray.darker()   

    public UnitRecordCellRenderer(ResourceBundle iso3166) {
        super();
        this.iso3166 = iso3166;
    }
    
    public ResourceBundle getISO3166ResourceBundle() {
        return iso3166;
    }

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);

        if (value != null) {
            Unit unit = (Unit) value;
            String iconCode = unit.getIcon();
            if (iso3166 != null) {
                String countryName = null;
                try {
                    countryName = iso3166.getString(iconCode);
                } catch (Exception e) {
                    System.err.println(e);
                }
                if (countryName != null) {
                    label.setToolTipText(String.format("%s: %s", countryName, unit.getText()));
                } else {
                    label.setToolTipText(unit.getText());
                }
            }
            try {
                URL url = getClass().getResource(ProgConstants.ICONS_FLAGS + iconCode + ".png");
                label.setIcon(new ImageIcon(url));
            } catch (Exception e) {
                System.err.println(e);
            }

            /*
             * if(index %2 == 0) {
             *     if (!isSelected) {
             *         label.setBackground(label.getBackground().brighter());
             *     }
             *  }
             */

            /*
             * String txt = unit.getText();
             * if (txt.length() >= 100) {
             *     txt = txt.substring(0,100) + " ...";
             * }
             * label.setText(txt);
             */
            label.setText(unit.getText());
            label.setForeground(unit.getActive() ? label.getForeground() : LIGHTGRAYDARKER);
            //label.setBackground(isSelected ?  LILA : GRAY);
            //label.setOpaque(isSelected);
        }
        return label;
    }

}
