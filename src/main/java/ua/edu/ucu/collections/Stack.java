package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import java.lang.*;


public class Stack {

    private ImmutableLinkedList stack = new ImmutableLinkedList();


    public Object peek() {

        return this.stack.getFirst();
    }

    public Object pop() {

        Object first = stack.getFirst();
        this.stack = this.stack.removeFirst();
        return first;
    }

    public void push(Object e) {
        this.stack = this.stack.addFirst(e);
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
