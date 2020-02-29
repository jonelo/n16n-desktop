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

import java.util.HashMap;
import net.numericalchameleon.data.Bookmark;
import net.numericalchameleon.data.Category;
import net.numericalchameleon.gui.common.interfaces.DialogInterface;

/**
 * The Interface for BookmarksModel
 */
public interface BookmarksInterface extends DialogInterface {

    /**
     * The interface for dealing with BookmarksModel
     *
     * @param bookmark the bookmark which you would like to visit
     * @throws java.lang.Exception
     */
    public void visitBookmark(Bookmark bookmark) throws Exception;

    public void releaseBookmarksDialog();

    public BookmarksModel getBookmarks();

    public HashMap<String,Category> getCategoryHashMap();
}
