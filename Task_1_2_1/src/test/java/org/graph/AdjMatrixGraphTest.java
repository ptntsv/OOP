package org.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjMatrixGraphTest {

    @Test
    void adjMatrixCreateTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {6, 5, 4, 3, 2, 1}).toList();
        Graph<Integer> igraph = new Graph<>(ints, GraphRepresentation.ADJ_MATRIX);
        Assertions.assertInstanceOf(AdjMatrixIntGraph.class, igraph.abstractGraph);
        for (int i = 0; i < ints.size(); i++) {
            Assertions.assertEquals(ints.get(i), igraph.maps.getIntTHashMap().get(i));
            Assertions.assertEquals(i, igraph.maps.gettIntHashMap().get(ints.get(i)));
        }
    }
    @Test
    void adjMatrixAddVertexTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_MATRIX);
        Assertions.assertInstanceOf(AdjMatrixIntGraph.class, graph.abstractGraph);
        Assertions.assertEquals(ints.size(), graph.maps.getN());
        graph.addVertex(8);
        Assertions.assertEquals(ints.size() + 1, graph.maps.getN());
        double[][] adjMatrix = ((AdjMatrixIntGraph)graph.abstractGraph).adjMatrix;
        for (int i = 0; i < ints.size() + 1; i++) {
            for (int j = 0; j < ints.size() + 1; j++) {
                Assertions.assertEquals((i == j) ? 0 : Double.POSITIVE_INFINITY, adjMatrix[i][j]);
            }
        }
    }
    @Test
    void adjMatrixRemoveVertexTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_MATRIX);
        var adjGraph = ((AdjMatrixIntGraph)graph.abstractGraph);
        graph.addVertex(8);
        Assertions.assertEquals(ints.size() + 1, graph.maps.getN());

        int toRemoveT = 8;
        int toRemove = graph.maps.gettIntHashMap().get(toRemoveT);
        graph.removeVertex(toRemoveT);
        for (int i = 0; i < adjGraph.n; i++) {
            for (int j = 0; j < adjGraph.n; j++) {
                if (i == toRemove || j == toRemove) {
                    Assertions.assertEquals(Double.NaN, adjGraph.adjMatrix[i][j]);
                }
            }
        }
        toRemoveT = 4;
        toRemove = graph.maps.gettIntHashMap().get(toRemoveT);
        graph.removeVertex(toRemoveT);
        for (int i = 0; i < adjGraph.n; i++) {
            for (int j = 0; j < adjGraph.n; j++) {
                if (i == toRemove || j == toRemove) {
                    Assertions.assertEquals(Double.NaN, adjGraph.adjMatrix[i][j]);
                }
            }
        }
    }
    @Test
    void edgesAdjacentMatrixGraphTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_MATRIX);
        var adjGraph = ((AdjMatrixIntGraph)graph.abstractGraph);
        int v = 2;
        int v_mapped = graph.maps.gettIntHashMap().get(v);
        int u = 5;
        int u_mapped = graph.maps.gettIntHashMap().get(u);
        graph.addEdge(v, u, 8);
        Assertions.assertTrue(graph.isAdjacent(v, u));
        Assertions.assertEquals(8, adjGraph.adjMatrix[v_mapped][u_mapped]);
        graph.removeEdge(v, u);
        Assertions.assertEquals(Double.POSITIVE_INFINITY, adjGraph.adjMatrix[v_mapped][u_mapped]);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 7, 2);
        graph.addEdge(7, 1, 3);
        var vs = Arrays.stream(new Integer[] {1, 2, 3, 7}).toList();
        for (var e : graph.getAdjacent(1)) {
            Assertions.assertTrue(vs.contains(e));
        }
    }
}
