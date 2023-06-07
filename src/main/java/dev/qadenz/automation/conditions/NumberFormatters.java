/*
Copyright Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package dev.qadenz.automation.conditions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A utility class intended to make for simplified invoking of the NumberFormat and to reduce the noise inside a test.
 *
 * @author Tim Slifer
 */
public class NumberFormatters {
    
    public static DecimalFormat groupedDecimal(String decimals) {
        DecimalFormat decimalFormat = new DecimalFormat(decimals);
        decimalFormat.setGroupingUsed(true);
        
        return decimalFormat;
    }
    
    public static DecimalFormat nonGroupedDecimal(String decimals) {
        DecimalFormat decimalFormat = new DecimalFormat(decimals);
        decimalFormat.setGroupingUsed(false);
        
        return decimalFormat;
    }
    
    public static NumberFormat groupedNumber() {
        return NumberFormat.getNumberInstance();
    }
    
    public static NumberFormat nonGroupedNumber() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        
        return numberFormat;
    }
}
