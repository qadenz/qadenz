/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsNotWithin implements TemporalExpectation<LocalTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalTime localTime;
    private DateTimeFormatter dateTimeFormatter;
    
    public LocalTimeIsNotWithin(Long period, ChronoUnit chronoUnit, LocalTime localTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return not(LocalTimeMatchers.within(period, chronoUnit, localTime));
    }
    
    @Override
    public String description() {
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" +
                localTime.format(dateTimeFormatter) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}