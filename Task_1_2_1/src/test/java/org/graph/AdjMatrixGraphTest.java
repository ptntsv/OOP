package org.graph;

import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjMatrixGraphTest {

    @Test
    void addVerticesTest() {
        IGraph<Integer> graph = new AdjMatrixGraph<>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(4, 4);
        graph.addVertex(v1);
        graph.addVertex(v4);
        graph.addVertex(v2);
        graph.addVertex(v3);
        Assertions.assertEquals(4, graph.getVerticesN());
        graph.print();
    }

    @Test
    void removeVerticesTest() {
        var graph = new AdjMatrixGraph<Integer>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(5, 5);
        graph.addVertex(v1);
        graph.addVertex(v4);
        graph.addVertex(v2);
        graph.addVertex(v3);
        Assertions.assertEquals(4, graph.getVerticesN());
        graph.removeVertex(v1);
        Assertions.assertEquals(3, graph.getVerticesN());
        Assertions.assertFalse(graph.adjMatrix.containsKey(v1));
        for (var v : graph.adjMatrix.keySet()) {
            Assertions.assertFalse(graph.adjMatrix.get(v).containsKey(v1));
        }
    }

    @Test
    void addEdgeTest() {
        var graph = new AdjMatrixGraph<Integer>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(5, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addEdge(new Edge<>(v1, v2));
        Assertions.assertThrows(NoSuchVertexException.class,
            () -> graph.addEdge(new Edge<>(new Vertex<>(6, 100), v2)));
        Assertions.assertEquals(true, graph.adjMatrix.get(v1).get(v2));
        Assertions.assertEquals(false, graph.adjMatrix.get(v2).get(v1));
    }

    @Test
    void removeEdgeTest() {
        var graph = new AdjMatrixGraph<Integer>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(5, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);
        graph.addEdge(new Edge<>(v1, v2));
        graph.addEdge(new Edge<>(v2, v3));
        graph.addEdge(new Edge<>(v3, v4));
        graph.addEdge(new Edge<>(v4, v3));
        Assertions.assertEquals(true, graph.adjMatrix.get(v1).get(v2));
        Assertions.assertEquals(false, graph.adjMatrix.get(v2).get(v1));
        Assertions.assertEquals(true, graph.adjMatrix.get(v2).get(v3));

        graph.removeEdge(new Edge<>(v1, v2));
        graph.removeEdge(new Edge<>(v2, v1));
        Assertions.assertEquals(false, graph.adjMatrix.get(v1).get(v2));
        Assertions.assertEquals(false, graph.adjMatrix.get(v2).get(v1));

        graph.removeEdge(new Edge<>(v4, v3));
        Assertions.assertEquals(true, graph.adjMatrix.get(v3).get(v4));
        Assertions.assertEquals(false, graph.adjMatrix.get(v4).get(v3));
    }

    @Test
    void getAdjVerticesTest() {
        var graph = new AdjMatrixGraph<Integer>();
        var v1 = new Vertex<>(1, 1);
        var v2 = new Vertex<>(2, 2);
        var v3 = new Vertex<>(3, 3);
        var v4 = new Vertex<>(5, 5);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        graph.addEdge(new Edge<>(v1, v2));
        graph.addEdge(new Edge<>(v1, v1));
        graph.addEdge(new Edge<>(v1, v3));
        graph.addEdge(new Edge<>(v4, v1));

        var adjToV1 = graph.getAdjacent(v1);
        Assertions.assertEquals(3, adjToV1.size());
        Assertions.assertTrue(adjToV1.contains(v1));
        Assertions.assertTrue(adjToV1.contains(v2));
        Assertions.assertTrue(adjToV1.contains(v3));
        Assertions.assertFalse(adjToV1.contains(v4));

        graph.removeEdge(new Edge<>(v1, v2));
        var adjToV1ExceptV2 = graph.getAdjacent(v1);
        Assertions.assertFalse(adjToV1ExceptV2.contains(v2));
    }

    @Test
    void deserializeTest() {
        String src = "1 2\n2 3\n4 5\n5 1\n2 7\n3 9\n";
        var graph = AdjMatrixGraph.deserialize(src);
        var verticesMap = new HashMap<Integer, Vertex<Integer>>();
        for (var v : graph.adjMatrix.keySet()) {
            verticesMap.put(v.getKey(), v);
        }
        var v1 = verticesMap.get(1);
        var v2 = verticesMap.get(2);
        var v4 = verticesMap.get(4);
        var v5 = verticesMap.get(5);

        Assertions.assertTrue(graph.isAdjacent(v1, v2));
        Assertions.assertFalse(graph.isAdjacent(v2, v1));

        Assertions.assertTrue(graph.isAdjacent(v4, v5));
        Assertions.assertTrue(graph.isAdjacent(v5, v1));
    }
}
