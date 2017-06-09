package org.yadavvi.decbinary;

import org.yadavvi.util.ReverseIterable;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by vishal on 3/6/17.
 */
public class NumberOfDecBinariesDP implements NumberOfDecBinaries {

    private List<Stack<String>> decBinaries;

    public NumberOfDecBinariesDP() {
        decBinaries = new LinkedList<>();
        Stack<String> forZero = new Stack<>();
        forZero.push("0");
        Stack<String> forOne = new Stack<>();
        forOne.push("1");
        decBinaries.add(forZero);
        decBinaries.add(forOne);
    }

    public static void main(String[] args) {
        NumberOfDecBinaries numberOfDecBinaries = new NumberOfDecBinariesDP();
        System.out.println("For: " + Integer.MAX_VALUE / 92 + " - " + numberOfDecBinaries.decBinaryStringForPosition(Integer.MAX_VALUE / 92));
    }

    @Override
    public String decBinaryStringForPosition(int position) {
        if (position == 1) return decBinaries.get(0).peek();
        if (position == 2) return decBinaries.get(1).peek();

        int sum = 2; // 0 has 1 decbinary representation - <0>, 1 has 1 decbinary representation - <1>
        int value = 2;
        while (sum < position) {
            String binaryOfValue = getBinaryForDecimal(value);
            Stack<String> stack = new Stack<>();
            getDecBinariesCombForADecNumber(value, binaryOfValue, stack);
            Stack<String> reversedStack = new Stack<>();
            while (!stack.empty()) {
                reversedStack.push(stack.pop());
            }
            decBinaries.add(reversedStack);

            sum = sum + decBinaries.get(value).size();
            value++;
        }

        /*File file = new File(System.getenv().get("HOME") + "/output.txt");
        try {
            file.createNewFile();
            try (BufferedWriter stream = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < decBinaries.size(); i++) {
                    stream.write(String.format("%3d(%3d): %s%n", i, decBinaries.get(i).size(), decBinaries.get(i)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if (sum == position) {
            return decBinaries.get(value - 1).peek();
        } else if (sum > position) {
            sum = sum - decBinaries.get(value - 1).size();
            int remaining = position - sum;
            String decBinaryAtPosition = "";
            for (String string : decBinaries.get(value - 1)) {
                if (remaining <= 0) break;
                decBinaryAtPosition = string;
                remaining--;
            }
            return decBinaryAtPosition;
        }
        return null;
    }

    private Stack<String> getDecBinariesCombForADecNumber(int value, String decBinary,
                                                          Stack<String> decBinariesForValue) {
        if (decBinary.isEmpty()) return decBinariesForValue;
        if (decBinary.length() == 1) {
            decBinariesForValue.push(decBinary);
            return decBinariesForValue;
        }

        char[] decBinArrChar = new StringBuilder(decBinary).reverse().toString().toCharArray();
        int[] decBinArr = new int[decBinArrChar.length];
        for (int i = 0; i < decBinArr.length; i++) {
            decBinArr[i] = decBinArrChar[i] - '0';
        }
        int max = decBinArr.length - 1;

        int powOf2ForMaxPos = getPowerOf2ForPosInBinaryNumber(max);
        int remainingValue = value - powOf2ForMaxPos * decBinArr[max];
        if (remainingValue < 0) return decBinariesForValue;

        String maxMinusOneString = "";
        for (String decBinaryValue : ReverseIterable.reversed(decBinaries.get(remainingValue))) {
            if (decBinaryValue.length() == decBinary.length() - 1) {
                maxMinusOneString = decBinArrChar[max] + decBinaryValue;
            }
            if (decBinaryValue.length() > decBinary.length() - 1) continue;
            StringBuilder decBinaryValueBuilder = new StringBuilder(decBinaryValue);
            while (decBinaryValueBuilder.length() < decBinary.length() - 1) {
                decBinaryValueBuilder.insert(0, "0");
            }
            decBinaryValue = decBinaryValueBuilder.toString();
            decBinariesForValue.push(decBinArrChar[max] + decBinaryValue);
            // System.out.println("decBinary:" + decBinary + " - " + decBinariesForValue);
        }

        String nextDecBinaryString;
        if (decBinArr[max - 1] + 2 > 9) {
            // System.out.println(maxMinusOneString);
            if (maxMinusOneString.equalsIgnoreCase(decBinary)) return decBinariesForValue;

            char[] decBinArrChar2 = new StringBuilder(maxMinusOneString).reverse().toString().toCharArray();
            int[] decBinArr2 = new int[decBinArrChar2.length];
            for (int i = 0; i < decBinArrChar2.length; i++) {
                decBinArr2[i] = decBinArrChar2[i] - '0';
            }

            StringBuilder builder = new StringBuilder();
            if (max - 1 <= 0) return decBinariesForValue;
            if (decBinArr2[max - 1] + 2 > 9) return decBinariesForValue;
            decBinArr2[max - 1] += 2;
            decBinArr2[max] -= 1;
            for (int i = 0; i < decBinArr2.length - 1; i++) {
                builder.append(decBinArr2[i]);
            }
            nextDecBinaryString = builder.reverse().toString();
            nextDecBinaryString = decBinArr2[max] == 0 ? nextDecBinaryString : decBinArr2[max] + nextDecBinaryString;

            // System.out.println("Value: " + value + " When  > 9 - DecBinString: " + nextDecBinaryString);
            return getDecBinariesCombForADecNumber(value, nextDecBinaryString, decBinariesForValue);
        } else {
            StringBuilder builder = new StringBuilder();
            if (max - 1 < 0) return decBinariesForValue;
            if (decBinArr[max - 1] + 2 > 9) return decBinariesForValue;
            decBinArr[max - 1] += 2;
            decBinArr[max] -= 1;
            for (int i = 0; i < decBinArr.length - 1; i++) {
                builder.append(decBinArr[i]);
            }
            nextDecBinaryString = builder.reverse().toString();
            nextDecBinaryString = decBinArr[max] == 0 ? nextDecBinaryString : decBinArr[max] + nextDecBinaryString;

            // System.out.println("Value: " + value + " When !> 9 - DecBinString: " + nextDecBinaryString);
            return getDecBinariesCombForADecNumber(value, nextDecBinaryString, decBinariesForValue);
        }
    }

    private int getPowerOf2ForPosInBinaryNumber(int length) {
        int decimal = 1;
        for (int i = 0; i < length; i++) {
            decimal *= 2;
        }
        return decimal;
    }

    private String getBinaryForDecimal(int value) {
        StringBuilder builder = new StringBuilder();
        while (value > 0) {
            builder.append(value % 2);
            value = value / 2;
        }
        return builder.reverse().toString();
    }

}
