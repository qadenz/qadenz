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
