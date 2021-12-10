/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/

This file is derived from the org.testng.reporters.EmailableReporter2.java source 
file, available in the TestNG Library. 

The original work is Copyright Cedric Beust and the TestNG Team.

TestNG is licensed under the Apache License, Version 2.0 (the "Apache License");
you may not use this file except in compliance with the Apache License.
A copy of the Apache License may be obtained at

http://www.apache.org/licenses/LICENSE-2.0
 */
package dev.qadenz.automation.reporter.testng;

import java.util.List;

/**
 * Copied from {@link org.testng.reporters.EmailableReporter2}. The original class is protected and static which
 * prevented direct usage from within the Qadenz library. The original class is unchanged, apart from being converted to
 * a public class.
 * <p>
 * TestNG JavaDoc: Groups {@link MethodResult}s by class.
 *
 * @author TestNG
 */
public class ClassResult {
    
    private final String className;
    private final List<MethodResult> methodResults;
    
    /**
     * @param className the class name
     * @param methodResults the non-null, non-empty {@link MethodResult} list
     */
    public ClassResult(String className, List<MethodResult> methodResults) {
        this.className = className;
        this.methodResults = methodResults;
    }
    
    public String getClassName() {
        return className;
    }
    
    /**
     * @return the non-null, non-empty {@link MethodResult} list
     */
    public List<MethodResult> getMethodResults() {
        return methodResults;
    }
}
