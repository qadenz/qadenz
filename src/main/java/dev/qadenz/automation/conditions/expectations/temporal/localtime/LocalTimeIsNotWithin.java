/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsNotWithin implements Expectation<LocalTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalTime localTime;
    
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
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" + localTime.toString() + "]";
    }
}
