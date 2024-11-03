package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Adjacency list graph implementation.
 */
public class AdjListGraph<T> extends AbstractGraph<T> {

    /**
     * Adjacency list.
     */
    protected HashMap<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    @Override
    public T addVertex(T v) {
        int nextMapIndex = getNextMapIndex();
        maps.insert(v, nextMapIndex);
        int vertexKey = maps.gettinthashmap().get(v);
        adjList.put(vertexKey, new ArrayList<>());
        return v;
    }

    @Override
    public T removeVertex(T v) {
        int vertexKey = maps.gettinthashmap().get(v);
        for (var u : adjList.keySet()) {
            adjList.get(u).removeIf(i -> Objects.equals(i, vertexKey));
        }
        adjList.remove(vertexKey);
        maps.remove(v);
        return v;
    }

    @Override
    public void addEdge(T src, T dst) {
        int srcKey = maps.gettinthashmap().get(src);
        int dstKey = maps.gettinthashmap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        adjList.get(srcKey).add(dstKey);
    }

    @Override
    public void removeEdge(T src, T dst) {
        int srcKey = maps.gettinthashmap().get(src);
        int dstKey = maps.gettinthashmap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        adjList.get(srcKey).removeIf(i -> Objects.equals(i, dstKey));
    }

    @Override
    public List<T> getAdjacent(T v) {
        if (!getVertices().contains(v)) {
            return new ArrayList<>();
        }
        int vertexKey = maps.gettinthashmap().get(v);
        return adjList.get(vertexKey).stream().map(i -> maps.getintthashmap().get(i)).toList();
    }

    @Override
    public boolean isAdjacent(T src, T dst) {
        int srcKey = maps.gettinthashmap().get(src);
        int dstKey = maps.gettinthashmap().get(dst);
        if (!adjList.containsKey(srcKey) || !adjList.containsKey(dstKey)) {
            throw new NoSuchVertexException(srcKey, dstKey);
        }
        for (var t : adjList.get(srcKey)) {
            if (Objects.equals(t, dstKey)) {
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
            .map(i -> maps.getintthashmap().get(i))
            .toList();
    }

    /**
     * Constructor.
     *
     * @param edges List of edeges.
     */
    public AdjListGraph(List<Pair<T, T>> edges) {
        for (var e : edges) {
            if (!maps.gettinthashmap().containsKey(e.first)) {
                addVertex(e.first);
            }
            if (!maps.gettinthashmap().containsKey(e.second)) {
                addVertex(e.second);
            }
        }
        for (var e : edges) {
            addEdge(e.first, e.second);
        }
    }
}
