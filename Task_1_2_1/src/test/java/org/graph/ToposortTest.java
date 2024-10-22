package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToposortTest {

    @Test
    void toposortCommonTest1() {
        Graph<Integer> graph = Graph.deserializeIntGraph("1 2 3|0 0\n2 3 4|0 0\n3 4 5|0 0",
            GraphRepresentation.ADJ_LIST);
        var sorted = graph.toposort();
        ArrayList<Integer> toCmp = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 4));
        for (int i = 0; i < sorted.size(); i++) {
            Assertions.assertEquals(sorted.get(i), toCmp.get(i));
        }
    }
    @Test
    void toposortCommonTest2() {
        Graph<Integer> graph = Graph.deserializeIntGraph("3 2|0\n2 1|0\n1 4|0",
            GraphRepresentation.ADJ_LIST);
        var sorted = graph.toposort();
        ArrayList<Integer> toCmp = new ArrayList<>(Arrays.asList(3, 2, 1, 4));
        for (int i = 0; i < sorted.size(); i++) {
            Assertions.assertEquals(sorted.get(i), toCmp.get(i));
        }
    }
    @Test
    void toposortCommonTest3() {
        Graph<Integer> graph = Graph.deserializeIntGraph("3 2|0",
            GraphRepresentation.ADJ_LIST);
        var sorted = graph.toposort();
        ArrayList<Integer> toCmp = new ArrayList<>(Arrays.asList(3, 2));
        for (int i = 0; i < sorted.size(); i++) {
            Assertions.assertEquals(sorted.get(i), toCmp.get(i));
        }
    }
}
