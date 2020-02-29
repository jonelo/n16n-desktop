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

import net.numericalchameleon.util.exchangerates.sort.SortItem;
import java.awt.Component;
import java.util.ResourceBundle;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class ExchangeRatesSortActionCellRenderer extends DefaultListCellRenderer {

    private final ResourceBundle rb;
    
    
    public ExchangeRatesSortActionCellRenderer(ResourceBundle rb) {
        super();
        this.rb = rb;
    }

    @Override
    public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value,
                index, isSelected, cellHasFocus);
        
          if (value != null) { 
             
              SortItem sortItem = (SortItem)value;

              // set icon
//              try {             
                  label.setIcon(sortItem.getImageIcon());
  //            } catch (Exception e) {
  //               System.err.println(e);
//              }
         
              // set text
              label.setText(rb.getString(sortItem.geti18nID()));
              label.setToolTipText(rb.getString(sortItem.geti18nID()));
          }

        return label;
    }
}
