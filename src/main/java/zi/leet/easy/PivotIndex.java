package zi.leet.easy;

//https://leetcode.com/problems/find-pivot-index
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
        }
        int t = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = runningSum - nums[i];
            if (x % 2 == 0 && t == x / 2)
                return i;
            t += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex pivotIndex = new PivotIndex();
        System.out.println(pivotIndex.pivotIndex(new int[]{-1, -1, -1, -1, -1, -1}));
    }
}
