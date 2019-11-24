package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import java.lang.*;


public class Queue {

    private ImmutableLinkedList queue = new ImmutableLinkedList();
    private int size = 0;


    public Object peek() {

        return this.queue.getFirst();
    }

    public Object dequeue() {

        Object first = queue.getFirst();
        this.queue = this.queue.removeFirst();
        this.size--;
        return first;
    }

    public void enqueue(Object e) {
        this.queue = this.queue.addLast(e);
        this.size++;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return this.queue.toString();
    }
}
