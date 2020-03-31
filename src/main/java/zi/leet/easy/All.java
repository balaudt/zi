package zi.leet.easy;

import java.util.*;

public class All {

    public boolean isPalindrome(int x) {
        char[] digits = Integer.toString(x).toCharArray();
        for (int i = 0; i < digits.length / 2; i++) {
            if (digits[i] != digits[digits.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> unique = new HashMap<>();
        int out = 0, lastStart = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (unique.containsKey(c[i])) {
                out = Math.max(out, unique.size());
                Integer temp = unique.get(c[i]);
                for (int j = lastStart; j <= temp; j++) {
                    unique.remove(c[j]);
                }
                lastStart = temp + 1;
            }
            unique.put(c[i], i);
        }
        out = Math.max(out, unique.size());
        return out;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(321));
//        System.out.println(romanToInt("MCMXCIV"));
//        System.out.println(longestCommonPrefix(new String[]{"aa", "a"}));
//        System.out.println(removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
//        System.out.println(strStr("hell", "ll"));
//        System.out.println(countAndSay(4));
//        System.out.println(lengthOfLastWord("Hello world"));
//        System.out.println(addBinary("1010", "1011"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
    }
}
