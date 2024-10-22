package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdjListGraphTest {

    @Test
    void createTest() {
        List<Pair<Integer, Pair<Integer, Double>>> edges = new ArrayList<>();
        edges.add(new Pair<>(0, new Pair<>(1, 1.0)));
        edges.add(new Pair<>(0, new Pair<>(2, 2.0)));
        edges.add(new Pair<>(0, new Pair<>(3, 3.0)));
        edges.add(new Pair<>(1, new Pair<>(0, 10.0)));
        edges.add(new Pair<>(1, new Pair<>(2, 11.0)));
        edges.add(new Pair<>(2, new Pair<>(99, 99.0)));
        AdjListGraph<Integer> g = new AdjListGraph<>(edges);
        Assertions.assertEquals(5, g.getVerticesN());

        Assertions.assertEquals(0, g.maps.gettIntHashMap().get(0));
        Assertions.assertEquals(4, g.maps.gettIntHashMap().get(99));
    }
}
