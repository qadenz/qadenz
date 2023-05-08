package dev.qadenz.automation.expectations.temporal.localdate;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be before the given LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsBefore implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public LocalDateIsBefore(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.before(localDate);
    }
    
    @Override
    public String description() {
        return "is before [" + localDate.toString() + "]";
    }
}
