package zi.leet.easy;

/**
 * @author balamurugan
 */
public class MagicSqaures {
	public int numMagicSquaresInside(int[][] grid) {
		int R = grid.length;
		if (R < 3)
			return 0;
		int C = grid[0].length;
		if (C < 3)
			return 0;
		int result = 0;
		for (int r = 0; r < R; r++) {
			int sum = 0;
			for (int c = 0; c < 3; c++)
				sum += grid[r][c];
			if (sum == 15 && check(grid, r, 0, R, C))
				result++;
			for (int c = 3; c < C; c++) {
				sum -= grid[r][c - 3];
				sum += grid[r][c];
				if (sum == 15 && check(grid, r, c - 2, R, C))
					result++;
			}
		}
		return result;
	}

	private boolean check(int[][] grid, int r, int c, int R, int C) {
		if (r > R - 3 || c > C - 3)
			return false;
		for (int i = c; i < c + 3; i++) {
			int sum = 0;
			for (int j = r; j < r + 3; j++)
				sum += grid[j][i];
			if (sum != 15)
				return false;
		}
		int sum = 0;
		for (int i = r, j = c; i < r + 3; i++, j++)
			sum += grid[i][j];
		if (sum != 15)
			return false;
		sum = 0;
		for (int i = r + 2, j = c + 2; i >= r; i--, j--)
			sum += grid[i][j];
		return sum == 15;
	}
}
