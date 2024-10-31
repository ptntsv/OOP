package org.graph;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for Graph's toString method.
 */
public class GraphToStringTest {

    @Test
    void commonTest1() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(1, 0));
        Graph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals("0 1 2\n1 0\n2\n", g.toString());
    }

    @Test
    void commonTest2() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 0));
        Graph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals("0 0\n", g.toString());
    }

    @Test
    void commonTest3() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        Graph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals("0 1 2 3\n1 0 2\n2 99\n3\n99\n", g.toString());
    }
}
