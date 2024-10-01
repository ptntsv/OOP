package org.example.Lexer;

public class VariableToken extends Token {

    public VariableToken(String name) {
        super(TokenType.VARIABLE, name);
    }
}
