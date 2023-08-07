/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.commands;

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
            LOG.info("Flushing soft assert failures - failures detected.");
            throw new AssertionError("One or more validations failed.");
        }
        LOG.info("Flushing soft assert failures - none detected.");
    }
}
