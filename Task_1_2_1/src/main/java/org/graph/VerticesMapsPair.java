package org.graph;

import java.util.HashMap;

public class VerticesMapsPair<T> {

    private HashMap<Integer, T> intTHashMap = new HashMap<>();
    private HashMap<T, Integer> tIntHashMap = new HashMap<>();

    private int N = 0;

    public VerticesMapsPair(HashMap<Integer, T> intTHashMap, HashMap<T, Integer> tIntHashMap) {
        this.intTHashMap = intTHashMap;
        this.tIntHashMap = tIntHashMap;
        this.N = intTHashMap.size();
    }

    public VerticesMapsPair() {
    }

    public void insert(T tv) {
        intTHashMap.put(N, tv);
        tIntHashMap.put(tv, N++);
    }
    public void remove(T v) {
        intTHashMap.remove(gettIntHashMap().get(v), v);
        tIntHashMap.remove(v);
    }

    public HashMap<Integer, T> getIntTHashMap() {
        return intTHashMap;
    }

    public HashMap<T, Integer> gettIntHashMap() {
        return tIntHashMap;
    }

    public int getN() {
        return N;
    }
}
