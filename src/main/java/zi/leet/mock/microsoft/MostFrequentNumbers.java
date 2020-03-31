package zi.leet.mock.microsoft;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/majority-element-ii/
public class MostFrequentNumbers {
	public List<Integer> majorityElement(int[] nums) {
		int ct1 = 0, ct2 = 0, n1 = -1, n2 = -1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == n1) ct1++;
			else if (nums[i] == n2) ct2++;
			else if (ct1 == 0) {
				n1 = nums[i];
				ct1 = 1;
			} else if (ct2 == 0) {
				n2 = nums[i];
				ct2 = 1;
			} else {
				ct1--;
				ct2--;
			}
		}
		ct1 = 0;
		ct2 = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == n1) ct1++;
			else if (nums[i] == n2) ct2++;
		}
		ArrayList<Integer> result = new ArrayList<>();
		if (ct1 > nums.length / 3) result.add(n1);
		if (ct2 > nums.length / 3) result.add(n2);
		return result;
	}
}
