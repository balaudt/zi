package zi.leet.easy;

//https://leetcode.com/problems/count-and-say
public class CountAndSay {
    public String countAndSay(int n) {
        String out = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder next = new StringBuilder();
            char[] chars = out.toCharArray();
            char lastChar = chars[0];
            int ct = 1;
            for (int j = 1; j < chars.length; j++) {
                if (lastChar == chars[j])
                    ct++;
                else {
                    next.append(ct).append(lastChar);
                    lastChar = chars[j];
                    ct = 1;
                }
            }
            next.append(ct).append(lastChar);
            out = next.toString();
        }
        return out;
    }
}
