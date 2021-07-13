package io.slifer.automation.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple means to track the running outcome of soft-assertions.
 *
 * @author Tim Slifer
 */
public class Assertions {
    
    private static final Logger LOG = LoggerFactory.getLogger(Assertions.class);
    
    private static ThreadLocal<Boolean> failures = new ThreadLocal<>();
    
    public static void init() {
        failures.set(false);
    }
    
    public static void setFailures(boolean failed) {
        failures.set(failed);
    }
    
    /**
     * Checks if a failure has been detected and, if so, aborts execution.
     */
    public static void flush() {
        if (failures.get()) {
            LOG.info("Purging soft assert failures - failures detected.");
            throw new AssertionError("One or more validations failed.");
        }
        LOG.info("Purging soft assert failures - none detected.");
    }
}
