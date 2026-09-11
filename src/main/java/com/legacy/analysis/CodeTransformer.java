package com.legacy.analysis;

import com.legacy.migration.MigrationProgressTracker;
import com.legacy.validation.MigrationValidator;
import java.io.FileNotFoundException;

/**
 * Handles code transformation and updates migration progress.
 */
public class CodeTransformer {
    private MigrationProgressTracker migrationProgressTracker;
    private RiskGraph riskGraph;
    private DocumentationGenerator documentationGenerator;
    private MigrationValidator migrationValidator;

    public CodeTransformer() {
        this.migrationProgressTracker = new MigrationProgressTracker();
        this.riskGraph = new RiskGraph();
        this.documentationGenerator = new DocumentationGenerator();
        this.migrationValidator = new MigrationValidator();
    }

    public MigrationProgressTracker getMigrationProgressTracker() {
        return migrationProgressTracker;
    }

    public RiskGraph getRiskGraph() {
        return riskGraph;
    }

    public DocumentationGenerator getDocumentationGenerator() {
        return documentationGenerator;
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
        if (documentationGenerator != null) {
            documentationGenerator.generateDocumentation(className);
        }
    }

    /**
     * Tests and validates the migrated codebase by generating characterization tests
     * for all legacy classes and ensuring that at least one test was produced.
     *
     * @param rootDirectory the root directory of the migrated codebase
     * @return true if validation succeeds (i.e., tests were generated), false otherwise
     * @throws FileNotFoundException if any source file cannot be found during test generation
     */
    public boolean validateMigratedCodebase(String rootDirectory) throws FileNotFoundException {
        migrationValidator.validateMigratedCodebase(rootDirectory);
        // If no exception, validation succeeded; return true if any tests were generated
        return migrationValidator.getGeneratedTestCount() > 0;
    }

    // ... existing code ...
}
