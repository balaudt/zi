package zi.cfc.r306;
import java.util.Arrays;
import java.util.Scanner;

public class B {

	static final String ROOT = "C:/ft/32/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(ROOT + "B-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int l = Integer.parseInt(inStr[1]);
		int r = Integer.parseInt(inStr[2]);
		int x = Integer.parseInt(inStr[3]);
		int c[] = new int[n];
		inStr = scanner.nextLine().split(" ");
		for (int i = 0; i < n; i++) {
			c[i] = Integer.parseInt(inStr[i]);
		}
		Arrays.sort(c);
		int count = 0;
		for (int i = 2; i <= n; i++) {
			Combination combs = new Combination(n, i);
			while (combs.hasNext()) {
				int[] comb = combs.next();
				if (c[comb[comb.length - 1]] - c[comb[0]] < x)
					continue;
				int sum = 0;
				for (int j = 0; j < comb.length; j++) {
					sum += c[comb[j]];
				}
				if (sum <= r && sum >= l)
					count++;
			}
		}
		System.out.println(count);
		scanner.close();
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