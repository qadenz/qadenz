package io.slifer.automation.commands;

/**
 * A simple means to track the running outcome of soft-assertions.
 *
 * @author Tim Slifer
 */
public class Assertions {
    
    private static ThreadLocal<Boolean> failures = new ThreadLocal<>();
    
    public static void setFailures(boolean failed) {
        failures.set(failed);
    }
    
    /**
     * Checks if a failure has been detected and, if so, aborts execution.
     */
    public static void flush() {
        if (failures.get()) {
            throw new AssertionError("One or more validations failed.");
        }
    }
}
