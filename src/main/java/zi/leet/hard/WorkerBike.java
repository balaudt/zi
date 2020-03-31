package zi.leet.hard;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class WorkerBike {
	class Result {
		Map<Integer, Integer> assignment = new HashMap<>();
		int dist = Integer.MAX_VALUE;

		Result() {

		}

		Result(int dist) {
			this.dist = dist;
		}
	}

	private int n, m;
	private int[][] workers, bikes;
	private Map<Map.Entry<Integer, Integer>, Result> memo;

	public int[] assignBikes(int[][] workers, int[][] bikes) {
		this.workers = workers;
		this.bikes = bikes;
		n = workers.length;
		m = bikes.length;
		memo = new HashMap<>();
		Result minimum = minimum(0, 0);
		int[] out = new int[workers.length];
		for (int i = 0; i < out.length; i++) {
			out[i] = minimum.assignment.get(i);
		}
		return out;
	}

	private Result minimum(int selWorkers, int selBikes) {
		if (selWorkers == (1 << n) - 1)
			return new Result(0);
		Map.Entry<Integer, Integer> key = new AbstractMap.SimpleEntry<>(selWorkers, selBikes);
		if (memo.containsKey(key))
			return memo.get(key);
		Result result = new Result();
		for (int w = 0; w < n; w++) {
			if (((1 << w) & selWorkers) != 0)
				continue;
			for (int b = 0; b < m; b++) {
				if (((1 << b) & selBikes) != 0)
					continue;
				selWorkers |= (1 << w);
				selBikes |= (1 << b);
				Result subResult = minimum(selWorkers, selBikes);
				int dist = dist(workers[w], bikes[b]) + subResult.dist;
				if (result.dist > dist) {
					result.dist = dist;
					result.assignment.clear();
					result.assignment.put(w, b);
					result.assignment.putAll(subResult.assignment);
				}
				selWorkers &= ~(1 << w);
				selBikes &= ~(1 << b);
			}
		}
		memo.put(key, result);
		return result;
	}

	private int dist(int[] worker, int[] bike) {
		int xDiff = Math.abs(worker[0] - bike[0]);
		int yDiff = Math.abs(worker[1] - bike[1]);
		return xDiff + yDiff;
	}
}
