package zi.leet.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author balamurugan
 */
public class PartitionSubset {
	private Set<Integer> memo;
	private boolean[] visited;
	private int setSum;
	private int[] in;

	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++)
			sum += nums[i];
		if (sum % k != 0)
			return false;
		setSum = sum / k;
		in = nums;
		visited = new boolean[nums.length];
		memo = new HashSet<>();
		for (int i = 0; i < nums.length; i++)
			if (nums[i] > setSum)
				return false;
			else if (nums[i] == setSum) {
				visited[i] = true;
				k--;
			} else if (nums[i] == 0)
				visited[i] = true;
		for (int i = 0; i < k; i++) {
			if (!isPossible(setSum, visited))
				return false;
		}
		return true;
	}

	private boolean isPossible(int sum, boolean[] visited) {
		if (sum == 0) {
			return true;
		}
		if (sum < 0) {
			return false;
		}
		if (memo.contains(sum))
			return false;
		for (int i = 0; i < in.length; i++) {
			if (visited[i] || in[i] > sum)
				continue;
			visited[i] = true;
			if (isPossible(sum - in[i], visited))
				return true;
			visited[i] = false;
		}
		memo.add(sum);
		return false;
	}
}
