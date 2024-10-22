package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Adjacency list graph implementation.
 */
public class AdjListGraph<T> extends AbstractGraph<T> {

    /**
     * Adjacency list.
     */
    protected HashMap<Integer, ArrayList<Pair<Integer, Double>>> adjList = new HashMap<>();

    @Override
    public T addVertex(T v) {
        maps.insert(v);
        int vKey = maps.gettIntHashMap().get(v);
        adjList.put(vKey, new ArrayList<>());
        return v;
    }

    @Override
    public T removeVertex(T v) {
        maps.remove(v);
        int vKey = maps.gettIntHashMap().get(v);
        for (var u : adjList.keySet()) {
            adjList.get(u).removeIf(i -> Objects.equals(i.first, vKey));
        }
        adjList.remove(vKey);
        return v;
    }

    @Override
    public void addEdge(T src, T dst, double weight) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        adjList.get(srcKey).add(new Pair<>(dstKey, weight));
    }

    @Override
    public void removeEdge(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        adjList.get(srcKey).removeIf(i -> Objects.equals(i.first, dstKey));
    }

    @Override
    public List<T> getAdjacent(T v) {
        int vKey = maps.gettIntHashMap().get(v);
        return adjList.get(vKey).stream().map(i -> maps.getIntTHashMap().get(i.first)).toList();
    }

    @Override
    public boolean isAdjacent(T src, T dst) {
        int srcKey = maps.gettIntHashMap().get(src);
        int dstKey = maps.gettIntHashMap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        for (var t : adjList.get(srcKey)) {
            if (Objects.equals(t.first, dstKey)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVerticesN() {
        return adjList.size();
    }

    @Override
    public List<T> getVertices() {
        return adjList.keySet().stream()
            .map(i -> maps.getIntTHashMap().get(i))
            .toList();
    }

    @Override
    public void reset() {
        for (var v : adjList.keySet()) {
            adjList.get(v).clear();
        }
    }

    /**
     * Constructor.
     *
     * @param edges List of edeges.
     */
    public AdjListGraph(List<Pair<T, Pair<T, Double>>> edges) {
        for (var e : edges) {
            if (!maps.gettIntHashMap().containsKey(e.first)) {
                addVertex(e.first);
            }
            if (!maps.gettIntHashMap().containsKey(e.second.first)) {
                addVertex(e.second.first);
            }
        }
        for (var e : edges) {
            addEdge(e.first, e.second.first, e.second.second);
        }
    }
}
