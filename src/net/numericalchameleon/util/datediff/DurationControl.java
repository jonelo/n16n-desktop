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
package net.numericalchameleon.util.datediff;

/**
 * DurationControl
 */
public class DurationControl {

    private boolean leapSecondsWanted;
    private boolean secondsWanted;
    private boolean minutesWanted;
    private boolean hoursWanted;
    private boolean daysWanted;
    private boolean weeksWanted;
    private boolean monthsWanted;
    private boolean quartersWanted;
    private boolean yearsWanted;
    private boolean decadesWanted;
    private boolean centuriesWanted;
    
    private void _setAll(boolean bool) {
        leapSecondsWanted = bool;
        secondsWanted = bool;
        minutesWanted = bool;
        hoursWanted = bool;
        daysWanted = bool;
        weeksWanted = bool;

        monthsWanted = bool;
        quartersWanted = bool;
        yearsWanted = bool;
        decadesWanted = bool;
        centuriesWanted = bool;
    }
    
    public void setAll(boolean bool) {
        _setAll(bool);
    }

    public void setExactly() {
        leapSecondsWanted = true;
        secondsWanted = true;
        minutesWanted = true;
        hoursWanted = true;
        daysWanted = true;
        weeksWanted = true;

        monthsWanted = false;
        quartersWanted = false;
        yearsWanted = false;
        decadesWanted = false;
        centuriesWanted = false;
    }
    
    public void setColloquial() {
        _setAll(true);
    }

    /** Creates a new instance of DurationControl */
    public DurationControl() {
        _setAll(false);
    }

    public boolean isSecondsWanted() {
        return secondsWanted;
    }

    public void setSecondsWanted(boolean secondsWanted) {
        this.secondsWanted = secondsWanted;
    }

    public boolean isMinutesWanted() {
        return minutesWanted;
    }

    public void setMinutesWanted(boolean minutesWanted) {
        this.minutesWanted = minutesWanted;
    }

    public boolean isHoursWanted() {
        return hoursWanted;
    }

    public void setHoursWanted(boolean hoursWanted) {
        this.hoursWanted = hoursWanted;
    }

    public boolean isDaysWanted() {
        return daysWanted;
    }

    public void setDaysWanted(boolean daysWanted) {
        this.daysWanted = daysWanted;
    }

    public boolean isWeeksWanted() {
        return weeksWanted;
    }

    public void setWeeksWanted(boolean weeksWanted) {
        this.weeksWanted = weeksWanted;
    }

    public boolean isMonthsWanted() {
        return monthsWanted;
    }

    public void setMonthsWanted(boolean monthsWanted) {
        this.monthsWanted = monthsWanted;
    }

    public boolean isQuartersWanted() {
        return quartersWanted;
    }

    public void setQartersWanted(boolean quartersWanted) {
        this.setQuartersWanted(quartersWanted);
    }

    public boolean isYearsWanted() {
        return yearsWanted;
    }

    public void setYearsWanted(boolean yearsWanted) {
        this.yearsWanted = yearsWanted;
    }

    public boolean isDecadesWanted() {
        return decadesWanted;
    }

    public void setDecadesWanted(boolean decadesWanted) {
        this.decadesWanted = decadesWanted;
    }

    public void setQuartersWanted(boolean quartersWanted) {
        this.quartersWanted = quartersWanted;
    }

    public boolean isLeapSecondsWanted() {
        return leapSecondsWanted;
    }

    public void setLeapSecondsWanted(boolean leapSecondsWanted) {
        this.leapSecondsWanted = leapSecondsWanted;
    }
    
    /**
     * @return the centuriesWanted
     */
    public boolean isCenturiesWanted() {
        return centuriesWanted;
    }

    /**
     * @param centuriesWanted the centuriesWanted to set
     */
    public void setCenturiesWanted(boolean centuriesWanted) {
        this.centuriesWanted = centuriesWanted;
    }    
    
}
