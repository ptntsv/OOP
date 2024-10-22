package org.graph;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjListGraphTest {
    @Test
    void graphCreateTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_LIST);
        Assertions.assertEquals(ints.size(), graph.getVerticesN());
    }
    @Test
    void verticesAddRemoveTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_LIST);
        Assertions.assertEquals(ints.size(), graph.getVerticesN());
        graph.addVertex(33);
        Assertions.assertEquals(ints.size() + 1, graph.getVerticesN());
        graph.removeVertex(2);
        Assertions.assertEquals(ints.size(), graph.getVerticesN());
    }
    @Test
    void edgesAddRemoveTest() {
        List<Integer> ints = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.ADJ_LIST);
        AdjListIntGraph adjListGraph = ((AdjListIntGraph) graph.abstractGraph);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 3);
        Assertions.assertEquals(4, adjListGraph.adjList.get(graph.maps.gettIntHashMap().get(1)).size());
        graph.removeVertex(3);
        Assertions.assertEquals(3, adjListGraph.adjList.get(graph.maps.gettIntHashMap().get(1)).size());
        graph.removeEdge(1, 2);
        Assertions.assertEquals(2, adjListGraph.adjList.get(graph.maps.gettIntHashMap().get(1)).size());
    }
}
