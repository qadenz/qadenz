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

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be not the same second as the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsNotSameSecondOfMinute implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public LocalTimeIsNotSameSecondOfMinute(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return not(LocalTimeMatchers.sameSecondOfMinute(localTime));
    }
    
    @Override
    public String description() {
        return "is not same second as [" + localTime.toString() + "]";
    }
}
