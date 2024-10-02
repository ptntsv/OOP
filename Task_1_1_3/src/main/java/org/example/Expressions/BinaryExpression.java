package org.example.Expressions;

import java.util.HashMap;

public abstract class BinaryExpression extends Expression {

    public Expression left;
    public Expression right;
    public char opSign;

    @Override
    protected void signify(HashMap<String, Integer> varMap) throws UnsignedVariableException {
        left.signify(varMap);
        right.signify(varMap);
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
