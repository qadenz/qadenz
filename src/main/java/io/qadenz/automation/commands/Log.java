/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides a means of adding a custom annotation to the reporting output, but to a Logger for the test itself, rather
 * than the Commands or Page Model. Both are similar in functionality, and together are intended to maximize flexibility
 * of this reporting feature.
 *
 * @author Tim Slifer
 */
public class Log {
    
    private static final Logger LOG = LoggerFactory.getLogger("Test");
    
    /**
     * Creates a log event at the WARN level, intended to provide a means to add high level notes to the logging/report
     * output, such as Labeling procedures, noting sections of tests, or adding information useful to interpreting
     * results.
     *
     * @param message The information to be added to the logs and report.
     */
    public static void annotate(String message) {
        LOG.warn(message);
    }
}
