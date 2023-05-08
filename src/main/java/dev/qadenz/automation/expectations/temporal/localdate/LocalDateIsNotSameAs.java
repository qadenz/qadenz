package dev.qadenz.automation.expectations.temporal.localdate;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be not the same as the given LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsNotSameAs implements Expectation<LocalDate> {
    
    private LocalDate localDate;
    
    public LocalDateIsNotSameAs(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return not(LocalDateMatchers.sameDay(localDate));
    }
    
    @Override
    public String description() {
        return "is the same as [" + localDate.toString() + "]";
    }
}
