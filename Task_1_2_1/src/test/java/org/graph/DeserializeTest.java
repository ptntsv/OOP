package org.graph;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for deserializing.
 */
public class DeserializeTest {

    @Test
    void deserializeTest0() {
        ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(1, 99));
        edges.add(new Pair<>(99, 72));
        edges.add(new Pair<>(99, 27));
        var g = new AdjListGraph<>(
            AbstractGraph.deserializeFromFile("src/test/resources/input1.txt"));
        var toCmp = new AdjListGraph<>(edges);
        Assertions.assertEquals(g, toCmp);
    }

    @Test
    void deserializeTest1() {
        ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 3));
        edges.add(new Pair<>(4, 5));
        edges.add(new Pair<>(6, 7));
        edges.add(new Pair<>(8, 9));
        edges.add(new Pair<>(8, 10));
        edges.add(new Pair<>(8, 11));
        edges.add(new Pair<>(11, 1));
        edges.add(new Pair<>(12, 11));
        var g = new AdjListGraph<>(
            AbstractGraph.deserializeFromFile("src/test/resources/input2.txt"));
        var toCmp = new AdjListGraph<>(edges);
        Assertions.assertEquals(g, toCmp);
    }
}
