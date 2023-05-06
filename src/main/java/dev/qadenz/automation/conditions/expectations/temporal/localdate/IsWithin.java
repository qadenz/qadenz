package dev.qadenz.automation.conditions.expectations.temporal.localdate;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * An expectation for the text of an element, represented as a LocalDate, to be within a timeframe of the given
 * LocalDate.
 *
 * @author Tim Slifer
 */
public class IsWithin implements Expectation<LocalDate> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDate localDate;
    
    public IsWithin(Long period, ChronoUnit chronoUnit, LocalDate localDate) {
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return LocalDateMatchers.within(period, chronoUnit, localDate);
    }
    
    @Override
    public String description() {
        return "is within [" + period + " " + chronoUnit.toString() + "] of [" + localDate.toString() + "]";
    }
}
