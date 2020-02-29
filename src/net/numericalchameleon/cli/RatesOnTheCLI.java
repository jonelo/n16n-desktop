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
package net.numericalchameleon.cli;

import net.numericalchameleon.util.net.DownloadTaskInterface;
import net.numericalchameleon.util.net.DownloadTask;
import jonelo.sugar.util.Counter;
import net.numericalchameleon.util.exchangerates.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.numericalchameleon.util.io.IOHelper;

public class RatesOnTheCLI implements DownloadTaskInterface, PropertyChangeListener {

    private final RatesOnTheCLIInterface ratesOnTheCLIInterface;
    private ExchangeRates exchangeRates;
    private ExchangeRatesFilter filter;
    private String inputFile;
    private boolean success = false;

    public RatesOnTheCLI(RatesOnTheCLIInterface ratesOnTheCLIInterface) {
        this.ratesOnTheCLIInterface = ratesOnTheCLIInterface;
        doAction();
    }

    private void printFilterList() {

        ExchangeRatesFilters filters = new ExchangeRatesFilters();
        filters.load(ratesOnTheCLIInterface.getCLIInterface().getGlobalProperties(), "No filter", true);

        for (ExchangeRatesFilter filter : filters.getFilters()) {
            System.out.println(filter.getFilename());
            if (ratesOnTheCLIInterface.isVerboseWanted()) {
                System.out.println("    Provider name:            " + filter.getProvider());
                System.out.println("    Supported exchange rates: " + filter.getSupportedRates());
            }
        }
    }

    @Override
    public void downloadProcessStarted() {
        System.out.println("Download started.");
    }

    @Override
    public void downloadProcessStopped() {
        System.out.println("Download stopped.");
    }

    @Override
    public void downloadWasSuccessful() {
        System.out.println("100 %");
        System.out.println("Download successful.");
        parseAndUpdate();
    }

    @Override
    public void downloadWasNotSuccessful() {
        System.out.println("Download was not successful.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            if (progress > 0 && progress < 100) {
                System.out.println(progress + " %");
            }
        }
    }

    private void doAction() {
        if (ratesOnTheCLIInterface.isFilterListWanted()) {
            printFilterList();
        } else {
            updateRates(ratesOnTheCLIInterface.getRatesFilename());
        }
    }

    private void updateRates(String inputFile) {
        this.inputFile = inputFile;
        if (ratesOnTheCLIInterface.isVerboseWanted()) {
            System.out.println("Updating exchange rates on the command line ...");
        }
        try {

            exchangeRates = new ExchangeRates();
            filter = new ExchangeRatesFilter(ratesOnTheCLIInterface.getFiltername());

            if (inputFile == null && !filter.isDirectDownloadAllowed()) {
                System.err.println("You have to use --rates in order to use this filter.");
                return;
            }

            if (inputFile == null) {

                File localFile;
                try {
                    localFile = IOHelper.createTempFile("rates", "html");
                    this.inputFile = localFile.toString();
                    System.out.println("Using temp file called " + this.inputFile);
                    DownloadTask downloadTask = new DownloadTask(this, filter.getWebsites(), localFile, filter.getUseragent());
                    downloadTask.addPropertyChangeListener(this);
                    //downloadTask.execute();
                    downloadTask.doInBackground();
                    downloadTask.done();

                } catch (IOException e) {
                    System.err.println("Download error.");
                }

            } else {
                parseAndUpdate();
            }
        } catch (IOException e) {
            System.err.println(e);
            // e.printStackTrace();
        }
    }

    public boolean isSuccess() {
        return success;
    }

    private void parseAndUpdate() {
        try {
            CurrencyConverter cc = new CurrencyConverter(filter,
                    this.inputFile, new Counter(), null, exchangeRates, ratesOnTheCLIInterface.getCLIInterface().getISO3166ResourceBundle());

            if (exchangeRates.success()) {
                try {
                    exchangeRates.writeToDisk();

                    // update all exchange rates related properties
                    ratesOnTheCLIInterface.getCLIInterface().updateGlobalProperties(exchangeRates.getGlobalProperties());
                    ratesOnTheCLIInterface.getCLIInterface().updateUpdateProperties(exchangeRates.getUpdateProperties());

                    System.out.println(exchangeRates.count() + " exchange rates updated.");
                    success = true;
                } catch (Exception e) {
                    System.err.println(e);
                    e.printStackTrace();
                }
            }
            if (ratesOnTheCLIInterface.isVerboseWanted()) {
                System.out.println("update finished.");
            }
        } catch (Exception ex) {
            Logger.getLogger(CLI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
