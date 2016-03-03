package com.bhar.ProducerConsumer;

/**
 * Created by bharghav on 3/3/2016.
 */
abstract public class ProducerFactory<T> {
    abstract public T produce();
    abstract public boolean isEndMarker(T element);
}
