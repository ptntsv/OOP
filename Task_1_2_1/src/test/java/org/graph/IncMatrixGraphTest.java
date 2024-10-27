package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        int v = 80;
        int expVKey = 5;
        g.addVertex(v);

        Assertions.assertEquals(6, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(6, g.maps.getIntTHashMap().size());
        Assertions.assertEquals(expVKey, g.maps.gettIntHashMap().get(v));

        v = 4;
        expVKey = 6;
        g.addVertex(v);
        Assertions.assertEquals(expVKey, g.maps.gettIntHashMap().get(v));
        Assertions.assertEquals(v, g.maps.getIntTHashMap().get(expVKey));
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
        int v = 2;
        int vKey = g.maps.gettIntHashMap().get(v);
        g.removeVertex(v);

        for (int i = 0; i < g.incMatrix.columns; i++) {
            Assertions.assertEquals(-2, g.incMatrix.get(vKey, i));
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
