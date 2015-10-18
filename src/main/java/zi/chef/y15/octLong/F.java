package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class F {
	static final int P = (int) (1e9 + 7);
	static final int NLIM = 100000;
	static final long[] factorials = new long[NLIM];
	static final long[] factorialsBy2 = new long[NLIM];
	static int n, q[];

	public static void main(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		System.setIn(new FileInputStream("C:/ft/F-gen-large.in"));
		System.setOut(new PrintStream("C:/ft/F-gen.out"));
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
			if (k % 2 == 0) {
				System.out.println(getQIndex(true));
				continue;
			}
			long ans = 1, mult;
			int pos, smallerNos, pSum = 0, qSum = 0;
			ArrayList<Integer> pSorted = new ArrayList<Integer>(n);
			ArrayList<Integer> qSorted = new ArrayList<Integer>(n);
			for (int j = 0; j < n - 1; j++) {
				pos = Collections.binarySearch(qSorted, q[j]);
				pos = -pos - 1;
				qSorted.add(pos, q[j]);
				smallerNos = q[j] - pos - 1;
				mult = (smallerNos * factorialsBy2[n - j - 2]) % P;
				ans = (ans + mult) % P;
				qSum += smallerNos;

				pos = Collections.binarySearch(pSorted, p[j]);
				pos = -pos - 1;
				pSorted.add(pos, p[j]);
				smallerNos = p[j] - pos - 1;
				pSum += smallerNos;
			}
			if (pSum % 2 != qSum % 2) {
				System.out.println(-1);
				continue;
			}
			System.out.println(ans);
		}
		System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		System.out.println(System.currentTimeMillis()-ct);
	}

	private static long getQIndex(boolean isFact) {
		long ans = 1, mult;
		int pos, smallerNos;
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		long[] fact = isFact ? factorials : factorialsBy2;
		for (int j = 0; j < n - 1; j++) {
			pos = Collections.binarySearch(sorted, q[j]);
			pos = -pos - 1;
			sorted.add(pos, q[j]);
			smallerNos = q[j] - pos - 1;
			mult = (smallerNos * fact[n - j - 2]) % P;
			ans = (ans + mult) % P;
		}
		return ans;
	}
}
