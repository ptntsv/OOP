package org.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for AdjMatrixGraph class.
 */
public class AdjMatrixGraphTest {

    @Test
    void createTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        Assertions.assertEquals(n, g.adjMatrix.rowsCapacity);
        Assertions.assertEquals(n, g.adjMatrix.columnsCapacity);

        Assertions.assertEquals(0, g.maps.gettinthashmap().size());
        Assertions.assertEquals(0, g.maps.getintthashmap().size());
    }

    @Test
    void addVerticesTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        int node1 = 1;
        g.addVertex(node1);
        int expectedKey = 0;

        Assertions.assertEquals(1, g.maps.gettinthashmap().size());
        Assertions.assertEquals(1, g.maps.getintthashmap().size());
        Assertions.assertEquals(expectedKey, g.maps.gettinthashmap().get(node1));

        node1 = 2;
        g.addVertex(node1);
        expectedKey = 1;
        Assertions.assertEquals(expectedKey, g.maps.gettinthashmap().get(node1));
        Assertions.assertEquals(node1, g.maps.getintthashmap().get(expectedKey));
    }

    @Test
    void removeVerticesTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }
        int node = 4;
        int nodeKey = g.maps.gettinthashmap().get(node);
        g.removeVertex(node);
        for (int i = 0; i < g.getVerticesN(); i++) {
            Assertions.assertEquals(Double.NaN, g.adjMatrix.get(nodeKey, i));
        }
        for (int i = 0; i < g.getVerticesN(); i++) {
            Assertions.assertEquals(Double.NaN, g.adjMatrix.get(i, nodeKey));
        }
    }

    @Test
    void addEdgeTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }
        g.addEdge(0, 1);
        Assertions.assertTrue(g.isAdjacent(0, 1));
        Assertions.assertEquals(1, g.adjMatrix.get(0, 1));
        g.addEdge(0, 2);
        Assertions.assertTrue(g.isAdjacent(0, 2));
        Assertions.assertEquals(1, g.adjMatrix.get(0, 2));
        g.addEdge(0, 3);
        Assertions.assertTrue(g.isAdjacent(0, 3));
        Assertions.assertEquals(1, g.adjMatrix.get(0, 3));
        g.addEdge(3, 4);
        Assertions.assertTrue(g.isAdjacent(3, 4));
        Assertions.assertEquals(1, g.adjMatrix.get(3, 4));
        g.addEdge(4, 1);
        Assertions.assertTrue(g.isAdjacent(4, 1));
        Assertions.assertEquals(1, g.adjMatrix.get(4, 1));
    }

    @Test
    void removeEdgeTest() {
        int n = 5;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }
        g.addEdge(0, 1);
        g.addEdge(2, 2);
        g.addEdge(3, 4);
        g.removeEdge(g.maps.gettinthashmap().get(0), g.maps.gettinthashmap().get(1));
        Assertions.assertFalse(
                g.isAdjacent(g.maps.gettinthashmap().get(0), g.maps.gettinthashmap().get(1)));
    }
}