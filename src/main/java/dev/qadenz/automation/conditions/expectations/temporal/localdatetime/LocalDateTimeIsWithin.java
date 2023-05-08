/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be within a timeframe of the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsWithin implements Expectation<LocalDateTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDateTime localDateTime;
    
    public LocalDateTimeIsWithin(Long period, ChronoUnit chronoUnit, LocalDateTime localDateTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.within(period, chronoUnit, localDateTime);
    }
    
    @Override
    public String description() {
        return "is within [" + period + " " + chronoUnit.toString() + "] of [" + localDateTime.toString() + "]";
    }
}
