package com.legacy.analysis;

import java.util.*;

/**
 * Represents a graph of code risks.
 */
public class RiskGraph {
    private Map<String, RiskLevel> risks;

    public RiskGraph() {
        this.risks = new HashMap<>();
    }

    /**
     * Adds a risk to the graph.
     * 
     * @param className the class that has a risk
     * @param riskLevel the level of risk for the class
     */
    public void addRisk(String className, RiskLevel riskLevel) {
        risks.put(className, riskLevel);
    }

    /**
     * Gets the risk level for a given class.
     * 
     * @param className the class to get risk level for
     * @return the risk level for the class
     */
    public RiskLevel getRisk(String className) {
        return risks.getOrDefault(className, RiskLevel.LOW);
    }

    /**
     * Removes a risk from the graph.
     * 
     * @param className the class that has a risk
     */
    public void removeRisk(String className) {
        risks.remove(className);
    }

    public enum RiskLevel {
        LOW,
        MEDIUM,
        HIGH
    }
}
