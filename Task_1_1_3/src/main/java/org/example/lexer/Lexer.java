package org.example.lexer;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static org.example.lexer.Associativity.ASSOC_LEFT;

import java.util.ArrayList;

/**
 * Class that represents a lexer.
 */
public class Lexer {

    /**
     * Source string.
     */
    private String src;
    private int cursor = 0;

    /**
     * Is it end of source string.
     *
     * @return Either an end or not.
     */
    public boolean isEnd() {
        return cursor == src.length();
    }

    /**
     * Advance cursor (with post increment).
     *
     * @return Advanced char.
     */
    public char advance() {
        if (isEnd()) {
            return 0;
        }
        return src.charAt(cursor++);
    }

    /**
     * Peek current char.
     *
     * @return Character under the cursor.
     */
    public char peek() {
        if (isEnd()) {
            return 0;
        }
        return src.charAt(cursor);
    }

    /**
     * Reads number.
     *
     * @return Number value.
     */
    public int getNumber() {
        int start = cursor - 1;
        while (isDigit(peek())) {
            advance();
        }
        return Integer.parseInt(src.substring(start, cursor));
    }

    /**
     * Reads identifier (in case of this task - variable's name).
     *
     * @return Identifier name.
     */
    public String getIdentifier() {
        int start = cursor - 1;
        while (isAlphabetic(peek()) || isDigit(peek())) {
            advance();
        }
        return src.substring(start, cursor);
    }

    /**
     * Peeks next token.
     *
     * @return Next token.
     */
    public Token nextToken() {
        char ch = advance();
        switch (ch) {
            case '(':
                return new Token(TokenType.LPAREN, Character.toString(ch));
            case ')':
                return new Token(TokenType.RPAREN, Character.toString(ch));
            case '+':
                return new OperationToken(OpType.OP_ADD, Character.toString(ch), 4, ASSOC_LEFT);
            case '-':
                return new OperationToken(OpType.OP_SUB, Character.toString(ch), 4, ASSOC_LEFT);
            case '/':
                return new OperationToken(OpType.OP_DIV, Character.toString(ch), 3, ASSOC_LEFT);
            case '*':
                return new OperationToken(OpType.OP_MUL, Character.toString(ch), 3, ASSOC_LEFT);
            default:
                ;
        }
        if (isDigit(ch)) {
            return new NumberToken(getNumber());
        }
        if (isAlphabetic(ch)) {
            return new VariableToken(getIdentifier());
        }
        return new Token(TokenType.NULL, "NULL");
    }

    /**
     * Turn the string into list of tokens.
     *
     * @return List of tokens.
     */
    public ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (!isEnd()) {
            Token token = nextToken();
            if (token.type != TokenType.NULL) {
                tokens.add(token);
            }
        }
        tokens.add(new Token(TokenType.EOF, "EOF"));
        return tokens;
    }

    public Lexer(String src) {
        this.cursor = 0;
        this.src = src;
    }
}

