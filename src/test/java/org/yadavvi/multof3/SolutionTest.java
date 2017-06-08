package org.yadavvi.multof3;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by vishal on 8/6/17.
 */
@RunWith(JUnitParamsRunner.class)
public class SolutionTest {

    private Solution solution;

    public Object[] inputAndMultipleOf3() {
        return new Object[]{
                new Object[]{3L, 1L},
                new Object[]{9L, 1L},
                new Object[]{6L, 4L},
                new Object[]{4L, 6L},
                new Object[]{999999766777L, 649267674885L},
                new Object[]{999999997668L, 649267443994L}
        };
    }

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @Test
    @Parameters(method = "inputAndMultipleOf3")
    public void multipleOf3(long input, long output) throws Exception {
        assertThat(solution.multipleOf3(input), is(output));
    }

}