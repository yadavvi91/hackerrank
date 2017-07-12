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
                // Input-Output 3
                new Object[]{610360465, new int[]{9, 12, 15}, new int[]{-1}},
                // Input-Output 4
                new Object[]{964518043, new int[]{4}, new int[]{-1}},
                // Input-Output 11
                new Object[]{231000000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000, 3000000, 21000000, 231000000}},
                // Input-Output 12
                new Object[]{273000000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000, 3000000, 21000000, 273000000}},
                // Input-Output 13
                new Object[]{357000000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000, 3000000, 21000000, 357000000}},
                new Object[]{600000, new int[]{2, 3, 5, 7, 11, 13, 17, 19},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000}},
                new Object[]{1073741824, new int[]{2},
                        new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824}},
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