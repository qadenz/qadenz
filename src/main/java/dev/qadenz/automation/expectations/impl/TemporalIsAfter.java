/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations.impl;

import dev.qadenz.automation.expectations.TemporalExpectation;
import org.exparity.hamcrest.date.core.IsAfter;
import org.exparity.hamcrest.date.core.TemporalConverter;
import org.exparity.hamcrest.date.core.TemporalFunction;
import org.exparity.hamcrest.date.core.TemporalProvider;
import org.hamcrest.Matcher;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * An expectation for the text of an element, represented as a Temporal 'actual' value, to be after the given Temporal
 * 'expected' value.
 *
 * @author Tim Slifer
 */
public class TemporalIsAfter<T, E> implements TemporalExpectation<T> {
    
    private Temporal temporal;
    private TemporalConverter<T, E> converter;
    private TemporalProvider<E> provider;
    private TemporalFunction<E> function;
    
    private DateTimeFormatter dateTimeFormatter;
    
    public TemporalIsAfter(Temporal temporal, TemporalConverter<T, E> converter, TemporalProvider<E> provider,
            TemporalFunction<E> function) {
        this.temporal = temporal;
        this.converter = converter;
        this.provider = provider;
        this.function = function;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsAfter<>(converter, provider, function);
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    
    @Override
    public String toString() {
        return "is after [" + dateTimeFormatter.format(temporal) + "]";
    }
}
