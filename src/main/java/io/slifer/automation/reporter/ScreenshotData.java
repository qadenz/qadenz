package io.slifer.automation.reporter;

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
