package io.slifer.automation.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerProxy {
    
    private static Logger logger;
    
    public LoggerProxy(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }
    
    public void error(String format, Object... args) {
        logger.error(format, args);
    }
    
    public void error(String format, Throwable throwable) {
        logger.error(format, throwable);
    }
    
    public void warn(String format, Object... args) {
        logger.warn(format, args);
    }
    
    public void info(String format, Object... args) {
        logger.info(format, args);
    }
    
    public void debug(String format, Object... args) {
        logger.debug(format, args);
    }
}
