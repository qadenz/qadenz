package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be the same as or before the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class IsSameAsOrBefore implements Expectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    
    public IsSameAsOrBefore(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.sameOrBefore(localDateTime);
    }
    
    @Override
    public String description() {
        return "is the same as or before [" + localDateTime.toString() + "]";
    }
}
