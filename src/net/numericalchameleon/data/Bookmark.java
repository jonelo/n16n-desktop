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
package net.numericalchameleon.data;

import java.util.StringTokenizer;

public class Bookmark {

    public final static String DELIM = "##";
    private String language,
            country,
            description,
            value;
    private final String
            category,
            sourceDescription,
            sourceCountry,
            targetDescription,
            targetCountry;

    /**
     * Creates a new instance of Bookmark
     * @param category
     * @param sourceUnit
     * @param targetUnit
     */
    public Bookmark(Category category, Unit sourceUnit, Unit targetUnit) {
        language = "en";
        country = "US";
        this.category = category.getName();
        sourceDescription = sourceUnit.getString();
        sourceCountry = sourceUnit.getIcon();
        targetDescription = targetUnit.getString();
        targetCountry = targetUnit.getIcon();
        description = sourceDescription + " -> " + targetDescription;
        value = "";
    }

    public Bookmark(Bookmark bookmark) {
        language = bookmark.language;
        country = bookmark.country;
        description = bookmark.description;
        category = bookmark.category;
        sourceDescription = bookmark.sourceDescription;
        sourceCountry = bookmark.sourceCountry;
        targetDescription = bookmark.targetDescription;
        targetCountry = bookmark.targetCountry;
        value = bookmark.value;
    }

    public Bookmark(String string) {
        StringTokenizer st = new StringTokenizer(string, DELIM);
        language = st.nextToken();
        country = st.nextToken();
        description = st.nextToken();
        category = st.nextToken();
        sourceDescription = st.nextToken();
        sourceCountry = st.nextToken();
        targetDescription = st.nextToken();
        targetCountry = st.nextToken();
        if (st.hasMoreTokens()) {
            value = st.nextToken();
        } else {
            value = "";
        }
    }

    @Override
    public String toString() {
        return description;
    }

    public String getBookmarkAsString() {
        return language + DELIM + country + DELIM
                + description + DELIM + category + DELIM
                + sourceDescription + DELIM + sourceCountry + DELIM
                + targetDescription + DELIM + targetCountry + DELIM + value;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public String getTargetDescription() {
        return targetDescription;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
