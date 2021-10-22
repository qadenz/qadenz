/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.config;

import org.openqa.selenium.Platform;

import java.time.LocalDateTime;

/**
 * Stores parameter values and other information useful to the test run.
 *
 * @author Tim Slifer
 */
public class WebConfig {
    
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
