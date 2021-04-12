package io.slifer.automation.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * Stores information about the test run, and holds the major thread-safe components.
 *
 * @author Tim Slifer
 */
public class RunContext {
    
    public static LocalDateTime suiteStartDate;
    public static LocalDateTime suiteEndDate;
    
    public static String reportOutputPath;
    
    public static String gridHost;
    public static Browser browser;
    public static String browserVersion;
    public static Platform platform;
    public static String applicationName;
    public static String appUrl;
    public static int timeout;
    public static boolean retryInterceptedClicks;
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> testId = new ThreadLocal<>();
    
    public static final Logger SUITE_LOG = LoggerFactory.getLogger("SUITE");
    
    public static void setWebDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    
    public static WebDriver getWebDriver() {
        return driver.get();
    }
    
    public static void setTestId(String id) {
        testId.set(id);
    }
    
    public static String getTestId() {
        return testId.get();
    }
}
