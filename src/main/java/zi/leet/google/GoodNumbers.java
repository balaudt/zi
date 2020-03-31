package zi.leet.google;


import java.util.Arrays;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/rotated-digits/
public class GoodNumbers {
	int[] rotations = new int[]{0, 1, 5, -1, -1, 2, 9, -1, 8, 6};

	public int rotatedDigits(int N) {
		int ct = 0;
		for (int i = 0; i <= N; i++) {
			char[] iStr = Integer.toString(i).toCharArray();
			char[] iRotateStr = new char[iStr.length];
			boolean isPossible = true;
			for (int j = 0; j < iStr.length; j++) {
				int rotation = rotations[iStr[j] - '0'];
				if (rotation == -1) {
					isPossible = false;
					break;
				}
				iRotateStr[j] = (char) (rotation + '0');
			}
			if (isPossible && !Arrays.equals(iStr, iRotateStr)) ct++;
		}
		return ct;
	}
}
