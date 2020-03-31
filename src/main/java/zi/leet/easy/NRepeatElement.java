package zi.leet.easy;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/n-repeated-element-in-size-2n-array/
public class NRepeatElement {
    public int repeatedNTimes(int[] A) {
        Set<Integer> unique = new HashSet<>();
        for (int i : A) {
            if (unique.contains(i))
                return i;
            else
                unique.add(i);
        }
        return -1;
    }
}
