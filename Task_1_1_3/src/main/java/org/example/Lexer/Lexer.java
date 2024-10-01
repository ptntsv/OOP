package Lexer;

import java.util.ArrayList;

public class Lexer {

    private String src;
    private int cursor = 0;

    public boolean isEnd() {
        return cursor == src.length();
    }

    public char advance() {
        return src.charAt(cursor++);
    }

    public ArrayList<Token> tokenize() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        while (!isEnd()) {
            char ch = advance();
            switch (ch) {
                case '(':
                    tokens.add(new Token(TokenType.LPAREN));
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RPAREN));
                    break;
                case '+':
                    tokens.add(new Token(TokenType.OP_ADD));
                    break;
                case '-':
                    tokens.add(new Token(TokenType.OP_SUB));
                    break;
                case '/':
                    tokens.add(new Token(TokenType.OP_DIV));
                    break;
                case '*':
                    tokens.add(new Token(TokenType.OP_MUL));
                    break;
            }
        }
        return tokens;
    }

    public Lexer(String src) {
        this.cursor = 0;
        this.src = src;
    }
}
