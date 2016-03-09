package com.bhar.MultiThreading;

import com.bhar.TreesAndGraph.Queue;

/**
 * Created by bharghav on 2/26/2016.
 */
public class MultiThreadLauncher {
    String inputFileName;
    String outputFileName;

    Queue<String> inputQueue;
    Queue<String> outputQueue;

    public MultiThreadLauncher(String inputFileName, String outputFileName, int queueSize) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        inputQueue = new Queue<String>(queueSize);
        outputQueue = new Queue<String>(queueSize);
    }

    public void launch() {
        final InputReader inputReader = new InputReader(inputFileName, inputQueue);
        final OutputWriter outputWriter = new OutputWriter(outputFileName, outputQueue);
        final StringCamelCaseProcessorThread processor = new StringCamelCaseProcessorThread(inputQueue, outputQueue);

        final Thread inputReaderThread = new Thread(inputReader);
        final Thread processorThread = new Thread(processor);
        final Thread outputWriterThread = new Thread(outputWriter);

        inputReaderThread.start();
        processorThread.start();
        outputWriterThread.start();
    }
}
