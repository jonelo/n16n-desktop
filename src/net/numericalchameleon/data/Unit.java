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

import java.io.Serializable;
import java.math.BigDecimal;
import jonelo.sugar.util.GeneralString;

public class Unit implements Comparable, Serializable {

    private String string;
    private BigDecimal factor;
    private String icon;
    private String name;
    private boolean active;
    int id = 0;
    private boolean invert = false;

    /** Creates new Unit */
    public Unit() {
    }

    public Unit(int id) {
        this.id=id;        
    }
    
    public int getId() {
        return id;
    }

    public Unit(int id, String string, String icon) {
        this(string, icon);
        this.id = id;
    }

    public Unit(String string, String icon) {
        this.string = string;
        this.factor = null;
        this.icon =  icon;
        this.name = null;
        this.active = true;
    }

    public Unit(String string, String icon, boolean active) {
        this.string = string;
        this.factor = null;
        this.icon = icon;
        this.name = null;
        this.active = active;
    }

    public Unit(int id, String string, String icon, boolean active) {
        this(string, icon, active);
        this.id = id;
    }

    public Unit(String string, BigDecimal factor) {
        this.string = string;
        this.factor = factor;
        this.icon = null;
        this.name = null;
        this.active = true;
    }

    public Unit(String string, BigDecimal factor, String icon) {
        this.string = string;
        this.factor = factor;
        this.icon = icon;
        this.name = null;
        this.active = true;
    }
    
    public Unit(int id, String string, BigDecimal factor, String icon) {
        this (string, factor, icon);
        this.id = id;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    public boolean isInvert() {
        return invert;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getString() {
        return string;
    }

    public String getText() {
        return string;
    }

    public BigDecimal getFactor() {
        return factor;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return string;
    }

    public String toComparableString() {
        // for german umlauts
        String st = string.toLowerCase();
        st = GeneralString.replaceAllStrings(st, "ö", "oe");
        st = GeneralString.replaceAllStrings(st, "ä", "ae");
        st = GeneralString.replaceAllStrings(st, "ü", "ue");
        return st;
    }

    @Override
    public int compareTo(Object obj) {
        return toComparableString().compareTo(((Unit) obj).toComparableString());
    }
}
