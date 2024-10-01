package org.example.Expressions;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import org.example.Lexer.Lexer;
import org.example.Lexer.NumberToken;
import org.example.Lexer.OperationToken;
import org.example.Lexer.Token;
import org.example.Lexer.VariableToken;
import org.example.Parser.Parser;

public abstract class Expression {

    public int value;
    public char opSign;

    public Expression left;
    public Expression right;

    private static Expression parseExpression(ArrayList<Token> tokens) throws EmptyStackException {
        Stack<Expression> s = new Stack<>();
        try {
            for (Token token : tokens) {
                if (token instanceof NumberToken n) {
                    s.push(new Number(n.value));
                }
                if (token instanceof VariableToken v) {
                    s.push(new Variable(v.content));
                }
                if (token instanceof OperationToken op) {
                    Expression right = s.pop();
                    Expression left = s.pop();
                    switch (op.type) {
                        case OP_ADD: {
                            s.push(new Add(left, right));
                            break;
                        }
                        case OP_SUB: {
                            s.push(new Sub(left, right));
                            break;
                        }
                        case OP_MUL: {
                            s.push(new Mul(left, right));
                            break;
                        }
                        case OP_DIV: {
                            s.push(new Div(left, right));
                            break;
                        }
                    }
                }
            }
            return s.pop();
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
    }

    public static Expression deserialize(String expr) {
        Lexer lexer = new Lexer(expr);
        ArrayList<Token> tokens = Parser.infixToPolish(lexer.tokenize());
        return parseExpression(tokens);
    }

    public void print() {
        System.out.println(this);
    }

    abstract int eval_helper();

    protected void signify(HashMap<String, Integer> varMap) {
        left.signify(varMap);
        right.signify(varMap);
    }

    public int eval(String variables) throws RuntimeException {
        HashMap<String, Integer> varMap = new HashMap<>();
        for (String s : variables.split(";")) {
            String[] split = s.split("=");
            if (split.length != 2) {
                throw new RuntimeException("Bad variable signification format");
            }
            String name = split[0].strip();
            try {
                int value = Integer.parseInt(split[1].strip());
                varMap.put(name, value);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Bad variable value");
            }
        }
        signify(varMap);
        return eval_helper();
    }

    public abstract Expression derivative(String var);

    public Expression() {
    }

    public Expression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return '(' + left.toString() + this.opSign + right.toString() + ')';
    }
}