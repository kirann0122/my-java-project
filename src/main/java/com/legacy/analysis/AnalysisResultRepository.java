package com.legacy.analysis;

import java.util.HashMap;
import java.util.Map;

/**
 * Repository for storing and retrieving legacy codebase analysis results.
 */
public class AnalysisResultRepository {
    private Map<String, AnalysisResult> results;

    public AnalysisResultRepository() {
        this.results = new HashMap<>();
    }

    public void storeResult(String id, AnalysisResult result) {
        results.put(id, result);
    }

    public AnalysisResult retrieveResult(String id) {
        return results.get(id);
    }
}
