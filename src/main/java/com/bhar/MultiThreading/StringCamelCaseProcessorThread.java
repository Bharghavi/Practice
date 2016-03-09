package com.bhar.MultiThreading;

import com.bhar.TreesAndGraph.Queue;

import java.util.StringTokenizer;

/**
 * Created by bharghav on 2/26/2016.
 */
public class StringCamelCaseProcessorThread implements Runnable {

    Queue<String> inputQueue;
    Queue<String> outputQueue;

    public StringCamelCaseProcessorThread(Queue<String> inputQueue, Queue<String> outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while (inputQueue.isEmpty())
                Thread.sleep(100);
            while (!inputQueue.isEmpty()) {
                String inputString = inputQueue.deque();
                StringBuilder outputString = new StringBuilder();
                StringTokenizer tokens = new StringTokenizer(inputString);
                while (tokens.hasMoreElements()) {
                    String eachToken = tokens.nextToken();
                    outputString.append(eachToken.substring(0, 1).toUpperCase());
                    outputString.append(eachToken.substring(1).toLowerCase());
                    outputString.append(" ");
                }
                while (outputQueue.isFull())
                    Thread.sleep(100);
                outputQueue.enque(outputString.toString());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
