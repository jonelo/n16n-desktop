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

import java.util.ArrayList;
import java.util.Collections;
import net.numericalchameleon.util.exchangerates.ExchangeRatesFilter;

public class SortByLocation extends SortItem implements SortAction {
    
    private final ArrayList<ExchangeRatesFilter> filters;
    
    public SortByLocation(ArrayList<ExchangeRatesFilter> filters, String id, String iconCode, String i18nID) {        
        super(id, iconCode, i18nID);
        this.filters=filters;
    }
    
    @Override
    public void sort() {
            // Sorting
            Collections.sort(filters,
                    (ExchangeRatesFilter filter2, ExchangeRatesFilter filter1)
                    -> filter1.getContinent().concat(filter1.getCountry()).compareTo(
                            filter2.getContinent().concat(filter2.getCountry()))
            );
    }
}
