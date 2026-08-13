package com.legacy.migration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MigrationProgressTracker {
    private List<MigrationProgress> migrationProgressList;

    public MigrationProgressTracker() {
        this.migrationProgressList = new ArrayList<>();
    }

    public void updateMigrationStatus(String methodName, MigrationStatus status) {
        MigrationProgress migrationProgress = new MigrationProgress(methodName, status, LocalDateTime.now());
        migrationProgressList.add(migrationProgress);
    }

    public List<MigrationProgress> getMigrationProgressList() {
        return migrationProgressList;
    }

    public enum MigrationStatus {
        IN_PROGRESS,
        COMPLETED,
        FAILED
    }

    public static class MigrationProgress {
        private String methodName;
        private MigrationStatus status;
        private LocalDateTime timestamp;

        public MigrationProgress(String methodName, MigrationStatus status, LocalDateTime timestamp) {
            this.methodName = methodName;
            this.status = status;
            this.timestamp = timestamp;
        }

        public String getMethodName() {
            return methodName;
        }

        public MigrationStatus getStatus() {
            return status;
        }

        public String getTimestamp() {
            return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
