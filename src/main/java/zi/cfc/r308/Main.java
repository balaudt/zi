package zi.cfc.r308;
import java.io.File;
import java.util.Scanner;

public class Main {
	static final String FOLDER = "/home/bala/temp/6/";

	public static void main(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		Scanner scanner = new Scanner(new File(FOLDER + "D-gen.in"));
		// Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		byte[][] in = new byte[n][2];
		for (int i = 0; i < n; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			in[i][0] = Byte.parseByte(inStr[0]);
			in[i][1] = Byte.parseByte(inStr[1]);
		}
		if (n < 3) {
			System.out.println(0);
			scanner.close();
			return;
		}
		Combination comb = new Combination(n, 3);
		long count = 0;
		while (comb.hasNext()) {
			int[] indices = comb.next();
			int xI = in[indices[1]][0] - in[indices[0]][0];
			int xII = in[indices[2]][0] - in[indices[0]][0];
			int yI = in[indices[1]][1] - in[indices[0]][1];
			int yII = in[indices[2]][1] - in[indices[0]][1];
			if (xI * yII - xII * yI != 0)
				count++;
		}
		System.out.println(count);
		scanner.close();
		System.out.println(System.currentTimeMillis() - ct);
	}

}

// ///////////////////////////////////
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
