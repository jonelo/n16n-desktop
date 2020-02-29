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

import java.io.*;
import javax.swing.DefaultListModel;
import net.numericalchameleon.data.Bookmark;
import net.numericalchameleon.info.ProgConstants;

public class BookmarksModel extends DefaultListModel implements Cloneable {

    private String filename;

    /**
     * Creates a new instance of BookmarksModel
     * @param filename
     */
    public BookmarksModel(String filename) {
        super();
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void save() throws IOException {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            fos = new FileOutputStream(filename);
            osw = new OutputStreamWriter(fos, "UnicodeBig");
            bw = new BufferedWriter(osw);

            for (int i = 0; i < size(); i++) {
                bw.write(((Bookmark) get(i)).getBookmarkAsString());
                bw.write("\n");
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (osw != null) {
                osw.close();
            }
            if (fos != null) {
                fos.close();
            }
        }

    }

    public void read() throws IOException {
        clear();

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filename);
            isr = new InputStreamReader(fis, "UnicodeBig");
            br = new BufferedReader(isr);

            String string;
            while ((string = br.readLine()) != null) {
                add(getSize(), new Bookmark(string));
            }
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Private constructor used for cloning
     *
     * @param bookmarks the instance to clone.
     */
    private BookmarksModel(BookmarksModel bookmarks) {
        super();
        copyFrom(bookmarks);
    }

    public void copyFrom(BookmarksModel bookmarks) {
        filename = bookmarks.getFilename();
        clear();
        for (int i = 0; i < bookmarks.size(); i++) {
            // make a real copy with the new Bookmark constructor
            // don't copy just the references
            addElement(new Bookmark((Bookmark) bookmarks.getElementAt(i)));
        }
    }


    @Override
    public Object clone()  {        
        return new BookmarksModel(this);
    }
    
    public static BookmarksModel loadBookmarks() {
        BookmarksModel temp = new BookmarksModel(
                System.getProperty("user.home") + File.separator + ProgConstants.FILE_BOOKMARKS);
        try {
            temp.read();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
        return temp;
    }
}
