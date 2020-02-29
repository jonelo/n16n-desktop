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
/**
 * Uses a SwingWorker to perform a time-consuming task.
 */
package net.numericalchameleon.gui.dialogs.exchangerates;

import net.numericalchameleon.util.exchangerates.ExchangeRatesFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JTextArea;
import jonelo.sugar.gui.SwingWorker;
import jonelo.sugar.util.Counter;
import net.numericalchameleon.util.exchangerates.CurrencyConverter;
import net.numericalchameleon.util.exchangerates.ExchangeRates;

public class ExchangeRatesUpdateTask {

    /**
     * @return the exchangeRatesFilter
     */
    public ExchangeRatesFilter getExchangeRatesFilter() {
        return exchangeRatesFilter;
    }

    /**
     * @param exchangeRatesFilter the exchangeRatesFilter to set
     */
    public void setExchangeRatesFilter(ExchangeRatesFilter exchangeRatesFilter) {
        this.exchangeRatesFilter = exchangeRatesFilter;
    }

    private int lengthOfTask;
    private String statMessage;
    private ExchangeRatesFilter exchangeRatesFilter;
    private String includeFilename;
    private Counter counter = null;
    private JTextArea textArea = null;
    private JButton button = null;
    private ExchangeRates exchangeRates;
    private ResourceBundle iso3166;

    ExchangeRatesUpdateTask(ExchangeRates exchangeRates) {
        //Compute length of task...
        this.exchangeRates = exchangeRates;
        lengthOfTask = 0;
        try {
            FileInputStream fin = new FileInputStream("../data/lists/iso4217.list");
            BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
            String thisLine;
            while ((thisLine = myInput.readLine()) != null) {
                if (!thisLine.startsWith("#") && !thisLine.equals("")) {
                    lengthOfTask++;
                }
            }
        } catch (IOException e) {
        }
        counter = new Counter();
    }

    void setIncludeFilename(String filename) {
        this.includeFilename = filename;
    }

    void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    void setButton(JButton button) {
        this.button = button;
    }
    
    void setISO3166ResourceBundle(ResourceBundle iso3166) {
        this.iso3166 = iso3166;
    }

    /**
     * Called to start the task.
     */
    void go() {
        counter.set(0);
        final SwingWorker worker = new SwingWorker() {

            @Override
            public Object construct() {
                return new ActualTask();
            }
        };
        worker.start();
    }

    /**
     * Called from ProgressBarDemo to find out how much work needs to be done.
     */
    int getLengthOfTask() {
        return lengthOfTask;
    }

    /**
     * Called from ProgressBarDemo to find out how much has been done.
     */
    int getCurrent() {
        return counter.get();
    }

    void stop() {
        counter.set(lengthOfTask);
    }

    /**
     * Called from ProgressBarDemo to find out if the task has completed.
     */
    boolean done() {
        return counter.get() >= lengthOfTask;
    }

    String getMessage() {
        return statMessage;
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class ActualTask {

        ActualTask() {
            try {
                CurrencyConverter cc = new CurrencyConverter(exchangeRatesFilter, includeFilename, counter, textArea, exchangeRates, iso3166);
                if (exchangeRates.success()) {
                    button.setEnabled(true);
                }
            } catch (Exception e) {
                System.err.println(e.toString());
                System.err.println("ERROR: exchange rate list corrupted.");
                textArea.append(e.toString());
            }
        }
    }
}
