package org.graph;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for AdjListGraph class.
 */
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

        Assertions.assertEquals(0, g.maps.gettinthashmap().get(0));
        Assertions.assertEquals(4, g.maps.gettinthashmap().get(99));
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
        int node = 1;
        g.addVertex(node);

        Assertions.assertEquals(5, g.maps.gettinthashmap().size());
        Assertions.assertEquals(5, g.maps.getintthashmap().size());
        int nodeKey = 1;
        Assertions.assertEquals(nodeKey, g.maps.gettinthashmap().get(node));

        node = 2;
        nodeKey = 2;
        g.addVertex(node);
        Assertions.assertEquals(nodeKey, g.maps.gettinthashmap().get(node));
        Assertions.assertEquals(node, g.maps.getintthashmap().get(nodeKey));
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
        int node = 3;
        int nodeKey = g.maps.gettinthashmap().get(node);
        g.removeVertex(node);
        Assertions.assertFalse(g.adjList.containsKey(nodeKey));
        for (var k : g.adjList.keySet()) {
            Assertions.assertFalse(g.adjList.get(k).contains(nodeKey));
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

        int srcKey = g.maps.gettinthashmap().get(0);
        int dstKey = g.maps.gettinthashmap().get(2);
        Assertions.assertTrue(g.getAdjacent(srcKey).contains(dstKey));
        dstKey = g.maps.gettinthashmap().get(2);
        Assertions.assertTrue(g.getAdjacent(srcKey).contains(dstKey));
        dstKey = g.maps.gettinthashmap().get(3);
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

        int srcKey = g.maps.gettinthashmap().get(0);
        int dstKey = g.maps.gettinthashmap().get(2);
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

    @Test
    void getAdjacentTest() {
        List<Pair<Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, 1));
        edges.add(new Pair<>(0, 2));
        edges.add(new Pair<>(0, 3));
        edges.add(new Pair<>(1, 0));
        edges.add(new Pair<>(1, 2));
        edges.add(new Pair<>(2, 99));
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals(0, g.getAdjacent(88).size());
    }
}
