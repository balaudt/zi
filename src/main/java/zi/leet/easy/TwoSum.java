package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/two-sum
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexToNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexToNum.containsKey(target - nums[i])) {
                return new int[]{indexToNum.get(target - nums[i]), i};
            }
            indexToNum.put(nums[i], i);
        }
        return null;
    }
}
