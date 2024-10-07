package org.example.Expressions;

public class Add extends BinaryExpression {

    @Override
    protected int eval_helper() {
        return left.eval_helper() + right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();

        if (left instanceof Number &&
            right instanceof Number) {
            return new Number(this.eval_helper());
        }
        return new Add(left, right);
    }

    public Add(Expression left, Expression right) {
        super(left, right);
        this.opSign = '+';
    }

}
