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
package net.numericalchameleon.util.exchangerates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;

import jonelo.sugar.io.GeneralIO;

public class ExchangeRatesFilters {

    ArrayList<ExchangeRatesFilter> filters = null;
    HashMap<String, ExchangeRatesFilter> hashmap = null;

    public ExchangeRatesFilters() {
        filters = new ArrayList<>();
        hashmap = new HashMap<>();
    }

    public void load(Properties propertiesForAllUsers, String msgifEmpty) {
        load(propertiesForAllUsers, msgifEmpty, false);
    }

    public void load(Properties propertiesForAllUsers, String msgifEmpty, boolean directDownloadFiltersOnly) {
        try {
            String myHome = "../data/rates/";
            String suffix = ".filter";

            // find all files with the suffix
            ArrayList<String> filterNames = GeneralIO.ls2(myHome, suffix, false);

            for (String filterName : filterNames) {
                ExchangeRatesFilter filter = new ExchangeRatesFilter(filterName);
                if (filter.isEnabled()
                        && (!directDownloadFiltersOnly
                        || (directDownloadFiltersOnly && filter.isDirectDownloadAllowed()))) {

                    filter.setFilename(filterName);
                    if (propertiesForAllUsers != null) {
                        filter.setSupportedRates(
                                propertiesForAllUsers.getProperty("rates_filter." + filterName + ".supportedRates", "unknown"));
                    }
                    filters.add(filter);
                    hashmap.put(filterName, filter);
                }
            }
            if (filters.isEmpty()) {
                throw new Exception(msgifEmpty);
            }

            // Sorting
            Collections.sort(filters,
                    (ExchangeRatesFilter filter1, ExchangeRatesFilter filter2)
                    -> filter2.getContinent().concat(filter2.getCountry()).compareTo(
                            filter1.getContinent().concat(filter1.getCountry()))
            );

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

    }

    public int size() {
        return filters.size();
    }

    public ArrayList<ExchangeRatesFilter> getFilters() {
        return filters;
    }

    public ExchangeRatesFilter getPreferredFilterByName(String name) {
        return hashmap.get(name);
    }
}
