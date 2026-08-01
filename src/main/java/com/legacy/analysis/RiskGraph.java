package com.legacy.analysis;

import java.util.HashMap;
import java.util.Map;

public class RiskGraph {
    private Map<String, RiskLevel> riskNodes;

    public RiskGraph() {
        this.riskNodes = new HashMap<>();
    }

    public void addRiskNode(String className, String riskLevel, String description) {
        riskNodes.put(className, new RiskLevel(riskLevel, description));
    }

    public Map<String, RiskLevel> getRiskNodes() {
        return riskNodes;
    }

    public static class RiskLevel {
        private String riskLevel;
        private String description;

        public RiskLevel(String riskLevel, String description) {
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
