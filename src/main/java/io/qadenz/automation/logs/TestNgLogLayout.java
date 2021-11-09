/*
Copyright 2021 Tim Slifer

Licensed under the PolyForm Internal Use License, Version 1.0.0 (the "License");
you may not use this file except in compliance with the License.
A copy of the License may be obtained at

https://polyformproject.org/licenses/internal-use/1.0.0/
 */
package io.qadenz.automation.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines a Logback logging layout to be used for the TestNG report logger.
 *
 * @author Tim Slifer
 */
public class TestNgLogLayout extends LayoutBase<ILoggingEvent> {
    
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder buffer = new StringBuilder();
        
        buffer.append(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
        buffer.append(" | ");
        buffer.append(event.getLevel());
        buffer.append(" | ");
        buffer.append(event.getLoggerName().substring(event.getLoggerName().lastIndexOf(".") + 1));
        buffer.append(" | ");
        buffer.append(event.getFormattedMessage());
        buffer.append(CoreConstants.LINE_SEPARATOR);
        
        return buffer.toString();
    }
}
