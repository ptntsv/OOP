package org.example.Expressions;

public class Sub extends BinaryExpression {

    @Override
    protected int eval_helper() {
        return left.eval_helper() - right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();
        if (this.left.equals(this.right)) {
            return new Number(0);
        }
        if (left instanceof Number && right instanceof Number)
            return new Number(this.eval_helper());
        return new Sub(left, right);
    }

    public Sub(Expression left, Expression right) {
        super(left, right);
        this.opSign = '-';
    }
}
