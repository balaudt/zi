package zi.leet.mock.uber;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/word-pattern/
public class StringPattern {
	public boolean wordPattern(String pattern, String in) {
		char[] p = pattern.toCharArray();
		String[] strs = in.split(" ");
		if (p.length != strs.length) return false;
		Map<Character, String> forwardMap = new HashMap<>();
		Map<String, Character> reverseMap = new HashMap<>();
		for (int i = 0; i < p.length; i++) {
			char c = p[i];
			String str = strs[i];
			if (forwardMap.containsKey(c) && !forwardMap.get(c).equals(str)) return false;
			if (reverseMap.containsKey(str) && !reverseMap.get(str).equals(c)) return false;
			if (!forwardMap.containsKey(c)) {
				forwardMap.put(c, str);
				reverseMap.put(str, c);
			}
		}
		return true;
	}
}
