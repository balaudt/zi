package zi.leet.google;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
public class ReverseVowels {
	public String reverseVowels(String s) {
		char[] chars = s.toCharArray();
		int start = 0, end = s.length() - 1;
		Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
		while (start < end) {
			if (vowels.contains(chars[start])) {
				while (end > start) {
					if (vowels.contains(chars[end])) {
						char temp = chars[start];
						chars[start] = chars[end];
						chars[end] = temp;
						end--;
						break;
					}
					end--;
				}
			}
			start++;
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		ReverseVowels reverseVowels = new ReverseVowels();
//		assert reverseVowels.reverseVowels("hello").equals("holle");
//		assert reverseVowels.reverseVowels("leetcode").equals("leotcede");
		assert reverseVowels.reverseVowels("race car").equals("race car");
	}
}
