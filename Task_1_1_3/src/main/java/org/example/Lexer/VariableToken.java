package org.example.Lexer;

public class IdentifierToken extends Token {

    String name;

    public IdentifierToken(String name) {
        this.type = TokenType.VARIABLE;
        this.name = name;
    }

    public IdentifierToken(TokenType type) {
        super(type);
    }
}
