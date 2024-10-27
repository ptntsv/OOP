package org.graph;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeserializeTest {

    @Test
    void deserializeTest() {
        String str = "0 1 2 3\n1 2 99\n99 72 27";
        var g = AbstractGraph.deserialize(str);
        ArrayList<Pair<Integer, Integer>> toCmp = new ArrayList<>();
        toCmp.add(new Pair<>(0, 1));
        toCmp.add(new Pair<>(0, 2));
        toCmp.add(new Pair<>(0, 3));
        toCmp.add(new Pair<>(1, 2));
        toCmp.add(new Pair<>(1, 99));
        toCmp.add(new Pair<>(99, 72));
        toCmp.add(new Pair<>(99, 27));
        for (int i = 0; i < g.size(); i++) {
            Assertions.assertEquals(g.get(i), toCmp.get(i));
        }
    }
}
