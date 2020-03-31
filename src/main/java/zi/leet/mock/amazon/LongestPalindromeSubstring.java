package zi.leet.mock.amazon;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromeSubstring {
	private char[] in;
	private Boolean[][] memo;

	public String longestPalindrome(String s) {
		in = s.toCharArray();
		if (in.length == 0) return "";
		int n = in.length;
		memo = new Boolean[n][n];
		int len = 1, st = 0;
		for (int i = 0; i < n - len; i++) {
			for (int j = i + len; j < n; j++) {
				if (isPalindrome(i, j)) {
					len = j - i + 1;
					st = i;
				}
			}
		}
		return s.substring(st, st + len);
	}

	private boolean isPalindrome(int l, int r) {
		if (l >= r) return true;
		if (memo[l][r] != null) return memo[l][r];
		memo[l][r] = in[l] == in[r] && isPalindrome(l + 1, r - 1);
		return memo[l][r];
	}
}
