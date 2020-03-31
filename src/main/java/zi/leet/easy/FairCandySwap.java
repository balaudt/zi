package zi.leet.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/fair-candy-swap
public class FairCandySwap {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        for (int ai : A) {
            sumA += ai;
        }
        Set<Integer> b = new HashSet<>();
        int sumB = 0;
        for (int bi : B) {
            b.add(bi);
            sumB += bi;
        }
        int diff = (sumB - sumA) / 2;
        for (int ai : A) {
            if (b.contains(diff + ai)) {
                return new int[]{ai, diff + ai};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FairCandySwap fairCandySwap = new FairCandySwap();
        System.out.println(Arrays.toString(fairCandySwap.fairCandySwap(new int[]{1, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(fairCandySwap.fairCandySwap(new int[]{1, 2}, new int[]{2, 3})));
        System.out.println(Arrays.toString(fairCandySwap.fairCandySwap(new int[]{2}, new int[]{1, 3})));
        System.out.println(Arrays.toString(fairCandySwap.fairCandySwap(new int[]{1, 2, 5}, new int[]{2, 4})));
    }
}
