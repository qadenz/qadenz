package dev.qadenz.automation.expectations.temporal.localtime;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be not the same minute as the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsNotSameMinuteOfHour implements Expectation<LocalTime> {
    
    private LocalTime localTime;
    
    public LocalTimeIsNotSameMinuteOfHour(LocalTime localTime) {
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return not(LocalTimeMatchers.sameMinuteOfHour(localTime));
    }
    
    @Override
    public String description() {
        return "is not same minute as [" + localTime.toString() + "]";
    }
}
