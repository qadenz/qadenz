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
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsAfter;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotDayOfWeek;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotSameHourOfDay;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotSameMinuteOfHour;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotSameSecondOfMinute;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsNotWithin;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameAs;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameAsOrAfter;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameAsOrBefore;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameHourOfDay;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameMinuteOfHour;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsSameSecondOfMinute;
import dev.qadenz.automation.conditions.expectations.temporal.TemporalIsWithin;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_DAYOFWEEK;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_HOUR;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_LOCALDATE;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_LOCALDATETIME;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_MINUTE;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATETIME_AS_SECOND;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATE_AS_DAYOFWEEK;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALDATE_AS_LOCALDATE;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALTIME_AS_HOUR;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALTIME_AS_LOCALTIME;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALTIME_AS_MINUTE;
import static org.exparity.hamcrest.date.core.TemporalConverters.LOCALTIME_AS_SECOND;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALDATE;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALDATETIME;
import static org.exparity.hamcrest.date.core.TemporalFunctions.LOCALTIME;
import static org.exparity.hamcrest.date.core.TemporalProviders.daysOfWeek;
import static org.exparity.hamcrest.date.core.TemporalProviders.hour;
import static org.exparity.hamcrest.date.core.TemporalProviders.localDate;
import static org.exparity.hamcrest.date.core.TemporalProviders.localDateTime;
import static org.exparity.hamcrest.date.core.TemporalProviders.localTime;
import static org.exparity.hamcrest.date.core.TemporalProviders.minute;
import static org.exparity.hamcrest.date.core.TemporalProviders.second;

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
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static TemporalExpectation<LocalDate> isAfter(LocalDate localDate) {
        return new TemporalIsAfter<>(localDate, LOCALDATE_AS_LOCALDATE, localDate(localDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be after the given LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isAfter(LocalDateTime localDateTime) {
        return new TemporalIsAfter<>(localDateTime, LOCALDATETIME_AS_LOCALDATETIME, localDateTime(localDateTime),
                LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be after the given LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isAfter(LocalTime localTime) {
        return new TemporalIsAfter<>(localTime, LOCALTIME_AS_LOCALTIME, localTime(localTime), LOCALTIME);
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
        return new TemporalIsSameAsOrAfter<>(date, LOCALDATE_AS_LOCALDATE, localDate(date), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrAfter(LocalDateTime localDateTime) {
        return new TemporalIsSameAsOrAfter<>(localDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(localDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or after the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameAsOrAfter(LocalTime localTime) {
        return new TemporalIsSameAsOrAfter<>(localTime, LOCALTIME_AS_LOCALTIME, localTime(localTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as the given LocalDate.
     *
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAs(LocalDate localDate) {
        return new TemporalIsSameAs<>(localDate, LOCALDATE_AS_LOCALDATE, localDate(localDate));
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
        return new TemporalIsSameAs<>(date, LOCALDATETIME_AS_LOCALDATE, localDate(date));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be not the same as the given
     * LocalDate.
     *
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotSameAs(LocalDate localDate) {
        return new TemporalIsNotSameAs<>(localDate, LOCALDATE_AS_LOCALDATE, localDate(localDate));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be not the same as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameAs(LocalDateTime localDateTime) {
        return new TemporalIsNotSameAs<>(localDateTime, LOCALDATETIME_AS_LOCALDATE, localDate(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or before the given
     * LocalDate.
     *
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAsOrBefore(LocalDate localDate) {
        return new TemporalIsSameAsOrBefore<>(localDate, LOCALDATE_AS_LOCALDATE, localDate(localDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or before the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrBefore(LocalDateTime localDateTime) {
        return new TemporalIsSameAsOrBefore<>(localDateTime, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(localDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same as or before the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameAsOrBefore(LocalTime localTime) {
        return new TemporalIsSameAsOrBefore<>(localTime, LOCALTIME_AS_LOCALTIME, localTime(localTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be before the given LocalDate.
     *
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isBefore(LocalDate localDate) {
        return new TemporalIsAfter<>(localDate, LOCALDATE_AS_LOCALDATE, localDate(localDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be before the given LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isBefore(LocalDateTime localDateTime) {
        return new TemporalIsAfter<>(localDateTime, LOCALDATETIME_AS_LOCALDATETIME, localDateTime(localDateTime),
                LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be before the given LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isBefore(LocalTime localTime) {
        return new TemporalIsAfter<>(localTime, LOCALTIME_AS_LOCALTIME, localTime(localTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isWithin(Long period, ChronoUnit unit, LocalDate localDate) {
        return new TemporalIsWithin<>(localDate, period, unit, LOCALDATE_AS_LOCALDATE, localDate(localDate), LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be within a timeframe of the given
     * LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isWithin(Long period, ChronoUnit unit, LocalDateTime localDateTime) {
        return new TemporalIsWithin<>(localDateTime, period, unit, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(localDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isWithin(Long period, ChronoUnit unit, LocalTime localTime) {
        return new TemporalIsWithin<>(localTime, period, unit, LOCALTIME_AS_LOCALTIME, localTime(localTime), LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be within a timeframe of the given
     * LocalDate.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localDate The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotWithin(Long period, ChronoUnit unit, LocalDate localDate) {
        return new TemporalIsNotWithin<>(localDate, period, unit, LOCALDATE_AS_LOCALDATE, localDate(localDate),
                LOCALDATE);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to not be within a timeframe of the
     * given LocalDateTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotWithin(Long period, ChronoUnit unit, LocalDateTime localDateTime) {
        return new TemporalIsNotWithin<>(localDateTime, period, unit, LOCALDATETIME_AS_LOCALDATETIME,
                localDateTime(localDateTime), LOCALDATETIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
     * LocalTime.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotWithin(Long period, ChronoUnit unit, LocalTime localTime) {
        return new TemporalIsNotWithin<>(localTime, period, unit, LOCALTIME_AS_LOCALTIME, localTime(localTime),
                LOCALTIME);
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isDayOfWeekAsLocalDate(DayOfWeek dayOfWeek) {
        return new TemporalIsDayOfWeek<>(dayOfWeek, LOCALDATE_AS_DAYOFWEEK, daysOfWeek(dayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isDayOfWeekAsLocalDateTime(DayOfWeek dayOfWeek) {
        return new TemporalIsDayOfWeek<>(dayOfWeek, LOCALDATETIME_AS_DAYOFWEEK, daysOfWeek(dayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to not be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotDayOfWeekAsLocalDate(DayOfWeek dayOfWeek) {
        return new TemporalIsNotDayOfWeek<>(dayOfWeek, LOCALDATE_AS_DAYOFWEEK, daysOfWeek(dayOfWeek));
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
        return new TemporalIsNotDayOfWeek<>(dayOfWeek, LOCALDATETIME_AS_DAYOFWEEK, daysOfWeek(dayOfWeek));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same hour as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameHourOfDay(LocalDateTime localDateTime) {
        return new TemporalIsSameHourOfDay<>(localDateTime, LOCALDATETIME_AS_HOUR, hour(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same hour as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameHourOfDay(LocalTime localTime) {
        return new TemporalIsSameHourOfDay<>(localTime, LOCALTIME_AS_HOUR, hour(localTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be not the same hour as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameHourOfDay(LocalDateTime localDateTime) {
        return new TemporalIsNotSameHourOfDay<>(localDateTime, LOCALDATETIME_AS_HOUR, hour(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same hour as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameHourOfDay(LocalTime localTime) {
        return new TemporalIsNotSameHourOfDay<>(localTime, LOCALTIME_AS_HOUR, hour(localTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same minute as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameMinuteOfHour(LocalDateTime localDateTime) {
        return new TemporalIsSameMinuteOfHour<>(localDateTime, LOCALDATETIME_AS_MINUTE, minute(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same minute as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameMinuteOfHour(LocalTime localTime) {
        return new TemporalIsSameMinuteOfHour<>(localTime, LOCALTIME_AS_MINUTE, minute(localTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be not the same minute as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameMinuteOfHour(LocalDateTime localDateTime) {
        return new TemporalIsNotSameMinuteOfHour<>(localDateTime, LOCALDATETIME_AS_MINUTE, minute(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same minute as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameMinuteOfHour(LocalTime localTime) {
        return new TemporalIsNotSameMinuteOfHour<>(localTime, LOCALTIME_AS_MINUTE, minute(localTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same second as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameSecondOfMinute(LocalDateTime localDateTime) {
        return new TemporalIsSameSecondOfMinute<>(localDateTime, LOCALDATETIME_AS_SECOND, second(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be the same second as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isSameSecondOfMinute(LocalTime localTime) {
        return new TemporalIsSameSecondOfMinute<>(localTime, LOCALTIME_AS_SECOND, second(localTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be not the same second as the given
     * LocalDateTime.
     *
     * @param localDateTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameSecondOfMinute(LocalDateTime localDateTime) {
        return new TemporalIsNotSameSecondOfMinute<>(localDateTime, LOCALDATETIME_AS_SECOND, second(localDateTime));
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalTime, to be not the same second as the given
     * LocalTime.
     *
     * @param localTime The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalTime> isNotSameSecondOfMinute(LocalTime localTime) {
        return new TemporalIsNotSameSecondOfMinute<>(localTime, LOCALTIME_AS_SECOND, second(localTime));
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
