package zi.leet.mock.microsoft;

/**
 * @author balamurugan
 */
public class Minesweeper {
	private char[][] board;
	private int R, C;

	public char[][] updateBoard(char[][] board, int[] click) {
		this.board = board;
		R = board.length;
		C = board[0].length;
		reveal(click[0], click[1]);
		return this.board;
	}

	private void reveal(int r, int c) {
		if (board[r][c] == 'M') {
			board[r][c] = 'X';
			return;
		}
		int adjMine = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int nr = r + i, nc = c + j;
				if (nr == r && nc == c) continue;
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && board[nr][nc] == 'M') adjMine++;
			}
		}
		if (adjMine > 0) {
			board[r][c] = (char) ('0' + adjMine);
			return;
		}
		board[r][c] = 'B';
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int nr = r + i, nc = c + j;
				if (nr == r && nc == c) continue;
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && board[nr][nc] == 'E') reveal(nr, nc);
			}
		}
	}
}
