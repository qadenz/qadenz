package dev.qadenz.automation.expectations.temporal.localdatetime;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or after the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsSameAsOrAfter implements Expectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    
    public LocalDateTimeIsSameAsOrAfter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.sameOrAfter(localDateTime);
    }
    
    @Override
    public String description() {
        return "is the same as or after [" + localDateTime.toString() + "]";
    }
}
