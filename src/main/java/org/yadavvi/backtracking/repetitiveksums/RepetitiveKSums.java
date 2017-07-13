package org.yadavvi.backtracking.repetitiveksums;

import java.util.Scanner;

/**
 * Created by vishal on 13/7/17.
 */
public class RepetitiveKSums {

    private int N;
    private int k;
    private int[] sums;

    RepetitiveKSums(int N, int k, int[] sums) {
        this.N = N;
        this.k = k;
        this.sums = sums;
    }

    public int[] getRepetitiveKSums() {
        return new int[0];
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
