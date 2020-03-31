package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author balamurugan
 */
public class BoldDict {
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

		int searchLongestMatch(char[] s, int stInd) {
			TrieNode node = root;
			int longestMatch = -1, ct = 0;
			for (int i = stInd; i < s.length; i++) {
				if (!node.children.containsKey(s[i]))
					break;
				node = node.children.get(s[i]);
				if (node.wordInd != -1) longestMatch = i;
			}
			return longestMatch;
		}
	}

	public String addBoldTag(String s, String[] dict) {
		char[] in = s.toCharArray();
		Trie trie = new Trie();
		for (int i = 0; i < dict.length; i++) {
			trie.addWord(dict[i], i);
		}
		int lastEnd = -1;
		Deque<int[]> q = new LinkedList<>();
		for (int i = 0; i < in.length; i++) {
			int curEnd = trie.searchLongestMatch(in, i);
			if (curEnd == -1) continue;
			if (curEnd <= lastEnd) continue;
			lastEnd = curEnd;
			if (q.isEmpty()) q.add(new int[]{i, curEnd});
			else {
				if (i > q.peekLast()[1] + 1) {
					q.add(new int[]{i, curEnd});
				} else {
					int[] prev = q.pollLast();
					q.add(new int[]{prev[0], curEnd});
				}
			}
		}
		q.forEach(e -> System.out.println(Arrays.toString(e)));
		lastEnd = -1;
		StringBuilder b = new StringBuilder();
		while (!q.isEmpty()) {
			int[] inter = q.poll();
			if (inter[0] > 0)
				b.append(s, lastEnd + 1, inter[0]);
			b.append("<b>");
			b.append(s, inter[0], inter[1] + 1);
			b.append("</b>");
			lastEnd = inter[1];
		}
		if (lastEnd != in.length) b.append(s, lastEnd + 1, in.length);
		return b.toString();
	}
}
