package zi.leet.easy;

import java.util.*;

//https://leetcode.com/problems/groups-of-special-equivalent-strings
public class GroupSpecialEquivalentStrings {

    public int numSpecialEquivGroups(String[] A) {
        Set<Map.Entry<Map<Character, Integer>, Map<Character, Integer>>> groups = new HashSet<>();
        for (String str : A) {
            char[] chars = str.toCharArray();
            Map<Character, Integer> oddChars = new HashMap<>();
            Map<Character, Integer> evenChars = new HashMap<>();
            for (int i = 0; i < chars.length; i++) {
                Map<Character, Integer> map = i % 2 == 0 ? evenChars : oddChars;
                if (map.containsKey(chars[i])) {
                    map.put(chars[i], map.get(chars[i]) + 1);
                } else {
                    map.put(chars[i], 1);
                }
            }
            groups.add(new AbstractMap.SimpleEntry<>(oddChars, evenChars));
        }
        return groups.size();
    }

    public static void main(String[] args) {
        GroupSpecialEquivalentStrings pgm = new GroupSpecialEquivalentStrings();
        System.out.println(pgm.numSpecialEquivGroups(new String[]{"a", "b", "c", "a", "c", "c"}));
        System.out.println(pgm.numSpecialEquivGroups(new String[]{"aa", "bb", "ab", "ba"}));
        System.out.println(pgm.numSpecialEquivGroups(new String[]{"abc", "acb", "bac", "bca", "cab", "cba"}));
        System.out.println(pgm.numSpecialEquivGroups(new String[]{"abcd", "cdab", "adcb", "cbad"}));
    }
}
