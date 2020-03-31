package zi.leet.easy;

//https://leetcode.com/problems/longest-common-prefix
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        int commonPrefixLength = 0;
        while (true) {
            if (strs[0].length() == commonPrefixLength)
                break;
            char currentChar = strs[0].charAt(commonPrefixLength);
            boolean possible = true;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() <= commonPrefixLength || strs[i].charAt(commonPrefixLength) != currentChar) {
                    possible = false;
                    break;
                }
            }
            if (possible)
                commonPrefixLength++;
            else
                break;
        }
        return commonPrefixLength == 0 ? "" : strs[0].substring(0, commonPrefixLength);
    }
}
