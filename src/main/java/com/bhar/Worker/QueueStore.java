package com.bhar.Worker;

import com.bhar.TreesAndGraph.ThreadSafeQueue;

/**
 * Created by amruthkesav on 06/03/16.
 */
public class QueueStore<T> implements DataInputStore<T>, DataOutputStore<T>{

    private static final int DEFAULT_QUEUE_SIZE = 5;
    ThreadSafeQueue<T> queue;

    public QueueStore(ThreadSafeQueue queue) {
        this.queue = queue;
    }

    public QueueStore(int queueSize) {
        this.queue = new ThreadSafeQueue<T>(queueSize);
    }

    public QueueStore() {
        this.queue = new ThreadSafeQueue<T>(DEFAULT_QUEUE_SIZE);
    }

    @Override
    public T getData() {
        try {
            return queue.deque();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(T data) {
        try {
            queue.enque(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
