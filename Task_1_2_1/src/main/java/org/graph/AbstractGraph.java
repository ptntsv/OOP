package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class that represents integer graph.
 */
public abstract class AbstractGraph<T> implements IGraph<T> {

    protected VerticesMapsPair<T> maps = new VerticesMapsPair<>();
    private int nextMapIndex = 0;

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

    protected int getNextMapIndex() {
        return nextMapIndex++;
    }

    public AbstractGraph() {
    }

    /**
     * Checking for equality is avaliable only between AbstractGraph realizations
     * because all of them should have key mappings, but IGraph could not.
     *
     * @param obj Graph to check
     * @return Equals or not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IGraph<?> ag) {
            try {
                var another = (AbstractGraph<T>) ag;
                for (var v : another.getVertices()) {
                    if (!maps.getIntTHashMap().containsKey(maps.gettIntHashMap().get(v))) {
                        return false;
                    }
                    List<Integer> a_vs = another.getAdjacent(v).stream().map(t -> another.maps.gettIntHashMap().get(t)).toList();
                    List<Integer> this_vs = getAdjacent(maps.getIntTHashMap().get(another.maps.gettIntHashMap().get(v)))
                            .stream()
                            .map(t -> maps.gettIntHashMap().get(t)).toList();
                    if (a_vs.size() != this_vs.size()) {
                        return false;
                    }
                    for (int i = 0; i < a_vs.size(); i++) {
                        if (!Objects.equals(a_vs.get(i), this_vs.get(i))) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (ClassCastException ex) {
                return false;
            }
        }
        return false;
    }
//        AbstractGraph another = (AbstractGraph) obj;
//        Matrix m1 = new Matrix(getVerticesN(), getVerticesN(), 0);
//        Matrix m2 = new Matrix(another.getVerticesN(), another.getVerticesN(), 0);
//        if (another.getVerticesN() != getVerticesN()) {
//            return false;
//        }
//        for (var v : another.getVertices()) {
//            int vKey = (int) another.maps.gettIntHashMap().get(v);
//            for (var u : another.getAdjacent(v)) {
//                int uKey = (int) another.maps.gettIntHashMap().get(u);
//                m2.set(vKey, uKey, 1);
//            }
//        }
//        for (var v : getVertices()) {
//            int vKey = maps.gettIntHashMap().get(v);
//            for (var u : getAdjacent(v)) {
//                int uKey = maps.gettIntHashMap().get(u);
//                m1.set(vKey, uKey, 1);
//            }
//        }
//        for (int i = 0; i < getVerticesN(); i++) {
//            for (int j = 0; j < getVerticesN(); j++) {
//                if (m1.get(i, j) != m2.get(i, j)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

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
            ArrayList<Integer> vs = new ArrayList<>(Arrays.stream(es.split(" ")).map(Integer::parseInt).toList());
            for (int i = 1; i < vs.size(); i++) {
                edges.add(new Pair<>(vs.get(0), vs.get(i)));
            }
        }
        return edges;
    }
}
