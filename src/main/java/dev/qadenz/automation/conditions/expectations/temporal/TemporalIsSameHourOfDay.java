/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions.expectations.temporal;

import dev.qadenz.automation.conditions.TemporalExpectation;
import org.exparity.hamcrest.date.core.IsHour;
import org.exparity.hamcrest.date.core.TemporalConverter;
import org.exparity.hamcrest.date.core.TemporalProvider;
import org.exparity.hamcrest.date.core.types.Hour;
import org.hamcrest.Matcher;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * An expectation for the text of an element, represented as a Temporal, to be the same hour as the given Temporal.
 *
 * @author Tim Slifer
 */
public class TemporalIsSameHourOfDay<T> implements TemporalExpectation<T> {
    
    private Temporal temporal;
    private TemporalConverter<T, Hour> converter;
    private TemporalProvider<Hour> provider;
    
    private DateTimeFormatter dateTimeFormatter;
    
    public TemporalIsSameHourOfDay(Temporal temporal, TemporalConverter<T, Hour> converter,
            TemporalProvider<Hour> provider) {
        this.temporal = temporal;
        this.converter = converter;
        this.provider = provider;
    }
    
    @Override
    public Matcher<T> matcher() {
        return new IsHour<>(converter, provider);
    }
    
    @Override
    public String description() {
        return "is same hour as [" + dateTimeFormatter.format(temporal) + "]";
    }
    
    @Override
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}
