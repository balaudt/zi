package zi.leet.mock.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/most-common-word/
public class MostFrequentWord {
	public String mostCommonWord(String paragraph, String[] banned) {
		Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
		String[] words = paragraph.toLowerCase().split("[!?',. ;]");
		Map<String, Integer> frequencies = new HashMap<>();
		for (String word : words) {
			word = word.trim();
			if (!word.isEmpty() && !bannedWords.contains(word)) {
				Integer count = frequencies.getOrDefault(word, 0);
				frequencies.put(word, count + 1);
			}
		}
		String out = null;
		int maxFreq = 0;
		for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
			if (entry.getValue() > maxFreq) {
				maxFreq = entry.getValue();
				out = entry.getKey();
			}
		}
		return out;
	}
}
