/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.reporter;

import java.util.HashMap;

/**
 * Stores Base64 encoded screenshots and unique identifiers for retrieval by the reporter.
 *
 * @author Tim Slifer
 */
public class ScreenshotData extends HashMap<String, String> {
    
    private static ScreenshotData instance;
    
    private ScreenshotData() {
        // Singleton
    }
    
    public static ScreenshotData getInstance() {
        if (instance == null) {
            instance = new ScreenshotData();
        }
        
        return instance;
    }
}
