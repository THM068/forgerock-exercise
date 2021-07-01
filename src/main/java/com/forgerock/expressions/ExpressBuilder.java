package com.forgerock.expressions;

public class ExpressBuilder {

    private Expression baseExpression;

    public ExpressBuilder(Expression baseExpression) {
        this.baseExpression = baseExpression;
    }

    public ExpressBuilder and(Expression expression) {
        baseExpression = new ExpressionWithAnd(baseExpression, expression );
        return this;
    }

    public ExpressBuilder or(Expression expression) {
        baseExpression = new ExpressionWithOr(baseExpression, expression );
        return this;
    }

    public ExpressBuilder not(Expression expression) {
        baseExpression = new ExpressionWithNot(baseExpression, expression );
        return this;
    }

    public Expression build() {
        return baseExpression;
    }
}
