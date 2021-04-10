package io.slifer.automation.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines a Logback logging layout to be used for the Console and TestNG report loggers.
 *
 * @author Tim Slifer
 */
public class LogEventLayout extends LayoutBase<ILoggingEvent> {
    
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder buffer = new StringBuilder();
        
        buffer.append(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
        buffer.append("|");
        buffer.append(event.getLevel());
        buffer.append("|");
        buffer.append(event.getLoggerName().substring(event.getLoggerName().lastIndexOf(".") + 1));
        buffer.append("|");
        buffer.append(event.getFormattedMessage());
        buffer.append(CoreConstants.LINE_SEPARATOR);
        
        return buffer.toString();
    }
}
