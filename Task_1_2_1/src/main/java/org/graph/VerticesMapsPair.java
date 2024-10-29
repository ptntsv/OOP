package org.graph;

import java.util.HashMap;

/**
 * Utility class to keep two-way mapping between Graph wrapper.
 *
 * @param <T> User's specified graph node type.
 */
public class VerticesMapsPair<T> {

    /**
     * From int to T map.
     */
    private HashMap<Integer, T> intthashmap = new HashMap<>();
    /**
     * From T to int map.
     */
    private HashMap<T, Integer> tinthashmap = new HashMap<>();

    /**
     * Contructor.
     */
    public VerticesMapsPair() {
    }

    /**
     * Insert new vertex for mapping.
     *
     * @param tv Vertex to insert.
     */
    public void insert(T tv, int index) {
        if (!tinthashmap.containsKey(tv)) {
            intthashmap.put(index, tv);
            tinthashmap.put(tv, index);
        }
    }

    /**
     * Remove a vertex for mapping.
     *
     * @param v Vertex to remove.
     */
    public void remove(T v) {
        intthashmap.remove(gettinthashmap().get(v), v);
        tinthashmap.remove(v);
    }

    /**
     * Getter for intTHashMap.
     *
     * @return intTHashMap
     */
    public HashMap<Integer, T> getintthashmap() {
        return intthashmap;
    }

    /**
     * Getter for tIntHashMap.
     *
     * @return tIntHashMap
     */
    public HashMap<T, Integer> gettinthashmap() {
        return tinthashmap;
    }
}
