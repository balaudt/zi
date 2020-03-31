package zi.leet.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author balamurugan
 */
public class PhoneLetterCombination {
	private static final String[] PHONE_LETTERS = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

	private int[] digitsIndices;

	public List<String> letterCombinations(String digits) {
		if (digits.isEmpty()) {
			return Collections.emptyList();
		}
		char[] digitLetters = digits.toCharArray();
		digitsIndices = new int[digitLetters.length];
		for (int i = 0; i < digitLetters.length; i++) {
			digitsIndices[i] = digitLetters[i] - '0' - 2;
		}
		return letterCombination(0);
	}

	public List<String> letterCombination(int start) {
		if (start == digitsIndices.length) {
			return Collections.singletonList("");
		}
		char[] letters = PHONE_LETTERS[digitsIndices[start]].toCharArray();
		List<String> nextCombinations = letterCombination(start + 1);
		List<String> out = new ArrayList<>(letters.length * nextCombinations.size());
		for (char letter : letters) {
			for (String next : nextCombinations) {
				out.add(letter + next);
			}
		}
		return out;
	}

	public static void main(String[] args) {
		System.out.println(new PhoneLetterCombination().letterCombinations("23"));
	}
}
