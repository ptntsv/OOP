package org.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IncidenceMatrixGraphTest {

    @Test
    void addVerticesTest() {
        IncidenceMatrixGraph<Integer> graph = new IncidenceMatrixGraph<>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        Assertions.assertEquals(3, graph.getVerticesN());
        for (var v : graph.incMatrix.keySet()) {
            Assertions.assertEquals(0, graph.incMatrix.get(v).size());
        }
    }

    @Test
    void addEdgesTest() {
        IncidenceMatrixGraph<Integer> graph = new IncidenceMatrixGraph<>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(4, 4);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(new Edge<>(v1, v2));
        graph.addEdge(new Edge<>(v2, v3));
        graph.addEdge(new Edge<>(v3, v4));
        graph.addEdge(new Edge<>(v4, v1));

        Assertions.assertTrue(graph.isAdjacent(v1, v2));
        Assertions.assertTrue(graph.isAdjacent(v3, v4));
        Assertions.assertFalse(graph.isAdjacent(v1, v4));
        Assertions.assertFalse(graph.isAdjacent(v4, v4));
    }
}
