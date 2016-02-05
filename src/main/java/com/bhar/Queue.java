package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class Queue<T> {

    private LLNode<T> head;
    private LLNode<T> marker;

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

    public void markEndAsMarker(){
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
}
