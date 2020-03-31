package zi.leet.easy;

//https://leetcode.com/problems/house-robber
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int t2 = Integer.MIN_VALUE, t1 = Integer.MIN_VALUE;
        t2 = nums[0];
        t1 = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int t = Math.max(t1, nums[i] + t2);
            t2 = t1;
            t1 = t;
        }
        return t1;
    }
}
