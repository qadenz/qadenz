package dev.qadenz.automation.expectations.temporal.localdatetime;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be not the same as the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsNotSameAs implements Expectation<LocalDateTime> {
    
    private LocalDateTime localDateTime;
    
    public LocalDateTimeIsNotSameAs(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return not(LocalDateTimeMatchers.sameDay(localDateTime));
    }
    
    @Override
    public String description() {
        return "is the same as [" + localDateTime.toString() + "]";
    }
}
