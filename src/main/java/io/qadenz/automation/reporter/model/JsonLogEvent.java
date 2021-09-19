package io.qadenz.automation.reporter.model;

/**
 * Models logging output for test cases, and stores Base64 encoded screenshots.
 *
 * @author Tim Slifer
 */
public class JsonLogEvent {
    
    private String logMessage;
    private String screenshot;
    
    public JsonLogEvent(String logMessage, String screenshot) {
        this.logMessage = logMessage;
        this.screenshot = screenshot;
    }
    
    public String getLogMessage() {
        return logMessage;
    }
    
    public String getScreenshot() {
        return screenshot;
    }
}
