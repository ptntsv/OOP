package org.example;

/**
 * Thrown when bad heading level is provided.
 */
public class BadHeadingLevelException extends Throwable {

    private final String message;

    public String getMsg() {
        return message;
    }

    public BadHeadingLevelException(int actual) {
        message = "Expected heading level range: 1 - 6, got " + actual;
    }
}
