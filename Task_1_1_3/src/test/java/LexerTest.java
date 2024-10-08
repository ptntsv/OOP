import org.example.lexer.Lexer;
import org.example.lexer.NumberToken;
import org.example.lexer.OperationToken;
import org.example.lexer.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for lexer.
 */
public class LexerTest {

    @Test
    public void emptyTest() {
        var l = new Lexer("");
        var tokens = l.tokenize();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenType.EOF, tokens.get(0).type);
    }

    @Test
    public void numberTest1() {
        var l = new Lexer("5");
        var tokens = l.tokenize();
        Assertions.assertEquals(2, tokens.size());
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(0).type);
        Assertions.assertInstanceOf(NumberToken.class, tokens.get(0));
        Assertions.assertEquals(5, ((NumberToken) tokens.get(0)).value);
    }

    @Test
    public void numberTest2() {
        var l = new Lexer("5 4 4344 23223");
        var tokens = l.tokenize();
        Assertions.assertEquals(5, tokens.size());
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(0).type);
        Assertions.assertEquals(5, ((NumberToken) tokens.get(0)).value);
        Assertions.assertEquals(4, ((NumberToken) tokens.get(1)).value);
        Assertions.assertEquals(4344, ((NumberToken) tokens.get(2)).value);
        Assertions.assertEquals(23223, ((NumberToken) tokens.get(3)).value);
    }

    @Test
    public void addTest() {
        var l = new Lexer("5333+32");
        var tokens = l.tokenize();
        Assertions.assertEquals(4, tokens.size());
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(0).type);
        Assertions.assertEquals(TokenType.OP, tokens.get(1).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(2).type);
        Assertions.assertEquals(5333, ((NumberToken) tokens.get(0)).value);
        Assertions.assertEquals(32, ((NumberToken) tokens.get(2)).value);
    }

    @Test
    public void mulTest() {
        var l = new Lexer("1 * 2+3 * 3");
        var tokens = l.tokenize();
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(0).type);
        Assertions.assertEquals(TokenType.OP, tokens.get(1).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(2).type);

        Assertions.assertInstanceOf(OperationToken.class, tokens.get(3));
        Assertions.assertEquals(TokenType.OP, tokens.get(3).type);

        Assertions.assertInstanceOf(OperationToken.class, tokens.get(5));
        Assertions.assertEquals(TokenType.OP, tokens.get(5).type);
        Assertions.assertEquals(1, ((NumberToken) tokens.get(0)).value);
        Assertions.assertEquals(2, ((NumberToken) tokens.get(2)).value);
        Assertions.assertEquals(3, ((NumberToken) tokens.get(4)).value);
        Assertions.assertEquals(3, ((NumberToken) tokens.get(4)).value);
    }

    @Test
    public void miscTest1() {
        var l = new Lexer("(1 + 2) * 3");
        var tokens = l.tokenize();
        Assertions.assertEquals(TokenType.LPAREN, tokens.get(0).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(1).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(3).type);
        Assertions.assertEquals(TokenType.RPAREN, tokens.get(4).type);
    }

    @Test
    public void varTest1() {
        var l = new Lexer("(1 + 2) xxx");
        var tokens = l.tokenize();
        Assertions.assertEquals(TokenType.LPAREN, tokens.get(0).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(1).type);
        Assertions.assertEquals(TokenType.OP, tokens.get(2).type);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(3).type);
        Assertions.assertEquals(TokenType.RPAREN, tokens.get(4).type);
        Assertions.assertEquals(TokenType.VARIABLE, tokens.get(5).type);
        Assertions.assertEquals("xxx", tokens.get(5).content);
    }

    @Test
    public void varTest2() {
        var l = new Lexer("xx sss x x1234 444");
        var tokens = l.tokenize();
        Assertions.assertEquals(TokenType.VARIABLE, tokens.get(0).type);
        Assertions.assertEquals(TokenType.VARIABLE, tokens.get(1).type);
        Assertions.assertEquals(TokenType.VARIABLE, tokens.get(2).type);
        Assertions.assertEquals(TokenType.VARIABLE, tokens.get(3).type);
        Assertions.assertEquals("x1234", tokens.get(3).content);
        Assertions.assertEquals(TokenType.NUMBER, tokens.get(4).type);
    }
}
