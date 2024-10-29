package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for IncMatrixGraph class.
 */
public class IncMatrixGraphTest {

    @Test
    void createTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> g = new IncMatrixGraph<>(5, 6, edges);
        Assertions.assertEquals(5, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(5, g.maps.getIntTHashMap().size());
    }

    @Test
    void addVerticesTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> g = new IncMatrixGraph<>(5, 6, edges);
        int node = 80;
        g.addVertex(node);

        Assertions.assertEquals(6, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(6, g.maps.getIntTHashMap().size());
        int expectedKey = 5;
        Assertions.assertEquals(expectedKey, g.maps.gettIntHashMap().get(node));

        node = 4;
        g.addVertex(node);
        expectedKey = 6;
        Assertions.assertEquals(expectedKey, g.maps.gettIntHashMap().get(node));
        Assertions.assertEquals(node, g.maps.getIntTHashMap().get(expectedKey));
    }

    @Test
    void removeVerticesTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> g = new IncMatrixGraph<>(5, 6, edges);
        int node = 2;
        int nodeKey = g.maps.gettIntHashMap().get(node);
        g.removeVertex(node);

        for (int i = 0; i < g.incMatrix.columns; i++) {
            Assertions.assertEquals(-2, g.incMatrix.get(nodeKey, i));
        }
    }

    @Test
    void addEdgesTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> g = new IncMatrixGraph<>(5, 6, edges);
        g.addEdge(0, 99);
        Assertions.assertTrue(g.isAdjacent(0, 99));
    }

    @Test
    void removeEdgeTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> g = new IncMatrixGraph<>(5, 6, edges);
        g.removeEdge(0, 3);
        Assertions.assertFalse(g.isAdjacent(0, 3));
    }
}
