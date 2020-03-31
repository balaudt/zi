package zi.leet.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author balamurugan
 */
public class SpiralMatrix {
	public static void main(String[] args) {
		System.out.println(new SpiralMatrix().spiralOrder(new int[][]{
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		}));
		System.out.println(new SpiralMatrix().spiralOrder(new int[][]{
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12}
		}));
	}

	private enum Direction {
		RIGHT, DOWN, LEFT, UP;
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		int R = matrix.length;
		if (R == 0) {
			return Collections.emptyList();
		}
		int C = matrix[0].length;
		if (C == 0) {
			return Collections.emptyList();
		}
		List<Integer> out = new ArrayList<>(R * C);
		boolean[][] visited = new boolean[R][C];
		Direction direction = Direction.RIGHT;
		int r = 0, c = 0;
		while (out.size() < R * C) {
			if (c == C) {
				direction = Direction.DOWN;
				c -= 1;
				r += 1;
			} else if (r == R) {
				direction = Direction.LEFT;
				r -= 1;
				c -= 1;
			} else if (c == -1) {
				direction = Direction.UP;
				r -= 1;
				c += 1;
			} else if (visited[r][c]) {
				switch (direction) {
					case RIGHT:
						direction = Direction.DOWN;
						c -= 1;
						r += 1;
						break;
					case DOWN:
						direction = Direction.LEFT;
						r -= 1;
						c -= 1;
						break;
					case LEFT:
						direction = Direction.UP;
						r -= 1;
						c += 1;
						break;
					case UP:
						direction = Direction.RIGHT;
						r += 1;
						c += 1;
				}
			} else {
				out.add(matrix[r][c]);
				visited[r][c] = true;
				switch (direction) {
					case RIGHT:
						c += 1;
						break;
					case DOWN:
						r += 1;
						break;
					case LEFT:
						c -= 1;
						break;
					case UP:
						r -= 1;
				}
			}
		}
		return out;
	}
}
