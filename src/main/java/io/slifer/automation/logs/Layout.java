package io.slifer.automation.logs;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import io.slifer.automation.config.RunContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines the logging output for Logback.
 *
 * @author Tim Slifer
 */
public class Layout extends LayoutBase<ILoggingEvent> {
    
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
        buffer.append("|");
        buffer.append(RunContext.getTestCaseName());
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
