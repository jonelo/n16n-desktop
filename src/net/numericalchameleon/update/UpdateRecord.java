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
package net.numericalchameleon.update;

import net.numericalchameleon.update.modules.ExchangeRatesUpdateModule;
import net.numericalchameleon.update.modules.L10nUpdateModule;
import net.numericalchameleon.update.modules.AppUpdateModule;
import net.numericalchameleon.update.modules.TzdataUpdateModule;

public class UpdateRecord {

    private TzdataUpdateModule tzdataModule;
    private AppUpdateModule ncVersionModule;
    private ExchangeRatesUpdateModule exchangeRatesVersionModule;
    private L10nUpdateModule l10nVersionModule;

    public L10nUpdateModule getL10nVersionModule() {
        return l10nVersionModule;
    }

    public void setL10nVersionModule(L10nUpdateModule l10nVersionModule) {
        this.l10nVersionModule = l10nVersionModule;
    }

    /**
     * Creates a new instance of UpdateRecord
     */
    public UpdateRecord() {
        tzdataModule = new TzdataUpdateModule();
        ncVersionModule = new AppUpdateModule();
        exchangeRatesVersionModule = new ExchangeRatesUpdateModule();
        l10nVersionModule = new L10nUpdateModule();
    }

    public TzdataUpdateModule getTzdataModule() {
        return tzdataModule;
    }

    public void setTzdataModule(TzdataUpdateModule tzdataModule) {
        this.tzdataModule = tzdataModule;
    }

    public AppUpdateModule getNcVersionModule() {
        return ncVersionModule;
    }

    public void setNcVersionModule(AppUpdateModule ncVersionModule) {
        this.ncVersionModule = ncVersionModule;
    }

    public ExchangeRatesUpdateModule getExchangeRatesVersionModule() {
        return exchangeRatesVersionModule;
    }

    public void setExchangeRatesVersionModule(ExchangeRatesUpdateModule exchangeRatesVersionModule) {
        this.exchangeRatesVersionModule = exchangeRatesVersionModule;
    }
}
