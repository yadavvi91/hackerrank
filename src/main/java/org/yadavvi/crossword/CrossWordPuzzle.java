package org.yadavvi.crossword;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vishal on 6/7/17.
 */
public class CrossWordPuzzle {

    public char[][] solveCrossWordPuzzle(char[][] input, List<String> cities) {
        List<Places> places = getPlaces(input);
        Collections.sort(places);
        Collections.sort(cities, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        /*for (Places place : places) {
            System.out.println(place);
        }*/
        List<Places> visitedPlaces = new LinkedList<>();
        List<List<Places>> validWays = new LinkedList<>();
        solveCrossWordPuzzle(input, cities, places, visitedPlaces, validWays);
        for (List<Places> validWay : validWays) {
            System.out.println(validWay);
        }
        return input;
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
        char[][] newInput = copyOldArray(input);
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

    private char[][] copyOldArray(char[][] input) {
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
