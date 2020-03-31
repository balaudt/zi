package zi.leet.easy;

//https://leetcode.com/problems/implement-strstr
public class StrStr {
    public int strStr(String haystack, String needle) {
        char[] hay = haystack.toCharArray();
        char[] need = needle.toCharArray();
        if (need.length == 0) return 0;
        for (int i = 0; i < hay.length - need.length + 1; i++) {
            boolean isMatch = true;
            for (int j = 0; j < need.length && isMatch; j++) {
                if (hay[i + j] != need[j]) isMatch = false;
            }
            if (isMatch)
                return i;
        }
        return -1;
    }
}
