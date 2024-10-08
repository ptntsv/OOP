package org.example.lexer;

/**
 * Superclass that represents lexical token.
 */
public class Token {

    public TokenType type;
    public String content;

    public Token(TokenType type, String content) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return content;
    }
}
