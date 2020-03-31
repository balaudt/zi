package zi.leet.easy;

import java.util.ArrayDeque;
import java.util.Queue;

//https://leetcode.com/problems/reverse-integer
public class ReverseInteger {
    public static int reverse(int x) {
        Queue<Integer> digits = new ArrayDeque<>();
        boolean isNegative = x < 0;
        x = isNegative ? -x : x;
        while (x > 0) {
            digits.add(x % 10);
            x /= 10;
        }
        int out = 0;
        while (!digits.isEmpty()) {
            out *= 10;
            out += digits.poll();
        }
        out = isNegative ? -out : out;
        return out;
    }
}
