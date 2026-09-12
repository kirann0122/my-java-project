package com.legacy.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple monitoring service that logs operation durations.
 */
public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    /**
     * Marks the start of an operation.
     *
     * @param operationName name of the operation
     * @return start time in nanoseconds
     */
    public long start(String operationName) {
        logger.info("Operation '{}' started.", operationName);
        return System.nanoTime();
    }

    /**
     * Marks the end of an operation and logs the elapsed time.
     *
     * @param operationName name of the operation
     * @param startTime     start time returned by {@link #start(String)}
     */
    public void end(String operationName, long startTime) {
        long durationNs = System.nanoTime() - startTime;
        double durationMs = durationNs / 1_000_000.0;
        logger.info("Operation '{}' completed in {} ms.", operationName, String.format("%.3f", durationMs));
    }
}
