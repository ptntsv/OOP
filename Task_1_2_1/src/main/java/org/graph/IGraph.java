package org.graph;

import java.util.List;

public interface IGraph<T> {

    void addVertex(Vertex<T> vertex);

    void removeVertex(Vertex<T> vertex);

    boolean isAdjacent(Vertex<T> from, Vertex<T> to);

    List<Vertex<T>> getAdjacent(Vertex<T> vertex);

    void addEdge(Edge<T> edge);

    void removeEdge(Edge<T> edge);

    int getVerticesN();

    void print();
}
