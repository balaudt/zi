package zi.leet.easy;

import java.util.*;

//https://leetcode.com/problems/index-pairs-of-a-string
public class IndexPairs {
    class TrieNode {
        char c;
        Map<Character, TrieNode> children = new HashMap<>();
        int wordInd = -1;

        public TrieNode(char c) {
            this.c = c;
        }

        @Override
        public String toString() {
            return c + "\t" + children.keySet();
        }
    }

    class Trie {
        TrieNode root = new TrieNode(' ');

        void addWord(String word, int ind) {
            addWord(word.toCharArray(), ind);
        }

        void addWord(char[] s, int ind) {
            TrieNode node = root;
            for (char c : s) {
                if (node.children.containsKey(c))
                    node = node.children.get(c);
                else {
                    TrieNode t = new TrieNode(c);
                    node.children.put(c, t);
                    node = t;
                }
            }
            node.wordInd = ind;
        }

        List<Integer> lookup(char[] word, int st) {
            List<Integer> out = new ArrayList<>();
            TrieNode node = root;
            for (int i = st; i < word.length; i++) {
                if (!node.children.containsKey(word[i]))
                    break;
                node = node.children.get(word[i]);
                if (node.wordInd != -1) out.add(node.wordInd);
            }
            return out;
        }
    }

    public int[][] indexPairs(String text, String[] words) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++)
            trie.addWord(words[i], i);
        char[] t = text.toCharArray();
        List<int[]> results = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            List<Integer> l = trie.lookup(t, i);
            if (!l.isEmpty())
                for (Integer wordInd : l)
                    results.add(new int[]{i, i + words[wordInd].length() - 1});
        }
        return results.toArray(new int[0][]);
    }

}
