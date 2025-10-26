package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class KruskalTest {

    @Test
    public void testKruskalCorrectness() {
        // Инициализация тестового графа
        Graph graph = new Graph(
                List.of("A", "B", "C", "D", "E"),
                List.of(
                        new Graph.Edge("A", "B", 4),
                        new Graph.Edge("A", "C", 3),
                        new Graph.Edge("B", "C", 2),
                        new Graph.Edge("B", "D", 5),
                        new Graph.Edge("C", "D", 7),
                        new Graph.Edge("C", "E", 8),
                        new Graph.Edge("D", "E", 6)
                )
        );

        // Запуск алгоритма Краскала
        Kruskal.Result result = Kruskal.kruskal(graph);

        // Проверки
        assertNotNull(result);
        assertNotNull(result.mstEdges);
        assertTrue(result.mstEdges.size() > 0);

        // Пример проверки правильности минимального остовного дерева
        assertEquals(16, result.totalCost);
    }
}
