package zi.leet.mock.fb;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/product-of-array-except-self/
public class ProductExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		if (nums == null || nums.length == 0) return nums;
		if (nums.length == 1) return new int[]{1};

		int n = nums.length;
		int[] l = new int[n], r = new int[n];
		l[0] = nums[0];
		r[n - 1] = nums[n - 1];
		for (int i = 1; i < n; i++) {
			l[i] = l[i - 1] * nums[i];
		}
		for (int i = n - 2; i >= 0; i--) {
			r[i] = r[i + 1] * nums[i];
		}
		int[] result = new int[n];
		result[0] = r[1];
		result[n - 1] = l[n - 2];
		for (int i = 1; i < n - 1; i++) {
			result[i] = l[i - 1] * r[i + 1];
		}
		return result;
	}
}
