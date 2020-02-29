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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonelo
 */
public class RandomGenerator {
    
    private static SecureRandom secureRandom = null;
    
    /**
     * Returns a randomly generated integer
     *
     * @param lowerbound the lower limit
     * @param upperbound the upper limit
     * @return the random number
     */
    public static int getRandom(int lowerbound, int upperbound) {
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        return secureRandom.nextInt(upperbound + 1 - lowerbound) + lowerbound;
    }

    /**
     * Returns a list of random numbers.
     * 
     * count - the number of random numbers
     * lowerbound - the lower bound
     * upperbound - the upper bound
     * duplicates - are duplicates allowed?
     * sort - sorted result desired ?
     */
    public static int[] generate(int count, int lowerbound, int upperbound, boolean duplicates, boolean sort) throws IllegalArgumentException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        if (lowerbound > upperbound) {
            throw new IllegalArgumentException(lowerbound + " > " + upperbound );
        }
        
        if (count < 1) {
            throw new IllegalArgumentException("count < 1");
        }

        // we don't want infinite loops
        if ((upperbound - lowerbound + 1 < count) && !duplicates) {
            duplicates = true;
        }

        Map hash = null;        
        for (int i = 0; i < count; i++) {
            int number = getRandom(lowerbound, upperbound);

            // we want check for duplicates (and only if we want at least 2 numbers)
            if (!duplicates && count > 1) {
                if (hash == null) {
                    hash = new HashMap();
                }
                // is it there already?
                while (hash.get(number) != null) {
                    // get a new number
                    number = getRandom(lowerbound, upperbound);
                }
                hash.put(number, ""); // indicator that the number was there
            }
            list.add(number);
        }

        // now, we have all numbers in the list
        Object[] IntArray = list.toArray();
        if (sort && count > 1) {
            Arrays.sort(IntArray);
        }
        int[] array = new int[IntArray.length];
        int i = 0;
        for (Object o : IntArray) {
            array[i++] = ((Integer) o).intValue();
        }
        return array;
    }
}
