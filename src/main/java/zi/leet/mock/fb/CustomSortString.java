package zi.leet.mock.fb;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/custom-sort-string/
public class CustomSortString {
	public String customSortString(String S, String T) {
		boolean[] lookup = new boolean[26];
		for (int i = 0; i < S.length(); i++) {
			lookup[S.charAt(i) - 'a'] = true;
		}
		Map<Character, Integer> tFrequency = new HashMap<>();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < T.length(); i++) {
			char c = T.charAt(i);
			if (lookup[c - 'a']) {
				Integer count = tFrequency.getOrDefault(c, 0);
				tFrequency.put(c, count + 1);
			} else {
				builder.append(c);
			}
		}
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (tFrequency.containsKey(c)) {
				for (int j = 0; j < tFrequency.get(c); j++) {
					builder.append(c);
				}
			}
		}
		return builder.toString();
	}
}
