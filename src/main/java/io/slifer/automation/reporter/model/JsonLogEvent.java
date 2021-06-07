package io.slifer.automation.reporter.model;

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
