package com.legacy.reporting;

import com.legacy.analysis.DependencyGraph;
import com.legacy.analysis.RiskGraph;

import java.util.List;

/**
 * Generates human-readable risk reports for legacy code.
 */
public class RiskReporter {
    private RiskGraph riskGraph;
    private DependencyGraph dependencyGraph;

    public RiskReporter(RiskGraph riskGraph, DependencyGraph dependencyGraph) {
        this.riskGraph = riskGraph;
        this.dependencyGraph = dependencyGraph;
    }

    /**
     * Generates a risk report for a given class.
     * 
     * @param className the class to generate a report for
     * @return a human-readable risk report
     */
    public String generateRiskReport(String className) {
        List<String> dependencies = dependencyGraph.getDependencies(className);
        String riskLevel = riskGraph.getRiskLevel(className);
        StringBuilder report = new StringBuilder();
        report.append("Risk Report for ").append(className).append(":\n");
        report.append("Risk Level: ").append(riskLevel).append("\n");
        report.append("Dependencies: ").append(dependencies).append("\n");
        return report.toString();
    }

    /**
     * Generates a comprehensive risk report for the entire codebase.
     * 
     * @return a human-readable risk report
     */
    public String generateComprehensiveRiskReport() {
        StringBuilder report = new StringBuilder();
        report.append("Comprehensive Risk Report:\n");
        for (String className : riskGraph.getClasses()) {
            report.append(generateRiskReport(className)).append("\n");
        }
        return report.toString();
    }
}
