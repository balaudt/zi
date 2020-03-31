package zi.leet.easy;

//https://leetcode.com/problems/valid-palindrome-ii
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        char[] c = s.toCharArray();
        int m = tryPalindrome(c, 0, c.length - 1);
        if (m == -1)
            return true;
        return tryPalindrome(c, m + 1, c.length - 1 - m) == -1
                || tryPalindrome(c, m, c.length - 2 - m) == -1;
    }

    private int tryPalindrome(char[] c, int l, int r) {
        while (l < r && c[l] == c[r]) {
            l++;
            r--;
        }
        return l >= r ? -1 : l;
    }

    public static void main(String[] args) {
        ValidPalindromeII vp = new ValidPalindromeII();
//        System.out.println(vp.validPalindrome("aba"));
        System.out.println(vp.validPalindrome("abca"));
    }
}
