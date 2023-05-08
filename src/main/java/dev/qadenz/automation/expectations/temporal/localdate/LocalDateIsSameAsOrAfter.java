package dev.qadenz.automation.expectations.temporal.localdate;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be the same as or after the given
 * LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsSameAsOrAfter implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public LocalDateIsSameAsOrAfter(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.sameOrAfter(localDate);
    }
    
    @Override
    public String description() {
        return "is the same as or after [" + localDate.toString() + "]";
    }
}
