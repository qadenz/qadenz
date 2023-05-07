package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class IsAfter implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public IsAfter(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return LocalTimeMatchers.after(localTime);
    }
    
    @Override
    public String description() {
        return "is after [" + localTime.toString() + "]";
    }
}
