package org.yadavvi.backtracking;

/**
 * Created by vishal on 12/7/17.
 */
public class PowerSum {

    private final int exponent;
    private int number;
    private int N;
    private int[] a;
    private int position;
    private int count;

    PowerSum(int number, int exponent) {
        if (number < 1) {
            throw new IllegalArgumentException("No square on an Integer is less than 1");
        }
        N = number == 1 ? 1 : number / 2;
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = 0; // Initialize the array with 0 (Not needed since int[] is always initialized to 0)
        }

        this.number = number;
        this.exponent = exponent;
        enumerate(1); // Starting it from 1 because Math.pow(0, x) would always be zero.
    }

    public void enumerate(int k) {
        if (number == 0) {
            process();
            return;
        }

        for (int i = k; i <= N; i++) {
            int product = (int) Math.pow(i, exponent);
            a[position++] = i;
            number = number - product;
            if (!canBacktrack()) enumerate(i + 1);
            // Cleanup
            number = number + product;
            a[--position] = 0;
        }
    }

    private boolean canBacktrack() {
        return number < 0;
    }

    private void process() {
        count++;
        /*for (int val : a) {
            if (val == 0) break;
            System.out.printf("%d^%d + ", val, 2);
        }
        System.out.println();*/
    }

    public static void main(String[] args) {
        PowerSum powerSum = new PowerSum(10, 2);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);

        powerSum = new PowerSum(100, 2);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
        powerSum = new PowerSum(400, 2);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
        powerSum = new PowerSum(800, 2);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
    }
}
