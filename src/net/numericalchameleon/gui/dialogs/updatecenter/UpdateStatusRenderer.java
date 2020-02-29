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
package net.numericalchameleon.gui.dialogs.updatecenter;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import net.numericalchameleon.gui.common.interfaces.I18NInterface;
import net.numericalchameleon.update.UpdateStatus;

public class UpdateStatusRenderer extends JLabel
        implements TableCellRenderer {

    private final static Color OUTDATED_COLOR = new Color(250, 106, 0);
    private final static Color UPTODATE_COLOR = new Color(69, 243, 85);
    private final static Color OLDER_COLOR = Color.YELLOW;
    private final I18NInterface i18nInterface;

    public UpdateStatusRenderer(I18NInterface i18nInterface) {
        this.i18nInterface = i18nInterface;
        setOpaque(true); // MUST do this for background to show up.
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object object,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        UpdateStatus status = (UpdateStatus) object;
        switch (status.getStatus()) {
            case OUTDATED:
                setBackground(OUTDATED_COLOR);
                break;
            case UPTODATE:
                setBackground(UPTODATE_COLOR);
                break;
            case UNKNOWN:
                setBackground(Color.YELLOW);
                break;
            case OLDER:
                setBackground(OLDER_COLOR);
                break;
        }
        setText(status.toString(i18nInterface));
        return this;
    }
}
