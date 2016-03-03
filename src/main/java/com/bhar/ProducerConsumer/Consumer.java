package com.bhar.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bharghav on 3/3/2016.
 */
public class Consumer<T> implements Runnable {
    ArrayBlockingQueue<T> queue;
    ConsumerFactory<T> consumerFactory;

    public Consumer(ConsumerFactory<T> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    public void setInputQueue(ArrayBlockingQueue<T> inputQueue) {
        this.queue = inputQueue;
    }

    @Override
    public void run() {
        T element = null;
        do {
            try {
                element = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumerFactory.consume(element);
        } while (!consumerFactory.isEndMarker(element));

    }
}
