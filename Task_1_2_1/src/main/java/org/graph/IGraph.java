package org.graph;

import java.util.List;

public interface IGraph<T> {

    public T addVertex(T v);

    public T removeVertex(T v);

    public void addEdge(T dst, T src, double weight);

    public void removeEdge(T dst, T src);

    public List<T> getAdjacent(T v);

    public boolean isAdjacent(T v, T u);
}
