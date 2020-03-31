package zi.leet.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class WordBreak {
	private Boolean[] triedIndices;
	private char[] s;
	private Set<char[]> dict;

	public boolean wordBreak(String s, List<String> wordDict) {
		this.s = s.toCharArray();
		dict = new HashSet<>(wordDict.size());
		wordDict.forEach(word -> dict.add(word.toCharArray()));
		triedIndices = new Boolean[s.length()];
		return wordBreak(0);
	}

	private boolean wordBreak(int start) {
		if (start == s.length) {
			return true;
		}
		if (triedIndices[start] != null) {
			return triedIndices[start];
		}
		for (char[] word : dict) {
			boolean possible = true;
			for (int i = 0; i < word.length; i++) {
				if (i + start == s.length) {
					possible = false;
					break;
				}
				if (s[i + start] != word[i]) {
					possible = false;
					break;
				}
			}
			if (possible && wordBreak(start + word.length)) {
				triedIndices[start] = true;
				return true;
			}
		}
		triedIndices[start] = false;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new WordBreak().wordBreak("leetcode", Arrays.asList("leet", "code")));
		System.out.println(new WordBreak().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
		System.out.println(new WordBreak().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
	}
}
