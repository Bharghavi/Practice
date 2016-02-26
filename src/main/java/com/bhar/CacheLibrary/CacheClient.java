package com.bhar.CacheLibrary;

/**
 * Created by amruthkesav on 24/02/16.
 */
public class CacheClient<T, R> {

    private Keystore<T, R> keystore;
    private Eviction<T> eviction;

    public CacheClient(Keystore<T, R> keystore, Eviction<T> eviction) {
        this.keystore = keystore;
        this.eviction = eviction;
    }

    public void put(T key, R value, int ttl){
        if (keystore.isFull() && !keystore.containsKey(key))
            keystore.removeKey(eviction.getKeyToRemove());
        keystore.put(key, value, ttl);
        eviction.addKey(key);
    }

    public void putIfAbsent(T key, R value, int ttl) {
        if (!keystore.containsKey(key))
            put(key, value, ttl);
    }

    public R get(T key) {
        eviction.accessKey(key);
        return keystore.get(key);
    }

    public void printCache() {
        keystore.printKeyValuePairs();
    }
}
