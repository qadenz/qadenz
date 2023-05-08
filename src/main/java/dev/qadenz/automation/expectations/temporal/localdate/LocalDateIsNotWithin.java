package dev.qadenz.automation.expectations.temporal.localdate;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalDateMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalDate, to not be within a timeframe of the given
 * LocalDate.
 *
 * @author Tim Slifer
 */
public class LocalDateIsNotWithin implements Expectation<LocalDate> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDate localDate;
    
    public LocalDateIsNotWithin(Long period, ChronoUnit chronoUnit, LocalDate localDate) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localDate = localDate;
    }
    
    @Override
    public Matcher<LocalDate> matcher() {
        return not(LocalDateMatchers.within(period, chronoUnit, localDate));
    }
    
    @Override
    public String description() {
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" + localDate.toString() + "]";
    }
}
