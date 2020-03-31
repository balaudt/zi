package zi.leet.mock.fb;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
public class SubarraySumK {
	public int maxSubArrayLen(int[] nums, int k) {
		int n = nums.length;
		if (n == 0) return 0;
		int[] runningSum = new int[n];
		runningSum[0] = nums[0];
		int result = nums[0] == k ? 1 : 0;
		for (int i = 1; i < n; i++) {
			runningSum[i] = runningSum[i - 1] + nums[i];
			if (nums[i] == k) result = 1;
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int sum = runningSum[j] - (i == 0 ? 0 : runningSum[i - 1]);
				if (sum == k) result = Math.max(result, j - i + 1);
			}
		}
		return result;
	}
}
