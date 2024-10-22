package org.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AdjListIntGraph extends AbstractIntGraph {

    /**
     * Adjacency list.
     */
    protected HashMap<Integer, ArrayList<Pair<Integer, Double>>> adjList = new HashMap<>();

    @Override
    public Integer addVertex(Integer v) {
        adjList.put(v, new ArrayList<>(Arrays.asList(new Pair<>(v, 0.0))));
        return 0;
    }

    @Override
    public Integer removeVertex(Integer v) {
        for (var u : adjList.keySet()) {
            adjList.get(u).removeIf(i -> Objects.equals(i.first, v));
        }
        adjList.remove(v);
        return v;
    }

    @Override
    public void addEdge(Integer src, Integer dst, double weight) {
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        adjList.get(src).add(new Pair<>(dst, weight));
    }

    @Override
    public void removeEdge(Integer src, Integer dst) {
        if (!adjList.containsKey(src) || !adjList.containsKey(dst)) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        adjList.get(src).removeIf(i -> Objects.equals(i.first, dst));
    }

    @Override
    public List<Integer> getAdjacent(Integer v) {
        return adjList.get(v).stream().map(i -> i.first).toList();
    }

    @Override
    public boolean isAdjacent(Integer v, Integer u) {
        if (!adjList.containsKey(v) || !adjList.containsKey(u)) {
            throw new NoSuchVertexException("Invalid vertices are provided");
        }
        for (var t : adjList.get(v)) {
            if (Objects.equals(t.first, u)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getVerticesN() {
        return adjList.size();
    }

    public AdjListIntGraph(List<Integer> vs, List<Pair<Integer, Pair<Integer, Double>>> edges) {
        for (var v : vs) {
            adjList.put(v, new ArrayList<>());
        }
        for(var e : edges) {
            adjList.get(e.first).add(e.second);
        }
    }
}
