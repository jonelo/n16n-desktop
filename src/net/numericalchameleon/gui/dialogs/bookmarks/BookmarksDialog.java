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
package net.numericalchameleon.gui.dialogs.bookmarks;

import java.awt.ComponentOrientation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import jonelo.sugar.gui.GeneralGUI;
import jonelo.sugar.util.GeneralString;
import net.numericalchameleon.data.Bookmark;
import net.numericalchameleon.data.Category;
import net.numericalchameleon.info.ProgConstants;


public class BookmarksDialog extends javax.swing.JDialog {

    private BookmarksModel bookmarks;
    private BookmarksModel bookmarksBackup;
    private HashMap<String,Category> categoriesHashtable;
    private ResourceBundle rb;
    private BookmarksInterface bookmarkInterface;

    public BookmarksDialog(BookmarksInterface bookmarkInterface) {

        super(bookmarkInterface.getFrame(), false);
        if (bookmarkInterface.getFrame().isUndecorated()) {
            setUndecorated(true);
            getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        }
        this.rb = bookmarkInterface.getResourceBundle();
        setTitle(rb.getString("GUI.Menu.Favorites.Manage"));
        this.bookmarkInterface = bookmarkInterface;
        this.bookmarks = bookmarkInterface.getBookmarks();
        this.bookmarksBackup = (BookmarksModel)bookmarks.clone();
        try {
            bookmarksBackup.read();
        } catch (Exception e) {
            System.err.println(e);
        }
        this.categoriesHashtable = bookmarkInterface.getCategoryHashMap();
        initComponents();
        updateCountLabel();
        bookmarksList.setModel(bookmarks);
        if (bookmarks.getSize() > 0) {
            int index = bookmarks.getSize()-1;
            bookmarksList.setSelectedIndex(index);
            bookmarksList.ensureIndexIsVisible(index);
        }

        setLocationRelativeTo(bookmarkInterface.getFrame());
        ComponentOrientation ce = ComponentOrientation.getOrientation(Locale.getDefault());
        if (!ce.isLeftToRight()) GeneralGUI.applyOrientation(bookmarkPanel, ce);
        cancelButton.requestFocus();

        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bookmarkPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        countLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookmarksList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        topButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        bottomButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        renameButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        restoreButton = new javax.swing.JButton();
        saveCloseButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        bookmarkPanel.setLayout(new java.awt.GridBagLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        countLabel.setText("0 Favorites");
        jPanel5.add(countLabel, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel5, java.awt.BorderLayout.SOUTH);

        bookmarksList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookmarksList.setVisibleRowCount(12);
        bookmarksList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookmarksListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookmarksList);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bookmarkPanel.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        topButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-top.png"))); // NOI18N
        topButton.setText(rb.getString("GUI.Bookmarks.GoTop")); // NOI18N
        topButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        topButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(topButton, gridBagConstraints);

        upButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-up.png"))); // NOI18N
        upButton.setText(rb.getString("GUI.Bookmarks.Up")); // NOI18N
        upButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(upButton, gridBagConstraints);

        downButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-down.png"))); // NOI18N
        downButton.setText(rb.getString("GUI.Bookmarks.Down")); // NOI18N
        downButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(downButton, gridBagConstraints);

        bottomButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-bottom.png"))); // NOI18N
        bottomButton.setText(rb.getString("GUI.Bookmarks.GoBottom")); // NOI18N
        bottomButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bottomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottomButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(bottomButton, gridBagConstraints);

        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/info.png"))); // NOI18N
        infoButton.setText(rb.getString("GUI.Bookmarks.Info")); // NOI18N
        infoButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(infoButton, gridBagConstraints);

        renameButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/editor.png"))); // NOI18N
        renameButton.setText(rb.getString("GUI.Bookmarks.Rename")); // NOI18N
        renameButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        renameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renameButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(renameButton, gridBagConstraints);

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/trash-empty.png"))); // NOI18N
        removeButton.setText(rb.getString("GUI.Bookmarks.Remove")); // NOI18N
        removeButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel4.add(removeButton, gridBagConstraints);

        goButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/go-next.png"))); // NOI18N
        goButton.setText(rb.getString("GUI.Bookmarks.GoTo")); // NOI18N
        goButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(goButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        bookmarkPanel.add(jPanel4, gridBagConstraints);

        getContentPane().add(bookmarkPanel, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.FlowLayout(2));

        restoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/undo.png"))); // NOI18N
        restoreButton.setText(rb.getString("GUI.General.Restore")); // NOI18N
        restoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreButtonActionPerformed(evt);
            }
        });
        jPanel2.add(restoreButton);

        saveCloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/save.png"))); // NOI18N
        saveCloseButton.setText(rb.getString("GUI.Bookmarks.SaveAndClose")); // NOI18N
        saveCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveCloseButtonActionPerformed(evt);
            }
        });
        jPanel2.add(saveCloseButton);

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/close.png"))); // NOI18N
        cancelButton.setText(rb.getString("GUI.General.Cancel")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelButton);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-568)/2, (screenSize.height-514)/2, 568, 514);
    }// </editor-fold>//GEN-END:initComponents

    private void bottomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottomButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          while (pos < bookmarks.getSize()-1) {
              change(pos, pos+1);
              pos++;
          }
          bookmarksList.ensureIndexIsVisible(pos);
        }
    }//GEN-LAST:event_bottomButtonActionPerformed

    private void topButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          while (pos > 0) {
              change(pos, pos-1);
              pos--;
          }
          bookmarksList.ensureIndexIsVisible(pos);
        }
    }//GEN-LAST:event_topButtonActionPerformed

    public void updateList() {
        bookmarksList.setSelectedIndex(bookmarks.getSize()-1);
        bookmarksList.ensureIndexIsVisible(bookmarks.getSize()-1);
    }

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
            try {
               bookmarkInterface.visitBookmark((Bookmark)bookmarks.get(bookmarksList.getSelectedIndex()));
            } catch (Exception e) {
                // Bookmark points to a non-existing entry, remove the bookmark?
                int result = JOptionPane.showConfirmDialog(this, 
                         rb.getString("GUI.Bookmarks.Dialog.BookmarkOutdatedRemove"),
                         rb.getString("GUI.Bookmarks.Dialog.BookmarkOutdatedRemove"),
                         JOptionPane.YES_NO_CANCEL_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix32x32/face-surprise.png"))  );
                if (result == JOptionPane.YES_OPTION) {
                    removeButtonActionPerformed(null);
                }
            }
        }
    }//GEN-LAST:event_goButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          Bookmark bookmark = (Bookmark)bookmarks.get(pos);
          BookmarkInfoPanel bookmarkInfoPanel = new BookmarkInfoPanel(rb);
          fillPanel(bookmarkInfoPanel, bookmark);
          JOptionPane.showMessageDialog(this, bookmarkInfoPanel, "Info", JOptionPane.PLAIN_MESSAGE);
        }

    }//GEN-LAST:event_infoButtonActionPerformed

    private void bookmarksListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmarksListMouseClicked
        // double-click
        if (evt.getClickCount() == 2) {
            goButtonActionPerformed(null);
        }
    }//GEN-LAST:event_bookmarksListMouseClicked

    private void fillPanel(BookmarkInfoPanel bookmarkInfoPanel, Bookmark bookmark) {
         bookmarkInfoPanel.getCategoryLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource(ProgConstants.ICONS_CATEGORIES + bookmark.getCategory().toLowerCase()+".png")));
         bookmarkInfoPanel.getCategoryLabel().setText((categoriesHashtable.get(bookmark.getCategory())).getString());
         bookmarkInfoPanel.getSourceLabel().setText(bookmark.getSourceDescription());
         bookmarkInfoPanel.getSourceLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource(ProgConstants.ICONS_FLAGS + bookmark.getSourceCountry()+".png")));
         bookmarkInfoPanel.getTargetLabel().setText(bookmark.getTargetDescription());
         bookmarkInfoPanel.getTargetLabel().setIcon(new javax.swing.ImageIcon(getClass().getResource(ProgConstants.ICONS_FLAGS + bookmark.getTargetCountry()+".png")));
    }

    private void renameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renameButtonActionPerformed
         if (!bookmarksList.isSelectionEmpty()) {
          String input = null;
          boolean end = false;
          int pos = bookmarksList.getSelectedIndex();
          Bookmark bookmark = (Bookmark)bookmarks.get(pos);
          BookmarkInfoPanel bookmarkInfoPanel = new BookmarkInfoPanel(rb);
          fillPanel(bookmarkInfoPanel, bookmark);

          while (!end) {             
              input = (String)JOptionPane.showInputDialog(this, bookmarkInfoPanel,  rb.getString("GUI.Bookmarks.Rename"), JOptionPane.PLAIN_MESSAGE, null, null,  bookmark.getDescription());
    
              if (input != null) { // it's not cancelled
                  if (!input.equals("") && input.indexOf(Bookmark.DELIM) == -1) { // delim not found, input is ok
                    bookmark.setDescription(input);
                    // let's trigger the JList by modifying the model
                    bookmarks.set(pos, bookmark);
                    end = true;
                  }
              } else {
                  end = true;
              }
          }
        }
    }//GEN-LAST:event_renameButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        bookmarks.copyFrom(bookmarksBackup);
        saveCloseButtonActionPerformed(evt);
        closeDialog(null);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveCloseButtonActionPerformed
        try {
          bookmarks.save();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        closeDialog(null);
    }//GEN-LAST:event_saveCloseButtonActionPerformed

    private void restoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreButtonActionPerformed
          bookmarks.copyFrom(bookmarksBackup);
          updateCountLabel();
          removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/trash-empty.png")));
    }//GEN-LAST:event_restoreButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          if (pos < bookmarks.getSize()-1) {
              change(pos,pos+1);
              bookmarksList.ensureIndexIsVisible(pos+1);
          }
        }
    }//GEN-LAST:event_downButtonActionPerformed

    public void updateCountLabel() {
       countLabel.setText(GeneralString.message(
              (bookmarks.getSize() == 1) ? rb.getString("GUI.Bookmarks.Favorite"):rb.getString("GUI.Bookmarks.Favorites"),
              bookmarks.getSize()));
    }

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          bookmarks.remove(pos);
          // it was the last item
          if (pos == bookmarks.getSize()) pos--;
          if (bookmarks.getSize() > 0) bookmarksList.setSelectedIndex(pos);
          removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/net/numericalchameleon/resources/icons/pix16x16/trash-full.png")));
          updateCountLabel();
        }
    }//GEN-LAST:event_removeButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        if (!bookmarksList.isSelectionEmpty()) {
          int pos = bookmarksList.getSelectedIndex();
          if (pos > 0) {
              change(pos,pos-1);
              bookmarksList.ensureIndexIsVisible(pos-1);
          }
        }
    }//GEN-LAST:event_upButtonActionPerformed

   private void change(int oldpos, int newpos) {
       Object backup = bookmarks.get(newpos);
       bookmarks.set(newpos, bookmarks.get(oldpos));
       bookmarks.set(oldpos, backup);
       bookmarksList.setSelectedIndex(newpos);
   }

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
        bookmarkInterface.releaseBookmarksDialog();
    }//GEN-LAST:event_closeDialog


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookmarkPanel;
    private javax.swing.JList bookmarksList;
    private javax.swing.JButton bottomButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel countLabel;
    private javax.swing.JButton downButton;
    private javax.swing.JButton goButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton renameButton;
    private javax.swing.JButton restoreButton;
    private javax.swing.JButton saveCloseButton;
    private javax.swing.JButton topButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

}
