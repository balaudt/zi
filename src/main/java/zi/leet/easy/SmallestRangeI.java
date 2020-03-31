package zi.leet.easy;

//https://leetcode.com/problems/smallest-range-i
public class SmallestRangeI {
    public int smallestRangeI(int[] A, int K) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : A) {
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
        return Math.max(0, max - min - 2 * K);
    }

    public static void main(String[] args) {
        SmallestRangeI smallestRangeI = new SmallestRangeI();
        System.out.println(smallestRangeI.smallestRangeI(new int[]{1}, 0));
        System.out.println(smallestRangeI.smallestRangeI(new int[]{0, 10}, 2));
    }
}
