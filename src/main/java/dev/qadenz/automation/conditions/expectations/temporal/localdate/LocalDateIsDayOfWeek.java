package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class LocalDateIsDayOfWeek implements Expectation<LocalDate> {
    
    private DayOfWeek dayOfWeek;
    
    public LocalDateIsDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.isDayOfWeek(dayOfWeek);
    }
    
    @Override
    public String description() {
        return "is day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
}
