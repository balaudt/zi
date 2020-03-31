package zi.leet.easy;

/**
 * @author balamurugan
 */
public class MissingElement {
	public int missingElement(int[] nums, int k) {
		int n = nums.length;
		int misses = nums[n - 1] - nums[0] - n + 1;
		if (k > misses)
			return (int) (nums[n - 1] + (long) k - misses);
		for (int i = 1; i < n; i++) {
			misses = nums[i] - nums[i - 1] - 1;
			if (k <= misses)
				return nums[i - 1] + k;
			k -= misses;
		}
		return -1;
	}
}
