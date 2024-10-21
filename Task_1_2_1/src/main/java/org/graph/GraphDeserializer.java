package org.graph;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Static class with methods that make map from T type to int.
 * Format: v1(src) v2(dst) v3(dst) ... vn(dst) (lines are separated by \n)
 * Example:
 *      1 2 3
 *      2 5 6
 * Means that there are 2 edges 1 -> 2 and 2 -> 3 on the first line
 * 2 -> 5 and 2 -> 6 on the second.
 */
public class GraphDeserializer {
    public static HashMap<Integer, Integer> deserializeIntGraph(String str) {
        HashMap<Integer, Integer> map = new HashMap<>();
        String[] lines = str.split("\n");
        for (var line : lines) {
            Integer[] vs = (Integer[]) Arrays.stream(line.split(" ")).map(Integer::getInteger).toArray();
            for (int i = 1; i < vs.length; i++) {
            }
        }
        return map;
    }
}
