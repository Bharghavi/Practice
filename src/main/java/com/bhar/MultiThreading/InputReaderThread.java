package com.bhar.MultiThreading;

import com.bhar.TreesAndGraph.Queue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by bharghav on 2/26/2016.
 */
public class InputReaderThread<T> implements Runnable {
    String inputFileName;
    Queue<T> inputQueue;

    public InputReaderThread(String filename, Queue<T> inputQueue) {
        this.inputFileName = filename;
        this.inputQueue = inputQueue;
    }

    @Override
    public void run() {
        File file = new File(inputFileName);
        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
