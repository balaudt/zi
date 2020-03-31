package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/self-dividing-numbers
public class SelfDivide {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> out = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDivide(i))
                out.add(i);
        }
        return out;
    }

    private boolean isSelfDivide(int n) {
        char[] digits = Integer.toString(n).toCharArray();
        for (char digit : digits) {
            if (digit == '0' || n % (digit - '0') != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new SelfDivide().selfDividingNumbers(1, 22));
    }
}
