package org.example.lexer;

/**
 * Token that represents a number.
 */
public class NumberToken extends Token {

    public int value;

    public NumberToken(int value) {
        super(TokenType.NUMBER, Integer.toString(value));
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
