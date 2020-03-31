package zi.leet.hard;

import java.util.Stack;

//https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleHistogram {
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		if (n == 0) return 0;
		if (n == 1) return heights[0];
		int result = Integer.MIN_VALUE;
		int[] left = new int[n], right = new int[n];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 1; i < n; i++) {
			if (heights[i] < heights[i - 1])
				while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) right[stack.pop()] = i;
			stack.push(i);
		}
		while (!stack.isEmpty()) right[stack.pop()] = -1;

		stack.push(n - 1);
		for (int i = n - 2; i >= 0; i--) {
			if (heights[i] < heights[i + 1])
				while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) left[stack.pop()] = i;
			stack.push(i);
		}
		while (!stack.isEmpty()) left[stack.pop()] = -1;

		for (int i = 0; i < n; i++) {
			int l = left[i] == -1 ? 0 : left[i] + 1;
			int r = right[i] == -1 ? n - 1 : right[i] - 1;
			result = Math.max(result, heights[i] * (r - l + 1));
		}

		return result;
	}
}
