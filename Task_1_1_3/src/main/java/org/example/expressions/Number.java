package org.example.expressions;

import java.util.HashMap;

/**
 * Class that represents a number.
 */
public class Number extends Expression {

    private int value;

    public int getValue() {
        return this.value;
    }

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
    public Expression simplify() {
        return new Number(this.value);
    }

    @Override
    protected int eval_helper() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Number n) {
            return n.value == this.value;
        }
        return false;
    }
}
