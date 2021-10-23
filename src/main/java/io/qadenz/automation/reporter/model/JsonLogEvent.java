/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.reporter.model;

/**
 * Models individual logging events as test steps, along with Base64 encoded screenshots of failures and errors.
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
