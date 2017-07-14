package org.yadavvi.backtracking.repetitiveksums;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by vishal on 13/7/17.
 */
@RunWith(JUnitParamsRunner.class)
public class RepetitiveKSumsTest {

    private RepetitiveKSums kSums;

    public Object[] inputAndOutputForRepetitiveKSums() {
        return new Object[] {
                new Object[] {1, 3, new int[]{3}, new int[]{1}},
                new Object[] {2, 2, new int[]{12, 34, 56}, new int[]{6, 28}},
                new Object[] {3, 2, new int[]{2, 3, 4, 4, 5, 6}, new int[]{1, 2, 3}},
                new Object[] {4, 3, new int[]{3, 4, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 10, 10, 11, 12},
                        new int[]{1, 2, 3, 4}},
                new Object[] {5, 3, new int[]{6, 7, 8, 9, 10, 11, 11, 12, 12, 13, 14, 15, 15, 15, 16, 16, 16, 17, 17,
                        18, 19, 19, 20, 20, 20, 21, 21, 23, 24, 24, 25, 25, 28, 29, 33},
                        new int[]{2, 3, 6, 7, 11}},
        };
    }

    public Object[] inputAndOutputForRepetitiveKSumsFromFiles() {
        return new Object[] {
                new Object[] {"input_sample00.txt", "output_sample00.txt"},
                new Object[] {"input00.txt", "output00.txt"},
                new Object[] {"input01.txt", "output01.txt"},
        };
    }

    @Test
    @Parameters(method = "inputAndOutputForRepetitiveKSums")
    public void getRepetitiveKSums(int N, int k, int[] sums, int[] output) throws Exception {
        kSums = new RepetitiveKSums(N, k, sums);
        int[] actualOutput = kSums.getRepetitiveKSums();
        int[] expectedOutput = output;

        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    @Parameters(method = "inputAndOutputForRepetitiveKSumsFromFiles")
    public void getRepetitiveKSumsFromFiles(String inputFile, String outputFile) throws Exception {
        Input[] inputs = getInputsFromFile(inputFile);
        Output[] outputs = getOutputsFromFile(outputFile);

        for (int i = 0; i < inputs.length; i++) {
            Input input = inputs[i];
            Output output = outputs[i];
            kSums = new RepetitiveKSums(input.N, input.k, input.sums);
            int[] actualOutput = kSums.getRepetitiveKSums();
            int[] expectedOutput = output.repetitiveSums;

            assertArrayEquals(expectedOutput, actualOutput);
        }
    }

    private Output[] getOutputsFromFile(String outputFile) {
        File file = new File(RepetitiveKSumsTest.class.getResource(outputFile).getFile());

        int count = 0;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.nextLine() != null) {
                count++;
            }
        } catch (NoSuchElementException e) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Output[] outputs = new Output[count];
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < count; i++) {
                String line = scanner.nextLine();
                String[] sumsString = line.trim().split(" ");
                int[] sums = new int[sumsString.length];
                for (int j = 0; j < sumsString.length; j++) {
                    sums[j] = Integer.valueOf(sumsString[j]);
                }
                outputs[i] = new Output(sums);
            }
        } catch (NoSuchElementException e) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputs;
    }

    private Input[] getInputsFromFile(String inputFile) {
        File file = new File(RepetitiveKSumsTest.class.getResource(inputFile).getFile());
        Input[] inputs = null;

        try (Scanner scanner = new Scanner(file)) {
            int T = scanner.nextInt();
            inputs = new Input[T];
            for (int i = 0; i < T; i++) {
                int N = scanner.nextInt();
                int k = scanner.nextInt();
                scanner.nextLine();

                String line = scanner.nextLine();
                String[] sumsString = line.trim().split(" ");
                int[] sums = new int[sumsString.length];
                for (int j = 0; j < sumsString.length; j++) {
                    sums[j] = Integer.valueOf(sumsString[j]);
                }

                inputs[i] = new Input(N, k, sums);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputs;
    }

    private static class Input {
        int N;
        int k;
        int[] sums;

        Input(int N, int k, int[] sums) {
            this.N = N;
            this.k = k;
            this.sums = sums;
        }
    }

    private class Output {
        int[] repetitiveSums;

        Output(int[] repetitiveSums) {
            this.repetitiveSums = repetitiveSums;
        }
    }

}