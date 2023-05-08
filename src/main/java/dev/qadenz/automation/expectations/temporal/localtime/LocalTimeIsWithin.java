package dev.qadenz.automation.expectations.temporal.localtime;

import dev.qadenz.automation.expectations.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * An expectation for the text of an element, represented as a LocalTime, to be within a timeframe of the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class LocalTimeIsWithin implements Expectation<LocalTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalTime localTime;
    
    public LocalTimeIsWithin(Long period, ChronoUnit chronoUnit, LocalTime localTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return LocalTimeMatchers.within(period, chronoUnit, localTime);
    }
    
    @Override
    public String description() {
        return "is within [" + period + " " + chronoUnit.toString() + "] of [" + localTime.toString() + "]";
    }
}
