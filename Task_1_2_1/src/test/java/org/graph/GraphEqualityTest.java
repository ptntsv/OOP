package org.graph;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for graphs equality checks.
 */
public class GraphEqualityTest {

    @Test
    void eqTest1() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        AdjListGraph<Integer> adjListGraph = new AdjListGraph<>(edges);
        AdjMatrixGraph<Integer> adjMatrixGraph = new AdjMatrixGraph<>(4, edges);
        Assertions.assertEquals(adjListGraph, adjMatrixGraph);
    }

    @Test
    void eqTest2() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> incMatrixGraph = new IncMatrixGraph<>(5, 5, edges);
        AdjMatrixGraph<Integer> adjMatrixGraph = new AdjMatrixGraph<>(5, edges);
        Assertions.assertEquals(incMatrixGraph, adjMatrixGraph);
    }

    @Test
    void eqTest3() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        IncMatrixGraph<Integer> incMatrixGraph = new IncMatrixGraph<>(5, 5, edges);
        AdjListGraph<Integer> adjListGraph = new AdjListGraph<>(edges);
        Assertions.assertEquals(incMatrixGraph, adjListGraph);
    }

    @Test
    void unequalityTest0() {
        List<Pair<Integer, Integer>> edges1 = new ArrayList<>();
        edges1.add(new Pair<>(0, 1));
        edges1.add(new Pair<>(0, 2));
        edges1.add(new Pair<>(0, 3));
        edges1.add(new Pair<>(5, 9));

        List<Pair<Integer, Integer>> edges2 = new ArrayList<>();
        edges2.add(new Pair<>(0, 1));
        edges2.add(new Pair<>(0, 2));
        edges2.add(new Pair<>(0, 3));
        edges2.add(new Pair<>(9, 5));
        IncMatrixGraph<Integer> incMatrixGraph = new IncMatrixGraph<>(10, 10, edges1);
        AdjListGraph<Integer> adjListGraph = new AdjListGraph<>(edges2);
        Assertions.assertNotEquals(incMatrixGraph, adjListGraph);
    }

    @Test
    void unequalityTest1() {
        List<Pair<Integer, Integer>> edges1 = new ArrayList<>();
        edges1.add(new Pair<>(5, 5));
        edges1.add(new Pair<>(0, 1));
        edges1.add(new Pair<>(0, 2));
        edges1.add(new Pair<>(0, 3));

        List<Pair<Integer, Integer>> edges2 = new ArrayList<>();
        edges2.add(new Pair<>(0, 1));
        edges2.add(new Pair<>(0, 2));
        edges2.add(new Pair<>(0, 3));
        edges2.add(new Pair<>(9, 5));
        IncMatrixGraph<Integer> incMatrixGraph = new IncMatrixGraph<>(10, 10, edges1);
        AdjListGraph<Integer> adjListGraph = new AdjListGraph<>(edges2);
        Assertions.assertNotEquals(incMatrixGraph, adjListGraph);
    }

    @Test
    void unequalityTest2() {
        List<Pair<Integer, Integer>> edges1 = new ArrayList<>();
        edges1.add(new Pair<>(0, 1));
        edges1.add(new Pair<>(0, 2));
        edges1.add(new Pair<>(0, 3));
        edges1.add(new Pair<>(5, 5));

        List<Pair<Integer, Integer>> edges2 = new ArrayList<>();
        edges2.add(new Pair<>(0, 1));
        edges2.add(new Pair<>(0, 2));
        edges2.add(new Pair<>(0, 3));
        edges2.add(new Pair<>(10, 5));
        IncMatrixGraph<Integer> incMatrixGraph = new IncMatrixGraph<>(10, 10, edges1);
        AdjListGraph<Integer> adjListGraph = new AdjListGraph<>(edges2);
        Assertions.assertNotEquals(incMatrixGraph, adjListGraph);
    }
}
