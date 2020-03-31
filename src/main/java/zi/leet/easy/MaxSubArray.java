package zi.leet.easy;

//https://leetcode.com/problems/maximum-subarray
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int runningSum = 0, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (runningSum < nums[i])
                runningSum = nums[i];
            if (maxSum < runningSum)
                maxSum = runningSum;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        System.out.println(msa.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
