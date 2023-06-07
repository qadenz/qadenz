/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A convenience class that provides quick access to various loggers.
 *
 * @author Tim Slifer
 */
public class Loggers {
    
    public static Logger getSuiteLogger() {
        return LoggerFactory.getLogger("SUITE");
    }
    
    public static Logger getReporterLogger() {
        return LoggerFactory.getLogger("REPORTER");
    }
}
