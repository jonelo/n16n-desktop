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
package net.numericalchameleon.categories;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;
import net.numericalchameleon.data.Unit;

public class CategoryColor extends CategoryObject {

    private static final int _RGB_dec = 0, _RRGGBB_hex = 1, _R_G_B_dec = 2, _HSB_dec = 3;

    private static String INVALID;

    private final ArrayList<Unit> units;
    private final Unit defaultSourceUnit, defaultTargetUnit, transferUnit;

    public CategoryColor(CategoryInterface categroyInterface) {
        rb = categroyInterface.getResourceBundle();
        INVALID = localize("Moduls.sourceInvalid", "Source is invalid");
        units = new ArrayList<>();

        units.add(new Unit(_RGB_dec, "RGB (dec)", "int"));
        units.add(new Unit(_RRGGBB_hex, "RRGGBB (hex)", "int"));
        units.add(new Unit(_R_G_B_dec, "R, G, B (dec)", "int"));
        units.add(new Unit(_HSB_dec, "H, S, B (dec)", "int"));

        transferUnit = units.get(0);
        defaultSourceUnit = units.get(0);
        defaultTargetUnit = units.get(2);
    }

    @Override
    public ArrayList<Unit> getSourceUnits() {
        return units;
    }

    @Override
    public ArrayList<Unit> getTargetUnits() {
        return units;
    }

    @Override
    public int getSourceDefault() {
        return 0;
    }

    public Unit getDefaultSourceUnit() {
        return defaultSourceUnit;
    }

    @Override
    public int getTargetDefault() {
        return 2;
    }

    public Unit getDefaultTargetUnit() {
        return defaultTargetUnit;
    }

    @Override
    public int getPreferredPrecision() {
        return -1;
    }

    @Override
    public String getOutput(int s, int t) throws Exception {
        Unit sourceUnit = units.get(s);
        Unit targetUnit = units.get(t);
        return getOutput(sourceUnit, targetUnit);
    }

    @Override
    public String getOutput(Unit sourceUnit, Unit targetUnit) throws Exception {
        int sid = sourceUnit.getId();
        int tid = targetUnit.getId();
        
        long value = 0;

        switch (sid) {
            case _RGB_dec: {
                try {
                    value = Long.valueOf(input, 10);
                    if ((value < 0) || (value > 0x00FFFFFFL)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new Exception(INVALID);
                }                
            }
            break;

            case _RRGGBB_hex: {
                try {
                    value = Long.valueOf(input, 16);
                    if ((value < 0) || (value > 0x00FFFFFFL)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    throw new Exception(INVALID);
                }
            }
            break;

            case _R_G_B_dec: {
                try {
                    ArrayList<Integer> tokens = new ArrayList<Integer>();
                    StringTokenizer st = new StringTokenizer(input, ",");
                    while (st.hasMoreTokens()) {
                        tokens.add(Integer.valueOf(st.nextToken().trim(), 10));
                    }
                    if (tokens.size() != 3) {
                        throw new Exception();
                    }
                    Color c = new Color(
                            tokens.get(0), tokens.get(1), tokens.get(2));
                    value = c.getRGB() & 0x00ffffffL;

                } catch (Exception e) {
                    throw new Exception(INVALID);
                }
            }
            break;

            case _HSB_dec: {
                try {
                    float f[] = new float[3];
                    StringTokenizer st = new StringTokenizer(input, ",");
                    // hue
                    if (st.hasMoreTokens()) {
                        f[0] = Float.valueOf(st.nextToken().trim());
                        if (f[0] > 359) {
                            throw new Exception(INVALID + " (H > 359)");
                        } else {
                            f[0] /= 359;
                        }
                    } else {
                        throw new Exception(INVALID);
                    }

                    // saturation
                    if (st.hasMoreTokens()) {
                        f[1] = Float.valueOf(st.nextToken().trim());
                        if (f[1] > 100) {
                            throw new Exception(INVALID + " (S > 100)");
                        } else {
                            f[1] /= 100;
                        }
                    } else {
                        throw new Exception(INVALID);
                    }

                    // brightness
                    if (st.hasMoreTokens()) {
                        f[2] = Float.valueOf(st.nextToken().trim());
                        if (f[2] > 100) {
                            throw new Exception(INVALID + " (B > 100)");
                        } else {
                            f[2] /= 100;
                        }
                    } else {
                        throw new Exception(INVALID);
                    }

                    if (st.hasMoreTokens()) {
                        throw new Exception();
                    }

                    int color = Color.HSBtoRGB(f[0], f[1], f[2]);
                    value = color & 0x00ffffffL;

                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
            break;
        }

        switch (tid) {
            case _RGB_dec:
                return Long.toString(value);
            case _RRGGBB_hex: {
                String temp = Long.toHexString(value & 0x00ffffffL).toUpperCase();
                // add a leading zero
                if (temp.length() == 5) {
                    return "0" + temp;
                }
                return temp;
            }
            case _R_G_B_dec: {
                Color c = new Color((int) value);
                return c.getRed() + ", " + c.getGreen() + ", " + c.getBlue();
            }
            case _HSB_dec: {
                Color c = new Color((int) value);
                float f[] = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
                return Math.round(f[0] * 359) + ", " + Math.round(f[1] * 100) + ", " + Math.round(f[2] * 100);
            }
        }
        return null;
    }

    
    @Override
    public String addValue(BigDecimal plus, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return addValue(plus, sourceUnit);
    }
    
    public String addValue(BigDecimal plus, Unit sourceUnit) throws Exception {
        if (input == null || input.length() == 0) {
            // set the default according to the selected sourceUnit
            input = "0";
            input = getOutput(transferUnit, sourceUnit);
        }
        String temp = getOutput(sourceUnit, transferUnit);
        BigDecimal big = new BigDecimal(temp);
        big = big.add(plus);
        return setValue(big, sourceUnit);
    }

    @Override
    public String setValue(BigDecimal big, int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return setValue(big, sourceUnit);
    }

    public String setValue(BigDecimal big, Unit sourceUnit) throws Exception {
        setInput(big.toPlainString());
        return getOutput(transferUnit, sourceUnit);
    }
    
    @Override
    public String getTransferValue(int sourceIndex) throws Exception {
        Unit sourceUnit = units.get(sourceIndex);
        return getTransferValue(sourceUnit);
    }

    @Override
    public String getTransferValue(Unit sourceUnit) throws Exception {
        return getOutput(sourceUnit, transferUnit);
    }

    @Override
    public String getCard() {
        return "colCard";
    }
}
