package zi.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author balamurugan
 */
public class MissingNumbers {
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		long cur = lower;
		List<String> result = new ArrayList<>();
		long[] longNums = new long[nums.length];
		for (int i = 0; i < nums.length; i++) {
			longNums[i] = nums[i];
		}
		while (cur <= upper) {
			int ip = Arrays.binarySearch(longNums, cur);
			if (ip >= 0) {
				cur++;
				continue;
			}
			if (cur == upper) {
				result.add("" + upper);
				cur++;
				continue;
			}
			ip = -ip - 1;
			if (ip == nums.length) {
				result.add(cur + "->" + upper);
				break;
			}
			int next = nums[ip];
			if (next == cur + 1) {
				result.add("" + cur);
				cur++;
			} else {
				if (next > upper) {
					result.add(cur + "->" + upper);
					break;
				} else {
					result.add(cur + "->" + (next - 1));
					cur = next;
				}
			}
		}
		return result;
	}
}
