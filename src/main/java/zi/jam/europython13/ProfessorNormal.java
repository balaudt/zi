package zi.jam.europython13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.lang3.text.StrBuilder;

public class ProfessorNormal {

	static int r;
	static int c;
	static long[][] marbles;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/8/D-small-practice-2.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/ft/8/D-small-practice-2.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			StrBuilder builder = new StrBuilder("Case #").append(i + 1).append(": ");
			r = Integer.parseInt(reader.readLine());
			c = Integer.parseInt(reader.readLine());
			marbles = new long[r][c];
			String in[];
			for (int j = 0; j < r; j++) {
				in = reader.readLine().split(" ");
				for (int k = 0; k < c; k++) {
					marbles[j][k] = Long.parseLong(in[k]);
				}
			}
			int exchangeCount = 0, lastChildCount = -1;
			boolean isInfinite = false;

			do {
				// Step 1
				boolean childRemoved;
				do {
					childRemoved = false;
					for (int j = 0; j < r; j++) {
						for (int k = 0; k < c; k++) {
							if (marbles[j][k] != 0 && (marbles[j][k] < 12 || neighbours(j, k).length == 0)) {
								marbles[j][k] = 0;
								childRemoved = true;
							}
						}
					}
				} while (childRemoved);

				// Step 2
				int childCount = 0;
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (marbles[j][k] != 0) {
							childCount++;
						}
					}
				}
				if (exchangeCount > 100000) {
					isInfinite = true;
					break;
				} else if (childCount == 0) {
					break;
				}
				lastChildCount = childCount;

				// The Loop - Start
				// printMarbles();
				long[][] next = new long[r][c];
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (marbles[j][k] != 0) {
							next[j][k] = marbles[j][k] - 12;
						}
					}
				}
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (marbles[j][k] == 0)
							continue;
						int[][] neighbours = neighbours(j, k);
						int marblesPerNeigh = 12 / neighbours.length;
						for (int[] neighbour : neighbours) {
							next[neighbour[0]][neighbour[1]] += marblesPerNeigh;
						}
					}
				}
				marbles = next;
				exchangeCount++;
				// The Loop - End
				// printMarbles();
			} while (true);

			if (isInfinite) {
				builder.append(lastChildCount).append(" children will play forever\n");
			} else {
				builder.append(exchangeCount).append(" turns\n");
			}
			 System.out.println(builder.toString());
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	static int[][] neighbours(int y, int x) {
		ArrayList<int[]> outputL = new ArrayList<int[]>();
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (i >= 0 && i < r && j >= 0 && j < c && !(j == x && i == y) && marbles[i][j] != 0 && (j == x || i == y)) {
					outputL.add(new int[] { i, j });
				}
			}
		}
		Iterator<int[]> iterator = outputL.iterator();
		int[][] output = new int[outputL.size()][];
		int count = 0;
		while (iterator.hasNext()) {
			output[count++] = iterator.next();
		}
		return output;
	}

	static void printMarbles() {
		for (int j = 0; j < r; j++) {
			System.out.println(Arrays.toString(marbles[j]));
		}
		System.out.println();
	}
}
