package com.bhar.TreesAndGraph;

/**
 * Created by amruthkesav on 05/03/16.
 */
public class ThreadSafeQueue<T> {

    protected LLNode<T> head;
    private int size;

    public ThreadSafeQueue(int size) {
        this.size = size;
    }

    public synchronized T deque() throws InterruptedException {
        while (this.isEmpty())
            wait();
        notifyAll();
        LLNode<T> oldHead = head;
        head = head.next;
        return oldHead.data;
    }

    public synchronized void enque(T data) throws InterruptedException {
        while (isFull())
            wait();
        notifyAll();
        if (isEmpty()) {
            head = new LLNode<>(data);
        } else {
            LLNode<T> currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new LLNode<T>(data);
        }
    }

    public boolean isFull() {
        LLNode<T> currentNode = head;
        int queueSize = 0;
        while (currentNode != null) {
            queueSize++;
            currentNode = currentNode.next;
        }
        return queueSize >= size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}
