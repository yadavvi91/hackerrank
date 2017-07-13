package org.yadavvi.backtracking;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by vishal on 13/7/17.
 */
@RunWith(JUnitParamsRunner.class)
public class PasswordCrackerTest {

    private PasswordCracker cracker;

    public Object[] passwordInputStringAndOutput() {
        return new Object[]{
                new Object[]{new String[]{"because", "can", "do", "must", "we", "what"}, "wedowhatwemustbecausewecan",
                        new String[]{"we", "do", "what", "we", "must", "because", "we", "can"}},
                new Object[]{new String[]{"hello", "planet"}, "helloworld",
                        new String[]{"WRONG PASSWORD"}},
                new Object[]{new String[]{"ab", "abcd", "cd"}, "abcd",
                        new String[]{"ab", "cd"}},
        };
    }

    @Test
    @Parameters(method = "passwordInputStringAndOutput")
    public void getSequence(String[] passwords, String input, String[] expectedOutput) throws Exception {
        cracker = new PasswordCracker(passwords, input);

        String[] actualOutput = cracker.getSequence();

        assertArrayEquals(expectedOutput, actualOutput);
    }

}