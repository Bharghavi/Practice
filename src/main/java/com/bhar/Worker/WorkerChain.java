package com.bhar.Worker;

import com.bhar.TreesAndGraph.Queue;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by amruthkesav on 07/03/16.
 */
public class WorkerChain<T, R> {

    LinkedHashMap<Worker<T, R>, Integer> workerMap;

    public WorkerChain(LinkedHashMap<Worker<T, R>, Integer> workerMap) {
        this.workerMap = workerMap;
    }

    public void start() {
        Iterator<Map.Entry<Worker<T, R>, Integer>> iterator = workerMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Worker<T, R>, Integer> each = iterator.next();
            each.getKey().start(each.getValue());
        }
    }

    public static class Builder<T, R> {

        DataInputStore<T> dataInputStore;
        DataOutputStore<R> dataOutputStore;

        LinkedHashMap<Worker<T, R>, Integer> workerMap;

        public Builder(DataInputStore<T> inputStore, DataOutputStore<R> outputStore) {
            this.dataInputStore = inputStore;
            this.dataOutputStore = outputStore;
            workerMap = new LinkedHashMap<Worker<T, R>, Integer>();
        }

        public Builder addTransformation(Transformer<T, R> transformer, int numberOfThreads)
        {
            Worker<T, R> worker;
            if (workerMap.isEmpty()) {
                worker = new Worker(dataInputStore, dataOutputStore, transformer);
            } else {
                Worker<T, R> lastWorker = getLastEntryFrom(workerMap);
                QueueStore<R> queueStore = new QueueStore<R>();
                lastWorker.outputStore = queueStore;
                worker = new Worker(queueStore, dataOutputStore, transformer);
            }
            workerMap.put(worker, numberOfThreads);
            return this;
        }

        private Worker<T, R> getLastEntryFrom(LinkedHashMap<Worker<T, R>, Integer> workerMap) {
            Iterator<Worker<T, R>> iterator = workerMap.keySet().iterator();
            Worker<T, R> lastWorker = null;
            while (iterator.hasNext())
                lastWorker = iterator.next();
            return lastWorker;
        }

    }
}
