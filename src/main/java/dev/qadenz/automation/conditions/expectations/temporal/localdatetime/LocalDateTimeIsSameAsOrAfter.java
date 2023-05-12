/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsSameAsOrAfter implements TemporalExpectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalDateTimeIsSameAsOrAfter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.sameOrAfter(localDateTime);
    }
    
    @Override
    public String description() {
        return "is the same as or after [" + localDateTime.format(dateTimeFormatter) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
