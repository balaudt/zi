package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class DictionaryDist {
	class TrieNode {
		char c;
		Map<Character, TrieNode> children = new HashMap<>();
		int ind = -1;

		TrieNode(char c) {
			this.c = c;
		}
	}

	class Trie {
		TrieNode root = new TrieNode(' ');

		void addWord(String word, int ind) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (!node.children.containsKey(c))
					node.children.put(c, new TrieNode(c));
				node = node.children.get(c);
			}
			node.ind = ind;
		}

		BitSet neighbours(String word, BitSet visited) {
			char[] in = word.toCharArray();
			in[0] = '.';
			BitSet out = neighbours(in, visited);
			for (int i = 1; i < in.length; i++) {
				in[i - 1] = word.charAt(i - 1);
				in[i] = '.';
				out.or(neighbours(in, visited));
			}
			return out;
		}

		BitSet neighbours(char[] dotWord, BitSet visited) {
			Set<TrieNode> q = new HashSet<>(Collections.singletonList(root));
			BitSet out = new BitSet();
			for (int i = 0; i < dotWord.length && !q.isEmpty(); i++) {
				Set<TrieNode> next = new HashSet<>();
				for (TrieNode node : q) {
					if (dotWord[i] == '.')
						next.addAll(node.children.values());
					else if (node.children.containsKey(dotWord[i]))
						next.add(node.children.get(dotWord[i]));
				}
				q = next;
			}
			for (TrieNode terNode : q) {
				if (terNode.ind != -1 && !visited.get(terNode.ind))
					out.set(terNode.ind);
			}
			return out;
		}
	}

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		int beginInd = -1, endInd = -1;
		Trie trie = new Trie();
		for (int i = 0; i < wordList.size(); i++) {
			String word = wordList.get(i);
			trie.addWord(word, i);
			if (word.equals(beginWord))
				beginInd = i;
			if (word.equals(endWord))
				endInd = i;
		}
		if (endInd == -1)
			return 0;
		if (beginInd == endInd)
			return 1;

		BitSet visited = new BitSet();
		Set<Integer> words = new HashSet<>();
		if (beginInd >= 0)
			words.add(beginInd);
		else {
			trie.addWord(beginWord, wordList.size());
			words.add(wordList.size());
			wordList = new ArrayList<>(wordList);
			wordList.add(beginWord);
		}
		int dest = 1;
		while (!words.isEmpty()) {
			Set<Integer> next = new HashSet<>();
			for (Integer ind : words) {
				visited.set(ind);
				BitSet neis = trie.neighbours(wordList.get(ind), visited);
				for (int i = neis.nextSetBit(0); i != -1; i = neis.nextSetBit(i + 1)) {
					if (i == endInd)
						return dest + 1;
					next.add(i);
				}
			}
			dest++;
			words = next;
		}
		return 0;
	}
}
