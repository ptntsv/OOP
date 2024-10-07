import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.example.Expressions.Add;
import org.example.Expressions.BinaryExpression;
import org.example.Expressions.Expression;
import org.example.Expressions.Mul;
import org.example.Expressions.Number;
import org.example.Expressions.Sub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimplificationTest {

    @Test
    public void exprEqualityTest1() {
        Expression e1 = Expression.deserialize("1 * x");
        Expression e2 = Expression.deserialize("99 * 99");
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    public void exprEqualityTest2() {
        Expression e1 = Expression.deserialize("1 + 2 * x - y");
        Expression e2 = Expression.deserialize("1 + 2 * x - x");
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    public void exprEqualityTest3() {
        Expression e1 = Expression.deserialize("1 * x");
        Expression e2 = Expression.deserialize("1 * x");
        Assertions.assertEquals(e1, e2);
    }

    @Test
    public void exprEqualityTest4() {
        Expression e1 = Expression.deserialize("1 + x");
        Expression e2 = Expression.deserialize("1 * x");
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    public void exprEqualityTest5() {
        Expression e1 = Expression.deserialize("2 + x");
        Expression e2 = Expression.deserialize("1 + x");
        Assertions.assertNotEquals(e1, e2);
    }

    @Test
    public void noSignificationTest() throws FileNotFoundException {
        Expression e = Expression.deserialize("1 + 2 * 3 / 4 + (2 + x * 0)");
        Expression se = e.simplify();
        Assertions.assertInstanceOf(Number.class, se);
        Number n = (Number) se;
        Assertions.assertEquals(n.toString(), "4");
    }

    @Test
    public void significationTest() {
        Expression e = Expression.deserialize("1 + 2 * x - y");
        var se = e.simplify();
        Assertions.assertInstanceOf(Sub.class, e);
        Assertions.assertInstanceOf(Sub.class, se);
    }

    @Test
    public void zeroMulTest() {
        Expression e = Expression.deserialize("1 * 0 + x");
        var se = e.simplify();
        Assertions.assertFalse(se instanceof Number);
        Assertions.assertTrue(
            ((BinaryExpression) se).left instanceof Number n && n.getValue() == 0);
        Assertions.assertEquals("(0+x)", se.toString());
    }

    @Test
    public void zeroMulTestFolded1() {
        Expression e = Expression.deserialize("1 + 0 * x");
        var se = e.simplify();
        Assertions.assertInstanceOf(Number.class, se);
        Assertions.assertEquals(((Number) se).getValue(), 1);
    }

    @Test
    public void almostSameSubExprTest() {
        Expression e = Expression.deserialize("(2 + 8 * (x / 2)) - (2 * 8 * (x / 2))");
        var se = e.simplify();
        Assertions.assertInstanceOf(Sub.class, se);
    }

    @Test
    public void sameSubExprTest() {
        Expression e = Expression.deserialize("(2 + 8 * (x / 2)) - (2 + 8 * (x / 2))");
        var se = e.simplify();
        Assertions.assertInstanceOf(Number.class, se);
        Assertions.assertEquals(((Number) se).getValue(), 0);
    }

    @Test
    public void sameSubExprFoldedTest() {
        Expression e = Expression.deserialize(
            "(2 + 8 * ((x+0) / 2)) - ((2 + x * 0) + 4 * 2 * ((x + y * 0) / 2))");
        var se = e.simplify();
        se.print();
        Assertions.assertInstanceOf(Number.class, se);
        Assertions.assertEquals(((Number) se).getValue(), 0);
    }

    @Test
    public void mul1ExprTest() {
        Expression e = Expression.deserialize("1 * (2 * 2 + x)");
        var se = e.simplify();
        Assertions.assertInstanceOf(Add.class, se);
        Assertions.assertEquals(se.toString(), "(4+x)");
    }

    @Test
    public void mul2ExprTest() {
        Expression e = Expression.deserialize("1 * (x * 0 + 2)");
        var se = e.simplify();
        Assertions.assertInstanceOf(Number.class, se);
        Assertions.assertEquals(((Number) se).getValue(), 2);
    }
}
