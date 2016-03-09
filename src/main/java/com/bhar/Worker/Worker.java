package com.bhar.Worker;

/**
 * Created by amruthkesav on 06/03/16.
 */
public class Worker<T, R> implements Runnable {

    DataInputStore<T> inputStore;
    DataOutputStore<R> outputStore;
    Transformer<T, R> transformer;

    @Override
    public void run() {
        T input = inputStore.getData();
        R output = transformer.transform(input);
        outputStore.put(output);
    }

    public void start(int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(this);
            t.start();
        }
    }

    public static class WorkerBuilder<T, R> {
        Worker<T, R> worker;

        public WorkerBuilder inputStore(DataInputStore<T> inputStore) {
            worker.inputStore = inputStore;
            return this;
        }

        public WorkerBuilder outputStore(DataOutputStore<R> outputStore) {
            worker.outputStore = outputStore;
            return this;
        }

        public WorkerBuilder transformer(Transformer<T, R> transformer) {
            worker.transformer = transformer;
            return this;
        }

        public Worker build() {
            return worker;
        }
    }

}
