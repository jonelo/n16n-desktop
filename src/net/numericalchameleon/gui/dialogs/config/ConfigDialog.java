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
package net.numericalchameleon.gui.dialogs.config;

import net.numericalchameleon.gui.dialogs.config.renderer.LangListRenderer;
import java.awt.ComponentOrientation;
import java.awt.Frame;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.*;
import jonelo.sugar.gui.GeneralGUI;
import jonelo.sugar.gui.MyListModel;
import jonelo.sugar.gui.PropertiesMetalTheme;
import jonelo.sugar.io.GeneralIO;
import jonelo.sugar.util.GeneralProgram;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.i18n.ResBundleRecord;
import net.numericalchameleon.data.i18n.ResBundles;
import net.numericalchameleon.data.properties.PropertiesManager;
import net.numericalchameleon.gui.lnf.LookAndFeel;

public class ConfigDialog extends javax.swing.JDialog {

    public static final String TRUE = "true",
            FALSE = "false";
    private final ResourceBundle rb;
    private final Properties userProps;
    private final Vector themesVector;
    private final List<ResBundleRecord> languageList;
    private final Frame parent;

    private final Properties backupUserProps;
    private final Properties systemProps;
    private final ConfigDialogInterface configDialogInterface;
    private LangListRenderer langListRenderer;
    private PropertiesManager propertiesManager;

    /** Creates new form ConfigDialog
     * @param configInterface */
    public ConfigDialog(ConfigDialogInterface configInterface) {
        super(configInterface.getFrame(), true);
        this.configDialogInterface = configInterface;
        this.rb = configInterface.getResourceBundle();
        this.parent = configInterface.getFrame();
        this.propertiesManager = configInterface.getPropertiesManager();
        this.userProps = propertiesManager.getUserProperties();
        this.systemProps = propertiesManager.getGlobalProperties();
        backupUserProps = new Properties();
        // make a backup
        GeneralIO.copyProperties(userProps, backupUserProps);

        if (parent.isUndecorated()) {
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        }
        setTitle(rb.getString("GUI.Options.Title"));
        themesVector = readThemes();
        ResBundles languages = new ResBundles();
        languageList = languages.getList();

        initComponents();
        postInitComponents();

        String selectedTheme = userProps.getProperty("theme", "Default.theme");
        themesList.setSelectedValue(selectedTheme, true);

        if (LookAndFeel.isJavaLookAndFeel(userProps.getProperty("lnf", ""))) {
            // option is only supported by Java based Look and Feels
            frameDecorationCheckBox.setEnabled(true);
        } else {
            // frameDecorationCheckBox is disabled already by default
            // frameDecorationCheckBox.setEnabled(false);
            userProps.setProperty("decoration", "native");
        }
        alwaysOnTopCheckBox.setEnabled(true);
        audioNotificationCheckBox.setEnabled(true);
        boldFontCheckBox.setEnabled(true);

        ComponentOrientation componentOrientation = configInterface.getAppComponentOrientation();
        if (!componentOrientation.isLeftToRight()) {
            GeneralGUI.applyOrientation(centerPanel, componentOrientation);
        }

        // set the status of all components (read the properties)
        updateGUIfromProps(userProps);
        setLocationRelativeTo(parent);
    }

    private Vector readThemes() {
        boolean debug = false;
        if (debug) {
            System.out.print("Reading Themes ... ");
        }
        Vector vector;
        try {
            String myHome = "../data/themes/";
            if (configDialogInterface.isOneJarWanted()) {
                vector = GeneralIO.readFromJarInVector(ConfigDialog.class, "/data/themes/onejar.list");
            } else {
                vector = GeneralIO.ls(myHome, ".theme");
            }
            if (vector == null) {
                throw new Exception("No themes found in " + myHome);
            }
            if (debug) {
                System.out.println("OK.");
            }
            return vector;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR: " + e.getMessage());
        }
        return null;
    }


    private void updateGUIfromProps(Properties props) {
        langList.setSelectedIndex(Integer.parseInt(props.getProperty("languageIndex", "1")));
        backupCatAndUnitCheckBox.setSelected(props.getProperty("backupCatAndUnit", "false").equals("true"));
        restoreCatAndUnitCheckBox.setSelected(props.getProperty("restoreCatAndUnit", "false").equals("true"));

        updateLookAndFeelFromProperties(props);
        frameDecorationCheckBox.setSelected(props.getProperty("decoration", "native").equals("java"));
        showToolTipsCheckBox.setSelected(props.getProperty("tooltips", "true").equals("true"));
        try {
            toolTipSlider.setValue(Integer.parseInt(props.getProperty("tooltips.delay", "750")));
        } catch (NumberFormatException nfe) {
        }
        setShowToolTipsCheckBoxText();
        alwaysOnTopCheckBox.setSelected(props.getProperty("alwaysOnTop", "false").equals("true"));
        askBeforeExitCheckBox.setSelected(props.getProperty("askBeforeExit", "true").equals("true"));
        showExitAnimationCheckBox.setSelected(props.getProperty("exitAnimation", "false").equals("true"));
        useLocalHelpSystemCheckBox.setSelected(props.getProperty("help.online", "true").equals("false"));        
        checkForNewAppVersionOnStartupCheckBox.setSelected(props.getProperty("version.updateCheckAllowed", "true").equals("true"));
        checkForNewAppVersionButAtMostOnceADayCheckBox.setSelected(props.getProperty("version.ignoreLastChecked", "false").equals("false"));
        updateExchangeRatesCheckBox.setSelected(systemProps.getProperty("rates_update.policy","none").equals("OnAppStartUp"));
        exRatesFilterTextField.setText(systemProps.getProperty("rates_update.policy.OnAppStartUp.filterID", "fx.sauder.ubc.ca"));
        
        updateExchangeRatesCheckBox.setEnabled(propertiesManager.isGlobalPropertiesWritable());
        
        if (LookAndFeel.isJavaThemeSupported(props.getProperty("lnf", ""))) {
            updateWithTheme(props.getProperty("theme", null));
        }
        audioNotificationCheckBox.setSelected(props.getProperty("audioNotification", "false").equals("true"));
        updateAudio();

        boldFontCheckBox.setSelected(props.getProperty("boldFont", "false").equals("true"));
        updateGUI();

    }

    private void updateAudio() {
        LookAndFeel.setAudioFeedback(audioNotificationCheckBox.isSelected());
    }

    private void updateFont() {
        UIManager.put("swing.boldMetal", Boolean.valueOf(boldFontCheckBox.isSelected()));
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
        }
        updateGUI();
    }

    private void updatePropertiesFromGUI(Properties props) {
        props.setProperty("decoration", frameDecorationCheckBox.isSelected() ? "java" : "native");
        props.setProperty("tooltips", showToolTipsCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("askBeforeExit", askBeforeExitCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("alwaysOnTop", alwaysOnTopCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("exitAnimation", showExitAnimationCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("tooltips.delay", Integer.toString(toolTipSlider.getValue()));
        props.setProperty("audioNotification", audioNotificationCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("boldFont", boldFontCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("backupCatAndUnit", backupCatAndUnitCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("restoreCatAndUnit", restoreCatAndUnitCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("help.online", useLocalHelpSystemCheckBox.isSelected() ? FALSE : TRUE);
        props.setProperty("version.updateCheckAllowed", checkForNewAppVersionOnStartupCheckBox.isSelected() ? TRUE : FALSE);
        props.setProperty("version.ignoreLastChecked", checkForNewAppVersionButAtMostOnceADayCheckBox.isSelected() ? FALSE: TRUE);
        systemProps.setProperty("rates_update.policy", updateExchangeRatesCheckBox.isSelected() ? "OnAppStartUp" : "none");
        systemProps.setProperty("rates_update.policy.OnAppStartUp.filterID", exRatesFilterTextField.getText());
        systemProps.remove("rates_update.policy.OnAppStartUp.lastSuccessfulUpdate");

        if (!langList.isSelectionEmpty()) {
            props.setProperty("language", ((ResBundleRecord) langList.getSelectedValue()).getLanguage());
            props.setProperty("country", ((ResBundleRecord) langList.getSelectedValue()).getCountry());
            props.setProperty("help", ((ResBundleRecord) langList.getSelectedValue()).getHelp());
            props.setProperty("languageIndex", Integer.toString(langList.getSelectedIndex()));
        }
    }

    private void restore() {
        GeneralIO.copyProperties(backupUserProps, userProps);
        updateGUIfromProps(userProps);
    }

    private void postInitComponents() {
        // Standards
        lafButtonGroup.add(aquaRadioButton);
        lafButtonGroup.add(gtkRadioButton);
        lafButtonGroup.add(macRadioButton);
        lafButtonGroup.add(metalRadioButton);
        lafButtonGroup.add(motifRadioButton);
        lafButtonGroup.add(nimbusRadioButton);
        lafButtonGroup.add(oceanRadioButton);
        lafButtonGroup.add(windowsRadioButton);
        lafButtonGroup.add(systemRadioButton);
        // Non-Standards
        lafButtonGroup.add(nimrodRadioButton);
        lafButtonGroup.add(metalThemesEnabledRadioButton);
        lafButtonGroup.add(jtattooRadioButton);

        // Look and Feels (enable and disable)
        aquaRadioButton.setEnabled(GeneralGUI.isLookAndFeelAvailable(GeneralGUI.LAF_AQUA));
        gtkRadioButton.setEnabled(GeneralGUI.isLookAndFeelAvailable(GeneralGUI.LAF_GTK));
        motifRadioButton.setEnabled(GeneralGUI.isLookAndFeelAvailable(GeneralGUI.LAF_MOTIF));
        windowsRadioButton.setEnabled(GeneralGUI.isLookAndFeelAvailable(GeneralGUI.LAF_WINDOWS));
        macRadioButton.setEnabled(GeneralGUI.isLookAndFeelAvailable(GeneralGUI.LAF_MAC));
        oceanRadioButton.setEnabled(GeneralProgram.isSupportFor("1.5"));
        nimbusRadioButton.setEnabled(GeneralProgram.isSupportFor("1.6")); // genauer: 1.6.0_10
        // Seaglass 0.1.7.3 at http://repo1.maven.org/maven2/com/seaglasslookandfeel/seaglasslookandfeel/
        // is not very stable, it throws NPE
        // at com.seaglasslookandfeel.ui.SeaGlassScrollPaneUI.paintScrollPaneCorner(SeaGlassScrollPaneUI.java:272)
        // disbabled for now, placeholder for the future
        // seaglassRadioButton.setEnabled(GeneralProgram.isSupportFor("1.6")); // genauer: 1.6.0_10

        setRenderers();
        updateLookAndFeelFromProperties(userProps);
    }

    private void setRenderers() {
        if (langListRenderer == null) {
            langListRenderer = new LangListRenderer();
        }
        langList.setCellRenderer(langListRenderer);
    }

    private void updateLookAndFeelFromProperties(Properties props) {
        if (props.getProperty("lnf", "").equals("aqua")) {
            aquaRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("gtk")) {
            gtkRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("mac")) {
            macRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("metal_default")) {
            metalRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("motif")) {
            motifRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("metal")) {
            metalThemesEnabledRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(true);
        } else if (props.getProperty("lnf", "").equals("metal_ocean")) {
            oceanRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(true);
        } else if (props.getProperty("lnf", "").equals("windows")) {
            windowsRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("nimrod")) {
            nimrodRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("nimbus")) {
            nimbusRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("system")) {
            systemRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        } else if (props.getProperty("lnf", "").equals("jtattoo")) {
            jtattooRadioButton.setSelected(true);
            boldFontCheckBox.setEnabled(false);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lafButtonGroup = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        southPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        restoreButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        optionsTabbedPane = new javax.swing.JTabbedPane();
        startStopPanel = new javax.swing.JPanel();
        startStopTabbedPane = new javax.swing.JTabbedPane();
        startOptionsPanel = new javax.swing.JPanel();
        restoreCatAndUnitCheckBox = new javax.swing.JCheckBox();
        checkForNewAppVersionOnStartupCheckBox = new javax.swing.JCheckBox();
        updateExchangeRatesCheckBox = new javax.swing.JCheckBox();
        checkForNewAppVersionButAtMostOnceADayCheckBox = new javax.swing.JCheckBox();
        exRatesFilterLabel = new javax.swing.JLabel();
        exRatesFilterTextField = new javax.swing.JTextField();
        exitOptionsPanel = new javax.swing.JPanel();
        backupCatAndUnitCheckBox = new javax.swing.JCheckBox();
        showExitAnimationCheckBox = new javax.swing.JCheckBox();
        askBeforeExitCheckBox = new javax.swing.JCheckBox();
        lnfPanel = new javax.swing.JPanel();
        uiTabbedPane = new javax.swing.JTabbedPane();
        languagePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        langList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        langInfoLabel = new javax.swing.JLabel();
        lookAndFeelPanel = new javax.swing.JPanel();
        themesPanel = new javax.swing.JPanel();
        themesScrollPane = new javax.swing.JScrollPane();
        themesList = new javax.swing.JList();
        metalThemesEnabledRadioButton = new javax.swing.JRadioButton();
        nimrodRadioButton = new javax.swing.JRadioButton();
        jtattooRadioButton = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        aquaRadioButton = new javax.swing.JRadioButton();
        gtkRadioButton = new javax.swing.JRadioButton();
        macRadioButton = new javax.swing.JRadioButton();
        motifRadioButton = new javax.swing.JRadioButton();
        nimbusRadioButton = new javax.swing.JRadioButton();
        oceanRadioButton = new javax.swing.JRadioButton();
        metalRadioButton = new javax.swing.JRadioButton();
        systemRadioButton = new javax.swing.JRadioButton();
        windowsRadioButton = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        optionsPanel = new javax.swing.JPanel();
        frameDecorationCheckBox = new javax.swing.JCheckBox();
        alwaysOnTopCheckBox = new javax.swing.JCheckBox();
        audioNotificationCheckBox = new javax.swing.JCheckBox();
        boldFontCheckBox = new javax.swing.JCheckBox();
        helpPanel = new javax.swing.JPanel();
        useLocalHelpSystemCheckBox = new javax.swing.JCheckBox();
        showToolTipsCheckBox = new javax.swing.JCheckBox();
        toolTipDelayLabel = new javax.swing.JLabel();
        toolTipSlider = new javax.swing.JSlider();

        setTitle(rb.getString("GUI.General.Preferences")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel5.setMinimumSize(new java.awt.Dimension(600, 480));
        jPanel5.setPreferredSize(new java.awt.Dimension(800, 480));
        jPanel5.setLayout(new java.awt.BorderLayout());

        southPanel.setLayout(new java.awt.BorderLayout());

        okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/ok.png"))); // NOI18N
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(okButton);

        restoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        restoreButton.setText(rb.getString("GUI.General.Restore")); // NOI18N
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(restoreButton);

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/close.png"))); // NOI18N
        cancelButton.setText(rb.getString("GUI.General.Cancel")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        southPanel.add(buttonPanel, java.awt.BorderLayout.EAST);

        jPanel5.add(southPanel, java.awt.BorderLayout.SOUTH);

        centerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        centerPanel.setMinimumSize(new java.awt.Dimension(100, 99));
        centerPanel.setPreferredSize(new java.awt.Dimension(100, 99));
        centerPanel.setLayout(new java.awt.BorderLayout());

        restoreCatAndUnitCheckBox.setText(rb.getString("GUI.Config.RestoreMemorizedCategoryAndUnitOnStartup")); // NOI18N

        checkForNewAppVersionOnStartupCheckBox.setSelected(true);
        checkForNewAppVersionOnStartupCheckBox.setText(rb.getString("GUI.Config.CheckForNewAppVersionOnStartup")); // NOI18N

        updateExchangeRatesCheckBox.setText(rb.getString("GUI.Config.UpdateExchangeRatesOnStartup")); // NOI18N

        checkForNewAppVersionButAtMostOnceADayCheckBox.setSelected(true);
        checkForNewAppVersionButAtMostOnceADayCheckBox.setText(rb.getString("GUI.Config.CheckForNewAppVersionOnStartup.AtMostOnceADay")); // NOI18N

        exRatesFilterLabel.setText("Filter ID:");

        javax.swing.GroupLayout startOptionsPanelLayout = new javax.swing.GroupLayout(startOptionsPanel);
        startOptionsPanel.setLayout(startOptionsPanelLayout);
        startOptionsPanelLayout.setHorizontalGroup(
            startOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(startOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(startOptionsPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(checkForNewAppVersionButAtMostOnceADayCheckBox)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(startOptionsPanelLayout.createSequentialGroup()
                        .addComponent(checkForNewAppVersionOnStartupCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(startOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateExchangeRatesCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(startOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restoreCatAndUnitCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE))
            .addGroup(startOptionsPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(exRatesFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exRatesFilterTextField)
                .addContainerGap())
        );
        startOptionsPanelLayout.setVerticalGroup(
            startOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkForNewAppVersionOnStartupCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkForNewAppVersionButAtMostOnceADayCheckBox)
                .addGap(18, 18, 18)
                .addComponent(updateExchangeRatesCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(startOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exRatesFilterLabel)
                    .addComponent(exRatesFilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(restoreCatAndUnitCheckBox)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        startStopTabbedPane.addTab(rb.getString("GUI.Config.StartOptions"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/traffic-light-green.png")), startOptionsPanel); // NOI18N

        backupCatAndUnitCheckBox.setText(rb.getString("GUI.Config.MemorizeTheLatestSelectedCategoryAndUnitOnExit")); // NOI18N

        showExitAnimationCheckBox.setText(rb.getString("GUI.Options.ShowExitAnimation")); // NOI18N

        askBeforeExitCheckBox.setText(rb.getString("GUI.Options.AskBeforeExit")); // NOI18N

        javax.swing.GroupLayout exitOptionsPanelLayout = new javax.swing.GroupLayout(exitOptionsPanel);
        exitOptionsPanel.setLayout(exitOptionsPanelLayout);
        exitOptionsPanelLayout.setHorizontalGroup(
            exitOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(exitOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(askBeforeExitCheckBox)
                    .addComponent(backupCatAndUnitCheckBox)
                    .addComponent(showExitAnimationCheckBox))
                .addContainerGap(271, Short.MAX_VALUE))
        );
        exitOptionsPanelLayout.setVerticalGroup(
            exitOptionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitOptionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(askBeforeExitCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupCatAndUnitCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showExitAnimationCheckBox)
                .addContainerGap(275, Short.MAX_VALUE))
        );

        startStopTabbedPane.addTab(rb.getString("GUI.Config.StopOptions"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/traffic-light-red.png")), exitOptionsPanel); // NOI18N

        javax.swing.GroupLayout startStopPanelLayout = new javax.swing.GroupLayout(startStopPanel);
        startStopPanel.setLayout(startStopPanelLayout);
        startStopPanelLayout.setHorizontalGroup(
            startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startStopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startStopTabbedPane)
                .addContainerGap())
        );
        startStopPanelLayout.setVerticalGroup(
            startStopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startStopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startStopTabbedPane)
                .addContainerGap())
        );

        optionsTabbedPane.addTab(rb.getString("GUI.Config.StartStop"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/arrow-circle-double-135.png")), startStopPanel); // NOI18N

        langList.setModel(new MyListModel(languageList));
        langList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        langList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                langListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(langList);

        jLabel2.setText(rb.getString("GUI.Text.ChangingTheLanguageRequiresAProgramRestart")); // NOI18N

        langInfoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout languagePanelLayout = new javax.swing.GroupLayout(languagePanel);
        languagePanel.setLayout(languagePanelLayout);
        languagePanelLayout.setHorizontalGroup(
            languagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(languagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(languagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(languagePanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(langInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        languagePanelLayout.setVerticalGroup(
            languagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, languagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(languagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(langInfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addContainerGap())
        );

        uiTabbedPane.addTab(rb.getString("GUI.General.Language"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/locale-alternate.png")), languagePanel); // NOI18N

        themesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(rb.getString("GUI.Config.LookAndFeel.Alternatives"))); // NOI18N

        themesList.setModel(GeneralGUI.getListModel(themesVector));
        themesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        themesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                themesListMouseClicked(evt);
            }
        });
        themesScrollPane.setViewportView(themesList);

        metalThemesEnabledRadioButton.setText("Metal");
        metalThemesEnabledRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                metalThemesEnabledRadioButtonItemStateChanged(evt);
            }
        });

        nimrodRadioButton.setText("NimROD");
        nimrodRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nimrodRadioButtonItemStateChanged(evt);
            }
        });

        jtattooRadioButton.setText("JTattoo");
        jtattooRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jtattooRadioButtonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout themesPanelLayout = new javax.swing.GroupLayout(themesPanel);
        themesPanel.setLayout(themesPanelLayout);
        themesPanelLayout.setHorizontalGroup(
            themesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(themesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(themesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(metalThemesEnabledRadioButton)
                    .addComponent(nimrodRadioButton)
                    .addComponent(jtattooRadioButton))
                .addGap(18, 18, 18)
                .addComponent(themesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
        themesPanelLayout.setVerticalGroup(
            themesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(themesPanelLayout.createSequentialGroup()
                .addGroup(themesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(themesPanelLayout.createSequentialGroup()
                        .addComponent(jtattooRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(metalThemesEnabledRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nimrodRadioButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(themesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(rb.getString("GUI.Config.LookAndFeel.Standards"))); // NOI18N

        aquaRadioButton.setText("Aqua (macOS)");
        aquaRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                aquaRadioButtonItemStateChanged(evt);
            }
        });

        gtkRadioButton.setText("GTK+ 2.0 (Linux/Unix)");
        gtkRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gtkRadioButtonItemStateChanged(evt);
            }
        });

        macRadioButton.setText("Mac (Mac OS)");
        macRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                macRadioButtonItemStateChanged(evt);
            }
        });

        motifRadioButton.setText("Motif");
        motifRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                motifRadioButtonItemStateChanged(evt);
            }
        });

        nimbusRadioButton.setText("Nimubs");
        nimbusRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nimbusRadioButtonItemStateChanged(evt);
            }
        });

        oceanRadioButton.setText("Ocean");
        oceanRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                oceanRadioButtonItemStateChanged(evt);
            }
        });

        metalRadioButton.setText("Steel");
        metalRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                metalRadioButtonItemStateChanged(evt);
            }
        });

        systemRadioButton.setText("System");
        systemRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                systemRadioButtonItemStateChanged(evt);
            }
        });

        windowsRadioButton.setText("Windows");
        windowsRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                windowsRadioButtonItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(aquaRadioButton)
                            .addComponent(gtkRadioButton)
                            .addComponent(macRadioButton)
                            .addComponent(motifRadioButton)
                            .addComponent(oceanRadioButton)
                            .addComponent(nimbusRadioButton)
                            .addComponent(metalRadioButton)
                            .addComponent(windowsRadioButton)
                            .addComponent(systemRadioButton))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(aquaRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gtkRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(macRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(motifRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nimbusRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oceanRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(metalRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowsRadioButton)
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(systemRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout lookAndFeelPanelLayout = new javax.swing.GroupLayout(lookAndFeelPanel);
        lookAndFeelPanel.setLayout(lookAndFeelPanelLayout);
        lookAndFeelPanelLayout.setHorizontalGroup(
            lookAndFeelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lookAndFeelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(themesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        lookAndFeelPanelLayout.setVerticalGroup(
            lookAndFeelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lookAndFeelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lookAndFeelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(themesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        uiTabbedPane.addTab(rb.getString("GUI.Config.LookAndFeel"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/t-shirt.png")), lookAndFeelPanel); // NOI18N

        frameDecorationCheckBox.setText(rb.getString("GUI.Options.FrameDecoration")); // NOI18N
        frameDecorationCheckBox.setToolTipText(rb.getString("GUI.Options.RequiresManualRestart")); // NOI18N
        frameDecorationCheckBox.setEnabled(false);
        frameDecorationCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frameDecorationCheckBoxActionPerformed(evt);
            }
        });

        alwaysOnTopCheckBox.setText(rb.getString("GUI.Config.WindowAlwaysOnTop")); // NOI18N

        audioNotificationCheckBox.setText(rb.getString("GUI.Config.AuditoryFeedback")); // NOI18N
        audioNotificationCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioNotificationCheckBoxActionPerformed(evt);
            }
        });

        boldFontCheckBox.setText(rb.getString("GUI.Config.BoldFont")); // NOI18N
        boldFontCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boldFontCheckBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameDecorationCheckBox)
                    .addComponent(alwaysOnTopCheckBox)
                    .addComponent(audioNotificationCheckBox)
                    .addComponent(boldFontCheckBox))
                .addContainerGap(409, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(frameDecorationCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alwaysOnTopCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(audioNotificationCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boldFontCheckBox)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        uiTabbedPane.addTab(rb.getString("GUI.Config.GraphicalOptions"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/t-shirt-print.png")), optionsPanel); // NOI18N

        useLocalHelpSystemCheckBox.setText(rb.getString("GUI.Options.UseLocalHelpSystem")); // NOI18N

        showToolTipsCheckBox.setText(rb.getString("GUI.Options.ShowToolTips")); // NOI18N
        showToolTipsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showToolTipsCheckBoxActionPerformed(evt);
            }
        });

        toolTipDelayLabel.setText(rb.getString("GUI.Options.TooltipDelayInMs")); // NOI18N

        toolTipSlider.setMajorTickSpacing(500);
        toolTipSlider.setMaximum(3000);
        toolTipSlider.setPaintTicks(true);
        toolTipSlider.setValue(750);
        toolTipSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toolTipSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout helpPanelLayout = new javax.swing.GroupLayout(helpPanel);
        helpPanel.setLayout(helpPanelLayout);
        helpPanelLayout.setHorizontalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(useLocalHelpSystemCheckBox)
                    .addComponent(showToolTipsCheckBox)
                    .addGroup(helpPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toolTipSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toolTipDelayLabel))))
                .addContainerGap(339, Short.MAX_VALUE))
        );
        helpPanelLayout.setVerticalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(useLocalHelpSystemCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showToolTipsCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolTipDelayLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolTipSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );

        uiTabbedPane.addTab(rb.getString("GUI.Config.HelpOptions"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/question-white.png")), helpPanel); // NOI18N

        javax.swing.GroupLayout lnfPanelLayout = new javax.swing.GroupLayout(lnfPanel);
        lnfPanel.setLayout(lnfPanelLayout);
        lnfPanelLayout.setHorizontalGroup(
            lnfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lnfPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiTabbedPane)
                .addContainerGap())
        );
        lnfPanelLayout.setVerticalGroup(
            lnfPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lnfPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uiTabbedPane)
                .addContainerGap())
        );

        optionsTabbedPane.addTab(rb.getString("GUI.General.UserInterface"), new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/application-blue-24x24.png")), lnfPanel); // NOI18N

        centerPanel.add(optionsTabbedPane, java.awt.BorderLayout.CENTER);

        jPanel5.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boldFontCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boldFontCheckBoxItemStateChanged
        updateFont();
    }//GEN-LAST:event_boldFontCheckBoxItemStateChanged

    private void audioNotificationCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioNotificationCheckBoxActionPerformed
        updateAudio();
    }//GEN-LAST:event_audioNotificationCheckBoxActionPerformed

    private void aquaRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_aquaRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(GeneralGUI.LAF_AQUA);
        standardLnfChanged("aqua");
    }//GEN-LAST:event_aquaRadioButtonItemStateChanged

    private void gtkRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gtkRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(GeneralGUI.LAF_GTK);
        standardLnfChanged("gtk");
    }//GEN-LAST:event_gtkRadioButtonItemStateChanged

    private void metalRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_metalRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(true);
        LookAndFeel.setMetalTheme(LookAndFeel.getDefaultMetalTheme());
        themedLnfChanged("metal_default", null);
    }//GEN-LAST:event_metalRadioButtonItemStateChanged

    private void macRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_macRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(GeneralGUI.LAF_MAC);
        standardLnfChanged("mac");
    }//GEN-LAST:event_macRadioButtonItemStateChanged

    private void motifRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_motifRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(GeneralGUI.LAF_MOTIF);
        standardLnfChanged("motif");
    }//GEN-LAST:event_motifRadioButtonItemStateChanged

    private void oceanRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_oceanRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(true);
        LookAndFeel.setMetalTheme(LookAndFeel.getOceanTheme());
        themedLnfChanged("metal_ocean", null);
    }//GEN-LAST:event_oceanRadioButtonItemStateChanged

    private void windowsRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_windowsRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(GeneralGUI.LAF_WINDOWS);
        standardLnfChanged("windows");
    }//GEN-LAST:event_windowsRadioButtonItemStateChanged

    private void systemRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_systemRadioButtonItemStateChanged
        boldFontCheckBox.setEnabled(false);
        LookAndFeel.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        standardLnfChanged("system");
    }//GEN-LAST:event_systemRadioButtonItemStateChanged

    private void themesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_themesListMouseClicked
       if (jtattooRadioButton.isSelected()) {
           // JTattoo
           boldFontCheckBox.setEnabled(false);
           String value = (String)themesList.getSelectedValue();
           updateJTattooWithTheme(value);
       } else {
           // Metal or Nimrod
           boldFontCheckBox.setEnabled(false);
           int index = themesList.locationToIndex(evt.getPoint());
           updateWithTheme((String) themesVector.get(index));
       }
    }//GEN-LAST:event_themesListMouseClicked

    private void loadStandardThemesInList() {
        System.out.println(themesVector);
        themesList.setModel(GeneralGUI.getListModel(themesVector));
    }
    
    private void metalThemesEnabledRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_metalThemesEnabledRadioButtonItemStateChanged

        // update the list with Themes
        loadStandardThemesInList();

        boldFontCheckBox.setEnabled(false);
        String theme = userProps.getProperty("theme", "Default.theme");
        PropertiesMetalTheme metalTheme = new PropertiesMetalTheme(getClass().getResourceAsStream("/data/themes/" + theme));
        LookAndFeel.setMetalTheme(metalTheme);
        themedLnfChanged("metal", theme);
    }//GEN-LAST:event_metalThemesEnabledRadioButtonItemStateChanged

    private void nimrodRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nimrodRadioButtonItemStateChanged
        
        // update the list with Themes
        loadStandardThemesInList();
        
        boldFontCheckBox.setEnabled(false);
        String theme = userProps.getProperty("theme", "Nimrod.theme");
        PropertiesMetalTheme metalTheme = new PropertiesMetalTheme(getClass().getResourceAsStream("/data/themes/" + theme));
        LookAndFeel.setNimrodTheme(metalTheme);
        themedLnfChanged("nimrod", theme);
    }//GEN-LAST:event_nimrodRadioButtonItemStateChanged

    private void frameDecorationCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frameDecorationCheckBoxActionPerformed
        JOptionPane.showMessageDialog(this, rb.getString("GUI.Options.FrameDecorationMessage"),
                rb.getString("GUI.Options.FrameDecoration"), JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_frameDecorationCheckBoxActionPerformed

    private void showToolTipsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showToolTipsCheckBoxActionPerformed
        toolTipSlider.setValue(750);
    }//GEN-LAST:event_showToolTipsCheckBoxActionPerformed

    private void toolTipSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toolTipSliderStateChanged
        setShowToolTipsCheckBoxText();
    }//GEN-LAST:event_toolTipSliderStateChanged

    public void updateGUI() {
        SwingUtilities.updateComponentTreeUI(getRootPane());
        configDialogInterface.updateGUI();
    }

    public void themedLnfChanged(String lnfCode, String themeCode) {
        boolean themeSupport = (themeCode != null);
        userProps.setProperty("lnf", lnfCode);
        if (themeSupport) {
            userProps.setProperty("theme", themeCode);
        }
        updateGUI();
        //uiTabbedPane.setEnabledAt(1, themeSupport);
        //themesScrollPane.setEnabled(themeSupport);
        //themesList.setEnabled(themeSupport);
        themesScrollPane.setVisible(themeSupport);
        if (themeSupport) {
            themesList.setSelectedValue(themeCode, true);
        }
    }
    
    public void loadJTattooThemesInList() {
        themesList.setModel(GeneralGUI.getListModel(new Vector(com.jtattoo.plaf.texture.TextureLookAndFeel.getThemes())));
    }

    public void jTattooThemeChanged(String jtattooLnf, String jtattooTheme) {
        
        // update the user props
        userProps.setProperty("lnf", "jtattoo");
        userProps.setProperty("jtattoo_lnf", jtattooLnf);
        userProps.setProperty("jtattoo_theme", jtattooTheme);
        
        // update the GUI
        updateGUI();
                
        themesScrollPane.setVisible(true);
        themesList.setSelectedValue(jtattooTheme, true);
    }

    

    private void standardLnfChanged(String lnfCode) {
        userProps.setProperty("lnf", lnfCode);
        updateGUI();
        // check for frame decoration
        if (userProps.getProperty("decoration", "native").equals("java")) {
            JOptionPane.showMessageDialog(this, rb.getString("GUI.Options.FrameDecorationMessage"), "", JOptionPane.WARNING_MESSAGE);
        }
        //uiTabbedPane.setEnabledAt(1, false);
    }

    private void updateWithTheme(String theme) {
        if (theme == null) {
            return;
        }
        PropertiesMetalTheme metalTheme = new PropertiesMetalTheme(getClass().getResourceAsStream("/data/themes/" + theme));
        // update the jtattooTheme list
        themesList.setSelectedValue(theme, true);
        userProps.setProperty("theme", theme);

        if (userProps.getProperty("lnf", "").equals("metal")) {
            LookAndFeel.setMetalTheme(metalTheme);
        } else if (userProps.getProperty("lnf", "").equals("nimrod")) {
            LookAndFeel.setNimrodTheme(metalTheme);
        }
        updateGUI();
        //uiTabbedPane.setEnabledAt(1, true);
        themesList.setSelectedValue(theme, true);
    }
    
    private void updateJTattooWithTheme(String theme) {
        themesList.setSelectedValue(theme, true);
        userProps.setProperty("jtattoo_theme", theme);
        LookAndFeel.setJTattooLnF("com.jtattoo.plaf.texture.TextureLookAndFeel", theme);
        updateGUI();
        themesList.setSelectedValue(theme, true);
    }

    // set the text for the CheckBox showToolTipsCheckBox
    private void setShowToolTipsCheckBoxText() {
        if (toolTipSlider.getValue() == 0) {
            showToolTipsCheckBox.setText(rb.getString("GUI.Options.ShowTooltipsImmediately"));
        } else {
            showToolTipsCheckBox.setText(
                    GeneralString.message(rb.getString("GUI.Options.ShowTooltipsAfterXms"), toolTipSlider.getValue()));
        }
    }

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
        restore();
    }//GEN-LAST:event_restoreButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        updatePropertiesFromGUI(userProps);
        if (!userProps.getProperty("languageIndex", "1").equals(backupUserProps.getProperty("languageIndex", "1"))) {
            userProps.setProperty("restart", "true"); // flag to restart the app
        }
        userProps.setProperty("save", "true"); // flag to store the properties
        if (propertiesManager.isGlobalPropertiesWritable()) {
            systemProps.setProperty("save", "true"); // flag to store the global properties
        }
        closeDialog(null);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        restore();
        closeDialog(null);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void nimbusRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nimbusRadioButtonItemStateChanged
        LookAndFeel.setNimbusLookAndFeel();
        standardLnfChanged("nimbus");
    }//GEN-LAST:event_nimbusRadioButtonItemStateChanged

    private void langListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_langListValueChanged
       if (!evt.getValueIsAdjusting()) {
            JList list = (JList)evt.getSource();

            ResBundleRecord lang = (ResBundleRecord)langList.getSelectedValue();
            updateLangInfoLabel(lang);
        }
    }//GEN-LAST:event_langListValueChanged

    private void jtattooRadioButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jtattooRadioButtonItemStateChanged
        // update the list with predefined JTattoo Themes
        loadJTattooThemesInList();
        
        // update special GUI items in the config dialog
        boldFontCheckBox.setEnabled(false);
        
        // read the last value if one was set in the past
        String jtattooLnf = userProps.getProperty("jtattoo_lnf", "com.jtattoo.plaf.texture.TextureLookAndFeel");
        String jtattooTheme = userProps.getProperty("jtattoo_theme", "Default");
        
        // set the Lnf properly
        LookAndFeel.setJTattooLnF(jtattooLnf, jtattooTheme);
        
        // notify the GUI about the change and repaint
        jTattooThemeChanged(jtattooLnf, jtattooTheme);
    }//GEN-LAST:event_jtattooRadioButtonItemStateChanged

    private void updateLangInfoLabel(ResBundleRecord lang) {
        langInfoLabel.setText(lang.getHTML());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alwaysOnTopCheckBox;
    private javax.swing.JRadioButton aquaRadioButton;
    private javax.swing.JCheckBox askBeforeExitCheckBox;
    private javax.swing.JCheckBox audioNotificationCheckBox;
    private javax.swing.JCheckBox backupCatAndUnitCheckBox;
    private javax.swing.JCheckBox boldFontCheckBox;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JCheckBox checkForNewAppVersionButAtMostOnceADayCheckBox;
    private javax.swing.JCheckBox checkForNewAppVersionOnStartupCheckBox;
    private javax.swing.JLabel exRatesFilterLabel;
    private javax.swing.JTextField exRatesFilterTextField;
    private javax.swing.JPanel exitOptionsPanel;
    private javax.swing.JCheckBox frameDecorationCheckBox;
    private javax.swing.JRadioButton gtkRadioButton;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton jtattooRadioButton;
    private javax.swing.ButtonGroup lafButtonGroup;
    private javax.swing.JLabel langInfoLabel;
    private javax.swing.JList langList;
    private javax.swing.JPanel languagePanel;
    private javax.swing.JPanel lnfPanel;
    private javax.swing.JPanel lookAndFeelPanel;
    private javax.swing.JRadioButton macRadioButton;
    private javax.swing.JRadioButton metalRadioButton;
    private javax.swing.JRadioButton metalThemesEnabledRadioButton;
    private javax.swing.JRadioButton motifRadioButton;
    private javax.swing.JRadioButton nimbusRadioButton;
    private javax.swing.JRadioButton nimrodRadioButton;
    private javax.swing.JRadioButton oceanRadioButton;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JTabbedPane optionsTabbedPane;
    private javax.swing.JButton restoreButton;
    private javax.swing.JCheckBox restoreCatAndUnitCheckBox;
    private javax.swing.JCheckBox showExitAnimationCheckBox;
    private javax.swing.JCheckBox showToolTipsCheckBox;
    private javax.swing.JPanel southPanel;
    private javax.swing.JPanel startOptionsPanel;
    private javax.swing.JPanel startStopPanel;
    private javax.swing.JTabbedPane startStopTabbedPane;
    private javax.swing.JRadioButton systemRadioButton;
    private javax.swing.JList themesList;
    private javax.swing.JPanel themesPanel;
    private javax.swing.JScrollPane themesScrollPane;
    private javax.swing.JLabel toolTipDelayLabel;
    private javax.swing.JSlider toolTipSlider;
    private javax.swing.JTabbedPane uiTabbedPane;
    private javax.swing.JCheckBox updateExchangeRatesCheckBox;
    private javax.swing.JCheckBox useLocalHelpSystemCheckBox;
    private javax.swing.JRadioButton windowsRadioButton;
    // End of variables declaration//GEN-END:variables

}
