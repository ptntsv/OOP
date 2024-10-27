package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class that represents integer graph.
 */
public abstract class AbstractGraph<T> implements IGraph<T> {

    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();

    @Override
    public void display() {
        for (var v : getVertices()) {
            System.out.print(v + " ");
            for (var adj : getAdjacent(v)) {
                System.out.print(adj + " ");
            }
            System.out.println();
        }
    }

    public AbstractGraph() {
    }

    @Override
    public boolean equals(Object obj) {
        AbstractGraph another = (AbstractGraph) obj;
        Matrix m1 = new Matrix(getVerticesN(), getVerticesN(), 0);
        Matrix m2 = new Matrix(another.getVerticesN(), another.getVerticesN(), 0);
        if (another.getVerticesN() != getVerticesN()) {
            return false;
        }
        for (var v : another.getVertices()) {
            int vKey = (int) another.maps.gettIntHashMap().get(v);
            for (var u : another.getAdjacent(v)) {
                int uKey = (int) another.maps.gettIntHashMap().get(u);
                m2.set(vKey, uKey, 1);
            }
        }
        for (var v : getVertices()) {
            int vKey = maps.gettIntHashMap().get(v);
            for (var u : getAdjacent(v)) {
                int uKey = maps.gettIntHashMap().get(u);
                m1.set(vKey, uKey, 1);
            }
        }
        for (int i = 0; i < getVerticesN(); i++) {
            for (int j = 0; j < getVerticesN(); j++) {
                if (m1.get(i, j) != m2.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Deserialize graph from input string.
     *
     * @param str Source string.
     * @param <T> Specified vertex type.
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
