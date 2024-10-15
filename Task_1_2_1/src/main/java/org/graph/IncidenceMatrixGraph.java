package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncidenceMatrixGraph<T> implements IGraph<T> {

    protected HashMap<Vertex<T>, HashMap<Edge<T>, Integer>> incMatrix = new HashMap<>();
    private ArrayList<Edge<T>> edges = new ArrayList<>();

    @Override
    public void addVertex(Vertex<T> vertex) {
        incMatrix.put(vertex, new HashMap<>());
        for (var e : edges) {
            incMatrix.get(vertex).put(e, 0);
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        for (var e : incMatrix.get(vertex).keySet()) {

        }
        incMatrix.remove(vertex);
    }

    @Override
    public boolean isAdjacent(Vertex<T> from, Vertex<T> to) {
        for (var v : incMatrix.get(from).keySet()) {
            if (incMatrix.get(from).get(v) == 1 && incMatrix.get(to).get(v) == -1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        ArrayList<Vertex<T>> adjList = new ArrayList<>();
        for (var e : incMatrix.get(vertex).keySet()) {
            if (incMatrix.get(vertex).get(e) == 1) {
                adjList.add(e.to);
            }
        }
        return adjList;
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!incMatrix.containsKey(edge.from)) {
            throw new NoSuchVertexException("Vertex " + edge.from + " not in graph");
        }
        if (!incMatrix.containsKey(edge.to)) {
            throw new NoSuchVertexException("Vertex " + edge.to + " not in graph");
        }
        edges.add(edge);
        for (var v : incMatrix.keySet()) {
            incMatrix.get(v).put(edge, 0);
        }
        incMatrix.get(edge.from).put(edge, 1);
        incMatrix.get(edge.to).put(edge, -1);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
    }

    @Override
    public int getVerticesN() {
        return incMatrix.size();
    }

    @Override
    public void print() {
    }
}
