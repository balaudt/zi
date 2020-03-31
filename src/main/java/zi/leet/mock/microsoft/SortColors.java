package zi.leet.mock.microsoft;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/sort-colors/
public class SortColors {
	public void sortColors(int[] nums) {
		int n0 = 0, n1 = 0, n2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) n0++;
			else if (nums[i] == 1) n1++;
			else n2++;
		}
		int j = 0;
		for (int i = 0; i < n0; i++) {
			nums[j++] = 0;
		}
		for (int i = 0; i < n1; i++) {
			nums[j++] = 1;
		}
		for (int i = 0; i < n2; i++) {
			nums[j++] = 2;
		}
	}
}
