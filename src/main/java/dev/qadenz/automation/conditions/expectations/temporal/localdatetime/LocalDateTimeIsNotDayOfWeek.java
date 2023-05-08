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

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to not be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsNotDayOfWeek implements Expectation<LocalDateTime> {
    
    private DayOfWeek dayOfWeek;
    
    public LocalDateTimeIsNotDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return not(LocalDateTimeMatchers.isDayOfWeek(dayOfWeek));
    }
    
    @Override
    public String description() {
        return "is not day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
}
