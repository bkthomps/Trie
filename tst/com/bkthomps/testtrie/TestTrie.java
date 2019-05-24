package com.bkthomps.testtrie;

import com.bkthomps.trie.Trie;

public final class TestTrie {

    public static void main(String[] args) {
        var trie = new Trie();
        assertTrue(trie.isEmpty());
        assertTrue(trie.size() == 0);
        assertTrue(trie.contains(""));
        assertTrue(!trie.contains("hello"));
        trie.add("hello");
        assertTrue(!trie.isEmpty());
        assertTrue(trie.size() == 1);
        assertTrue(trie.contains("hello"));
        assertTrue(!trie.contains("helloworld"));
        trie.add("helloworld");
        assertTrue(trie.size() == 2);
        assertTrue(trie.contains("hello"));
        assertTrue(!trie.contains("hellow"));
        assertTrue(trie.contains("helloworld"));
        assertTrue(!trie.remove("hellow"));
        assertTrue(trie.size() == 2);
        assertTrue(trie.remove("hello"));
        assertTrue(trie.size() == 1);
        assertTrue(!trie.contains("hello"));
        assertTrue(trie.contains("helloworld"));
        trie.clear();
        assertTrue(!trie.contains("helloworld"));
        assertTrue(trie.isEmpty());
    }

    private static void assertTrue(boolean truth) {
        if (!truth) {
            throw new IllegalStateException("Failed assertion");
        }
    }
}
