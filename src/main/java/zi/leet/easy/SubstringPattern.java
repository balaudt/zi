package zi.leet.easy;

//https://leetcode.com/problems/repeated-substring-pattern/
public class SubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        char cs[] = s.toCharArray();
        int n = cs.length;
        int end = 0;
        for (int i = 1; i < n; i++) {
            int j = 0;
            while (i < n && j <= end && cs[i] == cs[j]) {
                i++;
                j++;
            }
            if (j > end) continue;
            while (n % (i + 1) != 0) i++;
            if (n == i + 1) return false;
            end = i;
        }
        return true;
    }

    public static void main(String[] args) {
        SubstringPattern sp = new SubstringPattern();
        System.out.println(sp.repeatedSubstringPattern("abcabcabcabc"));
    }
}
