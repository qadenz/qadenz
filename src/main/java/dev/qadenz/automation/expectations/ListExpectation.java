package dev.qadenz.automation.expectations;

import org.hamcrest.Matcher;

import java.util.List;

public interface ListExpectation {
    
    List<Matcher<String>> matchers();
    
    String toString();
    
    List<String> getExpectedValues();
}
