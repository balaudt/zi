package zi.leet.mock.adobe;

import java.util.Arrays;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/array-partition-i/
public class ArraySort {
	public int arrayPairSum(int[] nums) {
		Arrays.sort(nums);
		int result = 0;
		for (int i = 0; i < nums.length; i += 2) {
			result += nums[i];
		}
		return result;
	}
}
