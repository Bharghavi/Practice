package com.bhar.ProducerConsumer;

/**
 * Created by bharghav on 3/3/2016.
 */
public abstract class ConsumerFactory<T> {

    public abstract void consume(T data);
    public abstract boolean isEndMarker(T data);
}
