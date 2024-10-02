package org.example.Expressions;

import java.util.HashMap;

public class Number extends Expression {

    private int value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    protected void signify(HashMap<String, Integer> varMap) {
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    protected int eval_helper() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
