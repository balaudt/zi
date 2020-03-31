package zi.leet.easy;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class CandyCrush {
	public int[][] candyCrush(int[][] in) {
		int R = in.length, C = in[0].length;
		int[][] crush = new int[R][C];
		for (int r = 0; r < R; r++) {
			int stInd = 0, ct = 1, last = in[r][0];
			for (int c = 1; c < C; c++) {
				if (in[r][c] == last)
					ct++;
				else {
					if (ct > 2)
						for (int i = stInd; i < c; i++)
							crush[r][i] = -1;
					stInd = c;
					ct = 1;
					last = in[r][c];
				}
			}
			if (ct > 2)
				for (int i = stInd; i < C; i++)
					crush[r][i] = -1;
		}

		for (int c = 0; c < C; c++) {
			int stInd = 0, ct = 1, last = in[0][c];
			for (int r = 1; r < R; r++) {
				if (in[r][c] == last)
					ct++;
				else {
					if (ct > 2)
						for (int i = stInd; i < r; i++)
							crush[i][c] = -1;
					stInd = r;
					ct = 1;
					last = in[r][c];
				}
			}
			if (ct > 2)
				for (int i = stInd; i < R; i++)
					crush[i][c] = -1;
		}

		int[][] next = new int[R][C];
		for (int c = 0; c < C; c++) {
			int ind = R - 1;
			for (int r = R - 1; r >= 0; r--) {
				if (crush[r][c] != -1) {
					next[ind--][c] = in[r][c];
				}
			}
		}

		if (Arrays.deepEquals(in, next))
			return in;
		else
			return candyCrush(next);
	}
}
