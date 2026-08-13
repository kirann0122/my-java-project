package com.legacy.analysis;

import com.legacy.migration.MigrationProgressTracker;

public class CodeTransformer {
    private MigrationProgressTracker migrationProgressTracker;

    public CodeTransformer() {
        this.migrationProgressTracker = new MigrationProgressTracker();
    }

    public MigrationProgressTracker getMigrationProgressTracker() {
        return migrationProgressTracker;
    }

    // ... existing code ...
}
