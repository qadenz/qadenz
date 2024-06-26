/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.core.IsDayOfWeek;
import org.exparity.hamcrest.date.core.TemporalConverter;
import org.exparity.hamcrest.date.core.TemporalProvider;
import org.hamcrest.Matcher;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 * An expectation for the text of an element, represented as a Temporal value, to be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class TemporalIsDayOfWeek<T> implements Expectation<T> {
    
    private DayOfWeek dayOfWeek;
    private TemporalConverter<T, DayOfWeek> converter;
    private TemporalProvider<List<DayOfWeek>> provider;
    
    public TemporalIsDayOfWeek(DayOfWeek dayOfWeek, TemporalConverter<T, DayOfWeek> converter,
            TemporalProvider<List<DayOfWeek>> provider) {
        this.dayOfWeek = dayOfWeek;
        this.converter = converter;
        this.provider = provider;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsDayOfWeek<>(converter, provider);
    }
    
    @Override
    public String toString() {
        return "is day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
}
