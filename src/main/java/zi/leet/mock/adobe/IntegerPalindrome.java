package zi.leet.mock.adobe;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/palindrome-number/
public class IntegerPalindrome {
	public boolean isPalindrome(int x) {
		char[] c = Integer.toString(x).toCharArray();
		for (int i = 0; i < c.length / 2; i++) {
			if (c[i] != c[c.length - i - 1]) return false;
		}
		return true;
	}

}
