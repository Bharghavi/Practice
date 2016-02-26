package com.bhar.TreesAndGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class Queue<T> {

    private LLNode<T> head;
    private LLNode<T> marker;
    private int size;

    public Queue() {
        head = null;
    }

    public Queue(int size) {
        this.size = size;
    }

    public Queue(T data) {
        head = new LLNode<T>(data);
    }

    public void enque(T data) {
        if (head == null) {
            head =  new LLNode<T>(data);
        }
        else {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new LLNode<T>(data);
        }
    }

    public void markEnd(){
        if (head != null) {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            marker = currentNode;
        }
    }

    public T getMarker() {
        if (marker == null)
            return null;
        return marker.data;
    }

    public T deque(){
    	if (!this.hasData())
    		return null;
        LLNode<T> oldHead = head;
        head = head.next;
        return oldHead.data;
    }

    public T peekFirst() {
        return head.data;
    }

    public boolean hasData() {
        return head!=null;
    }

    public List<T> getAllElementsAsList(){
        List<T> allElements = new ArrayList<T>();
        if (head != null) {
            LLNode<T> currentNode = head;
            while (currentNode != null) {
                allElements.add(currentNode.data);
                currentNode = currentNode.next;
            }
        }
        return allElements;
    }

    public boolean contains(T data) {
        LLNode<T> currentNode = head;

        while (currentNode != null) {
            if (currentNode.data == data)
                return true;
            currentNode = currentNode.next;
        }
        return false;
    }

    public T removeElementNotIn(List<T> whitelist) {
        if (head != null && !whitelist.contains(head.data)) {
            return deque();
        }
        LLNode<T> currentNode = head;
        T result;
        while (currentNode != null && currentNode.next != null) {
            if (!whitelist.contains(currentNode.next.data)) {
                result = currentNode.next.data;
                currentNode.next = currentNode.next.next;
                return result;
            }
            currentNode = currentNode.next;
        }
        return deque();
    }

    public boolean isFull() {
        LLNode<T> currentNode = head;
        int queueSize = 0;
        while (currentNode != null) {
            queueSize++;
            currentNode = currentNode.next;
        }
        return queueSize>=size;
    }

}
