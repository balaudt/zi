package zi.leet.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author balamurugan
 */
public class SummaryRanges {
	public List<String> summaryRanges(int[] nums) {
		if (nums == null || nums.length == 0) {
			return Collections.emptyList();
		}
		List<String> out = new ArrayList<>();
		int rangeStart = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] != nums[i - 1] + 1) {
				if (rangeStart == nums[i - 1]) out.add(nums[i - 1] + "");
				else out.add(rangeStart + "->" + nums[i - 1]);
				rangeStart = nums[i];
			}
		}
		if (rangeStart == nums[nums.length - 1]) out.add(nums[nums.length - 1] + "");
		else out.add(rangeStart + "->" + nums[nums.length - 1]);
		return out;
	}

	public static void main(String[] args) {
		SummaryRanges summaryRanges = new SummaryRanges();
		System.out.println(summaryRanges.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
		System.out.println(summaryRanges.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
	}
}
