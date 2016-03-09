package com.bhar.Worker;

/**
 * Created by amruthkesav on 06/03/16.
 */
public abstract class Transformer<T, R> {
    public abstract R transform(T data);
    public abstract boolean isEndMarker(T data);
    public abstract R getEndMarker();
}
