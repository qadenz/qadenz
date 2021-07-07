package io.slifer.automation.config;

import org.openqa.selenium.Platform;

import java.time.LocalDateTime;

/**
 * Stores information about the test run, and holds the major thread-safe components.
 *
 * @author Tim Slifer
 */
public class RunContext {
    
    public static LocalDateTime suiteStartDate;
    public static LocalDateTime suiteEndDate;
    
    public static String gridHost;
    public static Browser browser;
    public static String browserVersion;
    public static Platform platform;
    public static String applicationName;
    public static String appUrl;
    public static int timeout;
    public static boolean retryInterceptedClicks;
}
