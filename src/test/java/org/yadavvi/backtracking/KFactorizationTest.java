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
        };
    }

    @Test
    @Parameters(method = "inputAndOutputKFactorization")
    public void kFactorization(int N, int[] a, int[] output) throws Exception {
        kFactorization = new KFactorization(N, a);
        kFactorization.kFactorization();

        assertArrayEquals(kFactorization.smallestFactors(), output);
    }

}