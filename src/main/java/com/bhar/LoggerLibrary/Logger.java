package com.bhar.LoggerLibrary;

/**
 * Created by amruthkesav on 25/02/16.
 */
public abstract class Logger<T> {
    abstract protected void logError(T message);
    abstract protected void logWarning(T message);
}
