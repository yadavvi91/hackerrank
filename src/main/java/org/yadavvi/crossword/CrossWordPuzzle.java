package org.yadavvi.crossword;

import java.util.*;

/**
 * Created by vishal on 6/7/17.
 */
public class CrossWordPuzzle {

    private static List<List<Integer>> generateCombinationsForNumber(int start, int end) {
        List<Integer> firstCombination = new LinkedList<>();
        for (int i = start; i <= end; i++) {
            firstCombination.add(i);
        }

        return generatePerm(firstCombination);
    }

    public static List<List<Integer>> generatePerm(List<Integer> original) {
        if (original.size() == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Integer firstElement = original.remove(0);
        List<List<Integer>> returnValue = new ArrayList<>();
        List<List<Integer>> permutations = generatePerm(original);
        for (List<Integer> smallerPermutated : permutations) {
            for (int index = 0; index <= smallerPermutated.size(); index++) {
                List<Integer> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    private static List<List<Integer>> generateCombinations(List<Places> places) {
        List<List<Integer>> combinations = new LinkedList<>();
        combinations.add(new LinkedList<>());

        int continuous = 1;
        int i = 0;
        while (i < places.size()) {
            while (i + 1 < places.size() && places.get(i).length == places.get(i + 1).length) {
                continuous++;
                i++;
            }

            if (continuous > 1) {
                List<List<Integer>> numberCombinations = generateCombinationsForNumber(i - continuous + 1, i);
                List<List<Integer>> newCombinations = new LinkedList<>();
                for (List<Integer> combination : combinations) {
                    for (List<Integer> numberCombination : numberCombinations) {
                        List<Integer> list = new LinkedList<>();
                        list.addAll(combination);
                        list.addAll(numberCombination);
                        newCombinations.add(list);
                    }
                }
                combinations = newCombinations;
                i++;
            } else {
                for (List<Integer> combination : combinations) {
                    combination.add(i);
                }
                i++;
            }
            continuous = 1;
        }
        return combinations;
    }

    public char[][] solveCrossWordPuzzle(char[][] input, List<String> cities) {
        List<Places> places = getPlaces(input);
        Collections.sort(places);
        Collections.sort(cities, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        List<List<Integer>> combinations = generateCombinations(places);
        System.out.println(combinations);

        return solveCrossword(combinations, input, places, cities);
    }

    private char[][] solveCrossword(List<List<Integer>> combinations, char[][] input, List<Places> places,
                                    List<String> cities) {
        char[][] workingInput = createNewArray(input);
        List<Integer> tillNow = new LinkedList<>();
        List<Integer> breakValues = new LinkedList<>();

        for (List<Integer> combination : combinations) {
            tillNow.clear(); // Clear the combinations browsed till now.
            copyArray(input, workingInput);

            if (areFirstElementsSame(breakValues, combination)) continue;
            else breakValues.clear();

            for (Integer comb : combination) {
                if (isCombinationValid(comb, places, cities, workingInput)) {
                    tillNow.add(comb);
                } else {
                    breakValues.addAll(tillNow);
                    breakValues.add(comb);
                    break;
                }
            }
            if (tillNow.size() == combination.size()) return workingInput;
        }

        return null;
    }

    private void copyArray(char[][] source, char[][] destination) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                destination[i][j] = source[i][j];
            }
        }
    }

    private boolean areFirstElementsSame(List<Integer> breakValues, List<Integer> combination) {
        if (breakValues.isEmpty()) return false;

        for (int i = 0; i < breakValues.size(); i++) {
            if (!breakValues.get(i).equals(combination.get(i))) return false;
        }

        return true;
    }

    private boolean isCombinationValid(Integer comb, List<Places> places,
                                       List<String> cities, char[][] input) {
        Places place = places.get(comb);
        String city = cities.get(comb);
        if (place instanceof Horizontal) {
            int i = ((Horizontal) place).start;
            int row = ((Horizontal) place).row;
            for (char charValue : city.toCharArray()) {
                if (input[row][i] != '-') {
                    if (input[row][i] == charValue) {
                        i++;
                        continue;
                    } else {
                        return false;
                    }
                }
                input[((Horizontal) place).row][i++] = charValue;
            }
        } else if (place instanceof Vertical) {
            int i = ((Vertical) place).start;
            int column = ((Vertical) place).column;
            for (char charValue : city.toCharArray()) {
                if (input[i][column] != '-') {
                    if (input[i][column] == charValue) {
                        i++;
                        continue;
                    } else {
                        return false;
                    }
                }
                input[i++][column] = charValue;
            }
        }
        return true;
    }

    private char[][] getOutputArray(char[][] input, String city, Places place, char[][] output) {
        if (place instanceof Horizontal) {
            int i = ((Horizontal) place).start;
            int row = ((Horizontal) place).row;
            for (char charValue : city.toCharArray()) {
                if (output[row][i] != '-') {
                    if (output[row][i] == charValue) {
                        continue;
                    } else {
                        return null;
                    }
                }
                output[((Horizontal) place).row][i++] = charValue;
            }
        } else if (place instanceof Vertical) {
            int i = ((Vertical) place).start;
            int column = ((Vertical) place).column;
            for (char charValue : city.toCharArray()) {
                if (output[i][column] != '-') {
                    if (output[i][column] == charValue) {
                        continue;
                    } else {
                        return null;
                    }
                }
                output[i++][column] = charValue;
            }
        }

        return output;
    }

    private void solveCrossWordPuzzle(char[][] input, List<String> cities, List<Places> places,
                                      List<Places> visitedPlaces, List<List<Places>> validWays) {
        if (places.isEmpty()) {
            validWays.add(visitedPlaces);
            System.out.println(validWays);
            return;
        } else {
            System.out.println("Places: " + places);
            System.out.println("VisitedPlaces: " + visitedPlaces + "\n");
        }

        for (int t = 0; t < places.size(); t++) {
            Places place = places.get(t);
            String city = cities.get(t);

            List<Places> newPlaces = new LinkedList<>(places);
            newPlaces.remove(t);
            List<String> newCities = new LinkedList<>(cities);
            newCities.remove(t);

            char[][] newInput = getNewInputArray(input, city, place);
            if (newInput == null) continue;

            visitedPlaces.add(place);
            solveCrossWordPuzzle(newInput, newCities, newPlaces, visitedPlaces, validWays);
            visitedPlaces.remove(place);
        }
    }

    private char[][] getNewInputArray(char[][] input, String city, Places place) {
        char[][] newInput = createNewArray(input);
        if (place instanceof Horizontal) {
            int i = ((Horizontal) place).start;
            int row = ((Horizontal) place).row;
            for (char charValue : city.toCharArray()) {
                if (newInput[row][i] != '-') {
                    if (newInput[row][i] == charValue) {
                        continue;
                    } else {
                        return null;
                    }
                }
                newInput[((Horizontal) place).row][i++] = charValue;
            }
        } else if (place instanceof Vertical) {
            int i = ((Vertical) place).start;
            int column = ((Vertical) place).column;
            for (char charValue : city.toCharArray()) {
                if (newInput[i][column] != '-') {
                    if (newInput[i][column] == charValue) {
                        continue;
                    } else {
                        return null;
                    }
                }
                newInput[i++][column] = charValue;
            }
        }

        return newInput;
    }

    private char[][] createNewArray(char[][] input) {
        char[][] newInput = new char[input.length][];
        for (int i = 0; i < input.length; i++) {
            newInput[i] = new char[input[i].length];
            for (int j = 0; j < input[i].length; j++) {
                newInput[i][j] = input[i][j];
            }
        }
        return newInput;
    }

    private List<Places> getPlaces(char[][] input) {
        List<Places> places = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (input[i][j] == '-') {
                    // For left
                    int left = j;
                    for (int k = j; k >= 0; k--) {
                        if (input[i][k] == '-') left = k;
                        else break;
                    }
                    // For right
                    int right = j;
                    for (int k = j; k < input.length; k++) {
                        if (input[i][k] == '-') right = k;
                        else break;
                    }

                    // Add it to places if it is a word from left to right that starts at j
                    if (left != right && left == j) {
                        places.add(new Horizontal(left, right, i));
                    }

                    // For top
                    int top = i;
                    for (int k = i; k >= 0; k--) {
                        if (input[k][j] == '-') top = k;
                        else break;
                    }

                    // For bottom
                    int bottom = i;
                    for (int k = i; k < input.length; k++) {
                        if (input[k][j] == '-') bottom = k;
                        else break;
                    }

                    // Add it to places if it is a word from top to bottom that starts at i
                    if (top != bottom && top == i) {
                        places.add(new Vertical(top, bottom, j));
                    }
                }
            }
        }
        return places;
    }

}
