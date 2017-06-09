package org.yadavvi.decbinary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vishal on 2/6/17.
 */
public class NumberOfDecBinariesUsingArithmeticProgression implements NumberOfDecBinaries {

    public static void main(String[] args) {
        NumberOfDecBinariesUsingArithmeticProgression binaries = new NumberOfDecBinariesUsingArithmeticProgression();
        System.out.println("---------------------");
        //binaries.decBinaryStringForPosition(4);
        System.out.println("---------------------");
        binaries.decBinaryStringForPosition(100);
        //binaries.decBinaryStringForPosition(36);
    }

    public String decBinaryStringForPosition(int position) {
        if (position == 0) return "0";
        if (position == 1) return "1";

        List<Integer> count = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            count.add(1);
        }

        System.out.println(" 0:  1");
        System.out.println(" 1:  1");
        int sum = 2; // Added 0 and 1 sizes.

        int value = 2; // start from 2
        int powerOfTwo = 2;
        int counter = 0;
        while (sum < position) {
            if (value == count.size()) {
                count.add(0);
            }

            counter = count.get(value);
            if (value / powerOfTwo > 0 && value % powerOfTwo != 0) {
                counter += count.get(value - powerOfTwo);
                count.set(value, counter);

                if (value / (powerOfTwo * 2) > 0) {
                    powerOfTwo *= 2;
                } else {
                    System.out.printf("%2d: %2d%n", value, counter);
                    sum += counter;
                    value++;
                    powerOfTwo = 2;
                }
            } else if (value % powerOfTwo == 0) {
                counter += count.get(value - powerOfTwo);
                count.set(value, counter);

                if (value / (powerOfTwo * 2) > 0) {
                    powerOfTwo *= 2;
                } else {
                    System.out.printf("%2d: %2d%n", value, counter);
                    sum += counter;
                    value++;
                    powerOfTwo = 2;
                }
            }

        }
        if (sum > position) return "";
        return "";
    }

}
