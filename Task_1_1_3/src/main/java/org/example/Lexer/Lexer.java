package org.example.Lexer;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;
import static org.example.Lexer.Associativity.ASSOC_LEFT;

import java.util.ArrayList;

public class Lexer {

    private String src;
    private int cursor = 0;

    public boolean isEnd() {
        return cursor == src.length();
    }

    public char advance() {
        if (isEnd()) {
            return 0;
        }
        return src.charAt(cursor++);
    }

    public char peek() {
        if (isEnd()) {
            return 0;
        }
        return src.charAt(cursor);
    }

    public int getNumber() {
        int start = cursor - 1;
        while (isDigit(peek())) {
            advance();
        }
        return Integer.parseInt(src.substring(start, cursor));
    }

    public String getIdentifier() {
        int start = cursor - 1;
        while (isAlphabetic(peek()) || isDigit(peek())) {
            advance();
        }
        return src.substring(start, cursor);
    }

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
        }
        if (isDigit(ch)) {
            return new NumberToken(getNumber());
        }
        if (isAlphabetic(ch)) {
            return new VariableToken(getIdentifier());
        }
        return new Token(TokenType.NULL, "NULL");
    }

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

