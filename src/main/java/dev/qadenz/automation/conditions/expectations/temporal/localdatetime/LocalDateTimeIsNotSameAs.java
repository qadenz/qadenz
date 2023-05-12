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

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be not the same as the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsNotSameAs implements TemporalExpectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalDateTimeIsNotSameAs(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return not(LocalDateTimeMatchers.sameDay(localDateTime));
    }
    
    @Override
    public String description() {
        return "is the same as [" + localDateTime.format(dateTimeFormatter) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
