package org.example.Lexer;

public class OperationToken extends Token {

    public OpType type;
    public int precedence;
    public Associativity associativity;

    public OperationToken(OpType type, String content, int p, Associativity as) {
        super(TokenType.OP, content);
        this.type = type;
        precedence = p;
        associativity = as;
    }
}