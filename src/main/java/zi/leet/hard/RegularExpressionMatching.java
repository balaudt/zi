package zi.leet.hard;

//https://leetcode.com/problems/regular-expression-matching
public class RegularExpressionMatching {
    char[] s, p;
    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        memo = new Boolean[s.length()][p.length()];
        return isMatch(0, 0);
    }

    private boolean isMatch(int si, int pi) {
        if (si == s.length && pi == p.length) return true;
        boolean multiMatch = pi < p.length - 1 && p[pi + 1] == '*';
        if (pi == p.length) return false;
        if (si == s.length) return multiMatch && isMatch(si, pi + 2);
        if (memo[si][pi] != null) return memo[si][pi];
        char matchChar = p[pi];
        boolean doesMatch = false;
        if (matchChar == '.') {
            if (!multiMatch) doesMatch = isMatch(si + 1, pi + 1);
            else {
                for (int i = si; i <= s.length && !doesMatch; i++) {
                    doesMatch = isMatch(i, pi + 2);
                }
            }
        } else {
            if (!multiMatch) doesMatch = s[si] == p[pi] && isMatch(si + 1, pi + 1);
            else {
                doesMatch = isMatch(si, pi + 2);
                for (int i = si; i < s.length && !doesMatch && s[i] == p[pi]; i++) {
                    doesMatch = isMatch(i + 1, pi + 2);
                }
            }
        }
        memo[si][pi] = doesMatch;
        return memo[si][pi];
    }

    public static void main(String[] args) {
        RegularExpressionMatching matching = new RegularExpressionMatching();
//        System.out.println(matching.isMatch("aa", "a"));
//        System.out.println(matching.isMatch("aa", "a*"));
//        System.out.println(matching.isMatch("ab", ".*"));
//        System.out.println(matching.isMatch("aab", "c*a*b"));
//        System.out.println(matching.isMatch("ssippi", "s*p*."));
//        System.out.println(matching.isMatch("a", "ab*"));
        System.out.println(matching.isMatch("", ".*"));
    }
}
