package com.bhar.Worker;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by amruthkesav on 07/03/16.
 */
public class WorkerChain<T, R> {

    LinkedHashMap<Worker, Integer> workerMap;

    public WorkerChain(LinkedHashMap<Worker, Integer> workerMap) {
        this.workerMap = workerMap;
    }

    public void start() {
        Iterator<Map.Entry<Worker, Integer>> iterator = workerMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Worker, Integer> each = iterator.next();
            each.getKey().start(each.getValue());
        }
    }

    public static class Builder<T, R> {

        DataInputStore<T> dataInputStore;
        DataOutputStore<R> dataOutputStore;

        LinkedHashMap<Worker, Integer> workerMap;

        public Builder(DataInputStore<T> inputStore, DataOutputStore<R> outputStore) {
            this.dataInputStore = inputStore;
            this.dataOutputStore = outputStore;
            workerMap = new LinkedHashMap<Worker, Integer>();
        }

        public WorkerChain addTransformation(Transformer transformer, int numberOfThreads)
        {
            Worker worker;
            if (workerMap.isEmpty()) {
                worker = new Worker(dataInputStore, dataOutputStore, transformer);
            } else {
                Worker lastWorker = getLastEntryFrom(workerMap);
                QueueStore<R> queueStore = new QueueStore<R>();
                lastWorker.outputStore = queueStore;
                worker = new Worker(queueStore, dataOutputStore, transformer);
            }
            workerMap.put(worker, numberOfThreads);
            return new WorkerChain(workerMap);
        }

        private Worker getLastEntryFrom(LinkedHashMap<Worker, Integer> workerMap) {
            Iterator<Worker> iterator = workerMap.keySet().iterator();
            Worker lastWorker = null;
            while (iterator.hasNext())
                lastWorker = iterator.next();
            return lastWorker;
        }

    }
}
