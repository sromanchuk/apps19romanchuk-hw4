package ua.edu.ucu.collections.immutable;

import java.lang.*;

public class ImmutableLinkedList implements ImmutableList {

    class LLNode {

        private Object value;
        private LLNode next;

        public LLNode(){

        }

        public LLNode(Object value){
            this.value = value;
        }

        public Object getValue(){
            return this.value;
        }

        public LLNode getNext(){
            return this.next;
        }

        public void setNext(LLNode next){
            this.next = next;
        }

    }

    private ImmutableLinkedList.LLNode head;


    public ImmutableLinkedList(){
        this.head = null;
    }

    public ImmutableLinkedList(ImmutableLinkedList.LLNode head){

        this.head = head;
    }

    public ImmutableLinkedList clone() {
        ImmutableLinkedList newList = new ImmutableLinkedList();
        ImmutableLinkedList.LLNode currNode = this.head;
        ImmutableLinkedList.LLNode newNode;
        Object[] array = this.toArray();
        for (int i = 0; i < array.length; i++){
            newNode = newList.new LLNode(array[array.length - i - 1]);
            newNode.setNext(newList.head);
            newList.head = newNode;
        }
        return newList;
    }
    public ImmutableLinkedList addFirst(Object e){

        ImmutableLinkedList newList = this.clone();

        ImmutableLinkedList.LLNode newNode = newList.new LLNode(e);

        newNode.setNext(newList.head);
        newList.head = newNode;

        return newList;

    } //додає елемент у кінець колекції

    public ImmutableLinkedList add(Object e){
        ImmutableLinkedList newList = this.clone();
        ImmutableLinkedList.LLNode currNode = newList.head;
        ImmutableLinkedList.LLNode previous = null;

        while (currNode != null){
            previous = currNode;
            currNode = currNode.getNext();
        }
        ImmutableLinkedList.LLNode newNode = newList.new LLNode(e);
        if (previous == null){

            newNode.setNext(newList.head);
            newList.head = newNode;
            return newList;
        }
        previous.setNext(newNode);
        return newList;
    }

    public ImmutableLinkedList add(int index, Object e){

        if (index > this.size()){
            throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
        }

        ImmutableLinkedList newList = this.clone();


        ImmutableLinkedList.LLNode currNode = newList.head;
        ImmutableLinkedList.LLNode previous = null;
        for (int i = 0; i < index && currNode != null; i++){
            previous = currNode;
            currNode = currNode.getNext();
        }

        if (newList.isEmpty() || previous == null){
            return this.addFirst(e);
        }
        ImmutableLinkedList.LLNode newNode = newList.new LLNode(e);
        newNode.setNext(currNode);
        previous.setNext(newNode);

        return newList;
    } //додає елемент до колекції за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції

    public ImmutableLinkedList addLast(Object e){
        return this.add(e);
    }

    public ImmutableLinkedList addAll(Object[] c){ //додає масив елементів у кінець колекції
        ImmutableLinkedList newList = this.clone();
        for (int i = 0; i < c.length; i++){
            newList = newList.addLast(c[i]);
        }
        return newList;
    }

    public ImmutableLinkedList addAll(int index, Object[] c){
        if (index > this.size()){
            throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
        }
        ImmutableLinkedList newList = this.clone();
        ImmutableLinkedList.LLNode currNode = newList.head;
        ImmutableLinkedList.LLNode previous = null;
        ImmutableLinkedList.LLNode newNode;

        for (int i = 0; i < index && currNode != null; i++){
            previous = currNode;
            currNode = currNode.getNext();
        }
        ImmutableLinkedList.LLNode nextNode = currNode;
        ImmutableLinkedList.LLNode prev;

        for (int i = 0; i < c.length; i++){
            prev = nextNode;
            nextNode = newList.new LLNode(c[c.length - i - 1]);
            nextNode.setNext(prev);
        }

        previous.setNext(nextNode);

        return newList;

    } // додає масив елементів починаючи з зазначеного індекса, та кидає виключну ситуацію, якщо індекс виходить за межі колекції



    public Object get(int index){
        if (index >= this.size()){
            throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
        }
        ImmutableLinkedList.LLNode currNode = this.head;
        for (int i = 0; i < index && currNode != null; i++){
            currNode = currNode.getNext();
        }
        return currNode.getValue();
    } //повертає елемент за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції

    public Object getFirst(){
        if (this.isEmpty()){
            return null;
        }
        return this.get(0);
    }

    public Object getLast(){
        if (this.isEmpty()){
            return null;
        }
        return this.get(this.size() - 1);
    }

    public ImmutableLinkedList remove(int index){
        if (index >= this.size()){
            throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
        }
        ImmutableLinkedList newList = this.clone();
        ImmutableLinkedList.LLNode previous = null;
        ImmutableLinkedList.LLNode currNode = newList.head;
        for (int i = 0; i < index && currNode != null; i++)
        {
            previous = currNode;
            currNode = currNode.getNext();
        }
        if (previous == null){
            newList.head = currNode.getNext();
            return newList;
        }
        if (currNode.getNext() == null){
            previous.setNext(null);
            return newList;
        }

        previous.setNext(currNode.getNext());
        return newList;

    } //видаляє елемент за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції

    public ImmutableLinkedList removeFirst(){
        if (this.isEmpty()){
            return this;
        }
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast(){
        if (this.isEmpty()){
            return this;
        }
        return this.remove(this.size()-1);
    }

    public ImmutableLinkedList set(int index, Object e){
        if (index >= this.size()){
            throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
        }
        ImmutableLinkedList newList = this.clone();
        ImmutableLinkedList.LLNode previous = null;
        ImmutableLinkedList.LLNode currNode = newList.head;
        for (int i = 0; i < index && currNode != null; i++)
        {
            previous = currNode;
            currNode = currNode.getNext();
        }
        ImmutableLinkedList.LLNode setNode = newList.new LLNode(e);

        if (previous == null){
            setNode.setNext(currNode.getNext());
            newList.head = setNode;
            return newList;
        }
        if (currNode.getNext() == null){
            previous.setNext(setNode);
            return newList;
        }

        setNode.setNext(currNode.getNext());
        previous.setNext(setNode);
        return newList;


    } //змінює значення елементу за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції

    public int indexOf(Object e){
        ImmutableLinkedList.LLNode currNode = this.head;
        int count = 0;
        while(currNode != null){
            if (currNode.getValue() == e){
                return count;
            }
            count++;
            currNode = currNode.getNext();
        }
        return -1;
    } //шукає індекс елемента (повертає індекс першого який знайшов, або -1 у випадку відсутності)

    public int size(){
        int count = 0;
        ImmutableLinkedList.LLNode currNode = this.head;
        while (currNode != null){
            count++;
            currNode = currNode.getNext();
        }
        return count;
    } //розмір колекції

    public ImmutableLinkedList clear(){
        ImmutableLinkedList newList = new ImmutableLinkedList();
        return newList;
    } //очищує вміст колекції



    public boolean isEmpty(){
        return (this.head == null);
    } //якщо у колеції нема елементів то повертає true


    public Object[] toArray(){
        int count = 0;
        Object[] array = new Object[this.size()];
        ImmutableLinkedList.LLNode currNode = this.head;
        while (currNode != null){
            array[count] = currNode.getValue();
            count++;
            currNode = currNode.getNext();
        }
        return array;
    } //перетворює колекцію до масиву обєктів

    @Override
    public String toString(){
        Object[] array = this.toArray();
        String out = "";
        for (int i = 0; i < this.size(); i++){
            out += array[i] + "";
            if (i < this.size() - 1){
                out += ", ";
            }
        }
        return out;
    } //повертає рядок, де через кому відображаютсься елементи колекції


}
