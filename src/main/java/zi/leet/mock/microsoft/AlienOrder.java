package zi.leet.mock.microsoft;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
*
 * @author balamurugan
 */
public class AlienOrder {
	class TrieNode {
		char c;
		Map<Character, TrieNode> children = new LinkedHashMap<>();

		TrieNode(char c) {
			this.c = c;
		}
	}

	class Trie {
		TrieNode root = new TrieNode(' ');

		boolean addWord(String word) {
			TrieNode node = root;
			char[] in = word.toCharArray();
			boolean didAdd = false;
			for (int i = 0; i < in.length; i++) {
				if (node.children.containsKey(in[i]))
					node = node.children.get(in[i]);
				else {
					didAdd = true;
					TrieNode child = node.children.getOrDefault(in[i], new TrieNode(in[i]));
					node.children.put(in[i], child);
					node = child;
				}
			}
			return didAdd;
		}
	}

	private Map<Character, Integer> lookup;

	public boolean isAlienSorted(String[] words, String order) {
		Trie trie = new Trie();
		lookup = new HashMap<>();
		for (int i = 0; i < order.length(); i++)
			lookup.put(order.charAt(i), i);
		for (String word : words)
			if (!trie.addWord(word))
				return false;
		return isValid(trie.root);
	}

	private boolean isValid(TrieNode node) {
		int last = -1;
		for (TrieNode child : node.children.values()) {
			int current = lookup.get(child.c);
			if (current < last) return false;
			else {
				last = current;
				if (!isValid(child)) return false;
			}
		}
		return true;
	}
}
