package com.bhar.Worker;

import java.util.LinkedHashMap;

/**
 * Created by amruthkesav on 07/03/16.
 */
public class WorkerChain<T, R> {

    LinkedHashMap<Worker, Integer> workerMap;

    public WorkerChain(LinkedHashMap workerMap) {
        this.workerMap = workerMap;
    }

    public void start() {

    }

    public static class Builder<T, R> {

        DataInputStore<T> dataInputStore;
        DataOutputStore<R> dataOutputStore;

        LinkedHashMap<Worker, Integer> workerMap;

        public Builder(DataInputStore inputStore, DataOutputStore outputStore) {
            this.dataInputStore = inputStore;
            this.dataOutputStore = outputStore;
            workerMap = new LinkedHashMap<Worker, Integer>();
        }

        public Builder addTransformation(Transformer transformer, int numberOfThreads) {
            return this;
        }

    }
}
