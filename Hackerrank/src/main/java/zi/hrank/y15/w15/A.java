package zi.hrank.y15.w15;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/27/";

	static byte[][] curBoard = new byte[9][9];

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "A-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		StringBuffer builder = new StringBuffer();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k = 0; k < 9; k++) {
					curBoard[j][k] = scanner.nextByte();
				}
			}
			System.out.println("Case #" + (i + 1) + ":");
			if (isValid()) {
				System.out.println("Serendipity");
				continue;
			}
			Combination comb = new Combination(81, 2);
			while (comb.hasNext()) {
				int[] swapCells = comb.next();
				swapCells(swapCells[0], swapCells[1]);
				if (isValid()) {
					builder.setLength(0);
					builder.append('(').append(swapCells[0] / 9 + 1).append(',').append(swapCells[0] % 9 + 1).append(") <-> (")
							.append(swapCells[1] / 9 + 1).append(',').append(swapCells[1] % 9 + 1).append(')');
					System.out.println(builder.toString());
				}
				swapCells(swapCells[0], swapCells[1]);
			}
		}
		scanner.close();
	}

	static void swapCells(int c1, int c2) {
		int c1Row = c1 / 9;
		int c1Col = c1 % 9;
		int c2Row = c2 / 9;
		int c2Col = c2 % 9;
		byte temp = curBoard[c1Row][c1Col];
		curBoard[c1Row][c1Col] = curBoard[c2Row][c2Col];
		curBoard[c2Row][c2Col] = temp;
	}

	static boolean isValid() {
		for (byte i = 0; i < 9; i++) {
			Set<Byte> rowBytes = new HashSet<Byte>();
			Set<Byte> colBytes = new HashSet<Byte>();
			Set<Byte> gridBytes = new HashSet<Byte>();
			byte colPref = (byte) (i % 3);
			byte rowPref = (byte) (i / 3);
			for (byte j = 0; j < 9; j++) {
				if (!rowBytes.add(curBoard[i][j])) {
					return false;
				}
				if (!colBytes.add(curBoard[j][i])) {
					return false;
				}
				byte colSuf = (byte) (j % 3);
				byte rowSuf = (byte) (j / 3);
				if (!gridBytes.add(curBoard[rowPref * 3 + rowSuf][colPref * 3 + colSuf])) {
					return false;
				}
			}
		}
		return true;
	}
}

// ////////////////////////////////////
// Combination
//
// You do not need to write the code below here.
// You just need to be able to USE it.
// ////////////////////////////////////

// The algorithm is from Applied Combinatorics, by Alan Tucker.
// Based on code from koders.com

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
