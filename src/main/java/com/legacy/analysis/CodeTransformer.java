package com.legacy.analysis;

import com.legacy.migration.MigrationProgressTracker;
import com.legacy.validation.MigrationValidator;
import com.legacy.monitoring.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

/**
 * Handles code transformation and updates migration progress.
 */
public class CodeTransformer {
    private static final Logger logger = LoggerFactory.getLogger(CodeTransformer.class);

    private MigrationProgressTracker migrationProgressTracker;
    private RiskGraph riskGraph;
    private DocumentationGenerator documentationGenerator;
    private MigrationValidator migrationValidator;
    private MonitoringService monitoringService;

    public CodeTransformer() {
        this.migrationProgressTracker = new MigrationProgressTracker();
        this.riskGraph = new RiskGraph();
        this.documentationGenerator = new DocumentationGenerator();
        this.migrationValidator = new MigrationValidator();
        this.monitoringService = new MonitoringService();
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
        long start = monitoringService.start("updateRiskAndProgress");
        logger.debug("Updating risk for class '{}' with score {}", className, riskScore);
        if (riskGraph != null) {
            riskGraph.updateRisk(className, riskScore);
        }
        if (migrationProgressTracker != null) {
            migrationProgressTracker.incrementProgress();
        }
        if (documentationGenerator != null) {
            documentationGenerator.generateDocumentation(className);
        }
        monitoringService.end("updateRiskAndProgress", start);
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
        long start = monitoringService.start("validateMigratedCodebase");
        logger.info("Validating migrated codebase at '{}'", rootDirectory);
        migrationValidator.validateMigratedCodebase(rootDirectory);
        boolean result = migrationValidator.getGeneratedTestCount() > 0;
        logger.info("Validation result: {}", result ? "success" : "failure");
        monitoringService.end("validateMigratedCodebase", start);
        return result;
    }

    // ... existing code ...
}
