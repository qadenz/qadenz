/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations;

import org.hamcrest.Matcher;

import java.util.List;

public interface ListExpectation {
    
    List<Matcher<String>> matchers();
    
    String toString();
    
    List<String> getExpectedValues();
}
