package zi.leet.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/brace-expansion
public class BraceExpansion {
    private List<List<Character>> in = new ArrayList<>();
    private List<String> results = new ArrayList<>();

    public String[] expand(String S) {
        char[] s = S.toCharArray();
        for (int i = 0; i < s.length; i++) {
            List<Character> next = new ArrayList<>();
            if (s[i] != '{') {
                next.add(s[i]);
            } else {
                i++;
                while (true) {
                    next.add(s[i++]);
                    if (s[i] == '}')
                        break;
                    else
                        i++;
                }
            }
            next.sort(null);
            in.add(next);
        }
        traverse(0, new char[in.size()]);
        return results.toArray(new String[0]);
    }

    private void traverse(int st, char[] out) {
        if (st == in.size()) {
            results.add(new String(out));
            return;
        }
        for (Character c : in.get(st)) {
            out[st] = c;
            traverse(st + 1, out);
        }
    }

    public static void main(String[] args) {
        BraceExpansion expansion = new BraceExpansion();
        System.out.println(Arrays.toString(expansion.expand("abcd")));
    }
}
