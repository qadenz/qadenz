/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be before the given LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsBefore implements TemporalExpectation<LocalDate> {
    
    private LocalDate localDate;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalDateIsBefore(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.before(localDate);
    }
    
    @Override
    public String description() {
        return "is before [" + localDate.format(dateTimeFormatter) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
