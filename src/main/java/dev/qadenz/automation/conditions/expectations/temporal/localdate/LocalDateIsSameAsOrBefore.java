package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be the same as or before the given
 * LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsSameAsOrBefore implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public LocalDateIsSameAsOrBefore(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.sameOrBefore(localDate);
    }
    
    @Override
    public String description() {
        return "is the same as or before [" + localDate.toString() + "]";
    }
}
