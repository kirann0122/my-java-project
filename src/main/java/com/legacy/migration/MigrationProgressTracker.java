package com.legacy.migration;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks migration progress.
 */
public class MigrationProgressTracker {
    private Map<String, MigrationStatus> migrationStatus;

    public MigrationProgressTracker() {
        this.migrationStatus = new HashMap<>();
    }

    /**
     * Updates the migration status for a given class.
     * 
     * @param className the class to update migration status for
     * @param status the migration status
     */
    public void updateMigrationStatus(String className, MigrationStatus status) {
        migrationStatus.put(className, status);
    }

    /**
     * Gets the migration status for a given class.
     * 
     * @param className the class to get migration status for
     * @return the migration status
     */
    public MigrationStatus getMigrationStatus(String className) {
        return migrationStatus.getOrDefault(className, MigrationStatus.NOT_STARTED);
    }

    /**
     * Enum for migration status.
     */
    public enum MigrationStatus {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }
}
