package zi.leet.google;

import java.util.ArrayList;
import java.util.List;

/**
 * @author balamurugan
 */
public class DisappearedNumbers {
	public List<Integer> findDisappearedNumbers(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int ind = Math.abs(nums[i]) - 1;
			if (nums[ind] > 0) nums[ind] *= -1;
		}
		List<Integer> out = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) out.add(i + 1);
		}
		return out;
	}

	public static void main(String[] args) {
		System.out.println(new DisappearedNumbers().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
	}
}
