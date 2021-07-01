package com.forgerock.expressions;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class EqualExpression implements Expression {
    private final FilterData filterData;

    public EqualExpression(FilterData filterData) {
        this.filterData = filterData;
    }
    @Override
    public boolean intepret(Map<String, String> context) {
        Predicate<Set<Map.Entry<String, String>>> predicate = (entry) -> entry.stream().anyMatch((e) -> e.getKey().equals(filterData.getKey()) && e.getValue().equalsIgnoreCase(filterData.getTargetData()));

        return predicate.test(context.entrySet());
    }
}
