package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class CorrectPrice {

	public static void main(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		System.setIn(new FileInputStream("/Users/balaudt/Temp/fb/C.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/C.out"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] line = reader.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			long p = Integer.parseInt(line[1]);
			long b[] = new long[n];
			line = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				b[j] = Integer.parseInt(line[j]);
			}

			final long runningSum[] = new long[n + 1];
			runningSum[0] = 0l;
			long ans = 0;
			int left = 1;
			if (b[0] <= p) {
				ans++;
				left = 0;
				runningSum[1] = b[0];
			}

			for (int j = 1; j < n; j++) {
				if (b[j] >= p) {
					if (b[j] == p)
						ans++;
					left = j + 1;
					runningSum[j + 1] = 0l;
					continue;
				}
				runningSum[j + 1] = runningSum[j] + b[j];
				if (runningSum[j + 1] <= p) {
					ans += j + 1 - left;
					continue;
				}

				int ip = Arrays.binarySearch(runningSum, left, j + 2, runningSum[j + 1] - p);
				if (ip < 0)
					ip = -ip - 1;
				ip -= left;

				/*Long temp[] = new Long[j - left + 1];
				for (int k = left; k <= j; k++)
					temp[k - left] = runningSum[j + 1] - runningSum[k];
				System.out.println(Arrays.toString(runningSum));
				System.out.println(Arrays.toString(temp));
				int ip = Arrays.binarySearch(temp, (long) p, Comparator.<Long> reverseOrder());*/

				left += ip;
				ans += j - left + 1;
				// System.out.println(ip);
				// System.out.println('-');
			}

			System.out.println(String.format("Case #%d: %d", i + 1, ans));
		}
		reader.close();
		System.out.println(System.currentTimeMillis() - ct);
	}
}
