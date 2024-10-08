package org.example.expressions;

/**
 * Multiplication operation.
 */
public class Mul extends BinaryExpression {

    @Override
    protected int eval_helper() {
        return left.eval_helper() * right.eval_helper();
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    @Override
    public Expression simplify() {
        left = left.simplify();
        right = right.simplify();
        if (left instanceof Number l) {
            switch (l.getValue()) {
                case 0:
                    return new Number(0);
                case 1:
                    return right;
                default:
                    ;
            }
        }
        if (right instanceof Number r) {
            switch (r.getValue()) {
                case 0:
                    return new Number(0);
                case 1:
                    return left;
                default:
                    ;
            }
        }
        if (left instanceof Number
            && right instanceof Number) {
            return new Number(this.eval_helper());
        }
        return new Mul(left, right);
    }

    public Mul(Expression left, Expression right) {
        super(left, right);
        this.opSign = '*';
    }
}
