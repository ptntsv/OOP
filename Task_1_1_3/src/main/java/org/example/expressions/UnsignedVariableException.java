package org.example.expressions;

/**
 * Raises in case if expression has unsigned variable.
 */
public class UnsignedVariableException extends Exception {

    public UnsignedVariableException(String message) {
        super(message);
    }
}
