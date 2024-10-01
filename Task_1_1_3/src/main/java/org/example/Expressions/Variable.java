package org.example.Expressions;

import java.util.HashMap;

public class Variable extends Expression {

    public String name;

    public Variable(String name) {
        this.name = name;
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
    int eval_helper() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
