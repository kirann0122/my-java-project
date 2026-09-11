package com.legacy.validation;

import com.legacy.analysis.CharacterizationTestGenerator;
import java.io.FileNotFoundException;

/**
 * Validates a migrated codebase by generating characterization tests.
 */
public class MigrationValidator {
    private final CharacterizationTestGenerator testGenerator = new CharacterizationTestGenerator();

    /**
     * Generates characterization tests for all Java files under the given root directory.
     *
     * @param rootDirectory the root directory of the migrated codebase
     * @throws FileNotFoundException if any source file cannot be found during test generation
     */
    public void validateMigratedCodebase(String rootDirectory) throws FileNotFoundException {
        testGenerator.generateCharacterizationTestsForUntouchedLegacyClasses(rootDirectory);
    }

    /**
     * Returns the number of generated test methods after validation.
     *
     * @return the count of generated test methods
     */
    public int getGeneratedTestCount() {
        return testGenerator.getTestMethods().size();
    }
}
