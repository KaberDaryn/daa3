package org.example;

import java.util.*;

public class Kruskal {

    public static class Result {
        List<Graph.Edge> mstEdges;
        int totalCost;
        int operationsCount;
        long executionTime;

        public Result(List<Graph.Edge> mstEdges, int totalCost, int operationsCount, long executionTime) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTime = executionTime;
        }
    }

    public static Result kruskal(Graph graph) {
        long startTime = System.nanoTime();

        List<Graph.Edge> mst = new ArrayList<>();
        int totalCost = 0;
        int operationsCount = 0;

        UnionFind uf = new UnionFind();

        // Инициализация множества для всех вершин
        for (String node : graph.getNodes()) {
            uf.makeSet(node);
        }

        // Сортировка рёбер по весу
        List<Graph.Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Graph.Edge::getWeight));

        // Алгоритм Краскала
        for (Graph.Edge edge : edges) {
            operationsCount++;
            if (uf.find(edge.getFrom()) != uf.find(edge.getTo())) {
                uf.union(edge.getFrom(), edge.getTo());
                mst.add(edge);
                totalCost += edge.getWeight();
            }
        }

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000000;

        return new Result(mst, totalCost, operationsCount, executionTime);
    }

    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();
        private Map<String, Integer> rank = new HashMap<>();

        public String find(String node) {
            if (!parent.containsKey(node)) {
                parent.put(node, node);
                rank.put(node, 0);
            }
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node)));
            }
            return parent.get(node);
        }

        public void union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);
            if (!root1.equals(root2)) {
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }

        public void makeSet(String node) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }
}
