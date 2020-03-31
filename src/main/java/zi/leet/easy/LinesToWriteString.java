package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/number-of-lines-to-write-string
public class LinesToWriteString {
    public int[] numberOfLines(int[] widths, String S) {
        int lineNo = 1, column = 0;
        for (char c : S.toCharArray()) {
            int currentCharWidth = widths[c - 'a'];
            if (currentCharWidth > 100 - column) {
                lineNo++;
                column = currentCharWidth;
            } else {
                column += currentCharWidth;
            }
        }
        return new int[]{lineNo, column};
    }

    public static void main(String[] args) {
        LinesToWriteString clz = new LinesToWriteString();
        System.out.println(Arrays.toString(clz.numberOfLines(new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "abcdefghijklmnopqrstuvwxyz")));
        System.out.println(Arrays.toString(clz.numberOfLines(new int[]{4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, "bbbcccdddaaa")));
    }
}
