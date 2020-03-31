package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/valid-anagram
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        char[] sc = s.toCharArray();
        Arrays.sort(sc);
        char[] tc = t.toCharArray();
        Arrays.sort(tc);
        return Arrays.equals(sc, tc);
    }
}
