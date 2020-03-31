package zi.leet.easy;

import java.util.Arrays;

//https://leetcode.com/problems/array-partition-i/
public class ArrayPartitionI {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int out = 0;
        for (int i = 0; i < nums.length; i += 2) {
            out += nums[i];
        }
        return out;
    }

}
