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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class LocalDateIsDayOfWeek implements TemporalExpectation<LocalDate> {
    
    private DayOfWeek dayOfWeek;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalDateIsDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.isDayOfWeek(dayOfWeek);
    }
    
    @Override
    public String description() {
        return "is day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
