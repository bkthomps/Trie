package com.bkthomps.testtrie;

import com.bkthomps.trie.Trie;

public class TestTrie {

    public static void main(String[] args) {
        var trie = new Trie();
        assertTrue(trie.contains(""));
        assertTrue(!trie.contains("hello"));
        trie.add("hello");
        assertTrue(trie.contains("hello"));
        assertTrue(!trie.contains("helloworld"));
        trie.add("helloworld");
        assertTrue(trie.contains("hello"));
        assertTrue(!trie.contains("hellow"));
        assertTrue(trie.contains("helloworld"));
    }

    private static void assertTrue(boolean truth) {
        if (!truth) {
            throw new IllegalStateException("Failed assertion");
        }
    }
}
