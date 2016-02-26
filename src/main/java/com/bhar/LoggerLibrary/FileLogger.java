package com.bhar.LoggerLibrary;

import java.io.*;

/**
 * Created by amruthkesav on 25/02/16.
 */
public class FileLogger<T> extends Logger<T> {

    String fileName;
    int maximumFileSize;
    int fileIndex;

    public FileLogger(String filename, int maximumFileSize) {
        this.fileName = filename;
        this.maximumFileSize = maximumFileSize;
    }

    @Override
    protected void logError(T message) {
        logMessage("Error: " + message.toString());
    }

    @Override
    protected void logWarning(T message) {
        logMessage("Warn: " + message.toString());
    }

    private void logMessage(String message) {
        File logFile = getLogFile();
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(logFile, true));
            bufferedWriter.append(message);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private File getLogFile() {
        String currentFileName = new String(fileName);
        if (fileIndex != 0)
            currentFileName = fileIndex + fileName;
        File logFile = new File(currentFileName);
        if (!logFile.exists())
            return createNewLogFile(currentFileName);
        if (!hasFileSizeLimitReached(logFile))
            return logFile;

        fileIndex++;
        return createNewLogFile(fileIndex + fileName);
    }

    private File createNewLogFile(String logFileName) {
        File logFile = new File(logFileName);
        try {
            logFile.createNewFile();
            return logFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasFileSizeLimitReached(File file) {
        return (file.length()/1024) > maximumFileSize;
    }
}
