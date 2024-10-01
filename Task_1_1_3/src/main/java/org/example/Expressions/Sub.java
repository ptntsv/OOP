package org.example.Expressions;

public class Sub extends Expression {

    @Override
    int eval_helper() {
        return left.eval_helper() - right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));

    }

    public Sub(Expression left, Expression right) {
        super(left, right);
        this.opSign = '-';
    }
}
