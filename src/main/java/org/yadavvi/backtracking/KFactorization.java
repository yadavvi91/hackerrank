package org.yadavvi.backtracking;

import java.util.*;

/**
 * Created by vishal on 12/7/17.
 */
public class KFactorization {

    private int N;
    private int[] a;
    private int[] mult; // NOTE: mult[] doesn't store the actual output, it only stores the factors found along the way.
    private int[] factors;
    private int position;
    private int lastFactor;

    KFactorization(int N, int[] a) {
        List<Integer> integers = new LinkedList<>();
        for (int val : a) {
            integers.add(val);
        }
        Collections.sort(integers);

        int[] valid = getSanitizedFactors(N, integers);
        this.N = N;
        this.a = new int[valid.length];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = valid[i];
        }
        if (valid.length != 0) {
            int length = findValidLength(N, valid);
            this.mult = new int[length];
        } else {
            this.mult = new int[0];
        }
        this.position = 0;
        // We do not need to allocate memory for 'factors' here
        // this.factors = new int[mult.length + 1];
        this.lastFactor = 0;
    }

    private int findValidLength(int N, int[] valid) {
        int base = valid[0];
        int sum = 1;
        int count = 0;
        while (sum < N) {
            sum *= base;
            count++;
        }
        return count + 1;
    }

    private int[] getSanitizedFactors(int N, List<Integer> integers) {
        List<Integer> validFactors = new ArrayList<>();
        for (Integer val : integers) {
            if (N % val == 0) {
                validFactors.add(val);
            }
        }
        int[] valid = new int[validFactors.size()];
        for (int i = 0; i < valid.length; i++) {
            valid[i] = validFactors.get(i);
        }
        return valid;
    }

    public void kFactorization() {
        kFactorization(1, 0);
    }

    private void kFactorization(long k, int r) {
        if (k == N) {
            process();
            return;
        }

        // 'i' should start at 'r' and the next number at kFactorization should also have 'i' start
        // with 'r' rather than 'r+1' because a number can be repeated.
        // for e.g.: N - 600000, factors - {2, 3, 5, 7, 11, 13, 17, 19}
        // has output - {1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000}
        // for which mult[] values are {2, 2, 2, 2, 2, 2, 3, 5, 5, 5, 5, 5}
        for (int i = r; i < a.length; i++) {
            mult[position++] = a[i];
            if (!canBacktrack(k * a[i])) kFactorization(k * a[i], i);
            mult[--position] = 0;
        }
    }

    private boolean canBacktrack(long k) {
        return k > N;
    }

    private void process() {
        // -1 because we need to consider the first 1 in
        // factors[] which isn't there in mult[].
        if (lastFactor != 0 && position >= lastFactor - 1) return;

        factors = new int[position + 1];
        factors[0] = 1;
        for (int i = 1; i < position + 1; i++) {
            factors[i] = factors[i - 1] * mult[i - 1];
        }

        // Reset the lastFactor
        lastFactor = position + 1;
    }

    public int[] smallestFactors() {
        return factors == null ? new int[]{-1} : factors;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int q = scanner.nextInt();
        int[] a = new int[q];
        for (int i = 0; i < q; i++) {
            a[i] = scanner.nextInt();
        }

        KFactorization kFactorization = new KFactorization(N, a);
        kFactorization.kFactorization();
        int[] result = kFactorization.smallestFactors();

        for (int val : result) {
            System.out.printf("%d ", val);
        }
        System.out.println();
        scanner.close();
    }

}
