/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.config;

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
    
    private String name;
    
    Browser(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static Browser fromString(String name) {
        for (Browser browser : values()) {
            if (browser.getName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        
        throw new WebDriverException("Unrecognized browser: " + name);
    }
}
