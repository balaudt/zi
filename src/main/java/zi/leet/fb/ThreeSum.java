package zi.leet.fb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		Set<List<Integer>> out = new HashSet<>();
		int n = nums.length;
		for (int i = 0; i < n - 2; i++) {
			int l = i + 1, r = n - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum == 0) {
					out.add(Arrays.asList(nums[i], nums[l], nums[r]));
					l++;
					r--;
				} else if (sum > 0) {
					r--;
				} else {
					l++;
				}
			}
		}
		return new ArrayList<>(out);
	}

	public static void main(String[] args) {
		ThreeSum threeSum = new ThreeSum();
		System.out.println(threeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
		System.out.println(threeSum.threeSum(new int[]{0, 0, 0, 0}));
		System.out.println(threeSum.threeSum(new int[]{-2,0,1,1,2}));
	}
}
