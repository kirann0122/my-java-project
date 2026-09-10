package com.legacy.analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a graph of risk scores for legacy classes.
 */
public class RiskGraph {
    private final Map<String, Double> riskScores;

    public RiskGraph() {
        this.riskScores = new HashMap<>();
    }

    /**
     * Updates or adds a risk score for the specified class.
     *
     * @param className the fully qualified name of the class
     * @param riskScore the calculated risk score (e.g., 0.0 – 1.0)
     */
    public void updateRisk(String className, double riskScore) {
        riskScores.put(className, riskScore);
    }

    /**
     * Retrieves the risk score for a given class.
     *
     * @param className the fully qualified name of the class
     * @return the risk score, or null if not present
     */
    public Double getRisk(String className) {
        return riskScores.get(className);
    }

    /**
     * Returns an unmodifiable view of all risk scores.
     *
     * @return map of class names to risk scores
     */
    public Map<String, Double> getAllRisks() {
        return Collections.unmodifiableMap(riskScores);
    }
}
