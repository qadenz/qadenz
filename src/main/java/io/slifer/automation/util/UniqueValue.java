package io.slifer.automation.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UniqueValue {
    
    /**
     * Generates a unique value based on the current date/time stamp, resolving from Month to Millisecond. The time
     * stamp is converted to a Base36 String, resulting in an 8 character value that changes every millisecond that will
     * not be produced a second time in the same year.
     *
     * @return The unique value.
     */
    public static String generate() {
        Date date = new Date();
        String simpleDate = new SimpleDateFormat("MMddHHmmssSSS").format(date);
        long l = Long.parseLong(simpleDate);
        
        return Long.toString(l, 36).toUpperCase();
    }
}
