package com.legacy.analysis;

import com.legacy.migration.MigrationProgressTracker;
import com.legacy.migration.MigrationProgressTracker.MigrationStatus;

// ... existing code ...

public class CodeTransformer {
    // ... existing code ...

    private MigrationProgressTracker migrationProgressTracker;

    public CodeTransformer() {
        // ... existing code ...
        this.migrationProgressTracker = new MigrationProgressTracker();
    }

    // ... existing code ...

    private void transformMethod(MethodDeclaration method) throws Exception {
        // ... existing code ...
        migrationProgressTracker.updateMigrationStatus(method.getNameAsString(), MigrationStatus.IN_PROGRESS);
        // ... existing code ...
        migrationProgressTracker.updateMigrationStatus(method.getNameAsString(), MigrationStatus.COMPLETED);
    }

    // ... existing code ...
}
