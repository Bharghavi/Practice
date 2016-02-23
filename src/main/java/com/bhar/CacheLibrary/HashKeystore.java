package com.bhar.CacheLibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amruthkesav on 24/02/16.
 */
public class HashKeystore<T, R> extends Keystore<T,R> {
    private int size;
    private Map<T, R> map;

    public HashKeystore(int size) {
        this.size = size;
        this.map = new HashMap<T, R>();
    }

    @Override
    protected R get(T key) {
        return map.get(key);
    }

    @Override
    protected void put(T key, R value, int ttl) {
        map.put(key, value);
    }

    @Override
    protected boolean isFull() {
        return map.size() == this.size;
    }

    @Override
    protected void removeKey(T key) {
        if (map.containsKey(key))
            map.remove(key);

    }
}
