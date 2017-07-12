package org.yadavvi.backtracking;

/**
 * Created by vishal on 12/7/17.
 */
public class PowerSum {

    int number;
    int N;
    // double[] squares;
    int[] a;
    int position;
    int count;

    PowerSum(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("No square on an Integer is less than 1");
        }
        N = number == 1 ? 1 : number / 2;
        a = new int[N];
        // squares = new double[N];

        this.number = number;
        enumerate(1);
    }

    public void enumerate(int k) {
        if (number == 0) {
            process();
            return;
        }
        if (number < 0) {
            return;
        }

        for (int i = k; i <= N; i++) {
            int square = (int) Math.pow(i, 2);
            if (number - square < 0) {
                return;
            }
            a[position] = square;
            number = number - a[position];
            position++;
            enumerate(i + 1);
            // Cleanup
            number = number + a[--position];
            a[position] = 0;
        }
    }

    private void process() {
        count++;
        /*for (int val : a) {
            if (val == 0) break;
            System.out.printf("%d^%d + ", (int) Math.sqrt(val), 2);
        }
        System.out.println();*/
    }

    public static void main(String[] args) {
        PowerSum powerSum = new PowerSum(10);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);

        powerSum = new PowerSum(100);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
        powerSum = new PowerSum(400);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
        powerSum = new PowerSum(800);
        System.out.printf("Number: %d, Count: %d%n", powerSum.number, powerSum.count);
    }
}
