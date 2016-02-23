package com.bhar.CacheLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amruthkesav on 24/02/16.
 */
public abstract class Eviction<T> {

    List<T> whiteList;

    public Eviction(List<T> whiteList) {
        this.whiteList = new ArrayList<T>();
        this.whiteList.addAll(whiteList);
    }

    abstract protected void accessKey(T key);
    abstract protected void addKey(T key);
    abstract protected T removeElement();

}
