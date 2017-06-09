package org.yadavvi.dp.kingdomdivision;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by vishal on 10/6/17.
 */
@RunWith(JUnitParamsRunner.class)
public class KingdomDivisionDPTest {

    private KingdomDivision kingdomDivision;

    public static Object[] kingdomCitiesAndRoads() {
        return new Object[]{
                new Object[]{5, new int[][]{{1, 2}, {1, 3}, {3, 4}, {3, 5}}, ((int) Math.pow(10, 9) + 7), 4},
                new Object[]{17, new int[][]{{14, 5}, {17, 7}, {4, 9}, {13, 4}, {14, 10}, {11, 12}, {11, 13},
                        {3, 14}, {8, 16}, {3, 17}, {2, 3}, {1, 2}, {8, 1}, {15, 8}, {6, 15}, {11, 6}},
                        ((int) Math.pow(10, 9) + 7), 540},
        };
    }

    @Before
    public void setUp() throws Exception {
        kingdomDivision = new KingdomDivisionDP();
    }

    @Test
    @Parameters(method = "kingdomCitiesAndRoads")
    public void numberOfWaysToDivideAKingdom(int n, int[][] roads, int MOD_VALUE, int result) throws Exception {
        assertThat(kingdomDivision.numberOfWaysToDivideAKingdom(n, roads, MOD_VALUE), is(result));
    }

}