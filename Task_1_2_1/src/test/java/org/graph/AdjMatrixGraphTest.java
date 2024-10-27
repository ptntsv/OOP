package org.graph;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjMatrixGraphTest {

    @Test
    void createTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        Assertions.assertEquals(n, g.adjMatrix.rowsCapacity);
        Assertions.assertEquals(n, g.adjMatrix.columnsCapacity);

        Assertions.assertEquals(0, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(0, g.maps.getIntTHashMap().size());
    }

    @Test
    void addVerticesTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        int v = 1;
        int expVKey = 0;
        g.addVertex(v);

        Assertions.assertEquals(1, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(1, g.maps.getIntTHashMap().size());
        Assertions.assertEquals(expVKey, g.maps.gettIntHashMap().get(v));

        v = 2;
        expVKey = 1;
        g.addVertex(v);
        Assertions.assertEquals(expVKey, g.maps.gettIntHashMap().get(v));
        Assertions.assertEquals(v, g.maps.getIntTHashMap().get(expVKey));
    }

    @Test
    void removeVerticesTest() {
        int n = 10;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        for (int i = 0; i < n; i++) {
            g.addVertex(i);
        }
        int v = 4;
        int vKey = g.maps.gettIntHashMap().get(v);
        g.removeVertex(v);
        for (int i = 0; i < g.getVerticesN(); i++) {
            Assertions.assertEquals(Double.NaN, g.adjMatrix.get(vKey, i));
        }
        for (int i = 0; i < g.getVerticesN(); i++) {
            Assertions.assertEquals(Double.NaN, g.adjMatrix.get(i, vKey));
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
        g.removeEdge(g.maps.gettIntHashMap().get(0), g.maps.gettIntHashMap().get(1));
        Assertions.assertFalse(
            g.isAdjacent(g.maps.gettIntHashMap().get(0), g.maps.gettIntHashMap().get(1)));
    }
}