package dev.qadenz.automation.expectations.temporal.localdate;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDate, to not be on the given day of the week.
 *
 * @author Tim Slifer
 */
public class LocalDateIsNotDayOfWeek implements Expectation<LocalDate> {
    
    private DayOfWeek dayOfWeek;
    
    public LocalDateIsNotDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return not(LocalDateMatchers.isDayOfWeek(dayOfWeek));
    }
    
    @Override
    public String description() {
        return "is not day of week [" + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + "]";
    }
}
