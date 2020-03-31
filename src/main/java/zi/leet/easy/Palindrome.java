package zi.leet.easy;

/**
 * @author balamurugan
 */
public class Palindrome {
	public boolean isPalindrome(String s) {
		char[] in = s.toLowerCase().toCharArray();
		int l = 0, r = in.length - 1;
		while (l < r) {
			if (in[l] < 'a' || in[l] > 'z') {
				l++;
			} else if (in[r] < 'a' || in[r] > 'z') {
				r--;
			} else {
				if (in[l] != in[r]) return false;
				l++;
				r--;
			}
		}
		return true;
	}
}
