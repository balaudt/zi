package zi.leet.easy;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author balamurugan
 */
public class PathMinMax {
	class Result {
		int min;
		boolean didEnd;

		Result(int min, boolean didEnd) {
			this.min = min;
			this.didEnd = didEnd;
		}

		@Override
		public String toString() {
			return min + "";
		}
	}

	private Map<Map.Entry<Integer, Set<Integer>>, Result> memo;
	private int R, C;
	private int[][] in, neis = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

	public int maximumMinimumPath(int[][] A) {
		memo = new HashMap<>();
		in = A;
		R = in.length;
		C = in[0].length;
		return backtrack(0, 0, new LinkedHashSet<>()).min;
	}

	private Result backtrack(int r, int c, Set<Integer> visited) {
		if (r == R - 1 && c == C - 1)
			return new Result(in[r][c], true);
		int cell = r * C + c;
		Map.Entry<Integer, Set<Integer>> key = new AbstractMap.SimpleEntry<>(cell, visited);
		if (memo.containsKey(key))
			return memo.get(key);
		visited.add(cell);
		int result = Integer.MIN_VALUE;
		boolean didEnd = false;
		for (int[] nei : neis) {
			int nr = nei[0] + r, nc = nei[1] + c;
			if (nr == R || nr < 0) continue;
			if (nc == C || nc < 0) continue;
			int newCell = nr * C + nc;
			if (visited.contains(newCell))
				continue;
			Result partial = backtrack(nr, nc, visited);
			if (partial.didEnd) {
				didEnd = true;
				result = Math.max(result, partial.min);
			}
		}
		visited.remove(cell);
		Result out = new Result(Math.min(in[r][c], result), didEnd);
		memo.put(key, out);
		return out;
	}
}
