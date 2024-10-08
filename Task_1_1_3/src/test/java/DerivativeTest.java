import org.example.expressions.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for symbolic derivation.
 */
public class DerivativeTest {

    @Test
    public void constDerTest1() {
        Expression e = Expression.deserialize("1 + 2");
        var dx = e.derivative("x");
        Assertions.assertEquals("(0+0)", dx.toString());
    }

    @Test
    public void constDerTest2() {
        Expression e = Expression.deserialize("1 + 2 + y - z - 33");
        var dx = e.derivative("x");
        Assertions.assertEquals("((((0+0)+0)-0)-0)", dx.toString());
    }

    @Test
    public void linearDxTest1() {
        Expression e = Expression.deserialize("1 + 2 - x");
        var dx = e.derivative("x");
        Assertions.assertEquals("((0+0)-1)", dx.toString());
    }

    @Test
    public void mulDxTest1() {
        Expression e = Expression.deserialize("1*x");
        var dx = e.derivative("x");
        Assertions.assertEquals("((0*x)+(1*1))", dx.toString());
    }

    @Test
    public void mulDxTest2() {
        Expression e = Expression.deserialize("2 * x + 70 * x + y");
        var dx = e.derivative("x");
        Assertions.assertEquals("((((0*x)+(2*1))+((0*x)+(70*1)))+0)", dx.toString());
    }

    @Test
    public void divDxTest1() {
        Expression e = Expression.deserialize("1/x");
        var dx = e.derivative("x");
        Assertions.assertEquals("(((0*x)-(1*1))/(x*x))", dx.toString());
    }

    @Test
    public void divDxTest2() {
        Expression e = Expression.deserialize("(2*x)/(x*x)");
        var dx = e.derivative("x");
        Assertions.assertEquals("(((((0*x)+(2*1))*(x*x))-((2*x)*((1*x)+(x*1))))/((x*x)*(x*x)))",
            dx.toString());
    }

    @Test
    public void combinedDxTest1() {
        Expression e = Expression.deserialize("(3*x + 22)/y");
        var dx = e.derivative("x");
        Assertions.assertEquals("((((((0*x)+(3*1))+0)*y)-(((3*x)+22)*0))/(y*y))", dx.toString());
    }

    @Test
    public void combinedDxTest2() {
        Expression e = Expression.deserialize("y * y + 5");
        var dx = e.derivative("y");
        Assertions.assertEquals("(((1*y)+(y*1))+0)", dx.toString());
    }
}
