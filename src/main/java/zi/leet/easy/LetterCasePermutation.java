package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/letter-case-permutation
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<Integer> charLocations = new ArrayList<>();
        char[] str = S.toLowerCase().toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= 'a' && str[i] <= 'z')
                charLocations.add(i);
        }
        int perms = 1 << charLocations.size();
        List<String> out = new ArrayList<>(perms);
        for (int n = 0; n < perms; n++) {
            int t = n, ct = 0;
            while (t > 0) {
                if ((t & 1) == 1)
                    str[charLocations.get(ct)] = Character.toUpperCase(str[charLocations.get(ct)]);
                ct++;
                t >>= 1;
            }
            out.add(new String(str));
            str = S.toLowerCase().toCharArray();
        }
        return out;
    }

    public static void main(String[] args) {
        LetterCasePermutation permutation = new LetterCasePermutation();
        System.out.println(permutation.letterCasePermutation("a1b2"));
        System.out.println(permutation.letterCasePermutation("3z4"));
        System.out.println(permutation.letterCasePermutation("12345"));
    }
}
