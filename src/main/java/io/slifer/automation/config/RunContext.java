package io.slifer.automation.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

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
    public static String appUrl;
    public static int timeout;
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> testCaseName = new ThreadLocal<>();
    
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
}