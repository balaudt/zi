package zi.leet.mock.adobe;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/reverse-integer/
public class ReverseDigits {
	public int reverse(int x) {
		if (x == 0) return 0;
		boolean isNegative = x < 0;
		int absX = Math.abs(x);
		char[] c = Integer.toString(absX).toCharArray();
		for (int i = 0; i < c.length / 2; i++) {
			char t = c[i];
			c[i] = c[c.length - i - 1];
			c[c.length - i - 1] = t;
		}
		int y;
		try {
			y = Integer.parseInt(new String(c));
		} catch (NumberFormatException e) {
			return 0;
		}
		y = isNegative ? -1 * y : y;
		return y;
	}
}
