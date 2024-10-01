package Lexer;

public class NumberToken extends Token {

    int value;

    public NumberToken(int value) {
        this.value = value;
    }

    public NumberToken(TokenType type) {
        super(type);
    }
}
