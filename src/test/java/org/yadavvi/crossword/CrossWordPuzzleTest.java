package org.yadavvi.crossword;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

    private static List<List<Character>> getInputFromFile(String inputFile) {
        List<List<Character>> inputValues = new ArrayList<>();
        String input = CrossWordPuzzleTest.class.getResource(inputFile).getFile();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (!line.contains(";")) {
                    inputValues.add(getListOfCharFromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputValues;
    }

    private static List<Character> getListOfCharFromString(String line) {
        List<Character> characterList = new ArrayList<>();
        for (char aChar : line.toCharArray()) {
            characterList.add(aChar);
        }
        return characterList;
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

    private static List<List<Character>> getOutputFromFile(String outputFile) {
        List<List<Character>> outputValues = new ArrayList<>();
        String input = CrossWordPuzzleTest.class.getResource(outputFile).getFile();
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = inputStream.readLine()) != null) {
                if (!line.contains(";")) {
                    outputValues.add(getListOfCharFromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputValues;
    }

    private static void printCities(List<String> cities) {
        for (String city : cities) {
            System.out.println(city);
        }
    }

    private static void printCrosswordPuzzle(List<List<Character>> list) {
        for (List<Character> chars : list) {
            System.out.println(chars);
        }
    }

    public Object[] inputOutputFiles() {
        return new Object[]{
                new Object[]{"input1.txt", "output1.txt"},
                new Object[]{"input2.txt", "output2.txt"}
        };
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    @Parameters(method = "inputOutputFiles")
    public void crosswordOutput(String inputFile, String outputFile) {
        List<List<Character>> input = getInputFromFile(inputFile);
        List<String> cities = getCitiesFromFile(inputFile);
        List<List<Character>> output = getOutputFromFile(outputFile);

        printCrosswordPuzzle(input);
        printCities(cities);
        printCrosswordPuzzle(output);

        puzzle = new CrossWordPuzzle();

        assertThat(puzzle.solveCrossWordPuzzle(input, cities), is(output));
    }

}