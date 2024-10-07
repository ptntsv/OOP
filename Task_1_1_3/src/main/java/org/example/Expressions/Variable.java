package org.example.Expressions;

import java.util.HashMap;
import java.util.Objects;

public class Variable extends Expression {

    private int value;
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    protected void signify(HashMap<String, Integer> varMap) throws UnsignedVariableException {
        if (varMap.containsKey(this.name)) {
            this.value = varMap.get(this.name);
        } else {
            throw new UnsignedVariableException("Unsigned variable " + this.name);
        }
    }

    @Override
    public Expression derivative(String var) {
        return new Number(name.equals(var) ? 1 : 0);
    }

    @Override
    public Expression simplify() {
        return new Variable(this.name, this.value);
    }

    @Override
    protected boolean anyVariables() {
        return true;
    }

    @Override
    protected int eval_helper() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Variable v) {
            return v.value == this.value && Objects.equals(v.name, this.name);
        }
        return false;
    }
}
