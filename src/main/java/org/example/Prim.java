package org.example;

import java.util.*;

public class Prim {
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

    public static Result prim(Graph graph) {
        long startTime = System.nanoTime();
        int totalCost = 0;
        int operationsCount = 0;
        List<Graph.Edge> mstEdges = new ArrayList<>();
        Set<String> visitedNodes = new HashSet<>();
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Graph.Edge::getWeight));

        // Начинаем с первого узла
        String startNode = graph.getNodes().get(0);
        visitedNodes.add(startNode);
        pq.addAll(getEdgesFromNode(startNode, graph));

        while (!pq.isEmpty()) {
            Graph.Edge edge = pq.poll();
            operationsCount++;

            // Добавляем ребро в дерево, если оно соединяет ещё не посещённую вершину
            if (!visitedNodes.contains(edge.getTo())) {
                visitedNodes.add(edge.getTo());
                mstEdges.add(edge);
                totalCost += edge.getWeight();
                pq.addAll(getEdgesFromNode(edge.getTo(), graph));  // Добавляем рёбра из новой вершины
            }
        }

        long endTime = System.nanoTime();
        long executionTime = (endTime - startTime) / 1000000;

        return new Result(mstEdges, totalCost, operationsCount, executionTime);
    }

    private static List<Graph.Edge> getEdgesFromNode(String node, Graph graph) {
        List<Graph.Edge> edges = new ArrayList<>();
        for (Graph.Edge edge : graph.getEdges()) {
            if (edge.getFrom().equals(node)) {
                edges.add(edge);
            }
        }
        return edges;
    }
}
