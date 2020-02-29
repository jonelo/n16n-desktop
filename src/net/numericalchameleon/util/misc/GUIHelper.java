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
package net.numericalchameleon.util.misc;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class GUIHelper {

    public static void setUndoRedoFor(JTextArea textArea, final UndoManager manager) {
        Document doc = textArea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {

            @Override
            public void undoableEditHappened(UndoableEditEvent evt) {
                manager.addEdit(evt.getEdit());
            }
        });
        textArea.getActionMap().put("Undo",
                new AbstractAction("Undo") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                undo(manager);
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");

        textArea.getActionMap().put("Redo",
                new AbstractAction("Redo") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                redo(manager);
            }
        });
        textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
    }

    public static void undo(final UndoManager manager) {
        try {
            if (manager.canUndo()) {
                manager.undo();
            }
        } catch (CannotUndoException e) {
        }
    }

    public static void redo(final UndoManager manager) {
        try {
            if (manager.canRedo()) {
                manager.redo();
            }
        } catch (CannotRedoException e) {
        }
    }

    public static boolean showQuestion(Component parentComponent, String title, String message) {
        boolean temp = false;
        int result = JOptionPane.showConfirmDialog(
                parentComponent, // parentComponent
                message, // message
                title, // title
                JOptionPane.YES_NO_CANCEL_OPTION, // optionType
                JOptionPane.QUESTION_MESSAGE); // messageType

        switch (result) {
            case JOptionPane.CLOSED_OPTION:
                temp = false;
                break;
            case JOptionPane.YES_OPTION:
                temp = true;
                break;
            case JOptionPane.NO_OPTION:
                temp = false;
                break;
            case JOptionPane.CANCEL_OPTION:
                temp = false;
                break;
        }
        return temp;
    }

}
