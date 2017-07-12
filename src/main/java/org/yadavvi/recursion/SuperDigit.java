package org.yadavvi.recursion;

import java.util.Scanner;

/**
 * Created by vishal on 12/7/17.
 */
public class SuperDigit {

    private char[] input;

    public SuperDigit(String n, String k) {
        int sum = 0;
        for (char val : String.valueOf(n).toCharArray()) {
            sum += val - '0';
        }
        sum = superDigitValue(String.valueOf(sum).toCharArray());
        sum *= Integer.valueOf(k);

        input = String.valueOf(sum).toCharArray();
    }

    public int superDigitValue() {
        return superDigitValue(input);
    }

    private int superDigitValue(char[] input) {
        if (input.length == 1) return input[0] - '0';

        int sum = 0;
        for (char val : input) {
            sum += val - '0';
        }
        return superDigitValue(String.valueOf(sum).toCharArray());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.next();
        String k = scanner.next();
        SuperDigit superDigit = new SuperDigit(n, k);
        System.out.println(superDigit.superDigitValue());

        scanner.close();
    }

}
