package zi.leet.mock.amazon;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/greatest-common-divisor-of-strings/
public class StringGCD {
	public String gcdOfStrings(String str1, String str2) {
		int gcd = gcd(str1.length(), str2.length());
		char[] repeat = str1.substring(0, gcd).toCharArray();
		for (int i = 0; i < str1.length(); i++) {
			if (repeat[i % repeat.length] != str1.charAt(i)) return "";
		}
		for (int i = 0; i < str2.length(); i++) {
			if (repeat[i % repeat.length] != str2.charAt(i)) return "";
		}
		return new String(repeat);
	}

	private int gcd(int a, int b) {
		if (a < b) {
			int t = a;
			a = b;
			b = t;
		}
		if (a % b == 0) return b;
		else return gcd(a - b, b);
	}
}
