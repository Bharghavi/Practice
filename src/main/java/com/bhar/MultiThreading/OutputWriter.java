package com.bhar.MultiThreading;


import com.bhar.TreesAndGraph.Queue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by bharghav on 2/26/2016.
 */
public class OutputWriter implements Runnable {

    String outputFileName;
    Queue<String> outputQueue;

    public OutputWriter(String outputFileName, Queue<String> outputQueue) {
        this.outputFileName = outputFileName;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        try {
            while(outputQueue.isEmpty())
                Thread.sleep(100);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputFileName), true));
            while (!outputQueue.isEmpty()) {
                writer.append(outputQueue.deque());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
