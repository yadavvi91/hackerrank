package org.yadavvi.kingdomdivision;

import java.util.*;

/**
 * Created by vishal on 10/6/17.
 */
public class KingdomDivisionDP implements KingdomDivision {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] roads = new int[n - 1][2];
        for (int a0 = 0; a0 < n - 1; a0++) {
            int u = in.nextInt();
            int v = in.nextInt();
            roads[a0][0] = u;
            roads[a0][1] = v;
        }

        KingdomDivisionDP kingdomDivisionDP = new KingdomDivisionDP();
        System.out.println(kingdomDivisionDP.numberOfWaysToDivideAKingdom(n, roads, ((int) Math.pow(10, 9) + 7)));
    }

    @Override
    public int numberOfWaysToDivideAKingdom(int n, int[][] roads, int MOD_VALUE) {
        if (roads.length == 0) return 1;
        if (roads.length == 1) return 2;
        Graph graph = createGraph(n, roads);
        return numberOfWaysToDivideAKingdom(graph, MOD_VALUE);
    }

    private int numberOfWaysToDivideAKingdom(Graph graph, int MOD_VALUE) {
        int[] colorCount = new int[graph.bags.length];
        int firstCity = -1;
        for (Bag bag : graph.bags) {
            if (bag != null) {
                firstCity = bag.city;
                break;
            }
        }
        if (firstCity == -1) return 0;
        return numberOfWaysToDivideAKingdom(graph, MOD_VALUE, colorCount, firstCity);
    }

    private int numberOfWaysToDivideAKingdom(Graph graph, int MOD_VALUE, int[] colorCount, int fromCity) {
        if (colorCount[fromCity] != 0) return colorCount[fromCity];
        List<Integer> toCities = graph.getRoadsToCities(fromCity);
        if (toCities == null) return 0;
        if (toCities.size() == 0) return 0;

        int ways = 1;
        for (Integer toCity : toCities) {
            int toCityWays = numberOfWaysToDivideAKingdom(graph, MOD_VALUE, colorCount, toCity) % MOD_VALUE;
            colorCount[toCity] = toCityWays;
            if (toCityWays != 0) {
                ways = (ways * toCityWays) % MOD_VALUE;
            }
        }
        colorCount[fromCity] = ways == 1 ? 2 : ways * 2 % MOD_VALUE;
        return colorCount[fromCity];
    }

    private Graph createGraph(int n, int[][] roads) {
        Map<Integer, List<Integer>> mapOfRoads = getMapOfRoads(roads);

        Graph graph = new Graph(n);
        for (Map.Entry<Integer, List<Integer>> entry : mapOfRoads.entrySet()) {
            graph.addAdjacencyList(entry.getKey(), entry.getValue());
        }
        return graph;
    }

    private Map<Integer, List<Integer>> getMapOfRoads(int[][] roads) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int from = -1;
        for (int[] road : roads) {
            if (from != road[0]) from = road[0];

            List<Integer> roadList = map.get(from);
            if (roadList == null) {
                map.put(from, new ArrayList<>());
                roadList = map.get(from);
            }
            roadList.add(road[1]);
        }
        for (Integer key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        return map;
    }

    static class Bag {
        int city;
        List<Integer> roadsToCities;

        Bag(int city, List<Integer> roadsToCities) {
            this.city = city;
            this.roadsToCities = new LinkedList<>(roadsToCities);
        }

        @Override
        public String toString() {
            return "{" + city + ": " + roadsToCities + "}";
        }
    }

    static class Graph {
        Bag[] bags;

        Graph(int n) {
            bags = new Bag[n + 1];
        }

        void addAdjacencyList(Integer key, List<Integer> value) {
            bags[key] = new Bag(key, value);
        }

        List<Integer> getRoadsToCities(Integer fromCity) {
            if (bags[fromCity] == null) return null;
            return bags[fromCity].roadsToCities;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("[\n");
            for (Bag bag : bags) {
                builder.append("\t").append(bag).append(",\n");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("\n]");
            return builder.toString();
        }
    }

}
