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
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDate, to not be within a timeframe of the given
 * LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsNotWithin implements TemporalExpectation<LocalDate> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDate localDate;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalDateIsNotWithin(Long period, ChronoUnit chronoUnit, LocalDate localDate) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return not(LocalDateMatchers.within(period, chronoUnit, localDate));
    }
    
    @Override
    public String description() {
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" +
                localDate.format(dateTimeFormatter) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
