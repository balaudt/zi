package zi.leet.easy;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/power-of-three
public class PowerOf3 {
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        if (n == 1) return true;
        List<Integer> powerOf3 = new ArrayList<>();
        int p3 = 3;
        while (p3 > 0 && p3 < Integer.MAX_VALUE) {
            powerOf3.add(p3);
            p3 *= 3;
        }
        for (Integer t : powerOf3) {
            while (n % t == 0)
                n /= t;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        PowerOf3 p3 = new PowerOf3();
        System.out.println(p3.isPowerOfThree(9));
    }
}
