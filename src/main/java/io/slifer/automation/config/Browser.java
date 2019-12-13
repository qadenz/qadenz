package io.slifer.automation.config;

import org.openqa.selenium.WebDriverException;

/**
 * Enumerated browser names for configuring execution sessions.
 *
 * @author Tim Slifer
 */
public enum Browser {
    CHROME("Chrome"),
    EDGE("Edge"),
    FIREFOX("Firefox"),
    INTERNET_EXPLORER("Internet Explorer");
    
    private String browser;
    
    Browser(String browser) {
        this.browser = browser;
    }
    
    public static Browser fromString(String name) {
        for (Browser browser : values()) {
            if (browser.toString().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        
        throw new WebDriverException("Unrecognized browser: " + name);
    }
    
    public String toString() {
        return browser;
    }
}
