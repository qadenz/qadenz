/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import dev.qadenz.automation.conditions.expectations.bool.IsFalse;
import dev.qadenz.automation.conditions.expectations.bool.IsTrue;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsGreaterThan;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsGreaterThanOrEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsLessThan;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsLessThanOrEqualTo;
import dev.qadenz.automation.conditions.expectations.numeric.integer.IntegerIsNotEqualTo;
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
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsNotDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsNotSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsNotWithin;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsSameAsOrAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsSameAsOrBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localdate.LocalDateIsWithin;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsNotDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsNotSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsNotWithin;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsSameAsOrAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsSameAsOrBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localdatetime.LocalDateTimeIsWithin;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsNotSameHourOfDay;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsNotSameMinuteOfHour;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsNotSameSecondOfMinute;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsNotWithin;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsSameAsOrAfter;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsSameAsOrBefore;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsSameHourOfDay;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsSameMinuteOfHour;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsSameSecondOfMinute;
import dev.qadenz.automation.conditions.expectations.temporal.localtime.LocalTimeIsWithin;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

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
     * An Expectation for an integer value to be greater than the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThan(int value) {
        return new IntegerIsGreaterThan(value);
    }
    
    /**
     * An Expectation for an integer value to be greater than or equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThanOrEqualTo(int value) {
        return new IntegerIsGreaterThanOrEqualTo(value);
    }
    
    /**
     * An Expectation for an integer value to be equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isEqualTo(int value) {
        return new IntegerIsEqualTo(value);
    }
    
    /**
     * An Expectation for an integer value to be not equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isNotEqualTo(int value) {
        return new IntegerIsNotEqualTo(value);
    }
    
    /**
     * An Expectation for an integer value to be less than or equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThanOrEqualTo(int value) {
        return new IntegerIsLessThanOrEqualTo(value);
    }
    
    /**
     * An Expectation for an integer value to be less than the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThan(int value) {
        return new IntegerIsLessThan(value);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be after the given LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isAfter(LocalDate date) {
        return new LocalDateIsAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be after the given LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isAfter(LocalDateTime date) {
        return new LocalDateTimeIsAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be after the given LocalTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isAfter(LocalTime date) {
        return new LocalTimeIsAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or after the given
     * LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAsOrAfter(LocalDate date) {
        return new LocalDateIsSameAsOrAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
     * LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrAfter(LocalDateTime date) {
        return new LocalDateTimeIsSameAsOrAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or after the given
     * LocalTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameAsOrAfter(LocalTime date) {
        return new LocalTimeIsSameAsOrAfter(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as the given LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAs(LocalDate date) {
        return new LocalDateIsSameAs(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as the given
     * LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAs(LocalDateTime date) {
        return new LocalDateTimeIsSameAs(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be not the same as the given
     * LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotSameAs(LocalDate date) {
        return new LocalDateIsNotSameAs(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be not the same as the given
     * LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameAs(LocalDateTime date) {
        return new LocalDateTimeIsNotSameAs(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or before the given
     * LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAsOrBefore(LocalDate date) {
        return new LocalDateIsSameAsOrBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or before the given
     * LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrBefore(LocalDateTime date) {
        return new LocalDateTimeIsSameAsOrBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or before the given
     * LocalTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameAsOrBefore(LocalTime date) {
        return new LocalTimeIsSameAsOrBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be before the given LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isBefore(LocalDate date) {
        return new LocalDateIsBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be before the given LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isBefore(LocalDateTime date) {
        return new LocalDateTimeIsBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be before the given LocalTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isBefore(LocalTime date) {
        return new LocalTimeIsBefore(date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isWithin(Long period, ChronoUnit unit, LocalDate date) {
        return new LocalDateIsWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be within a timeframe of the given
     * LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isWithin(Long period, ChronoUnit unit, LocalDateTime date) {
        return new LocalDateTimeIsWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isWithin(Long period, ChronoUnit unit, LocalTime date) {
        return new LocalTimeIsWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotWithin(Long period, ChronoUnit unit, LocalDate date) {
        return new LocalDateIsNotWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to not be within a timeframe of the
     * given LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotWithin(Long period, ChronoUnit unit, LocalDateTime date) {
        return new LocalDateTimeIsNotWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotWithin(Long period, ChronoUnit unit, LocalTime date) {
        return new LocalTimeIsNotWithin(period, unit, date);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isDayOfWeekAsLocalDate(DayOfWeek dayOfWeek) {
        return new LocalDateIsDayOfWeek(dayOfWeek);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isDayOfWeekAsLocalDateTime(DayOfWeek dayOfWeek) {
        return new LocalDateTimeIsDayOfWeek(dayOfWeek);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotDayOfWeekAsLocalDate(DayOfWeek dayOfWeek) {
        return new LocalDateIsNotDayOfWeek(dayOfWeek);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to not be on the given day of the
     * week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotDayOfWeekAsLocalDateTime(DayOfWeek dayOfWeek) {
        return new LocalDateTimeIsNotDayOfWeek(dayOfWeek);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same hour as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameHourOfDay(LocalTime time) {
        return new LocalTimeIsSameHourOfDay(time);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same hour as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameHourOfDay(LocalTime time) {
        return new LocalTimeIsNotSameHourOfDay(time);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same minute as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameMinuteOfHour(LocalTime time) {
        return new LocalTimeIsSameMinuteOfHour(time);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same minute as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameMinuteOfHour(LocalTime time) {
        return new LocalTimeIsNotSameMinuteOfHour(time);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same second as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameSecondOfMinute(LocalTime time) {
        return new LocalTimeIsSameSecondOfMinute(time);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same second as the given
     * LocalTime.
     *
     * @param time The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameSecondOfMinute(LocalTime time) {
        return new LocalTimeIsNotSameSecondOfMinute(time);
    }
    
    /**
     * An Expectation for a String value to be equal to the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualTo(String text) {
        return new IsEqualTo(text);
    }
    
    /**
     * An Expectation for a String value to be equal to the given value, ignoring case.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> equalsIgnoreCase(String text) {
        return new EqualsIgnoreCase(text);
    }
    
    /**
     * An Expectation for a String value to be not equal to the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEqualTo(String text) {
        return new IsNotEqualTo(text);
    }
    
    /**
     * An Expectation for a String value to contain the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> contains(String text) {
        return new Contains(text);
    }
    
    /**
     * An Expectation for a String value to contain the given value, ignoring case.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> containsIgnoreCase(String text) {
        return new ContainsIgnoreCase(text);
    }
    
    /**
     * An Expectation for a String value to not contain the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotContain(String text) {
        return new DoesNotContain(text);
    }
    
    /**
     * An Expectation for a String value to start with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> startsWith(String text) {
        return new StartsWith(text);
    }
    
    /**
     * An Expectation for a String value to not start with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotStartWith(String text) {
        return new DoesNotStartWith(text);
    }
    
    /**
     * An Expectation for a String value to end with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> endsWith(String text) {
        return new EndsWith(text);
    }
    
    /**
     * An Expectation for a String value to not end with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotEndWith(String text) {
        return new DoesNotEndWith(text);
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
