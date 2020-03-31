package zi.leet.google;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author balamurugan
 */
public class DailyTemperature {
	public int[] dailyTemperatures(int[] temperatures) {
		Stack<int[]> s = new Stack<>();
		int n = temperatures.length;
		int[] out = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			while (!s.isEmpty() && s.peek()[0] <= temperatures[i]) s.pop();
			if (!s.isEmpty()) out[i] = s.peek()[1] - i;
			s.push(new int[]{temperatures[i], i});
		}
		return out;
	}

	public static void main(String[] args) {
		DailyTemperature dailyTemperature = new DailyTemperature();
		System.out.println(Arrays.toString(dailyTemperature.dailyTemperatures(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70})));
	}
}
