import org.example.Lexer.Lexer;
import org.example.Lexer.NumberToken;
import org.example.Lexer.OperationToken;
import org.example.Lexer.TokenType;
import org.example.Lexer.VariableToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LexerTest {

    @Test
    public void emptyTest() {
        var l = new Lexer("");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.size(), 1);
        Assertions.assertEquals(tokens.get(0).type, TokenType.EOF);
    }

    @Test
    public void numberTest1() {
        var l = new Lexer("5");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.size(), 2);
        Assertions.assertEquals(tokens.get(0).type, TokenType.NUMBER);
        Assertions.assertInstanceOf(NumberToken.class, tokens.get(0));
        Assertions.assertEquals(((NumberToken) tokens.get(0)).value, 5);
    }

    @Test
    public void numberTest2() {
        var l = new Lexer("5 4 4344 23223");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.size(), 5);
        Assertions.assertEquals(tokens.get(0).type, TokenType.NUMBER);
        Assertions.assertEquals(((NumberToken) tokens.get(0)).value, 5);
        Assertions.assertEquals(((NumberToken) tokens.get(1)).value, 4);
        Assertions.assertEquals(((NumberToken) tokens.get(2)).value, 4344);
        Assertions.assertEquals(((NumberToken) tokens.get(3)).value, 23223);
    }

    @Test
    public void addTest() {
        var l = new Lexer("5333+32");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.size(), 4);
        Assertions.assertEquals(tokens.get(0).type, TokenType.NUMBER);
        Assertions.assertEquals(tokens.get(1).type, TokenType.OP);
        Assertions.assertEquals(tokens.get(2).type, TokenType.NUMBER);
        Assertions.assertEquals(((NumberToken) tokens.get(0)).value, 5333);
        Assertions.assertEquals(((NumberToken) tokens.get(2)).value, 32);
    }

    @Test
    public void mulTest() {
        var l = new Lexer("1 * 2+3 * 3");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.get(0).type, TokenType.NUMBER);
        Assertions.assertEquals(tokens.get(1).type, TokenType.OP);
        Assertions.assertEquals(tokens.get(2).type, TokenType.NUMBER);

        Assertions.assertInstanceOf(OperationToken.class, tokens.get(3));
        Assertions.assertEquals(tokens.get(3).type, TokenType.OP);

        Assertions.assertInstanceOf(OperationToken.class, tokens.get(5));
        Assertions.assertEquals(tokens.get(5).type, TokenType.OP);
        Assertions.assertEquals(((NumberToken) tokens.get(0)).value, 1);
        Assertions.assertEquals(((NumberToken) tokens.get(2)).value, 2);
        Assertions.assertEquals(((NumberToken) tokens.get(4)).value, 3);
        Assertions.assertEquals(((NumberToken) tokens.get(4)).value, 3);
    }

    @Test
    public void miscTest1() {
        var l = new Lexer("(1 + 2) * 3");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.get(0).type, TokenType.LPAREN);
        Assertions.assertEquals(tokens.get(1).type, TokenType.NUMBER);
//        Assertions.assertEquals(tokens.get(2).type, TokenType.OP_ADD);
        Assertions.assertEquals(tokens.get(3).type, TokenType.NUMBER);
        Assertions.assertEquals(tokens.get(4).type, TokenType.RPAREN);
    }

    @Test
    public void varTest1() {
        var l = new Lexer("(1 + 2) xxx");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.get(0).type, TokenType.LPAREN);
        Assertions.assertEquals(tokens.get(1).type, TokenType.NUMBER);
        Assertions.assertEquals(tokens.get(2).type, TokenType.OP);
        Assertions.assertEquals(tokens.get(3).type, TokenType.NUMBER);
        Assertions.assertEquals(tokens.get(4).type, TokenType.RPAREN);
        Assertions.assertEquals(tokens.get(5).type, TokenType.VARIABLE);
        Assertions.assertEquals(((VariableToken) tokens.get(5)).content, "xxx");
    }

    @Test
    public void varTest2() {
        var l = new Lexer("xx sss x x1234 444");
        var tokens = l.tokenize();
        Assertions.assertEquals(tokens.get(0).type, TokenType.VARIABLE);
        Assertions.assertEquals(tokens.get(1).type, TokenType.VARIABLE);
        Assertions.assertEquals(tokens.get(2).type, TokenType.VARIABLE);
        Assertions.assertEquals(tokens.get(3).type, TokenType.VARIABLE);
        Assertions.assertEquals(((VariableToken) tokens.get(3)).content, "x1234");
        Assertions.assertEquals(tokens.get(4).type, TokenType.NUMBER);
    }
}
