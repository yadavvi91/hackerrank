package org.yadavvi.coinchange;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.yadavvi.coinchange.CoinChange.getWays;

/**
 * Created by vishal on 8/6/17.
 */
@RunWith(JUnitParamsRunner.class)
public class CoinChangeTest {

    public Object[] valueAndCoins() {
        return new Object[]{
                new Object[]{3L, new long[]{8, 3, 1, 2}, 3L},
                new Object[]{4L, new long[]{1, 2, 3}, 4L},
                new Object[]{10L, new long[]{2, 5, 3, 6}, 5L},
                new Object[]{75L, new long[]{25, 10, 11, 29, 49, 31, 33, 39, 12, 36, 40, 22,
                        21, 16, 37, 8, 18, 4, 27, 17, 26, 32, 6, 38, 2, 30, 34}, 16694},
                new Object[]{222L, new long[]{3, 25, 34, 38, 26, 42, 16, 10, 15, 50, 39, 44,
                        36, 29, 22, 43, 20, 27, 9, 30, 47, 13, 40, 33}, 5621927},
        };
    }

    @Test
    @Parameters(method = "valueAndCoins")
    public void getWaysTest(long n, long[] c, long ways) throws Exception {
        assertThat(getWays(n, c), is(equalTo(ways)));
    }

}