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
package net.numericalchameleon.gui.dialogs.updatecenter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import jonelo.sugar.gui.GUIHelper;
import jonelo.sugar.gui.SwingWorker;
import jonelo.sugar.util.GeneralNet;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.i18n.ResBundles;
import net.numericalchameleon.gui.common.interfaces.DialogInterface;
import net.numericalchameleon.gui.common.interfaces.UpdateInterface;
import net.numericalchameleon.update.UpdateRecord;
import net.numericalchameleon.update.modules.ExchangeRatesUpdateModule;
import net.numericalchameleon.update.modules.HomepageUpdateProperties;
import net.numericalchameleon.update.modules.L10nUpdateModule;
import net.numericalchameleon.update.modules.AppUpdateModule;
import net.numericalchameleon.update.modules.TzdataUpdateModule;

public final class UpdateCenterDialog extends javax.swing.JDialog {

    private final UpdateInterface updateInterface;
    private final ResourceBundle rb;
    private final UpdateRecord updateRecord;

    private final static int ROW_NCVER = 0;
    private final static int ROW_L10N = 1;
    private final static int ROW_RATES = 2;
    private final static int ROW_TZDATA = 3;
    private final static int ROW_EXRATES_FILTERS = 4;

    private final static int COL_VENDOR = 0;
    private final static int COL_NAME = 1;
    private final static int COL_INSTALLED = 2;
    private final static int COL_UPDATE = 3;
    private final static int COL_STATUS = 4;

    public final static String LAST_UPDATE_CHECK_PROPERTY_NAME = "last_update_check";
    private final DialogInterface i18nInterface;

    /**
     * Creates new form UpdateCenterDialog
     *
     * @param dialogInterface
     * @param updateInterface
     */
    public UpdateCenterDialog(DialogInterface dialogInterface, UpdateInterface updateInterface) {
        super(dialogInterface.getFrame(), true);

        this.updateInterface = updateInterface;
        this.updateRecord = updateInterface.getUpdateRecord();
        this.rb = dialogInterface.getResourceBundle();
        this.i18nInterface = dialogInterface;

        initComponents();
        myTable.getColumnModel().getColumn(COL_VENDOR).setPreferredWidth(150);
        myTable.getColumnModel().getColumn(COL_NAME).setPreferredWidth(150);
        myTable.getColumnModel().getColumn(COL_INSTALLED).setPreferredWidth(80);
        myTable.getColumnModel().getColumn(COL_UPDATE).setPreferredWidth(80);

        Properties props = updateInterface.getUpdateProperties();
        updateLastCheckedLabel(props);

        ResBundles languages = new ResBundles();
        updateRecord.getL10nVersionModule().setInstalledVersion(languages.getVersion());
        updateRecord.getTzdataModule().setLatestKnownVersion(props);
        updateRecord.getNcVersionModule().setLatestKnownVersion(props);
        updateRecord.getL10nVersionModule().setLatestKnownVersion(props);

        updateRecord.getExchangeRatesVersionModule().setVendor(props);
        updateRecord.getExchangeRatesVersionModule().setInstalledVersion(props);

        updateTableWith(updateRecord);

        myTable.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    switch (myTable.getSelectedRow()) {
                        case ROW_NCVER:
                            gotoNcUpdate();
                            break;
                        case ROW_RATES:
                            gotoRatesUpdate();
                            break;
                        case ROW_TZDATA:
                            gotoTzUpdate();
                            break;
                        case ROW_L10N:
                            gotoL10nUpdate();
                            break;
                        default:
                            System.err.println("missing update handler for " + myTable.getSelectedRow());
                    }

                    // ((DefaultTableModel)jTable1.getModel()).setValueAt("Y", jTable1.getSelectedRow(), 0);
                }
            }
        });

        setLocationRelativeTo(dialogInterface.getFrame());
        setVisible(true);
    }

    public void updateLastCheckedLabel(Properties props) {
        lastCheckLabel.setText(GeneralString.message(rb.getString("GUI.UpdateCenter.lastCheck"),
                props.getProperty(LAST_UPDATE_CHECK_PROPERTY_NAME, "-")));
    }

    public void updateTableWith(UpdateRecord updateRecord) {
        TzdataUpdateModule tzdataModule = updateRecord.getTzdataModule();
        myTable.getModel().setValueAt(
                tzdataModule.getLatestKnownVersion(), ROW_TZDATA, COL_UPDATE);
        myTable.getModel().setValueAt(
                tzdataModule.getStatus(), ROW_TZDATA, COL_STATUS);

        ExchangeRatesUpdateModule exchangeRatesVersionModule = updateRecord.getExchangeRatesVersionModule();
        myTable.getModel().setValueAt(
                exchangeRatesVersionModule.getInstalledVersion(), ROW_RATES, COL_INSTALLED);
        myTable.getModel().setValueAt(
                exchangeRatesVersionModule.getLatestKnownVersion(), ROW_RATES, COL_UPDATE);
        myTable.getModel().setValueAt(
                exchangeRatesVersionModule.getStatus(), ROW_RATES, COL_STATUS);
        myTable.getModel().setValueAt(
                exchangeRatesVersionModule.getVendor(), ROW_RATES, COL_VENDOR);

        AppUpdateModule ncVersionModule = updateRecord.getNcVersionModule();
        myTable.getModel().setValueAt(
                ncVersionModule.getLatestKnownVersion(), ROW_NCVER, COL_UPDATE);
        myTable.getModel().setValueAt(
                ncVersionModule.getStatus(), ROW_NCVER, COL_STATUS);

        L10nUpdateModule l10nVersionModule = updateRecord.getL10nVersionModule();
        myTable.getModel().setValueAt(
                l10nVersionModule.getInstalledVersion(), ROW_L10N, COL_INSTALLED);
        myTable.getModel().setValueAt(
                l10nVersionModule.getLatestKnownVersion(), ROW_L10N, COL_UPDATE);
        myTable.getModel().setValueAt(
                l10nVersionModule.getStatus(), ROW_L10N, COL_STATUS);

    }

    private boolean isModuleAlreadyUpToDate(int rowIndex) {
        return myTable.getModel().getValueAt(rowIndex, COL_INSTALLED).equals(myTable.getModel().getValueAt(rowIndex, COL_UPDATE));
    }

    private void messageModuleIsAlreadyUpToDate() {
        JOptionPane.showMessageDialog(this,
                rb.getString("GUI.UpdateCenter.ModuleIsAlreadyUpToDate"),
                rb.getString("GUI.General.Info"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void gotoRatesUpdate() {
        // do nothing if everything is up to date
        if (isModuleAlreadyUpToDate(ROW_RATES)) {
            messageModuleIsAlreadyUpToDate();
            return;
        }

        // close the update dialog
        formWindowClosing(null);
        // and open the exchange rates dialog
        updateInterface.updateExchangeRates();
    }

    public void gotoTzUpdate() {
        // do nothing if everything is up to date
        if (isModuleAlreadyUpToDate(ROW_TZDATA)) {
            messageModuleIsAlreadyUpToDate();
            return;
        }

        String msg = GeneralString.message(rb.getString("GUI.UpdateCenter.Moduls.TimezoneDatabase.UpdateInstructions"),
                TzdataUpdateModule.DOWNLOAD_URL);
        String title = rb.getString("GUI.General.Info");

        JOptionPane.showMessageDialog(this,
                msg, title, JOptionPane.INFORMATION_MESSAGE);
        try {
            GUIHelper.openInBrowser(TzdataUpdateModule.DOWNLOAD_URL);
        } catch (IOException | URISyntaxException e) {
            System.err.println(e);
        }
    }

    public void gotoL10nUpdate() {
        if (isModuleAlreadyUpToDate(ROW_L10N)) {
            messageModuleIsAlreadyUpToDate();
        }
        // close the update dialog ...
        formWindowClosing(null);
        // ... and open the program update dialog
        updateInterface.appUpdateDialog();

    }

    public void gotoNcUpdate() {
        if (isModuleAlreadyUpToDate(ROW_NCVER)) {
            messageModuleIsAlreadyUpToDate();
        }
        // close the update dialog ...
        formWindowClosing(null);
        // ... and open the program update dialog
        updateInterface.appUpdateDialog();

        /*

        try {
            String url = updateRecord.getNcVersionModule().whatIsTheDownloadURL();
            String title = rb.getString("GUI.General.Info");
            String msg = GeneralString.message(
                    rb.getString("GUI.UpdateCenter.Moduls.NumericalChameleon.UpdateInstructions"),
                    updateRecord.getNcVersionModule().getLatestKnownVersion(),
                    url);
            
            JOptionPane.showMessageDialog(this, 
                    msg, title, JOptionPane.INFORMATION_MESSAGE);

            GUIHelper.openInBrowser(url);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    e.toString(),
                    rb.getString("GUI.General.Error"),
                    JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this,
                    e.toString(),
                    rb.getString("GUI.General.Error"),
                    JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(this,
                    e.toString(),
                    rb.getString("GUI.General.Error"),
                    JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }
         */
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkForUpdatesButton = new javax.swing.JButton();
        lastCheckLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();
        // myTable.getTableHeader().setReorderingAllowed(false);

        myTable.setDefaultRenderer(
            net.numericalchameleon.update.UpdateStatus.class,
            new net.numericalchameleon.gui.dialogs.updatecenter.UpdateStatusRenderer(i18nInterface)
        );
        jLabel3 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        progressBar.setVisible(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(rb.getString("GUI.General.Update")); // NOI18N
        setMinimumSize(new java.awt.Dimension(640, 240));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        checkForUpdatesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/magnifier-left.png"))); // NOI18N
        checkForUpdatesButton.setText(rb.getString("GUI.UpdateCenter.CheckForUpdates")); // NOI18N
        checkForUpdatesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkForUpdatesButtonActionPerformed(evt);
            }
        });

        lastCheckLabel.setText(rb.getString("GUI.UpdateCenter.lastCheck")); // NOI18N

        myTable.setModel(          new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {
                    updateRecord.getNcVersionModule().getVendor(),
                    updateRecord.getNcVersionModule().getName(),
                    updateRecord.getNcVersionModule().getInstalledVersion(),
                    updateRecord.getNcVersionModule().getLatestKnownVersion(),
                    updateRecord.getNcVersionModule().getStatus()
                },
                {
                    updateRecord.getL10nVersionModule().getVendor(),
                    updateRecord.getL10nVersionModule().getName(),
                    updateRecord.getL10nVersionModule().getInstalledVersion(),
                    updateRecord.getL10nVersionModule().getLatestKnownVersion(),
                    updateRecord.getL10nVersionModule().getStatus()
                },
                {
                    updateRecord.getExchangeRatesVersionModule().getVendor(),
                    updateRecord.getExchangeRatesVersionModule().getName(),
                    updateRecord.getExchangeRatesVersionModule().getInstalledVersion(),
                    updateRecord.getExchangeRatesVersionModule().getLatestKnownVersion(),
                    updateRecord.getExchangeRatesVersionModule().getStatus()
                },
                {
                    updateRecord.getTzdataModule().getVendor(),
                    //rb.getString("GUI.UpdateCenter.TimeZoneDataBase"),
                    updateRecord.getTzdataModule().getName(),
                    updateRecord.getTzdataModule().getInstalledVersion(),
                    updateRecord.getTzdataModule().getLatestKnownVersion(),
                    updateRecord.getTzdataModule().getStatus()
                }
            },
            new String [] {

                rb.getString("GUI.UpdateCenter.TableHeader.Vendor"),
                rb.getString("GUI.UpdateCenter.TableHeader.Module"),
                rb.getString("GUI.UpdateCenter.TableHeader.Installed"),
                rb.getString("GUI.UpdateCenter.TableHeader.Available"),
                rb.getString("GUI.UpdateCenter.TableHeader.Status")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.String.class,
                net.numericalchameleon.update.UpdateStatus.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }
    );
    myTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    jScrollPane1.setViewportView(myTable);

    jLabel3.setFont(jLabel3.getFont().deriveFont(jLabel3.getFont().getStyle() | java.awt.Font.BOLD));
    jLabel3.setText(rb.getString("GUI.UpdateCenter.InstructionText")); // NOI18N

    closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/close.png"))); // NOI18N
    closeButton.setText(rb.getString("GUI.General.Close")); // NOI18N
    closeButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeButtonActionPerformed(evt);
        }
    });

    progressBar.setIndeterminate(true);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(checkForUpdatesButton)
                    .addGap(18, 18, 18)
                    .addComponent(lastCheckLabel))
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(jLabel3)
                    .addGap(18, 18, 18)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                    .addComponent(closeButton)))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(checkForUpdatesButton)
                .addComponent(lastCheckLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addComponent(closeButton)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    private void checkForUpdatesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkForUpdatesButtonActionPerformed

        class ModuleWorker {

            public ModuleWorker() {
                start();
                boolean success = true;

                Properties properties = updateInterface.getUpdateProperties();
                HomepageUpdateProperties homepageUpdateProperties = HomepageUpdateProperties.getInstance();
                try {
                    homepageUpdateProperties.load();
                } catch (IOException ioex) {
                    success = false;
                    JOptionPane.showMessageDialog(null, ioex.getMessage(),
                            rb.getString("GUI.General.Error"), JOptionPane.ERROR_MESSAGE);
                }

                // *** timezone data
                try {
                    updateRecord.getTzdataModule().setLatestKnownVersion(
                            GeneralNet.whatIsTheLatestTzdata());
                } catch (Exception e) {
                    success = false;
                    progressBar.setVisible(false);
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            rb.getString("GUI.General.Error"), JOptionPane.ERROR_MESSAGE);
                }
                properties.setProperty(TzdataUpdateModule.LATEST_KNOWN_VERSION_PROPERTY_NAME,
                        updateRecord.getTzdataModule().getLatestKnownVersion());

                //*** NC program version
                updateRecord.getNcVersionModule().setLatestKnownVersion(
                        homepageUpdateProperties.whatIsTheLastestProgramVersion());
                properties.setProperty(AppUpdateModule.LATEST_KNOWN_VERSION_PROPERTY_NAME,
                        updateRecord.getNcVersionModule().getLatestKnownVersion());

                // *** l10n module
                updateRecord.getL10nVersionModule().setLatestKnownVersion(
                        homepageUpdateProperties.whatIsTheLastestL10NVersion());
                properties.setProperty(L10nUpdateModule.LATEST_KNOWN_VERSION_PROPERTY_NAME,
                        updateRecord.getL10nVersionModule().getLatestKnownVersion());

                // *** exchange rates
                properties.setProperty(ExchangeRatesUpdateModule.LATEST_KNOWN_VERSION_PROPERTY_NAME,
                        updateRecord.getExchangeRatesVersionModule().getLatestKnownVersion());

                // all checks must be successfully
                if (success) {
                    String date = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(new Date());
                    properties.setProperty(
                            LAST_UPDATE_CHECK_PROPERTY_NAME,
                            date);
                }

                updateInterface.saveUpdateProperties();

                // graphical updates
                updateLastCheckedLabel(properties);
                updateTableWith(updateRecord);
                done();
            }

            private void start() {
                progressBar.setVisible(true);
                checkForUpdatesButton.setEnabled(false);
                closeButton.setEnabled(false);
            }

            private void done() {
                progressBar.setVisible(false);
                checkForUpdatesButton.setEnabled(true);
                closeButton.setEnabled(true);
            }
        }

        SwingWorker worker = new SwingWorker() {

            @Override
            public Object construct() {
                return new ModuleWorker();
            }
        };
        worker.start();
    }//GEN-LAST:event_checkForUpdatesButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        setVisible(false);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        formWindowClosing(null);
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton checkForUpdatesButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastCheckLabel;
    private javax.swing.JTable myTable;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    class MyTableModel extends AbstractTableModel {

        private UpdateRecord updateRecord;
        private final static boolean DEBUG = false;

        public MyTableModel(UpdateRecord updateRecord) {
            this.updateRecord = updateRecord;
        }
        private final String[] columnNames = {
            "Vendor", "Module", "Installed", "Update Available", "Status"};
        private final Object[][] data = {
            {
                updateRecord.getNcVersionModule().getVendor(),
                updateRecord.getNcVersionModule().getName(),
                updateRecord.getNcVersionModule().getInstalledVersion(),
                updateRecord.getNcVersionModule().getLatestKnownVersion(),
                updateRecord.getNcVersionModule().getStatus()
            },
            {
                updateRecord.getL10nVersionModule().getVendor(),
                updateRecord.getL10nVersionModule().getName(),
                updateRecord.getL10nVersionModule().getInstalledVersion(),
                updateRecord.getL10nVersionModule().getLatestKnownVersion(),
                updateRecord.getL10nVersionModule().getStatus()
            },
            {
                updateRecord.getExchangeRatesVersionModule().getVendor(),
                updateRecord.getExchangeRatesVersionModule().getName(),
                updateRecord.getExchangeRatesVersionModule().getInstalledVersion(),
                updateRecord.getExchangeRatesVersionModule().getLatestKnownVersion(),
                updateRecord.getExchangeRatesVersionModule().getStatus()
            },
            {
                updateRecord.getTzdataModule().getVendor(),
                updateRecord.getTzdataModule().getName(),
                updateRecord.getTzdataModule().getInstalledVersion(),
                updateRecord.getTzdataModule().getLatestKnownVersion(),
                updateRecord.getTzdataModule().getStatus()
            }

        };

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /**
         * JTable uses this method to determine the default renderer/ editor for
         * each cell. If we didn't implement this method, then the a boolean
         * column would contain text ("true"/"false"), rather than a check box.
         */
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /**
         * Don't need to implement this method unless your table's editable.
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            return true;
        }

        /**
         * Don't need to implement this method unless your table's data can
         * change.
         */
        @Override
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
}
