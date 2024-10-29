package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for topological sorting algorithm.
 */
public class ToposortTest {

    @Test
    void topoSortCommonTest1() {
        int n = 1;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        g.addVertex(1);
        var toCmp = new ArrayList<>(List.of(1));
        List<Integer> sortedVertices = Toposort.toposort(g);
        Assertions.assertEquals(toCmp.size(), sortedVertices.size());
        for (int i = 0; i < toCmp.size(); i++) {
            Assertions.assertEquals(toCmp.get(i), sortedVertices.get(i));
        }
    }

    @Test
    void topoSortCommonTest2() {
        int n = 5;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);

        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(1, 3);
        g.addEdge(3, 5);
        g.addEdge(2, 4);
        g.addEdge(3, 4);

        var toCmp = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> sortedVertices = Toposort.toposort(g);
        Assertions.assertEquals(toCmp.size(), sortedVertices.size());
        for (int i = 0; i < toCmp.size(); i++) {
            Assertions.assertEquals(toCmp.get(i), sortedVertices.get(i));
        }
    }

    @Test
    void topoSortCommonTest3() {
        int n = 5;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        g.addVertex(3);
        g.addVertex(2);
        g.addVertex(1);
        g.addVertex(4);

        g.addEdge(3, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 4);
        var toCmp = new ArrayList<Integer>(Arrays.asList(3, 2, 1, 4));
        List<Integer> sortedVertices = Toposort.toposort(g);
        Assertions.assertEquals(toCmp.size(), sortedVertices.size());
        for (int i = 0; i < toCmp.size(); i++) {
            Assertions.assertEquals(toCmp.get(i), sortedVertices.get(i));
        }
    }

    @Test
    void topoSortCommonTest4() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(3, 8));
        edges.add(new Pair<>(3, 10));
        edges.add(new Pair<>(5, 11));
        edges.add(new Pair<>(7, 11));
        edges.add(new Pair<>(7, 8));
        edges.add(new Pair<>(11, 2));
        edges.add(new Pair<>(11, 9));
        edges.add(new Pair<>(11, 10));

        edges.add(new Pair<>(8, 9));

        AdjListGraph<Integer> g = new AdjListGraph<>(edges);

        var toCmp = new ArrayList<Integer>(Arrays.asList(7, 5, 11, 2, 3, 8, 9, 10));
        List<Integer> sortedVertices = Toposort.toposort(g);
        Assertions.assertEquals(toCmp.size(), sortedVertices.size());
        for (int i = 0; i < toCmp.size(); i++) {
            Assertions.assertEquals(toCmp.get(i), sortedVertices.get(i));
        }
    }
}
