package org.graph;

import org.junit.jupiter.api.Test;

public class AdjListGraphTest {
//
//    @Test
//    void deserializeGraphTest() {
//        String src = "1 2\n2 4\n5 10\n6 22\n1 1\n5 12";
//        AdjListGraph<Integer> graph = AdjListGraph.deserialize(src);
//    }
//
    @Test
    void removeVertexGraphTest() {
        AdjListGraph<Integer> graph = new AdjListGraph<>();
        var v1 = new Vertex<Integer>(1, 1);
        var v2 = new Vertex<Integer>(2, 2);
        var v3 = new Vertex<Integer>(3, 3);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addEdge(new Edge<>(v1, v2));
        graph.addEdge(new Edge<>(v2, v3));
        graph.addEdge(new Edge<>(v3, v1));
        graph.print();
    }
}
