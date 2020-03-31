package zi.leet.easy;

import java.util.Arrays;
import java.util.Random;

/**
 * @author balamurugan
 */
public class WeightedRandom {
	private int[] runningSum;
	private Random random;

	public WeightedRandom(int[] w) {
		runningSum = new int[w.length];
		runningSum[0] = w[0];
		for (int i = 1; i < w.length; i++)
			runningSum[i] = runningSum[i - 1] + w[i];
		random = new Random();
		System.out.println(Arrays.toString(runningSum));
	}

	public int pickIndex() {
		int num = random.nextInt(runningSum[runningSum.length - 1]) + 1;
		int ip = Arrays.binarySearch(runningSum, num);
//		System.out.println(num + "\t" + (ip >= 0 ? ip : (-ip - 1)));
		if (ip >= 0) return ip;
		else return -ip - 1;
	}
}
