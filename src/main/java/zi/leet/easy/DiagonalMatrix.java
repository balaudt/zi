package zi.leet.easy;

/**
 * @author balamurugan
 */
public class DiagonalMatrix {
	public int longestLine(int[][] M) {
		if (M.length == 0) return 0;
		int R = M.length, C = M[0].length;
		int result = 0;

		//Horizontal
		for (int r = 0; r < R; r++) {
			int oneCt = 0;
			for (int c = 0; c < C; c++) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
			}
			result = Math.max(result, oneCt);
		}

		//Vertical
		for (int c = 0; c < C; c++) {
			int oneCt = 0;
			for (int r = 0; r < R; r++) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
			}
			result = Math.max(result, oneCt);
		}

		//Right to left diagonals
		for (int st = 0; st < C; st++) {
			int r = 0, c = st, oneCt = 0;
			while (r < R && c >= 0) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
				r++;
				c--;
			}
			result = Math.max(result, oneCt);
		}
		for (int st = 0; st < R; st++) {
			int r = st, c = C - 1, oneCt = 0;
			while (r < R && c >= 0) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
				r++;
				c--;
			}
			result = Math.max(result, oneCt);
		}

		//Left to right diagonals
		for (int st = 0; st < R; st++) {
			int r = st, c = C-1, oneCt = 0;
			while (r >= 0 && c >= 0) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
				r--;
				c--;
			}
			result = Math.max(result, oneCt);
		}
		for (int st = 0; st < C; st++) {
			int r = R - 1, c = st, oneCt = 0;
			while (r >= 0 && c >= 0) {
				if (M[r][c] == 1)
					oneCt++;
				else {
					result = Math.max(result, oneCt);
					oneCt = 0;
				}
				r--;
				c--;
			}
			result = Math.max(result, oneCt);
		}

		return result;
	}
}
