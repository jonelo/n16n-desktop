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
package net.numericalchameleon.gui.dialogs.calendarchooser;

import java.util.Calendar;
import java.util.TimeZone;


public class CalendarChooserPayload implements CalendarChooserInterface {

    private Calendar calendar;
    private TimeZone timeZone;
    private boolean cancelled;

    public CalendarChooserPayload() {    
    }
    
    public CalendarChooserPayload(Calendar calendar, TimeZone timeZone) {
        this.calendar = calendar;
        this.timeZone = timeZone;
    }
    
    @Override
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public Calendar getCalcandar() {
        return calendar;
    }

    /**
     * @return the cancelled
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * @param cancelled the cancelled to set
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * @return the timeZone
     */
    @Override
    public TimeZone getTimeZone() {
        return timeZone;
    }

    /**
     * @param timeZone the timeZone to set
     */
    @Override
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    
}
