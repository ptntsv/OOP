package org.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}
