/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.test.expectations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalTime.NOON;

public class TemporalExpectationTest {
    
    public static final LocalDate AUG_01_2015 = LocalDate.of(2015, Month.AUGUST, 1);
    public static final LocalDate AUG_02_2015 = LocalDate.of(2015, Month.AUGUST, 2);
    public static final LocalDate AUG_03_2015 = LocalDate.of(2015, Month.AUGUST, 3);
    public static final LocalDate AUG_04_2015 = LocalDate.of(2015, Month.AUGUST, 4);
    public static final LocalDate AUG_05_2015 = LocalDate.of(2015, Month.AUGUST, 5);
    public static final LocalDate AUG_06_2015 = LocalDate.of(2015, Month.AUGUST, 6);
    public static final LocalDate AUG_07_2015 = LocalDate.of(2015, Month.AUGUST, 7);
    
    public static final LocalDateTime AUG_01_2015_NOON = LocalDateTime.of(AUG_01_2015, NOON);
    public static final LocalDateTime AUG_02_2015_NOON = LocalDateTime.of(AUG_02_2015, NOON);
    public static final LocalDateTime AUG_03_2015_NOON = LocalDateTime.of(AUG_03_2015, NOON);
    public static final LocalDateTime AUG_04_2015_NOON = LocalDateTime.of(AUG_04_2015, NOON);
    public static final LocalDateTime AUG_05_2015_NOON = LocalDateTime.of(AUG_05_2015, NOON);
    public static final LocalDateTime AUG_06_2015_NOON = LocalDateTime.of(AUG_06_2015, NOON);
    public static final LocalDateTime AUG_07_2015_NOON = LocalDateTime.of(AUG_07_2015, NOON);
}
