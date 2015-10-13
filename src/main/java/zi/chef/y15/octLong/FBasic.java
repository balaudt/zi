package chef.octlong;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//Basic test to whether understanding of problem statement is correct
public class FBasic {
	static final int P = (int) (1e9 + 7);
	static final int NLIM = 100000;
	static final long[] factorials = new long[NLIM];
	static final long[] factorialsBy2 = new long[NLIM];
	static int n, q[];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:/ft/F-gen.in"));
		System.setOut(new PrintStream("C:/ft/F-gen-base.out"));
		factorials[0] = 1;
		factorials[1] = 2;
		factorialsBy2[0] = 0;
		factorialsBy2[1] = 1;
		for (int i = 2; i < NLIM; i++) {
			factorials[i] = factorials[i - 1] * (i + 1) % P;
			factorialsBy2[i] = factorialsBy2[i - 1] * (i + 1) % P;
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr, inStr1;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			n = Integer.parseInt(inStr[0]);
			int k = Integer.parseInt(inStr[1]);
			inStr = reader.readLine().split(" ");
			inStr1 = reader.readLine().split(" ");
			int[] p = new int[n];
			q = new int[n];
			for (int j = 0; j < n; j++) {
				p[j] = Integer.parseInt(inStr[j]);
				q[j] = Integer.parseInt(inStr1[j]);
			}
			inStr = inStr1 = null;
			if (k == 1) {
				if (Arrays.equals(p, q)) {
					System.out.println(1);
				} else {
					System.out.println(-1);
				}
				continue;
			}
			if (k == n) {
				int j = 0, l;
				while (q[j++] != p[0])
					;
				j--;
				boolean flag = true;
				for (l = 0; j < n; l++, j++) {
					if (p[l] != q[j]) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					System.out.println(-1);
					continue;
				}
				for (j = 0; l < n; l++, j++) {
					if (p[l] != q[j]) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					System.out.println(-1);
				} else {
					System.out.println(q[0]);
				}
				continue;
			}
			if (n > 5)
				throw new UnsupportedOperationException();
			StringBuilder qBuilder = new StringBuilder();
			StringBuilder pBuilder = new StringBuilder();
			for (int j = 0; j < q.length; j++) {
				qBuilder.append(q[j]);
				pBuilder.append(p[j]);
			}
			String qPerm = qBuilder.toString();
			String pPerm = pBuilder.toString();
			List<String> allPerms = getAllPerms(n);
			if (k % 2 == 0) {
				System.out.println(allPerms.indexOf(qPerm) + 1);
				continue;
			}
			List<String> group1 = visitAll(allPerms.get(0));
			List<String> group2 = visitAll(allPerms.get(1));
			if (group1.contains(pPerm) && group1.contains(qPerm))
				System.out.println(group1.indexOf(qPerm) + 1);
			else if (group2.contains(pPerm) && group2.contains(qPerm))
				System.out.println(group2.indexOf(qPerm) + 1);
			else
				System.out.println(-1);
		}
	}

	static String rotate(String str, int k, int i) {
		String out = str.substring(0, i);
		char[] arr = str.substring(i, i + k).toCharArray();
		char lastChar = arr[arr.length - 1];
		for (int j = arr.length - 1; j > 0; j--) {
			arr[j] = arr[j - 1];
		}
		arr[0] = lastChar;
		out += new String(arr);
		out += str.substring(i + k);
		return out;
	}

	private static List<String> visitAll(String initalStr) {
		Set<String> visistedPerms = new TreeSet<>();
		visistedPerms.add(initalStr);
		List<String> inStrs = new ArrayList<>(visistedPerms);
		int n = initalStr.length();
		int k = 3;
		while (true) {
			List<String> nextStrs = new ArrayList<>();
			for (String inStr : inStrs) {
				for (int i = 0; i <= n - k; i++) {
					String rotString = rotate(inStr, k, i);
					if (visistedPerms.contains(rotString))
						continue;
					visistedPerms.add(rotString);
					nextStrs.add(rotString);
				}
			}
			if (nextStrs.isEmpty())
				break;
			inStrs = nextStrs;
		}
		//		System.out.println(visistedPerms);
		//		System.out.println(visistedPerms.size());
		return new ArrayList<String>(visistedPerms);
	}

	private static List<String> getAllPerms(int n) {
		Permutation permutation = new Permutation(n, n);
		StringBuilder builder = new StringBuilder();
		List<String> out = new ArrayList<String>();
		while (permutation.hasNext()) {
			int[] next = permutation.next();
			builder.setLength(0);
			for (int i = 0; i < next.length; i++) {
				builder.append(next[i] + 1);
			}
			out.add(builder.toString());
		}
		return out;
	}
}

//////////////////////////////////////
//Permutation
//
//You do not need to write the code below here.
//You just need to be able to USE it.
//////////////////////////////////////

//The algorithm is from Applied Combinatorics, by Alan Tucker.
//Based on code from koders.com

class Permutation {
	private int n, r;
	private int[] index;
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
	// {1, 2, 4, 3, 0}. The dip is 2  the rightmost element less 
	// than its neighbor on its right. The least element to the right of 
	// 2 that is greater than 2 is 3. These elements are swapped, 
	// yielding {1, 3, 4, 2, 0}, and the list right of the dip point is 
	// reversed, yielding {1, 3, 0, 2, 4}.

	private void moveIndex() {
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
