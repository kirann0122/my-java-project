package com.legacy.reporting;

import com.legacy.analysis.RiskGraph;

import java.util.Map;

/**
 * Generates reports for code risks.
 */
public class RiskReporter {
    private RiskGraph riskGraph;

    public RiskReporter(RiskGraph riskGraph) {
        this.riskGraph = riskGraph;
    }

    public void generateRiskReport() {
        Map<String, RiskGraph.RiskLevel> risks = riskGraph.risks;
        for (Map.Entry<String, RiskGraph.RiskLevel> entry : risks.entrySet()) {
            System.out.println("Class: " + entry.getKey() + ", Risk Level: " + entry.getValue());
        }
    }
}
