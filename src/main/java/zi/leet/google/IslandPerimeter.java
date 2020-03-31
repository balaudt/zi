package zi.leet.google;

/**
 * @author balamurugan
 */
public class IslandPerimeter {
	public int islandPerimeter(int[][] grid) {
		if (grid.length == 0) {
			return 0;
		}
		int R = grid.length;
		int C = grid[0].length;
		int perimeter = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 0) {
					continue;
				}
				if (j == 0 || grid[i][j - 1] == 0) {
					perimeter += 1;
				}
				if (i == 0 || grid[i - 1][j] == 0) {
					perimeter += 1;
				}
				if (j == C - 1 || grid[i][j + 1] == 0) {
					perimeter += 1;
				}
				if (i == R - 1 || grid[i + 1][j] == 0) {
					perimeter += 1;
				}
			}
		}
		return perimeter;
	}

	public static void main(String[] args) {
		System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{
				{0, 1, 0, 0},
				{1, 1, 1, 0},
				{0, 1, 0, 0},
				{1, 1, 0, 0}
		}));
	}
}
