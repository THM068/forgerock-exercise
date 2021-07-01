package com.forgerock.expressions;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class GreaterThanExpression implements Expression{
    private final FilterData filterData;

    public GreaterThanExpression(FilterData filterData) {
        this.filterData = filterData;
    }

    @Override
    public boolean intepret(Map<String, String> context) {
        try {
            Predicate<Set<Map.Entry<String, String>>> predicate = (entry) -> entry.stream().anyMatch((e) -> e.getKey().equals(filterData.getKey()) &&  Integer.parseInt(filterData.getTargetData()) > Integer.parseInt(e.getValue()));
            return  predicate.test(context.entrySet());
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
}
