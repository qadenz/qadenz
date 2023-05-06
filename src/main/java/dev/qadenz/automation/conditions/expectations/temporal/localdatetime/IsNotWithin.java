package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to not be within a timeframe of the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class IsNotWithin implements Expectation<LocalDateTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDateTime localDateTime;
    
    public IsNotWithin(Long period, ChronoUnit chronoUnit, LocalDateTime localDateTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return Matchers.not(LocalDateTimeMatchers.within(period, chronoUnit, localDateTime));
    }
    
    @Override
    public String description() {
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" + localDateTime.toString() + "]";
    }
}
