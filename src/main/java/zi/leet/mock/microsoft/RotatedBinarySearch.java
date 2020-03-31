package zi.leet.mock.microsoft;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class RotatedBinarySearch {
	public int search(int[] nums, int target) {
		int n = nums.length;
		if (n == 0) return -1;
		if (n == 1) return nums[0] == target ? 0 : -1;
		int l = 0, r = n - 1, mid, pivot = -1;
		while (l < r) {
			if (r - l == 1 && nums[l] > nums[r]) {
				pivot = r;
				break;
			}
			mid = (l + r) / 2;
			if (nums[mid] > nums[l]) {
				l = mid;
			} else {
				r = mid;
			}
		}
		if (pivot == -1) {
			int ip = Arrays.binarySearch(nums, target);
			return ip >= 0 ? ip : -1;
		} else {
			int ip = Arrays.binarySearch(nums, 0, pivot, target);
			if (ip >= 0) return ip;
			ip = Arrays.binarySearch(nums, pivot, n, target);
			return ip >= 0 ? ip : -1;
		}
	}
}
