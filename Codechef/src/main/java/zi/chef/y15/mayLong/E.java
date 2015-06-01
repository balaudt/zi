package zi.chef.y15.mayLong;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class E {

	public static final String FOLDER_ROOT = "C:/ft/23/";

	static final int PRIME = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "E-sample-2.in"));
		// Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int q = scanner.nextInt();
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		int d = scanner.nextInt();
		int e = scanner.nextInt();
		int f = scanner.nextInt();
		int r = scanner.nextInt();
		int s = scanner.nextInt();
		int t0 = scanner.nextInt();
		t0 %= s;
		int t = t0;
		int m = scanner.nextInt();
		int ax = scanner.nextInt();
		int[][] minMat = new int[n][];
		minMat[0] = new int[n];
		minMat[0][0] = ax;
		for (int i = 2; i <= n; i++) {
			minMat[i - 1] = new int[n - (i - 1)];
			t = (int) (((long) t * t0) % s);
			if (t <= r) {
				ax = (int) ((a * (long) ax * ax + b * ax + c) % m);
			} else {
				ax = (int) ((d * (long) ax * ax + e * ax + f) % m);
			}
			minMat[0][i - 1] = ax;
			for (int j = 1; j < i; j++) {
				minMat[j][i - j - 1] = Math.min(ax, minMat[j - 1][i - j - 1]);
			}
		}
		System.out.println(Arrays.toString(minMat[0]));

		int l1 = scanner.nextInt();
		int la = scanner.nextInt();
		int lc = scanner.nextInt();
		int lm = scanner.nextInt();
		int d1 = scanner.nextInt();
		int da = scanner.nextInt();
		int dc = scanner.nextInt();
		int dm = scanner.nextInt();
		int prod = 1;
		long sum = 0;
		for (int i = 0; i < q; i++) {
			l1 = (int) (((long) la * l1 + lc) % lm);
			d1 = (int) (((long) da * d1 + dc) % dm);
			int L = l1 + 1;
			int R = Math.min(L + k - 1 + d1, n);
			// System.out.println(L + " " + R);
			int min = minMat[R - L][L - 1];
			// System.out.println(min);
			long ans = (long) prod * min;
			prod = (int) (ans % PRIME);
			sum += min;
		}
		System.out.println(sum + " " + prod);
		scanner.close();
	}
}
