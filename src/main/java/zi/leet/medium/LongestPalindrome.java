package zi.leet.medium;

//https://leetcode.com/problems/longest-palindromic-substring
public class LongestPalindrome {
    Boolean[][] memo;
    char[] in;
    int n, resSt = 0, resLen = 1;

    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;
        in = s.toCharArray();
        n = in.length;
        memo = new Boolean[n][n];
        for (int i = 0; i < in.length - resLen; i++) {
            for (int j = i + resLen; j < in.length; j++) {
                int len = j - i + 1;
                if (len <= resLen) continue;
                if (isPalindrome(i, j)) {
                    resSt = i;
                    resLen = len;
                }
            }
        }
        return new String(in, resSt, resLen);
    }

    private boolean isPalindrome(int st, int end) {
        if (st >= end) return true;
        if (memo[st][end] != null)
            return memo[st][end];
        memo[st][end] = in[st] == in[end] && isPalindrome(st + 1, end - 1);
        return memo[st][end];
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
//        System.out.println(longestPalindrome.longestPalindrome("babad"));
        System.out.println(longestPalindrome.longestPalindrome("cbbd"));
    }
}
