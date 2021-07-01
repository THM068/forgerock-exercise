package com.forgerock;

import com.forgerock.expressions.Expression;
import java.util.Map;

public class Filter {

    private Expression expression;

    public Filter(Expression expression) {
        this.expression = expression;
    }

    public boolean match(Map<String, String> user) {
        return expression.intepret(user);
    }
}
