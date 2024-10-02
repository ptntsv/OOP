import java.util.Scanner;
import org.example.Expressions.Expression;
import org.example.Expressions.UnsignedVariableException;

public class Main {

    public static void main(String[] args) throws UnsignedVariableException {
        Expression e = Expression.deserialize(new Scanner(System.in));
        int val = e.eval("x = 3; y = 4");
        Expression de = e.derivative("x");
        e.print();
        de.print();
        System.out.println(val);
    }
}