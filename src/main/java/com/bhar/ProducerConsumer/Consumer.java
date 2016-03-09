package com.bhar.ProducerConsumer;

import com.bhar.TreesAndGraph.ThreadSafeQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bharghav on 3/3/2016.
 */
public class Consumer<T> implements Runnable {
    ThreadSafeQueue<T> queue;
    ConsumerFactory<T> consumerFactory;

    public Consumer(ConsumerFactory<T> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    public void setInputQueue(ThreadSafeQueue<T> inputQueue) {
        this.queue = inputQueue;
    }

    @Override
    public void run() {
        T element = null;
        do {
            try {
                element = queue.deque();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumerFactory.consume(element);
        } while (!consumerFactory.isEndMarker(element));

    }

    public void start(int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(this);
            t.start();
        }
    }
}
