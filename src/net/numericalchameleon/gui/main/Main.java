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
package net.numericalchameleon.gui.main;

import net.numericalchameleon.update.UpdateManagerProgramVersion;
import net.numericalchameleon.data.properties.PropertiesManager;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.undo.UndoManager;
import jonelo.sugar.gui.AboutDialog;
import jonelo.sugar.gui.GUIHelper;
import jonelo.sugar.gui.GeneralGUI;
import jonelo.sugar.io.ExampleFileFilter;
import jonelo.sugar.io.GeneralIO;
import jonelo.sugar.datetime.ExtendedGregorianCalendar;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.categories.*;
import net.numericalchameleon.cli.CLI;
import net.numericalchameleon.data.*;
import net.numericalchameleon.info.ProgConstants;
import net.numericalchameleon.gui.lnf.LookAndFeel;
import net.numericalchameleon.gui.lnf.LookAndFeelProperties;
import net.numericalchameleon.gui.dialogs.bookmarks.BookmarksDialog;
import net.numericalchameleon.gui.dialogs.bookmarks.BookmarksModel;
import net.numericalchameleon.gui.dialogs.calendarchooser.CalendarChooserDialog;
import net.numericalchameleon.gui.dialogs.calendarchooser.CalendarChooserPayload;
import net.numericalchameleon.gui.dialogs.config.ConfigDialog;
import net.numericalchameleon.gui.dialogs.datediff.DateDiffDialog;
import net.numericalchameleon.gui.dialogs.editunits.EditUnits;
import net.numericalchameleon.gui.dialogs.exchangerates.ExchangeRatesDialog;
import net.numericalchameleon.gui.dialogs.find.FindDialog;
import net.numericalchameleon.gui.dialogs.listgenerator.ListGeneratorCluster;
import net.numericalchameleon.gui.dialogs.listgenerator.ListGeneratorDialog;
import net.numericalchameleon.gui.dialogs.phoneticalphabet.PhoneticAlphabetDialog;
import net.numericalchameleon.gui.dialogs.ramdomgenerator.RandomGeneratorDialog;
import net.numericalchameleon.gui.dialogs.summary.SummaryDialog;
import net.numericalchameleon.gui.dialogs.timechooser.TimeChooserDialog;
import net.numericalchameleon.gui.dialogs.timechooser.TimeChooserPayload;
import net.numericalchameleon.gui.dialogs.updateapplication.AppUpdateDialog;
import net.numericalchameleon.gui.dialogs.updatecenter.UpdateCenterDialog;
import net.numericalchameleon.gui.main.renderer.CategoryCellRenderer;
import net.numericalchameleon.gui.main.renderer.GroupCellRenderer;
import net.numericalchameleon.gui.common.renderer.UnitRecordCellRenderer;
import net.numericalchameleon.info.ProgInfo;
import net.numericalchameleon.update.UpdateRecord;
import net.numericalchameleon.util.exchangerates.ExchangeRates;
import net.numericalchameleon.util.misc.Gimmicks;
import net.numericalchameleon.util.io.IOHelper;
import net.numericalchameleon.util.misc.ModelHelperNC;
import net.numericalchameleon.util.misc.ProcessHelper;
import net.numericalchameleon.util.misc.RandomGenerator;
import net.numericalchameleon.util.misc.Support;
import net.numericalchameleon.util.misc.SupportNC;
import net.numericalchameleon.util.spokennumbers.SpokenNumber;
import net.numericalchameleon.gui.help.HelpHelper;
import net.numericalchameleon.util.misc.Constants;
import net.numericalchameleon.util.misc.PropertyTools;

public class Main extends javax.swing.JFrame implements MainInterface {

    // useful for a restart, so we can make sure that the old process
    // died completely
    static {
        String waitValue = System.getProperty("waitBeforeStart", "0");
        if (!waitValue.equals("0")) {
            try {
                Thread.sleep(Integer.parseInt(waitValue));
            } catch (InterruptedException | NumberFormatException e) {
                System.err.println(e);
            }
        }
    }

    // private members
    private int latestCount;
    private UnitRecordCellRenderer unitRecordCellRenderer;
    private CategoryCellRenderer categoryCellRenderer;
    private GroupCellRenderer groupCellRenderer;
    private CategoryObject categoryObject;
    private String transferValue;
    private boolean precisionerror;
    private ResourceBundle rb;
    private ResourceBundle iso3166;
    private final PropertiesManager propertiesManager;
    private String exchange_rates_description = null;
    private int view;
    private ListGeneratorCluster multipleNoticeCluster = null;
    private String notice_form = DEFAULT_NOTICE_FORM;
    private JFileChooser fc_save, fc_load;
    private ExampleFileFilter utf16be, utf16le, utf16, utf8, html, iso8859, vcs, ics, xcs;
    private ArrayList<String> groupCurrentSelection;
    private ArrayList<String> groupAllCategories;
    private int lastTypeBoxSelectedIndex = -1;
    private BookmarksModel bookmarks = null;
    private BookmarksDialog bookmarksDialog = null;
    private long rates_updated_ms = 0;
    private UpdateRecord updateRecord = null;
    private boolean resizeComboBoxPopups = true;
    private UndoManager historyUndoManager;
    private UndoManager sourceUndoManager;
    private HashSet<String> groupHashSet;
    private HashMap<String, Category> categoryHashMap;
    private ComponentOrientation appComponentOrientation;

    // public final static members (shared constants)
    public final static Color GREEN = new Color(0xBBFFBB);
    public final static Color RED = new Color(0xFF9B9C);

    // private final static members (local constants)
    private final static int NOCOUNT = -1;
    private final static BigDecimal MINUS_ONE = BigDecimal.valueOf(-1);
    private final static String DEFAULT_NOTICE_FORM = "$SVALUE [$SUNIT] = $TVALUE [$TUNIT]";
    private final static String ERROR_GUI_PRECISION = "Error.GUI.Precision";
    private final static String MESSAGE_GUI_PROMPT = "Message.GUI.Prompt";
    private final static String HTML_HEADER
            = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\n"
            + " \"http://www.w3.org/TR/html4/strict.dtd\">\n"
            + "<html>\n<head>\n  <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n  <title>Generated by "
            + ProgInfo.getInstance().getProgramName() + " "
            + ProgInfo.getInstance().getVersion() + "</title>\n  <meta name=\"author\" content=\""
            + ProgInfo.getInstance().getProgramName() + " "
            + ProgInfo.getInstance().getVersion() + "\">\n</head>\n<body>\n\n<table BORDER CELLSPACING=0 CELLPADDING=3>\n";
    private final static String HTML_FOOTER
            = "</table>\n\n</body>\n</html>";

    private final CLI cli;
    private boolean isNCAdmin;
    private HashMap<String, Class> classMap;
    private HashMap<String, CategoryObject> cacheMap;
    private HelpHelper helpHelper;

    // PUBLIC METHODS
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * The main routine in order to start the GUI.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Main main = new Main(args);
    }

    // Constructor
    public Main(String[] args) {
        propertiesManager = new PropertiesManager();
        cli = new CLI(this, args);
        cli.performAction();
        if (cli.isContinueWanted()) {
            preStart();
            init();
        }
    }

    @Override
    public Frame getFrame() {
        return this;
    }

    @Override
    public ComponentOrientation getAppComponentOrientation() {
        return appComponentOrientation;
    }

    // visit the bookmark, take units into account or not (only category of the bookmark)
    @Override
    public void visitBookmark(Bookmark bookmark, boolean takeUnitsIntoAccount) throws Exception {
        // is code not in the whiteList ?
        if (!groupHashSet.contains(bookmark.getCategory())) {
            // reset the filter        
            clickOnFilterCategoryIndicator();
        }

        // search for the category
        int found = findCategoryWithCode(bookmark.getCategory());
        if (found == -1) {
            // not found!
            JOptionPane.showMessageDialog(this,
                    rb.getString("Message.CategoryNotFound"),
                    rb.getString("Message.CategoryNotFound"),
                    JOptionPane.ERROR_MESSAGE);
        } else {
            categoryBox.setSelectedIndex(found);

            if (takeUnitsIntoAccount) {
                int foundS = findUnitExact(sourceBox, bookmark.getSourceDescription(), 0, true); // country berücksichtigen!!!
                int foundT = findUnitExact(targetBox, bookmark.getTargetDescription(), 0, true);

                if (foundS > -1 && foundT > -1) {
                    sourceBox.setSelectedIndex(foundS);
                    targetBox.setSelectedIndex(foundT);
                    sourceTextArea.setText(bookmark.getValue());
                    process(NOCOUNT);
                } else {
                    throw new Exception("Lesezeichen veraltet");
                }
            }
        }
    }

    // visit the bookmark, take both into account: category and units
    @Override
    public void visitBookmark(Bookmark bookmark) throws Exception {
        visitBookmark(bookmark, true);
    }

    @Override
    public void updateGUI() {
        SwingUtilities.updateComponentTreeUI(getRootPane());
        if (view > -1) {
            this.pack();
        }
    }

    @Override
    public boolean isOneJarWanted() {
        return cli.isOneJarWanted();
    }

    @Override
    public ResourceBundle getResourceBundle() {
        return rb;
    }

    @Override
    public ResourceBundle getISO3166ResourceBundle() {
        if (iso3166 == null) {
            iso3166 = ResourceBundle.getBundle("data.lang.iso3166");
        }
        return iso3166;
    }

    @Override
    public void appUpdateDialog() {
        System.out.println("calling appUpdateDialog");
        AppUpdateDialog appUpdateDialog = new AppUpdateDialog(this);
    }

    @Override
    public UpdateRecord getUpdateRecord() {
        if (updateRecord == null) {
            updateRecord = new UpdateRecord();
        }
        return updateRecord;
    }

    @Override
    public void releaseDateDiffDialog(boolean[] status, long[] seconds) {
        if (status[0] == true) { // not cancelled

            if (categoryBox.getSelectedItem() != categoryHashMap.get("zeit")) {
                categoryBox.setSelectedItem(categoryHashMap.get("zeit"));
                categoryBoxItemStateChanged();
            } else {
                int found = findUnitExact(sourceBox, "second (s)", 0, true);
                if (found > -1) {
                    sourceBox.setSelectedIndex(found);
                }
            }

            try {
                setValue(new BigDecimal(String.valueOf(seconds[0]))); // 1.4 compatible constructor
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void releaseBookmarksDialog() {
        bookmarksDialog = null;
    }

    @Override
    public BookmarksModel getBookmarks() {
        return bookmarks;
    }

    @Override
    public HashMap<String, Category> getCategoryHashMap() {
        return categoryHashMap;
    }

    @Override
    public int findEntry(Class myCast, JComboBox box, String find, int begin, boolean matchCase) {
        return findEntry(myCast, box, find, begin, matchCase, false);
    }

    @Override
    public JComboBox getComboBox(int type) {
        switch (type) {
            case FindDialog.CATEGORY:
                return categoryBox;
            case FindDialog.SOURCE:
                return sourceBox;
            case FindDialog.TARGET:
                return targetBox;
        }
        return null;
    }

    @Override
    public void updateExchangeRates() {
        updateExchangeRatesMenuItemActionPerformed(null);
    }

    @Override
    public void dontPanic() {
        JOptionPane.showMessageDialog(this,
                rb.getString("Message.GUI.DontPanic"),
                rb.getString("GUI.Menu.Main.Restart"),
                JOptionPane.INFORMATION_MESSAGE,
                new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix48x48/dontpanic.png")));
    }

    @Override
    public void gotoWebpage(String path) {
        helpHelper.gotoWebpage(path);
    }

    @Override
    public void saveWork() {
        setBoundsForUserProps();
        if (propertiesManager.getUserProperties().getProperty("backupCatAndUnit", "false").equals("true")) {
            Bookmark b = getCurrentSelectionAsBookmark();
            propertiesManager.getUserProperties().setProperty("lastCatAndUnit", b.getBookmarkAsString());
        }
        // in that order, because saveNotizblock sets a property (the caret of the area)
        saveNotizblock();
        propertiesManager.saveUserProperties();
    }

    @Override
    public void prepareHardExit() {
        if (propertiesManager.getUserProperties().getProperty("exitAnimation", "false").equals("true")) {
            Gimmicks.doExitAnimation(this);
        }
        setVisible(false);
    }

    @Override
    public void hardExit() {
        // for compatibility with the Java Shell
        this.dispose();
        // Terminate the current process
        System.exit(0);
    }

    @Override
    public boolean isResizeComboBoxPopups() {
        return resizeComboBoxPopups;
    }

    // PRIVATE METHODS
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean isVerbose() {
        return cli.isVerboseWanted();
    }

    // update exchange rates at first if that is wanted by the user
    private void preStart() {
        PropertiesManager.setVerbose(cli.isVerboseWanted());
        // if the wish to update the exchange rates has been specified on the command line already,
        // we can ignore the wish in the system settings
        if (cli.getFiltername() == null) {
            Properties props = propertiesManager.getGlobalProperties();
            if (props.getProperty("rates_update.policy", "none").equalsIgnoreCase("OnAppStartUp")) {
                try {
                    long ms = Long.parseLong(props.getProperty("rates_update.policy.OnAppStartUp.doNotUpdateIfLastUpdateIsYoungerThanMs", "21600000")); // default is 6h
                    long now = System.currentTimeMillis();
                    long last = Long.parseLong(props.getProperty("rates_update.policy.OnAppStartUp.lastSuccessfulUpdate", "0"));

                    if (now - last > ms) {
                        String filtername = props.getProperty("rates_update.policy.OnAppStartUp.filterID", "list");
                        String[] startupargs = {"--filter", filtername, "-c"};
                        CLI startupCLI = new CLI(this, startupargs);
                        startupCLI.performAction();
                        if (startupCLI.wasUpdatingRatesSuccessful()) {
                            props.setProperty("rates_update.policy.OnAppStartUp.lastSuccessfulUpdate", Long.toString(System.currentTimeMillis()));
                            propertiesManager.saveGlobalProperties();
                        }
                    } else {
                        System.out.println("Info: ignoring updating exchange rates on app startup, because last successful update was only " + (now - last) + " ms ago.");
                    }
                } catch (NumberFormatException npe) {
                    System.err.println(npe);
                }
            }
        }
    }

    private void printRuntimeProperties() {
        for (String entry : getRuntimePropertiesAsList()) {
            System.out.println(entry);
        }
    }

    /**
     * Teens both standard output and standard error to nc<version>_out.log and
     * nc_<version>_err.log
     */
    private void teeBothStdoutStderr() {
        try {
            String filePrefix = "nc" + ProgInfo.getInstance().getVersion();
            IOHelper.teeBothStdoutStderr(filePrefix + "_out.log", filePrefix + "_err.log");
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }



    private void init() {
        if (cli.isLogfileWanted()) {
            // Tee both standard output and standard error channel
            teeBothStdoutStderr();
        }

        // Print out support information (at start and restart)
        if (isVerbose()) {
            printRuntimeProperties();
        }

        initClassMap();
        initCacheMap();

        propertiesManager.init();

        resizeComboBoxPopups = determineResizeComboBoxPopups();

        lastTypeBoxSelectedIndex = -1;

        // User properties have been loaded
        // Do some actions dependent on the users' properties.
        LookAndFeelProperties lnfProps = new LookAndFeelProperties();
        lnfProps.setPreferredLnF(propertiesManager.getUserProperties().getProperty("lnf", "nimbus"));
        lnfProps.setPreferredTheme(propertiesManager.getUserProperties().getProperty("theme", "Default.theme"));
        if (lnfProps.getPreferredLnF().equals("jtattoo")) {
            lnfProps.setPreferredTheme(propertiesManager.getUserProperties().getProperty("jtattoo_theme", "Default"));
        }
        lnfProps.setJavaFrameDecorationWanted(propertiesManager.getUserProperties().getProperty("decoration", "native").equals("java"));
        lnfProps.setBoldFont(propertiesManager.getUserProperties().getProperty("boldFont", "false").equals("true"));
        lnfProps.setAudioFeedback(propertiesManager.getUserProperties().getProperty("audioNotification", "false").equals("true"));

        // has a tooltip user property been set?
        // tooltips
        if (propertiesManager.getUserProperties().getProperty("tooltips", "").length() > 0) {
            // disable tooltips only if property set to "false"
            setToolTips(!propertiesManager.getUserProperties().getProperty("tooltips", "false").equals("false"));
        }

        loadResourceBundle();
        iso3166 = getISO3166ResourceBundle();
        CategoryObject.setDecSep(((java.text.DecimalFormat) (java.text.NumberFormat.getInstance())).getDecimalFormatSymbols().getDecimalSeparator());

        appComponentOrientation = ComponentOrientation.getOrientation(Locale.getDefault());
        LookAndFeel.setLookAndFeelBasedOnPreferences(lnfProps);
        if (lnfProps.isJavaFrameDecorationWanted() && lnfProps.isJavaFrameDecorationSupported()) {
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }
        updateGUI();

        initComponents();
        tzDateButton.setVisible(false);
        tzTimeButton.setVisible(false);

        isNCAdmin = propertiesManager.isGlobalPropertiesWritable();
        if (!isNCAdmin) {
            editUnitsMenuItem.setEnabled(false);
            updateCenterMenuItem.setEnabled(false);
            currencyUpdateButton.setEnabled(false);
            updateExchangeRatesMenuItem.setEnabled(false);
        }

        setRenderers();

        this.historyUndoManager = new UndoManager();
        net.numericalchameleon.util.misc.GUIHelper.setUndoRedoFor(historyTextArea, historyUndoManager);

        this.sourceUndoManager = new UndoManager();
        net.numericalchameleon.util.misc.GUIHelper.setUndoRedoFor(sourceTextArea, sourceUndoManager);

        multipleNoticeCluster = new ListGeneratorCluster();

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.out.println();
                saveExit();
            }
        });

        if (cli.isOneJarWanted()) {
            // let's do some moifications in case of Java Web Start
            // disable "update exchange rates"
            updateExchangeRatesMenuItem.setEnabled(false);
        }

        // Internationalization
        Font font = historyTextArea.getFont();
        try {
            historyTextArea.setFont(font.deriveFont(Float.valueOf((String) propertiesManager.getUserProperties().get("fontsize"))));
        } catch (NumberFormatException e) {
            System.err.println(e.toString());
        }

        frameButtonGroup.add(luxuryRadioButtonMenuItem);
        frameButtonGroup.add(standardRadioButtonMenuItem);
        frameButtonGroup.add(economicalRadioButtonMenuItem);
        frameButtonGroup.add(miserlyRadioButtonMenuItem);
        frameButtonGroup.add(maximumRadioButtonMenuItem);
        view = 0;
        try {
            view = Integer.parseInt((String) propertiesManager.getUserProperties().get("view"));
            if ((view < -1) || (view > 3)) {
                view = 0;
            }
        } catch (NumberFormatException e) {
            System.err.println(e.toString());
        }

        switch (view) {
            case -1:
                maximumRadioButtonMenuItem.setSelected(true);
                break;
            case 0:
                luxuryRadioButtonMenuItem.setSelected(true);
                break;
            case 1:
                standardRadioButtonMenuItem.setSelected(true);
                break;
            case 2:
                economicalRadioButtonMenuItem.setSelected(true);
                break;
            case 3:
                miserlyRadioButtonMenuItem.setSelected(true);
                break;
        }

        groupHashSet = new HashSet<>();
        groupCurrentSelection = new ArrayList();
        initGroupsList();

        registerCategories();

        luxuryFrame(view);
        try {
            setLocation(Integer.parseInt(propertiesManager.getUserProperties().getProperty("locationX")),
                    Integer.parseInt(propertiesManager.getUserProperties().getProperty("locationY")));

            setSize(Integer.parseInt(propertiesManager.getUserProperties().getProperty("width")),
                    Integer.parseInt(propertiesManager.getUserProperties().getProperty("heigth")));

        } catch (NumberFormatException e) {
            GeneralGUI.centerComponent(this);
        }

        if (!appComponentOrientation.isLeftToRight()) {
            GeneralGUI.applyOrientation(ncMenuBar, appComponentOrientation);
            GeneralGUI.applyOrientation(converterPanel, appComponentOrientation);
            GeneralGUI.applyOrientation(sourcePopupMenu, appComponentOrientation);
            GeneralGUI.applyOrientation(targetPopupMenu, appComponentOrientation);
            GeneralGUI.applyOrientation(historyPopupMenu, appComponentOrientation);
        }

        if (propertiesManager.getUserProperties().getProperty("restoreCatAndUnit", "false").equals("true")
                && propertiesManager.getUserProperties().getProperty("lastCatAndUnit", null) != null) {
            try {
                visitBookmark(new Bookmark(propertiesManager.getUserProperties().getProperty("lastCatAndUnit")));
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            categoryBoxItemStateChanged();
        }
        // live resizing
        Toolkit.getDefaultToolkit().setDynamicLayout(true);

        // make sure all components have the expected size
        SwingUtilities.updateComponentTreeUI(getRootPane());

        setAlwaysOnTop(propertiesManager.getUserProperties().getProperty("alwaysOnTop", "false").equals("true"));
        // fix: resize required on init
        if (resizeComboBoxPopups) {
            GeneralGUI.resizeComboBoxPopup(sourceBox);
            GeneralGUI.resizeComboBoxPopup(targetBox);
        }
        restoreNotizblock();
        helpHelper = new HelpHelper(this);
        // aus den globalProeprties: nur 1x am Tag nach neuer Version überprüfen
        // (wenn überhaupt gewünscht) checkProgramVersionUpdateStatusOnStartUp=true
        // 

        UpdateManagerProgramVersion updateManagerProgramVersion = new UpdateManagerProgramVersion(propertiesManager.getUserProperties());
        updateManagerProgramVersion.checkForNewProgramVersion();
        // The splash screen should be kept open when the version is being queried from the network
        // So we perform the update dialog only if we have queried the network AND the GUI has been made visible
        // (and the splash screen is gone)
        setVisible(true);
        
        if (updateManagerProgramVersion.isCurrentVersionOutdatedForSure()) {
            appUpdateDialog();
        }

        requestFocus();
        toFront();
    }
    
    private void setToolTips(boolean status) {
        ToolTipManager.sharedInstance().setEnabled(status);
    }

    // no resizing of comboboxes on Aqua LnF
    private boolean determineResizeComboBoxPopups() {
        String os = System.getProperty("os.name").toLowerCase(Locale.US);
        return !(os.equalsIgnoreCase(Constants.MAC_OS_X)
                && (propertiesManager.getUserProperties().getProperty("lnf", "").equals("aqua")
                || propertiesManager.getUserProperties().getProperty("lnf", "").equals("system")));
    }

    private void showAboutDialog() {

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        AboutDialog dialog = new AboutDialog(this, false);
        dialog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/images/splash.png")));
        if (isUndecorated()) {
            dialog.setUndecorated(true);
            dialog.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        }
        try {
            dialog.setLicense(readFromJar("/net/numericalchameleon/resources/docs/license.html"));
        } catch (IOException e) {
            dialog.setLicense(GeneralString.message(rb.getString("Error.FileDoesNotExist"), "license.html"));
        }

        try {
            dialog.setCopyright(readFromJar("/net/numericalchameleon/resources/docs/copyright.html"));
        } catch (IOException e) {
            dialog.setCopyright(GeneralString.message(rb.getString("Error.FileDoesNotExist"), "copyright.html"));
        }

        try {
            dialog.setThanks(readFromJar("/net/numericalchameleon/resources/docs/thanks.html"));
        } catch (IOException e) {
            dialog.setThanks(GeneralString.message(rb.getString("Error.FileDoesNotExist"), "thanks.html"));
        }

        StringBuilder sb = new StringBuilder();
        for (String entry : getRuntimePropertiesAsList()) {
            sb.append(entry);
            sb.append("\n");
        }
        dialog.setSysProps(sb.toString());

        dialog.setTitle(rb.getString("GUI.Menu.Help.About"));
        dialog.setAboutTab(rb.getString("GUI.Menu.Help.About"));
        dialog.setLicenseTab(rb.getString("GUI.General.License"));
        dialog.setCopyrightTab("Copyright");
        dialog.setThanksTab(rb.getString("GUI.General.Thanks"));
        dialog.setSysPropsTab(rb.getString("GUI.General.SysProps"));
        dialog.create();
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        dialog = null;
        sourceTextArea.requestFocus();
    }

    private void loadResourceBundle() {
        if (isVerbose()) {
            System.out.print("Loading resource bundle ... ");
        }
        try {
            rb = ResourceBundle.getBundle("data.lang.resource");

            // let's modify the OptionPane, because it's localized by the JRE
            // for the Top 10 languages only
            UIManager.put("OptionPane.yesButtonText", rb.getString("GUI.General.Yes"));
            UIManager.put("OptionPane.noButtonText", rb.getString("GUI.General.No"));
            UIManager.put("OptionPane.cancelButtonText", rb.getString("GUI.General.Cancel"));
            UIManager.put("OptionPane.okButtonText", rb.getString("GUI.General.OK"));

            // TODO => move those to the actual classes and not here
            AboutDialog.OK = rb.getString("GUI.General.OK");
            AboutDialog.THANKS = rb.getString("GUI.General.Thanks");
            AboutDialog.LICENSE = rb.getString("GUI.General.License");
            AboutDialog.ABOUT = rb.getString("GUI.Menu.Help.About");

            ExtendedGregorianCalendar.ERROR_1583 = rb.getString("Message.GUI.Year1583");

            SpokenNumber.ERROR_TOOBIG = rb.getString("SpokenNumber.toobig");
            SpokenNumber.ERROR_TOOSMALL = rb.getString("SpokenNumber.toosmall");

            CategoryHolidays.INVALID = rb.getString("Moduls.sourceInvalid");
            CategoryHolidays.YES = rb.getString("GUI.General.Yes");
            CategoryHolidays.NO = rb.getString("GUI.General.No");
            CategoryHolidays.YEAR = rb.getString("Calendar.year");

            if (isVerbose()) {
                System.out.println("OK.");
            }
        } catch (MissingResourceException ex) {
            error(ex.getMessage() + "\nExit.");
            System.exit(1);
        }
    }

    /**
     * prints an error at the command line and at the GUI
     */
    private void error(String error) {
        System.err.println(error);
        JOptionPane.showMessageDialog(this, error, rb.getString("Error.errorname"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * set Renderers to all important GUI components
     */
    private void setRenderers() {
        categoryCellRenderer = new CategoryCellRenderer();
        GeneralGUI.applyOrientation(categoryCellRenderer, appComponentOrientation);
        categoryBox.setRenderer(categoryCellRenderer);

        unitRecordCellRenderer = new UnitRecordCellRenderer(getISO3166ResourceBundle());
        //unitRecordCellRenderer.setISO3166ResourceBundle(getISO3166ResourceBundle());
        GeneralGUI.applyOrientation(unitRecordCellRenderer, appComponentOrientation);
        sourceBox.setRenderer(unitRecordCellRenderer);
        targetBox.setRenderer(unitRecordCellRenderer);

        groupCellRenderer = new GroupCellRenderer(this);
        GeneralGUI.applyOrientation(groupCellRenderer, appComponentOrientation);
        groupComboBox.setRenderer(groupCellRenderer);
    }

    private void registerCategories() {
        if (isVerbose()) {
            System.out.print("Loading categories.list ... ");
        }
        try {
            readCategoriesFromJar();
            if (isVerbose()) {
                System.out.println("OK.");
            }
        } catch (Exception e) {
            error("data/lists/categories.list not found. Exit.");
            System.exit(1);
        }

        fillCategoryModel(groupAllCategories);

        transferValue = "";
//        categoryBoxItemStateChanged();
    }

    private void fillCategoryModel(ArrayList list) {

        DefaultComboBoxModel catModel = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            catModel.addElement(categoryHashMap.get(list.get(i)));
        }
        categoryBox.setModel(catModel);
        if (!groupHashSet.isEmpty()) {
            // disable menu items if categories are not available (due to filtering)
            sourceMenuItemColor.setEnabled(groupHashSet.contains("COLOR"));
            colorToolbarButton.setEnabled(groupHashSet.contains("COLOR"));

            sourceMenuItemCalendar.setEnabled(groupHashSet.contains("CALENDAR"));
            calendarButton.setEnabled(groupHashSet.contains("CALENDAR"));

            dateDiffMenuItem.setEnabled(groupHashSet.contains("zeit"));
            datetimeDiffButton.setEnabled(groupHashSet.contains("zeit"));
        } else {
            sourceMenuItemColor.setEnabled(true);
            colorToolbarButton.setEnabled(true);

            sourceMenuItemCalendar.setEnabled(true);
            calendarButton.setEnabled(true);

            dateDiffMenuItem.setEnabled(true);
            datetimeDiffButton.setEnabled(true);
        }
    }

    private void initGroupsList() {
        if (isVerbose()) {
            System.out.print("Loading group files ... ");
        }
        Vector groupsVector = null;
        try {
            String myHome = "../data/groups/";
            if (cli.isOneJarWanted()) {
                groupsVector = GeneralIO.readFromJarInVector(Main.class, "/data/groups/onejar.list");
            } else {
                groupsVector = GeneralIO.ls(myHome, ".group", false);
            }

            if (groupsVector == null) {
                throw new Exception("No group files found in " + myHome);
            }
            groupsVector.insertElementAt("all", 0);

            // search for .group files in the home directory
            myHome = System.getProperty("user.home")
                    + File.separator + ProgConstants.DIR_USER_PROPERTIES + File.separator;
            Vector v2 = GeneralIO.ls(myHome, ".group", false);
            if (v2 != null) {
                groupsVector.addAll(v2);
            }
            groupsVector.add("random");

            Vector<Group> v3 = new Vector<>();
            for (Object name : groupsVector) {
                v3.add(new Group((String) name, (String) name, (String) name));
            }

            // group files
            groupComboBox.setModel(GeneralGUI.getComboBoxModel(v3));
            if (groupsVector.size() < 20) {
                groupComboBox.setMaximumRowCount(groupsVector.size());
            } else {
                groupComboBox.setMaximumRowCount(20);
            }

            if (isVerbose()) {
                System.out.println("OK.");
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private String readFromJar(String filename) throws IOException {
        InputStream is = getClass().getResourceAsStream(filename);
        StringBuilder sb;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String thisLine;
            sb = new StringBuilder();
            while ((thisLine = br.readLine()) != null) {
                sb.append(thisLine);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * categoryHashMap will be filled with all Categories (key=category-code,
     * value=Category) groupAllCategories Vector contains all category-codes
     */
    private void readCategoriesFromJar() throws Exception {

        categoryHashMap = new HashMap<>();
        groupAllCategories = new ArrayList<>();

        // read the category file
        Properties cat = new Properties();
        Properties catDefault = new Properties();

        try {
            InputStream is = getClass().getResourceAsStream(
                    "/data/lang/categories.properties");
            catDefault.load(is);
            is.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            String lang = propertiesManager.getUserProperties().getProperty("language", "en");
            InputStream is = getClass().getResourceAsStream("/data/lang/categories_"
                    + lang + "_" + propertiesManager.getUserProperties().getProperty("country", "") + ".properties");

            if (is == null) {
                is = getClass().getResourceAsStream("/data/lang/categories_" + lang + ".properties");
                if (is == null) {
                    is = getClass().getResourceAsStream("/data/lang/categories.properties");
                }
            }
            cat.load(is);
            is.close();
            if (isVerbose()) {
                System.out.println(" OK.");
            }
        } catch (IOException e) {
            System.err.println(e);
        }

        InputStream is = getClass().getResourceAsStream("/data/lists/categories.list");
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringTokenizer st = null;
        String description = null;

        String thisLine;
        String code;

        while ((thisLine = br.readLine()) != null) {
            if (!thisLine.startsWith("#") && !thisLine.equals("")) {

                st = new StringTokenizer(thisLine, ":");
                code = st.nextToken();
                // is code in the white list? (groupHashSet)
                if (groupHashSet.isEmpty() || groupHashSet.contains(code)) {

                    Category category = new Category();
                    category.setName(code);
                    category.setIcon(ProgConstants.ICONS_CATEGORIES + code.toLowerCase(Locale.US) + ".png");

                    String helplang = preferredHelpLanguage();
                    String filename = System.getProperty("user.dir")
                            + "/../help/" + helplang + "/categories/" + code.toLowerCase(Locale.US) + ".html";
                    category.setHasHelp(new File(filename).exists());

                    // description
                    // Category names without a translation should be read from the default list and if
                    // not in the default list display them as "NOT TRANSLATED".
                    description = cat.getProperty("Category." + code, PropertiesManager.getPropertyDefault(catDefault, "Category." + code, "NOT TRANSLATED"));

                    // modify description if exchange rates
                    if (code.equals("exchange_rates")) {
                        exchange_rates_description = description;
                        description = GeneralString.replaceString(description, "$RATES_DATE", propertiesManager.getGlobalProperties().getProperty("rates_date", "n/a"));
                        description = GeneralString.replaceString(description, "$RATES_NAME", propertiesManager.getGlobalProperties().getProperty("rates_name", "n/a"));
                    }

                    category.setString(description);

                    // figures
                    boolean cont = false;
                    String figures = "-1";
                    if (st.hasMoreTokens()) {
                        figures = st.nextToken();
                        cont = true;
                    } else {
                        figures = "12";
                    }
                    if (figures.equals("-")) {
                        figures = "-1";
                    }
                    int fig = -1;
                    try {
                        fig = Integer.parseInt(figures);
                    } catch (NumberFormatException e) {
                    }
                    category.setPreferredPrecision(fig);

                    // Logic
                    boolean b = true;
                    if (cont && st.hasMoreTokens()) {
                        try {
                            String token = st.nextToken();
                            if (code.equals("exchange_rates")) {
                                token = GeneralString.replaceString(token, "$RATES_LOGIC", propertiesManager.getGlobalProperties().getProperty("rates_logic", "false"));
                            }
                            b = (token.equals("true") || token.equals("1"));

                        } catch (Exception e) {
                        }
                    }
                    category.setLogic(b);
                    groupAllCategories.add(code);
                    categoryHashMap.put(code, category);
                }
            }
        }
        br.close();
    }

    /**
     * categoryBox - the item state changed
     */
    private void categoryBoxItemStateChanged() {

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Category category = (Category) categoryBox.getSelectedItem();
        if (category == null) {
            category = (Category) categoryBox.getItemAt(0);
        }
        categoryHelpButton.setEnabled(category.hasHelp());
        String code = category.getName();
        if (!category.hasHelp()) {
            System.out.println("Help for " + code + " is missing");
        }

        categoryObject = constructCategoryObjectFromCategory(category);

        tzDateButton.setVisible(false);
        tzTimeButton.setVisible(false);

        if (code.equals("SPOKENNUMBERS")) { // CategorySpokenNumbers
            targetMenuItemPlay.setEnabled(true);
            targetMenuItemStopPlaying.setEnabled(true);
            targetPopupMenuItemPlay.setEnabled(true);
            targetPopupMenuItemStopPlaying.setEnabled(true);
        } else if (code.equals("CALENDARYEAR")) { // CategoryHolidays
            dateFormatComboBox.setSelectedIndex(3);
            categoryObject.setNumberType(dateFormatComboBox.getSelectedIndex());
        } else if (code.equals("TIMEZONES")) { // CategoryTimezones
            warnLabel.setVisible(getUpdateRecord().getTzdataModule().isItOutdated());
            tzDateButton.setVisible(true);
            tzTimeButton.setVisible(true);
        } else if (code.equals("CALENDARFORMATS")) { // CategoryDateFormats
            dateFormatComboBox.setSelectedIndex(1);
            categoryObject.setNumberType(dateFormatComboBox.getSelectedIndex());
        } else if (code.equalsIgnoreCase("exchange_rates")) {
            warnLabel.setVisible(isExchangeRatesOutdated());
        }

        boolean plusMinusSupported = categoryObject.isPlusMinusSupported();
        sourceMinusButton.setEnabled(plusMinusSupported);
        sourcePlusButton.setEnabled(plusMinusSupported);
        sourceMenuItemPlus.setEnabled(plusMinusSupported);
        sourceMenuItemMinus.setEnabled(plusMinusSupported);
        sourcePopupMenuItemPlus.setEnabled(plusMinusSupported);
        sourcePopupMenuItemMinus.setEnabled(plusMinusSupported);
        makeListButton.setEnabled(plusMinusSupported);
        recordMenuItemMakeList.setEnabled(plusMinusSupported);
        historyPopupMenuItemMakeList.setEnabled(plusMinusSupported);

        cardLayout.show(cardPanel, categoryObject.getCard());

        sourceBox.setModel(ModelHelperNC.getComboBoxModel(categoryObject.getSourceUnits()));
        sourceBox.setSelectedIndex(categoryObject.getSourceDefault());
        targetBox.setModel(ModelHelperNC.getComboBoxModel(categoryObject.getTargetUnits()));
        targetBox.setSelectedIndex(categoryObject.getTargetDefault());

        if (resizeComboBoxPopups) {
            GeneralGUI.resizeComboBoxPopup(sourceBox);
            GeneralGUI.resizeComboBoxPopup(targetBox);
        }

        if (!code.equals("SPOKENNUMBERS")) { // CategorySpokenNumbers
            targetMenuItemPlay.setEnabled(false);
            targetMenuItemStopPlaying.setEnabled(false);
            targetPopupMenuItemPlay.setEnabled(false);
            targetPopupMenuItemStopPlaying.setEnabled(false);
        }

        if (!code.equals("TIMEZONES") && (!code.equalsIgnoreCase("exchange_rates"))) {
            warnLabel.setVisible(false);
        }

        boolean enable = !categoryObject.isOneway();
        swapButton.setEnabled(enable);
        programMenuItemSwap.setEnabled(enable);
        enableNavigationDependentOnEntryCount();

        // mit Wert vom letzten Cluster füllen (transferValue)
        String initValue = categoryObject.getInitialValue();
        sourceTextArea.setText(initValue == null ? transferValue : initValue);

        precisionerror = false;

        if (categoryObject.getPreferredPrecision() == -1) {
            precisionTextField.setText("0");
            precisionTextField.setEditable(false);
            precisionTextField.setEnabled(false);
            precisionLabel.setEnabled(false);
            targetMenuItemFigures.setEnabled(false);
            targetMenuItemDecimals.setEnabled(false);
        } else {
            int preferredPrecision = categoryObject.getPreferredPrecision();
            precisionTextField.setText(Integer.toString(preferredPrecision));
            categoryObject.setPrecision(preferredPrecision);
            precisionTextField.setEditable(true);
            precisionTextField.setEnabled(true);
            precisionLabel.setEnabled(true);
            targetMenuItemFigures.setEnabled(true);
            targetMenuItemDecimals.setEnabled(true);
        }
        sciCheckBox.setEnabled(categoryObject.isScientificSupported());
        categoryObject.setScientific(sciCheckBox.isSelected());

        latestCount = Math.max(sourceBox.getItemCount(), targetBox.getItemCount());

        editUnitsMenuItem.setEnabled((categoryObject instanceof CategoryGeneric) && isNCAdmin);

        process(latestCount);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        sourceTextArea.requestFocus();
    }

    private int findEntry(Class myCast, JComboBox box, String find, int begin, boolean matchCase, boolean exact) {
        if ((find != null) && (!find.equals(""))) { // user didn't cancel
            // search in the combobox text
            for (int i = begin; i < box.getItemCount(); i++) {
                String name = null;
                if (myCast == Unit.class) {
                    name = ((Unit) box.getItemAt(i)).getString();
                } else {
                    name = ((Category) box.getItemAt(i)).getString();
                }
                if (matchCase) {
                    if (exact) {
                        if (name.equals(find)) {
                            return i;
                        }
                    } else {
                        if (name.contains(find)) {
                            return i;
                        }
                    }
                } else {
                    if (exact) {
                        if (name.toLowerCase().equals(find.toLowerCase())) {
                            return i;
                        }
                    } else {
                        if (name.toLowerCase().contains(find.toLowerCase())) {
                            return i;
                        }
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Enables or disables graphical Navigation Components the state is
     * dependend on the item count in the source and target combo boxes
     */
    private void enableNavigationDependentOnEntryCount() {
        // enable or disable items
        boolean moreThanOneEntry = sourceBox.getItemCount() > 1;

        findSourceButton.setEnabled(moreThanOneEntry);
        sourceMenuItemFindUnit.setEnabled(moreThanOneEntry);
        sourcePopupMenuItemFindUnit.setEnabled(moreThanOneEntry);

        sourceMenuItemDefault.setEnabled(moreThanOneEntry);
        sourcePopupMenuItemDefault.setEnabled(moreThanOneEntry);

        sourceBackButton.setEnabled(moreThanOneEntry);
        sourceNextButton.setEnabled(moreThanOneEntry);
        searchSourceMenuItem.setEnabled(moreThanOneEntry);

        moreThanOneEntry = targetBox.getItemCount() > 1;

        findTargetButton.setEnabled(moreThanOneEntry);
        targetMenuItemFindUnit.setEnabled(moreThanOneEntry);
        targetPopupMenuItemFindUnit.setEnabled(moreThanOneEntry);

        targetMenuItemDefault.setEnabled(moreThanOneEntry);
        targetPopupMenuItemDefault.setEnabled(moreThanOneEntry);

        targetBackButton.setEnabled(moreThanOneEntry);
        targetNextButton.setEnabled(moreThanOneEntry);
        searchTargetMenuItem.setEnabled(moreThanOneEntry);
    }

    // perform a conversion with info about currently supported units
    //  int support  if support is NOCOUNT,
    //  then no information about the number of units is printed out
    private void process(int support) {

        // no unneccessary process if precission is wrong
        if (precisionerror) {
            messageError(rb.getString(ERROR_GUI_PRECISION));
        } else {

            if (!categoryObject.acceptEmptyStrings() && sourceTextArea.getText().length() == 0) {
                targetTextArea.setText("");
                if (support == NOCOUNT) {
                    messageOk(rb.getString(MESSAGE_GUI_PROMPT), false);
                } else {
                    messageOk(GeneralString.message(rb.getString("Message.GUI.Support"), support) + " " + rb.getString(MESSAGE_GUI_PROMPT), false);
                }
            } else {
                categoryObject.setInput(sourceTextArea.getText());
                try {
                    // eigentliche Konvertierung
                    //targetTextArea.setText(categoryObject.getOutput(sourceBox.getSelectedIndex(), targetBox.getSelectedIndex()));
                    targetTextArea.setText(categoryObject.getOutput((Unit) sourceBox.getSelectedItem(), (Unit) targetBox.getSelectedItem()));

                    // update only for timezones
                    if (categoryObject instanceof CategoryTimezones) {
                        deltaLabel.setText(categoryObject.getInfoConversion());
                    }

                    if (support == NOCOUNT) {
                        messageOk("ok.", true);
                    } else {
                        messageOk(GeneralString.message(rb.getString("Message.GUI.Support"), support), true);
                    }
                } catch (Exception e) {
                    messageError(e.getMessage()); // alert
                }
            }
        }
    }

    /**
     * method to add a value (usually -1 or +1)
     */
    private void addValue(BigDecimal plus) throws Exception {
        categoryObject.setInput(sourceTextArea.getText());
        String temp = categoryObject.addValue(plus, sourceBox.getSelectedIndex());
        sourceTextArea.setText(temp);
        process(NOCOUNT);
    }

    /*
     * method to set a value for source (is used for the feature "generate a
     * list")
     */
    private void setValue(BigDecimal big) throws Exception {
        String temp = categoryObject.setValue(big, sourceBox.getSelectedIndex());
        sourceTextArea.setText(temp);
        process(NOCOUNT);
    }

    private void messageLightError(String s) {
        messageTextField.setText(s);
        messageTextField.setBackground(RED);
        messageTextField.setForeground(Color.BLACK);
    }

    private void messageError(String s) {
        messageLightError(s);
        targetTextArea.setText("");
        editorNoticeButton.setEnabled(false);
        recordMenuItemNew.setEnabled(false);
        historyPopupMenuItemNew.setEnabled(false);
        //        sourceMinusButton.setEnabled(false);
        //        sourcePlusButton.setEnabled(false);
    }

    private void messageOk(String s) {
        messageTextField.setText(s);
        messageTextField.setBackground(GREEN);
        messageTextField.setForeground(Color.black);
    }

    private void messageOk(String s, boolean b) {
        messageOk(s);
        editorNoticeButton.setEnabled(b);
        recordMenuItemNew.setEnabled(b);
        historyPopupMenuItemNew.setEnabled(b);
        //        sourceMinusButton.setEnabled(b);
        //        sourcePlusButton.setEnabled(b);
    }

    private void checkPopupMenu(MouseEvent event, JPopupMenu popupMenu) {
        if (event.isPopupTrigger()) {
            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }

    private void textAreaClear(JTextArea textArea) {
        int start = textArea.getSelectionStart();
        int end = textArea.getSelectionEnd();
        if (start == end) {
            textArea.setText("");
            categoryObject.setInput("");
        } else {
            StringBuilder sb = new StringBuilder(textArea.getText());
            sb = sb.delete(start, end);
            textArea.setText(sb.toString());
            textArea.setCaretPosition(start);
            if (textArea.getText().equals("")) {
                categoryObject.setInput("");
            }
        }
        process(NOCOUNT);
        textArea.requestFocus();
    }

    private void sourceTextAreaInsert(char c) {
        StringBuilder sb = new StringBuilder(sourceTextArea.getText());
        int i = sourceTextArea.getCaretPosition();
        sb.insert(i, c);
        sourceTextArea.setText(sb.toString());
        sourceTextArea.setCaretPosition(i + 1);
        process(NOCOUNT);
        sourceTextArea.requestFocus();
    }

    private void textAreaCopy(JTextArea textArea) {
        String s = textArea.getSelectedText();
        if (s == null) {
            textArea.selectAll();
            s = textArea.getText();
        }
        GeneralIO.setClipboard(s);
        textArea.requestFocus();
    }

    private void sourceTextAreaPaste() {
        try {
            int start = sourceTextArea.getSelectionStart();
            int end = sourceTextArea.getSelectionEnd();
            StringBuilder sb = new StringBuilder(sourceTextArea.getText());
            if (start == end) { // no selection
                start = sourceTextArea.getCaretPosition();
            } else {
                sb = sb.delete(start, end);
            }
            String s = GeneralIO.getClipboard().replace('\n', ' ').replace('\r', ' ').trim();
            sb = sb.insert(start, s);
            sourceTextArea.setText(sb.toString());
            sourceTextArea.setCaretPosition(start + s.length());
            process(NOCOUNT);
            sourceTextArea.requestFocusInWindow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Clipboard Info", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void historyTextAreaPaste() {
        try {
            int start = historyTextArea.getSelectionStart();
            int end = historyTextArea.getSelectionEnd();
            StringBuilder sb = new StringBuilder(historyTextArea.getText());
            if (start == end) { // no selection
                start = historyTextArea.getCaretPosition();
            } else {
                sb = sb.delete(start, end);
            }
            String s = GeneralIO.getClipboard();
            sb = sb.insert(start, GeneralIO.getClipboard());
            historyTextArea.setText(sb.toString());
            historyTextArea.setCaretPosition(start + s.length());
            historyTextArea.requestFocus();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Clipboard Info", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void luxuryFrame(int i) {
        view = i;
        navigationPanel.setVisible(i < 2);
        recordPanel.setVisible(i < 1);
        editTogglePanel.setVisible(i < 1);
        sourceButtonPanel.setVisible(i < 2);
        targetButtonPanel.setVisible(i < 2);
        headerPanel.setVisible(i < 3);
        propertiesManager.getUserProperties().setProperty("view", Integer.toString(i));
        propertiesManager.saveUserProperties();

        if (i > -1) {
            pack();
            GeneralGUI.centerComponent(this);
        } else {
            GeneralGUI.maximizeComponent(this);
        }
    }

    private int whatDateFormat() {
        int index = dateFormatComboBox.getSelectedIndex();
        if (index == 0) {
            return DateFormat.SHORT;
        }
        if (index == 1) {
            return DateFormat.MEDIUM;
        }
        if (index == 2) {
            return DateFormat.LONG;
        }
        if (index == 3) {
            return DateFormat.FULL;
        }
        return DateFormat.MEDIUM;
    }

    private void performNotice() {
        if (multipleNoticeCluster.getListFormat() != null) {
            notice_form = multipleNoticeCluster.getListFormat().getFormat();
            try {
                String result = multipleNoticeCluster.getListFormat().reformatDate(whatDateFormat(), targetTextArea.getText());
                notice_form = GeneralString.replaceString(notice_form, "$TVALUE", result);
                result = multipleNoticeCluster.getListFormat().reformatUnit(((Unit) targetBox.getSelectedItem()).getString());
                notice_form = GeneralString.replaceString(notice_form, "$TUNIT", result);
                notice_form = GeneralString.replaceString(notice_form, "$TFLAG", ((Unit) targetBox.getSelectedItem()).getIcon());
                historyTextArea.insert(notice_form + "\n", historyTextArea.getCaretPosition());

            } catch (java.text.ParseException pe) {
                System.err.println(pe);
                // don't add it
            }
        } else {
            notice_form = propertiesManager.getUserProperties().getProperty("notice_form", DEFAULT_NOTICE_FORM);
            notice_form = GeneralString.replaceString(notice_form, "$SVALUE", sourceTextArea.getText());
            notice_form = GeneralString.replaceString(notice_form, "$SUNIT", ((Unit) sourceBox.getSelectedItem()).getString());

            notice_form = GeneralString.replaceString(notice_form, "$TVALUE", targetTextArea.getText());
            notice_form = GeneralString.replaceString(notice_form, "$TUNIT", ((Unit) targetBox.getSelectedItem()).getString());

            notice_form = GeneralString.replaceString(notice_form, "$SFLAG", ((Unit) sourceBox.getSelectedItem()).getIcon());
            notice_form = GeneralString.replaceString(notice_form, "$TFLAG", ((Unit) targetBox.getSelectedItem()).getIcon());
            historyTextArea.insert(notice_form + "\n", historyTextArea.getCaretPosition());
        }
    }

    // brings the Update Center dialog on the screen
    private void openUpdateCenter() {
        UpdateCenterDialog updateCenterDialog = new UpdateCenterDialog(this, this);
    }

    private boolean isExchangeRatesOutdated() {
        String updated = propertiesManager.getUpdateProperties().getProperty("rates_updated");
        if (updated == null) {
            return false;
        }
        rates_updated_ms = Long.parseLong(updated);
        return (System.currentTimeMillis() - rates_updated_ms >= 86400000); // 1 day
    }

    private void manageFavorites() {
        if (bookmarks == null) {
            bookmarks = BookmarksModel.loadBookmarks();
        }
        if (bookmarksDialog == null) {
            bookmarksDialog = new BookmarksDialog(this);
        }
    }

    private Bookmark getCurrentSelectionAsBookmark() {
        Bookmark bookmark = new Bookmark(
                (Category) categoryBox.getSelectedItem(),
                (Unit) sourceBox.getSelectedItem(),
                (Unit) targetBox.getSelectedItem());
        // for a specific locale!
        bookmark.setLanguage(propertiesManager.getUserProperties().getProperty("language"));
        bookmark.setCountry(propertiesManager.getUserProperties().getProperty("country"));
        bookmark.setValue(sourceTextArea.getText());
        return bookmark;
    }

    // add bookmark to bookmarks
    private void addFavorite() {
        Bookmark bookmark = getCurrentSelectionAsBookmark();

        if (bookmarks == null) {
            bookmarks = BookmarksModel.loadBookmarks();
        }
        // Append an item
        bookmarks.addElement(bookmark);

        // update the bookmark dialog
        if (bookmarksDialog != null) {
            bookmarksDialog.updateCountLabel();
            bookmarksDialog.updateList();
        }
    }

    private String websiteLanguage() {
        return preferredHelpLanguage().equals("de") ? "de" : "en";
    }

    private void defaultValuePressed() {
        sourceBox.setSelectedIndex(categoryObject.getSourceDefault());
        String initValue = categoryObject.getInitialValue();
        sourceTextArea.setText(initValue == null ? "1" : initValue);
        process(NOCOUNT);
    }

    private void dateButtonPressed(String category) {
        GregorianCalendar calendar = new GregorianCalendar();
        CalendarChooserPayload payload = new CalendarChooserPayload(calendar, TimeZone.getDefault());

        CalendarChooserDialog calendarChooserDialog = new CalendarChooserDialog(this, payload);

        if (!payload.isCancelled()) { // not cancelled

            if (categoryBox.getSelectedItem() == categoryHashMap.get("CALENDAR")
                    || categoryBox.getSelectedItem() == categoryHashMap.get("CALENDARS")) {
            } else if (categoryBox.getSelectedItem() != categoryHashMap.get(category)) {
                categoryBox.setSelectedItem(categoryHashMap.get(category));
                categoryBoxItemStateChanged();
            }
            try {
                String value
                        = String.format("%04d%02d%02d",
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1,
                                calendar.get(Calendar.DATE));

                setValue(new BigDecimal(value));
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }

    private void menuItemCalendarPressed() {
        dateButtonPressed("CALENDAR");
    }

    private void clickOnFilterCategoryIndicator() {
        groupHashSet.clear();
        fillCategoryModel(groupAllCategories);
        lastTypeBoxSelectedIndex = -1;
        filterCatIndicatorLabel.setVisible(false);
        groupComboBox.setSelectedIndex(0);

        categoryBoxItemStateChanged();
    }

    private int findCategoryWithCode(String code) {
        for (int i = 0; i < categoryBox.getItemCount(); i++) {
            String name = ((Category) categoryBox.getItemAt(i)).getName();
            if (code.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void findSourceUnit() {
        FindDialog findDialog = new FindDialog(this, FindDialog.SOURCE);
    }

    private int findUnit(JComboBox box, String find, int begin, boolean matchCase) {
        return findEntry(Unit.class, box, find, begin, matchCase, false);
    }

    private int findUnitExact(JComboBox box, String find, int begin, boolean matchCase) {
        return findEntry(Unit.class, box, find, begin, matchCase, true);
    }

    private int findCategory(JComboBox box, String find, int begin, boolean matchCase) {
        return findEntry(Category.class, box, find, begin, matchCase, false);
    }

    private int findCategoryExact(JComboBox box, String find, int begin, boolean matchCase) {
        return findEntry(Category.class, box, find, begin, matchCase, true);
    }

    private void findTargetUnit() {
        FindDialog findDialog = new FindDialog(this, FindDialog.TARGET);
    }

    private void menuItemColorPressed() {
        BigDecimal big = null;
        try {
            big = new BigDecimal(categoryObject.getTransferValue(sourceBox.getSelectedIndex()));
        } catch (Exception e) {
        }
        Color color = Color.black;

        if (big != null) {
            BigInteger bigi = big.toBigInteger();

            if ((bigi.compareTo(new BigInteger("FFFFFF", 16)) <= 0)
                    && (bigi.compareTo(new BigInteger("0")) > 0)) {
                color = new Color(bigi.intValue());
            }
        }
        // JDK 7 workaround: set the default locale right before we show the dialog
        JColorChooser.setDefaultLocale(Locale.getDefault());
        Color newColor = JColorChooser.showDialog(this, rb.getString("GUI.General.ColorPicker"), color);

        if (newColor != null) {
            categoryBox.setSelectedItem(categoryHashMap.get("COLOR"));
            try {
                setValue(new BigDecimal(Integer.toString(newColor.getRGB() & 0x00ffffff)));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private JFileChooser newSingleton_JFileChooserSave() {
        if (fc_save == null) {
            fc_save = new JFileChooser();
            fc_save.removeChoosableFileFilter(fc_save.getAcceptAllFileFilter());
            if (html == null) {
                html = new ExampleFileFilter("html", "HTML, UTF-8");
            }
            if (vcs == null) {
                vcs = new ExampleFileFilter("vcs", "vCalendar");
            }
            if (ics == null) {
                ics = new ExampleFileFilter("ics", "iCalendar");
            }
            if (xcs == null) {
                xcs = new ExampleFileFilter("xcs", "xCal");
            }
            if (utf8 == null) {
                utf8 = new ExampleFileFilter("txt", "Unicode text, UTF-8");
            }
            if (iso8859 == null) {
                iso8859 = new ExampleFileFilter("txt", "Text, ISO8859_1");
            }
            if (utf16le == null) {
                utf16le = new ExampleFileFilter("txt", "Unicode text, UTF-16 little-endian");
            }
            if (utf16be == null) {
                utf16be = new ExampleFileFilter("txt", "Unicode text, UTF-16 big-endian");
            }

            fc_save.addChoosableFileFilter(html);
            fc_save.addChoosableFileFilter(vcs);
            fc_save.addChoosableFileFilter(ics);
            fc_save.addChoosableFileFilter(xcs);
            fc_save.addChoosableFileFilter(utf8);
            fc_save.addChoosableFileFilter(iso8859);
            fc_save.addChoosableFileFilter(utf16le);
            fc_save.addChoosableFileFilter(utf16be);

            fc_save.setFileFilter(utf8);
        }
        return fc_save;
    }

    private String encodingFromFileFilter(ExampleFileFilter ff) {
        String encoding = null;
        if (ff.equals(utf16be)) {
            encoding = "UnicodeBig";
        } else if (ff.equals(utf16le)) {
            encoding = "UnicodeLittle";
        } else if (ff.equals(utf8)) {
            encoding = "UTF-8";
        } else if (ff.equals(iso8859) || ff.equals(vcs)) {
            encoding = "ISO8859_1";
        } else if (ff.equals(html)) {
            encoding = "UTF-8";
        } else if (ff.equals(ics) || ff.equals(xcs)) {
            encoding = "UTF-8";
        }
        return encoding;
    }

    /**
     * return how many lines were processed
     */
    private int processList(ListGeneratorCluster generator) {
        int i = 0;

        /*
        BigDecimal startValue;
        BigDecimal endValue;
        BigDecimal stepValue;

        boolean countdown = generator.getStartValue().compareTo(generator.getEndValue()) > 0;
        stepValue = generator.getStepValue();
        if (countdown) {
            startValue = generator.getEndValue();
            endValue = generator.getStartValue();
            if (stepValue.signum() == 1) { // positive
                stepValue = stepValue.negate();
            }
        } else {
            startValue = generator.getStartValue();
            endValue = generator.getEndValue();
        }
         */
        BigDecimal runValue = generator.getStartValue();

        //end=end.add(generator.getStepValue());
        boolean firstRun = true;
        try {
            if (generator.getStartValue().equals(generator.getEndValue())) {
                setValue(runValue);
                i++;
                performNotice();
            } else { // nur weitermachen, wenn alles ok war bei der letzten Konvertierung

                while (runValue.add(generator.getStepValue()).compareTo(generator.getEndValue()) <= 0) {
                    if (firstRun) {
                        setValue(runValue);
                        firstRun = false;
                    } else {
                        addValue(generator.getStepValue());
                    }

                    if (!editorNoticeButton.isEnabled()) {
                        break;
                    }

                    i++;
                    performNotice();
                    runValue = new BigDecimal(categoryObject.getTransferValue(sourceBox.getSelectedIndex()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            messageError(e.getMessage());
            // In case of an error
            // e.g. RomanNumber 4999+1=5000, 5000+1=5001 => error
            // an invalid number is set internally in categoryObject, probably
            // therefore we set it again
            categoryObject.setInput(sourceTextArea.getText());
        }
        return i;
    }

    private Map getCategoryMapping() {
        Map<String, Class> map = new LinkedHashMap<String, Class>(128);
        // map.put(ADLER32, "Adler-32");
        //map.put(CKSUM_MINIX, "cksum (Minix)");
        return map;
    }

    private CategoryObject newInstanceOf(String name) {
        try {
            return newInstanceOf(Class.forName("net.numericalchameleon.categories." + name));
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        return null;
    }

    private CategoryObject newInstanceOf(Class clazz) {
        try {
            Class[] argTypes = {CategoryInterface.class};
            java.lang.reflect.Constructor constructor = clazz.getDeclaredConstructor(argTypes);
            Object[] arguments = {this};
            return (CategoryObject) constructor.newInstance(arguments);
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    private void initClassMap() {
        classMap = new HashMap<String, Class>();
        classMap.put("ALPHAPHONE", CategoryAlphaPhone.class);
        classMap.put("ANGLE", CategoryAngle.class);
        classMap.put("RADIX35", CategoryBases35.class);
        classMap.put("CALENDARS", CategoryCalendarSystems.class);
        classMap.put("COLOR", CategoryColor.class);
        classMap.put("COORD", CategoryCoordinates.class);
        classMap.put("CALENDARFORMATS", CategoryDateFormats.class);
        classMap.put("CALENDAR", CategoryDateQueries.class);
        classMap.put("DIALCODE", CategoryDialCodes.class);
        classMap.put("FINGERPRINTS", CategoryDigitalFingerprints.class);
        classMap.put("CALENDARYEAR", CategoryHolidays.class);
        classMap.put("FRACTIONS", CategoryRationalNumbers.class);
        classMap.put("ROMAN", CategoryRomanNumerals.class);
        classMap.put("STEIGUNG", CategorySlope.class);
        classMap.put("SPOKENNUMBERS", CategorySpokenNumbers.class);
        classMap.put("SPOKENTIME", CategorySpokenTime.class);
        classMap.put("TEMPERATURE", CategoryTemperatures.class);
        classMap.put("TIMEZONES", CategoryTimezones.class);
        classMap.put("UNICODE", CategoryUnicode.class);
    }

    private void initCacheMap() {
        cacheMap = new HashMap<>();
    }

    private CategoryObject constructCategoryObjectFromCategory(Category category) {
        String code = category.getName();
        CategoryObject co;
        if (classMap.containsKey(code)) {
            // look into the cache first
            if (cacheMap.containsKey(code)) {
                co = cacheMap.get(code);
            } else {
                // instantiate and put it into the cache
                co = newInstanceOf(classMap.get(code));
                cacheMap.put(code, co);
            }
        } else {
            co = new CategoryGeneric(code, category.getLogic(), this);
        }
        co.setPreferredPrecision(category.getPreferredPrecision());
        return co;
    }

    private void playNumber() {
        try {
            categoryObject.play((Unit) targetBox.getSelectedItem());
        } catch (Exception e) {
            messageLightError(rb.getString("Message.GUI.SoundsNotFound"));
        }
    }

    private void setBoundsForUserProps() {
        propertiesManager.getUserProperties().setProperty("width", Integer.toString(getWidth()));
        propertiesManager.getUserProperties().setProperty("heigth", Integer.toString(getHeight()));
        propertiesManager.getUserProperties().setProperty("locationX", Integer.toString(getX()));
        propertiesManager.getUserProperties().setProperty("locationY", Integer.toString(getY()));
    }

    private boolean isPortable() {
        return System.getProperty("user.home").equals(".");
    }

    // restart the application
    private void restart() {
        saveWork();
        dontPanic();
        prepareHardExit();
        try {
            String[] JVMoptions;

            if (System.getProperty("os.name").equalsIgnoreCase(Constants.MAC_OS_X)) {
                JVMoptions = new String[]{
                    "-Dapple.laf.useScreenMenuBar=true",
                    "-Dcom.apple.macos.useScreenMenuBar=true",
                    "-Dcom.apple.macos.use-file-dialog-packages=true",
                    "-Dcom.apple.mrj.application.apple.menu.about.name=NumericalChameleon",
                    "-Xdock:name=NumericalChameleon",
                    "-Xdock:icon=./unix-like/nc.icns",
                    "-DwaitBeforeStart=1000"
                };
            } else {
                JVMoptions = new String[]{"-DwaitBeforeStart=1000"};
            }

            String[] args = null;
            Class clazz = isPortable() ? net.numericalchameleon.launchers.MainGUIPortable.class : net.numericalchameleon.launchers.MainGUI.class;
            ProcessHelper.startJarApplication(JVMoptions, clazz, args);
        } catch (Exception e) {
            System.err.println(e);
        }
        hardExit();
    }

    private void openTimeChooserDialog() {
        TimeChooserPayload payload = new TimeChooserPayload();
        payload.setCalendar(new GregorianCalendar());

        TimeChooserDialog timeChooserDialog = new TimeChooserDialog(this, payload);

        if (!payload.isCancelled()) { // not cancelled
            // make sure we have loaded the correct category module
            if (categoryBox.getSelectedItem() != categoryHashMap.get("SPOKENTIME")) {
                categoryBox.setSelectedItem(categoryHashMap.get("SPOKENTIME"));
                categoryBoxItemStateChanged();
            }
            try {
                int value = payload.getCalcandar().get(Calendar.HOUR_OF_DAY) * 100
                        + payload.getCalcandar().get(Calendar.MINUTE);
                setValue(BigDecimal.valueOf(value));
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }

    private void groupComboBoxItemSelected(Object obj) {
        Group groupRecord = (Group) obj;

        // keyword all
        if (groupRecord.getId().equals("all")) {
            clickOnFilterCategoryIndicator();
            return;
        }

        // keyword random
        if (groupRecord.getId().equals("random")) {
            groupCurrentSelection.clear(); // remain the order in the file
            groupHashSet.clear(); // for an indexed access

            // at least one up to seven random categories
            int count = RandomGenerator.getRandom(1, Math.min(7, groupAllCategories.size()));
            int[] array = RandomGenerator.generate(count, 0, groupAllCategories.size() - 1, false, true);

            for (int i : array) {
                groupHashSet.add(groupAllCategories.get(i));
                groupCurrentSelection.add(groupAllCategories.get(i));
            }
            filterCatIndicatorLabel.setToolTipText(GeneralString.message(rb.getString("GUI.Area.NoFilter"), groupRecord.getDescription()));
            filterCatIndicatorLabel.setVisible(true);
            fillCategoryModel(groupCurrentSelection);
            categoryBoxItemStateChanged();
            return;
        }

        // an underlying group file has been selected
        try {
            InputStream is = getClass().getResourceAsStream("/data/groups/" + groupRecord.getId() + ".group");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String thisLine;

            groupCurrentSelection.clear(); // remain the order in the file
            groupHashSet.clear(); // for an indexed access

            while ((thisLine = br.readLine()) != null) {
                // ignore empty lines and comments
                if (!(thisLine.length() == 0 || thisLine.startsWith("#"))) {
                    groupHashSet.add(thisLine);
                    groupCurrentSelection.add(thisLine);
                }
            }
            br.close();
            is.close();
            filterCatIndicatorLabel.setToolTipText(GeneralString.message(rb.getString("GUI.Area.NoFilter"), groupRecord.getDescription()));
            filterCatIndicatorLabel.setVisible(true);
            fillCategoryModel(groupCurrentSelection);
            categoryBoxItemStateChanged();
        } catch (IOException e) {
            System.err.println(e);
            error("ERROR: group file corrupted.");
        }

    }

    private boolean reallyExit() {
        int result = JOptionPane.YES_OPTION;
        if (propertiesManager.getUserProperties().getProperty("askBeforeExit", "true").equals("true")) {
            result = JOptionPane.showConfirmDialog(this,
                    rb.getString("Message.GUI.ReallyQuit"),
                    rb.getString("GUI.Menu.Main.Exit"),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix48x48/quit.png")));
        }
        return result == JOptionPane.YES_OPTION;
    }

    private void saveExit() {
        if (reallyExit()) {
            saveWork();
            prepareHardExit();
            hardExit();
        }
    }

    private List<String> getRuntimePropertiesAsList() {
        Map<String, String> map = new HashMap<>(128);
        SupportNC.addProgramPropertiesToMap(map);
        Support.addSystemPropertiesToMap(map);
        return Support.getMapAsList(map);
    }

    private static void googleMaps(String latlong) throws Exception {
        latlong = latlong.replace(' ', '+'); // for Google Maps URL Syntax
        String address = "http://maps.google.com/maps?q=" + latlong + "&iwloc=A";
        GUIHelper.openInBrowser(address);
    }

    private void restoreNotizblock() {
        try {
            Path path = Paths.get(System.getProperty("user.home"), ".NumericalChameleon", ".notizblock");
            byte[] bytes = Files.readAllBytes(path);
            historyTextArea.setText(new String(bytes, "UTF-8"));
            try {
                historyTextArea.setCaretPosition(Integer.parseInt(propertiesManager.getUserProperties().getProperty("caretPosArea", "0")));
            } catch (IllegalArgumentException iae) {
                System.err.println(iae);
                historyTextArea.setCaretPosition(0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void saveNotizblock() {
        try {
            Path path = Paths.get(System.getProperty("user.home"), ".NumericalChameleon", ".notizblock");
            byte[] bytes = historyTextArea.getText().getBytes(Charset.forName("UTF-8"));
            Files.write(path, bytes, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SYNC);
            propertiesManager.getUserProperties().setProperty("caretPosArea", Integer.toString(historyTextArea.getText().length()));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    // GUI-GENERATED
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        sourcePopupMenu = new javax.swing.JPopupMenu();
        sourcePopupMenuItemUndo = new javax.swing.JMenuItem();
        sourcePopupMenuItemRedo = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        sourcePopupMenuItemCut = new javax.swing.JMenuItem();
        sourcePopupMenuItemCopy = new javax.swing.JMenuItem();
        sourcePopupMenuItemPaste = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        sourcePopupMenuItemFindUnit = new javax.swing.JMenuItem();
        sourcePopupMenuItemDefault = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        sourcePopupMenuItemPlus = new javax.swing.JMenuItem();
        sourcePopupMenuItemMinus = new javax.swing.JMenuItem();
        sourcePopupMenuDefaultValue = new javax.swing.JMenuItem();
        targetPopupMenu = new javax.swing.JPopupMenu();
        targetPopupMenuItemCopy = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        targetPopupMenuItemFindUnit = new javax.swing.JMenuItem();
        targetPopupMenuItemDefault = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        targetPopupMenuItemPlay = new javax.swing.JMenuItem();
        targetPopupMenuItemStopPlaying = new javax.swing.JMenuItem();
        historyPopupMenu = new javax.swing.JPopupMenu();
        historyMenuItemUndo = new javax.swing.JMenuItem();
        historyMenuItemRedo = new javax.swing.JMenuItem();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        historyMenuItemCut = new javax.swing.JMenuItem();
        historyMenuItemCopy = new javax.swing.JMenuItem();
        historyMenuItemPaste = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        historyPopupMenuItemNew = new javax.swing.JMenuItem();
        historyPopupMenuItemMakeList = new javax.swing.JMenuItem();
        historyPopupMenuenuItemFormat = new javax.swing.JMenuItem();
        historyMenuFontSize = new javax.swing.JMenuItem();
        historyPopupMenuItemHTML = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JPopupMenu.Separator();
        historyMenuItemLoad = new javax.swing.JMenuItem();
        historyMenuItemSave = new javax.swing.JMenuItem();
        frameButtonGroup = new javax.swing.ButtonGroup();
        converterPanel = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        categoryBox = new javax.swing.JComboBox();
        conversionLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        categoryUpButton = new javax.swing.JButton();
        categoryDownButton = new javax.swing.JButton();
        searchCategoryButton = new javax.swing.JButton();
        categoryHelpButton = new javax.swing.JButton();
        groupComboBox = new javax.swing.JComboBox();
        groupLabel = new javax.swing.JLabel();
        sourcePanel = new javax.swing.JPanel();
        sourceHeaderPanel = new javax.swing.JPanel();
        sourceBox = new javax.swing.JComboBox();
        sourceScrollPane = new javax.swing.JScrollPane();
        sourceTextArea = new javax.swing.JTextArea();
        findSourceButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        sourceBackButton = new javax.swing.JButton();
        sourceNextButton = new javax.swing.JButton();
        sourceButtonPanel = new javax.swing.JPanel();
        sourceClearButton = new javax.swing.JButton();
        sourceMinusButton = new javax.swing.JButton();
        sourcePlusButton = new javax.swing.JButton();
        sourceCopyButton = new javax.swing.JButton();
        sourcePasteButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        tzDateButton = new javax.swing.JButton();
        tzTimeButton = new javax.swing.JButton();
        targetPanel = new javax.swing.JPanel();
        targetHeaderPanel = new javax.swing.JPanel();
        targetBox = new javax.swing.JComboBox();
        targetScrollPane = new javax.swing.JScrollPane();
        targetTextArea = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        targetBackButton = new javax.swing.JButton();
        targetNextButton = new javax.swing.JButton();
        findTargetButton = new javax.swing.JButton();
        targetButtonPanel = new javax.swing.JPanel();
        targetCopyButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        sciPanel = new javax.swing.JPanel();
        precisionLabel = new javax.swing.JLabel();
        precisionTextField = new javax.swing.JTextField();
        sciCheckBox = new javax.swing.JCheckBox();
        soundPanel = new javax.swing.JPanel();
        numberTypeComboBox = new javax.swing.JComboBox();
        playButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        timePanel = new javax.swing.JPanel();
        playTimeButton = new javax.swing.JButton();
        stopPlayingTimeButton = new javax.swing.JButton();
        timePanelTimeChooserButton = new javax.swing.JButton();
        calPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        colPanel = new javax.swing.JPanel();
        colorButton = new javax.swing.JButton();
        tzPanel = new javax.swing.JPanel();
        deltaLabel = new javax.swing.JLabel();
        fingerprintPanel = new javax.swing.JPanel();
        fingerprintComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        datePanel = new javax.swing.JPanel();
        dateFormatComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        googleMapsPanel = new javax.swing.JPanel();
        googleMapsButton = new javax.swing.JButton();
        unicodePanel = new javax.swing.JPanel();
        unicodeButton = new javax.swing.JButton();
        emptyPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        swapButton = new javax.swing.JButton();
        defaultButton = new javax.swing.JButton();
        recordPanel = new javax.swing.JPanel();
        historyPane = new javax.swing.JScrollPane();
        historyTextArea = new javax.swing.JTextArea();
        historyButtonPanel = new javax.swing.JPanel();
        editorNoticeButton = new javax.swing.JButton();
        makeListButton = new javax.swing.JButton();
        historyClearButton = new javax.swing.JButton();
        historyCopyButton = new javax.swing.JButton();
        historyPasteButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        bottomPanel = new javax.swing.JPanel();
        messageTextField = new javax.swing.JTextField();
        warnLabel = new javax.swing.JLabel();
        warnLabel.setVisible(false);
        filterCatIndicatorLabel = new javax.swing.JLabel();
        filterCatIndicatorLabel.setVisible(false);
        editTogglePanel = new javax.swing.JPanel();
        editToggleButton = new javax.swing.JToggleButton();
        editToggleButton.setVisible(false);
        jLabel4 = new javax.swing.JLabel();
        jSeparator31 = new javax.swing.JSeparator();
        navigationPanel = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        editFavoritesjButton = new javax.swing.JButton();
        addFavoritesButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        datatablesButton = new javax.swing.JButton();
        calculatorButton = new javax.swing.JButton();
        calendarButton = new javax.swing.JButton();
        timeDialogButton = new javax.swing.JButton();
        datetimeDiffButton = new javax.swing.JButton();
        randomButton = new javax.swing.JButton();
        colorToolbarButton = new javax.swing.JButton();
        phoneticAlphabetButton = new javax.swing.JButton();
        currencyUpdateButton = new javax.swing.JButton();
        ncMenuBar = new javax.swing.JMenuBar();
        programMenu = new javax.swing.JMenu();
        updateCenterMenuItem = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        restartItem = new javax.swing.JMenuItem();
        exitItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        programMenuItemSwap = new javax.swing.JMenuItem();
        sourceMenu = new javax.swing.JMenu();
        sourceMenuItemUndo = new javax.swing.JMenuItem();
        sourceMenuItemRedo = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        sourceMenuItemClear = new javax.swing.JMenuItem();
        sourceMenuItemCopy = new javax.swing.JMenuItem();
        sourceMenuItemPaste = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        sourceMenuItemFindUnit = new javax.swing.JMenuItem();
        sourceMenuItemDefault = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JSeparator();
        sourceMenuItemPlus = new javax.swing.JMenuItem();
        sourceMenuItemMinus = new javax.swing.JMenuItem();
        sourceMenuItemDefaultValue = new javax.swing.JMenuItem();
        targetMenu = new javax.swing.JMenu();
        targetMenuItemCopy = new javax.swing.JMenuItem();
        targetMenuItemFigures = new javax.swing.JMenuItem();
        targetMenuItemDecimals = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        targetMenuItemFindUnit = new javax.swing.JMenuItem();
        targetMenuItemDefault = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        targetMenuItemPlay = new javax.swing.JMenuItem();
        targetMenuItemStopPlaying = new javax.swing.JMenuItem();
        recordMenu = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        recordMenuItemClear = new javax.swing.JMenuItem();
        recordMenuItemCopy = new javax.swing.JMenuItem();
        recordMenuItemPaste = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JSeparator();
        recordMenuItemNew = new javax.swing.JMenuItem();
        recordMenuItemMakeList = new javax.swing.JMenuItem();
        recordMenuItemFormat = new javax.swing.JMenuItem();
        recordMenuItemFontsize = new javax.swing.JMenuItem();
        htmlMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        countUnitsMenuItem = new javax.swing.JMenuItem();
        unitsMenu = new javax.swing.JMenu();
        editUnitsMenuItem = new javax.swing.JMenuItem();
        reloadUnitsItem = new javax.swing.JMenuItem();
        categoriesMenu = new javax.swing.JMenu();
        editCategoriesMenuItem = new javax.swing.JMenuItem();
        reloadCategoriesMenuItem = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        propsMenuItem = new javax.swing.JMenuItem();
        frameMenu = new javax.swing.JMenu();
        miserlyRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        economicalRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        standardRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        luxuryRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        maximumRadioButtonMenuItem = new javax.swing.JRadioButtonMenuItem();
        navigationMenu = new javax.swing.JMenu();
        searchMenu = new javax.swing.JMenu();
        searchSourceMenuItem = new javax.swing.JMenuItem();
        searchTargetMenuItem = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JSeparator();
        searchCategoriesMenuItem = new javax.swing.JMenuItem();
        favoritesMenu = new javax.swing.JMenu();
        addFavoriteMenuItem = new javax.swing.JMenuItem();
        manageFavoritesMenuItem = new javax.swing.JMenuItem();
        filterMenuItem = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        tablesMenuItem = new javax.swing.JMenuItem();
        ToolsMenu = new javax.swing.JMenu();
        calculatorMenuItem = new javax.swing.JMenuItem();
        sourceMenuItemCalendar = new javax.swing.JMenuItem();
        timeDialogMenuItem = new javax.swing.JMenuItem();
        dateDiffMenuItem = new javax.swing.JMenuItem();
        randomNumbersMenuItem = new javax.swing.JMenuItem();
        sourceMenuItemColor = new javax.swing.JMenuItem();
        spellMenuItem = new javax.swing.JMenuItem();
        updateExchangeRatesMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpIndexMenuItem = new javax.swing.JMenuItem();
        tutorialMenuItem = new javax.swing.JMenuItem();
        newsletterMenuItem = new javax.swing.JMenuItem();
        supportMenuItem = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        homepageMenuItem = new javax.swing.JMenuItem();
        helpMenuItemAbout = new javax.swing.JMenuItem();

        sourcePopupMenuItemUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        sourcePopupMenuItemUndo.setText(rb.getString("GUI.General.Undo")); // NOI18N
        sourcePopupMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemUndoActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemUndo);

        sourcePopupMenuItemRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/redo.png"))); // NOI18N
        sourcePopupMenuItemRedo.setText(rb.getString("GUI.General.Redo")); // NOI18N
        sourcePopupMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemRedoActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemRedo);
        sourcePopupMenu.add(jSeparator23);

        sourcePopupMenuItemCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        sourcePopupMenuItemCut.setText(rb.getString("GUI.General.Cut")); // NOI18N
        sourcePopupMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemCutActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemCut);

        sourcePopupMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        sourcePopupMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        sourcePopupMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemCopyActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemCopy);

        sourcePopupMenuItemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        sourcePopupMenuItemPaste.setText(rb.getString("GUI.General.Paste")); // NOI18N
        sourcePopupMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemPasteActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemPaste);
        sourcePopupMenu.add(jSeparator28);

        sourcePopupMenuItemFindUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        sourcePopupMenuItemFindUnit.setText(rb.getString("GUI.Menu.Source.FindUnit")); // NOI18N
        sourcePopupMenuItemFindUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemFindUnitActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemFindUnit);

        sourcePopupMenuItemDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        sourcePopupMenuItemDefault.setText(rb.getString("GUI.Menu.Source.Default")); // NOI18N
        sourcePopupMenuItemDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemDefaultActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemDefault);
        sourcePopupMenu.add(jSeparator27);

        sourcePopupMenuItemPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-090.png"))); // NOI18N
        sourcePopupMenuItemPlus.setText("+1");
        sourcePopupMenuItemPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemPlusActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemPlus);

        sourcePopupMenuItemMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-270.png"))); // NOI18N
        sourcePopupMenuItemMinus.setText("-1");
        sourcePopupMenuItemMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuItemMinusActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuItemMinus);

        sourcePopupMenuDefaultValue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        sourcePopupMenuDefaultValue.setText(rb.getString("GUI.Menu.Source.DefaultValue")); // NOI18N
        sourcePopupMenuDefaultValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePopupMenuDefaultValueActionPerformed(evt);
            }
        });
        sourcePopupMenu.add(sourcePopupMenuDefaultValue);

        targetPopupMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        targetPopupMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        targetPopupMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPopupMenuItemCopyActionPerformed(evt);
            }
        });
        targetPopupMenu.add(targetPopupMenuItemCopy);
        targetPopupMenu.add(jSeparator34);

        targetPopupMenuItemFindUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        targetPopupMenuItemFindUnit.setText(rb.getString("GUI.Menu.Target.FindUnit")); // NOI18N
        targetPopupMenuItemFindUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPopupMenuItemFindUnitActionPerformed(evt);
            }
        });
        targetPopupMenu.add(targetPopupMenuItemFindUnit);

        targetPopupMenuItemDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        targetPopupMenuItemDefault.setText(rb.getString("GUI.Menu.Target.Default")); // NOI18N
        targetPopupMenuItemDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPopupMenuItemDefaultActionPerformed(evt);
            }
        });
        targetPopupMenu.add(targetPopupMenuItemDefault);
        targetPopupMenu.add(jSeparator33);

        targetPopupMenuItemPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-high.png"))); // NOI18N
        targetPopupMenuItemPlay.setText(rb.getString("GUI.Menu.Target.Play")); // NOI18N
        targetPopupMenuItemPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPopupMenuItemPlayActionPerformed(evt);
            }
        });
        targetPopupMenu.add(targetPopupMenuItemPlay);

        targetPopupMenuItemStopPlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-muted.png"))); // NOI18N
        targetPopupMenuItemStopPlaying.setText(rb.getString("GUI.Menu.Target.StopPlaying")); // NOI18N
        targetPopupMenuItemStopPlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPopupMenuItemStopPlayingActionPerformed(evt);
            }
        });
        targetPopupMenu.add(targetPopupMenuItemStopPlaying);

        historyMenuItemUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        historyMenuItemUndo.setText(rb.getString("GUI.General.Undo")); // NOI18N
        historyMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemUndoActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemUndo);

        historyMenuItemRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/redo.png"))); // NOI18N
        historyMenuItemRedo.setText(rb.getString("GUI.General.Redo")); // NOI18N
        historyMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemRedoActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemRedo);
        historyPopupMenu.add(jSeparator35);

        historyMenuItemCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        historyMenuItemCut.setText(rb.getString("GUI.General.Cut")); // NOI18N
        historyMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemCutActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemCut);

        historyMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        historyMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        historyMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemCopyActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemCopy);

        historyMenuItemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        historyMenuItemPaste.setText(rb.getString("GUI.General.Paste")); // NOI18N
        historyMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemPasteActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemPaste);
        historyPopupMenu.add(jSeparator36);

        historyPopupMenuItemNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-down.png"))); // NOI18N
        historyPopupMenuItemNew.setText(rb.getString("GUI.General.Notice")); // NOI18N
        historyPopupMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyPopupMenuItemNewActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyPopupMenuItemNew);

        historyPopupMenuItemMakeList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/script.png"))); // NOI18N
        historyPopupMenuItemMakeList.setText(rb.getString("GUI.General.MakeList")); // NOI18N
        historyPopupMenuItemMakeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyPopupMenuItemMakeListActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyPopupMenuItemMakeList);

        historyPopupMenuenuItemFormat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/format.png"))); // NOI18N
        historyPopupMenuenuItemFormat.setText(rb.getString("GUI.Menu.Records.Format")); // NOI18N
        historyPopupMenuenuItemFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyPopupMenuenuItemFormatActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyPopupMenuenuItemFormat);

        historyMenuFontSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/fontsize.png"))); // NOI18N
        historyMenuFontSize.setText(rb.getString("GUI.Menu.Records.Fontsize")); // NOI18N
        historyMenuFontSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuFontSizeActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuFontSize);

        historyPopupMenuItemHTML.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/format-headerfooter.png"))); // NOI18N
        historyPopupMenuItemHTML.setText(rb.getString("GUI.Menu.Records.AddHTML")); // NOI18N
        historyPopupMenuItemHTML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyPopupMenuItemHTMLActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyPopupMenuItemHTML);
        historyPopupMenu.add(jSeparator37);

        historyMenuItemLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/document-open.png"))); // NOI18N
        historyMenuItemLoad.setText(rb.getString("GUI.General.Load")); // NOI18N
        historyMenuItemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemLoadActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemLoad);

        historyMenuItemSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/save.png"))); // NOI18N
        historyMenuItemSave.setText(rb.getString("GUI.General.Save")); // NOI18N
        historyMenuItemSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyMenuItemSaveActionPerformed(evt);
            }
        });
        historyPopupMenu.add(historyMenuItemSave);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(ProgInfo.getInstance().getProgramName()+" "+ProgInfo.getInstance().getVersion()+" - "+ProgInfo.getInstance().getHomepage());
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix32x32/chameleon.png")).getImage());
        setName("NumericalChameleon"); // NOI18N

        converterPanel.setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());

        headerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 1, 5));
        headerPanel.setLayout(new java.awt.GridBagLayout());

        categoryBox.setMaximumRowCount(12);
        categoryBox.setToolTipText(rb.getString("GUI.Area.Main.Conversion.ToolTip")); // NOI18N
        categoryBox.setMinimumSize(new java.awt.Dimension(25, 51));
        categoryBox.setOpaque(false);
        categoryBox.setPreferredSize(new java.awt.Dimension(29, 51));
        categoryBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        headerPanel.add(categoryBox, gridBagConstraints);

        conversionLabel.setText(rb.getString("GUI.Area.Main.Category.Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        headerPanel.add(conversionLabel, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        categoryUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/up.png"))); // NOI18N
        categoryUpButton.setToolTipText(rb.getString("GUI.Area.Previous.Category")); // NOI18N
        categoryUpButton.setPreferredSize(new java.awt.Dimension(15, 13));
        categoryUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryUpButtonActionPerformed(evt);
            }
        });
        jPanel3.add(categoryUpButton);

        categoryDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/down.png"))); // NOI18N
        categoryDownButton.setToolTipText(rb.getString("GUI.Area.Next.Category")); // NOI18N
        categoryDownButton.setPreferredSize(new java.awt.Dimension(15, 13));
        categoryDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryDownButtonActionPerformed(evt);
            }
        });
        jPanel3.add(categoryDownButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        headerPanel.add(jPanel3, gridBagConstraints);

        searchCategoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        searchCategoryButton.setToolTipText(rb.getString("GUI.Menu.Find.FindCategory")); // NOI18N
        searchCategoryButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        searchCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCategoryButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        headerPanel.add(searchCategoryButton, gridBagConstraints);

        categoryHelpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/question-white.png"))); // NOI18N
        categoryHelpButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        categoryHelpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryHelpButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        headerPanel.add(categoryHelpButton, gridBagConstraints);

        groupComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                groupComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        headerPanel.add(groupComboBox, gridBagConstraints);

        groupLabel.setText(rb.getString("GUI.GroupColon")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        headerPanel.add(groupLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        mainPanel.add(headerPanel, gridBagConstraints);

        sourcePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        sourcePanel.setLayout(new java.awt.GridBagLayout());

        sourceHeaderPanel.setLayout(new java.awt.GridBagLayout());

        sourceBox.setMaximumRowCount(20);
        sourceBox.setToolTipText(rb.getString("GUI.Area.Source.ComboBox.ToolTip")); // NOI18N
        sourceBox.setMinimumSize(new java.awt.Dimension(260, 25));
        sourceBox.setPreferredSize(new java.awt.Dimension(260, 25));
        sourceBox.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                sourceBoxComponentResized(evt);
            }
        });
        sourceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        sourceHeaderPanel.add(sourceBox, gridBagConstraints);

        sourceScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
        sourceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        sourceScrollPane.setMinimumSize(new java.awt.Dimension(300, 42));
        sourceScrollPane.setPreferredSize(new java.awt.Dimension(300, 42));

        sourceTextArea.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        sourceTextArea.setRows(1);
        sourceTextArea.setToolTipText(rb.getString("GUI.Area.Source.TextField.ToolTip")); // NOI18N
        sourceTextArea.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        sourceTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sourceTextAreaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sourceTextAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sourceTextAreaMouseReleased(evt);
            }
        });
        sourceTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sourceTextAreaKeyReleased(evt);
            }
        });
        sourceScrollPane.setViewportView(sourceTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        sourceHeaderPanel.add(sourceScrollPane, gridBagConstraints);

        findSourceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        findSourceButton.setToolTipText(rb.getString("GUI.Menu.Find.FindSourceUnit")); // NOI18N
        findSourceButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        findSourceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findSourceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceHeaderPanel.add(findSourceButton, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        sourceBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/up.png"))); // NOI18N
        sourceBackButton.setToolTipText(rb.getString("GUI.Area.Back.Button")); // NOI18N
        sourceBackButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        sourceBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceBackButtonActionPerformed(evt);
            }
        });
        jPanel1.add(sourceBackButton);

        sourceNextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/down.png"))); // NOI18N
        sourceNextButton.setToolTipText(rb.getString("GUI.Area.Next.Button")); // NOI18N
        sourceNextButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        sourceNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceNextButtonActionPerformed(evt);
            }
        });
        jPanel1.add(sourceNextButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        sourceHeaderPanel.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        sourcePanel.add(sourceHeaderPanel, gridBagConstraints);

        sourceButtonPanel.setLayout(new java.awt.GridBagLayout());

        sourceClearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        sourceClearButton.setToolTipText(rb.getString("GUI.Area.Source.Cut.ToolTip")); // NOI18N
        sourceClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceClearButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceButtonPanel.add(sourceClearButton, gridBagConstraints);

        sourceMinusButton.setMnemonic('-');
        sourceMinusButton.setText("-1");
        sourceMinusButton.setToolTipText(rb.getString("GUI.Area.Source.Sub1.ToolTip")); // NOI18N
        sourceMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceButtonPanel.add(sourceMinusButton, gridBagConstraints);

        sourcePlusButton.setMnemonic('1');
        sourcePlusButton.setText("+1");
        sourcePlusButton.setToolTipText(rb.getString("GUI.Area.Source.Add1.ToolTip")); // NOI18N
        sourcePlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceButtonPanel.add(sourcePlusButton, gridBagConstraints);

        sourceCopyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        sourceCopyButton.setToolTipText(rb.getString("GUI.Area.Source.Copy.ToolTip")); // NOI18N
        sourceCopyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceCopyButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceButtonPanel.add(sourceCopyButton, gridBagConstraints);

        sourcePasteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        sourcePasteButton.setToolTipText(rb.getString("GUI.Area.Source.Paste.ToolTip")); // NOI18N
        sourcePasteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourcePasteButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        sourceButtonPanel.add(sourcePasteButton, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        tzDateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/calendar.png"))); // NOI18N
        tzDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tzDateButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(tzDateButton, gridBagConstraints);

        tzTimeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/time.png"))); // NOI18N
        tzTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tzTimeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel7.add(tzTimeButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        sourceButtonPanel.add(jPanel7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        sourcePanel.add(sourceButtonPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        mainPanel.add(sourcePanel, gridBagConstraints);

        targetPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        targetPanel.setLayout(new java.awt.GridBagLayout());

        targetHeaderPanel.setLayout(new java.awt.GridBagLayout());

        targetBox.setMaximumRowCount(20);
        targetBox.setToolTipText(rb.getString("GUI.Area.Target.ComboBox.ToolTip")); // NOI18N
        targetBox.setMinimumSize(new java.awt.Dimension(260, 25));
        targetBox.setPreferredSize(new java.awt.Dimension(260, 25));
        targetBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetBoxActionPerformed(evt);
            }
        });
        targetBox.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                targetBoxComponentResized(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        targetHeaderPanel.add(targetBox, gridBagConstraints);

        targetScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        targetScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        targetScrollPane.setMinimumSize(new java.awt.Dimension(300, 42));
        targetScrollPane.setPreferredSize(new java.awt.Dimension(300, 42));

        targetTextArea.setEditable(false);
        targetTextArea.setBackground(new Color(230,230,230));
        targetTextArea.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        targetTextArea.setRows(1);
        targetTextArea.setToolTipText(rb.getString("GUI.Area.Target.TextField.ToolTip")); // NOI18N
        targetTextArea.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        targetTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                targetTextAreaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                targetTextAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                targetTextAreaMouseReleased(evt);
            }
        });
        targetScrollPane.setViewportView(targetTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        targetHeaderPanel.add(targetScrollPane, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        targetBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/up.png"))); // NOI18N
        targetBackButton.setToolTipText(rb.getString("GUI.Area.Back.Button")); // NOI18N
        targetBackButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        targetBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetBackButtonActionPerformed(evt);
            }
        });
        jPanel2.add(targetBackButton);

        targetNextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/down.png"))); // NOI18N
        targetNextButton.setToolTipText(rb.getString("GUI.Area.Next.Button")); // NOI18N
        targetNextButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        targetNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetNextButtonActionPerformed(evt);
            }
        });
        jPanel2.add(targetNextButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        targetHeaderPanel.add(jPanel2, gridBagConstraints);

        findTargetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        findTargetButton.setToolTipText(rb.getString("GUI.Menu.Find.FindTargetUnit")); // NOI18N
        findTargetButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        findTargetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findTargetButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        targetHeaderPanel.add(findTargetButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        targetPanel.add(targetHeaderPanel, gridBagConstraints);

        targetButtonPanel.setLayout(new java.awt.GridBagLayout());

        targetCopyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        targetCopyButton.setToolTipText(rb.getString("GUI.Area.Source.Copy.ToolTip")); // NOI18N
        targetCopyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetCopyButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        targetButtonPanel.add(targetCopyButton, gridBagConstraints);

        cardPanel.setLayout(new java.awt.CardLayout());
        // jonelo-begin
        //cardLayout = new java.awt.CardLayout();
        //cardPanel.setLayout(cardLayout);
        cardLayout = (java.awt.CardLayout)cardPanel.getLayout();
        // jonelo-end

        precisionLabel.setText(rb.getString("GUI.Menu.Target.PlacesOfDecimals")); // NOI18N
        precisionLabel.setToolTipText(rb.getString("GUI.Area.Target.PlacesOfDecimals.ToolTip")); // NOI18N

        precisionTextField.setText("2");
        precisionTextField.setToolTipText(rb.getString("GUI.Area.Target.PlacesOfDecimals.ToolTip")); // NOI18N
        precisionTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        precisionTextField.setMinimumSize(new java.awt.Dimension(30, 20));
        precisionTextField.setPreferredSize(new java.awt.Dimension(30, 20));
        precisionTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                precisionTextFieldKeyReleased(evt);
            }
        });

        sciCheckBox.setText("SCI");
        sciCheckBox.setToolTipText(rb.getString("GUI.Area.Target.SCI")); // NOI18N
        sciCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sciCheckBoxItemStateChanged(evt);
            }
        });
        sciCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sciCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sciPanelLayout = new javax.swing.GroupLayout(sciPanel);
        sciPanel.setLayout(sciPanelLayout);
        sciPanelLayout.setHorizontalGroup(
            sciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sciPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(precisionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(precisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sciCheckBox)
                .addContainerGap())
        );
        sciPanelLayout.setVerticalGroup(
            sciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sciPanelLayout.createSequentialGroup()
                .addGroup(sciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sciCheckBox)
                    .addComponent(precisionLabel))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        cardPanel.add(sciPanel, "sciCard");

        numberTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { rb.getString("SpokenNumber.Number"), rb.getString("SpokenNumber.Digits") })
        );
        numberTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberTypeComboBoxActionPerformed(evt);
            }
        });

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-high.png"))); // NOI18N
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-muted.png"))); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout soundPanelLayout = new javax.swing.GroupLayout(soundPanel);
        soundPanel.setLayout(soundPanelLayout);
        soundPanelLayout.setHorizontalGroup(
            soundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberTypeComboBox, 0, 70, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopButton)
                .addContainerGap())
        );
        soundPanelLayout.setVerticalGroup(
            soundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soundPanelLayout.createSequentialGroup()
                .addGroup(soundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stopButton)
                    .addComponent(playButton)
                    .addComponent(numberTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(soundPanel, "soundCard");

        playTimeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-high.png"))); // NOI18N
        playTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playTimeButtonActionPerformed(evt);
            }
        });

        stopPlayingTimeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-muted.png"))); // NOI18N
        stopPlayingTimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopPlayingTimeButtonActionPerformed(evt);
            }
        });

        timePanelTimeChooserButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/time.png"))); // NOI18N
        timePanelTimeChooserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timePanelTimeChooserButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timePanelLayout = new javax.swing.GroupLayout(timePanel);
        timePanel.setLayout(timePanelLayout);
        timePanelLayout.setHorizontalGroup(
            timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playTimeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopPlayingTimeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timePanelTimeChooserButton)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        timePanelLayout.setVerticalGroup(
            timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timePanelLayout.createSequentialGroup()
                .addGroup(timePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playTimeButton)
                    .addComponent(stopPlayingTimeButton)
                    .addComponent(timePanelTimeChooserButton))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        cardPanel.add(timePanel, "timeCard");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/calendar.png"))); // NOI18N
        jButton1.setText(rb.getString("GUI.General.DatePicker")); // NOI18N
        jButton1.setToolTipText(rb.getString("GUI.General.DatePicker")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout calPanelLayout = new javax.swing.GroupLayout(calPanel);
        calPanel.setLayout(calPanelLayout);
        calPanelLayout.setHorizontalGroup(
            calPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        calPanelLayout.setVerticalGroup(
            calPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calPanelLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(calPanel, "calCard");

        colorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/color.png"))); // NOI18N
        colorButton.setText(rb.getString("GUI.General.ColorPicker")); // NOI18N
        colorButton.setToolTipText(rb.getString("GUI.General.ColorPicker")); // NOI18N
        colorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout colPanelLayout = new javax.swing.GroupLayout(colPanel);
        colPanel.setLayout(colPanelLayout);
        colPanelLayout.setHorizontalGroup(
            colPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, colPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        colPanelLayout.setVerticalGroup(
            colPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colPanelLayout.createSequentialGroup()
                .addComponent(colorButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(colPanel, "colCard");

        deltaLabel.setText("delta: 0 hours");

        javax.swing.GroupLayout tzPanelLayout = new javax.swing.GroupLayout(tzPanel);
        tzPanel.setLayout(tzPanelLayout);
        tzPanelLayout.setHorizontalGroup(
            tzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tzPanelLayout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addComponent(deltaLabel)
                .addContainerGap())
        );
        tzPanelLayout.setVerticalGroup(
            tzPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tzPanelLayout.createSequentialGroup()
                .addComponent(deltaLabel)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        cardPanel.add(tzPanel, "tzCard");

        fingerprintComboBox.setMaximumRowCount(20);
        fingerprintComboBox.setModel(GeneralGUI.getComboBoxModel(net.numericalchameleon.categories.CategoryDigitalFingerprints.getEncodings()));
        fingerprintComboBox.setRenderer(new net.numericalchameleon.gui.main.renderer.EncodingCellRenderer());
        fingerprintComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fingerprintComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText(rb.getString("General.FormatColon")); // NOI18N

        javax.swing.GroupLayout fingerprintPanelLayout = new javax.swing.GroupLayout(fingerprintPanel);
        fingerprintPanel.setLayout(fingerprintPanelLayout);
        fingerprintPanelLayout.setHorizontalGroup(
            fingerprintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fingerprintPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fingerprintComboBox, 0, 142, Short.MAX_VALUE)
                .addContainerGap())
        );
        fingerprintPanelLayout.setVerticalGroup(
            fingerprintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fingerprintPanelLayout.createSequentialGroup()
                .addGroup(fingerprintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fingerprintComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        cardPanel.add(fingerprintPanel, "checksumCard");

        dateFormatComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { rb.getString("General.SHORT"), rb.getString("General.MEDIUM"), rb.getString("General.LONG"), rb.getString("General.FULL") }));
        dateFormatComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFormatComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText(rb.getString("General.FormatColon")); // NOI18N

        javax.swing.GroupLayout datePanelLayout = new javax.swing.GroupLayout(datePanel);
        datePanel.setLayout(datePanelLayout);
        datePanelLayout.setHorizontalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateFormatComboBox, 0, 142, Short.MAX_VALUE)
                .addContainerGap())
        );
        datePanelLayout.setVerticalGroup(
            datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datePanelLayout.createSequentialGroup()
                .addGroup(datePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateFormatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        cardPanel.add(datePanel, "dateCard");

        googleMapsButton.setText("Google Maps");
        googleMapsButton.setToolTipText("Google Maps");
        googleMapsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                googleMapsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout googleMapsPanelLayout = new javax.swing.GroupLayout(googleMapsPanel);
        googleMapsPanel.setLayout(googleMapsPanelLayout);
        googleMapsPanelLayout.setHorizontalGroup(
            googleMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(googleMapsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(googleMapsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        googleMapsPanelLayout.setVerticalGroup(
            googleMapsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(googleMapsPanelLayout.createSequentialGroup()
                .addComponent(googleMapsButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(googleMapsPanel, "coordCard");

        unicodeButton.setText("www.unicode.org");
        unicodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unicodeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout unicodePanelLayout = new javax.swing.GroupLayout(unicodePanel);
        unicodePanel.setLayout(unicodePanelLayout);
        unicodePanelLayout.setHorizontalGroup(
            unicodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unicodePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unicodeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        unicodePanelLayout.setVerticalGroup(
            unicodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(unicodePanelLayout.createSequentialGroup()
                .addComponent(unicodeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(unicodePanel, "unicodeCard");

        javax.swing.GroupLayout emptyPanelLayout = new javax.swing.GroupLayout(emptyPanel);
        emptyPanel.setLayout(emptyPanelLayout);
        emptyPanelLayout.setHorizontalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );
        emptyPanelLayout.setVerticalGroup(
            emptyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        cardPanel.add(emptyPanel, "emptyCard");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        targetButtonPanel.add(cardPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        targetPanel.add(targetButtonPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        mainPanel.add(targetPanel, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(rb.getString("GUI.Menu.Source")); // NOI18N
        jPanel5.add(jLabel2, java.awt.BorderLayout.CENTER);
        jPanel5.add(jSeparator29, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        mainPanel.add(jPanel5, gridBagConstraints);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setText(rb.getString("GUI.Menu.Target")); // NOI18N
        jPanel6.add(jLabel3, java.awt.BorderLayout.WEST);
        jPanel6.add(jSeparator30, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 5, 0);
        mainPanel.add(jPanel6, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        swapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/swap.png"))); // NOI18N
        swapButton.setMnemonic('w');
        swapButton.setToolTipText(rb.getString("GUI.Menu.Main.Switch")); // NOI18N
        swapButton.setMaximumSize(new java.awt.Dimension(35, 35));
        swapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swapButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel4.add(swapButton, gridBagConstraints);

        defaultButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/arrow-circle-135-left.png"))); // NOI18N
        defaultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(defaultButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        mainPanel.add(jPanel4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        converterPanel.add(mainPanel, gridBagConstraints);

        recordPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        recordPanel.setMinimumSize(new java.awt.Dimension(642, 300));
        recordPanel.setPreferredSize(new java.awt.Dimension(642, 300));
        recordPanel.setLayout(new java.awt.GridBagLayout());

        // pre init for Mac OS X support (Apple HI guidelines)
        if (System.getProperty("os.name").equals(Constants.MAC_OS_X))
        {
            historyPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            historyPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        }

        historyTextArea.setRows(10);
        historyTextArea.setToolTipText(rb.getString("GUI.Area.Records.TextArea.ToolTip")); // NOI18N
        historyTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        historyTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                historyAreaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                historyAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                historyAreaMouseReleased(evt);
            }
        });
        historyPane.setViewportView(historyTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        recordPanel.add(historyPane, gridBagConstraints);

        historyButtonPanel.setLayout(new java.awt.GridBagLayout());

        editorNoticeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-down.png"))); // NOI18N
        editorNoticeButton.setText(rb.getString("GUI.General.Notice")); // NOI18N
        editorNoticeButton.setToolTipText(rb.getString("GUI.Area.Records.Notice.ToolTip")); // NOI18N
        editorNoticeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorNoticeButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(editorNoticeButton, new java.awt.GridBagConstraints());

        makeListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/script.png"))); // NOI18N
        makeListButton.setText(rb.getString("GUI.General.MakeList.Button")); // NOI18N
        makeListButton.setToolTipText(rb.getString("GUI.General.MakeList.Button")); // NOI18N
        makeListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeListButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(makeListButton, new java.awt.GridBagConstraints());

        historyClearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        historyClearButton.setText(rb.getString("GUI.General.Cut")); // NOI18N
        historyClearButton.setToolTipText(rb.getString("GUI.Area.Records.Cut.ToolTip")); // NOI18N
        historyClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyClearButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(historyClearButton, new java.awt.GridBagConstraints());

        historyCopyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        historyCopyButton.setText(rb.getString("GUI.General.Copy")); // NOI18N
        historyCopyButton.setToolTipText(rb.getString("GUI.Area.Records.Copy.ToolTip")); // NOI18N
        historyCopyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyCopyButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(historyCopyButton, new java.awt.GridBagConstraints());

        historyPasteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        historyPasteButton.setText(rb.getString("GUI.General.Paste")); // NOI18N
        historyPasteButton.setToolTipText(rb.getString("GUI.Area.Records.Paste.ToolTip")); // NOI18N
        historyPasteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyPasteButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(historyPasteButton, new java.awt.GridBagConstraints());

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/save.png"))); // NOI18N
        saveButton.setText(rb.getString("GUI.General.Save")); // NOI18N
        saveButton.setToolTipText(rb.getString("GUI.General.Save")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        historyButtonPanel.add(saveButton, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        recordPanel.add(historyButtonPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        converterPanel.add(recordPanel, gridBagConstraints);

        bottomPanel.setLayout(new java.awt.GridBagLayout());

        messageTextField.setEditable(false);
        messageTextField.setToolTipText(rb.getString("GUI.Area.Message.ToolTip")); // NOI18N
        messageTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        messageTextField.setFocusable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        bottomPanel.add(messageTextField, gridBagConstraints);

        warnLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/warning.png"))); // NOI18N
        warnLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                warnLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        bottomPanel.add(warnLabel, gridBagConstraints);

        filterCatIndicatorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/filter.png"))); // NOI18N
        filterCatIndicatorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterCatIndicatorLabelMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 5);
        bottomPanel.add(filterCatIndicatorLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 5, 5);
        converterPanel.add(bottomPanel, gridBagConstraints);

        editTogglePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));
        editTogglePanel.setLayout(new java.awt.BorderLayout());

        editToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix10x10/arrow-right.png"))); // NOI18N
        editToggleButton.setSelected(true);
        editToggleButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 5));
        editToggleButton.setBorderPainted(false);
        editToggleButton.setContentAreaFilled(false);
        editToggleButton.setFocusPainted(false);
        editToggleButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix10x10/arrow-down.png"))); // NOI18N
        editToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                editToggleButtonStateChanged(evt);
            }
        });
        editTogglePanel.add(editToggleButton, java.awt.BorderLayout.WEST);

        jLabel4.setText(rb.getString("GUI.General.Editor")); // NOI18N
        editTogglePanel.add(jLabel4, java.awt.BorderLayout.CENTER);
        editTogglePanel.add(jSeparator31, java.awt.BorderLayout.SOUTH);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        converterPanel.add(editTogglePanel, gridBagConstraints);

        navigationPanel.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);

        editFavoritesjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/bookmarks.png"))); // NOI18N
        editFavoritesjButton.setToolTipText(rb.getString("GUI.Menu.Favorites.Manage")); // NOI18N
        editFavoritesjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editFavoritesjButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(editFavoritesjButton);

        addFavoritesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/bookmarks-plus.png"))); // NOI18N
        addFavoritesButton.setToolTipText(rb.getString("GUI.Menu.Favorites.Add")); // NOI18N
        addFavoritesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFavoritesButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addFavoritesButton);
        jToolBar1.add(jSeparator2);

        datatablesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/table.png"))); // NOI18N
        datatablesButton.setToolTipText(rb.getString("GUI.Menu.Service.Tables")); // NOI18N
        datatablesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datatablesButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(datatablesButton);

        calculatorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/calculator-scientific.png"))); // NOI18N
        calculatorButton.setToolTipText(rb.getString("GUI.Menu.Service.Calculator")); // NOI18N
        calculatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculatorButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(calculatorButton);

        calendarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/calendar.png"))); // NOI18N
        calendarButton.setToolTipText(rb.getString("GUI.General.DatePicker")); // NOI18N
        calendarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendarButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(calendarButton);

        timeDialogButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/time.png"))); // NOI18N
        timeDialogButton.setToolTipText(rb.getString("GUI.General.TimePicker")); // NOI18N
        timeDialogButton.setFocusable(false);
        timeDialogButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timeDialogButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        timeDialogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeDialogButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(timeDialogButton);

        datetimeDiffButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/duration.png"))); // NOI18N
        datetimeDiffButton.setToolTipText(rb.getString("DateDiff.CalculateTimeDifference")); // NOI18N
        datetimeDiffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datetimeDiffButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(datetimeDiffButton);

        randomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/dice.png"))); // NOI18N
        randomButton.setToolTipText(rb.getString("GUI.RandomGenerator.DialogTitle")); // NOI18N
        randomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(randomButton);

        colorToolbarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/color.png"))); // NOI18N
        colorToolbarButton.setToolTipText(rb.getString("GUI.General.ColorPicker")); // NOI18N
        colorToolbarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorToolbarButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(colorToolbarButton);

        phoneticAlphabetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/phonetic-alphabet.png"))); // NOI18N
        phoneticAlphabetButton.setToolTipText(rb.getString("GUI.Menu.Service.Spell")); // NOI18N
        phoneticAlphabetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneticAlphabetButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(phoneticAlphabetButton);

        currencyUpdateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/currency.png"))); // NOI18N
        currencyUpdateButton.setToolTipText(rb.getString("GUI.Menu.Main.UpdateExchangeRates")); // NOI18N
        currencyUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currencyUpdateButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(currencyUpdateButton);

        navigationPanel.add(jToolBar1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 5);
        converterPanel.add(navigationPanel, gridBagConstraints);

        getContentPane().add(converterPanel, java.awt.BorderLayout.CENTER);

        programMenu.setText(rb.getString("GUI.Menu.Main")); // NOI18N

        updateCenterMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/update.png"))); // NOI18N
        updateCenterMenuItem.setText(rb.getString("GUI.General.Update")+" ..."); // NOI18N
        updateCenterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCenterMenuItemActionPerformed(evt);
            }
        });
        programMenu.add(updateCenterMenuItem);
        programMenu.add(jSeparator15);

        restartItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/arrow-circle.png"))); // NOI18N
        restartItem.setText(rb.getString("GUI.Menu.Main.Restart")); // NOI18N
        restartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartItemActionPerformed(evt);
            }
        });
        programMenu.add(restartItem);

        exitItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/close.png"))); // NOI18N
        exitItem.setText(rb.getString("GUI.Menu.Main.Exit")); // NOI18N
        exitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitItemActionPerformed(evt);
            }
        });
        programMenu.add(exitItem);

        ncMenuBar.add(programMenu);

        editMenu.setText(rb.getString("GUI.General.Edit")); // NOI18N

        programMenuItemSwap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/swap.png"))); // NOI18N
        programMenuItemSwap.setText(rb.getString("GUI.Menu.Main.Switch")); // NOI18N
        programMenuItemSwap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                programMenuItemSwapActionPerformed(evt);
            }
        });
        editMenu.add(programMenuItemSwap);

        sourceMenu.setText(rb.getString("GUI.Menu.Source")); // NOI18N

        sourceMenuItemUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        sourceMenuItemUndo.setText(rb.getString("GUI.General.Undo")); // NOI18N
        sourceMenuItemUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemUndoActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemUndo);

        sourceMenuItemRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/redo.png"))); // NOI18N
        sourceMenuItemRedo.setText(rb.getString("GUI.General.Redo")); // NOI18N
        sourceMenuItemRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemRedoActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemRedo);
        sourceMenu.add(jSeparator9);

        sourceMenuItemClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        sourceMenuItemClear.setText(rb.getString("GUI.General.Cut")); // NOI18N
        sourceMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemClearActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemClear);

        sourceMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        sourceMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        sourceMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemCopyActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemCopy);

        sourceMenuItemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        sourceMenuItemPaste.setText(rb.getString("GUI.General.Paste")); // NOI18N
        sourceMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemPasteActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemPaste);
        sourceMenu.add(jSeparator7);

        sourceMenuItemFindUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        sourceMenuItemFindUnit.setText(rb.getString("GUI.Menu.Source.FindUnit")); // NOI18N
        sourceMenuItemFindUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemFindUnitActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemFindUnit);

        sourceMenuItemDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        sourceMenuItemDefault.setText(rb.getString("GUI.Menu.Source.Default")); // NOI18N
        sourceMenuItemDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemDefaultActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemDefault);
        sourceMenu.add(jSeparator25);

        sourceMenuItemPlus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-090.png"))); // NOI18N
        sourceMenuItemPlus.setText("+1");
        sourceMenuItemPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemPlusActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemPlus);

        sourceMenuItemMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-270.png"))); // NOI18N
        sourceMenuItemMinus.setText("-1");
        sourceMenuItemMinus.setPreferredSize(new java.awt.Dimension(69, 21));
        sourceMenuItemMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemMinusActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemMinus);

        sourceMenuItemDefaultValue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        sourceMenuItemDefaultValue.setText(rb.getString("GUI.Menu.Source.DefaultValue")); // NOI18N
        sourceMenuItemDefaultValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemDefaultValueActionPerformed(evt);
            }
        });
        sourceMenu.add(sourceMenuItemDefaultValue);

        editMenu.add(sourceMenu);

        targetMenu.setText(rb.getString("GUI.Menu.Target")); // NOI18N

        targetMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        targetMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        targetMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemCopyActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemCopy);

        targetMenuItemFigures.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/sci.png"))); // NOI18N
        targetMenuItemFigures.setText(rb.getString("GUI.Menu.Target.SignificantFigures")); // NOI18N
        targetMenuItemFigures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemFiguresActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemFigures);

        targetMenuItemDecimals.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/decimal-places.png"))); // NOI18N
        targetMenuItemDecimals.setText(rb.getString("GUI.Menu.Target.PlacesOfDecimals")); // NOI18N
        targetMenuItemDecimals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemDecimalsActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemDecimals);
        targetMenu.add(jSeparator10);

        targetMenuItemFindUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        targetMenuItemFindUnit.setText(rb.getString("GUI.Menu.Target.FindUnit")); // NOI18N
        targetMenuItemFindUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemFindUnitActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemFindUnit);

        targetMenuItemDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/control-stop-square.png"))); // NOI18N
        targetMenuItemDefault.setText(rb.getString("GUI.Menu.Source.Default")); // NOI18N
        targetMenuItemDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemDefaultActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemDefault);
        targetMenu.add(jSeparator13);

        targetMenuItemPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-high.png"))); // NOI18N
        targetMenuItemPlay.setText(rb.getString("GUI.Menu.Target.Play")); // NOI18N
        targetMenuItemPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemPlayActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemPlay);

        targetMenuItemStopPlaying.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/audio-volume-muted.png"))); // NOI18N
        targetMenuItemStopPlaying.setText(rb.getString("GUI.Menu.Target.StopPlaying")); // NOI18N
        targetMenuItemStopPlaying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetMenuItemStopPlayingActionPerformed(evt);
            }
        });
        targetMenu.add(targetMenuItemStopPlaying);

        editMenu.add(targetMenu);

        recordMenu.setText(rb.getString("GUI.General.Editor")); // NOI18N

        undoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        undoMenuItem.setText(rb.getString("GUI.General.Undo")); // NOI18N
        undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuItemActionPerformed(evt);
            }
        });
        recordMenu.add(undoMenuItem);

        redoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/redo.png"))); // NOI18N
        redoMenuItem.setText(rb.getString("GUI.General.Redo")); // NOI18N
        redoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoMenuItemActionPerformed(evt);
            }
        });
        recordMenu.add(redoMenuItem);
        recordMenu.add(jSeparator1);

        recordMenuItemClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-cut.png"))); // NOI18N
        recordMenuItemClear.setText(rb.getString("GUI.General.Cut")); // NOI18N
        recordMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemClearActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemClear);

        recordMenuItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        recordMenuItemCopy.setText(rb.getString("GUI.General.Copy")); // NOI18N
        recordMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemCopyActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemCopy);

        recordMenuItemPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-paste.png"))); // NOI18N
        recordMenuItemPaste.setText(rb.getString("GUI.General.Paste")); // NOI18N
        recordMenuItemPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemPasteActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemPaste);
        recordMenu.add(jSeparator22);

        recordMenuItemNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-down.png"))); // NOI18N
        recordMenuItemNew.setText(rb.getString("GUI.General.Notice")); // NOI18N
        recordMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemNewActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemNew);

        recordMenuItemMakeList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/script.png"))); // NOI18N
        recordMenuItemMakeList.setText(rb.getString("GUI.General.MakeList")); // NOI18N
        recordMenuItemMakeList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemMakeListActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemMakeList);

        recordMenuItemFormat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/format.png"))); // NOI18N
        recordMenuItemFormat.setText(rb.getString("GUI.Menu.Records.Format")); // NOI18N
        recordMenuItemFormat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemFormatActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemFormat);

        recordMenuItemFontsize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/fontsize.png"))); // NOI18N
        recordMenuItemFontsize.setText(rb.getString("GUI.Menu.Records.Fontsize")); // NOI18N
        recordMenuItemFontsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordMenuItemFontsizeActionPerformed(evt);
            }
        });
        recordMenu.add(recordMenuItemFontsize);

        htmlMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/format-headerfooter.png"))); // NOI18N
        htmlMenuItem.setText(rb.getString("GUI.Menu.Records.AddHTML")); // NOI18N
        htmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                htmlMenuItemActionPerformed(evt);
            }
        });
        recordMenu.add(htmlMenuItem);
        recordMenu.add(jSeparator6);

        loadMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/document-open.png"))); // NOI18N
        loadMenuItem.setText(rb.getString("GUI.General.Load")); // NOI18N
        loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMenuItemActionPerformed(evt);
            }
        });
        recordMenu.add(loadMenuItem);

        saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/save.png"))); // NOI18N
        saveMenuItem.setText(rb.getString("GUI.General.Save")); // NOI18N
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        recordMenu.add(saveMenuItem);

        editMenu.add(recordMenu);
        editMenu.add(jSeparator17);

        countUnitsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/sum.png"))); // NOI18N
        countUnitsMenuItem.setText(rb.getString("GUI.Menu.Count")); // NOI18N
        countUnitsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countUnitsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(countUnitsMenuItem);

        unitsMenu.setText(rb.getString("GUI.Config.Units")); // NOI18N

        editUnitsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/editor.png"))); // NOI18N
        editUnitsMenuItem.setText(rb.getString("GUI.Config.Units.Edit")); // NOI18N
        editUnitsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUnitsMenuItemActionPerformed(evt);
            }
        });
        unitsMenu.add(editUnitsMenuItem);

        reloadUnitsItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/arrow-circle-135-left.png"))); // NOI18N
        reloadUnitsItem.setText(rb.getString("GUI.Config.Units.Reload")); // NOI18N
        reloadUnitsItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadUnitsItemActionPerformed(evt);
            }
        });
        unitsMenu.add(reloadUnitsItem);

        editMenu.add(unitsMenu);

        categoriesMenu.setText(rb.getString("GUI.Config.Categories")); // NOI18N

        editCategoriesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/editor.png"))); // NOI18N
        editCategoriesMenuItem.setText(rb.getString("GUI.Config.Category.Edit")); // NOI18N
        editCategoriesMenuItem.setEnabled(false);
        categoriesMenu.add(editCategoriesMenuItem);

        reloadCategoriesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/arrow-circle-135-left.png"))); // NOI18N
        reloadCategoriesMenuItem.setText(rb.getString("GUI.Config.Category.Reload")); // NOI18N
        reloadCategoriesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reloadCategoriesMenuItemActionPerformed(evt);
            }
        });
        categoriesMenu.add(reloadCategoriesMenuItem);

        editMenu.add(categoriesMenu);
        editMenu.add(jSeparator19);

        propsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/wrench-screwdriver.png"))); // NOI18N
        propsMenuItem.setText(rb.getString("GUI.General.Preferences")+" ..."); // NOI18N
        propsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(propsMenuItem);

        ncMenuBar.add(editMenu);

        frameMenu.setText(rb.getString("GUI.Menu.Frame")); // NOI18N

        miserlyRadioButtonMenuItem.setText(rb.getString("GUI.Menu.Frame.Miserly")); // NOI18N
        miserlyRadioButtonMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/eye-squint.png"))); // NOI18N
        miserlyRadioButtonMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                miserlyRadioButtonMenuItemItemStateChanged(evt);
            }
        });
        frameMenu.add(miserlyRadioButtonMenuItem);

        economicalRadioButtonMenuItem.setText(rb.getString("GUI.Menu.Frame.Economical")); // NOI18N
        economicalRadioButtonMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/eye-half.png"))); // NOI18N
        economicalRadioButtonMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                economicalRadioButtonMenuItemItemStateChanged(evt);
            }
        });
        frameMenu.add(economicalRadioButtonMenuItem);

        standardRadioButtonMenuItem.setText(rb.getString("GUI.Menu.Frame.Standard")); // NOI18N
        standardRadioButtonMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/eye.png"))); // NOI18N
        standardRadioButtonMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                standardRadioButtonMenuItemItemStateChanged(evt);
            }
        });
        frameMenu.add(standardRadioButtonMenuItem);

        luxuryRadioButtonMenuItem.setText(rb.getString("GUI.Menu.Frame.Luxury")); // NOI18N
        luxuryRadioButtonMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/eye-plus.png"))); // NOI18N
        luxuryRadioButtonMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                luxuryRadioButtonMenuItemItemStateChanged(evt);
            }
        });
        frameMenu.add(luxuryRadioButtonMenuItem);

        maximumRadioButtonMenuItem.setText(rb.getString("GUI.Menu.Frame.Maximum")); // NOI18N
        maximumRadioButtonMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/arrow-out.png"))); // NOI18N
        maximumRadioButtonMenuItem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                maximumRadioButtonMenuItemItemStateChanged(evt);
            }
        });
        frameMenu.add(maximumRadioButtonMenuItem);

        ncMenuBar.add(frameMenu);

        navigationMenu.setText(rb.getString("GUI.General.Navigate")); // NOI18N

        searchMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        searchMenu.setText(rb.getString("GUI.Menu.Find")); // NOI18N

        searchSourceMenuItem.setText(rb.getString("GUI.Menu.Find.FindSourceUnit")); // NOI18N
        searchSourceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSourceMenuItemActionPerformed(evt);
            }
        });
        searchMenu.add(searchSourceMenuItem);

        searchTargetMenuItem.setText(rb.getString("GUI.Menu.Find.FindTargetUnit")); // NOI18N
        searchTargetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTargetMenuItemActionPerformed(evt);
            }
        });
        searchMenu.add(searchTargetMenuItem);
        searchMenu.add(jSeparator21);

        searchCategoriesMenuItem.setText(rb.getString("GUI.Menu.Find.FindCategory")); // NOI18N
        searchCategoriesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCategoriesMenuItemActionPerformed(evt);
            }
        });
        searchMenu.add(searchCategoriesMenuItem);

        navigationMenu.add(searchMenu);

        favoritesMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/bookmarks.png"))); // NOI18N
        favoritesMenu.setText(rb.getString("GUI.Menu.Favorites")); // NOI18N

        addFavoriteMenuItem.setText(rb.getString("GUI.Menu.Favorites.Add")); // NOI18N
        addFavoriteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFavoriteMenuItemActionPerformed(evt);
            }
        });
        favoritesMenu.add(addFavoriteMenuItem);

        manageFavoritesMenuItem.setText(rb.getString("GUI.Menu.Favorites.Manage")); // NOI18N
        manageFavoritesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageFavoritesMenuItemActionPerformed(evt);
            }
        });
        favoritesMenu.add(manageFavoritesMenuItem);

        navigationMenu.add(favoritesMenu);

        filterMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/filter.png"))); // NOI18N
        filterMenuItem.setText(rb.getString("GUI.Menu.Filter")); // NOI18N
        filterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(filterMenuItem);
        navigationMenu.add(jSeparator20);

        tablesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/table.png"))); // NOI18N
        tablesMenuItem.setText(rb.getString("GUI.Menu.Service.Tables")); // NOI18N
        tablesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tablesMenuItemActionPerformed(evt);
            }
        });
        navigationMenu.add(tablesMenuItem);

        ncMenuBar.add(navigationMenu);

        ToolsMenu.setText(rb.getString("GUI.General.Tools")); // NOI18N

        calculatorMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/calculator-scientific.png"))); // NOI18N
        calculatorMenuItem.setText(rb.getString("GUI.Menu.Service.Calculator")); // NOI18N
        calculatorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculatorMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(calculatorMenuItem);

        sourceMenuItemCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/calendar.png"))); // NOI18N
        sourceMenuItemCalendar.setText(rb.getString("GUI.General.DatePicker")); // NOI18N
        sourceMenuItemCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemCalendarActionPerformed(evt);
            }
        });
        ToolsMenu.add(sourceMenuItemCalendar);

        timeDialogMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/time.png"))); // NOI18N
        timeDialogMenuItem.setText(rb.getString("GUI.General.TimePicker")); // NOI18N
        timeDialogMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeDialogMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(timeDialogMenuItem);

        dateDiffMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/duration.png"))); // NOI18N
        dateDiffMenuItem.setText(rb.getString("DateDiff.CalculateTimeDifference")); // NOI18N
        dateDiffMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateDiffMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(dateDiffMenuItem);

        randomNumbersMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/dice.png"))); // NOI18N
        randomNumbersMenuItem.setText(rb.getString("GUI.RandomGenerator.DialogTitle")); // NOI18N
        randomNumbersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomNumbersMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(randomNumbersMenuItem);

        sourceMenuItemColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/color.png"))); // NOI18N
        sourceMenuItemColor.setText(rb.getString("GUI.General.ColorPicker")); // NOI18N
        sourceMenuItemColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sourceMenuItemColorActionPerformed(evt);
            }
        });
        ToolsMenu.add(sourceMenuItemColor);

        spellMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/phonetic-alphabet.png"))); // NOI18N
        spellMenuItem.setText(rb.getString("GUI.Menu.Service.Spell")); // NOI18N
        spellMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spellMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(spellMenuItem);

        updateExchangeRatesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/currency.png"))); // NOI18N
        updateExchangeRatesMenuItem.setText(rb.getString("GUI.Rates.Load")); // NOI18N
        updateExchangeRatesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateExchangeRatesMenuItemActionPerformed(evt);
            }
        });
        ToolsMenu.add(updateExchangeRatesMenuItem);

        ncMenuBar.add(ToolsMenu);

        helpMenu.setText(rb.getString("GUI.Menu.Help")); // NOI18N

        helpIndexMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/book-open.png"))); // NOI18N
        helpIndexMenuItem.setText(rb.getString("GUI.Menu.Help.Index")); // NOI18N
        helpIndexMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpIndexMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(helpIndexMenuItem);

        tutorialMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/graduation-hat.png"))); // NOI18N
        tutorialMenuItem.setText(rb.getString("GUI.Menu.Help.Tutorial")); // NOI18N
        tutorialMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutorialMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(tutorialMenuItem);

        newsletterMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/new-text.png"))); // NOI18N
        newsletterMenuItem.setText("Newsletter");
        newsletterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newsletterMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(newsletterMenuItem);

        supportMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/lifebuoy.png"))); // NOI18N
        supportMenuItem.setText(rb.getString("GUI.Menu.Support")); // NOI18N
        supportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supportMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(supportMenuItem);
        helpMenu.add(jSeparator18);

        homepageMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/home.png"))); // NOI18N
        homepageMenuItem.setText(rb.getString("GUI.General.Homepage")); // NOI18N
        homepageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homepageMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(homepageMenuItem);

        helpMenuItemAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/balloon-white-left.png"))); // NOI18N
        helpMenuItemAbout.setText(rb.getString("GUI.Menu.Help.About")); // NOI18N
        helpMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpMenuItemAboutActionPerformed(evt);
            }
        });
        helpMenu.add(helpMenuItemAbout);

        ncMenuBar.add(helpMenu);

        setJMenuBar(ncMenuBar);
        ncMenuBar.getAccessibleContext().setAccessibleName("Menu");
        ncMenuBar.getAccessibleContext().setAccessibleDescription("Menu");

        getAccessibleContext().setAccessibleName("NC Frame");
        getAccessibleContext().setAccessibleDescription("This is the NC");
    }// </editor-fold>//GEN-END:initComponents

    private void randomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomButtonActionPerformed
        RandomGeneratorDialog randomGeneratorDialog = new RandomGeneratorDialog(this);
    }//GEN-LAST:event_randomButtonActionPerformed

    private void updateCenterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCenterMenuItemActionPerformed
        openUpdateCenter();
    }//GEN-LAST:event_updateCenterMenuItemActionPerformed

    private void warnLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_warnLabelMouseClicked
        // Zeitzonen
        if (categoryObject instanceof CategoryTimezones) {
            JOptionPane.showMessageDialog(this,
                    GeneralString.message(rb.getString("Tzdata.Outdated"),
                            getUpdateRecord().getTzdataModule().getInstalledVersion(),
                            getUpdateRecord().getTzdataModule().getLatestKnownVersion()), rb.getString("Tzdata.Title"), JOptionPane.WARNING_MESSAGE);
            if (isNCAdmin) {
                openUpdateCenter();
            } else {
                JOptionPane.showMessageDialog(this,
                        rb.getString("Message.GUI.NoAdminPermissions"),
                        rb.getString("GUI.General.Error"),
                        JOptionPane.ERROR_MESSAGE);
            }
        } else // Wechselkurse
        if (categoryObject instanceof CategoryGeneric) {
            String code = ((CategoryGeneric) categoryObject).getFilename();
            if (code.equalsIgnoreCase("exchange_rates")) {
                int fullDays = (int) ((System.currentTimeMillis() - rates_updated_ms) / 86400000L);
                JOptionPane.showMessageDialog(this,
                        GeneralString.message(rb.getString("ExchangeRates.Outdated"), fullDays),
                        rb.getString("ExchangeRates.WarningTitle"),
                        JOptionPane.WARNING_MESSAGE);

                if (isNCAdmin) {
                    updateExchangeRatesMenuItemActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(this,
                            rb.getString("Message.GUI.NoAdminPermissions"),
                            rb.getString("GUI.General.Error"),
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }//GEN-LAST:event_warnLabelMouseClicked

    private void dateFormatComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFormatComboBoxActionPerformed
        categoryObject.setNumberType(dateFormatComboBox.getSelectedIndex());
        process(NOCOUNT);
    }//GEN-LAST:event_dateFormatComboBoxActionPerformed

    private void targetMenuItemDecimalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemDecimalsActionPerformed
        sciCheckBox.setSelected(false);
        precisionTextField.requestFocus();
    }//GEN-LAST:event_targetMenuItemDecimalsActionPerformed

    private void editUnitsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUnitsMenuItemActionPerformed
        Bookmark bookmark = getCurrentSelectionAsBookmark();
        EditUnits editUnits = new EditUnits(this, false);
        Category category = (Category) categoryBox.getSelectedItem();
        editUnits.setHeader(category.getText());
        editUnits.setBookmark(bookmark);
        editUnits.setFilename(((CategoryGeneric) categoryObject).getFilename());
        editUnits.setVisible(true);
        editUnits.fill();
    }//GEN-LAST:event_editUnitsMenuItemActionPerformed

    private void phoneticAlphabetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneticAlphabetButtonActionPerformed
        spellMenuItemActionPerformed(evt);
    }//GEN-LAST:event_phoneticAlphabetButtonActionPerformed

    private void calculatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculatorButtonActionPerformed
        GUIHelper.openNativeCalculator();
    }//GEN-LAST:event_calculatorButtonActionPerformed

    private void datatablesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datatablesButtonActionPerformed
        tablesMenuItemActionPerformed(null);
    }//GEN-LAST:event_datatablesButtonActionPerformed

    private void datetimeDiffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datetimeDiffButtonActionPerformed
        dateDiffMenuItemActionPerformed(null);
    }//GEN-LAST:event_datetimeDiffButtonActionPerformed

    private void currencyUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currencyUpdateButtonActionPerformed
        updateExchangeRatesMenuItemActionPerformed(null);
    }//GEN-LAST:event_currencyUpdateButtonActionPerformed

    private void calendarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calendarButtonActionPerformed
        menuItemCalendarPressed();
    }//GEN-LAST:event_calendarButtonActionPerformed

    private void searchCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCategoryButtonActionPerformed
        searchCategoriesMenuItemActionPerformed(null);
    }//GEN-LAST:event_searchCategoryButtonActionPerformed

    private void colorToolbarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorToolbarButtonActionPerformed
        menuItemColorPressed();
    }//GEN-LAST:event_colorToolbarButtonActionPerformed

    private void editFavoritesjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editFavoritesjButtonActionPerformed
        manageFavorites();
    }//GEN-LAST:event_editFavoritesjButtonActionPerformed

    private void addFavoritesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFavoritesButtonActionPerformed
        addFavorite();
        manageFavorites();
    }//GEN-LAST:event_addFavoritesButtonActionPerformed

    private void editToggleButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_editToggleButtonStateChanged
        recordPanel.setVisible(editToggleButton.isSelected());
        pack();
    }//GEN-LAST:event_editToggleButtonStateChanged

    private void dateDiffMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateDiffMenuItemActionPerformed
        long[] seconds = {0};
        boolean[] status = {false};
        DateDiffDialog dateDiffDialog = new DateDiffDialog(this, unitRecordCellRenderer, seconds, status);

        if (status[0] == true) { // not cancelled

            if (categoryBox.getSelectedItem() != categoryHashMap.get("zeit")) {
                categoryBox.setSelectedItem(categoryHashMap.get("zeit"));
                categoryBoxItemStateChanged();
            } else {
                int found = findUnitExact(sourceBox, "second (s)", 0, true);
                if (found > -1) {
                    sourceBox.setSelectedIndex(found);
                }
            }

            try {
                setValue(new BigDecimal(String.valueOf(seconds[0]))); // 1.4 compatible constructor
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }//GEN-LAST:event_dateDiffMenuItemActionPerformed

    private void categoryDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryDownButtonActionPerformed
        int now = categoryBox.getSelectedIndex();
        int all = categoryBox.getItemCount();
        if (now + 1 < all) {
            categoryBox.setSelectedIndex(now + 1);
        } else {
            categoryBox.setSelectedIndex(0);
        }
    }//GEN-LAST:event_categoryDownButtonActionPerformed

    private void categoryUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryUpButtonActionPerformed
        int now = categoryBox.getSelectedIndex();
        int all = categoryBox.getItemCount();
        if (now > 0) {
            categoryBox.setSelectedIndex(now - 1);
        } else {
            categoryBox.setSelectedIndex(all - 1);
        }
    }//GEN-LAST:event_categoryUpButtonActionPerformed

    private void fingerprintComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fingerprintComboBoxActionPerformed
        ((CategoryDigitalFingerprints) categoryObject).setNumberType(fingerprintComboBox.getSelectedItem());
        process(NOCOUNT);
    }//GEN-LAST:event_fingerprintComboBoxActionPerformed

    private void numberTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberTypeComboBoxActionPerformed
        categoryObject.setNumberType(numberTypeComboBox.getSelectedIndex());
        process(NOCOUNT);
    }//GEN-LAST:event_numberTypeComboBoxActionPerformed

    private void sourceBoxComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_sourceBoxComponentResized
        GeneralGUI.resizeComboBoxPopup(sourceBox);
    }//GEN-LAST:event_sourceBoxComponentResized

    private void targetBoxComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_targetBoxComponentResized
        GeneralGUI.resizeComboBoxPopup(targetBox);
    }//GEN-LAST:event_targetBoxComponentResized

    private void sourcePopupMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemRedoActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.redo(sourceUndoManager);
    }//GEN-LAST:event_sourcePopupMenuItemRedoActionPerformed

    private void sourcePopupMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemUndoActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.undo(sourceUndoManager);
    }//GEN-LAST:event_sourcePopupMenuItemUndoActionPerformed

    private void sourceMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemRedoActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.redo(sourceUndoManager);
    }//GEN-LAST:event_sourceMenuItemRedoActionPerformed

    private void sourceMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemUndoActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.undo(sourceUndoManager);
    }//GEN-LAST:event_sourceMenuItemUndoActionPerformed

    private void supportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supportMenuItemActionPerformed
        gotoWebpage("support.html");
    }//GEN-LAST:event_supportMenuItemActionPerformed

    private void findTargetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findTargetButtonActionPerformed
        findTargetUnit();
    }//GEN-LAST:event_findTargetButtonActionPerformed

    private void findSourceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findSourceButtonActionPerformed
        findSourceUnit();
    }//GEN-LAST:event_findSourceButtonActionPerformed

    private void reloadCategoriesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadCategoriesMenuItemActionPerformed
        loadResourceBundle();
        registerCategories();
    }//GEN-LAST:event_reloadCategoriesMenuItemActionPerformed

    private void historyMenuItemRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemRedoActionPerformed
        redoMenuItemActionPerformed(evt);
    }//GEN-LAST:event_historyMenuItemRedoActionPerformed

    private void historyMenuItemUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemUndoActionPerformed
        undoMenuItemActionPerformed(evt);
    }//GEN-LAST:event_historyMenuItemUndoActionPerformed

    private void historyPopupMenuItemHTMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyPopupMenuItemHTMLActionPerformed
        htmlMenuItemActionPerformed(evt);
    }//GEN-LAST:event_historyPopupMenuItemHTMLActionPerformed

    private void redoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redoMenuItemActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.redo(historyUndoManager);
    }//GEN-LAST:event_redoMenuItemActionPerformed

    private void undoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuItemActionPerformed
        net.numericalchameleon.util.misc.GUIHelper.undo(historyUndoManager);
    }//GEN-LAST:event_undoMenuItemActionPerformed

    private void manageFavoritesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageFavoritesMenuItemActionPerformed
        manageFavorites();
    }//GEN-LAST:event_manageFavoritesMenuItemActionPerformed

    private void htmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_htmlMenuItemActionPerformed
        boolean add = true;
        if (historyTextArea.getText().contains("<html>")) {
            // html header already there really add?
            add = net.numericalchameleon.util.misc.GUIHelper.showQuestion(this, "", rb.getString("Edit.HTMLTemplateAddAgain"));
        }
        if (add) {
            historyTextArea.insert(HTML_HEADER, 0);
            historyTextArea.append(HTML_FOOTER);
        }
    }//GEN-LAST:event_htmlMenuItemActionPerformed

    private void addFavoriteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFavoriteMenuItemActionPerformed
        addFavorite();
        manageFavorites();
    }//GEN-LAST:event_addFavoriteMenuItemActionPerformed

    private void searchCategoriesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCategoriesMenuItemActionPerformed
        FindDialog findDialog = new FindDialog(this, FindDialog.CATEGORY);
    }//GEN-LAST:event_searchCategoriesMenuItemActionPerformed

    private void searchTargetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTargetMenuItemActionPerformed
        findTargetUnit();
    }//GEN-LAST:event_searchTargetMenuItemActionPerformed

    private void searchSourceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSourceMenuItemActionPerformed
        findSourceUnit();
    }//GEN-LAST:event_searchSourceMenuItemActionPerformed

    private void colorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorButtonActionPerformed
        menuItemColorPressed();
    }//GEN-LAST:event_colorButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menuItemCalendarPressed();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        categoryObject.stopPlaying();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        playNumber();
    }//GEN-LAST:event_playButtonActionPerformed

    private void homepageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homepageMenuItemActionPerformed
        gotoWebpage("index.html");
    }//GEN-LAST:event_homepageMenuItemActionPerformed

    private void tablesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tablesMenuItemActionPerformed
        helpHelper.openHelp("tables.html");
    }//GEN-LAST:event_tablesMenuItemActionPerformed

    private void spellMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spellMenuItemActionPerformed
        PhoneticAlphabetDialog phoneticAlphabetDialog = new PhoneticAlphabetDialog(this);
    }//GEN-LAST:event_spellMenuItemActionPerformed

    private void sourceMenuItemDefaultValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemDefaultValueActionPerformed
        defaultValuePressed();
    }//GEN-LAST:event_sourceMenuItemDefaultValueActionPerformed

    private void sourcePopupMenuDefaultValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuDefaultValueActionPerformed
        defaultValuePressed();
    }//GEN-LAST:event_sourcePopupMenuDefaultValueActionPerformed

    private void sourceMenuItemCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemCalendarActionPerformed
        menuItemCalendarPressed();
    }//GEN-LAST:event_sourceMenuItemCalendarActionPerformed

    private void filterCatIndicatorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterCatIndicatorLabelMouseClicked
        clickOnFilterCategoryIndicator();
    }//GEN-LAST:event_filterCatIndicatorLabelMouseClicked

    private void propsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propsMenuItemActionPerformed
        new ConfigDialog(this).setVisible(true);
        if (propertiesManager.getUserProperties().containsKey("save")) {
            propertiesManager.getUserProperties().remove("save");
            propertiesManager.saveUserProperties();
        }
        if (propertiesManager.getGlobalProperties().containsKey("save")) {
            propertiesManager.getGlobalProperties().remove("save");
            propertiesManager.saveGlobalProperties();
        }
        
        resizeComboBoxPopups = determineResizeComboBoxPopups();
        setToolTips(propertiesManager.getUserProperties().getProperty("tooltips", "true").equals("true"));
        try {
            ToolTipManager.sharedInstance().setInitialDelay(Integer.parseInt(propertiesManager.getUserProperties().getProperty("tooltips.delay", "750")));
        } catch (NumberFormatException nfe) {
        }
        setAlwaysOnTop(propertiesManager.getUserProperties().getProperty("alwaysOnTop", "false").equals("true"));
        if (propertiesManager.getUserProperties().containsKey("restart")) {
            propertiesManager.getUserProperties().remove("restart");
//            Locale.setDefault(new Locale(userProperties.getProperty("language"), userProperties.getProperty("country")));
//            CategoryObject.setDecSep(((java.text.DecimalFormat) (java.text.NumberFormat.getInstance())).getDecimalFormatSymbols().getDecimalSeparator());
//            JColorChooser.setDefaultLocale(Locale.getDefault());
            restart();
        }
    }//GEN-LAST:event_propsMenuItemActionPerformed

    private void targetMenuItemFindUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemFindUnitActionPerformed
        findTargetUnit();
    }//GEN-LAST:event_targetMenuItemFindUnitActionPerformed

    private void targetPopupMenuItemFindUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPopupMenuItemFindUnitActionPerformed
        findTargetUnit();
    }//GEN-LAST:event_targetPopupMenuItemFindUnitActionPerformed

    private void sourcePopupMenuItemFindUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemFindUnitActionPerformed
        findSourceUnit();
    }//GEN-LAST:event_sourcePopupMenuItemFindUnitActionPerformed

    private void sourceMenuItemFindUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemFindUnitActionPerformed
        findSourceUnit();
    }//GEN-LAST:event_sourceMenuItemFindUnitActionPerformed

    private void targetPopupMenuItemStopPlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPopupMenuItemStopPlayingActionPerformed
        categoryObject.stopPlaying();
    }//GEN-LAST:event_targetPopupMenuItemStopPlayingActionPerformed

    private void targetPopupMenuItemPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPopupMenuItemPlayActionPerformed
        playNumber();
    }//GEN-LAST:event_targetPopupMenuItemPlayActionPerformed

    private void targetMenuItemStopPlayingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemStopPlayingActionPerformed
        categoryObject.stopPlaying();
    }//GEN-LAST:event_targetMenuItemStopPlayingActionPerformed

    private void targetBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetBoxActionPerformed
        process(NOCOUNT);
        sourceTextArea.requestFocus();
    }//GEN-LAST:event_targetBoxActionPerformed

    private void categoryBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryBoxActionPerformed
        // do it, if it is not the same as before
        if ((categoryObject != null) && (lastTypeBoxSelectedIndex != categoryBox.getSelectedIndex())) {
            // stop any sound (if there is any sound ;-)
            categoryObject.stopPlaying();
            // save the last index
            lastTypeBoxSelectedIndex = categoryBox.getSelectedIndex();

            // backup the value from the current category
            transferValue = "";
            try {
                transferValue = categoryObject.getTransferValue(sourceBox.getSelectedIndex());
            } catch (Exception e) {
            }
            categoryBoxItemStateChanged();
        }
    }//GEN-LAST:event_categoryBoxActionPerformed

    private void sourceBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceBoxActionPerformed
        process(NOCOUNT);
        sourceTextArea.requestFocus();
    }//GEN-LAST:event_sourceBoxActionPerformed

    private void sciCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sciCheckBoxActionPerformed
        process(NOCOUNT);
    }//GEN-LAST:event_sciCheckBoxActionPerformed

    private void sciCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sciCheckBoxItemStateChanged
        int change = evt.getStateChange();
        if (change == ItemEvent.SELECTED) {
            precisionLabel.setText(rb.getString("GUI.Menu.Target.SignificantFigures"));
            precisionLabel.setToolTipText(rb.getString("GUI.Area.Target.SignificantFigures.ToolTip"));
            precisionTextField.setToolTipText(rb.getString("GUI.Area.Target.SignificantFigures.ToolTip"));
            categoryObject.setScientific(true);
            /*
             * precisionTextField.setText("1000");
             * categoryObject.setPrecision(1000);
             * precisionTextField.setEditable(false);
             * precisionLabel.setEnabled(false);
             * targetMenuItemFigures.setEnabled(false);
             */
        } else if (change == ItemEvent.DESELECTED) {
            precisionLabel.setText(rb.getString("GUI.Menu.Target.PlacesOfDecimals"));
            precisionLabel.setToolTipText(rb.getString("GUI.Area.Target.PlacesOfDecimals.ToolTip"));
            precisionTextField.setToolTipText(rb.getString("GUI.Area.Target.PlacesOfDecimals.ToolTip"));
            categoryObject.setScientific(false);
            /*
             * categoryObject.setPrecision(categoryObject.getPreferredPrecision());
             * precisionTextField.setText(Integer.toString(categoryObject.getPrecision()));
             * precisionTextField.setEditable(true);
             * precisionLabel.setEnabled(true);
             * targetMenuItemFigures.setEnabled(true);
             */
        }
    }//GEN-LAST:event_sciCheckBoxItemStateChanged

    private void targetPopupMenuItemDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPopupMenuItemDefaultActionPerformed
        targetBox.setSelectedIndex(categoryObject.getTargetDefault());
    }//GEN-LAST:event_targetPopupMenuItemDefaultActionPerformed

    private void sourcePopupMenuItemDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemDefaultActionPerformed
        sourceBox.setSelectedIndex(categoryObject.getSourceDefault());
    }//GEN-LAST:event_sourcePopupMenuItemDefaultActionPerformed

    private void targetMenuItemDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemDefaultActionPerformed
        targetBox.setSelectedIndex(categoryObject.getTargetDefault());
    }//GEN-LAST:event_targetMenuItemDefaultActionPerformed

    private void sourceMenuItemDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemDefaultActionPerformed
        sourceBox.setSelectedIndex(categoryObject.getSourceDefault());
    }//GEN-LAST:event_sourceMenuItemDefaultActionPerformed

    private void recordMenuItemFontsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemFontsizeActionPerformed
        Font font = historyTextArea.getFont();
        String input;
        boolean end = false;
        while (!end) {
            input = JOptionPane.showInputDialog(this, rb.getString("GUI.Menu.Records.Fontsize"), Float.toString(font.getSize2D()));
            if (input != null) {
                try {
                    historyTextArea.setFont(font.deriveFont(Float.valueOf(input)));
                    end = true;
                    propertiesManager.getUserProperties().setProperty("fontsize", input);
                } catch (NumberFormatException e) {
                    System.err.println(e.toString());
                }
            } else {
                end = true;
            }
        }
    }//GEN-LAST:event_recordMenuItemFontsizeActionPerformed

    private void historyMenuFontSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuFontSizeActionPerformed
        recordMenuItemFontsizeActionPerformed(evt);
    }//GEN-LAST:event_historyMenuFontSizeActionPerformed

    private void historyMenuItemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemLoadActionPerformed
        loadMenuItemActionPerformed(evt);
    }//GEN-LAST:event_historyMenuItemLoadActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed

        if (fc_load == null) {
            fc_load = new JFileChooser();
            fc_load.removeChoosableFileFilter(fc_load.getAcceptAllFileFilter());
            if (utf8 == null) {
                utf8 = new ExampleFileFilter("txt", "Unicode, UTF-8");
            }
            if (utf16 == null) {
                utf16 = new ExampleFileFilter("txt", "Unicode, UTF-16");
            }
            if (iso8859 == null) {
                iso8859 = new ExampleFileFilter("txt", "Text, ISO8859_1");
            }
            fc_load.addChoosableFileFilter(iso8859);
            fc_load.addChoosableFileFilter(utf8);
            fc_load.addChoosableFileFilter(utf16);
            fc_load.setFileFilter(utf8);
        }
        boolean error;
        int returnVal;

        do {
            error = false;
            returnVal = fc_load.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String encoding;
                ExampleFileFilter ff = (ExampleFileFilter) fc_load.getFileFilter();
                if (ff.equals(utf8)) {
                    encoding = "UTF-8";
                } else if (ff.equals(utf16)) {
                    encoding = "UTF-16";
                } else {
                    encoding = "ISO8859_1";
                }

                File selFile = fc_load.getSelectedFile();
                FileInputStream fis;
                try {
                    fis = new FileInputStream(selFile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis, encoding));
                    String thisLine;

                    historyTextArea.setText("");
                    while ((thisLine = br.readLine()) != null) {
                        historyTextArea.append(thisLine);
                        historyTextArea.append(ProgConstants.NEWLINE);
                    }

                    br.close();
                    fis.close();

                } catch (IOException e) {
                    error = true;
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (returnVal == JFileChooser.APPROVE_OPTION && error);

    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void targetMenuItemPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemPlayActionPerformed
        playNumber();
        /*try {
            categoryObject.play((Unit)targetBox.getSelectedItem());
        } catch (Exception e) {
            messageLightError(e.getMessage());
        }*/
    }//GEN-LAST:event_targetMenuItemPlayActionPerformed

    private void sourceMenuItemColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemColorActionPerformed
        menuItemColorPressed();
    }//GEN-LAST:event_sourceMenuItemColorActionPerformed

    private void historyMenuItemSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemSaveActionPerformed
        saveButtonActionPerformed(evt);
    }//GEN-LAST:event_historyMenuItemSaveActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveButtonActionPerformed(evt);
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        fc_save = newSingleton_JFileChooserSave();

        int returnVal = fc_save.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selFile = fc_save.getSelectedFile();
            boolean overwrite = true;
            if (selFile.exists() && selFile.isFile()) {
                overwrite = net.numericalchameleon.util.misc.GUIHelper.showQuestion(this, "", GeneralString.message(rb.getString("Error.FileAlreadyExists"), selFile.getPath()));
            }
            if (overwrite) {
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(selFile);
                    ExampleFileFilter ff = (ExampleFileFilter) fc_save.getFileFilter();
                    String encoding = encodingFromFileFilter(ff);

                    BufferedWriter myOutput = new BufferedWriter(new OutputStreamWriter(fos, encoding));
                    if (ff.equals(html)) {
                        // check, if HTML-Header/Footer is there
                        boolean add = false;
                        if (!historyTextArea.getText().startsWith("<html>")) {
                            // html header already there really add?
                            add = net.numericalchameleon.util.misc.GUIHelper.showQuestion(this, "", rb.getString("Edit.HTMLTemplateNotFound"));
                        }
                        if (add) {
                            historyTextArea.insert(HTML_HEADER, 0);
                            historyTextArea.append(HTML_FOOTER);
                        }
                    }

                    String content = historyTextArea.getText();
                    if (ff.equals(ics)) {
                        content = GeneralString.replaceAllStrings(content, "\n", "\r\n");
                    }
                    myOutput.write(content);
                    myOutput.close();
                    fos.close();

                    messageOk(rb.getString("Message.GUI.FileWritten"));
                } catch (IOException e) {
                    messageLightError(e.getMessage());
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_saveButtonActionPerformed

  private void recordMenuItemMakeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemMakeListActionPerformed
      makeListButtonActionPerformed(evt);
  }//GEN-LAST:event_recordMenuItemMakeListActionPerformed

  private void historyPopupMenuItemMakeListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyPopupMenuItemMakeListActionPerformed
      makeListButtonActionPerformed(evt);
  }//GEN-LAST:event_historyPopupMenuItemMakeListActionPerformed

  private void historyPopupMenuenuItemFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyPopupMenuenuItemFormatActionPerformed
      recordMenuItemFormatActionPerformed(evt);
  }//GEN-LAST:event_historyPopupMenuenuItemFormatActionPerformed

  private void recordMenuItemFormatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemFormatActionPerformed
      String input = propertiesManager.getUserProperties().getProperty("notice_form", DEFAULT_NOTICE_FORM);

      ArrayList al = new ArrayList();
      int i = 0;
      while (propertiesManager.getGlobalProperties().getProperty("format_" + i) != null) {
          al.add(propertiesManager.getGlobalProperties().getProperty("format_" + i, DEFAULT_NOTICE_FORM));
          i++;
      }
      i = 0;
      while (propertiesManager.getUserProperties().getProperty("format_" + i) != null) {
          al.add(propertiesManager.getUserProperties().getProperty("format_" + i, DEFAULT_NOTICE_FORM));
          i++;
      }

      String entered = (String) JOptionPane.showInputDialog(this, rb.getString("GUI.Menu.Records.Format"), rb.getString("GUI.Menu.Records.Format"), JOptionPane.QUESTION_MESSAGE, null, al.toArray(), input);
      if (entered != null) {
          propertiesManager.getUserProperties().setProperty("notice_form", entered);
          propertiesManager.saveUserProperties();
      }
  }//GEN-LAST:event_recordMenuItemFormatActionPerformed

    private void makeListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeListButtonActionPerformed
        multipleNoticeCluster.reset();

        // if category "Calendar (year)" is selected, then init list values with current year
        // and make the "show list format" visible
        if (categoryObject instanceof CategoryHolidays) {
            multipleNoticeCluster.setStartValue(new BigDecimal(categoryObject.getInitialValue()));
            multipleNoticeCluster.setEndValue(multipleNoticeCluster.getStartValue());
            multipleNoticeCluster.setListFormatVisible(true);
        }

        if (categoryObject instanceof CategoryTimezones) {
            multipleNoticeCluster.setStartValue(new BigDecimal(CategoryTimezones.getInternationalTimeValue()));
            multipleNoticeCluster.setEndValue(multipleNoticeCluster.getStartValue());
        }

        if (categoryObject instanceof CategoryCalendarSystems
                || categoryObject instanceof CategoryDateQueries
                || categoryObject instanceof CategoryDateFormats) {
            try {
                multipleNoticeCluster.setStartValue(new BigDecimal(categoryObject.getTransferValue((Unit) sourceBox.getSelectedItem())));
                multipleNoticeCluster.setEndValue(multipleNoticeCluster.getStartValue());
            } catch (Exception e) {
            }
        }

        ListGeneratorDialog listGeneratorDialog = new ListGeneratorDialog(this, true, multipleNoticeCluster);

        if (multipleNoticeCluster.isSuccess()
                && (multipleNoticeCluster.getStartValue().compareTo(multipleNoticeCluster.getEndValue()) <= 0)
                && (((multipleNoticeCluster.getStartValue().compareTo(multipleNoticeCluster.getEndValue()) < 0)
                && (multipleNoticeCluster.getStepValue().compareTo(new BigDecimal(BigInteger.ZERO)) > 0))
                || (multipleNoticeCluster.getStartValue().compareTo(multipleNoticeCluster.getEndValue()) == 0))) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

            boolean specialFormat = multipleNoticeCluster.getListFormat() != null;
            int headerPos = historyTextArea.getCaretPosition();
            int count;
            if (multipleNoticeCluster.getTargets() == ListGeneratorCluster.ALL_TARGETS) {
                dontPanic();
                setState(Frame.ICONIFIED);
                int save = targetBox.getSelectedIndex();
                for (int i = 0; i < targetBox.getItemCount(); i++) {
                    targetBox.setSelectedIndex(i);
                    count = processList(multipleNoticeCluster);
                    if (!specialFormat && count > 1) {
                        historyTextArea.append("\n");
                    }
                }
                targetBox.setSelectedIndex(save);
                setState(Frame.NORMAL);
            } else if (multipleNoticeCluster.getTargets() == ListGeneratorCluster.MULTIPLE_TARGETS) {
                int save = targetBox.getSelectedIndex();
                // what flag?
                String flag = ((Unit) targetBox.getSelectedItem()).getIcon();
                for (int i = 0; i < targetBox.getItemCount(); i++) {
                    if (((Unit) targetBox.getItemAt(i)).getIcon().equals(flag)) {
                        targetBox.setSelectedIndex(i);
                        count = processList(multipleNoticeCluster);
                        if (!specialFormat && count > 1) {
                            historyTextArea.append("\n");
                        }
                    }
                }
                targetBox.setSelectedIndex(save);
            } else {
                processList(multipleNoticeCluster);
            }

            // add both header and footer if a special output format was requested
            if (specialFormat) {
                // header
                historyTextArea.insert(multipleNoticeCluster.getListFormat().getHeader(), headerPos);
                // footer
                historyTextArea.append(multipleNoticeCluster.getListFormat().getFooter());
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        }
        // else do nothing

    }//GEN-LAST:event_makeListButtonActionPerformed

    private void maximumRadioButtonMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_maximumRadioButtonMenuItemItemStateChanged
        luxuryFrame(-1);
    }//GEN-LAST:event_maximumRadioButtonMenuItemItemStateChanged

    private void reloadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadMenuItemActionPerformed
        categoryBoxItemStateChanged();
    }//GEN-LAST:event_reloadMenuItemActionPerformed

    private void reloadUnitsItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reloadUnitsItemActionPerformed
        categoryBoxItemStateChanged();
    }//GEN-LAST:event_reloadUnitsItemActionPerformed

    private void countUnitsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countUnitsMenuItemActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            String filename = "/data/lists/categories.list";
            InputStream is = getClass().getResourceAsStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            Summary summary = new Summary();
            summary.setUnitsSelectable(latestCount);

            StringTokenizer st;
            String thisLine;
            while ((thisLine = br.readLine()) != null) {
                if (!thisLine.startsWith("#") && !thisLine.equals("")) {

                    st = new StringTokenizer(thisLine, ":");
                    String code = st.nextToken();

                    Category category = categoryHashMap.get(code);
                    CategoryObject co = constructCategoryObjectFromCategory(category);
                    if (co != null) {

                        // update summary
                        int supportedUnits = Math.max(co.getSourceUnits().size(), co.getTargetUnits().size());
                        summary.addCategoriesComplete(1);
                        summary.addUnitsComplete(supportedUnits);
                        if (groupHashSet.isEmpty() || groupHashSet.contains(code)) {
                            summary.addCategoriesFiltered(1);
                            summary.addUnitsFiltered(supportedUnits);
                        }
                    }

                }
            }
            br.close();

            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            SummaryDialog summaryDialog = new SummaryDialog(this, true, summary);

        } catch (IOException e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_countUnitsMenuItemActionPerformed

    @Override
    public boolean isOnlineHelpPreferred() {
        return propertiesManager.getUserProperties().getProperty("help.online", "false").equals("true");
    }


    private void helpIndexMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpIndexMenuItemActionPerformed
        helpHelper.openHelp("index.html");
    }//GEN-LAST:event_helpIndexMenuItemActionPerformed


    private void updateExchangeRatesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateExchangeRatesMenuItemActionPerformed
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setGlobalProperties(propertiesManager.getGlobalProperties());
        exchangeRates.setPreferredFilter(propertiesManager.getGlobalProperties().getProperty("rates_preferred", ""));
        exchangeRates.setPreferredDirectory(propertiesManager.getGlobalProperties().getProperty("rates_preferred_dir", null));
        ExchangeRatesDialog exchangeRatesDialog = new ExchangeRatesDialog(this, exchangeRates);

        if (exchangeRates.success()) {
            try {
                exchangeRates.writeToDisk();

                // update all exchange rates related properties
                PropertyTools.updateProperties(propertiesManager.getGlobalProperties(), exchangeRates.getGlobalProperties());
                propertiesManager.saveGlobalProperties();
                PropertyTools.updateProperties(propertiesManager.getUpdateProperties(), exchangeRates.getUpdateProperties());
                propertiesManager.saveUpdateProperties();

            } catch (Exception e) {
                System.err.println(e);
            }

            // modify the exchange rates category record
            Category category = categoryHashMap.get("exchange_rates");

            String newString = GeneralString.replaceString(exchange_rates_description, "$RATES_DATE", exchangeRates.getDate());
            newString = GeneralString.replaceString(newString, "$RATES_NAME", exchangeRates.getName());
            category.setString(newString);
            category.setLogic(exchangeRates.getLogic());

            // reset the filter, if exchange rates are not loaded
            if (!groupHashSet.contains("exchange_rates")) {
                clickOnFilterCategoryIndicator();
            }
            categoryBox.setSelectedItem(categoryHashMap.get("exchange_rates"));
            //     categoryBox.getModel().setSelectedItem(category);

            transferValue = "1";
            categoryBoxItemStateChanged();
        }
        messageOk(GeneralString.message(rb.getString("GUI.Rates.count"), exchangeRates.count()), true);
    }//GEN-LAST:event_updateExchangeRatesMenuItemActionPerformed

    private void miserlyRadioButtonMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_miserlyRadioButtonMenuItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            luxuryFrame(3);
        }
    }//GEN-LAST:event_miserlyRadioButtonMenuItemItemStateChanged

    private void economicalRadioButtonMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_economicalRadioButtonMenuItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            luxuryFrame(2);
        }
    }//GEN-LAST:event_economicalRadioButtonMenuItemItemStateChanged

    private void standardRadioButtonMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_standardRadioButtonMenuItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            luxuryFrame(1);
        }
    }//GEN-LAST:event_standardRadioButtonMenuItemItemStateChanged

    private void luxuryRadioButtonMenuItemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_luxuryRadioButtonMenuItemItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            luxuryFrame(0);
        }
    }//GEN-LAST:event_luxuryRadioButtonMenuItemItemStateChanged

  private void targetMenuItemFiguresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemFiguresActionPerformed
      sciCheckBox.setSelected(true);
      precisionTextField.requestFocus();
  }//GEN-LAST:event_targetMenuItemFiguresActionPerformed

  private void historyPopupMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyPopupMenuItemNewActionPerformed
      editorNoticeButtonActionPerformed(evt);
  }//GEN-LAST:event_historyPopupMenuItemNewActionPerformed

  private void recordMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemNewActionPerformed
      editorNoticeButtonActionPerformed(evt);
  }//GEN-LAST:event_recordMenuItemNewActionPerformed

  private void editorNoticeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorNoticeButtonActionPerformed
      performNotice();
      sourceTextArea.requestFocus();
  }//GEN-LAST:event_editorNoticeButtonActionPerformed

  private void recordMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemPasteActionPerformed
      historyPasteButtonActionPerformed(evt);
  }//GEN-LAST:event_recordMenuItemPasteActionPerformed

  private void recordMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemCopyActionPerformed
      historyCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_recordMenuItemCopyActionPerformed

  private void sourceMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemPasteActionPerformed
      sourcePasteButtonActionPerformed(evt);
  }//GEN-LAST:event_sourceMenuItemPasteActionPerformed

  private void sourceMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemCopyActionPerformed
      sourceCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_sourceMenuItemCopyActionPerformed

  private void sourceMenuItemPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemPlusActionPerformed
      sourcePlusButtonActionPerformed(evt);
  }//GEN-LAST:event_sourceMenuItemPlusActionPerformed

  private void sourceMenuItemMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemMinusActionPerformed
      sourceMinusButtonActionPerformed(evt);
  }//GEN-LAST:event_sourceMenuItemMinusActionPerformed

  private void programMenuItemSwapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_programMenuItemSwapActionPerformed
      swapButtonActionPerformed(evt);
  }//GEN-LAST:event_programMenuItemSwapActionPerformed

  private void recordMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recordMenuItemClearActionPerformed
      historyClearButtonActionPerformed(evt);
  }//GEN-LAST:event_recordMenuItemClearActionPerformed

  private void targetMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetMenuItemCopyActionPerformed
      targetCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_targetMenuItemCopyActionPerformed

  private void sourceMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMenuItemClearActionPerformed
      sourceClearButtonActionPerformed(evt);
  }//GEN-LAST:event_sourceMenuItemClearActionPerformed

  private void targetCopyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetCopyButtonActionPerformed
      textAreaCopy(targetTextArea);
  }//GEN-LAST:event_targetCopyButtonActionPerformed

  private void historyMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemPasteActionPerformed
      historyPasteButtonActionPerformed(evt);
  }//GEN-LAST:event_historyMenuItemPasteActionPerformed

  private void historyMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemCopyActionPerformed
      historyCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_historyMenuItemCopyActionPerformed

  private void historyAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyAreaMouseClicked
      checkPopupMenu(evt, historyPopupMenu);
  }//GEN-LAST:event_historyAreaMouseClicked

  private void historyAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyAreaMouseReleased
      checkPopupMenu(evt, historyPopupMenu);
  }//GEN-LAST:event_historyAreaMouseReleased

  private void historyAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_historyAreaMousePressed
      checkPopupMenu(evt, historyPopupMenu);
  }//GEN-LAST:event_historyAreaMousePressed

  private void historyMenuItemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyMenuItemCutActionPerformed
      historyClearButtonActionPerformed(evt);
  }//GEN-LAST:event_historyMenuItemCutActionPerformed

  private void sourcePopupMenuItemMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemMinusActionPerformed
      sourceMinusButtonActionPerformed(evt);
  }//GEN-LAST:event_sourcePopupMenuItemMinusActionPerformed

  private void sourcePopupMenuItemPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemPlusActionPerformed
      sourcePlusButtonActionPerformed(evt);
  }//GEN-LAST:event_sourcePopupMenuItemPlusActionPerformed

  private void sourcePasteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePasteButtonActionPerformed
      sourceTextAreaPaste();
  }//GEN-LAST:event_sourcePasteButtonActionPerformed

  private void sourceCopyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceCopyButtonActionPerformed
      textAreaCopy(sourceTextArea);
  }//GEN-LAST:event_sourceCopyButtonActionPerformed

  private void sourceClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceClearButtonActionPerformed
      textAreaCopy(sourceTextArea);
      textAreaClear(sourceTextArea);
  }//GEN-LAST:event_sourceClearButtonActionPerformed

  private void targetTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_targetTextAreaMouseClicked
      checkPopupMenu(evt, targetPopupMenu);
  }//GEN-LAST:event_targetTextAreaMouseClicked

  private void targetTextAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_targetTextAreaMouseReleased
      checkPopupMenu(evt, targetPopupMenu);
  }//GEN-LAST:event_targetTextAreaMouseReleased

  private void targetTextAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_targetTextAreaMousePressed
      checkPopupMenu(evt, targetPopupMenu);
  }//GEN-LAST:event_targetTextAreaMousePressed

  private void targetPopupMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPopupMenuItemCopyActionPerformed
      targetCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_targetPopupMenuItemCopyActionPerformed

  private void sourcePopupMenuItemPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemPasteActionPerformed
      sourcePasteButtonActionPerformed(evt);
  }//GEN-LAST:event_sourcePopupMenuItemPasteActionPerformed

  private void sourcePopupMenuItemCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemCutActionPerformed
      sourceClearButtonActionPerformed(evt);
  }//GEN-LAST:event_sourcePopupMenuItemCutActionPerformed

  private void sourcePopupMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePopupMenuItemCopyActionPerformed
      sourceCopyButtonActionPerformed(evt);
  }//GEN-LAST:event_sourcePopupMenuItemCopyActionPerformed

  private void sourceTextAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sourceTextAreaMouseReleased
      checkPopupMenu(evt, sourcePopupMenu);
  }//GEN-LAST:event_sourceTextAreaMouseReleased

  private void sourceTextAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sourceTextAreaMousePressed
      checkPopupMenu(evt, sourcePopupMenu);
  }//GEN-LAST:event_sourceTextAreaMousePressed

  private void sourceTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sourceTextAreaMouseClicked
      checkPopupMenu(evt, sourcePopupMenu);
  }//GEN-LAST:event_sourceTextAreaMouseClicked

  private void sourceNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceNextButtonActionPerformed
      int now = sourceBox.getSelectedIndex();
      int all = sourceBox.getItemCount();
      if (now + 1 < all) {
          sourceBox.setSelectedIndex(now + 1);
      } else {
          sourceBox.setSelectedIndex(0);
      }
  }//GEN-LAST:event_sourceNextButtonActionPerformed

  private void restartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartItemActionPerformed
      restart();
  }//GEN-LAST:event_restartItemActionPerformed

  private void exitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitItemActionPerformed
      saveExit();
  }//GEN-LAST:event_exitItemActionPerformed

  private void helpMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpMenuItemAboutActionPerformed
      showAboutDialog();
  }//GEN-LAST:event_helpMenuItemAboutActionPerformed

  private void sourceBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceBackButtonActionPerformed
      int now = sourceBox.getSelectedIndex();
      int all = sourceBox.getItemCount();
      if (now > 0) {
          sourceBox.setSelectedIndex(now - 1);
      } else {
          sourceBox.setSelectedIndex(all - 1);
      }
  }//GEN-LAST:event_sourceBackButtonActionPerformed

  private void historyPasteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyPasteButtonActionPerformed
      historyTextAreaPaste();
  }//GEN-LAST:event_historyPasteButtonActionPerformed

  private void precisionTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_precisionTextFieldKeyReleased
      int code = evt.getKeyCode();
      boolean bool = true;
      for (int i = 0; i < GeneralGUI.noTextChangeKeyEvents.length; i++) {
          if (code == GeneralGUI.noTextChangeKeyEvents[i]) {
              bool = false;
              break;
          }
      }
      if (bool) {
          try {
              if (precisionTextField.getText().length() > 4) {
                  throw new Exception();
              }
              int i = Integer.parseInt(precisionTextField.getText());
              if (i > 1000) {
                  throw new Exception();
              }
              categoryObject.setPrecision(i);
              precisionerror = false;
              process(NOCOUNT);
          } catch (Exception e) {
              messageError(rb.getString(ERROR_GUI_PRECISION));
              precisionerror = true;
          }
      }
  }//GEN-LAST:event_precisionTextFieldKeyReleased

  private void historyCopyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyCopyButtonActionPerformed
      textAreaCopy(historyTextArea);
  }//GEN-LAST:event_historyCopyButtonActionPerformed

  private void historyClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyClearButtonActionPerformed
      textAreaCopy(historyTextArea);
      textAreaClear(historyTextArea);
  }//GEN-LAST:event_historyClearButtonActionPerformed

  private void sourceTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sourceTextAreaKeyReleased
      int code = evt.getKeyCode();
      boolean b = true;
      for (int i = 0; i < GeneralGUI.noTextChangeKeyEvents.length; i++) {
          if (code == GeneralGUI.noTextChangeKeyEvents[i]) {
              b = false;
              break;
          }
      }
      if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_CONTROL || code == KeyEvent.VK_PASTE) { // Ctrl+V for inserting
          sourceTextArea.setText(sourceTextArea.getText().replace('\n', ' ').replace('\r', ' ').trim());
      }
      if (b) {
          process(NOCOUNT);
      }
  }//GEN-LAST:event_sourceTextAreaKeyReleased

  private void sourceMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourceMinusButtonActionPerformed
      try {
          addValue(MINUS_ONE);
      } catch (Exception e) {
          categoryObject.setInput(sourceTextArea.getText());
          messageError(e.getMessage());
      }
      sourceTextArea.requestFocus();
  }//GEN-LAST:event_sourceMinusButtonActionPerformed

  private void sourcePlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sourcePlusButtonActionPerformed
      try {
          addValue(BigDecimal.ONE);
      } catch (Exception e) {
          categoryObject.setInput(sourceTextArea.getText());
          messageError(e.getMessage());
      }
      sourceTextArea.requestFocus();
  }//GEN-LAST:event_sourcePlusButtonActionPerformed

  private void targetBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetBackButtonActionPerformed
      int now = targetBox.getSelectedIndex();
      int all = targetBox.getItemCount();
      if (now > 0) {
          targetBox.setSelectedIndex(now - 1);
      } else {
          targetBox.setSelectedIndex(all - 1);
      }
  }//GEN-LAST:event_targetBackButtonActionPerformed

  private void targetNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetNextButtonActionPerformed
      int now = targetBox.getSelectedIndex();
      int all = targetBox.getItemCount();
      if (now + 1 < all) {
          targetBox.setSelectedIndex(now + 1);
      } else {
          targetBox.setSelectedIndex(0);
      }
  }//GEN-LAST:event_targetNextButtonActionPerformed

  private void swapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapButtonActionPerformed
      // source should be target, target should be source
      if (!categoryObject.acceptEmptyStrings()) {
          sourceTextArea.setText(targetTextArea.getText());
      }

      int indexTemp = sourceBox.getSelectedIndex();
      sourceBox.setSelectedIndex(targetBox.getSelectedIndex());
      targetBox.setSelectedIndex(indexTemp);
      enableNavigationDependentOnEntryCount();
      process(NOCOUNT);
      sourceTextArea.setCaretPosition(0);
      targetTextArea.setCaretPosition(0);
      sourceTextArea.requestFocus();
  }//GEN-LAST:event_swapButtonActionPerformed

  private void calculatorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculatorMenuItemActionPerformed
      calculatorButtonActionPerformed(evt);
  }//GEN-LAST:event_calculatorMenuItemActionPerformed

  private void googleMapsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_googleMapsButtonActionPerformed
      try {
          //String latlong = categoryObject.getOutput(sourceBox.getSelectedIndex(), CategoryCoordinates.cLATLNGDEC);
          String latlong = categoryObject.getOutput((Unit) sourceBox.getSelectedItem(), new Unit(CategoryCoordinates.cLATLNGDEC));
          googleMaps(latlong);
      } catch (Exception e) {
          System.err.println(e);
      }
  }//GEN-LAST:event_googleMapsButtonActionPerformed

  private void unicodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unicodeButtonActionPerformed
      try {
          GUIHelper.openInBrowser("http://www.unicode.org/charts/index.html");
      } catch (IOException | URISyntaxException e) {
          System.err.println(e);
      }
  }//GEN-LAST:event_unicodeButtonActionPerformed

    /*
     * wikipediaTzButtonAction try { String tzid =
     * ((CategoryTimezones)categoryObject).getTzId(targetBox.getSelectedIndex());
     * GUIHelper.openInBrowser("http://en.wikipedia.org/wiki/"+tzid); } catch
     * (Exception e) { System.err.println(e); }
     */
  private void groupComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_groupComboBoxItemStateChanged
      Object item = evt.getItem();
      if (evt.getStateChange() == ItemEvent.SELECTED) {
          groupComboBoxItemSelected(item);
      }
  }//GEN-LAST:event_groupComboBoxItemStateChanged

  private void categoryHelpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryHelpButtonActionPerformed
      String id = ((Category) categoryBox.getSelectedItem()).getName().toLowerCase(Locale.US);
      helpHelper.openHelpForCategory(id);
  }//GEN-LAST:event_categoryHelpButtonActionPerformed


  private void randomNumbersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomNumbersMenuItemActionPerformed
      RandomGeneratorDialog randomGeneratorDialog = new RandomGeneratorDialog(this);
  }//GEN-LAST:event_randomNumbersMenuItemActionPerformed

  private void primaryFavoriteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryFavoriteMenuItemActionPerformed
      Bookmark bookmark = getCurrentSelectionAsBookmark();
      propertiesManager.getUserProperties().setProperty("lastCatAndUnit", bookmark.getBookmarkAsString());
      propertiesManager.getUserProperties().setProperty("backupCatAndUnit", "false");
      propertiesManager.saveUserProperties();
  }//GEN-LAST:event_primaryFavoriteMenuItemActionPerformed

  private void filterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterMenuItemActionPerformed
      groupComboBox.setPopupVisible(true);
  }//GEN-LAST:event_filterMenuItemActionPerformed

    private void tutorialMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutorialMenuItemActionPerformed
        gotoWebpage("tutorials.html");
    }//GEN-LAST:event_tutorialMenuItemActionPerformed

    private void timePanelTimeChooserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timePanelTimeChooserButtonActionPerformed
        openTimeChooserDialog();
    }//GEN-LAST:event_timePanelTimeChooserButtonActionPerformed

    private void timeDialogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeDialogButtonActionPerformed
        openTimeChooserDialog();
    }//GEN-LAST:event_timeDialogButtonActionPerformed

    private void timeDialogMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeDialogMenuItemActionPerformed
        openTimeChooserDialog();
    }//GEN-LAST:event_timeDialogMenuItemActionPerformed

    private void playTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playTimeButtonActionPerformed
        playNumber();
    }//GEN-LAST:event_playTimeButtonActionPerformed

    private void stopPlayingTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopPlayingTimeButtonActionPerformed
        categoryObject.stopPlaying();
    }//GEN-LAST:event_stopPlayingTimeButtonActionPerformed

    private void defaultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultButtonActionPerformed
        defaultValuePressed();
        targetBox.setSelectedIndex(categoryObject.getTargetDefault());
    }//GEN-LAST:event_defaultButtonActionPerformed

    private void tzDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tzDateButtonActionPerformed

        GregorianCalendar calendar = new GregorianCalendar();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        dateFormat.setTimeZone(TimeZone.getDefault());
        dateFormat.setLenient(false);
        try {
            calendar.setTime(dateFormat.parse(sourceTextArea.getText()));
        } catch (ParseException e) {
            System.err.println(e);
        }

        CalendarChooserPayload payload = new CalendarChooserPayload(calendar, TimeZone.getDefault());
        CalendarChooserDialog calendarChooserDialog = new CalendarChooserDialog(this, payload);
        if (!payload.isCancelled()) { // not cancelled

            try {
                String value
                        = String.format("%04d%02d%02d%02d%02d%02d",
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1,
                                calendar.get(Calendar.DATE),
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                calendar.get(Calendar.SECOND));

                setValue(new BigDecimal(value));
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }//GEN-LAST:event_tzDateButtonActionPerformed

    private void tzTimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tzTimeButtonActionPerformed

        GregorianCalendar calendar = new GregorianCalendar();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        dateFormat.setTimeZone(TimeZone.getDefault());
        dateFormat.setLenient(false);
        try {
            calendar.setTime(dateFormat.parse(sourceTextArea.getText()));
        } catch (ParseException e) {
            System.err.println(e);
        }

        TimeChooserPayload payload = new TimeChooserPayload();
        payload.setCalendar(calendar);
        TimeChooserDialog timeChooserDialog = new TimeChooserDialog(this, payload);
        if (!payload.isCancelled()) { // not cancelled

            try {
                String value
                        = String.format("%04d%02d%02d%02d%02d%02d",
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH) + 1,
                                calendar.get(Calendar.DATE),
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                calendar.get(Calendar.SECOND));

                setValue(new BigDecimal(value));
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }

    }//GEN-LAST:event_tzTimeButtonActionPerformed

    private void newsletterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newsletterMenuItemActionPerformed
        gotoWebpage("newsletter.html");
    }//GEN-LAST:event_newsletterMenuItemActionPerformed

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private java.awt.CardLayout cardLayout;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ToolsMenu;
    private javax.swing.JMenuItem addFavoriteMenuItem;
    private javax.swing.JButton addFavoritesButton;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel calPanel;
    private javax.swing.JButton calculatorButton;
    private javax.swing.JMenuItem calculatorMenuItem;
    private javax.swing.JButton calendarButton;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JMenu categoriesMenu;
    private javax.swing.JComboBox categoryBox;
    private javax.swing.JButton categoryDownButton;
    private javax.swing.JButton categoryHelpButton;
    private javax.swing.JButton categoryUpButton;
    private javax.swing.JPanel colPanel;
    private javax.swing.JButton colorButton;
    private javax.swing.JButton colorToolbarButton;
    private javax.swing.JLabel conversionLabel;
    private javax.swing.JPanel converterPanel;
    private javax.swing.JMenuItem countUnitsMenuItem;
    private javax.swing.JButton currencyUpdateButton;
    private javax.swing.JButton datatablesButton;
    private javax.swing.JMenuItem dateDiffMenuItem;
    private javax.swing.JComboBox dateFormatComboBox;
    private javax.swing.JPanel datePanel;
    private javax.swing.JButton datetimeDiffButton;
    private javax.swing.JButton defaultButton;
    private javax.swing.JLabel deltaLabel;
    private javax.swing.JRadioButtonMenuItem economicalRadioButtonMenuItem;
    private javax.swing.JMenuItem editCategoriesMenuItem;
    private javax.swing.JButton editFavoritesjButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JToggleButton editToggleButton;
    private javax.swing.JPanel editTogglePanel;
    private javax.swing.JMenuItem editUnitsMenuItem;
    private javax.swing.JButton editorNoticeButton;
    private javax.swing.JPanel emptyPanel;
    private javax.swing.JMenuItem exitItem;
    private javax.swing.JMenu favoritesMenu;
    private javax.swing.JLabel filterCatIndicatorLabel;
    private javax.swing.JMenuItem filterMenuItem;
    private javax.swing.JButton findSourceButton;
    private javax.swing.JButton findTargetButton;
    private javax.swing.JComboBox fingerprintComboBox;
    private javax.swing.JPanel fingerprintPanel;
    private javax.swing.ButtonGroup frameButtonGroup;
    private javax.swing.JMenu frameMenu;
    private javax.swing.JButton googleMapsButton;
    private javax.swing.JPanel googleMapsPanel;
    private javax.swing.JComboBox groupComboBox;
    private javax.swing.JLabel groupLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JMenuItem helpIndexMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpMenuItemAbout;
    private javax.swing.JPanel historyButtonPanel;
    private javax.swing.JButton historyClearButton;
    private javax.swing.JButton historyCopyButton;
    private javax.swing.JMenuItem historyMenuFontSize;
    private javax.swing.JMenuItem historyMenuItemCopy;
    private javax.swing.JMenuItem historyMenuItemCut;
    private javax.swing.JMenuItem historyMenuItemLoad;
    private javax.swing.JMenuItem historyMenuItemPaste;
    private javax.swing.JMenuItem historyMenuItemRedo;
    private javax.swing.JMenuItem historyMenuItemSave;
    private javax.swing.JMenuItem historyMenuItemUndo;
    private javax.swing.JScrollPane historyPane;
    private javax.swing.JButton historyPasteButton;
    private javax.swing.JPopupMenu historyPopupMenu;
    private javax.swing.JMenuItem historyPopupMenuItemHTML;
    private javax.swing.JMenuItem historyPopupMenuItemMakeList;
    private javax.swing.JMenuItem historyPopupMenuItemNew;
    private javax.swing.JMenuItem historyPopupMenuenuItemFormat;
    private javax.swing.JTextArea historyTextArea;
    private javax.swing.JMenuItem homepageMenuItem;
    private javax.swing.JMenuItem htmlMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator33;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JPopupMenu.Separator jSeparator37;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JRadioButtonMenuItem luxuryRadioButtonMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton makeListButton;
    private javax.swing.JMenuItem manageFavoritesMenuItem;
    private javax.swing.JRadioButtonMenuItem maximumRadioButtonMenuItem;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JRadioButtonMenuItem miserlyRadioButtonMenuItem;
    private javax.swing.JMenu navigationMenu;
    private javax.swing.JPanel navigationPanel;
    private javax.swing.JMenuBar ncMenuBar;
    private javax.swing.JMenuItem newsletterMenuItem;
    private javax.swing.JComboBox numberTypeComboBox;
    private javax.swing.JButton phoneticAlphabetButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton playTimeButton;
    private javax.swing.JLabel precisionLabel;
    private javax.swing.JTextField precisionTextField;
    private javax.swing.JMenu programMenu;
    private javax.swing.JMenuItem programMenuItemSwap;
    private javax.swing.JMenuItem propsMenuItem;
    private javax.swing.JButton randomButton;
    private javax.swing.JMenuItem randomNumbersMenuItem;
    private javax.swing.JMenu recordMenu;
    private javax.swing.JMenuItem recordMenuItemClear;
    private javax.swing.JMenuItem recordMenuItemCopy;
    private javax.swing.JMenuItem recordMenuItemFontsize;
    private javax.swing.JMenuItem recordMenuItemFormat;
    private javax.swing.JMenuItem recordMenuItemMakeList;
    private javax.swing.JMenuItem recordMenuItemNew;
    private javax.swing.JMenuItem recordMenuItemPaste;
    private javax.swing.JPanel recordPanel;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JMenuItem reloadCategoriesMenuItem;
    private javax.swing.JMenuItem reloadUnitsItem;
    private javax.swing.JMenuItem restartItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JCheckBox sciCheckBox;
    private javax.swing.JPanel sciPanel;
    private javax.swing.JMenuItem searchCategoriesMenuItem;
    private javax.swing.JButton searchCategoryButton;
    private javax.swing.JMenu searchMenu;
    private javax.swing.JMenuItem searchSourceMenuItem;
    private javax.swing.JMenuItem searchTargetMenuItem;
    private javax.swing.JPanel soundPanel;
    private javax.swing.JButton sourceBackButton;
    private javax.swing.JComboBox sourceBox;
    private javax.swing.JPanel sourceButtonPanel;
    private javax.swing.JButton sourceClearButton;
    private javax.swing.JButton sourceCopyButton;
    private javax.swing.JPanel sourceHeaderPanel;
    private javax.swing.JMenu sourceMenu;
    private javax.swing.JMenuItem sourceMenuItemCalendar;
    private javax.swing.JMenuItem sourceMenuItemClear;
    private javax.swing.JMenuItem sourceMenuItemColor;
    private javax.swing.JMenuItem sourceMenuItemCopy;
    private javax.swing.JMenuItem sourceMenuItemDefault;
    private javax.swing.JMenuItem sourceMenuItemDefaultValue;
    private javax.swing.JMenuItem sourceMenuItemFindUnit;
    private javax.swing.JMenuItem sourceMenuItemMinus;
    private javax.swing.JMenuItem sourceMenuItemPaste;
    private javax.swing.JMenuItem sourceMenuItemPlus;
    private javax.swing.JMenuItem sourceMenuItemRedo;
    private javax.swing.JMenuItem sourceMenuItemUndo;
    private javax.swing.JButton sourceMinusButton;
    private javax.swing.JButton sourceNextButton;
    private javax.swing.JPanel sourcePanel;
    private javax.swing.JButton sourcePasteButton;
    private javax.swing.JButton sourcePlusButton;
    private javax.swing.JPopupMenu sourcePopupMenu;
    private javax.swing.JMenuItem sourcePopupMenuDefaultValue;
    private javax.swing.JMenuItem sourcePopupMenuItemCopy;
    private javax.swing.JMenuItem sourcePopupMenuItemCut;
    private javax.swing.JMenuItem sourcePopupMenuItemDefault;
    private javax.swing.JMenuItem sourcePopupMenuItemFindUnit;
    private javax.swing.JMenuItem sourcePopupMenuItemMinus;
    private javax.swing.JMenuItem sourcePopupMenuItemPaste;
    private javax.swing.JMenuItem sourcePopupMenuItemPlus;
    private javax.swing.JMenuItem sourcePopupMenuItemRedo;
    private javax.swing.JMenuItem sourcePopupMenuItemUndo;
    private javax.swing.JScrollPane sourceScrollPane;
    private javax.swing.JTextArea sourceTextArea;
    private javax.swing.JMenuItem spellMenuItem;
    private javax.swing.JRadioButtonMenuItem standardRadioButtonMenuItem;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton stopPlayingTimeButton;
    private javax.swing.JMenuItem supportMenuItem;
    private javax.swing.JButton swapButton;
    private javax.swing.JMenuItem tablesMenuItem;
    private javax.swing.JButton targetBackButton;
    private javax.swing.JComboBox targetBox;
    private javax.swing.JPanel targetButtonPanel;
    private javax.swing.JButton targetCopyButton;
    private javax.swing.JPanel targetHeaderPanel;
    private javax.swing.JMenu targetMenu;
    private javax.swing.JMenuItem targetMenuItemCopy;
    private javax.swing.JMenuItem targetMenuItemDecimals;
    private javax.swing.JMenuItem targetMenuItemDefault;
    private javax.swing.JMenuItem targetMenuItemFigures;
    private javax.swing.JMenuItem targetMenuItemFindUnit;
    private javax.swing.JMenuItem targetMenuItemPlay;
    private javax.swing.JMenuItem targetMenuItemStopPlaying;
    private javax.swing.JButton targetNextButton;
    private javax.swing.JPanel targetPanel;
    private javax.swing.JPopupMenu targetPopupMenu;
    private javax.swing.JMenuItem targetPopupMenuItemCopy;
    private javax.swing.JMenuItem targetPopupMenuItemDefault;
    private javax.swing.JMenuItem targetPopupMenuItemFindUnit;
    private javax.swing.JMenuItem targetPopupMenuItemPlay;
    private javax.swing.JMenuItem targetPopupMenuItemStopPlaying;
    private javax.swing.JScrollPane targetScrollPane;
    private javax.swing.JTextArea targetTextArea;
    private javax.swing.JButton timeDialogButton;
    private javax.swing.JMenuItem timeDialogMenuItem;
    private javax.swing.JPanel timePanel;
    private javax.swing.JButton timePanelTimeChooserButton;
    private javax.swing.JMenuItem tutorialMenuItem;
    private javax.swing.JButton tzDateButton;
    private javax.swing.JPanel tzPanel;
    private javax.swing.JButton tzTimeButton;
    private javax.swing.JMenuItem undoMenuItem;
    private javax.swing.JButton unicodeButton;
    private javax.swing.JPanel unicodePanel;
    private javax.swing.JMenu unitsMenu;
    private javax.swing.JMenuItem updateCenterMenuItem;
    private javax.swing.JMenuItem updateExchangeRatesMenuItem;
    private javax.swing.JLabel warnLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public String preferredHelpLanguage() {
        return propertiesManager.getUserProperties().getProperty("help", "en");
    }

    
    @Override
    public PropertiesManager getPropertiesManager() {
        return propertiesManager;
    }
    /*
    @Override
    public Properties getUserProperties() {
        return propertiesManager.getUserProperties();
    }
*/
    @Override
    public void updateUpdateProperties(Properties props) {
        propertiesManager.updateUpdateProperties(props);
    }

    @Override
    public Properties getUpdateProperties() {
        return propertiesManager.getUpdateProperties();
    }

    @Override
    public void saveUpdateProperties() {
        propertiesManager.saveUpdateProperties();
    }

    @Override
    public void updateGlobalProperties(Properties props) {
        propertiesManager.updateGlobalProperties(props);
    }

    @Override
    public Properties getGlobalProperties() {
        return propertiesManager.getGlobalProperties();
    }

}
