package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be not the same second as the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class IsNotSameSecondOfMinute implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public IsNotSameSecondOfMinute(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return not(LocalTimeMatchers.sameSecondOfMinute(localTime));
    }
    
    @Override
    public String description() {
        return "is not same second as [" + localTime.toString() + "]";
    }
}
