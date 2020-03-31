package zi.leet.hard;

//https://leetcode.com/problems/wildcard-matching/
public class WildcardMatching {
    private char[] s, p;
    private Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        this.s = s.toCharArray();
        this.p = p.toCharArray();
        memo = new Boolean[s.length() + 1][p.length()];
        return isMatch(0, 0);
    }

    private boolean isMatch(int si, int pi) {
        if (si == s.length && pi == p.length) return true;
        if (pi == p.length) return false;
        if (memo[si][pi] != null) return memo[si][pi];
        if (si == s.length) {
            memo[si][pi] = p[pi] == '*' && isMatch(si, pi + 1);
            return memo[si][pi];
        }
        boolean doesMatch = false;
        if (p[pi] == '?') doesMatch = isMatch(si + 1, pi + 1);
        else if (p[pi] != '*') doesMatch = s[si] == p[pi] && isMatch(si + 1, pi + 1);
        else {
            for (int i = si; i <= s.length && !doesMatch; i++) {
                doesMatch = isMatch(i, pi + 1);
            }
        }
        memo[si][pi] = doesMatch;
        return doesMatch;
    }
}
