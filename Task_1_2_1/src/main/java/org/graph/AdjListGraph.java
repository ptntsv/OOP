package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjListGraph<T> implements IGraph<T> {

    protected HashMap<Vertex<T>, ArrayList<Vertex<T>>> adjList = new HashMap<>();

    @Override
    public void addVertex(Vertex<T> vertex) {
        adjList.put(vertex, new ArrayList<>());
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        adjList.remove(vertex);
        for (var v : adjList.keySet()) {
            adjList.get(v).remove(vertex);
        }
    }

    @Override
    public boolean isAdjacent(Vertex<T> from, Vertex<T> to) {
        if (!adjList.containsKey(from)) {
            throw new NoSuchVertexException("Vertex " + from + " not in graph");
        }
        return adjList.get(from).contains(to);
    }

    @Override
    public List<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        if (!adjList.containsKey(vertex)) {
            throw new NoSuchVertexException("Vertex " + vertex + " not in graph");
        }
        return new ArrayList<>(adjList.get(vertex));
    }

    @Override
    public void addEdge(Edge<T> edge) {
        if (!adjList.containsKey(edge.from)) {
            throw new NoSuchVertexException("Vertex " + edge.from + " not in graph");
        }
        if (!adjList.containsKey(edge.to)) {
            throw new NoSuchVertexException("Vertex " + edge.to + " not in graph");
        }
        adjList.get(edge.from).add(edge.to);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (!adjList.containsKey(edge.from)) {
            throw new NoSuchVertexException("Vertex " + edge.from + " not in graph");
        }
        adjList.get(edge.from).remove(edge.to);
    }

    @Override
    public int getVerticesN() {
        return adjList.size();
    }

    @Override
    public void print() {
        for (var v : adjList.keySet()) {
            System.out.print(v + ": ");
            for (var u : adjList.get(v)) {
                System.out.print(u + " ");
            }
            System.out.println();
        }
    }
}
