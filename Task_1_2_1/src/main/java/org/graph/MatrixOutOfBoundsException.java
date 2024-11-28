package org.graph;

/**
 * Thrown if provided matrix index was out of boundaries.
 */
public class MatrixOutOfBoundsException extends RuntimeException {

    public MatrixOutOfBoundsException(String message) {
        super(message);
    }
}
