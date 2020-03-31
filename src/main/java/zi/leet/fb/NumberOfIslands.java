package zi.leet.fb;

/**
 * @author balamurugan
 */
public class NumberOfIslands {
	private char[][] grid;
	private boolean[][] visited;
	private int R;
	private int C;

	public int numIslands(char[][] grid) {
		this.grid = grid;
		R = grid.length;
		if (R == 0) {
			return 0;
		}
		C = grid[0].length;
		if (C == 0) {
			return 0;
		}
		visited = new boolean[R][C];
		int out = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j] && grid[i][j] == '1') {
					out++;
					visit(i, j);
				}
			}
		}
		return out;
	}

	private void visit(int r, int c) {
		if (visited[r][c]) {
			return;
		}
		visited[r][c] = true;
		if (r < R - 1 && grid[r + 1][c] == '1') {
			visit(r + 1, c);
		}
		if (c < C - 1 && grid[r][c + 1] == '1') {
			visit(r, c + 1);
		}
		if (r > 0 && grid[r - 1][c] == '1') {
			visit(r - 1, c);
		}
		if (c > 0 && grid[r][c - 1] == '1') {
			visit(r, c - 1);
		}
	}

	public static void main(String[] args) {
		NumberOfIslands numberOfIslands = new NumberOfIslands();
//		System.out.println(numberOfIslands.numIslands(generateIn(new String[]{"11110", "11010", "11000", "00000"})));
//		System.out.println(numberOfIslands.numIslands(generateIn(new String[]{"11000", "11000", "00100", "00011"})));
		System.out.println(numberOfIslands.numIslands(generateIn(new String[]{"111", "010", "111"})));
	}

	private static char[][] generateIn(String[] in) {
		char[][] out = new char[in.length][];
		for (int i = 0; i < in.length; i++) {
			out[i] = in[i].toCharArray();
		}
		return out;
	}
}
