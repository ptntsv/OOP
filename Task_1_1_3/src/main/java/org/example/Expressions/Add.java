package org.example.Expressions;

public class Add extends Expression {

    @Override
    int eval_helper() {
        return left.eval_helper() + right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    public Add(Expression left, Expression right) {
        super(left, right);
        this.opSign = '+';
    }

}
