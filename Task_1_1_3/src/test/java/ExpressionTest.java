import org.example.expressions.BadSignificationFormatException;
import org.example.expressions.Expression;
import org.example.expressions.UnsignedVariableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for miscellaneous Expression methods.
 */
public class ExpressionTest {

    @Test
    public void goodSignificationTest() throws UnsignedVariableException {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        int res = e.eval("x = 22");
        Assertions.assertEquals(24, res);
    }

    @Test
    public void badSignificationTest1() throws UnsignedVariableException {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String badFormat = "bad variable string";
        Assertions.assertThrows(BadSignificationFormatException.class, () -> e.eval(badFormat));
    }

    @Test
    public void badSignificationTest2() throws UnsignedVariableException {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String lessBadFormat = "x = y";
        Assertions.assertThrows(BadSignificationFormatException.class, () -> e.eval(lessBadFormat));
    }

    @Test
    public void badSignificationTest3() {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String lessBadFormat = "x = x1234";
        var ex = Assertions.assertThrows(BadSignificationFormatException.class,
            () -> e.eval(lessBadFormat));
        Assertions.assertEquals("Bad variable value.", ex.getMessage());
    }

    @Test
    public void badSignificationTest4() throws UnsignedVariableException {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String lessBadFormat = "x = 12; y=9a-adsfb;";
        Assertions.assertThrows(BadSignificationFormatException.class, () -> e.eval(lessBadFormat));
    }

    @Test
    public void goodButUnnecessarySignificationTest() {
        Expression e = Expression.deserialize("1 * (9 * 1 + 2)");
        String goodFormat = "y = 120; x = 999;";
        Assertions.assertDoesNotThrow(() -> e.eval(goodFormat));
    }

    @Test
    public void unsignifiedTest1() {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String goodFormat = "";
        Assertions.assertThrows(UnsignedVariableException.class, () -> e.eval(goodFormat));
    }

    @Test
    public void unsignifiedTest2() {
        Expression e = Expression.deserialize("y * (x * 1 + 2)");
        String goodFormat = "x = 2";
        Assertions.assertThrows(UnsignedVariableException.class, () -> e.eval(goodFormat));
    }

    @Test
    public void unsignifiedTest3() {
        Expression e = Expression.deserialize("1 * (x * 1 + 2)");
        String goodFormat = ";;;;;;";
        Assertions.assertThrows(UnsignedVariableException.class, () -> e.eval(goodFormat));
    }

    @Test
    public void unsignifiedTest4() {
        Expression e = Expression.deserialize(
            "1 + (x * 0 + 2)");
        String goodFormat = "";
        Assertions.assertDoesNotThrow(() -> e.eval(goodFormat));
    }

    @Test
    public void unsignifiedTest5() {
        Expression e = Expression.deserialize(
            "(x + y + z - zyx / abcde) - (x + y + z - zyx / abcde)");
        String goodFormat = ";;;;;;";
        Assertions.assertDoesNotThrow(() -> e.eval(goodFormat));
    }
}
