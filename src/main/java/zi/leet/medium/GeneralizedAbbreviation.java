package zi.leet.medium;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/generalized-abbreviation
public class GeneralizedAbbreviation {
    private List<String> results = new ArrayList<>();
    private char[] in;

    public List<String> generateAbbreviations(String word) {
        in = word.toCharArray();
        backtrack(0, false, new StringBuilder());
        return results;
    }

    private void backtrack(int st, boolean last, StringBuilder b) {
        if (st == in.length) {
            results.add(b.toString());
            return;
        }
        b.append(in[st]);
        backtrack(st + 1, false, b);
        b.deleteCharAt(b.length() - 1);
        if (last)
            return;
        for (int i = st + 1; i <= in.length; i++) {
            String s = Integer.toString(i - st);
            b.append(s);
            backtrack(i, true, b);
            b.delete(b.length() - s.length(), b.length());
        }
    }

    public static void main(String[] args) {
        System.out.println(new GeneralizedAbbreviation().generateAbbreviations("interaction"));
    }
}
