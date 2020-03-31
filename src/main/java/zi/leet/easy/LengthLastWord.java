package zi.leet.easy;

//https://leetcode.com/problems/length-of-last-word
public class LengthLastWord {
    public int lengthOfLastWord(String s) {
        if (s.isEmpty()) return 0;
        int ind = s.lastIndexOf(' ');
        return s.length() - ind - 1;
    }

}
