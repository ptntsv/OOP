package org.example.lexer;

/**
 * Raises if unsupported operation met.
 */
public class UnsupportedOperationException extends RuntimeException {

    public UnsupportedOperationException(String message) {
        super(message);
    }
}
