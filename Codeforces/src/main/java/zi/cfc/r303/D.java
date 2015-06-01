package zi.cfc.r303;

import java.util.Arrays;
import java.util.Scanner;

public class D {

	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main1(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER_ROOT + "D-sample.in"));
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int in[] = new int[n];
		for (int i = 0; i < n; i++) {
			in[i] = scanner.nextInt();
		}
		Arrays.sort(in);
		long currentWaitingTime = 0;
		int disCount = 0;
		for (int i = 0; i < in.length; i++) {
			if (in[i] < currentWaitingTime) {
				disCount++;
			} else {
				currentWaitingTime += in[i];
			}
		}
		System.out.println(n - disCount);
		scanner.close();
	}

	public static void main(String[] args) {
		int n = 6, c = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= i; j++) {
				for (int k = 0; k <= j; k++) {
					c++;
				}
			}
		}
		System.out.println(c);
		int ans = ((n + 1) * (n + 2) / 2 + (n + 1) * (n + 2) * (2 * (n + 1) + 1) / 6) / 2;
		System.out.println(ans);
	}
}
