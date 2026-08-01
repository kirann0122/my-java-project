package com.legacy.analysis;

import java.util.*;

/**
 * Represents a graph of code risks.
 */
public class RiskGraph {
    private Map<String, String> riskLevels;
    private Set<String> classes;

    public RiskGraph() {
        this.riskLevels = new HashMap<>();
        this.classes = new HashSet<>();
    }

    /**
     * Adds a class to the risk graph.
     * 
     * @param className the class to add
     */
    public void addClass(String className) {
        classes.add(className);
    }

    /**
     * Sets the risk level for a given class.
     * 
     * @param className the class to set the risk level for
     * @param riskLevel the risk level to set
     */
    public void setRiskLevel(String className, String riskLevel) {
        riskLevels.put(className, riskLevel);
    }

    /**
     * Gets the risk level for a given class.
     * 
     * @param className the class to get the risk level for
     * @return the risk level for the class
     */
    public String getRiskLevel(String className) {
        return riskLevels.getOrDefault(className, "Unknown");
    }

    /**
     * Gets the classes in the risk graph.
     * 
     * @return a set of classes in the risk graph
     */
    public Set<String> getClasses() {
        return classes;
    }
}
