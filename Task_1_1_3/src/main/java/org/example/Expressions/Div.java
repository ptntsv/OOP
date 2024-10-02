package org.example.Expressions;

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

    public Div(Expression left, Expression right) {
        super(left, right);
        this.opSign = '/';
    }
}
