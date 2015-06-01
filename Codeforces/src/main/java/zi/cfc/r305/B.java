package zi.cfc.r305;

import java.util.Scanner;

public class B {

	public static final String FOLDER_ROOT = "C:/ft/26/";
	static boolean[][] b;
	static int n, m;
	static int[] scores;

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "B-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		int q = scanner.nextInt();
		b = new boolean[n][m];
		byte in;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				in = scanner.nextByte();
				if (in == 0)
					b[i][j] = false;
				else
					b[i][j] = true;
			}
		}
		scores = new int[n];
		for (int i = 0; i < n; i++) {
			scores[i] = calculate(i);
		}
		int lastMaxRow, maxScore;
		int[] maxScores = getMaxScore();
		maxScore = maxScores[0];
		lastMaxRow = maxScores[1];
//		print();
//		System.out.println(maxScore);

		int x, y;
		for (int i = 0; i < q; i++) {
			x = scanner.nextInt();
			y = scanner.nextInt();
			b[x - 1][y - 1] = !b[x - 1][y - 1];
			scores[x - 1] = calculate(x - 1);
//			print();
			if (lastMaxRow == x - 1 && scores[x - 1] < maxScore) {
				for (int j = 0; j < n; j++) {
					scores[j] = calculate(j);
				}
				maxScores = getMaxScore();
				maxScore = maxScores[0];
				lastMaxRow = maxScores[1];
			} else if (lastMaxRow == x - 1) {
				maxScore = calculate(x - 1);
			} else if (scores[x - 1] > maxScore) {
				maxScore = scores[x - 1];
				lastMaxRow = x - 1;
			}
			System.out.println(maxScore);
		}
		scanner.close();
	}

	static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(b[i][j] ? 1 : 0);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	static int[] getMaxScore() {
		int maxScore = Integer.MIN_VALUE, ind = -1;
		for (int i = 0; i < n; i++) {
			if (scores[i] > maxScore) {
				maxScore = scores[i];
				ind = i;
			}
		}
		return new int[] { maxScore, ind };
	}

	static int calculate(int row) {
		int maxScore = 0, curScore = 0;
		for (int i = 0; i < m; i++) {
			if (b[row][i]) {
				curScore++;
			} else {
				if (curScore > maxScore)
					maxScore = curScore;
				curScore = 0;
			}
		}
		if (curScore > 0 && curScore > maxScore) {
			return curScore;
		}
		return maxScore;
	}
}
