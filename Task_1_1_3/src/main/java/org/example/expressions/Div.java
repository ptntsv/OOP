package org.example.expressions;

public class Div extends BinaryExpression {

    @Override
    protected int eval_helper() {
        return left.eval_helper() / right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Div(
            new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))),
            new Mul(right, right));
    }

    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();
        if (left instanceof Number && right instanceof Number) {
            return new Number(this.eval_helper());
        }
        return new Div(left, right);
    }

    public Div(Expression left, Expression right) {
        super(left, right);
        this.opSign = '/';
    }
}
