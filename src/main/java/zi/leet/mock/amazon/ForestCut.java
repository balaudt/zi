package zi.leet.mock.amazon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/cut-off-trees-for-golf-event/
public class ForestCut {
	int[][] memo;

	public int cutOffTree(List<List<Integer>> forest) {
		int R = forest.size();
		if (R == 0) return 0;
		int C = forest.get(0).size();
		int result = 0;
		int v = R * C;
		memo = new int[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				memo[i][j] = (i == j) ? 0 : -1;
			}
		}
		Map<Integer, List<Integer>> adjacencies = new HashMap<>();
		Map<Integer, Integer> trees = new TreeMap<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int val = forest.get(i).get(j);
				int cell = C * i + j;
				ArrayList<Integer> neighbours = new ArrayList<>();
				adjacencies.put(cell, neighbours);
				if (val == 0) continue;
				if (val != 1) {
					trees.put(val, cell);
				}
				if (i != 0 && forest.get(i - 1).get(j) != 0) neighbours.add((i - 1) * C + j);
				if (j != 0 && forest.get(i).get(j - 1) != 0) neighbours.add(i * C + j - 1);
				if (j != C - 1 && forest.get(i).get(j + 1) != 0) neighbours.add(C * i + j + 1);
				if (i != R - 1 && forest.get(i + 1).get(j) != 0) neighbours.add((i + 1) * C + j);
			}
		}
		int lastCell = 0;
		for (Map.Entry<Integer, Integer> entry : trees.entrySet()) {
			Integer nextCell = entry.getValue();
			int dist = dist(lastCell, nextCell, adjacencies);
			if (dist == 10000)
				return -1;
			result += dist;
			lastCell = nextCell;
		}
		return result;
	}

	private int dist(int src, int dest, Map<Integer, List<Integer>> adjList) {
		if (memo[src][dest] != -1) return memo[src][dest];
		int depth = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(src);
		Set<Integer> visited = new HashSet<>();
		while (!q.isEmpty()) {
			Queue<Integer> next = new ArrayDeque<>();
			boolean unknownExists = false;
			int shortest = Integer.MAX_VALUE;
			for (Integer node : q) {
				memo[src][node] = depth;
				memo[node][src] = depth;
				if (node == dest) return depth;
				if (!unknownExists && memo[node][dest] != -1) {
					shortest = Math.min(memo[node][dest] + depth, shortest);
				} else {
					unknownExists = true;
				}
				visited.add(node);
				adjList.get(node).stream().filter(n -> !visited.contains(n)).forEach(next::add);
			}
			if (!unknownExists) {
				memo[src][dest] = shortest;
				memo[dest][src] = shortest;
				return shortest;
			}
			q = next;
			depth++;
		}
		return 10000;
	}

}
