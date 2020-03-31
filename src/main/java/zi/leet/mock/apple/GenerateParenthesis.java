package zi.leet.mock.apple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/generate-parentheses/
public class GenerateParenthesis {
	private List<String> results;
	private int n;

	public List<String> generateParenthesis(int n) {
		this.n = n;
		results = new ArrayList<>();
		generate(0, 0, 0, new char[2 * n]);
		return results;
	}

	private void generate(int open, int close, int ind, char[] out) {
		if (open == n && close == n) {
			results.add(new String(out));
			return;
		}
		if (open < n) {
			out[ind] = '(';
			generate(open + 1, close, ind + 1, out);
		}
		if (close < open) {
			out[ind] = ')';
			generate(open, close + 1, ind + 1, out);
		}
	}
}
