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

import java.awt.Dimension;
import java.awt.Point;

public class Gimmicks {
    // the method doExitAnimation() is based on the work of Hughes Monget,
    // he is the author of JPassGen (GPL) - a really cool product by the way ;-)
    // see http://jpassgen.sourceforge.net/

    /**
     * do a fancy exit animation
     *
     * @param frame
     */
    public static void doExitAnimation(java.awt.Frame frame) {

        Point oldP = frame.getLocation();
        Dimension dim = frame.getSize();

        oldP.x += dim.width / 2;
        oldP.y += dim.height / 2;

        int loop;
        if (dim.width > dim.height) {
            loop = dim.width / 32;
        } else {
            loop = dim.height / 32;
        }

        int dx = dim.width / loop;
        int dy = dim.height / loop;

        for (int ii = 0; ii < loop; ii++) {
            dim.width -= dx;
            dim.height -= dy;
            frame.setSize(dim);
            Dimension newDim = frame.getSize();
            Point newP = new Point();
            newP.x = oldP.x - newDim.width / 2;
            newP.y = oldP.y - newDim.height / 2;
            frame.setLocation(newP);
            try {
                Thread.sleep(100 / loop);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
