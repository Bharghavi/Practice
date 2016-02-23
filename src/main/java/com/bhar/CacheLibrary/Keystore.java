package com.bhar.CacheLibrary;

/**
 * Created by amruthkesav on 24/02/16.
 */
public abstract class Keystore<T,R> {

    abstract protected R get(T key);
    abstract protected void put(T key, R value, int ttl);
    abstract protected boolean isFull();
    abstract protected void removeKey(T key);
}
