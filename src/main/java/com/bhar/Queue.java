package com.bhar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amrk7 on 2/2/2016.
 */
public class Queue<T> {

    private LLNode<T> head;
    private LLNode<T> tail;

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

    public void setTail(){
        if (head != null) {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            tail = currentNode;
        }
    }

    public T getTail() {
        if (tail == null)
            return null;
        return tail.data;
    }

    public T deque(){
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

   /* public void mergeQueue(Queue queue) {
        if (this.head == null)
            this.head = queue.head;
        else {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = queue.head;
        }
    }*/

    public List<T> getAllElementsAsList(){
        List<T> allElements = new ArrayList<T>();
        if (head != null) {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                allElements.add(currentNode.data);
                currentNode = currentNode.next;
            }
        }
        return allElements;
    }
}
