/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.core.IsBefore;
import org.exparity.hamcrest.date.core.TemporalConverter;
import org.exparity.hamcrest.date.core.TemporalFunction;
import org.exparity.hamcrest.date.core.TemporalProvider;
import org.hamcrest.Matcher;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * An expectation for the text of an element, represented as a Temporal, to be before the given Temporal.
 *
 * @author Tim Slifer
 */
public class TemporalIsBefore<T, E> implements TemporalExpectation<T> {
    
    private Temporal temporal;
    private TemporalConverter<T, E> converter;
    private TemporalProvider<E> provider;
    private TemporalFunction<E> function;
    
    private DateTimeFormatter dateTimeFormatter;
    
    public TemporalIsBefore(Temporal temporal, TemporalConverter<T, E> converter, TemporalProvider<E> provider,
            TemporalFunction<E> function) {
        this.temporal = temporal;
        this.converter = converter;
        this.provider = provider;
        this.function = function;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsBefore<>(converter, provider, function);
    }
    
    @Override
    public String description() {
        return "is before [" + dateTimeFormatter.format(temporal) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}