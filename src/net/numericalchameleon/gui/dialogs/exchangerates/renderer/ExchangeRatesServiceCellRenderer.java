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
package net.numericalchameleon.gui.dialogs.exchangerates.renderer;

import net.numericalchameleon.util.exchangerates.ExchangeRatesFilter;
import java.awt.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import net.numericalchameleon.info.ProgConstants;

public class ExchangeRatesServiceCellRenderer extends DefaultListCellRenderer {

    private final ResourceBundle iso3166;
    
    
    public ExchangeRatesServiceCellRenderer(ResourceBundle iso3166) {
        super();
        this.iso3166 = iso3166;
    }

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);
        
          if (value != null) { 
             
              ExchangeRatesFilter ers = (ExchangeRatesFilter)value;
              String iconCode = ers.getCountry();
              // set tooltip
              if (iso3166 != null) {
                  String countryName = null;
                  try {
                      countryName = iso3166.getString(iconCode);
                  } catch (Exception e) {
                      System.err.println(e);
                  }
                  if (countryName != null) {
                      label.setToolTipText(String.format("%s: %s [%s]", countryName, ers.getFilename(), ers.getSupportedRates()));
                  } else {
                      label.setToolTipText(ers.getFilename());
                  }
              }

              // set icon
              try {
                  URL url = getClass().getResource(ProgConstants.ICONS_FLAGS + iconCode + ".png");
                  label.setIcon(new ImageIcon(url));
              } catch (Exception e) {
                 System.err.println(ers.getFilename()+": "+e);
              }
         
              // set text
              label.setText(ers.getName());
          }

        return label;
    }
}
