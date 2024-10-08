package org.example.lexer;

/**
 * Token that represents variable.
 */
public class VariableToken extends Token {

    public VariableToken(String name) {
        super(TokenType.VARIABLE, name);
    }
}
