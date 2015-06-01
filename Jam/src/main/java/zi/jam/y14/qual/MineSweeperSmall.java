package zi.jam.y14.qual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MineSweeperSmall {
	static int[][] mines;
	static int[][] counts;
	static boolean[][] isMines;
	static boolean[][] isRevealed;
	static int r, c, m;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C-small-attempt0.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C-small-attempt0.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] curInps = reader.readLine().split(" ");
			r = Integer.parseInt(curInps[0]);
			c = Integer.parseInt(curInps[1]);
			m = Integer.parseInt(curInps[2]);
			mines = new int[m][2];
			Combination combination = new Combination(r * c, m);
			boolean layoutFound = false;

			StringBuilder builder = new StringBuilder("Case #").append(i + 1).append(":").append('\n');
			while (!layoutFound && combination.hasNext()) {
				int[] mineLocs = combination.next();
				isMines = new boolean[r][c];
				counts = new int[r][c];
				loadMines(mineLocs);
				for (int j = 0; j < r && !layoutFound; j++) {
					for (int k = 0; k < c; k++) {
						isRevealed = new boolean[r][c];
						if (!isMines[j][k]) {
							reveal(j, k);
							if (isAllRevealed()) {
								layoutFound = true;
								builder.append(layout(j, k));
								break;
							}
						}
					}
				}
			}
			if (!layoutFound) {
				builder.append("Impossible").append('\n');
			}
			writer.write(builder.toString());
		}
		reader.close();
		writer.close();
	}

	static void reveal(int y, int x) {
		isRevealed[y][x] = true;
		if (counts[y][x] == 0) {
			int[][] neighbours = neighbours(y, x);
			for (int i = 0; i < neighbours.length; i++) {
				int neighbourY = neighbours[i][0];
				int neighbourX = neighbours[i][1];
				if (!isMines[neighbourY][neighbourX] && !isRevealed[neighbourY][neighbourX]) {
					reveal(neighbourY, neighbourX);
				}
			}
		}
	}

	static String layout(int y, int x) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (isMines[i][j]) {
					builder.append('*');
				} else if (y == i && x == j) {
					builder.append('c');
				} else {
					builder.append('.');
				}
			}
			builder.append('\n');
		}
		// builder.append('\n');
		return builder.toString();
	}

	static boolean isAllRevealed() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (!isMines[i][j] && !isRevealed[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	static void loadMines(int[] mineLocs) {
		int mineX, mineY;
		for (int i = 0; i < mineLocs.length; i++) {
			mineY = mines[i][0] = mineLocs[i] / c;
			mineX = mines[i][1] = mineLocs[i] % c;
			isMines[mineY][mineX] = true;
			int[][] neighbours = neighbours(mineY, mineX);
			for (int j = 0; j < neighbours.length; j++) {
				counts[neighbours[j][0]][neighbours[j][1]]++;
			}
		}
	}

	static int[][] neighbours(int y, int x) {
		ArrayList<int[]> outputL = new ArrayList<>();
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (i >= 0 && i < r && j >= 0 && j < c && !(j == x && i == y)) {
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
}

class Combination {
	private int n, r;
	private int[] index;
	private boolean hasNext = true;

	public Combination(int n, int r) {
		this.n = n;
		this.r = r;
		index = new int[r];
		for (int i = 0; i < r; i++)
			index[i] = i;
	}

	public boolean hasNext() {
		return hasNext;
	}

	// Based on code from KodersCode:
	// The algorithm is from Applied Combinatorics, by Alan Tucker.
	// Move the index forward a notch. The algorithm finds the rightmost
	// index element that can be incremented, increments it, and then
	// changes the elements to the right to each be 1 plus the element on their
	// left.
	//
	// For example, if an index of 5 things taken 3 at a time is at {0 3 4},
	// only the 0 can
	// be incremented without running out of room. The next index is {1, 1+1,
	// 1+2) or
	// {1, 2, 3}. This will be followed by {1, 2, 4}, {1, 3, 4}, and {2, 3, 4}.

	private void moveIndex() {
		int i = rightmostIndexBelowMax();
		if (i >= 0) {
			index[i] = index[i] + 1;
			for (int j = i + 1; j < r; j++)
				index[j] = index[j - 1] + 1;
		} else
			hasNext = false;
	}

	public int[] next() {
		if (!hasNext)
			return null;
		int[] result = new int[r];
		for (int i = 0; i < r; i++)
			result[i] = index[i];
		moveIndex();
		return result;
	}

	// return int,the index which can be bumped up.
	private int rightmostIndexBelowMax() {
		for (int i = r - 1; i >= 0; i--)
			if (index[i] < n - r + i)
				return i;
		return -1;
	}
}