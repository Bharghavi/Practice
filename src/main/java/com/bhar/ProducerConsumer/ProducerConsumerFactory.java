package com.bhar.ProducerConsumer;

/**
 * Created by bharghav on 3/3/2016.
 */
abstract public class ProducerConsumerFactory<T, R> {
    public abstract R produceConsume(T inputElement);
    public abstract boolean isEndMarker(R element);
}
