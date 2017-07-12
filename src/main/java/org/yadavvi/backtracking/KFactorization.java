package org.yadavvi.backtracking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vishal on 12/7/17.
 */
public class KFactorization {

    private int N;
    private int[] a;
    private int[] mult; // NOTE: mult[] doesn't store the actual output, it only stores the factors found along the way.
    private int[] factors;
    private int position;
    private boolean found;

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
        kFactorization(1, 0);
    }

    private void kFactorization(int k, int r) {
        if (found) return;

        if (k == N) {
            found = true;
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
        factors = new int[position + 1];
        factors[0] = 1;
        for (int i = 1; i < position + 1; i++) {
            factors[i] = factors[i - 1] * mult[i - 1];
        }
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
