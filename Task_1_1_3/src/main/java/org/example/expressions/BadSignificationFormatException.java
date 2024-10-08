package org.example.expressions;

/**
 * Raises if signification of variables has wrong format.
 */
public class BadSignificationFormatException extends RuntimeException {
    public BadSignificationFormatException(String message) {
        super(message);
    }
}
