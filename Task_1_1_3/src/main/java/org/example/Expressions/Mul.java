package org.example.Expressions;

import java.util.HashMap;

public class Mul extends BinaryExpression {

    @Override
    protected int eval_helper() {
        return left.eval_helper() * right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    public Mul(Expression left, Expression right) {
        super(left, right);
        this.opSign = '*';
    }
}
