package zi.leet.mock.amazon;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/moving-average-from-data-stream/
public class MovingAverage {
	private Queue<Integer> q;
	private int sum, k;

	/**
	 * Initialize your data structure here.
	 */
	public MovingAverage(int size) {
		q = new ArrayDeque<>();
		sum = 0;
		k = size;
	}

	public double next(int val) {
		if (q.size() == k) {
			sum -= q.poll();
		}
		sum += val;
		q.offer(val);
		return (double) sum / q.size();
	}
}
