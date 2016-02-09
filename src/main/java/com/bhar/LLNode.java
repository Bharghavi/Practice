package com.bhar;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class LLNode<T> {
    public LLNode<T> next;
    public T data;

    public LLNode(T data) {
        this.data = data;
        this.next = null;
    }

    public LLNode() {
    }

    public void add(T data) {
        LLNode<T> newNode = new LLNode<T>(data);
        this.next = newNode;
    }
}
