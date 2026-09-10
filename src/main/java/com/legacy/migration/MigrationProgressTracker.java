package com.legacy.migration;

/**
 * Tracks migration progress across the codebase.
 */
public class MigrationProgressTracker {
    private int totalTasks;
    private int completedTasks;

    public MigrationProgressTracker() {
        this.totalTasks = 0;
        this.completedTasks = 0;
    }

    /**
     * Sets the total number of migration tasks to be performed.
     *
     * @param totalTasks total task count
     */
    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    /**
     * Increments the count of completed tasks.
     */
    public void incrementProgress() {
        completedTasks++;
    }

    /**
     * Returns the migration progress as a percentage.
     *
     * @return progress percentage (0.0 – 100.0)
     */
    public double getProgressPercentage() {
        if (totalTasks == 0) {
            return 0.0;
        }
        return (completedTasks * 100.0) / totalTasks;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getTotalTasks() {
        return totalTasks;
    }
}
