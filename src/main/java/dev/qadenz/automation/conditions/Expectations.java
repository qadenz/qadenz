/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import org.exparity.hamcrest.date.LocalDateMatchers;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class Expectations {
    
    /**
     * An Expectation for a boolean outcome to be true.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isTrue() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Boolean> matcher() {
                return Matchers.is(true);
            }
            
            @Override
            public String description() {
                return "is TRUE";
            }
        };
    }
    
    /**
     * An Expectation for a boolean outcome to be false.
     *
     * @return The Expectation.
     */
    public static Expectation<Boolean> isFalse() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Boolean> matcher() {
                return Matchers.is(false);
            }
            
            @Override
            public String description() {
                return "is FALSE";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThan(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.greaterThan(value);
            }
            
            @Override
            public String description() {
                return "is greater than [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be greater than or equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isGreaterThanOrEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.greaterThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is greater than or equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.equalTo(value);
            }
            
            @Override
            public String description() {
                return "is equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be not equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isNotEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return not(Matchers.equalTo(value));
            }
            
            @Override
            public String description() {
                return "is not equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than or equal to the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThanOrEqualTo(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.lessThanOrEqualTo(value);
            }
            
            @Override
            public String description() {
                return "is less than or equal to [" + value + "]";
            }
        };
    }
    
    /**
     * An Expectation for an integer value to be less than the given value.
     *
     * @param value The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<Integer> isLessThan(final int value) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<Integer> matcher() {
                return Matchers.lessThan(value);
            }
            
            @Override
            public String description() {
                return "is less than [" + value + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be after the given LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isAfter(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.after(date);
            }
            
            @Override
            public String description() {
                return "is after [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be the same as or after the given
     * LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAsOrAfter(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.sameOrAfter(date);
            }
            
            @Override
            public String description() {
                return "is the same as or after [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be the same as the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAs(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.sameDay(date);
            }
            
            @Override
            public String description() {
                return "is the same as [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be not the same as the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotSameAs(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return not(LocalDateMatchers.sameDay(date));
            }
            
            @Override
            public String description() {
                return "is the same as [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be the same as or before the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isSameAsOrBefore(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.sameOrBefore(date);
            }
            
            @Override
            public String description() {
                return "is the same as or before [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be before the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isBefore(final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.before(date);
            }
            
            @Override
            public String description() {
                return "is before [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be within a timeframe of the given Date.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isWithin(final Long period, final ChronoUnit unit, final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.within(period, unit, date);
            }
            
            @Override
            public String description() {
                return "is within [" + period + " " + unit.toString() + "] of [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to not be within a timeframe of the given
     * Date.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotWithin(final Long period, final ChronoUnit unit, final LocalDate date) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return Matchers.not(LocalDateMatchers.within(period, unit, date));
            }
            
            @Override
            public String description() {
                return "is not within [" + period + " " + unit.toString() + "] of [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isDayOfWeek(final DayOfWeek dayOfWeek) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return LocalDateMatchers.isDayOfWeek(dayOfWeek);
            }
            
            @Override
            public String description() {
                return "is day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to not be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDate> isNotDayOfWeek(final DayOfWeek dayOfWeek) {
        
        return new Expectation<LocalDate>() {
            
            @Override
            public Matcher<LocalDate> matcher() {
                return Matchers.not(LocalDateMatchers.isDayOfWeek(dayOfWeek));
            }
            
            @Override
            public String description() {
                return "is not day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDate, to be after the given LocalDate.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isAfter(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.after(date);
            }
            
            @Override
            public String description() {
                return "is after [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
     * LocalDateTime.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrAfter(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.sameOrAfter(date);
            }
            
            @Override
            public String description() {
                return "is the same as or after [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be the same as the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAs(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.sameDay(date);
            }
            
            @Override
            public String description() {
                return "is the same as [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be not the same as the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotSameAs(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return not(LocalDateTimeMatchers.sameDay(date));
            }
            
            @Override
            public String description() {
                return "is the same as [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be the same as or before the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isSameAsOrBefore(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.sameOrBefore(date);
            }
            
            @Override
            public String description() {
                return "is the same as or before [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be before the given Date.
     *
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isBefore(final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.before(date);
            }
            
            @Override
            public String description() {
                return "is before [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be within a timeframe of the given Date.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isWithin(final Long period, final ChronoUnit unit,
            final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.within(period, unit, date);
            }
            
            @Override
            public String description() {
                return "is within [" + period + " " + unit.toString() + "] of [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to not be within a timeframe of the given
     * Date.
     *
     * @param period The number of ChronoUnits within the allowable range.
     * @param unit The unit of time.
     * @param date The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotWithin(final Long period, final ChronoUnit unit,
            final LocalDateTime date) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return Matchers.not(LocalDateTimeMatchers.within(period, unit, date));
            }
            
            @Override
            public String description() {
                return "is not within [" + period + " " + unit.toString() + "] of [" + date.toString() + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isDayOfWeek(final DayOfWeek dayOfWeek) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return LocalDateTimeMatchers.isDayOfWeek(dayOfWeek);
            }
            
            @Override
            public String description() {
                return "is day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
            }
        };
    }
    
    /**
     * An expectation for the text of an element, represented as a Date, to not be on the given day of the week.
     *
     * @param dayOfWeek The formatted date value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<LocalDateTime> isNotDayOfWeek(final DayOfWeek dayOfWeek) {
        
        return new Expectation<LocalDateTime>() {
            
            @Override
            public Matcher<LocalDateTime> matcher() {
                return Matchers.not(LocalDateTimeMatchers.isDayOfWeek(dayOfWeek));
            }
            
            @Override
            public String description() {
                return "is not day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualTo(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.is(text);
            }
            
            @Override
            public String description() {
                return "is equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to the given value, ignoring case.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> equalsIgnoreCase(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.equalToIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "is, ignoring case, equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be not equal to the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEqualTo(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.not(text);
            }
            
            @Override
            public String description() {
                return "is not equal to [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> contains(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.containsString(text);
            }
            
            @Override
            public String description() {
                return "contains [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to contain the given value, ignoring case.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> containsIgnoreCase(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.containsStringIgnoringCase(text);
            }
            
            @Override
            public String description() {
                return "ignoring case, contains [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not contain the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotContain(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.containsString(text));
            }
            
            @Override
            public String description() {
                return "does not contain [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to start with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> startsWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.startsWith(text);
            }
            
            @Override
            public String description() {
                return "starts with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not start with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotStartWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.startsWith(text));
            }
            
            @Override
            public String description() {
                return "does not start with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to end with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> endsWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.endsWith(text);
            }
            
            @Override
            public String description() {
                return "ends with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to not end with the given value.
     *
     * @param text The value for comparison.
     *
     * @return The Expectation.
     */
    public static Expectation<String> doesNotEndWith(final String text) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return not(Matchers.endsWith(text));
            }
            
            @Override
            public String description() {
                return "does not end with [" + text + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be equal to one of several possible options.
     *
     * @param options The group of possible options.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEqualToOneOf(final String... options) {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                List<Matcher<String>> matchers = new ArrayList<>();
                for (String option : options) {
                    matchers.add(Matchers.is(option));
                }
                
                return Matchers.anyOf(matchers.toArray(new Matcher[matchers.size()]));
            }
            
            @Override
            public String description() {
                return "is equal to one of [" + Arrays.toString(options) + "]";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be either empty or null.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isEmptyOrNull() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.is(emptyOrNullString());
            }
            
            @Override
            public String description() {
                return "is null or empty";
            }
        };
    }
    
    /**
     * An Expectation for a String value to be neither empty nor null.
     *
     * @return The Expectation.
     */
    public static Expectation<String> isNotEmptyOrNull() {
        
        return new Expectation<>() {
            
            @Override
            public Matcher<String> matcher() {
                return Matchers.not(emptyOrNullString());
            }
            
            @Override
            public String description() {
                return "is neither null nor empty";
            }
        };
    }
}
