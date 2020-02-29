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
package net.numericalchameleon.gui.dialogs.exchangerates;

import net.numericalchameleon.util.net.DownloadTaskInterface;
import net.numericalchameleon.util.net.DownloadTask;
import net.numericalchameleon.util.exchangerates.ExchangeRatesFilters;
import net.numericalchameleon.gui.dialogs.exchangerates.renderer.ExchangeRatesSortActionCellRenderer;
import net.numericalchameleon.gui.dialogs.exchangerates.renderer.ExchangeRatesServiceCellRenderer;
import net.numericalchameleon.util.exchangerates.sort.SortByQuantityDescending;
import net.numericalchameleon.util.exchangerates.sort.SortByAlphabet;
import net.numericalchameleon.util.exchangerates.sort.SortByAlphabetDescending;
import net.numericalchameleon.util.exchangerates.sort.SortAction;
import net.numericalchameleon.util.exchangerates.sort.SortByQuantity;
import net.numericalchameleon.util.exchangerates.sort.SortByLocation;
import net.numericalchameleon.util.exchangerates.ExchangeRatesFilter;
import java.awt.ComponentOrientation;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import jonelo.sugar.gui.GUIHelper;
import jonelo.sugar.gui.GeneralGUI;
import jonelo.sugar.io.ExampleFileFilter;
import jonelo.sugar.io.GeneralIO;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.util.exchangerates.ExchangeRates;
import net.numericalchameleon.util.io.IOHelper;

public class ExchangeRatesDialog extends javax.swing.JDialog implements PropertyChangeListener, DownloadTaskInterface {

    private javax.swing.Timer timer;
    private ExchangeRatesUpdateTask task;
    private static boolean debug = false;
    private ResourceBundle rb;
    private JFileChooser openChooser;
    private ExchangeRates exchangeRates;
    private ExchangeRatesFilter filter;
    private ResourceBundle iso3166;
    private ExchangeRatesDialogInterface dialogInterface;
    private Properties propertiesForAllUsers;
    private ExchangeRatesFilters exchangeRatesFilters;
    private File localFile;

    /**
     * Creates new form ExchangeRatesDialog
     */
    public ExchangeRatesDialog(ExchangeRatesDialogInterface dialogInterface, ExchangeRates exchangeRates) {
        super(dialogInterface.getFrame(), true);
        this.dialogInterface = dialogInterface;

        this.exchangeRates = exchangeRates;
        this.rb = dialogInterface.getResourceBundle();
        this.iso3166 = dialogInterface.getISO3166ResourceBundle();
        this.propertiesForAllUsers = exchangeRates.getPropertiesForAllUsers();

        if (dialogInterface.getFrame().isUndecorated()) {
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        }

        setVisible(false);
        openChooser = new JFileChooser();
        task = new ExchangeRatesUpdateTask(exchangeRates);
        task.setISO3166ResourceBundle(iso3166);
        exchangeRatesFilters = new ExchangeRatesFilters();
        initComponents();

        progressBar.setValue(0);
        progressBar.setMinimum(0);
        progressBar.setMaximum(task.getLengthOfTask());
        progressBar.setStringPainted(true);

        timer = new javax.swing.Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                int here = task.getCurrent();
                progressBar.setValue(here);
                progressBar.setString((here * 100 / task.getLengthOfTask()) + " %");

                if (task.done()) {
                    //Toolkit.getDefaultToolkit().beep();
                    timer.stop();
                    startButton.setEnabled(true);
                    logLabel.setIcon(null);
                    progressBar.setValue(progressBar.getMinimum());
                    progressBar.setString("0 %");
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        prepareExchangeRateUpdateList();
        sortComboBox.setRenderer(new ExchangeRatesSortActionCellRenderer(rb));
        chooseOneRateServiceLabel.setText(GeneralString.message(rb.getString("GUI.Rates.SelectAService.Label"), exchangeRatesFilters.size()));

        setLocationRelativeTo(dialogInterface.getFrame());
        ComponentOrientation ce = ComponentOrientation.getOrientation(Locale.getDefault());
        if (!ce.isLeftToRight()) {
            GeneralGUI.applyOrientation(dialogPanel, ce);
        }

        setVisible(true);
    }

    public static void setVerbose(boolean flag) {
        debug = flag;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        visitPopupMenu = new javax.swing.JPopupMenu();
        copyMenuItem = new javax.swing.JMenuItem();
        dialogPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        appendCheckBox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        stepsTabbedPane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        chooseOneRateServiceLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        rateServiceComboBox = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        leftButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        providerNameDescriptionLabel = new javax.swing.JLabel();
        providerNameValueLabel = new javax.swing.JLabel();
        domainNameDescriptionLabel = new javax.swing.JLabel();
        domainNameValueLabel = new javax.swing.JLabel();
        supportedExchangeRatesDescriptionLabel = new javax.swing.JLabel();
        supportedExchangeRatesValueLabel = new javax.swing.JLabel();
        directDownloadIsSupportedDescriptionLabel = new javax.swing.JLabel();
        directDownloadIsSupportedValueLabel = new javax.swing.JLabel();
        filterIDDescriptionLabel = new javax.swing.JLabel();
        filterIDValueLabel = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        toStep2Button = new javax.swing.JButton();
        optionToggleButton = new javax.swing.JToggleButton();
        optionPanel = new javax.swing.JPanel();
        optionPanel.setVisible(false);
        sortComboBox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        visitTextField = new javax.swing.JTextField();
        copyButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        instructionTextArea = new javax.swing.JTextArea();
        toStep1Button = new javax.swing.JButton();
        toStep3Button = new javax.swing.JButton();
        downloadProgressBar = new javax.swing.JProgressBar();
        downloadButton = new javax.swing.JButton();
        pageComboBox = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        filenameTextField = new javax.swing.JTextField();
        filechooseButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        logLabel = new javax.swing.JLabel();
        toStep2ButtonB = new javax.swing.JButton();

        copyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/edit-copy.png"))); // NOI18N
        copyMenuItem.setText(rb.getString("GUI.General.Copy")); // NOI18N
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        visitPopupMenu.add(copyMenuItem);

        setTitle(rb.getString("GUI.Menu.Main.UpdateExchangeRates")); // NOI18N
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        dialogPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        dialogPanel.setMinimumSize(new java.awt.Dimension(600, 600));
        dialogPanel.setPreferredSize(new java.awt.Dimension(600, 600));
        dialogPanel.setLayout(new java.awt.GridBagLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        appendCheckBox.setText(rb.getString("GUI.Rates.Append")); // NOI18N
        appendCheckBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        appendCheckBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        appendCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                appendCheckBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel5.add(appendCheckBox, gridBagConstraints);

        okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/update.png"))); // NOI18N
        okButton.setText(rb.getString("GUI.General.Update")); // NOI18N
        okButton.setEnabled(false);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(okButton, gridBagConstraints);

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/close.png"))); // NOI18N
        cancelButton.setText(rb.getString("GUI.General.Cancel")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel5.add(cancelButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 15);
        dialogPanel.add(jPanel5, gridBagConstraints);

        stepsTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                stepsTabbedPaneStateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        chooseOneRateServiceLabel.setText(rb.getString("GUI.Rates.SelectAService.Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(chooseOneRateServiceLabel, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        rateServiceComboBox.setMaximumRowCount(16);
        rateServiceComboBox.setMinimumSize(new java.awt.Dimension(320, 26));
        rateServiceComboBox.setOpaque(false);
        rateServiceComboBox.setPreferredSize(new java.awt.Dimension(320, 26));
        rateServiceComboBox.setRenderer(new ExchangeRatesServiceCellRenderer(dialogInterface.getISO3166ResourceBundle()));
        rateServiceComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rateServiceComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        jPanel7.add(rateServiceComboBox, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridLayout(2, 0));

        leftButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/up.png"))); // NOI18N
        leftButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        leftButton.setMaximumSize(new java.awt.Dimension(21, 21));
        leftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftButtonActionPerformed(evt);
            }
        });
        jPanel6.add(leftButton);

        rightButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix6x3/down.png"))); // NOI18N
        rightButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
        rightButton.setMaximumSize(new java.awt.Dimension(21, 21));
        rightButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightButtonActionPerformed(evt);
            }
        });
        jPanel6.add(rightButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel7.add(jPanel6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(jPanel7, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        providerNameDescriptionLabel.setText("Provider name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 10);
        jPanel1.add(providerNameDescriptionLabel, gridBagConstraints);

        providerNameValueLabel.setText("provider name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        jPanel1.add(providerNameValueLabel, gridBagConstraints);

        domainNameDescriptionLabel.setText("Domain:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(domainNameDescriptionLabel, gridBagConstraints);

        domainNameValueLabel.setText("domain name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(domainNameValueLabel, gridBagConstraints);

        supportedExchangeRatesDescriptionLabel.setText("Supported exchange rates:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(supportedExchangeRatesDescriptionLabel, gridBagConstraints);

        supportedExchangeRatesValueLabel.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(supportedExchangeRatesValueLabel, gridBagConstraints);

        directDownloadIsSupportedDescriptionLabel.setText("Direct download is supported:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(directDownloadIsSupportedDescriptionLabel, gridBagConstraints);

        directDownloadIsSupportedValueLabel.setText("yes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(directDownloadIsSupportedValueLabel, gridBagConstraints);

        filterIDDescriptionLabel.setText("Filter ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(filterIDDescriptionLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(filterIDValueLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel2.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jPanel8, gridBagConstraints);

        toStep2Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-next.png"))); // NOI18N
        toStep2Button.setText(rb.getString("GUI.Rates.Continue.Button")); // NOI18N
        toStep2Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        toStep2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStep2ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel2.add(toStep2Button, gridBagConstraints);

        optionToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix10x10/arrow-right.png"))); // NOI18N
        optionToggleButton.setText("Optionen");
        optionToggleButton.setBorderPainted(false);
        optionToggleButton.setContentAreaFilled(false);
        optionToggleButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        optionToggleButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix10x10/arrow-down.png"))); // NOI18N
        optionToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                optionToggleButtonStateChanged(evt);
            }
        });
        optionToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionToggleButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(optionToggleButton, gridBagConstraints);

        optionPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sortComboBox.setModel(getComboBoxSortModel(populateSortItems()));
        sortComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sortComboBoxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortComboBox, 0, 606, Short.MAX_VALUE)
                .addContainerGap())
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel2.add(optionPanel, gridBagConstraints);

        stepsTabbedPane.addTab("1.", new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/currency.png")), jPanel2); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel5.setText(rb.getString("GUI.Rates.Visit.Label")); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(320, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel3.add(jLabel5, gridBagConstraints);

        visitTextField.setEditable(false);
        visitTextField.setMinimumSize(new java.awt.Dimension(290, 21));
        visitTextField.setPreferredSize(new java.awt.Dimension(290, 21));
        visitTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visitTextFieldMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                visitTextFieldMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                visitTextFieldMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(visitTextField, gridBagConstraints);

        copyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/earth.png"))); // NOI18N
        copyButton.setText(rb.getString("GUI.General.Go")); // NOI18N
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(copyButton, gridBagConstraints);

        jLabel7.setText(rb.getString("GUI.Rates.Visit.Instructions.Label")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(jLabel7, gridBagConstraints);

        instructionTextArea.setColumns(60);
        instructionTextArea.setEditable(false);
        instructionTextArea.setFont(new java.awt.Font("Courier", 0, 12)); // NOI18N
        instructionTextArea.setRows(10);
        jScrollPane1.setViewportView(instructionTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel3.add(jScrollPane1, gridBagConstraints);

        toStep1Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-previous.png"))); // NOI18N
        toStep1Button.setText(rb.getString("GUI.Rates.Back.Button")); // NOI18N
        toStep1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStep1ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(toStep1Button, gridBagConstraints);

        toStep3Button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-next.png"))); // NOI18N
        toStep3Button.setText(rb.getString("GUI.Rates.Continue.Button")); // NOI18N
        toStep3Button.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        toStep3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStep3ButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel3.add(toStep3Button, gridBagConstraints);

        downloadProgressBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel3.add(downloadProgressBar, gridBagConstraints);

        downloadButton.setText("Download");
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(downloadButton, gridBagConstraints);

        pageComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pageComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel3.add(pageComboBox, gridBagConstraints);

        stepsTabbedPane.addTab("2.", new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/earth.png")), jPanel3); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText(rb.getString("GUI.Rates.Include.Label")); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(320, 17));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(jLabel6, gridBagConstraints);

        filenameTextField.setMinimumSize(new java.awt.Dimension(250, 21));
        filenameTextField.setPreferredSize(new java.awt.Dimension(250, 21));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(filenameTextField, gridBagConstraints);

        filechooseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/document-open.png"))); // NOI18N
        filechooseButton.setText(rb.getString("GUI.General.SelectFile")); // NOI18N
        filechooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filechooseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(filechooseButton, gridBagConstraints);

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        startButton.setText(rb.getString("GUI.Rates.Include.Start")); // NOI18N
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(startButton, gridBagConstraints);

        progressBar.setMinimumSize(new java.awt.Dimension(250, 14));
        progressBar.setName("progressBar"); // NOI18N
        progressBar.setPreferredSize(new java.awt.Dimension(250, 14));
        progressBar.setString("0 %");
        progressBar.setStringPainted(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        jPanel4.add(progressBar, gridBagConstraints);

        logTextArea.setEditable(false);
        logTextArea.setColumns(60);
        logTextArea.setFont(new java.awt.Font("Courier", 0, 12)); // NOI18N
        logTextArea.setRows(10);
        jScrollPane2.setViewportView(logTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jScrollPane2, gridBagConstraints);

        logLabel.setText(rb.getString("GUI.Rates.Include.Log")); // NOI18N
        logLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel4.add(logLabel, gridBagConstraints);

        toStep2ButtonB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-previous.png"))); // NOI18N
        toStep2ButtonB.setText(rb.getString("GUI.Rates.Back.Button")); // NOI18N
        toStep2ButtonB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toStep2ButtonBActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        jPanel4.add(toStep2ButtonB, gridBagConstraints);

        stepsTabbedPane.addTab("3.", new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix24x24/magnifier-left.png")), jPanel4); // NOI18N

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        dialogPanel.add(stepsTabbedPane, gridBagConstraints);

        getContentPane().add(dialogPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        GeneralIO.setClipboard(visitTextField.getText());
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void visitTextFieldMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visitTextFieldMouseReleased
        checkPopupMenu(evt, visitPopupMenu);
    }//GEN-LAST:event_visitTextFieldMouseReleased

    private void visitTextFieldMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visitTextFieldMousePressed
        checkPopupMenu(evt, visitPopupMenu);
    }//GEN-LAST:event_visitTextFieldMousePressed

    private void visitTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visitTextFieldMouseClicked
        checkPopupMenu(evt, visitPopupMenu);
    }//GEN-LAST:event_visitTextFieldMouseClicked

    private void checkPopupMenu(MouseEvent event, JPopupMenu popupMenu) {
        if (event.isPopupTrigger()) {
            popupMenu.show(event.getComponent(), event.getX(), event.getY());
        }
    }

    private void toStep2ButtonBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStep2ButtonBActionPerformed
        stepsTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_toStep2ButtonBActionPerformed

    private void appendCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_appendCheckBoxItemStateChanged
        int change = evt.getStateChange();
        if (change == ItemEvent.SELECTED) {
            exchangeRates.setAppend(true);
        } else if (change == ItemEvent.DESELECTED) {
            exchangeRates.setAppend(false);
        }
    }//GEN-LAST:event_appendCheckBoxItemStateChanged

    private void toStep1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStep1ButtonActionPerformed
        stepsTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_toStep1ButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        closeWindow();
    }//GEN-LAST:event_okButtonActionPerformed

    private void rightButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightButtonActionPerformed
        int now = rateServiceComboBox.getSelectedIndex();
        int all = rateServiceComboBox.getItemCount();
        if (now + 1 < all) {
            rateServiceComboBox.setSelectedIndex(now + 1);
        } else {
            rateServiceComboBox.setSelectedIndex(0);
        }

    }//GEN-LAST:event_rightButtonActionPerformed

    private void leftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftButtonActionPerformed
        int now = rateServiceComboBox.getSelectedIndex();
        int all = rateServiceComboBox.getItemCount();
        if (now > 0) {
            rateServiceComboBox.setSelectedIndex(now - 1);
        } else {
            rateServiceComboBox.setSelectedIndex(all - 1);
        }
    }//GEN-LAST:event_leftButtonActionPerformed

    private void stepsTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_stepsTabbedPaneStateChanged
        if (stepsTabbedPane.getSelectedIndex() == 2) {
            appendCheckBox.setVisible(filter.isAllowAppend());
        } else {
            appendCheckBox.setVisible(false);
        }
        visitTextField.setCaretPosition(0);
    }//GEN-LAST:event_stepsTabbedPaneStateChanged

    private void toStep2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStep2ButtonActionPerformed
        stepsTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_toStep2ButtonActionPerformed

    private void toStep3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toStep3ButtonActionPerformed
        stepsTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_toStep3ButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancel();
        closeWindow();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void filechooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filechooseButtonActionPerformed
        pressOpen();
    }//GEN-LAST:event_filechooseButtonActionPerformed

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        for (String address : filter.getWebsites()) {
            // visit the website
            try {
                GUIHelper.openInBrowser(address);
            } catch (IOException | URISyntaxException e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_copyButtonActionPerformed

    public void checkFileForInput(String filename) throws Exception {
        if (filename == null) {
            throw new Exception(rb.getString("Error.FileNotSpecified"));
        }
        if (filename.equals("")) {
            throw new Exception(rb.getString("Error.FileNotSpecified"));
        }

        File file = new File(filenameTextField.getText());
        if (!file.exists()) {
            throw new Exception(GeneralString.message(rb.getString("Error.FileDoesNotExist"), filename));
        }
        if (!file.isFile()) {
            throw new Exception(GeneralString.message(rb.getString("Error.NotAFile"), filename));
        }
        if (!file.canRead()) {
            throw new Exception(GeneralString.message(rb.getString("Error.ReadProblem"), filename));
        }
    }

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // check input
        try {
            // logLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pixlarge/throbber.gif")));
            startButton.setEnabled(false);
            okButton.setEnabled(false);
            checkFileForInput(filenameTextField.getText());
            exchangeRates.clear();
            task.setExchangeRatesFilter(filter);
            task.setIncludeFilename(filenameTextField.getText()); // "../data/rates/rates.html"
            task.setTextArea(logTextArea);
            task.setButton(okButton);
            logTextArea.append("");
            task.go();
            timer.start();
        } catch (Exception e) {
            //logLabel.setIcon(null);
            JOptionPane.showMessageDialog(this, e.toString(), rb.getString("Error.errorname"), JOptionPane.ERROR_MESSAGE);
            //logLabel.setIcon(null);
            startButton.setEnabled(true);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void rateServiceComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rateServiceComboBoxItemStateChanged
        rateServiceComboBoxItemStateChanged();
    }//GEN-LAST:event_rateServiceComboBoxItemStateChanged

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        cancel();
        closeWindow();
    }//GEN-LAST:event_closeDialog

    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadButtonActionPerformed
        if (!filter.isDirectDownloadAllowed()) {
            JOptionPane.showMessageDialog(this, rb.getString("GUI.Rates.DirectDownloadHasBeenDisabled"));
        } else {
            try {
                localFile = IOHelper.createTempFile("rates", "html");
                downloadTask = new DownloadTask(this, filter.getWebsites(), localFile, filter.getUseragent());
                downloadTask.addPropertyChangeListener(this);
                downloadTask.execute();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, rb.getString("Error.DownloadError"));
            }
        }
    }//GEN-LAST:event_downloadButtonActionPerformed

    private void pageComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pageComboBoxItemStateChanged
        String selected = (String) pageComboBox.getSelectedItem();
        GeneralIO.setClipboard(selected);
    }//GEN-LAST:event_pageComboBoxItemStateChanged

    private void sortComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sortComboBoxItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            SortAction sortAction = (SortAction) sortComboBox.getSelectedItem();
            sortAction.sort();
            rateServiceComboBox.setModel(getComboBoxModel(exchangeRatesFilters.getFilters()));
            rateServiceComboBox.setSelectedIndex(0);
            rateServiceComboBoxItemStateChanged();
        }
    }//GEN-LAST:event_sortComboBoxItemStateChanged

    private void optionToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionToggleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionToggleButtonActionPerformed

    private void optionToggleButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_optionToggleButtonStateChanged

        optionPanel.setVisible(optionToggleButton.isSelected());
        pack();
    }//GEN-LAST:event_optionToggleButtonStateChanged
    private DownloadTask downloadTask;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            downloadProgressBar.setValue(progress);
        }
    }

    private void cancel() {
        task.stop();
        exchangeRates.clear();
    }

    private void closeWindow() {
        setVisible(false);
        dispose();
    }

    private static String domainName(String domain) {
        URI uri = null;
        try {
            uri = new URI(domain);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ExchangeRatesDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uri.getHost();
    }

    private void rateServiceComboBoxItemStateChanged() {
        logTextArea.setText("");
        try {
            filter = (ExchangeRatesFilter) rateServiceComboBox.getSelectedItem();
            exchangeRates.setPreferredFilter(filter.getFilename());

            openChooser.resetChoosableFileFilters();
            String[] filetypes = filter.getFiletypes();
            if (filetypes != null) {
                ExampleFileFilter eff = new ExampleFileFilter(filetypes);
                openChooser.addChoosableFileFilter(eff);
                openChooser.setFileFilter(eff);
            }

            filterIDValueLabel.setText(filter.getFilename());
            providerNameValueLabel.setText(filter.getProvider());
            domainNameValueLabel.setText(domainName(filter.getWebsites()[0]));
            supportedExchangeRatesValueLabel.setText(filter.getSupportedRates());
            directDownloadIsSupportedValueLabel.setText(filter.isDirectDownloadAllowed() ? "yes": "no");
            instructionTextArea.setText(filter.getInstruction());
            instructionTextArea.setCaretPosition(0);
            appendCheckBox.setVisible(false);

            if (filter.getWebsites().length > 1) {
                pageComboBox.setVisible(true);
                pageComboBox.setModel(GeneralGUI.getComboBoxModel(filter.getWebsites()));
                visitTextField.setVisible(false);
            } else {
                pageComboBox.setVisible(false);
                //pageComboBox.setModel(GeneralGUI.getComboBoxModel(pages));
                visitTextField.setVisible(true);
                visitTextField.setText(filter.getWebsites()[0]);
                GeneralIO.setClipboard(filter.getWebsites()[0]);
            }

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    private void prepareExchangeRateUpdateList() {
        if (debug) {
            System.out.print("Loading exchange rates update filter ... ");
        }

        exchangeRatesFilters.load(propertiesForAllUsers, rb.getString("GUI.Rates.NoFilter"));
        rateServiceComboBox.setModel(getComboBoxModel(exchangeRatesFilters.getFilters()));

        // set the preferred ExchangeRatesFilter in the combo box
        if (exchangeRates.getPreferredFilter() != null && exchangeRates.getPreferredFilter().length() > 0) {
            rateServiceComboBox.setSelectedItem(exchangeRatesFilters.getPreferredFilterByName(exchangeRates.getPreferredFilter()));
            rateServiceComboBoxItemStateChanged();
        }

        if (debug) {
            System.out.println("OK.");
        }

    }

    private javax.swing.ComboBoxModel getComboBoxModel(Vector data) {
        javax.swing.JComboBox cb = new javax.swing.JComboBox(data);
        return cb.getModel();
    }

    private javax.swing.ComboBoxModel getComboBoxModel(ArrayList<ExchangeRatesFilter> arrayList) {
        ExchangeRatesFilter[] array = arrayList.toArray(new ExchangeRatesFilter[arrayList.size()]);
        return new DefaultComboBoxModel(array);
    }

    private ArrayList<SortAction> populateSortItems() {
        ArrayList<SortAction> sortActions = new ArrayList<>();
        sortActions.add(new SortByLocation(exchangeRatesFilters.getFilters(), "sort-by-location", "sort-location.png", "GUI.ExchangeRates.Filter.SortByLocation"));
        sortActions.add(new SortByQuantity(exchangeRatesFilters.getFilters(), "sort-by-quantity", "sort-quantity.png", "GUI.ExchangeRates.Filter.SortByQuantity"));
        sortActions.add(new SortByQuantityDescending(
                exchangeRatesFilters.getFilters(), "sort-by-quantity-descending", "sort-quantity-descending.png", "GUI.ExchangeRates.Filter.SortByQuantity.Descending"));
        sortActions.add(new SortByAlphabet(exchangeRatesFilters.getFilters(), "sort-by-alphabet", "sort-alphabet.png", "GUI.ExchangeRates.Filter.SortByAlphabet"));
        sortActions.add(new SortByAlphabetDescending(
                exchangeRatesFilters.getFilters(), "sort-by-alphabet-descending", "sort-alphabet-descending.png", "GUI.ExchangeRates.Filter.SortByAlphabet.Descending"));
        return sortActions;
    }

    private javax.swing.ComboBoxModel getComboBoxSortModel(ArrayList<SortAction> arrayList) {
        SortAction[] array = arrayList.toArray(new SortAction[arrayList.size()]);
        return new DefaultComboBoxModel(array);
    }

    private void pressOpen() {
        String temp = exchangeRates.getPreferredDirectory();
        if (temp != null) {
            openChooser.setCurrentDirectory(new File(temp));
        }
        int state = openChooser.showOpenDialog(this);
        File sourceFile = openChooser.getSelectedFile();
        if ((sourceFile != null) && (state == JFileChooser.APPROVE_OPTION)) {
            filenameTextField.setText(sourceFile.getPath());
            exchangeRates.setPreferredDirectory(sourceFile.getAbsolutePath());
        }
        /*
        else // Cancel is pressed
        if(state == JFileChooser.CANCEL_OPTION)
        {
        } else // FrameCooser is closed
        if(state == JFileChooser.ERROR_OPTION)
        {
        }
         */
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox appendCheckBox;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel chooseOneRateServiceLabel;
    private javax.swing.JButton copyButton;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JPanel dialogPanel;
    private javax.swing.JLabel directDownloadIsSupportedDescriptionLabel;
    private javax.swing.JLabel directDownloadIsSupportedValueLabel;
    private javax.swing.JLabel domainNameDescriptionLabel;
    private javax.swing.JLabel domainNameValueLabel;
    private javax.swing.JButton downloadButton;
    private javax.swing.JProgressBar downloadProgressBar;
    private javax.swing.JButton filechooseButton;
    private javax.swing.JTextField filenameTextField;
    private javax.swing.JLabel filterIDDescriptionLabel;
    private javax.swing.JLabel filterIDValueLabel;
    private javax.swing.JTextArea instructionTextArea;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton leftButton;
    private javax.swing.JLabel logLabel;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JToggleButton optionToggleButton;
    private javax.swing.JComboBox pageComboBox;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel providerNameDescriptionLabel;
    private javax.swing.JLabel providerNameValueLabel;
    private javax.swing.JComboBox rateServiceComboBox;
    private javax.swing.JButton rightButton;
    private javax.swing.JComboBox<String> sortComboBox;
    private javax.swing.JButton startButton;
    private javax.swing.JTabbedPane stepsTabbedPane;
    private javax.swing.JLabel supportedExchangeRatesDescriptionLabel;
    private javax.swing.JLabel supportedExchangeRatesValueLabel;
    private javax.swing.JButton toStep1Button;
    private javax.swing.JButton toStep2Button;
    private javax.swing.JButton toStep2ButtonB;
    private javax.swing.JButton toStep3Button;
    private javax.swing.JPopupMenu visitPopupMenu;
    private javax.swing.JTextField visitTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void downloadProcessStarted() {
        downloadButton.setEnabled(false);
    }

    @Override
    public void downloadProcessStopped() {
        downloadButton.setEnabled(true);
    }

    @Override
    public void downloadWasSuccessful() {
        filenameTextField.setText(localFile.toString());
        stepsTabbedPane.setSelectedIndex(2);
    }

    public void downloadWasNotSuccessful() {
        JOptionPane.showMessageDialog(null, rb.getString("Error.DownloadError"));
    }

}
