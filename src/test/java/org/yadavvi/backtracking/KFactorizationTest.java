package org.yadavvi.backtracking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by vishal on 12/7/17.
 */
@RunWith(JUnitParamsRunner.class)
public class KFactorizationTest {

    private KFactorization kFactorization;

    public Object[] inputAndOutputKFactorization() {
        return new Object[]{
                new Object[]{12, new int[]{2, 3, 4}, new int[]{1, 3, 12}},
                new Object[]{15, new int[]{2, 10, 6, 9, 11}, new int[]{-1}},
                new Object[]{72, new int[]{2, 4, 6, 9, 3, 7, 16, 10, 5}, new int[]{1, 2, 8, 72}},
                // Input-Output 11
                new Object[]{231000000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000, 3000000, 21000000, 231000000}},
                // Input-Output 13
                new Object[]{357000000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000, 3000000, 21000000, 357000000}},
                new Object[]{600000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000}},
        };
    }

    @Test
    @Parameters(method = "inputAndOutputKFactorization")
    public void kFactorization(int N, int[] a, int[] output) throws Exception {
        kFactorization = new KFactorization(N, a);
        kFactorization.kFactorization();

        int[] actualOutput = kFactorization.smallestFactors();
        int[] expectedOutput = output;
        assertArrayEquals(actualOutput, expectedOutput);
    }

}