package zi.leet.mock.microsoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class WordLadder {
	class TrieNode {
		char c;
		Map<Character, TrieNode> children = new HashMap<>();
		int wordInd = -1;

		TrieNode(char c) {
			this.c = c;
		}
	}

	class Trie {
		TrieNode root = new TrieNode(' ');

		void addWord(String word, int wordInd) {
			char[] w = word.toCharArray();
			TrieNode node = root;
			for (int i = 0; i < w.length; i++) {
				if (!node.children.containsKey(w[i]))
					node.children.put(w[i], new TrieNode(w[i]));
				node = node.children.get(w[i]);
			}
			node.wordInd = wordInd;
		}
	}

	private char[][] board;
	private int R, C;
	private Set<Integer> results;

	public List<String> findWords(char[][] board, String[] words) {
		results = new HashSet<>();
		Trie trie = new Trie();
		for (int i = 0; i < words.length; i++)
			trie.addWord(words[i], i);
		this.board = board;
		List<List<Integer>> charPos = new ArrayList<>(26);
		for (int i = 0; i < 26; i++) charPos.add(new ArrayList<>());
		R = board.length;
		C = board[0].length;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++)
				charPos.get(board[i][j] - 'a').add(i * C + j);
		for (Map.Entry<Character, TrieNode> entry : trie.root.children.entrySet()) {
			List<Integer> currentCharPos = charPos.get(entry.getKey() - 'a');
			for (int cell : currentCharPos) {
				traverse(entry.getValue(), cell, new HashSet<>(Collections.singletonList(cell)));
			}
		}
		List<String> out = new ArrayList<>(results.size());
		for (int wordInd : results)
			out.add(words[wordInd]);
		return out;
	}

	private void traverse(TrieNode node, int cell, Set<Integer> visited) {
		int r = cell / C;
		int c = cell % C;
		if (node.wordInd != -1) results.add(node.wordInd);
		Map<Character, TrieNode> children = node.children;
		if (r > 0 && children.containsKey(board[r - 1][c])) {
			int nextCell = (r - 1) * C + c;
			traverseChild(nextCell, visited, children.get(board[r - 1][c]));
		}
		if (c > 0 && children.containsKey(board[r][c - 1])) {
			int nextCell = r * C + c - 1;
			traverseChild(nextCell, visited, children.get(board[r][c - 1]));
		}
		if (r < R - 1 && children.containsKey(board[r + 1][c])) {
			int nextCell = (r + 1) * C + c;
			traverseChild(nextCell, visited, children.get(board[r + 1][c]));
		}
		if (c < C - 1 && children.containsKey(board[r][c + 1])) {
			int nextCell = r * C + c + 1;
			traverseChild(nextCell, visited, children.get(board[r][c + 1]));
		}
	}

	private void traverseChild(int nextCell, Set<Integer> visited, TrieNode node) {
		if (!visited.contains(nextCell)) {
			visited.add(nextCell);
			traverse(node, nextCell, visited);
			visited.remove(nextCell);
		}
	}
}
