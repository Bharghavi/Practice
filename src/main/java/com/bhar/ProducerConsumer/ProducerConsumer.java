package com.bhar.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bharghav on 3/3/2016.
 */
public class ProducerConsumer<T, R> implements Runnable{
    ArrayBlockingQueue<T> inputQueue;
    ArrayBlockingQueue<R> outputQueue;
    ProducerConsumerFactory<T,R> factory;

    public ProducerConsumer(int queueSize, ProducerConsumerFactory<T,R> factory) {
        this.outputQueue = new ArrayBlockingQueue<R>(queueSize);
        this.factory = factory;
    }

    public void setInputQueue(ArrayBlockingQueue<T> inputQueue) {
        this.inputQueue = inputQueue;
    }

    public ArrayBlockingQueue<R> getOutputQueue() {
        return outputQueue;
    }

    public ProducerConsumer pipe(ProducerConsumer producerConsumer) {
        producerConsumer.setInputQueue(outputQueue);
        return producerConsumer;
    }

    public Consumer pipe(Consumer consumer) {
        consumer.setInputQueue(outputQueue);
        return consumer;
    }


    @Override
    public void run() {
        R element = null;
        do{
            try {
                element = factory.produceConsume(inputQueue.take());
                outputQueue.put(element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!factory.isEndMarker(element));

    }
}
