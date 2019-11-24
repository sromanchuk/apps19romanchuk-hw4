package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = new RWayTrie();
    }

    public int load(String... strings) {
        int cnt = 0;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] instanceof String) {
                for (String word: strings[i].split(" ")) {
                    if (word.length() > 2) {
                        trie.add(new Tuple(word, word.length()));
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public boolean contains(String word) {

        return trie.contains(word);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean delete(String word) {
        return trie.delete(word);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> dict = trie.wordsWithPrefix(pref);
        ArrayList<String> res = new ArrayList<>();
        for (String word : dict){
            if (word.length() < k + pref.length()){
                res.add(word);
            }
        }
        return res;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public int size() {
        return trie.size();
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
