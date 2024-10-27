package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjListGraphTest {

    @Test
    void createTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals(5, g.getVerticesN());

        Assertions.assertEquals(0, g.maps.gettIntHashMap().get(0));
        Assertions.assertEquals(4, g.maps.gettIntHashMap().get(99));
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
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);
        int v = 1;
        int expVKey = 1;
        g.addVertex(v);

        Assertions.assertEquals(5, g.maps.gettIntHashMap().size());
        Assertions.assertEquals(5, g.maps.getIntTHashMap().size());
        Assertions.assertEquals(expVKey, g.maps.gettIntHashMap().get(v));

        v = 2;
        expVKey = 2;
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
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);
        int v = 3;
        int vKey = g.maps.gettIntHashMap().get(v);
        g.removeVertex(v);
        Assertions.assertFalse(g.adjList.containsKey(vKey));
        for (var k : g.adjList.keySet()) {
            Assertions.assertFalse(g.adjList.get(k).contains(vKey));
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
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);

        int srcKey = g.maps.gettIntHashMap().get(0);
        int dstKey = g.maps.gettIntHashMap().get(2);
        Assertions.assertTrue(g.getAdjacent(srcKey).contains(dstKey));
        dstKey = g.maps.gettIntHashMap().get(2);
        Assertions.assertTrue(g.getAdjacent(srcKey).contains(dstKey));
        dstKey = g.maps.gettIntHashMap().get(3);
        Assertions.assertTrue(g.getAdjacent(srcKey).contains(dstKey));
    }

    @Test
    void removeEdgesTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);

        int srcKey = g.maps.gettIntHashMap().get(0);
        int dstKey = g.maps.gettIntHashMap().get(2);
        g.removeEdge(srcKey, dstKey);
        Assertions.assertFalse(g.getAdjacent(srcKey).contains(dstKey));
        Assertions.assertTrue(g.adjList.containsKey(dstKey));
    }

    @Test
    void isAdjacentTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);

        Assertions.assertTrue(g.isAdjacent(0, 1));
        Assertions.assertTrue(g.isAdjacent(0, 2));
        Assertions.assertTrue(g.isAdjacent(0, 3));
        Assertions.assertTrue(g.isAdjacent(1, 0));
        Assertions.assertTrue(g.isAdjacent(1, 2));
    }
}
