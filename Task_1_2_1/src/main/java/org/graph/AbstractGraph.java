package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Abstract class that represents integer graph.
 */
public abstract class AbstractGraph<T> implements Graph<T> {

    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();
    private int nextMapIndex = 0;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var v : getVertices()) {
            sb.append(v).append(" ");
            for (var adj : getAdjacent(v)) {
                sb.append(adj).append(" ");
            }
            System.out.println();
            sb.append("\n");
        }
        return sb.toString();
    }

    protected int getNextMapIndex() {
        return nextMapIndex++;
    }

    public AbstractGraph() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Graph<?> ag) {
            try {
                var another = (Graph<T>) ag;
                for (var v : another.getVertices()) {
                    Set<T> anotherVertices = new HashSet<>(another.getAdjacent(v));
                    Set<T> thisVertices = new HashSet<>(getAdjacent(v));
                    if (anotherVertices.size() != thisVertices.size() || !thisVertices.equals(
                        anotherVertices)) {
                        return false;
                    }
                }
                return true;
            } catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }

    /**
     * Deserialize graph from input string.
     *
     * @param str Source string.
     * @return List of edges to build graph of.
     */
    public static List<Pair<Integer, Integer>> deserialize(String str) {
        ArrayList<Pair<Integer, Integer>> edges = new ArrayList<>();
        String[] lines = str.split("\n");
        for (var line : lines) {
            var es = line.split("\\|")[0];
            ArrayList<Integer> vs = new ArrayList<>(
                Arrays.stream(es.split(" ")).map(Integer::parseInt).toList());
            for (int i = 1; i < vs.size(); i++) {
                edges.add(new Pair<>(vs.get(0), vs.get(i)));
            }
        }
        return edges;
    }
}
