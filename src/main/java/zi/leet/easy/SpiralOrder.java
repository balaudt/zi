package zi.leet.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author balamurugan
 */
public class SpiralOrder {
	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0)
			return Collections.emptyList();
		int R = matrix.length;
		int C = matrix[0].length;
		boolean[][] visited = new boolean[R][C];
		int r = 0, c = 0;
		char dir = 'R';
		List<Integer> results = new ArrayList<>();
		while (true) {
			if (r == R || c == C || r < 0 || c < 0 || visited[r][c]) {
				switch (dir) {
					case 'R':
						c--;
						r++;
						dir = 'D';
						break;
					case 'D':
						dir = 'L';
						r--;
						c--;
						break;
					case 'L':
						dir = 'U';
						c++;
						r--;
						break;
					case 'U':
						dir = 'R';
						r++;
						c++;
				}
			}
			if (r == R || c == C || r < 0 || c < 0 || visited[r][c])
				break;
			visited[r][c] = true;
			results.add(matrix[r][c]);
			switch (dir) {
				case 'R':
					c++;
					if (c == C) {
						dir = 'D';
						r++;
						c--;
					}
					break;
				case 'D':
					r++;
					if (r == R) {
						dir = 'L';
						r--;
						c--;
					}
					break;
				case 'L':
					c--;
					if (c < 0) {
						dir = 'U';
						c++;
						r--;
					}
					break;
				case 'U':
					r--;
			}
		}
		return results;
	}

}
