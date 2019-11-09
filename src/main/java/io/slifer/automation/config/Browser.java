package io.slifer.automation.config;

import org.openqa.selenium.WebDriverException;

public enum Browser {
    CHROME("Chrome"),
    EDGE("Edge"),
    FIREFOX("Firefox"),
    INTERNET_EXPLORER("Internet Explorer");
    
    private String browser;
    
    Browser(String browser) {
        this.browser = browser;
    }
    
    public String toString() {
        return browser;
    }
    
    public static Browser fromString(String name) {
        for (Browser browser : values()) {
            if (browser.toString().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        
        throw new WebDriverException("Unrecognized browser: " + name);
    }
}
