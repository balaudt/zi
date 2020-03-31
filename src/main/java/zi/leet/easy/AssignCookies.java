package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/assign-cookies
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        if (s.length == 0 || g.length == 0) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int si = 0, gi = 0;
        for (; gi < g.length; gi++) {
            if (g[gi] <= s[si])
                si++;
            else
                break;
        }
        return gi;
    }
}
