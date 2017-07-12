package org.yadavvi.backtracking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vishal on 12/7/17.
 */
public class KFactorization {

    private int N;
    private int[] a;
    private int[] mult;
    private int position;

    KFactorization(int N, int[] a) {
        List<Integer> integers = new LinkedList<>();
        for (int val : a) {
            integers.add(val);
        }
        Collections.sort(integers);

        this.N = N;
        this.a = new int[a.length];
        for (int i = 0; i < this.a.length; i++) {
            this.a[i] = integers.get(i);
        }
        this.mult = new int[a.length];
        this.position = 0;
    }

    public void kFactorization() {
        mult[position++] = 1;
        kFactorization(1, 0);
    }

    private void kFactorization(int k, int r) {
        if (k == N) {
            process();
            return;
        }

        for (int i = r; i < a.length; i++) {
            mult[position++] = a[i];
            if (!canBacktrack(k * a[i])) kFactorization(k * a[i], r + 1);
            mult[--position] = 0;
        }
    }

    private boolean canBacktrack(int k) {
        return k > N;
    }

    private void process() {
        for (int i = 0; i < position; i++) {
            System.out.printf("%d ", mult[i]);
        }
        System.out.println();
    }

    public int[] smallestFactors() {
        if (position == 1 && mult[position - 1] == 1) {
            return new int[]{-1};
        }

        int[] factors = new int[position + 1];
        factors[0] = 1;
        for (int i = 1; i < position + 1; i++) {
            factors[i] = factors[i - 1] * mult[i];
        }

        return factors;
    }

    public static void main(String[] args) {
        KFactorization kFactorization = new KFactorization(72, new int[]{2, 4, 6, 9, 3, 7, 16, 10, 5});
        kFactorization.kFactorization();
        int[] result = kFactorization.smallestFactors();
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%d ", result[i]);
        }
        System.out.println();
    }
}
