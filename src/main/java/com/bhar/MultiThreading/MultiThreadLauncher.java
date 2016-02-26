package com.bhar.MultiThreading;

import com.bhar.TreesAndGraph.Queue;

/**
 * Created by bharghav on 2/26/2016.
 */
public class MultiThreadLauncher<T> {
    String inputFileName;
    String outputFileName;

    Queue<T> inputQueue;
    Queue<T> outputQueue;

    public MultiThreadLauncher(String inputFileName, String outputFileName, int queueSize) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        inputQueue = new Queue<T>(queueSize);
        outputQueue = new Queue<T>(queueSize);
    }
}
