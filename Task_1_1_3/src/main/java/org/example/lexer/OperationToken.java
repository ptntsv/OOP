package org.example.lexer;

public class OperationToken extends Token {

    public OpType type;
    public int precedence;
    public Associativity associativity;

    public OperationToken(OpType type, String content, int precedence,
        Associativity associativity) {
        super(TokenType.OP, content);
        this.type = type;
        this.precedence = precedence;
        this.associativity = associativity;
    }
}