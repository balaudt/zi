package zi.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/word-squares
public class WordSquares {
    class TrieNode {
        char c;
        TrieNode[] children = new TrieNode[26];
        List<Integer> wordInds = new ArrayList<>();

        public TrieNode(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return "TrieNode{" + "c=" + c + ", children=" + Arrays.toString(children) + '}';
        }
    }

    class Trie {
        TrieNode root = new TrieNode(' ');

        void addWord(char[] s, int ind) {
            TrieNode node = root;
            for (char c : s) {
                if (node.children[c - 'a'] != null) {
                    node = node.children[c - 'a'];
                } else {
                    TrieNode t = new TrieNode(c);
                    node.children[c - 'a'] = t;
                    node = t;
                }
                node.wordInds.add(ind);
            }
        }

    }

    List<List<String>> results = new ArrayList<>();
    char[][] in, out;
    int noOfWords, n;
    Trie trie = new Trie();

    public List<List<String>> wordSquares(String[] words) {
        noOfWords = words.length;
        n = words[0].length();
        in = new char[noOfWords][];
        for (int i = 0; i < noOfWords; i++) {
            in[i] = words[i].toCharArray();
            trie.addWord(in[i], i);
        }
        out = new char[n][n];
        for (int i = 0; i < noOfWords; i++) {
            System.arraycopy(in[i], 0, out[0], 0, n);
            backtrack(1);
        }
        return results;
    }

    private void backtrack(int st) {
        if (st == n) {
            List<String> result = new ArrayList<>();
            for (char[] row : out) result.add(new String(row));
            results.add(result);
            return;
        }
        TrieNode node = trie.root;
        for (int i = 0; i < st; i++) {
            char c = out[i][st];
            if (node.children[c - 'a'] == null) {
                node = null;
                break;
            }
            node = node.children[c - 'a'];
        }
        if (node != null) {
            for (Integer wordInd : node.wordInds) {
                System.arraycopy(in[wordInd], 0, out[st], 0, n);
                backtrack(st + 1);
            }
        }
    }

    public static void main(String[] args) {
        WordSquares wordSquares = new WordSquares();
        System.out.println(wordSquares.wordSquares(new String[]{"abat", "baba", "atan", "atal"}));
    }
}
