package com.bhar.LoggerLibrary;

/**
 * Created by amruthkesav on 25/02/16.
 */
public class LogClient<T> {

    private Logger logger;

    public LogClient(Logger logger) {
        this.logger = logger;
    }

    public void logError(T message) {
        logger.logError(message);
    }

    public void logWarning(T message) {
        logger.logWarning(message);
    }
}
