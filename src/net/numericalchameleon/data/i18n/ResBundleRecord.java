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
package net.numericalchameleon.data.i18n;

import java.net.URL;
import java.util.Arrays;
import java.util.StringTokenizer;
import net.numericalchameleon.info.ProgConstants;

public class ResBundleRecord {

    private String language;
    private ResBundleAuthor[] authors;
    private String country;
    private String help;
    private String description;
    private String version;
    public final static String DELIM = ";";

    /**
     * Creates a new instance of ResBundleRecord
     */
    public ResBundleRecord() {
    }

    public ResBundleRecord(String parseThis) {
        StringTokenizer st = new StringTokenizer(parseThis, DELIM);
        version = st.nextToken();
        String[] authorsToken = st.nextToken().split("&");
        authors = new ResBundleAuthor[authorsToken.length];
        int i = 0;
        for (String authorToken : authorsToken) {
            String[] tuple = authorToken.split(":");
            authors[i] = new ResBundleAuthor(tuple[1], tuple[0].trim().toLowerCase());
            i++;
        }
        language = st.nextToken();
        country = st.nextToken();
        help = st.nextToken();
        description = st.nextToken();
    }

    @Override
    public String toString() {
        return version + DELIM + Arrays.toString(getAuthors()) + DELIM + language + DELIM
                + country + DELIM + help + DELIM + description;
    }

    public String getHTML() {
        return "<html><body>"
                + "<b>Version:</b>&nbsp;<br>" + version + "<br><br>"
                + "<b>Translators:</b>&nbsp;<br>"
                + getAuthorsAsHTML()
                + "</body></html>";
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the authors
     */
    public ResBundleAuthor[] getAuthors() {
        return authors;
    }

    public static String getImgTag(String country) {
        // starting with Oracle Java 8u201 this doesn't work anymore; the image is not being rendered anymore
        URL url = ResBundleRecord.class.getResource(ProgConstants.ICONS_FLAGS + country.toLowerCase() + ".png");             
        return "<img src=\"" + url + "\"" + ">";
    }

    public String getAuthorsAsHTML() {
        StringBuilder sb = new StringBuilder();
        for (ResBundleAuthor author : authors) {
            boolean red=author.getName().trim().equals("WE NEED A NEW VOLUNTEER");
            
            if (red) {
                sb.append("<b><font size=\"+2\" color=\"red\">");
            }
            sb.append(author.getName());
            if (red) {
                sb.append("!!!</font></b><br>");                             
                sb.append("<br><br><b>Former translators:</b>");
            }
            sb.append("&nbsp;");
            //sb.append(getImgTag(author.getCountry()));
            sb.append("<br>");
        }
        return sb.toString();
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(ResBundleAuthor[] authors) {
        this.authors = authors;
    }
}
