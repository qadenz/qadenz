/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.TemporalExpectation;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * An expectation for the text of an element, represented as a Temporal 'actual' value, to be not equal to the given
 * Temporal 'expected' value.
 *
 * @author Tim Slifer
 */
public class TemporalIsNotEqualTo<T extends Temporal> implements TemporalExpectation<T> {
    
    private T temporal;
    
    private DateTimeFormatter dateTimeFormatter;
    
    public TemporalIsNotEqualTo(T temporal) {
        this.temporal = temporal;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsNot<>(new IsEqual<>(temporal));
    }
    
    @Override
    public String description() {
        return "is not equal to [" + dateTimeFormatter.format(temporal) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
