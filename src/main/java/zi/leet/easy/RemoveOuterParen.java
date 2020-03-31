package zi.leet.easy;

//https://leetcode.com/problems/remove-outermost-parentheses
public class RemoveOuterParen {
    public String removeOuterParentheses(String S) {
        int count = 1;
        StringBuilder builder = new StringBuilder();
        char[] s = S.toCharArray();
        for (int i = 1; i < s.length; i++) {
            if (s[i] == '(') {
                if (count != 0)
                    builder.append('(');
                count++;
            } else {
                count--;
                if (count != 0)
                    builder.append(')');
            }
        }
        return builder.toString();
    }
}
