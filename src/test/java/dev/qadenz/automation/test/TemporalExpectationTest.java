package dev.qadenz.automation.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalTime.NOON;

public class TemporalExpectationTest {
    
    public static final LocalDate AUG_04_2015 = LocalDate.of(2015, Month.AUGUST, 4);
    public static final LocalDate AUG_05_2015 = LocalDate.of(2015, Month.AUGUST, 5);
    public static final LocalDate AUG_06_2015 = LocalDate.of(2015, Month.AUGUST, 6);
    public static final LocalDate AUG_07_2015 = LocalDate.of(2015, Month.AUGUST, 7);
    
    public static final LocalDateTime AUG_04_2015_NOON = LocalDateTime.of(AUG_04_2015, NOON);
    public static final LocalDateTime AUG_05_2015_NOON = LocalDateTime.of(AUG_05_2015, NOON);
    public static final LocalDateTime AUG_06_2015_NOON = LocalDateTime.of(AUG_06_2015, NOON);
    public static final LocalDateTime AUG_07_2015_NOON = LocalDateTime.of(AUG_07_2015, NOON);
}
