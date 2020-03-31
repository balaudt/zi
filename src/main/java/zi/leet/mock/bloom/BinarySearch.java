package zi.leet.mock.bloom;

import java.util.Arrays;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/search-insert-position/
public class BinarySearch {
	public int searchInsert(int[] nums, int target) {
		int ip = Arrays.binarySearch(nums, target);
		return ip >= 0 ? ip : -ip - 1;
	}
}
