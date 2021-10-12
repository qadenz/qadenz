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

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2}. The original class is protected and static which
 * prevented direct usage from within the Qadenz library. The original class is unchanged, apart from being converted to
 * a public class.
 * <p>
 * TestNG JavaDoc: Groups {@link TestResult}s by suite.
 *
 * @author TestNG
 */
public class SuiteResult {
    
    private final String suiteName;
    private final List<TestResult> testResults = Lists.newArrayList();
    private final ParallelMode mode;
    
    public SuiteResult(ISuite suite) {
        suiteName = suite.getName();
        mode = suite.getXmlSuite().getParallel();
        for (ISuiteResult suiteResult : suite.getResults().values()) {
            testResults.add(new TestResult(suiteResult.getTestContext()));
        }
    }
    
    public String getSuiteName() {
        return suiteName;
    }
    
    /**
     * @return the test results (possibly empty)
     */
    public List<TestResult> getTestResults() {
        return testResults;
    }
    
    public ParallelMode getParallelMode() {
        return mode;
    }
}
