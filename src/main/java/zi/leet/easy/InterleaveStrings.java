package zi.leet.easy;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class InterleaveStrings {
	private Boolean[][] memo;
	private char[] s1, s2, s3;

	public boolean isInterleave(String str1, String str2, String str3) {
		s1 = str1.toCharArray();
		s2 = str2.toCharArray();
		s3 = str3.toCharArray();
		memo = new Boolean[s1.length + 1][s2.length + 1];
		if (s3.length != s1.length + s2.length) return false;
		if (s1.length == 0) return str2.equals(str3);
		if (s2.length == 0) return str1.equals(str3);
		boolean result = canInterleave(0, 0);
		return result;
	}

	private boolean canInterleave(int s1i, int s2i) {
		if (s1i == s1.length && s2i == s2.length) return true;
		if (memo[s1i][s2i] != null) return memo[s1i][s2i];
		boolean result = false;
		int s3Ind = s1i + s2i;
		if (s1i < s1.length && s1[s1i] == s3[s3Ind] && canInterleave(s1i + 1, s2i)) result = true;
		if (!result && s2i < s2.length && s2[s2i] == s3[s3Ind] && canInterleave(s1i, s2i + 1)) result = true;
		memo[s1i][s2i] = result;
		return result;
	}

}
