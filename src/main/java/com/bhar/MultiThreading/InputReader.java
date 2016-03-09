package com.bhar.MultiThreading;

import com.bhar.TreesAndGraph.Queue;

import java.io.*;


/**
 * Created by bharghav on 2/26/2016.
 */
public class InputReader implements Runnable {

    String inputFileName;
    Queue<String> inputQueue;

    public InputReader(String inputFileName, Queue<String> inputQueue) {
        this.inputFileName = inputFileName;
        this.inputQueue = inputQueue;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFileName)));
            String line;
            while ( (line = reader.readLine()) != null) {
                while (inputQueue.isFull())
                    Thread.sleep(100);
                inputQueue.enque(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
