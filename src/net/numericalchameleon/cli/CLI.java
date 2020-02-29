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

import net.numericalchameleon.gui.dialogs.exchangerates.ExchangeRatesDialog;
import net.numericalchameleon.util.exchangerates.CurrencyConverter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The Command Line Interface
 */
public class CLI implements RatesOnTheCLIInterface, RatesUpdatePolicyInterface {

    private final CLIInterface cliInterface;
    private final String[] args;

    private boolean continueWanted = true;
    private boolean verboseWanted = false;
    private boolean versionWanted = false;
    private boolean logfileWanted = false;
    private boolean oneJarWanted = false;
    private boolean resetWanted = false;
    private boolean helpWanted = false;
    private boolean filterListWanted = false;
    private String filtername = null;
    private String ratesFilename = null;
    private String ratesUpdatePolicyName = null;

    private Options options;
    private boolean success;

    public CLI(CLIInterface cliInterface, String[] args) {

        // String[] myargs = {"--rates", "x:\\ecb.xml", "--filter", "ecb.europa.eu", "--verbose"};
        // args = myargs;
        this.cliInterface = cliInterface;
        this.args = args;

        // if there are no args, we don't need to parse args and we can continue with starting the GUI
        // as specified by the continueWanted default value of "true"
        if (args.length == 0) {
            return;
        }
        // since we land here, program args have been specified, the command line mode is active
        // and therefore we don't want to continue with the GUI by default
        continueWanted = false;

        parseArgs();

    }

    private void parseArgs() {
        // create the Options
        options = new Options();
        options.addOption("h", "help", false, "prints this help");
        options.addOption(null, "verbose", false, "output in verbose mode");
        options.addOption("v", "version", false, "version output");
        options.addOption("l", "logfile", false, "output to a logfile");
        options.addOption("r", "reset", false, "resets user config");
        options.addOption("j", "onejar", false, "one jar (experimental)");
        options.addOption("c", "continue", false, "continues with running the GUI");
        options.addOption(Option.builder("f")
                .hasArg()
                .longOpt("filter")
                .argName("name")
                .desc("filter in order to update exchange rates, if name is set to \"list\", a list of supported filters is being returned.")
                .build());

        options.addOption(Option.builder()
                .hasArg()
                .longOpt("rates")
                .argName("file")
                .desc("file that contains exchange rates")
                .build());

        options.addOption(Option.builder("p")
                .hasArg()
                .longOpt("policy")
                .argName("policy")
                .desc("persist the policy for updating exchange rates")
                .build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline;
        try {
            // parse the command line arguments
            cmdline = parser.parse(options, getArgs());
            continueWanted = cmdline.hasOption("continue");
            verboseWanted = cmdline.hasOption("verbose");
            logfileWanted = cmdline.hasOption("logfile");
            oneJarWanted = cmdline.hasOption("jar");
            resetWanted = cmdline.hasOption("reset");
            helpWanted = cmdline.hasOption("help");
            versionWanted = cmdline.hasOption("version");

            if (cmdline.hasOption("policy")) {
                this.ratesUpdatePolicyName = cmdline.getOptionValue("policy");
                
                if (!cmdline.hasOption("filter")) {
                    throw new ParseException("option policy requires option filter.");
                }
            }

            if (cmdline.hasOption("filter")) {
                this.filtername = cmdline.getOptionValue("filter");

                if (this.filtername.equals("list")) {
                    this.filterListWanted = true;
                } else {
                    if (cmdline.hasOption("rates")) {
                        this.ratesFilename = cmdline.getOptionValue("rates");
                    }
                }
            }
        } catch (ParseException exp) {
            // oops, something went wrong
            continueWanted = false;
            System.err.println("Parsing of parameters failed. Reason: " + exp.getMessage());
        }

    }

    public void performAction() {
        if (getArgs().length == 0) {
            return;
        }

        if (versionWanted) {
            CLIVersion.setVerbose(isVerboseWanted());
            CLIVersion.printVersion();
        }

        if (verboseWanted) {
            CurrencyConverter.setVerbose(true);
            ExchangeRatesDialog.setVerbose(true);
        }

        if (helpWanted) {
            CLIHelp.setVerbose(isVerboseWanted());
            CLIHelp.printHelp(options);
        }

        if (resetWanted) {
            CLIResetter.removeUserProps();
        }

        if (ratesUpdatePolicyName != null && filtername != null) {
            RatesUpdatePolicy ratesUpdatePolicy = new RatesUpdatePolicy(this);
            ratesUpdatePolicy.persist();
        }

        if (ratesUpdatePolicyName == null && filtername != null) {
            RatesOnTheCLI ratesOnTheCLI
                    = new RatesOnTheCLI(this);
            success = ratesOnTheCLI.isSuccess();
        }

    }

    /**
     *
     * @return whether updating exchange rates was successful
     */
    public boolean wasUpdatingRatesSuccessful() {
        return success;
    }

    /**
     * @return the verboseWanted
     */
    @Override
    public boolean isVerboseWanted() {
        return verboseWanted;
    }

    /**
     * @return the logfileWanted
     */
    @Override
    public boolean isLogfileWanted() {
        return logfileWanted;
    }

    /**
     * @return the continueWanted
     */
    @Override
    public boolean isContinueWanted() {
        return continueWanted;
    }

    /**
     * @return whether one jar is wanted
     */
    public boolean isOneJarWanted() {
        return oneJarWanted;
    }

    /**
     * @return the filtername
     */
    @Override
    public String getFiltername() {
        return filtername;
    }

    @Override
    public CLIInterface getCLIInterface() {
        return cliInterface;
    }

    /**
     *
     * @return whether a filter listing is wanted
     */
    @Override
    public boolean isFilterListWanted() {
        return filterListWanted;
    }

    /**
     *
     * @return the name of the file that contains exchange rates
     */
    @Override
    public String getRatesFilename() {
        return ratesFilename;
    }

    public String[] getArgs() {
        return args;
    }

    /**
     * @return the ratesUpdatePolicy
     */
    @Override
    public String getRatesUpdatePolicyName() {
        return ratesUpdatePolicyName;
    }
}
