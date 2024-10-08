package org.example.expressions;

import java.util.HashMap;

public abstract class BinaryExpression extends Expression {

    public Expression left;
    public Expression right;
    public char opSign;

    @Override
    protected boolean anyVariables() {
        return left.anyVariables() || right.anyVariables();
    }

    @Override
    public abstract Expression simplify();

    @Override
    protected void signify(HashMap<String, Integer> varMap) throws UnsignedVariableException {
        left.signify(varMap);
        right.signify(varMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryExpression be) {
            return this.left.equals(be.left) && this.right.equals(be.right)
                   && be.opSign == this.opSign;
        }
        return false;
    }

    @Override
    public String toString() {
        return '(' + left.toString() + this.opSign + right.toString() + ')';
    }

    public BinaryExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}
