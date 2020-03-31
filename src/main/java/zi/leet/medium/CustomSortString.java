package zi.leet.medium;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/custom-sort-string
public class CustomSortString {
    public String customSortString(String S, String T) {
        Set<Character> pos = new LinkedHashSet<>();
        for (int i = 0; i < S.length(); i++)
            pos.add(S.charAt(i));
        Map<Character, Integer> tFreq = new HashMap<>();
        char[] t = T.toCharArray();
        for (char tc : t)
            if (pos.contains(tc)) {
                if (tFreq.containsKey(tc))
                    tFreq.put(tc, tFreq.get(tc) + 1);
                else
                    tFreq.put(tc, 1);
            }
        char[] out = new char[t.length];
        int j = 0;
        for (Character sc : pos) {
            if (tFreq.containsKey(sc))
                for (int i = 0; i < tFreq.get(sc); i++)
                    out[j++] = sc;
        }
        for (char tc : t) {
            if (!pos.contains(tc))
                out[j++] = tc;
        }
        return new String(out);
    }

    public static void main(String[] args) {
        System.out.println(new CustomSortString().customSortString("cba", "abcd"));
    }
}
