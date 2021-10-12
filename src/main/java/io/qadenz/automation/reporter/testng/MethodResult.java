/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/

This file is derived from the org.testng.reporters.EmailableReporter2.java source 
file, available in the TestNG Library. 

The original work is Copyright Cedric Beust and the TestNG Team.

TestNG is licensed under the Apache 2.0 License (the "Apache License");
you may not use this file except in compliance with the Apache License.
A copy of the Apache License may be obtained at

http://www.apache.org/licenses/LICENSE-2.0
 */
package io.qadenz.automation.reporter.testng;

import org.testng.ITestResult;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2}. The original class is protected and static which
 * prevented direct usage from within the Qadenz library. The original class is unchanged, apart from being converted to
 * a public class.
 * <p>
 * TestNG JavaDoc: Groups test results by method.
 *
 * @author TestNG
 */
public class MethodResult {
    
    private final List<ITestResult> results;
    
    /**
     * @param results the non-null, non-empty result list
     */
    public MethodResult(List<ITestResult> results) {
        this.results = results;
    }
    
    /**
     * @return the non-null, non-empty result list
     */
    public List<ITestResult> getResults() {
        return results;
    }
}
