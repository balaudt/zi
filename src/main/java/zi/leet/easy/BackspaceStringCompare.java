package zi.leet.easy;

//https://leetcode.com/problems/backspace-string-compare
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int si = s.length - 1, ti = t.length - 1;
        while (true) {
            if (si < 0 && ti < 0)
                break;
            int del = 0;
            while (si >= 0 && s[si] == '#') {
                si--;
                del++;
            }
            while (del > 0 && si >= 0) {
                if (s[si--] != '#') del--;
                else del++;
            }
            char sc = si >= 0 ? s[si--] : 'A';

            del = 0;
            while (ti >= 0 && t[ti] == '#') {
                ti--;
                del++;
            }
            while (del > 0 && ti >= 0) {
                if (t[ti--] != '#') del--;
                else del++;
            }
            char tc = ti >= 0 ? t[ti--] : 'A';

            if (sc == '#' || tc == '#') {
                if (si >= 0) si++;
                if (ti >= 0) ti++;
            } else {
                if (sc != tc) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BackspaceStringCompare compare = new BackspaceStringCompare();
        System.out.println(compare.backspaceCompare("ab#c", "ad#c"));
        System.out.println(compare.backspaceCompare("ab##", "c#d#"));
        System.out.println(compare.backspaceCompare("a##c", "#a#c"));
        System.out.println(compare.backspaceCompare("a#c", "b"));
        System.out.println(compare.backspaceCompare("y#fo##f", "y#fx#o##f"));
        System.out.println(compare.backspaceCompare("isfcow#", "isfcog#w#"));
    }
}
