package com.forgerock.expressions;

import java.util.Map;

public class ExpressionWithAnd implements Expression {
    private final Expression firstExpression;
    private final Expression secondExpression;

    public ExpressionWithAnd(Expression firstExpression, Expression secondExpression) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
    }

    @Override
    public boolean intepret(Map<String, String> context) {
        return firstExpression.intepret(context) && secondExpression.intepret(context);
    }
}
