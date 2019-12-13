package io.slifer.automation.config;

import org.openqa.selenium.Platform;

/**
 * Stores information about the test run, and holds the major thread-safe components.
 *
 * @author Tim Slifer
 */
public class RunContext {
    
    static String gridHost;
    static Browser browser;
    static String browserVersion;
    static Platform platform;
    static String appUrl;
    public static int timeout;
    
    private static ThreadLocal<String> testCaseName = new ThreadLocal<>();
    
    public static String getTestCaseName() {
        return testCaseName.get();
    }
    
    static void setTestCaseName(String name) {
        testCaseName.set(name);
    }
}
