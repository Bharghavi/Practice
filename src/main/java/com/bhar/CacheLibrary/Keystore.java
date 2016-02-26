package com.bhar.CacheLibrary;

/**
 * Created by amruthkesav on 24/02/16.
 */
public abstract class Keystore<T,R> {

    protected int size;

    public Keystore(int size) {
        this.size = size;
    }

    abstract protected R get(T key);
    abstract protected void put(T key, R value, int ttl);
    abstract protected boolean isFull();
    abstract protected void removeKey(T key);
    abstract protected void printKeyValuePairs();
    abstract protected boolean containsKey(T key);
}
