package org.yadavvi.coinchange;

import java.util.*;

/**
 * Created by vishal on 8/6/17.
 */
public class CoinChange implements NumberOfWaysForCoinChange {

    public static ArrayList<Long> convertArray(long[] array) {
        ArrayList<Long> result = new ArrayList<>(array.length);
        for (long item : array) {
            result.add(item);
        }
        return result;
    }

    public long getWaysForCoinChange(long n, long[] c) {
        return getWays(n, c);
    }

    public static long getWays(long n, long[] c) {
        if (n == 0 || c.length == 0) return 0L;
        ArrayList<Long> coins = convertArray(c);
        Collections.sort(coins);
        HashSet<CoinValues>[] arr = new HashSet[((int) n) + 1];

        arr[0] = new HashSet<>();
        arr[0].add(new CoinValues(coins));

        for (int i = 1; i <= n; i++) {
            arr[i] = new HashSet<>();
        }

        for (int i = 1; i <= n; i++) {
            HashSet<CoinValues> coinValuesOfN = arr[i];
            for (Long coin : coins) {
                if (coin > i) break;
                HashSet<CoinValues> valuesOfOtherN = arr[i - ((int) ((long) coin))];
                for (CoinValues coinValues : valuesOfOtherN) {
                    CoinValues coinValues1 = new CoinValues(coins);
                    for (Map.Entry<Long, Integer> entry : coinValues.coinCount.entrySet()) {
                        coinValues1.coinCount.put(entry.getKey(), entry.getValue());
                    }
                    coinValues1.coinCount.put(coin, coinValues.coinCount.get(coin) + 1);
                    coinValuesOfN.add(coinValues1);
                }
            }
        }
        return arr[((int) n)].size();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] c = new long[m];
        for (int c_i = 0; c_i < m; c_i++) {
            c[c_i] = in.nextLong();
        }
        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
        long ways = getWays(n, c);
    }

    static class CoinValues {
        HashMap<Long, Integer> coinCount;

        CoinValues(List<Long> availableCoins) {
            coinCount = new HashMap<>();
            for (Long coin : availableCoins) {
                coinCount.put(coin, 0);
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof CoinValues)) return false;

            CoinValues otherCoinValues = (CoinValues) obj;
            HashMap<Long, Integer> otherCoinCount = otherCoinValues.coinCount;
            if (coinCount.size() != otherCoinCount.size()) return false;

            for (Long key : otherCoinCount.keySet()) {
                if (!coinCount.get(key).equals(otherCoinCount.get(key))) return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            return coinCount.hashCode();
        }
    }

}
