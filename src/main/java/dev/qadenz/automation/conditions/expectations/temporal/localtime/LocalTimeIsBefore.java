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

/**
 * An expectation for the text of an element, represented as a LocalTime, to be before the given LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsBefore implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public LocalTimeIsBefore(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return LocalTimeMatchers.before(localTime);
    }
    
    @Override
    public String description() {
        return "is before [" + localTime.toString() + "]";
    }
}
