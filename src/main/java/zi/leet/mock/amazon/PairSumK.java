package zi.leet.mock.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/two-sum/
public class PairSumK {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, List<Integer>> lookup = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			List<Integer> indices = lookup.getOrDefault(nums[i], new ArrayList<>());
			indices.add(i);
			lookup.put(nums[i], indices);
		}
		for (int i = 0; i < nums.length; i++) {
			int other = target - nums[i];
			if (lookup.containsKey(other)) {
				List<Integer> indices = lookup.get(other);
				for (Integer otherIndex : indices) {
					if (otherIndex != i) return new int[]{i, otherIndex};
				}
			}
		}
		return null;
	}
}
