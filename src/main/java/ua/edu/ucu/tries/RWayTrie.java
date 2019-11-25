package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;
import java.lang.*;
import java.util.*;

public class RWayTrie implements Trie {

    static class Node {

        private int value;
        private Node[] next = new Node[26];

        public Node(){
            this.value = -1;
        }

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node[] getNext() {
            return this.next;
        }

        public Node getNext(int index) {
            if (index >= this.next.length) {
                throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
            }
            return this.next[index];
        }


        public void addNext(Node next) {

            Node[] newNext = Arrays.copyOf(this.next, this.next.length+1);
            newNext[this.next.length] = next;
            this.next = Arrays.copyOf(newNext, newNext.length);
        }

        public void addNexts(Node[] next) {

            Node[] newNext = new Node[this.next.length+next.length];

            newNext = Arrays.copyOf(this.next, this.next.length);
            for (int i = 0; i < next.length; i++){
                newNext[this.next.length+i] = next[i];
            }

            this.next = Arrays.copyOf(newNext, newNext.length);
        }

        public void setNext(Node node, int index) {
            if (index >= this.next.length) {
                throw new IndexOutOfBoundsException("Unable to add element: index is out of bounds!");
            }
            this.next[index] = node;
        }
    }

    private Node root;
    private int size;
    private int max;


    public RWayTrie() {
        this.root = new Node();
        this.size = 0;
    }

    public RWayTrie(Node node) {
        this.root = node;
        this.size = 0;
    }

    @Override
    public void add(Tuple t) {

        Node curr = this.root;
        Node prev;
        String word = t.term;
        String str = "";
        for (int i = 0; i < word.length(); i++) {

            if (curr.getNext(word.charAt(i) - 97) == null) {
                curr.setNext(new Node(), word.charAt(i) - 97);
            }
            prev = curr;
            curr = curr.getNext(word.charAt(i) - 97);
        }
        if (t.weight > this.max) {
            this.max = t.weight;
        }
        curr.setValue(this.size);
        this.size++;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(String word) {
        Node curr = this.root;
        Node[] next;

        for (int i = 0; i < word.length(); i++) {
            if (curr.getNext(word.charAt(i) - 97) == null) {
                return false;
            }
            curr = curr.getNext(word.charAt(i) - 97);
        }
        if (curr.getValue() == -1) {
            return false;
        }
        return true;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(String word) {

        Node curr = this.root;

        for (int i = 0; i < word.length(); i++) {
            if (curr.getNext(word.charAt(i) - 97) == null) {
                return false;
            }
            curr = curr.getNext(word.charAt(i) - 97);
        }
        if (curr.getValue() == -1) {
            return false;
        }
        else {
            curr.setValue(-1);
            this.size--;
            return true;
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        ArrayList<String> res = new ArrayList<>();

        String new_s = "";
        Node curr = this.root;

        for (int i = 0; i < s.length(); i++){
            if (curr.getNext(s.charAt(i) - 97) == null) {
                return null;
            }

            curr = curr.getNext(s.charAt(i) - 97);
        }
        this.toQu(q, new_s,curr);


        for (int i = 0; i < q.size(); i++) {
            String sym = ""+q.dequeue();
            res.add(s+sym);

        }
        return res;
    }



    private void toQu(Queue qu, String str, Node curr) {

        if (curr == null) return;
        if (curr.getValue() != -1) qu.enqueue(str);

        for (int i = 0; i < curr.getNext().length; i++)
            toQu(qu, str + "" + (char)(i+97), curr.getNext(i));

    }

    @Override
    public int size() {
        return this.size;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}
