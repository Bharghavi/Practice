package com.bhar.ProducerConsumer;

import com.bhar.TreesAndGraph.ThreadSafeQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bharghav on 3/3/2016.
 */
public class ProducerConsumer<T, R> implements Runnable{
    ThreadSafeQueue<T> inputQueue;
    ThreadSafeQueue<R> outputQueue;
    ProducerConsumerFactory<T,R> factory;

    public ProducerConsumer(int queueSize, ProducerConsumerFactory<T,R> factory) {
        this.outputQueue = new ThreadSafeQueue<R>(queueSize);
        this.factory = factory;
    }

    public void setInputQueue(ThreadSafeQueue<T> inputQueue) {
        this.inputQueue = inputQueue;
    }

    public ProducerConsumer pipe(ProducerConsumer producerConsumer) {
        producerConsumer.setInputQueue(outputQueue);
        return producerConsumer;
    }

    public Consumer pipe(Consumer consumer) {
        consumer.setInputQueue(outputQueue);
        return consumer;
    }

    public void start(int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(this);
            t.start();
        }
    }


    @Override
    public void run() {
        R element = null;
        do{
            try {
                element = factory.produceConsume(inputQueue.deque());
                outputQueue.enque(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!factory.isEndMarker(element));

    }
}
