package zi.leet.google;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class FindDifference {
	public char findTheDifference(String s, String t) {
		Map<Character, Integer> frequency = new HashMap<>(s.length());
		for (char c : s.toCharArray()) {
			if (frequency.containsKey(c)) {
				frequency.put(c, frequency.get(c) + 1);
			} else {
				frequency.put(c, 1);
			}
		}
		for (char c : t.toCharArray()) {
			if (frequency.containsKey(c)) {
				Integer ct = frequency.get(c);
				if (ct == 1) {
					frequency.remove(c);
				} else {
					frequency.put(c, ct - 1);
				}
			} else {
				return c;
			}
		}
		return '.';
	}

	public static void main(String[] args) {
		System.out.println(new FindDifference().findTheDifference("abcd", "abcde"));
	}
}
