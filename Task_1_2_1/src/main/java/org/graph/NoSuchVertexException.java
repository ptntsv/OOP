package org.graph;

/**
 * Thrown if provided vertex not in graph.
 */
public class NoSuchVertexException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message Message.
     */
    public NoSuchVertexException(String message) {
        super(message);
    }

    public NoSuchVertexException(int v) {
        super("Invalid vertex " + v);
    }

    public NoSuchVertexException(int v, int u) {
        super("Invalid vertices " + v + ", " + u);
    }
}
