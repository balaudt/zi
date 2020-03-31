package zi.leet.easy;

//https://leetcode.com/problems/detect-capital
public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        int capCount = 0;
        boolean hasSmall = false;
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                capCount++;
            } else {
                hasSmall = true;
            }
        }
        return !hasSmall || capCount == 0 || (capCount == 1 && Character.isUpperCase(word.charAt(0)));
    }
}
