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
package net.numericalchameleon.gui.dialogs.listgenerator;

import java.math.BigDecimal;
import net.numericalchameleon.util.calendarlistformats.ListFormat;

public class ListGeneratorCluster {

    public static final int ONE_TARGET = 0,
            MULTIPLE_TARGETS = 1,
            ALL_TARGETS = 2;
    private BigDecimal startValue, endValue, stepValue;
    private boolean success, listFormatVisible;
    private int targets;
    private ListFormat listFormat;

    /** Creates new MultipleNoticesCluster */
    public ListGeneratorCluster() {
        startValue = new BigDecimal("1");
        endValue = new BigDecimal("1");
        stepValue = new BigDecimal("1");
        targets = ONE_TARGET;
        reset();
    }

    public void reset() {
        success = false;
        listFormatVisible = false;
        listFormat = null;
    }

    public void setStartValue(BigDecimal startValue) {
        this.startValue = startValue;
    }

    public BigDecimal getStartValue() {
        return startValue;
    }

    public void setEndValue(BigDecimal endValue) {
        this.endValue = endValue;
    }

    public BigDecimal getEndValue() {
        return endValue;
    }

    public void setStepValue(BigDecimal stepValue) {
        this.stepValue = stepValue;
    }

    public BigDecimal getStepValue() {
        return stepValue;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setTargets(int targets) {
        this.targets = targets;
    }

    public int getTargets() {
        return targets;
    }

    public void setListFormatVisible(boolean listFormatVisible) {
        this.listFormatVisible = listFormatVisible;
    }

    public boolean isListFormatVisible() {
        return listFormatVisible;
    }

    public void setListFormat(ListFormat listFormat) {
        this.listFormat = listFormat;
    }

    public ListFormat getListFormat() {
        return listFormat;
    }
}
