package org.graph;

public class Edge<T> {

    Vertex<T> from;
    Vertex<T> to;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge<?> e) {
            return e.from == this.from && e.to == this.to;
        }
        return false;
    }
    public Edge(Vertex<T> from, Vertex<T> to) {
        this.from = from;
        this.to = to;
    }
}
