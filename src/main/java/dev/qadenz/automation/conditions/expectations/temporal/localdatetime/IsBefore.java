package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be before the given LocalDateTime.
 *
 * @author Tim Slifer
 */
public class IsBefore implements Expectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    
    public IsBefore(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.before(localDateTime);
    }
    
    @Override
    public String description() {
        return "is before [" + localDateTime.toString() + "]";
    }
}
