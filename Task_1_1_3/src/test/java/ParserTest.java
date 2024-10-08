import java.util.ArrayList;
import org.example.lexer.Lexer;
import org.example.lexer.Token;
import org.example.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for check correctness of parsing into reverse polish notation.
 */
public class ParserTest {

    private static String tokensToString(ArrayList<Token> tokens) {
        String s = "";
        for (Token token : tokens) {
            s = s.concat(token.toString()) + " ";
        }
        return s.substring(0, s.length() - 1);
    }

    @Test
    public void samePrecedenceTest1() {
        Lexer l = new Lexer("1 + 2 + 3");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 + 3 +", tokensStr);
    }

    @Test
    public void samePrecedenceTest2() {
        Lexer l = new Lexer("1 * 2 * 3");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 * 3 *", tokensStr);
    }

    @Test
    public void samePrecedenceTest3() {
        Lexer l = new Lexer("1 / 33 / 3 / 4 / 6");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 33 / 3 / 4 / 6 /", tokensStr);
    }

    @Test
    public void differPrecedenceTest1() {
        Lexer l = new Lexer("1 + 2 * 3");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 3 * +", tokensStr);
    }

    @Test
    public void differPrecedenceTest2() {
        Lexer l = new Lexer("1 * 2 + 3");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 * 3 +", tokensStr);
    }

    @Test
    public void differPrecedenceTest3() {
        Lexer l = new Lexer("1 * 2 * 3 + 5");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 * 3 * 5 +", tokensStr);
    }

    @Test
    public void differPrecedenceTest4() {
        Lexer l = new Lexer("1 + 2 + 3 + 4 * 5");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 + 3 + 4 5 * +", tokensStr);
    }

    @Test
    public void parensTest1() {
        Lexer l = new Lexer("1 * (2 + 3)");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 3 + *", tokensStr);
    }

    @Test
    public void parensTest2() {
        Lexer l = new Lexer("1 + (2 * 3)");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 3 * +", tokensStr);
    }

    @Test
    public void parensTest3() {
        Lexer l = new Lexer("(1) + (2)");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 +", tokensStr);
    }

    @Test
    public void parensTest4() {
        Lexer l = new Lexer("(2 * ((3) + (4)))");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("2 3 4 + *", tokensStr);
    }

    @Test
    public void parensTest5() {
        Lexer l = new Lexer("((1 + (2 * 3) + 8) / (2 / 3))");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 3 * + 8 + 2 3 / /", tokensStr);
    }

    @Test
    public void parensTest6() {
        Lexer l = new Lexer("(((1) + (((3)))) * 9)");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 3 + 9 *", tokensStr);
    }

    @Test
    public void varTest1() {
        Lexer l = new Lexer("1 + 2 * x");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("1 2 x * +", tokensStr);
    }

    @Test
    public void varTest2() {
        Lexer l = new Lexer("2 * (1 + (9 / (xxxxx * abcde1234) + 3))");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("2 1 9 xxxxx abcde1234 * / 3 + + *", tokensStr);
    }

    @Test
    public void varTest3() {
        Lexer l = new Lexer("2 + x * 8 / 9");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("2 x 8 * 9 / +", tokensStr);
    }

    @Test
    public void varTest4() {
        Lexer l = new Lexer("2 * x + y + z + m + n - 56 - 66");
        var tokens = Parser.infixToPolish(l.tokenize());
        String tokensStr = tokensToString(tokens);
        Assertions.assertEquals("2 x * y + z + m + n + 56 - 66 -", tokensStr);
    }
}