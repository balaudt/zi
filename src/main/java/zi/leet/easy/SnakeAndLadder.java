package zi.leet.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author balamurugan
 */
public class SnakeAndLadder {
	private Map<Integer, Integer> memo;
	private int[][] board;
	private int n;
	private boolean[] visited;

	public int snakesAndLadders(int[][] board) {
		n = board.length;
		memo = new HashMap<>();
		visited = new boolean[n * n];
		memo.put(n * n, 0);
		this.board = board;
		int result = traverse(1);
		return result == Integer.MAX_VALUE ? -1 : result;
	}

	private int traverse(int cell) {
		if (memo.containsKey(cell)) {
			return memo.get(cell);
		}
		if (visited[cell - 1])
			return Integer.MAX_VALUE;
		visited[cell - 1] = true;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 6 && (cell + i) <= n * n; i++) {
			int[] rc = convert(i + cell);
			if (board[rc[0]][rc[1]] != -1)
				min = Math.min(min, traverse(board[rc[0]][rc[1]]));
			else
				min = Math.min(min, traverse(i + cell));
		}
		memo.put(cell, min == Integer.MAX_VALUE ? min : min + 1);
		return min == Integer.MAX_VALUE ? min : min + 1;
	}

	private int[] convert(int alternate) {
		alternate -= 1;
		int r = (n - 1) - alternate / n;
		int c = alternate % n;
		if (r % 2 == n % 2)
			c = n - 1 - c;
		return new int[]{r, c};
	}
}
