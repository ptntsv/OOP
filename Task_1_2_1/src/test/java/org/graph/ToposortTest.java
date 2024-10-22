package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToposortTest {

    @Test
    void topoSortCommonTest1() {
        int n = 5;
        AdjMatrixGraph<Integer> g = new AdjMatrixGraph<>(n);
        g.addVertex(3);
        g.addVertex(2);
        g.addVertex(1);
        g.addVertex(4);

        g.addEdge(3, 2, 0);
        g.addEdge(2, 1, 0);
        g.addEdge(1, 4, 0);
        Toposort.sort(g);
    }
}
