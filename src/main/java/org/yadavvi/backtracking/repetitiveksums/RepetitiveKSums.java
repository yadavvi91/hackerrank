package org.yadavvi.backtracking.repetitiveksums;

import java.util.Scanner;

/**
 * Created by vishal on 13/7/17.
 */
public class RepetitiveKSums {

    private int N;
    private int k;
    private int[] sums;
    private int[] found;
    private int foundPosition;
    private int actualCount;

    RepetitiveKSums(int N, int k, int[] sums) {
        this.N = N;
        this.k = k;
        this.sums = sums;
        this.found = new int[N];
        this.foundPosition = 0;
        this.actualCount = 0;

        // Starting point
        int i = sums[0];
        int first = i / k;
        found[foundPosition++] = first;
        if (k > 1) {
            int second = sums[1] - first * (k - 1);
            found[foundPosition++] = second;
            findValues();
        }
    }

    private void findValues() {
        int i = 2;
        int prev = 0;
        while (foundPosition != N) {
            int val = sums[i];
            if (prev == val) {
                found[foundPosition++] = val - found[0] * (k - 1);
                i++;
                continue;
            }
            int count = getWaysToFormValUsingFound(val);
            if (count == 0) {
                found[foundPosition++] = val - found[0] * (k - 1);
                i++;
            }
            while (count > 0) {
                i = i + 1;
                count = count - 1;
            }
            prev = val;
        }
    }

    private int getWaysToFormValUsingFound(int val) {
        int[] validFound = new int[foundPosition];
        for (int i = 0; i < foundPosition; i++) {
            validFound[i] = found[i];
        }

        int[] numbers = new int[k];
        for (int i = 0; i < k; i++) {
            numbers[i] = found[0];
        }
        actualCount = 0;
        enumerate(val, validFound, numbers, 0);
        return actualCount;
    }

    private void enumerate(int val, int[] validFound, int[] numbers, int l) {
        if (l == k) {
            process(numbers);
            if (sumIsVal(numbers, val)) {
                actualCount++;
            }
            return;
        }

        for (int i = 0; i < validFound.length; i++) {
            numbers[l] = validFound[i];
            enumerate(val, validFound, numbers, l + 1);
        }
    }

    private void process(int[] numbers) {
        for (int number : numbers) {
            System.out.printf("%d ", number);
        }
        System.out.println();
    }

    private boolean sumIsVal(int[] numbers, int val) {
        int maxValue = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (maxValue < numbers[i]) maxValue = numbers[i];
            else if (maxValue > numbers[i]) return false;
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum == val;
    }

    public int[] getRepetitiveKSums() {
        return found;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int T = scanner.nextInt();
            for (int i = 0; i < T; i++) {
                int N = scanner.nextInt();
                int k = scanner.nextInt();
                scanner.nextLine();

                String line = scanner.nextLine();
                String[] sumsString = line.split(" ");
                int[] sums = new int[sumsString.length];
                for (int j = 0; j < sumsString.length; j++) {
                    sums[j] = Integer.valueOf(sumsString[j]);
                }

                RepetitiveKSums kSums = new RepetitiveKSums(N, k, sums);
                int[] repetitiveKSums = kSums.getRepetitiveKSums();

                for (int sum : repetitiveKSums) {
                    System.out.printf("%d ", sum);
                }
                System.out.println();
            }
        }
    }

}
