package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be after the given LocalDate.
 *
 * @author Tim Slifer
 */
public class IsAfter implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public IsAfter(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.after(localDate);
    }
    
    @Override
    public String description() {
        return "is after [" + localDate.toString() + "]";
    }
}
