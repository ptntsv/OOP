package org.example.lexer;

/**
 * Token that represents operation.
 */
public class OperationToken extends Token {

    public OpType type;
    public int precedence;
    public Associativity associativity;

    /**
     * Constructor.
     *
     * @param type          type.
     * @param content       content.
     * @param precedence    precedence.
     * @param associativity associativity.
     */
    public OperationToken(OpType type, String content, int precedence,
        Associativity associativity) {
        super(TokenType.OP, content);
        this.type = type;
        this.precedence = precedence;
        this.associativity = associativity;
    }
}