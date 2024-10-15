package org.graph;

public class Vertex<T> {

    private int key;
    T value;

    public T getValue() {
        return this.value;
    }
    public int getKey() {
        return this.key;
    }

    public Vertex(T value, int key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public int hashCode() {
        return key;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex<?> other) {
            return this.key == other.key;
        }
        return false;
    }
}
