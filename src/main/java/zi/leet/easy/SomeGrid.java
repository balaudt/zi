package zi.leet.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author balamurugan
 */
public class SomeGrid {
	private static final int UNFEASIBLE = -2;
	private static final int UNCOMPUTED = -1;
	private int[][][][] shortestDis;
	private int R, C;

	public int shortestDistance(int[][] grid) {
		R = grid.length;
		C = grid[0].length;
		shortestDis = new int[R][C][R][C];
		List<int[]> buildings = new ArrayList<>();
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 1) buildings.add(new int[]{i, j});
				for (int k = 0; k < R; k++)
					for (int l = 0; l < C; l++) {
						if (i == k && j == l)
							shortestDis[i][j][k][l] = 0;
						else if (isNei(i, j, k, l)) {
							shortestDis[i][j][k][l] = grid[i][j] == 0 ? 1 : UNFEASIBLE;
						} else shortestDis[i][j][k][l] = UNCOMPUTED;
					}
			}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == 0) {
					int curSum = 0;
					for (int[] building : buildings) {
						int minDist = shortest(i, j, building[0], building[1], new HashSet<>());
						if (minDist == UNFEASIBLE) {
							curSum = UNFEASIBLE;
							break;
						}
						curSum += minDist;
					}
					if (curSum != UNFEASIBLE) result = Math.min(curSum, result);
				}
			}
		}
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private int shortest(int r1, int c1, int r2, int c2, Set<Integer> visited) {
		visited.add(r1 * C + c1);
		if (shortestDis[r1][c1][r2][c2] != UNCOMPUTED) return shortestDis[r1][c1][r2][c2];
		int[][] neighs = new int[][]{{r1 + 1, c1}, {r1 - 1, c1}, {r1, c1 + 1}, {r1, c1 - 1}};
		int minDist = UNFEASIBLE;
		for (int[] neigh : neighs) {
			int r3 = neigh[0], c3 = neigh[1];
			int cell = r3 * C + c3;
			if (!visited.contains(cell) && r3 >= 0 && r3 < R && c3 >= 0 && c3 < C) {
				int dist = shortest(r3, c3, r2, c2, visited);
				if (dist != UNFEASIBLE) {
					if (minDist == UNFEASIBLE) minDist = dist;
					else minDist = Math.min(dist, minDist);
				}
			}
		}
		shortestDis[r1][c1][r2][c2] = minDist;
		return minDist;
	}

	private boolean isNei(int r1, int c1, int r2, int c2) {
		return (r1 == r2 && Math.abs(c1 - c2) == 1) || (c1 == c2 && Math.abs(r1 - r2) == 1);
	}
}
