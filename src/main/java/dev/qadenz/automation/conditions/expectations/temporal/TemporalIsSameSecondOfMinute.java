/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.core.IsSecond;
import org.exparity.hamcrest.date.core.TemporalConverter;
import org.exparity.hamcrest.date.core.TemporalProvider;
import org.exparity.hamcrest.date.core.types.Second;
import org.hamcrest.Matcher;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * An expectation for the text of an element, represented as a Temporal, to be the same second as the given Temporal.
 *
 * @author Tim Slifer
 */
public class TemporalIsSameSecondOfMinute<T> implements TemporalExpectation<T> {
    
    private Temporal temporal;
    private TemporalConverter<T, Second> converter;
    private TemporalProvider<Second> provider;
    
    private DateTimeFormatter dateTimeFormatter;
    
    public TemporalIsSameSecondOfMinute(Temporal temporal, TemporalConverter<T, Second> converter,
            TemporalProvider<Second> provider) {
        this.temporal = temporal;
        this.converter = converter;
        this.provider = provider;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsSecond<>(converter, provider);
    }
    
    @Override
    public String description() {
        return "is same second as [" + dateTimeFormatter.format(temporal) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
