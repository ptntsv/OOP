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
}
