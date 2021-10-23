/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.config;

import org.openqa.selenium.WebDriver;

/**
 * Provides a centralized location for accessibility to the {@link WebDriver}.
 *
 * @author Tim Slifer
 */
public class WebDriverProvider {
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static void setWebDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }
    
    public static WebDriver getWebDriver() {
        return driver.get();
    }
}
