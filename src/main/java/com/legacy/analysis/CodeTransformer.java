package com.legacy.analysis;

import com.legacy.migration.MigrationProgressTracker;

/**
 * Handles code transformation and updates migration progress.
 */
public class CodeTransformer {
    private MigrationProgressTracker migrationProgressTracker;
    private RiskGraph riskGraph;

    public CodeTransformer() {
        this.migrationProgressTracker = new MigrationProgressTracker();
        this.riskGraph = new RiskGraph();
    }

    public MigrationProgressTracker getMigrationProgressTracker() {
        return migrationProgressTracker;
    }

    public RiskGraph getRiskGraph() {
        return riskGraph;
    }

    /**
     * Updates the risk graph for a given class and increments migration progress.
     *
     * @param className the name of the class whose risk is being updated
     * @param riskScore the calculated risk score for the class
     */
    public void updateRiskAndProgress(String className, double riskScore) {
        if (riskGraph != null) {
            riskGraph.updateRisk(className, riskScore);
        }
        if (migrationProgressTracker != null) {
            migrationProgressTracker.incrementProgress();
        }
    }

    // ... existing code ...
}
