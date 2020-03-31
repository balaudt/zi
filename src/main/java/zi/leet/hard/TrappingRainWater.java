package zi.leet.hard;

//https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
	public int trap(int[] height) {
		int n = height.length;
		if (n == 0 || n == 1) return 0;
		int[] left = new int[n], right = new int[n];
		int max = height[0];
		for (int i = 0; i < n; i++) {
			if (height[i] >= max) {
				left[i] = -1;
				max = height[i];
			} else {
				left[i] = max;
			}
		}
		max = height[n - 1];
		for (int i = n - 1; i >= 0; i--) {
			if (height[i] >= max) {
				right[i] = -1;
				max = height[i];
			} else {
				right[i] = max;
			}
		}

		int result = 0;
		for (int i = 0; i < n; i++) {
			if (left[i] == -1 || right[i] == -1) continue;
			result += Math.min(left[i], right[i]) - height[i];
		}
		return result;
	}
}
