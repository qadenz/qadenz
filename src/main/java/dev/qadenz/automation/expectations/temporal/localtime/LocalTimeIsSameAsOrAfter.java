package dev.qadenz.automation.expectations.temporal.localtime;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be the same as or after the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsSameAsOrAfter implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public LocalTimeIsSameAsOrAfter(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return LocalTimeMatchers.sameOrAfter(localTime);
    }
    
    @Override
    public String description() {
        return "is the same as or after [" + localTime.toString() + "]";
    }
}
