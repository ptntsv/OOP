package org.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjMatrixGraph<T> extends Graph<T> {

    protected HashMap<Vertex<T>, HashMap<Vertex<T>, Boolean>> adjMatrix = new HashMap<>();

    @Override
    public void addVertex(Vertex<T> vertex) {
        for (var v : adjMatrix.values()) {
            v.put(vertex, false);
        }
        adjMatrix.put(vertex, new HashMap<>());
        for (var v : adjMatrix.keySet()) {
            adjMatrix.get(vertex).put(v, false);
        }
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        adjMatrix.remove(vertex);
        for (var v : adjMatrix.keySet()) {
            adjMatrix.get(v).remove(vertex);
        }
    }

    @Override
    public boolean isAdjacent(Vertex<T> from, Vertex<T> to) {
        if (!adjMatrix.containsKey(from)) {
            throw new NoSuchVertexException("Vertex " + from + " not in graph");
        }
        return adjMatrix.get(from).get(to);
    }

    @Override
    public List<Vertex<T>> getAdjacent(Vertex<T> vertex) {
        if (!adjMatrix.containsKey(vertex)) {
            throw new NoSuchVertexException("Vertex " + vertex.toString() + " not in graph");
        }
        ArrayList<Vertex<T>> adj = new ArrayList<>();
        for (var v : adjMatrix.get(vertex).keySet()) {
            if (adjMatrix.get(vertex).get(v)) {
                adj.add(v);
            }
        }
        return adj;
    }

    @Override
    public void addEdge(Edge<T> e) {
        if (!adjMatrix.containsKey(e.from)) {
            throw new NoSuchVertexException("Vertex " + e.from + " not in graph");
        }
        adjMatrix.get(e.from).put(e.to, true);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        if (!adjMatrix.containsKey(edge.from)) {
            throw new NoSuchVertexException("Vertex " + edge.from.toString() + " not in graph");
        }
        adjMatrix.get(edge.from).put(edge.to, false);
    }

    @Override
    public int getVerticesN() {
        return adjMatrix.size();
    }

    // TODO: somehow remove copy-paste
    public static AdjMatrixGraph<Integer> deserialize(String input) {
        var graph = new AdjMatrixGraph<Integer>();
        for (var line : input.split("\n")) {
            var pair = line.split(" ");
            if (pair.length != 2) {
                throw new BadGraphFormatException("Bad format of graph representation.");
            }

            int first = Integer.parseInt(pair[0]);
            int second = Integer.parseInt(pair[1]);

            Vertex<Integer> src = new Vertex<>(first, first);
            Vertex<Integer> dst = new Vertex<>(second, second);

            if (!graph.adjMatrix.containsKey(src)) {
                graph.addVertex(src);
            }
            if (!graph.adjMatrix.containsKey(dst)) {
                graph.addVertex(dst);
            }
            graph.addEdge(new Edge<>(src, dst));
        }
        return graph;
    }

    @Override
    public void print() {
        System.out.print("  ");
        for (var v : adjMatrix.keySet()) {
            System.out.print(v + " ");
        }
        System.out.println();

        for (var v : adjMatrix.keySet()) {
            System.out.print(v + " ");
            for (var u : adjMatrix.get(v).keySet()) {
                System.out.print((adjMatrix.get(v).get(u) ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
}