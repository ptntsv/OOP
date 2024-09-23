package org.example;

/**
 * Exception that raises when Deck class method peekCard() called on empty deck.
 */
public class DeckIsEmptyException extends BaseException {

    public DeckIsEmptyException(String message) {
        super(message);
    }
}
