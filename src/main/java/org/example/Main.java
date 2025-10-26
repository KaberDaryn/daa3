package org.example;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try {
            // Загрузка файла input.json из ресурсов
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("input.json");
            if (inputStream == null) {
                throw new IOException("File not found in resources");
            }

            // Чтение графа из потока
            Graph graph = JSONUtils.readGraphFromFile(inputStream);

            // Выводим информацию о загруженном графе
            System.out.println("Loaded graph: ");
            System.out.println("Nodes: " + graph.getNodes());  // Используем геттер
            System.out.println("Edges: " + graph.getEdges());  // Используем геттер

            // Запуск алгоритмов Прима и Краскала
            Prim.Result primResult = Prim.prim(graph);
            Kruskal.Result kruskalResult = Kruskal.kruskal(graph);

            // Запись результатов в файл
            JSONUtils.writeResultToFile("output.json", primResult, kruskalResult);

            // Вывод результатов на консоль
            System.out.println("Prim's algorithm result: ");
            primResult.mstEdges.forEach(edge -> System.out.println(edge.getFrom() + " - " + edge.getTo() + ": " + edge.getWeight()));
            System.out.println("Total cost: " + primResult.totalCost);
            System.out.println("Operations count: " + primResult.operationsCount);
            System.out.println("Execution time: " + primResult.executionTime + "ms");

            System.out.println("\nKruskal's algorithm result: ");
            kruskalResult.mstEdges.forEach(edge -> System.out.println(edge.getFrom() + " - " + edge.getTo() + ": " + edge.getWeight()));
            System.out.println("Total cost: " + kruskalResult.totalCost);
            System.out.println("Operations count: " + kruskalResult.operationsCount);
            System.out.println("Execution time: " + kruskalResult.executionTime + "ms");

        } catch (IOException e) {
            System.err.println("Error reading graph data: " + e.getMessage());
            e.printStackTrace(); // Выводим подробности ошибки
        }
    }
}
