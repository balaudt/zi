package zi.chef.y15.mayLong;

import java.util.Arrays;
import java.util.Scanner;
 
public class B {
 
	public static final String FOLDER_ROOT = "C:/ft/23/";
 
	static final int PRIME = (int) (1e9 + 7);
 
	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "B-sample.in"));
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			int[] in = new int[n];
			for (int j = 0; j < in.length; j++) {
				in[j] = scanner.nextInt();
			}
			if (n == 1) {
				System.out.println(0);
				continue;
			}
			Arrays.sort(in);
			int maxSum = 0, minSum = 0, mult = 1;
			long l;
			for (int j = 0; j < in.length - 1; j++) {
				l = in[j + 1] * (long) mult + maxSum;
				maxSum = (int) (l % PRIME);
				l = in[n - j - 2] * (long) mult + minSum;
				minSum = (int) (l % PRIME);
				l = (long) 2 * (mult + 1) - 1;
				mult = (int) (l % PRIME);
			}
			int ans = maxSum - minSum;
			if (ans <= 0)
				ans += PRIME;
			System.out.println(ans);
		}
		scanner.close();
	}
}