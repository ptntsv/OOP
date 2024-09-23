package org.example;

/**
 * Exception that raises when DeckIsEmptyException handled.
 */
public class InvalidTurnException extends BaseException {
    public InvalidTurnException(String message) {
        super(message);
    }
}
