package zi.jam.y18.qual;

import java.util.Scanner;

/**
 * @author balamurugan
 */
public class GoGopher {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt();
			//grid size
			int gs = (a == 20) ? 5 : 15;
			boolean orchard[][] = new boolean[gs][gs];
			while (true) {
				int maxScore = 0;
				int posMaxScore = Math.min(9, gs * gs - fillCount(orchard));
				int[] nextCell = new int[2];
				for (int j = 1; j <= (gs - 2) && maxScore < posMaxScore; j++) {
					for (int k = 1; k <= (gs - 2) && maxScore < posMaxScore; k++) {
						int score = 0;
						for (int l = j - 1; l <= j + 1; l++) {
							for (int m = k - 1; m <= k + 1; m++) {
								if (!orchard[l][m]) {
									score++;
								}
							}
						}
						if (score > maxScore) {
							nextCell[0] = j;
							nextCell[1] = k;
							maxScore = score;
						}
					}
				}
				System.out.println(String.format("%d %d", nextCell[0] + 1, nextCell[1] + 1));
				nextCell[0] = scanner.nextInt();
				nextCell[1] = scanner.nextInt();
				if (nextCell[0] == -1 && nextCell[1] == -1) {
					throw new RuntimeException();
				}
				if (nextCell[0] == 0 && nextCell[1] == 0) {
					break;
				}
				orchard[nextCell[0] - 1][nextCell[1] - 1] = true;
			}
		}
	}

	private static int fillCount(boolean orchard[][]) {
		int out = 0;
		for (boolean[] row : orchard) {
			for (boolean cell : row) {
				if (cell) {
					out++;
				}
			}
		}
		return out;
	}
}
