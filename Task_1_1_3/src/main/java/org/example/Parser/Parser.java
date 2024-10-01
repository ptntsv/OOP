package org.example.Parser;

import java.util.ArrayList;
import java.util.Stack;
import org.example.Lexer.Associativity;
import org.example.Lexer.NumberToken;
import org.example.Lexer.OperationToken;
import org.example.Lexer.Token;
import org.example.Lexer.TokenType;
import org.example.Lexer.VariableToken;

public class Parser {

    public static ArrayList<Token> infixToPolish(ArrayList<Token> tokens) {
        ArrayList<Token> result = new ArrayList<>();
        Stack<Token> op_stack = new Stack<>();
        for (var t : tokens) {
            if (t instanceof NumberToken n) {
                result.add(n);
            } else if (t instanceof VariableToken v) {
                result.add(v);
            } else if (t instanceof OperationToken op) {
                if (op_stack.empty()) {
                    op_stack.push(op);
                } else if (op_stack.peek() instanceof OperationToken top_op) {
                    if (op.precedence < top_op.precedence) {
                        op_stack.push(op);
                    } else {
                        while (!op_stack.empty() &&
                               op_stack.peek().type != TokenType.LPAREN) {
                            if (op_stack.peek() instanceof OperationToken top) {
                                if (op.precedence > top.precedence || (
                                    op.precedence == top.precedence
                                    && op.associativity == Associativity.ASSOC_LEFT)) {
                                    result.add(op_stack.pop());
                                } else {
                                    break;
                                }
                            }
                        }
                        op_stack.push(op);
                    }
                } else if (op_stack.peek().type == TokenType.LPAREN) {
                    op_stack.push(op);
                }
            } else if (t.type == TokenType.LPAREN) {
                op_stack.push(t);
            } else if (t.type == TokenType.RPAREN) {
                while (op_stack.peek().type != TokenType.LPAREN) {
                    result.add(op_stack.pop());
                }
                op_stack.pop();
            }
        }
        while (!op_stack.empty()) {
            result.add(op_stack.pop());
        }
        return new ArrayList<Token>(result);
    }
}
