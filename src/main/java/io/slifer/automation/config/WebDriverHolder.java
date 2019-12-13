package io.slifer.automation.config;

import org.openqa.selenium.WebDriver;

/**
 * A simple class to hold onto a set of web drivers for the tests being executed.
 *
 * @author Matt Maurer
 */
public class WebDriverHolder {
    
    private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<>();
    
    public static WebDriver getWebDriver() {
        return webDrivers.get();
    }
    
    static void setWebDriver(WebDriver webDriver) {
        webDrivers.set(webDriver);
    }
}
