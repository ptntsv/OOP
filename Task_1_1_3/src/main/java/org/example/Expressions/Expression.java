package org.example.Expressions;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import org.example.Lexer.Lexer;
import org.example.Lexer.NumberToken;
import org.example.Lexer.OperationToken;
import org.example.Lexer.Token;
import org.example.Lexer.VariableToken;
import org.example.Parser.Parser;

public abstract class Expression {

    /**
     * @param tokens Tokens in reverse polish format.
     * @return Converted expression.
     * @throws EmptyStackException Exception is thrown if given expression has wrong format.
     */
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
            throw new RuntimeException("Bad expression.");
        }
    }

    /**
     * @param reader Input stream (might be file or stdin).
     * @return Deserialized expression.
     */
    public static Expression deserialize(Scanner reader) {
        String expr = reader.nextLine();
        Lexer lexer = new Lexer(expr);
        ArrayList<Token> tokens = Parser.infixToPolish(lexer.tokenize());
        return parseExpression(tokens);
    }

    /**
     * Recursively prints expression content.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * Helper method that executes operation upon signed(!) left and right operands.
     *
     * @return Resulting integer value.
     */
    protected abstract int eval_helper();

    /**
     * Method that signifies provided throughout varMap variables.
     *
     * @param varMap Given signification.
     * @throws UnsignedVariableException Thrown if some variable in expression is unsigned.
     */
    protected abstract void signify(HashMap<String, Integer> varMap)
        throws UnsignedVariableException;

    /**
     * 'High order' method that parses variable signification, calls signify method and
     * eval_helper.
     *
     * @param variables String with variable signification.
     * @return Result of helper method.
     * @throws BadSignificationFormatException Thrown if variables differs from provided format.
     * @throws UnsignedVariableException       Thrown if some variable in expression is unsigned.
     */
    public int eval(String variables)
        throws BadSignificationFormatException, UnsignedVariableException {
        HashMap<String, Integer> varMap = new HashMap<>();
        try {
            for (String s : variables.split(";")) {
                String[] split = s.split("=");
                if (split.length != 2) {
                    throw new BadSignificationFormatException("Bad variable signification format.");
                }
                String name = split[0].strip();
                int value = Integer.parseInt(split[1].strip());
                varMap.put(name, value);
            }
            signify(varMap);

        } catch (NumberFormatException e) {
            throw new BadSignificationFormatException("Bad variable value.");
        }
        return eval_helper();
    }

    /**
     * Method for calculating symbol-derivation.
     *
     * @param var Variable upon which calculate derivative.
     * @return New expression, result of derivation.
     */
    public abstract Expression derivative(String var);
}