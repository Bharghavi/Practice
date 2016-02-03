package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/4/2016.
 */
public class Stack<T> {
    private LLNode<T> top;

    public void push(T data) {
        if (top == null)
            top =  new LLNode<T>(data);
        else {
            LLNode<T> newNode = new LLNode<T>(data);
            newNode.next = top;
            top = newNode;
        }
    }

    public T getTop() {
        if (top != null)
            return top.data;
        return null;
    }

    public T pop() {
        if (top == null)
            return null;
        top = top.next;
        return top.data;
    }

    public List<T> getAllElementsAsList(){
        List<T> allElements = new ArrayList<T>();
        if (top != null) {
            LLNode<T> currentNode = top;
            while (currentNode.next != null) {
                allElements.add(currentNode.data);
                currentNode = currentNode.next;
            }
        }
        return allElements;
    }
}
