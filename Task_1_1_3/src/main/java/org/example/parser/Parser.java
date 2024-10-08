package org.example.parser;

import java.util.ArrayList;
import java.util.Stack;
import org.example.lexer.Associativity;
import org.example.lexer.NumberToken;
import org.example.lexer.OperationToken;
import org.example.lexer.Token;
import org.example.lexer.TokenType;
import org.example.lexer.VariableToken;

/**
 * Parser class.
 */
public class Parser {

    /**
     * Method that converts list of tokens into polish notated.
     *
     * @param tokens Infix tokens.
     * @return Reverse polish notated tokens.
     */
    public static ArrayList<Token> infixToPolish(ArrayList<Token> tokens) {
        ArrayList<Token> result = new ArrayList<>();
        Stack<Token> opStack = new Stack<>();
        for (var t : tokens) {
            if (t instanceof NumberToken n) {
                result.add(n);
            } else if (t instanceof VariableToken v) {
                result.add(v);
            } else if (t instanceof OperationToken op) {
                if (opStack.empty()) {
                    opStack.push(op);
                } else if (opStack.peek() instanceof OperationToken topOp) {
                    if (op.precedence < topOp.precedence) {
                        opStack.push(op);
                    } else {
                        while (!opStack.empty()
                               && opStack.peek().type != TokenType.LPAREN) {
                            if (opStack.peek() instanceof OperationToken top) {
                                if (op.precedence > top.precedence || (
                                    op.precedence == top.precedence
                                    && op.associativity == Associativity.ASSOC_LEFT)) {
                                    result.add(opStack.pop());
                                } else {
                                    break;
                                }
                            }
                        }
                        opStack.push(op);
                    }
                } else if (opStack.peek().type == TokenType.LPAREN) {
                    opStack.push(op);
                }
            } else if (t.type == TokenType.LPAREN) {
                opStack.push(t);
            } else if (t.type == TokenType.RPAREN) {
                while (opStack.peek().type != TokenType.LPAREN) {
                    result.add(opStack.pop());
                }
                opStack.pop();
            }
        }
        while (!opStack.empty()) {
            result.add(opStack.pop());
        }
        return new ArrayList<Token>(result);
    }
}
