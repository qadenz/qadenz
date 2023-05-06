package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to not be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class IsNotDayOfWeek implements Expectation<LocalDateTime> {
    
    private DayOfWeek dayOfWeek;
    
    public IsNotDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return Matchers.not(LocalDateTimeMatchers.isDayOfWeek(dayOfWeek));
    }
    
    @Override
    public String description() {
        return "is not day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
}
