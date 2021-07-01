package com.forgerock.expressions;

public class FilterData {
    private final String key;
    private final String targetData;

    public FilterData(String key, String targetData) {
        this.targetData = targetData;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getTargetData() {
        return targetData;
    }
}
