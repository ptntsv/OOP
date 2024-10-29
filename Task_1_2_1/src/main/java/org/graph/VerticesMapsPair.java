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
    private HashMap<Integer, T> intThashMap = new HashMap<>();
    /**
     * From T to int map.
     */
    private HashMap<T, Integer> tIntHashMap = new HashMap<>();

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
        if (!tIntHashMap.containsKey(tv)) {
            intThashMap.put(index, tv);
            tIntHashMap.put(tv, index);
        }
    }

    /**
     * Remove a vertex for mapping.
     *
     * @param v Vertex to remove.
     */
    public void remove(T v) {
        intThashMap.remove(gettinthashmap().get(v), v);
        tIntHashMap.remove(v);
    }

    /**
     * Getter for intTHashMap.
     *
     * @return intTHashMap
     */
    public HashMap<Integer, T> getintthashmap() {
        return intThashMap;
    }

    /**
     * Getter for tIntHashMap.
     *
     * @return tIntHashMap
     */
    public HashMap<T, Integer> gettinthashmap() {
        return tIntHashMap;
    }
}
