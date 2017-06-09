package org.yadavvi.kingdomdivision;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by vishal on 10/6/17.
 */
@RunWith(JUnitParamsRunner.class)
public class KingdomDivisionDPTest {

    private KingdomDivision kingdomDivision;

    public static Object[] kingdomCitiesAndRoads() {
        return new Object[]{
            new Object[]{5, new int[][]{{1, 2}, {1, 3}, {3, 4}, {3, 5}}, ((int) Math.pow(10, 9) + 7), 4},
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