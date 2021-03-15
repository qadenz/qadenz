package io.slifer.automation.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores information about the test run, and holds the major thread-safe components.
 *
 * @author Tim Slifer
 */
public class RunContext {
    
    public static String gridHost;
    public static Browser browser;
    public static String browserVersion;
    public static Platform platform;
    public static String applicationName;
    public static String appUrl;
    public static int timeout;
    public static boolean retryInterceptedClicks;
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> testCaseName = new ThreadLocal<>();
    private static ThreadLocal<String> testId = new ThreadLocal<>();
    
    public static final Logger SUITE_LOG = LoggerFactory.getLogger("SUITE");
    // public static final Logger TEST_LOG = LoggerFactory.getLogger("TEST");
    
    public static void setWebDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    
    public static WebDriver getWebDriver() {
        return driver.get();
    }
    
    public static void setTestCaseName(String name) {
        testCaseName.set(name);
    }
    
    public static String getTestCaseName() {
        return testCaseName.get();
    }
    
    public static void setTestId(String id) {
        testId.set(id);
    }
    
    public static String getTestId() {
        return testId.get();
    }
}
