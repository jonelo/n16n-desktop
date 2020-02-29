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

public class Summary {
    private int unitsComplete;
    private int unitsFiltered;
    private int unitsSelectable;
    private int categoriesComplete;
    private int categoriesFiltered;

    /** Creates a new instance of Summary */
    public Summary() {
    }

    public int getUnitsComplete() {
        return unitsComplete;
    }

    public void setUnitsComplete(int unitsComplete) {
        this.unitsComplete = unitsComplete;
    }

    public int getUnitsFiltered() {
        return unitsFiltered;
    }

    public void setUnitsFiltered(int unitsFiltered) {
        this.unitsFiltered = unitsFiltered;
    }

    public int getUnitsSelectable() {
        return unitsSelectable;
    }

    public void setUnitsSelectable(int unitsSelectable) {
        this.unitsSelectable = unitsSelectable;
    }

    public int getCategoriesComplete() {
        return categoriesComplete;
    }

    public void setCategoriesComplete(int categoriesComplete) {
        this.categoriesComplete = categoriesComplete;
    }

    public int getCategoriesFiltered() {
        return categoriesFiltered;
    }

    public void setCategoriesFiltered(int categoriesFiltered) {
        this.categoriesFiltered = categoriesFiltered;
    }
    
    public void addUnitsComplete(int i) {
        unitsComplete+=i;
    }
    
    public void addUnitsFiltered(int i) {
        unitsFiltered+=i;
    }
    
    public void addUnitsSelectable(int i) {
        unitsSelectable+=i;
    }
    
    public void addCategoriesComplete(int i) {
        categoriesComplete+=i;
    }
    
    public void addCategoriesFiltered(int i) {
        categoriesFiltered+=i;
    }
    
    
}
