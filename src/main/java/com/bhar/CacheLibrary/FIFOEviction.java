package com.bhar.CacheLibrary;


import com.bhar.Queue;

import java.util.List;



/**
 * Created by amruthkesav on 24/02/16.
 */
public class FIFOEviction<T> extends Eviction<T> {

    private Queue<T> queue;

    public FIFOEviction(List whiteList) {
        super(whiteList);
        queue = new Queue<T>();
    }

    @Override
    protected void accessKey(T key) {
    }

    @Override
    protected void addKey(T key) {
        if (!queue.contains(key))
            queue.enque(key);

    }

    @Override
    protected T getKeyToRemove() {
        return queue.removeElementNotIn(whiteList);
    }
}
