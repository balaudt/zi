package zi.leet.easy;

/**
 * @author balamurugan
 */
public class PalindromeWithEdit {
	private char[] in;

	public boolean validPalindrome(String s) {
		in = s.toCharArray();
		int[] ip = isPalindrome(0, in.length - 1);
		if (ip == null) return true;
		return isPalindrome(ip[0] + 1, ip[1]) == null || isPalindrome(ip[0], ip[1] - 1) == null;
	}

	private int[] isPalindrome(int l, int r) {
		while (l < r) {
			if (in[l] != in[r]) return new int[]{l, r};
			l++;
			r--;
		}
		return null;
	}
}
