/*
 * Copyright (c) 2019 Bailey Thompson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.bkthomps.trie;

public final class Trie {

    private static final int LETTERS_IN_ENGLISH = 26;
    private final Node root = new Node();
    private int size;

    private final class Node {

        private boolean isWord;
        private final Node[] letters = new Node[LETTERS_IN_ENGLISH];

        private boolean add(char[] word, int index) {
            if (word.length - 1 == index) {
                boolean wasWord = isWord;
                isWord = true;
                return !wasWord;
            }
            var lettersIndex = word[index] - 'a';
            if (letters[lettersIndex] == null) {
                letters[lettersIndex] = new Node();
            }
            return letters[lettersIndex].add(word, index + 1);
        }

        private boolean contains(char[] word, int index) {
            if (word.length - 1 == index) {
                return isWord;
            }
            var lettersIndex = word[index] - 'a';
            if (letters[lettersIndex] == null) {
                return false;
            }
            return letters[lettersIndex].contains(word, index + 1);
        }

        private boolean remove(char[] word, int index) {
            if (word.length - 1 == index) {
                boolean wasWord = isWord;
                isWord = false;
                return wasWord;
            }
            var lettersIndex = word[index] - 'a';
            if (letters[lettersIndex] == null) {
                return false;
            }
            return letters[lettersIndex].remove(word, index + 1);
        }
    }

    private char[] checkWord(String word) {
        var arr = word.toLowerCase().toCharArray();
        for (var c : arr) {
            if (!Character.isLetter(c)) {
                throw new IllegalArgumentException("Must be a word with only English letters and no accents");
            }
        }
        return arr;
    }

    public void add(String word) {
        if (word.length() == 0) {
            return;
        }
        var arr = checkWord(word);
        boolean added = root.add(arr, 0);
        if (added) {
            size++;
        }
    }

    public boolean contains(String word) {
        if (word.length() == 0) {
            return true;
        }
        var arr = checkWord(word);
        return root.contains(arr, 0);
    }

    public boolean remove(String word) {
        if (word.length() == 0) {
            return true;
        }
        var arr = checkWord(word);
        boolean removed = root.remove(arr, 0);
        if (removed) {
            size--;
        }
        return removed;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
