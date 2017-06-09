package org.yadavvi.decbinary;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vishal on 2/6/17.
 */
@RunWith(JUnitParamsRunner.class)
public class NumberOfDecBinariesTest {

    NumberOfDecBinaries decBinaries;

    public static Object[] positionAndItsDecBinary() {
        return new Object[]{
                new Object[]{4, "10"},
                new Object[]{10, "100"},
        };
    }

    @Before
    public void setUp() {

    }

    @Test
    @Parameters(method = "positionAndItsDecBinary")
    public void decBinaryStringForPositionArithmeticProgression(int position, String decBinary) throws Exception {
        decBinaries = new NumberOfDecBinariesUsingArithmeticProgression();
        assertThat(decBinaries.decBinaryStringForPosition(position), is(equalTo(decBinary)));
    }

    @Test
    @Parameters(method = "positionAndItsDecBinary")
    public void decBinaryStringForPositionDP(int position, String decBinary) throws Exception {
        decBinaries = new NumberOfDecBinariesDP();
        assertThat(decBinaries.decBinaryStringForPosition(position), is(equalTo(decBinary)));
    }

}
