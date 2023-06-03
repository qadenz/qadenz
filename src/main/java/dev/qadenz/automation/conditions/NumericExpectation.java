/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import java.text.NumberFormat;

/**
 * Interface modeling an expectation used for evaluating Number-based Conditions.
 *
 * @author Tim Slifer
 */
public interface NumericExpectation<T> extends Expectation<T> {
    
    void setNumberFormat(NumberFormat numberFormat);
}