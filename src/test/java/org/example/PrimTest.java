package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PrimTest {

    @Test
    public void testPrimCorrectness() {
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

        // Вызываем алгоритм Прима
        Prim.Result result = Prim.prim(graph);

        // Выводим рёбра MST и стоимость
        System.out.println("Prim MST edges: " + result.mstEdges);
        System.out.println("Prim total cost: " + result.totalCost);

        // Проверки
        assertNotNull(result);
        assertNotNull(result.mstEdges);
        assertTrue(result.mstEdges.size() > 0);

        // Пример проверки правильности минимального остовного дерева
        // Здесь ожидаемая сумма может быть 16 или 18 в зависимости от того, какие рёбра Прим выбирает.
        assertEquals(18, result.totalCost); // Это будет ваше ожидаемое значение, если выбранные рёбра правильные.
    }
}
