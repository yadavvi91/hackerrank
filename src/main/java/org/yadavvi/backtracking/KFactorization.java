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

    private boolean found;

    KFactorization(int N, int[] a) {
        List<Integer> integers = new LinkedList<>();
        for (int val : a) {
            integers.add(val);
        }
        Collections.sort(integers, Collections.reverseOrder());

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
        this.factors = new int[mult.length + 1];
        this.lastFactor = 0;
    }

    private int findValidLength(int N, int[] valid) {
        int length = N / valid[valid.length - 1] + 1;
        List<Integer> integers = new ArrayList<>(valid.length);
        for (int val : valid) {
            integers.add(val);
        }
        Collections.sort(integers);
        int value = N;

        int count = 0;
        for (int j = 0; j < valid.length; j++) {
            int val = valid[j];
            while (value % val == 0) {
                value = value / val;
                count++;
            }
        }

        return count < 5000 ? 5000 : count;
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
        kFactorization(1);
    }

    private void kFactorization(long k) {
        if (found) return;

        if (k == N) {
            found = true;
            process();
            return;
        }

        // i starts from 0 rather than r because the combinations can be repeated
        // for e.g.: N - 600000, factors - {2, 3, 5, 7, 11, 13, 17, 19}
        // has output - {1, 2, 4, 8, 16, 32, 64, 192, 960, 4800, 24000, 120000, 600000}
        // for which mult[] values are {2, 2, 2, 2, 2, 2, 3, 5, 5, 5, 5, 5}
        for (int i = 0; i < a.length; i++) {
            mult[position++] = a[i];
            if (!canBacktrack(k * a[i])) kFactorization(k * a[i]);
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

        int[] mult_rev = new int[position];
        // copy valid values in mult_rev
        for (int i = 0; i < position; i++) {
            mult_rev[i] = mult[i];
        }
        // reverse mult_rev
        for (int i = 0; i < mult_rev.length / 2; i++) {
            int temp = mult_rev[i];
            mult_rev[i] = mult_rev[mult_rev.length - 1 - i];
            mult_rev[mult_rev.length - 1 - i] = temp;
        }

        factors[0] = 1;
        for (int i = 1; i < position + 1; i++) {
            factors[i] = factors[i - 1] * mult_rev[i - 1];
        }

        // Set extra values to 0
        for (int i = position + 1; i < lastFactor; i++) {
            factors[i] = 0;
        }
        // Reset the lastFactor
        lastFactor = position + 1;
    }

    public int[] smallestFactors() {
        int i;
        for (i = 0; i < factors.length; i++) {
            if (factors[i] == 0) break;
            if (i > 0 && factors[i - 1] == N && factors[i] == N) break;
        }
        int[] validFactor = new int[i];
        for (int j = 0; j < i; j++) {
            validFactor[j] = factors[j];
        }
        return validFactor.length == 0 ? new int[]{-1} : validFactor;
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
