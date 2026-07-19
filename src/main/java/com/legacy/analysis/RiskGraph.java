package com.legacy.analysis;

import java.util.*;

/**
 * Represents a graph of risk analysis results.
 */
public class RiskGraph {
    private Map<String, RiskNode> riskNodes;

    public RiskGraph() {
        this.riskNodes = new HashMap<>();
    }

    /**
     * Adds a risk node to the graph.
     * 
     * @param className the class that has a risk
     * @param riskLevel the level of risk (e.g. high, medium, low)
     * @param description a description of the risk
     */
    public void addRiskNode(String className, String riskLevel, String description) {
        riskNodes.computeIfAbsent(className, k -> new RiskNode()).addRisk(riskLevel, description);
    }

    /**
     * Gets the risk nodes for a given class.
     * 
     * @param className the class to get risk nodes for
     * @return a list of risk nodes for the class
     */
    public List<Risk> getRiskNodes(String className) {
        return riskNodes.getOrDefault(className, new RiskNode()).getRisks();
    }

    /**
     * Removes a risk node from the graph.
     * 
     * @param className the class that has a risk
     * @param riskLevel the level of risk (e.g. high, medium, low)
     */
    public void removeRiskNode(String className, String riskLevel) {
        riskNodes.computeIfPresent(className, (k, v) -> {
            v.removeRisk(riskLevel);
            return v.getRisks().isEmpty() ? null : v;
        });
    }

    private class RiskNode {
        private List<Risk> risks;

        public RiskNode() {
            this.risks = new ArrayList<>();
        }

        public void addRisk(String riskLevel, String description) {
            risks.add(new Risk(riskLevel, description));
        }

        public List<Risk> getRisks() {
            return risks;
        }

        public void removeRisk(String riskLevel) {
            risks.removeIf(r -> r.getRiskLevel().equals(riskLevel));
        }
    }

    private class Risk {
        private String riskLevel;
        private String description;

        public Risk(String riskLevel, String description) {
            this.riskLevel = riskLevel;
            this.description = description;
        }

        public String getRiskLevel() {
            return riskLevel;
        }

        public String getDescription() {
            return description;
        }
    }
}
