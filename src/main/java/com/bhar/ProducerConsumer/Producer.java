package com.bhar.ProducerConsumer;

import com.bhar.TreesAndGraph.ThreadSafeQueue;

/**
 * Created by bharghav on 3/3/2016.
 */
public class Producer<T> implements Runnable {

    ThreadSafeQueue<T> queue;
    ProducerFactory<T> producerFactory;
    Status status;

    private Producer(ProducerBuilder<T> builder) {
        this.queue = new ThreadSafeQueue<T>(builder.queueSize);
        this.producerFactory = builder.producerFactory;
        this.status = builder.status;
    }

    public ProducerConsumer pipe(ProducerConsumer producerConsumer) {
        producerConsumer.setInputQueue(queue);
        return producerConsumer;
    }

    public Consumer pipe(Consumer consumer) {
        consumer.setInputQueue(queue);
        return consumer;
    }

    public void printStatus() {
        System.out.print("Producer: ");
        status.printStatus();
    }


    @Override
    public void run() {
        try {
            T product;
            do {
                product = producerFactory.produce();
                queue.enque(product);
                status.update();
            } while (!producerFactory.isEndMarker(product));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start(int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(this);
            t.start();
        }
    }

    public static class ProducerBuilder<T> {
        ProducerFactory<T> producerFactory;
        Status status;
        int queueSize = 5;

        public ProducerBuilder(ProducerFactory<T> producerFactory) {
            this.producerFactory = producerFactory;
        }

        public ProducerBuilder<T> queueSize(int queueSize) {
            this.queueSize = queueSize;
            return this;
        }

        public ProducerBuilder<T> status(Status status) {
            this.status = status;
            return this;
        }

        public Producer<T> build() {
            return new Producer<T>(this);
        }
    }
}
