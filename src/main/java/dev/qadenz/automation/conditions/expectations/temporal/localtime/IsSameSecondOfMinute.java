package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be the same second as the given LocalTime.
 *
 * @author Tim Slifer
 */
public class IsSameSecondOfMinute implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public IsSameSecondOfMinute(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return LocalTimeMatchers.sameSecondOfMinute(localTime);
    }
    
    @Override
    public String description() {
        return "is same second as [" + localTime.toString() + "]";
    }
}
