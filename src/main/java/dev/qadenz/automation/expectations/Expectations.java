/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.expectations;

import dev.qadenz.automation.conditions.expectations.bool.IsFalse;
import dev.qadenz.automation.conditions.expectations.bool.IsTrue;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsGreaterThan;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsGreaterThanOrEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsLessThan;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsLessThanOrEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.NumberIsNotEqualTo;
import dev.qadenz.automation.conditions.expectations.string.Contains;
import dev.qadenz.automation.conditions.expectations.string.ContainsIgnoreCase;
import dev.qadenz.automation.conditions.expectations.string.DoesNotContain;
import dev.qadenz.automation.conditions.expectations.string.DoesNotEndWith;
import dev.qadenz.automation.conditions.expectations.string.DoesNotStartWith;
import dev.qadenz.automation.conditions.expectations.string.EndsWith;
import dev.qadenz.automation.conditions.expectations.string.EqualsIgnoreCase;
import dev.qadenz.automation.conditions.expectations.string.IsEmptyOrNull;
import dev.qadenz.automation.conditions.expectations.string.IsEqualTo;
import dev.qadenz.automation.conditions.expectations.string.IsEqualToOneOf;
import dev.qadenz.automation.conditions.expectations.string.IsNotEmptyOrNull;
import dev.qadenz.automation.conditions.expectations.string.IsNotEqualTo;
import dev.qadenz.automation.conditions.expectations.string.StartsWith;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsAfter;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsBefore;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsEqualTo;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsEqualToOrAfter;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsEqualToOrBefore;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotEqualTo;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotWithin;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsWithin;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_DAYOFWEEK;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_LOCALDATETIME;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATE_AS_DAYOFWEEK;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATE_AS_LOCALDATE;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALTIME_AS_LOCALTIME;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALDATE;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALDATETIME;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALTIME;
import static org.exparity.hamcrest.date.core.TemporalProviders.daysOfWeek;
import static org.exparity.hamcrest.date.core.TemporalProviders.localDate;
import static org.exparity.hamcrest.date.core.TemporalProviders.localDateTime;
import static org.exparity.hamcrest.date.core.TemporalProviders.localTime;

public class Expectations {
    
    /**
     * An Expectation for a boolean outcome to be true.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isTrue() {
        return new IsTrue();
    }
    
    /**
     * An Expectation for a boolean outcome to be false.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isFalse() {
        return new IsFalse();
    }
    
    /**
     * An Expectation for a double value to be greater than the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isGreaterThan(Double expectedValue) {
        return new NumberIsGreaterThan<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be greater than the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isGreaterThan(Integer expectedValue) {
        return new NumberIsGreaterThan<>(expectedValue);
    }
    
    /**
     * An Expectation for a double value to be greater than or equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isGreaterThanOrEqualTo(Double expectedValue) {
        return new NumberIsGreaterThanOrEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be greater than or equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isGreaterThanOrEqualTo(Integer expectedValue) {
        return new NumberIsGreaterThanOrEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for a double value to be equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isEqualTo(Double expectedValue) {
        return new NumberIsEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isEqualTo(Integer expectedValue) {
        return new NumberIsEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for a double value to be not equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isNotEqualTo(Double expectedValue) {
        return new NumberIsNotEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be not equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isNotEqualTo(Integer expectedValue) {
        return new NumberIsNotEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for a double value to be less than or equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isLessThanOrEqualTo(Double expectedValue) {
        return new NumberIsLessThanOrEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be less than or equal to the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isLessThanOrEqualTo(Integer expectedValue) {
        return new NumberIsLessThanOrEqualTo<>(expectedValue);
    }
    
    /**
     * An Expectation for a double value to be less than the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Double> isLessThan(Double expectedValue) {
        return new NumberIsLessThan<>(expectedValue);
    }
    
    /**
     * An Expectation for an integer value to be less than the given value.
     *
     * @param expectedValue The value for comparison.
     *
     * @return The Expectation.
     */
    public static NumericExpectation<Integer> isLessThan(Integer expectedValue) {
        return new NumberIsLessThan<>(expectedValue);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be after the given LocalDate.
     *
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isAfter(LocalDate expectedLocalDate) {
        return new TemporalIsAfter<>(expectedLocalDate, LOCALDATE_AS_LOCALDATE, localDate(expectedLocalDate),
                LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be after the given LocalDateTime.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isAfter(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsAfter<>(expectedLocalDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be after the given LocalTime.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isAfter(LocalTime expectedLocalTime) {
        return new TemporalIsAfter<>(expectedLocalTime, LOCALTIME_AS_LOCALTIME, localTime(expectedLocalTime),
                LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or after the given
     * LocalDate.
     *
     * @param expectedLocaldate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isEqualToOrAfter(LocalDate expectedLocaldate) {
        return new TemporalIsEqualToOrAfter<>(expectedLocaldate, LOCALDATE_AS_LOCALDATE, localDate(expectedLocaldate),
                LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
     * LocalDateTime.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isEqualToOrAfter(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsEqualToOrAfter<>(expectedLocalDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or after the given
     * LocalTime.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isEqualToOrAfter(LocalTime expectedLocalTime) {
        return new TemporalIsEqualToOrAfter<>(expectedLocalTime, LOCALTIME_AS_LOCALTIME, localTime(expectedLocalTime),
                LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be equal to the given LocalDate.
     *
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isEqualTo(LocalDate expectedLocalDate) {
        return new TemporalIsEqualTo<>(expectedLocalDate);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be equal to the given LocalDate.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isEqualTo(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsEqualTo<>(expectedLocalDateTime);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be equal to the given LocalDate.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isEqualTo(LocalTime expectedLocalTime) {
        return new TemporalIsEqualTo<>(expectedLocalTime);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be not equal to the given LocalDate.
     *
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isNotEqualTo(LocalDate expectedLocalDate) {
        return new TemporalIsNotEqualTo<>(expectedLocalDate);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be not equal to the given LocalDate.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isNotEqualTo(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsNotEqualTo<>(expectedLocalDateTime);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be not equal to the given LocalDate.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isNotEqualTo(LocalTime expectedLocalTime) {
        return new TemporalIsNotEqualTo<>(expectedLocalTime);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or before the given
     * LocalDate.
     *
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isEqualToOrBefore(LocalDate expectedLocalDate) {
        return new TemporalIsEqualToOrBefore<>(expectedLocalDate, LOCALDATE_AS_LOCALDATE, localDate(expectedLocalDate),
                LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or before the given
     * LocalDateTime.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isEqualToOrBefore(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsEqualToOrBefore<>(expectedLocalDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or before the given
     * LocalTime.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isEqualTosOrBefore(LocalTime expectedLocalTime) {
        return new TemporalIsEqualToOrBefore<>(expectedLocalTime, LOCALTIME_AS_LOCALTIME, localTime(expectedLocalTime),
                LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be before the given LocalDate.
     *
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isBefore(LocalDate expectedLocalDate) {
        return new TemporalIsBefore<>(expectedLocalDate, LOCALDATE_AS_LOCALDATE, localDate(expectedLocalDate),
                LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be before the given LocalDateTime.
     *
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isBefore(LocalDateTime expectedLocalDateTime) {
        return new TemporalIsBefore<>(expectedLocalDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be before the given LocalTime.
     *
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isBefore(LocalTime expectedLocalTime) {
        return new TemporalIsBefore<>(expectedLocalTime, LOCALTIME_AS_LOCALTIME, localTime(expectedLocalTime),
                LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isWithin(long period, ChronoUnit unit, LocalDate expectedLocalDate) {
        return new TemporalIsWithin<>(expectedLocalDate, period, unit, LOCALDATE_AS_LOCALDATE,
                localDate(expectedLocalDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be within a timeframe of the given
     * LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isWithin(long period, ChronoUnit unit,
            LocalDateTime expectedLocalDateTime) {
        return new TemporalIsWithin<>(expectedLocalDateTime, period, unit, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isWithin(long period, ChronoUnit unit, LocalTime expectedLocalTime) {
        return new TemporalIsWithin<>(expectedLocalTime, period, unit, LOCALTIME_AS_LOCALTIME,
                localTime(expectedLocalTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isNotWithin(long period, ChronoUnit unit,
            LocalDate expectedLocalDate) {
        return new TemporalIsNotWithin<>(expectedLocalDate, period, unit, LOCALDATE_AS_LOCALDATE,
                localDate(expectedLocalDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to not be within a timeframe of the
     * given LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDateTime> isNotWithin(long period, ChronoUnit unit,
            LocalDateTime expectedLocalDateTime) {
        return new TemporalIsNotWithin<>(expectedLocalDateTime, period, unit, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(expectedLocalDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param expectedLocalTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalTime> isNotWithin(long period, ChronoUnit unit,
            LocalTime expectedLocalTime) {
        return new TemporalIsNotWithin<>(expectedLocalTime, period, unit, LOCALTIME_AS_LOCALTIME,
                localTime(expectedLocalTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be on the given day of the week.
     *
     * @param expectedDayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isDayOfWeekAsLocalDate(DayOfWeek expectedDayOfWeek) {
        return new TemporalIsDayOfWeek<>(expectedDayOfWeek, LOCALDATE_AS_DAYOFWEEK, daysOfWeek(expectedDayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be on the given day of the week.
     *
     * @param expectedDayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isDayOfWeekAsLocalDateTime(DayOfWeek expectedDayOfWeek) {
        return new TemporalIsDayOfWeek<>(expectedDayOfWeek, LOCALDATETIME_AS_DAYOFWEEK, daysOfWeek(expectedDayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be on the given day of the week.
     *
     * @param expectedDayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotDayOfWeekAsLocalDate(DayOfWeek expectedDayOfWeek) {
        return new TemporalIsNotDayOfWeek<>(expectedDayOfWeek, LOCALDATE_AS_DAYOFWEEK, daysOfWeek(expectedDayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to not be on the given day of the
     * week.
     *
     * @param expectedDayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotDayOfWeekAsLocalDateTime(DayOfWeek expectedDayOfWeek) {
        return new TemporalIsNotDayOfWeek<>(expectedDayOfWeek, LOCALDATETIME_AS_DAYOFWEEK,
                daysOfWeek(expectedDayOfWeek));
    }
    
    /**
     * An Expectation for a String value to be equal to the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualTo(String expectedText) {
        return new IsEqualTo(expectedText);
    }
    
    /**
     * An Expectation for a String value to be equal to the given value, ignoring case.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> equalsIgnoreCase(String expectedText) {
        return new EqualsIgnoreCase(expectedText);
    }
    
    /**
     * An Expectation for a String value to be not equal to the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEqualTo(String expectedText) {
        return new IsNotEqualTo(expectedText);
    }
    
    /**
     * An Expectation for a String value to contain the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> contains(String expectedText) {
        return new Contains(expectedText);
    }
    
    /**
     * An Expectation for a String value to contain the given value, ignoring case.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> containsIgnoreCase(String expectedText) {
        return new ContainsIgnoreCase(expectedText);
    }
    
    /**
     * An Expectation for a String value to not contain the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotContain(String expectedText) {
        return new DoesNotContain(expectedText);
    }
    
    /**
     * An Expectation for a String value to start with the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> startsWith(String expectedText) {
        return new StartsWith(expectedText);
    }
    
    /**
     * An Expectation for a String value to not start with the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotStartWith(String expectedText) {
        return new DoesNotStartWith(expectedText);
    }
    
    /**
     * An Expectation for a String value to end with the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> endsWith(String expectedText) {
        return new EndsWith(expectedText);
    }
    
    /**
     * An Expectation for a String value to not end with the given value.
     *
     * @param expectedText The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotEndWith(String expectedText) {
        return new DoesNotEndWith(expectedText);
    }
    
    /**
     * An Expectation for a String value to be equal to one of several possible options.
     *
     * @param options The group of possible options.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualToOneOf(String... options) {
        return new IsEqualToOneOf(options);
    }
    
    /**
     * An Expectation for a String value to be either empty or null.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEmptyOrNull() {
        return new IsEmptyOrNull();
    }
    
    /**
     * An Expectation for a String value to be neither empty nor null.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEmptyOrNull() {
        return new IsNotEmptyOrNull();
    }
}
