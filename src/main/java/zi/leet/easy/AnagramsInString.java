package zi.leet.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class AnagramsInString {
    public List<Integer> findAnagrams(String S, String P) {
        List<Integer> out = new ArrayList<>();
        if (P == null || S == null || P.length() > S.length())
            return out;
        Map<Character, Integer> f = new HashMap<>();
        char p[] = P.toCharArray();
        for (int i = 0; i < p.length; i++)
            if (f.containsKey(p[i]))
                f.put(p[i], f.get(p[i]) + 1);
            else
                f.put(p[i], 1);
        char s[] = S.toCharArray();
        Map<Character, Integer> t = new HashMap<>();
        int i;
        for (i = 0; i < p.length; i++)
            if (t.containsKey(s[i]))
                t.put(s[i], t.get(s[i]) + 1);
            else
                t.put(s[i], 1);
        while (true) {
            if (f.equals(t)) out.add(i - p.length);
            if (i == s.length) break;
            char c = s[i - p.length];
            int n = t.get(c);
            if (n == 1)
                t.remove(c);
            else
                t.put(c, n - 1);
            if (t.containsKey(s[i]))
                t.put(s[i], t.get(s[i]) + 1);
            else
                t.put(s[i], 1);
            i++;
        }
        return out;
    }
}
