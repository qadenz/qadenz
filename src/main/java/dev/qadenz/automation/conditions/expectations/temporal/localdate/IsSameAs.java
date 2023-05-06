package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be the same as the given LocalDate.
 *
 * @author Tim Slifer
 */
public class IsSameAs implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public IsSameAs(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.sameDay(localDate);
    }
    
    @Override
    public String description() {
        return "is the same as [" + localDate.toString() + "]";
    }
}
