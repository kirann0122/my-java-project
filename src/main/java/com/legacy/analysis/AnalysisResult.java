package com.legacy.analysis;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the result of a legacy codebase analysis.
 */
public class AnalysisResult {
    private Map<String, String> results;

    public AnalysisResult() {
        this.results = new HashMap<>();
    }

    public void addResult(String key, String value) {
        results.put(key, value);
    }

    public String getResult(String key) {
        return results.get(key);
    }

    public Map<String, String> getResults() {
        return results;
    }
}
