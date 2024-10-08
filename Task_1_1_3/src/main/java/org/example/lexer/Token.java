package org.example.lexer;

public class Token {

    public TokenType type;
    public String content;

    public Token() {
    }

    public Token(TokenType type, String content) {
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return content;
    }
}
