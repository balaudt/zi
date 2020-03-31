package zi.leet.mock.uber;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author balamurugan
 */
//https://leetcode.com/problems/01-matrix/
public class ZeroDistance {
	public int[][] updateMatrix(int[][] matrix) {
		Queue<Integer> q = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		int R = matrix.length;
		int C = matrix[0].length;
		int[][] result = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (matrix[r][c] == 0) {
					int cell = r * C + c;
					q.add(cell);
					visited.add(cell);
				}
			}
		}
		int depth = 0;
		while (!q.isEmpty()) {
			Queue<Integer> next = new ArrayDeque<>();
			for (Integer cell : q) {
				int r = cell / C;
				int c = cell % C;
				result[r][c] = depth;
				if (r > 0) process(C, r - 1, c, visited, next);
				if (c > 0) process(C, r, c - 1, visited, next);
				if (c != C - 1) process(C, r, c + 1, visited, next);
				if (r != R - 1) process(C, r + 1, c, visited, next);
			}
			q = next;
			depth++;
		}
		return result;
	}

	private void process(int C, int r, int c, Set<Integer> visited, Queue<Integer> next) {
		int neighbour = r * C + c;
		if (!visited.contains(neighbour)) {
			visited.add(neighbour);
			next.add(neighbour);
		}
	}
}
