package org.yadavvi.coinchange;

import java.util.*;

/**
 * Created by vishal on 9/6/17.
 */
public class CoinChangeRecursively implements NumberOfWaysForCoinChange {

    private Map<Long, Map<CoinCombination, Long>> valAndCombinations = new HashMap<>();

    private static List<Long> convertArray(long[] array) {
        List<Long> result = new LinkedList<>();
        for (long item : array) {
            result.add(item);
        }
        return result;
    }

    @Override
    public long getWaysForCoinChange(long n, long[] c) {
        if (n == 0 || c.length == 0) return 0L;

        List<Long> coins = convertArray(c);
        coins.sort(Collections.reverseOrder());
        return getWaysForCoinChange(n, coins);
    }

    private Long getWaysForCoinChange(Long n, List<Long> coins) {
        if (valAndCombinations.get(n) != null) {
            Map<CoinCombination, Long> vals = valAndCombinations.get(n);
            Long count = vals.get(new CoinCombination(coins));
            if (count != null) {
                return count;
            }
        }
        if (coins.size() == 1) {
            Long noOfWays = n % coins.get(0) == 0 ? 1L : 0L;
            saveWaysInMap(n, coins, noOfWays);
            return noOfWays;
        }

        Long noOfWays = 0L;
        Long noOfWaysForFirstCoin = 0L;

        Long firstCoin = coins.get(0);
        List<Long> remainingCoins = new LinkedList<>(coins);
        remainingCoins.remove(0);

        for (long i = 0; i <= n; i += firstCoin) {
            noOfWays = getWaysForCoinChange(n - i, remainingCoins);
            if (noOfWays > 0) {
                noOfWaysForFirstCoin += noOfWays;
            }
        }
        saveWaysInMap(n, coins, noOfWaysForFirstCoin);
        /*This gives a NullPointerException
        return valAndCombinations.get(n).get(new CoinCombination(coins));*/
        return noOfWaysForFirstCoin;
    }

    private void saveWaysInMap(Long n, List<Long> remainingCoins, Long noOfWays) {
        Map<CoinCombination, Long> coinCombinationMap = new HashMap<>();
        coinCombinationMap.put(new CoinCombination(remainingCoins), noOfWays);
        valAndCombinations.put(n, coinCombinationMap);
    }

    static class CoinCombination {
        ArrayList<Long> coins;

        CoinCombination(List<Long> coins) {
            this.coins = new ArrayList<>(coins);
            coins.sort(Collections.reverseOrder());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof CoinCombination)) return false;

            CoinCombination other = (CoinCombination) obj;
            if (this.coins.size() != other.coins.size()) return false;

            for (int i = 0; i < coins.size(); i++) {
                if (!coins.get(i).equals(other.coins.get(i))) return false;
            }

            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            for (Long coin : coins) {
                builder.append(coin).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("]");
            return builder.toString();
        }
    }

}
