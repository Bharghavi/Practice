package com.bhar.Worker;

/**
 * Created by amruthkesav on 06/03/16.
 */
public class Worker<T, R> implements Runnable {

    DataInputStore<T> inputStore;
    DataOutputStore<R> outputStore;
    Transformer<T, R> transformer;

    public Worker(DataInputStore<T> inputStore, DataOutputStore<R> outputStore, Transformer<T,R> transformer) {
        this.inputStore = inputStore;
        this.outputStore = outputStore;
        this.transformer = transformer;
    }

    @Override
    public void run() {
        while (true) {
            T input = inputStore.getData();
            R output;
            if (transformer.isEndMarker(input)) {
                output = transformer.getEndMarker();
                outputStore.put(output);
                break;
            }
            else {
                output = transformer.transform(input);
                outputStore.put(output);
            }
        }
    }

    public void start(int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(this);
            t.start();
        }
    }
}
