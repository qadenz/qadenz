package dev.qadenz.automation.conditions.expectations.temporal.localdatetime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalDateTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * An expectation for the text of an element, represented as a LocalDateTime, to be within a timeframe of the given
 * LocalDateTime.
 *
 * @author Tim Slifer
 */
public class LocalDateTimeIsWithin implements Expectation<LocalDateTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalDateTime localDateTime;
    
    public LocalDateTimeIsWithin(Long period, ChronoUnit chronoUnit, LocalDateTime localDateTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localDateTime = localDateTime;
    }
    
    @Override
    public Matcher<LocalDateTime> matcher() {
        return LocalDateTimeMatchers.within(period, chronoUnit, localDateTime);
    }
    
    @Override
    public String description() {
        return "is within [" + period + " " + chronoUnit.toString() + "] of [" + localDateTime.toString() + "]";
    }
}
