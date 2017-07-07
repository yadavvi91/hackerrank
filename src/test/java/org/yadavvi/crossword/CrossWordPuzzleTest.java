package org.yadavvi.crossword;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;


/**
 * Created by vishal on 6/7/17.
 */
@RunWith(JUnitParamsRunner.class)
public class CrossWordPuzzleTest {

    private CrossWordPuzzle puzzle;

    private static char[][] getInputFromFile(String inputFile) {
        int numberOfLines = 0;
        String input = CrossWordPuzzleTest.class.getResource(inputFile).getFile();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (!line.contains(";")) {
                    numberOfLines++;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        int i = 0;
        char[][] inputValues = new char[numberOfLines][numberOfLines];
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (!line.contains(";")) {
                    inputValues[i++] = line.toCharArray();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputValues;
    }

    private static List<String> getCitiesFromFile(String inputFile) {
        String input = CrossWordPuzzleTest.class.getResource(inputFile).getFile();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (line.contains(";")) {
                    return Arrays.asList(line.split(";"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static char[][] getOutputFromFile(String outputFile) {
        int numberOfLines = 0;
        String input = CrossWordPuzzleTest.class.getResource(outputFile).getFile();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            while (inputStream.readLine() != null) {
                numberOfLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0;
        char[][] outputValue = new char[numberOfLines][numberOfLines];
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (!line.contains(";")) {
                    outputValue[i++] = line.toCharArray();
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputValue;
    }

    private static void printCities(List<String> cities) {
        for (String city : cities) {
            System.out.println(city);
        }
    }

    private static void printCrosswordPuzzle(char[][] list) {
        for (char[] chars : list) {
            System.out.println(chars);
        }
    }

    public Object[] inputOutputFiles() {
        return new Object[]{
                new Object[]{"input0.txt", "output0.txt"},
                new Object[]{"input1.txt", "output1.txt"},
                new Object[]{"input2.txt", "output2.txt"},
                new Object[]{"input3.txt", "output3.txt"},
                new Object[]{"input4.txt", "output4.txt"},
                new Object[]{"input6.txt", "output6.txt"},
        };
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    @Parameters(method = "inputOutputFiles")
    public void crosswordOutput(String inputFile, String outputFile) {
        char[][] input = getInputFromFile(inputFile);
        List<String> cities = getCitiesFromFile(inputFile);
        char[][] output = getOutputFromFile(outputFile);

        printCrosswordPuzzle(input);
        printCities(cities);
        printCrosswordPuzzle(output);

        puzzle = new CrossWordPuzzle();

        assertThat(puzzle.solveCrossWordPuzzle(input, cities), is(output));
    }

}