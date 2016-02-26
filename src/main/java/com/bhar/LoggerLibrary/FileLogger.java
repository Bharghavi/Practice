package com.bhar.LoggerLibrary;

import java.io.File;
import java.io.IOException;

/**
 * Created by amruthkesav on 25/02/16.
 */
public class FileLogger<T> extends Logger {

    File logFile;
    int fileSize; 

    public FileLogger(String filename, int fileSize) {
        logFile = new File(filename);
        this.fileSize = fileSize;
    }

    @Override
    protected void logError(Object message) {

    }

    @Override
    protected void logWarning(Object message) {

    }
}
