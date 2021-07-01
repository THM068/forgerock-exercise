package com.forgerock.expressions;

import java.util.Map;

public interface Expression {

    boolean intepret(Map<String, String> context);
}
