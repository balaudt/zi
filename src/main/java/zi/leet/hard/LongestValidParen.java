package zi.leet.hard;

//https://leetcode.com/problems/longest-valid-parentheses
public class LongestValidParen {
    private Boolean[][] memo;
    private char[] in;
    private int n, res = 1;

    public int longestValidParentheses(String s) {
        in = s.toCharArray();
        n = in.length;
        memo = new Boolean[n][n];
        for (int i = 0; i < n - res; i++) {
            for (int j = i + res; j < n; j++) {
                if (isValid(i, j)) res = j - i + 1;
            }
        }
        return res == 1 ? 0 : res;
    }

    private boolean isValid(int l, int r) {
        if (l > r) return true;
        if (memo[l][r] != null) return memo[l][r];
        if (in[l] == ')' || in[r] == '(' || (r - l) % 2 == 0) {
            memo[l][r] = false;
            return false;
        }
        if (in[l] == '(' && in[r] == ')' && isValid(l + 1, r - 1)) {
            memo[l][r] = true;
            return true;
        }
        for (int i = l + 1; i <= r - 2; i++) {
            if (isValid(l, i) && isValid(i + 1, r)) {
                memo[l][r] = true;
                return true;
            }
        }
        memo[l][r] = false;
        return false;
    }

    public static void main(String[] args) {
        LongestValidParen paren = new LongestValidParen();
        System.out.println(paren.longestValidParentheses("(()"));
        System.out.println(paren.longestValidParentheses(")()())"));
    }
}
