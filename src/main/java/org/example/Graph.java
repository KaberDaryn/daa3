// Graph.java
package org.example;

import java.util.List;

public class Graph {
    private List<String> nodes;  // Список узлов
    private List<Edge> edges;    // Список рёбер

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    // Геттеры
    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    // Вложенный класс для рёбер
    public static class Edge {
        private String from;
        private String to;
        private int weight;

        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // Геттеры
        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        // Метод для вывода ребра в строковом виде
        @Override
        public String toString() {
            return from + " - " + to + ": " + weight;
        }
    }
}
