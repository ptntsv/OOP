package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IncMatrixGraphTest {

    @Test
    void createGraphTest() {
        List<Integer> ints = Arrays.stream(new Integer[]{6, 5, 4, 3, 2, 1}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.INC_MATRIX);
        Assertions.assertInstanceOf(IncMatrixIntGraph.class, graph.abstractGraph);
    }
    @Test
    void addVerticesTest() {
        List<Integer> ints = Arrays.stream(new Integer[]{6, 5, 4, 3, 2, 1}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.INC_MATRIX);
        var incGraph = ((IncMatrixIntGraph)graph.abstractGraph);
        Assertions.assertEquals(ints.size(), incGraph.nvertices);
        graph.addVertex(10);
        graph.addVertex(13);
        graph.addVertex(1);
        Assertions.assertEquals(ints.size() + 3, incGraph.nvertices);
    }
    @Test
    void addEdgesTest() {
        List<Integer> ints = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.INC_MATRIX);
        var incGraph = ((IncMatrixIntGraph)graph.abstractGraph);
        Assertions.assertEquals(ints.size(), incGraph.nvertices);
        graph.addEdge(1, 2, 0.0);
        graph.addEdge(1, 3, 0.0);
        graph.addEdge(1, 4, 0.0);
        graph.addEdge(2, 6, 0.0);
        graph.addEdge(6, 2, 0.0);
        graph.addEdge(6, 1, 0.0);

        Assertions.assertEquals(1, incGraph.incMatrix[0][0]);
        Assertions.assertEquals(-1, incGraph.incMatrix[1][0]);

        Assertions.assertEquals(1, incGraph.incMatrix[0][1]);
        Assertions.assertEquals(-1, incGraph.incMatrix[2][1]);

        Assertions.assertEquals(1, incGraph.incMatrix[0][2]);
        Assertions.assertEquals(-1, incGraph.incMatrix[3][2]);

        Assertions.assertEquals(-1, incGraph.incMatrix[0][5]);
        Assertions.assertEquals(1, incGraph.incMatrix[5][4]);
    }
    @Test
    void removeEdgesTest() {
        List<Integer> ints = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.INC_MATRIX);
        var incGraph = ((IncMatrixIntGraph)graph.abstractGraph);
        Assertions.assertEquals(ints.size(), incGraph.nvertices);
        graph.addEdge(1, 2, 0.0);
        graph.addEdge(1, 3, 0.0);
        graph.addEdge(1, 4, 0.0);
        graph.addEdge(2, 6, 0.0);
        graph.addEdge(6, 2, 0.0);
        graph.addEdge(6, 1, 0.0);

        graph.removeEdge(1, 3);
        graph.removeEdge(6, 2);

        Assertions.assertEquals(0, incGraph.incMatrix[0][1]);
        Assertions.assertEquals(0, incGraph.incMatrix[2][1]);

        Assertions.assertEquals(0, incGraph.incMatrix[1][5]);
        Assertions.assertEquals(0, incGraph.incMatrix[4][5]);
    }
    @Test
    void getAdjTest() {
        List<Integer> ints = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6}).toList();
        Graph<Integer> graph = new Graph<>(ints, GraphRepresentation.INC_MATRIX);
        var incGraph = ((IncMatrixIntGraph)graph.abstractGraph);
        Assertions.assertEquals(ints.size(), incGraph.nvertices);
        graph.addEdge(1, 2, 0.0);
        graph.addEdge(1, 3, 0.0);
        graph.addEdge(1, 4, 0.0);
        graph.addEdge(2, 6, 0.0);
        graph.addEdge(6, 2, 0.0);
        graph.addEdge(6, 1, 0.0);

        var vs = graph.getAdjacent(1);
        List<Integer> toCmp = new ArrayList<>(Arrays.asList(2, 3, 4));
        for (var v : vs) {
            Assertions.assertTrue(toCmp.contains(v));
        }
    }
}
