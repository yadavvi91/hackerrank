package org.yadavvi.multof3;

import java.util.Scanner;

/**
 * Created by vishal on 8/6/17.
 */
public class Solution {

    public long multipleOf3(long input) {
        long t = input;
        long mult = 3;
        // Starting point, if t > 1_000_000
        // Here, we use the formula - 2^0 + 2^1 + 2^2 + .... + 2^n = 2^(n+1) - 1
        if (t / 1_000_000 > 0) {
            // Starting point, if t > 1_000_000 and t > 1_000_000_000
            if (t / 1_000_000_000 > 0) {
                // Sum of all the 3 * 2^n (n = 0 to 25) can be given by the formula
                t -= 3 * ((2 << 26) - 1);
                // we have considered all multiples of 2 ^ n (where n is 0 to 24).
                // Hence, next mult becomes 3 * (2 ^ 25) * 2 i.e. 3 * (2 ^ 26) i.e. 3 * (2 << 26)
                mult = 3 * (2 << 26);
            } else {
                t -= 3 * ((2 << 23) - 1);
                mult = 3 * (2 << 23);
            }
        }

        while (t > 0) {
            t -= mult;
            mult *= 2;
        }
        if (t < 0) {
            mult /= 2;
            t += mult;
        }

        long start = 0L;
        if (t == 0) {
            start = 1L;
            System.out.println(start);
        } else if (t > 0) {
            start = mult - t + 1;
            System.out.println(start);
        } else {
            throw new IllegalArgumentException();
        }

        return start;
    }

}
