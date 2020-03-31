package zi.leet.mock.microsoft;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/remove-k-digits/
public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
		int n = num.length();
		if (n == k) return "0";
		if (k == 0) return num;
		int r = n - k, inInd = 0;
		char[] out = new char[r];
		for (int i = 0; i < r; i++) {
			int minInd = -1;
			char min = '9' + 1;
			for (int j = inInd; j < n - (r - i) + 1; j++) {
				if (num.charAt(j) < min) {
					min = num.charAt(j);
					minInd = j;
				}
			}
			out[i] = num.charAt(minInd);
			inInd = minInd + 1;
		}
		int zeroCt = 0;
		while (zeroCt < r && out[zeroCt] == '0') zeroCt++;
		if (zeroCt == r) return "0";
		char[] trimmedOut = new char[r - zeroCt];
		System.arraycopy(out, zeroCt, trimmedOut, 0, r - zeroCt);
		return new String(trimmedOut);
	}
}
