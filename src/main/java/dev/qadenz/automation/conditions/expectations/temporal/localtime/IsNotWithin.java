package dev.qadenz.automation.conditions.expectations.temporal.localtime;

import dev.qadenz.automation.conditions.Expectation;
import org.exparity.hamcrest.date.LocalTimeMatchers;
import org.hamcrest.Matcher;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.not;

/**
 * An expectation for the text of an element, represented as a LocalTime, to not be within a timeframe of the given
 * LocalTime.
 *
 * @author Tim Slifer
 */
public class IsNotWithin implements Expectation<LocalTime> {
    
    private Long period;
    private ChronoUnit chronoUnit;
    private LocalTime localTime;
    
    public IsNotWithin(Long period, ChronoUnit chronoUnit, LocalTime localTime) {
        this.period = period;
        this.chronoUnit = chronoUnit;
        this.localTime = localTime;
    }
    
    @Override
    public Matcher<LocalTime> matcher() {
        return not(LocalTimeMatchers.within(period, chronoUnit, localTime));
    }
    
    @Override
    public String description() {
        return "is not within [" + period + " " + chronoUnit.toString() + "] of [" + localTime.toString() + "]";
    }
}
