package org.yadavvi.kingdomdivision;

import java.util.*;

/**
 * Created by vishal on 10/6/17.
 */
public class KingdomDivisionDP implements KingdomDivision {

    @Override
    public int numberOfWaysToDivideAKingdom(int n, int[][] roads, int MOD_VALUE) {
        Graph graph = createGraph(n, roads);
        System.out.println(graph);
        return 4;
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
