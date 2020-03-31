package zi.leet.fb;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class RotatedSortedSearch {
	public int search(int[] nums, int target) {
		int n = nums.length;
		if (n == 0) {
			return -1;
		}
		if (n == 1) {
			return nums[0] == target ? 0 : -1;
		}
		return rotateSearch(nums, target, 0, n - 1);
	}

	private int rotateSearch(int[] nums, int target, int st, int end) {
		int mid = st + (end - st) / 2;
		if (nums[st] < nums[mid] && target >= nums[st] && target <= nums[mid]) {
			return Arrays.binarySearch(nums, target, st, mid);
		}
		if (nums[mid] < nums[end] && target >= nums[mid] && target <= nums[end]) {
			return Arrays.binarySearch(nums, target, mid, end);
		}
		if (nums[st] > nums[mid]) {
			return rotateSearch(nums, target, st, mid);
		} else if (nums[mid] > nums[end]) {
			return rotateSearch(nums, target, mid, end);
		} else {
			return -1;
		}
	}

	public static void main(String[] args) {
		RotatedSortedSearch rotatedSortedSearch = new RotatedSortedSearch();
		System.out.println(rotatedSortedSearch.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
		System.out.println(rotatedSortedSearch.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
	}
}
