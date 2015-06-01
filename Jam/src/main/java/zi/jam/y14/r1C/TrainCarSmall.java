package zi.jam.y14.r1C;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.StrBuilder;

public class TrainCarSmall {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("B-small-attempt2.bin"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("B-small-attempt2.out"));
		int t = Integer.parseInt(reader.readLine());
		Pattern pattern = Pattern.compile("(\\w)\\1+");
		for (int i = 0; i < t; i++) {
			System.out.println(i);
			int n = Integer.parseInt(reader.readLine());
			String[] inps = reader.readLine().split(" ");
			for (int j = 0; j < inps.length; j++) {
				String in = inps[j];
				Matcher matcher = pattern.matcher(in);
				if (matcher.find()) {
					inps[j] = matcher.replaceAll("$1");
				}
			}
//			System.out.println(Arrays.toString(inps));
			Permutation permutation = new Permutation(n, n);
			int count = 0;
			while (permutation.hasNext()) {
				int[] indices = permutation.next();
				String str = inps[indices[0]];
				boolean possible = true;
				for (int j = 1; j < indices.length; j++) {
					str += inps[indices[j]];
					if (!isValid(str)) {
						possible = false;
						break;
					}
				}
				if (possible) {
					// System.out.println(str);
					count++;
				}
			}
			writer.write(new StrBuilder("Case #").append(i + 1).append(": ").append(count).append('\n').toString());
			// System.out.println(count);
		}
		reader.close();
		writer.close();
	}

	static boolean isValid(String in) {
		char[] cs = in.toCharArray();
		Set<Character> visited = new HashSet<>();
		for (int i = 1; i < cs.length; i++) {
			if (cs[i] == cs[i - 1]) {
				continue;
			}
			visited.add(cs[i - 1]);
			if (visited.contains(cs[i])) {
				// System.out.println(in + "\t" + false);
				return false;
			}
		}
		// System.out.println(in + "\t" + true);
		return true;
	}
}

class Permutation {
	private int n, r;
	int[] index;
	private boolean hasNext = true;

	public Permutation(int n, int r) {
		this.n = n;
		this.r = r;
		index = new int[n];
		for (int i = 0; i < n; i++)
			index[i] = i;
		reverseAfter(r - 1);
	}

	public boolean hasNext() {
		return hasNext;
	}

	// Based on code from KodersCode:
	// The algorithm is from Applied Combinatorics, by Alan Tucker.
	// Move the index forward a notch. The algorithm first finds the
	// rightmost index that is less than its neighbor to the right. This
	// is the dip point. The algorithm next finds the least element to
	// the right of the dip that is greater than the dip. That element is
	// switched with the dip. Finally, the list of elements to the right
	// of the dip is reversed.
	//
	// For example, in a permutation of 5 items, the index may be
	// {1, 2, 4, 3, 0}. The dip is 2 the rightmost element less
	// than its neighbor on its right. The least element to the right of
	// 2 that is greater than 2 is 3. These elements are swapped,
	// yielding {1, 3, 4, 2, 0}, and the list right of the dip point is
	// reversed, yielding {1, 3, 0, 2, 4}.

	void moveIndex() {
		// find the index of the first element that dips
		int i = rightmostDip();
		if (i < 0) {
			hasNext = false;
			return;
		}

		// find the smallest element to the right of the dip
		int smallestToRightIndex = i + 1;
		for (int j = i + 2; j < n; j++)
			if ((index[j] < index[smallestToRightIndex]) && (index[j] > index[i]))
				smallestToRightIndex = j;

		// switch dip element with smallest element to its right
		swap(index, i, smallestToRightIndex);

		if (r - 1 > i) {
			// reverse the elements to the right of the dip
			reverseAfter(i);
			// reverse the elements to the right of r - 1
			reverseAfter(r - 1);
		}
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

	// Reverse the index elements to the right of the specified index.
	private void reverseAfter(int i) {
		int start = i + 1;
		int end = n - 1;
		while (start < end) {
			swap(index, start, end);
			start++;
			end--;
		}
	}

	// return int the index of the first element from the right
	// that is less than its neighbor on the right.
	private int rightmostDip() {
		for (int i = n - 2; i >= 0; i--)
			if (index[i] < index[i + 1])
				return i;
		return -1;
	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}