package zi.leet.mock.apple;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/remove-element/
public class RemoveInstance {
	public int removeElement(int[] nums, int val) {
		int j = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) nums[j++] = nums[i];
		}
		return j;
	}
}
