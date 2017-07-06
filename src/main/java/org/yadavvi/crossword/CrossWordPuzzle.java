package org.yadavvi.crossword;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vishal on 6/7/17.
 */
public class CrossWordPuzzle {

    public char[][] solveCrossWordPuzzle(char[][] input, List<String> cities) {
        List<Places> places = getPlaces(input);
        Collections.sort(places);

        for (Places place : places) {
            System.out.println(place);
        }
        return null;
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
