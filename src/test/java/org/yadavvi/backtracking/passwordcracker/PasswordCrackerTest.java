package org.yadavvi.backtracking.passwordcracker;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
                new Object[]{new String[]{"the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie"},
                        "thecakeisaliethecakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalie" +
                                "thecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisa" +
                                "liethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecake" +
                                "isaliethecakeisalieathecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethethecakeisaliethecakeisa" +
                                "liethecakeisaliethecakeisaliethethecakeisalieakthecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliesalthecakeisaliethecakeisaliethecakeisa" +
                                "liethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliesalthe" +
                                "cakeisaliethecakeisalielieakthecakeisalieliethecakeisaliethecakeisaliethecake" +
                                "isaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecake" +
                                "isaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieakthecakeisaliethecake" +
                                "isaliethecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisalieeithe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalieiethecakeisalie" +
                                "thecakeisaliethecakeisaliethecakeisalieisthecakeisaliethecakeisalieiscakeakthe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisalieakcakethecakeisaliethecieiethecthecakeisaliethecakeisalie" +
                                "thecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisaliethecakeisalie" +
                                "thecakeisaliethecakeisalieathecakeisaliethecakeisaliethecakeisaliethecakeisalie" +
                                "thecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalie" +
                                "thecakeisaliethecakeisaliethecakeisaliethecakeisalieeithecakeisaliethecakeisa" +
                                "liethecakeisaliethecakeisalieacakethecakeisaliethecakeisaliesalthecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisalieakthecakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisaliethe" +
                                "cakeisaliethecakeisaliethecakeisaliethecakeisaliethecakeisalie",
                        new String[]{"the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "ak", "the",
                                "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal",
                                "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak",
                                "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie",
                                "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei",
                                "sal", "ie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "the",
                                "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a",
                                "lie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "the", "cake",
                                "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie",
                                "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "a", "thec", "ak",
                                "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie",
                                "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei",
                                "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the",
                                "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei",
                                "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the", "thec", "ak", "ei", "sal", "ie",
                                "ak", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "thec", "ak",
                                "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie",
                                "sal", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "the", "cake",
                                "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie",
                                "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is",
                                "a", "lie", "sal", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie",
                                "lie", "ak", "thec", "ak", "ei", "sal", "ie", "lie", "the", "cake", "is", "a", "lie",
                                "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei",
                                "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the",
                                "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal",
                                "ie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "thec", "ak",
                                "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "ak", "the", "cake", "is", "a",
                                "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake",
                                "is", "a", "lie", "the", "cake", "is", "a", "lie", "ei", "thec", "ak", "ei", "sal",
                                "ie", "the", "cake", "is", "a", "lie", "ei", "thec", "ak", "ei", "sal", "ie", "thec",
                                "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a",
                                "lie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak",
                                "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie",
                                "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "ie", "the", "cake",
                                "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie",
                                "thec", "ak", "ei", "sal", "ie", "is", "thec", "ak", "ei", "sal", "ie", "the", "cake",
                                "is", "a", "lie", "is", "cake", "ak", "the", "cake", "is", "a", "lie", "the", "cake",
                                "is", "a", "lie", "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie",
                                "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is",
                                "a", "lie", "thec", "ak", "ei", "sal", "ie", "ak", "cake", "the", "cake", "is", "a",
                                "lie", "thec", "ie", "ie", "thec", "the", "cake", "is", "a", "lie", "the", "cake",
                                "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie",
                                "the", "cake", "is", "a", "lie", "ei", "thec", "ak", "ei", "sal", "ie", "thec", "ak",
                                "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie",
                                "thec", "ak", "ei", "sal", "ie", "a", "the", "cake", "is", "a", "lie", "thec", "ak",
                                "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie",
                                "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei",
                                "sal", "ie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie", "the",
                                "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a",
                                "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "ei", "the",
                                "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a",
                                "lie", "thec", "ak", "ei", "sal", "ie", "a", "cake", "the", "cake", "is", "a", "lie",
                                "thec", "ak", "ei", "sal", "ie", "sal", "thec", "ak", "ei", "sal", "ie", "the", "cake",
                                "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "thec", "ak", "ei", "sal", "ie",
                                "thec", "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "the", "cake", "is",
                                "a", "lie", "the", "cake", "is", "a", "lie", "ak", "thec", "ak", "ei", "sal", "ie",
                                "the", "cake", "is", "a", "lie", "thec", "ak", "ei", "sal", "ie", "the", "cake", "is",
                                "a", "lie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a", "lie", "thec",
                                "ak", "ei", "sal", "ie", "the", "cake", "is", "a", "lie", "the", "cake", "is", "a",
                                "lie", "thec", "ak", "ei", "sal", "ie"}},
        };
    }

    public Object[] passwordCrackerInputAndOutputFiles() {
        return new Object[]{
                new Object[]{"input07.txt", "output07.txt"},
                new Object[]{"input19.txt", "output19.txt"},
        };
    }

    @Test
    @Parameters(method = "passwordInputStringAndOutput")
    public void getSequence(String[] passwords, String input, String[] expectedOutput) throws Exception {
        cracker = new PasswordCracker(passwords, input);

        String[] actualOutput = cracker.getSequence();

        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    @Parameters(method = "passwordCrackerInputAndOutputFiles")
    public void getSequenceIPOPFromFiles(String inputFile, String outputFile) throws Exception {
        Input[] inputs = getInputs(inputFile);
        Output[] outputs = getOutputs(outputFile);

        for (int i = 0; i < inputs.length; i++) {
            Input input = inputs[i];
            Output output = outputs[i];
            String[] expectedOutput = output.sequences;

            cracker = new PasswordCracker(input.passwords, input.input);
            String[] actualOutput = cracker.getSequence();

            assertArrayEquals(expectedOutput, actualOutput);
        }
    }

    private Output[] getOutputs(String outputFile) {
        Output[] outputs;
        File file = new File(PasswordCrackerTest.class.getResource(outputFile).getFile());

        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            String line;
            while ((line = scanner.nextLine()) != null) {
                System.out.println(line);
                count++;
            }
        } catch (NoSuchElementException e) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        outputs = new Output[count];

        int i = 0;
        try (Scanner scanner = new Scanner(file)) {
            String line;
            while ((line = scanner.nextLine()) != null) {
                if (line.equals("WRONG PASSWORD")) {
                    outputs[i++] = new Output(new String[]{"WRONG PASSWORD"});
                } else {
                    outputs[i++] = new Output(line.split(" "));
                }
            }
        } catch (NoSuchElementException e) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputs;
    }

    private Input[] getInputs(String inputFile) {
        Input[] inputs= null;
        File file = new File(PasswordCrackerTest.class.getResource(inputFile).getFile());
        try (Scanner scanner = new Scanner(file)) {
            int T = scanner.nextInt();
            inputs = new Input[T];
            for (int i = 0; i < T; i++) {
                int N = scanner.nextInt();

                String[] passwords = new String[N];
                for (int j = 0; j < N; j++) {
                    passwords[j] = scanner.next();
                }

                String input = scanner.next();
                inputs[i] = new Input(passwords, input);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    private static class Input {
        String[] passwords;
        String input;

        Input(String[] passwords, String input) {
            this.passwords = passwords;
            this.input = input;
        }
    }

    private static class Output {
        String[] sequences;

        Output(String[] sequences) {
            this.sequences = sequences;
        }
    }

}