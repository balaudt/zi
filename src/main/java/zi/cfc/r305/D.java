package zi.cfc.r305;

import java.util.Scanner;

//TODO Debug TLE
public class D {

	public static final String FOLDER_ROOT = "C:/ft/26/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "D-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] in = new int[n];
		int[] min = new int[n];
		int maxInGroup = Integer.MIN_VALUE;
		for (int i = 0; i < in.length; i++) {
			in[i] = scanner.nextInt();
			min[i] = in[i];
			if (min[i] > maxInGroup)
				maxInGroup = min[i];
		}
		// System.out.println(Arrays.toString(min));
		System.out.print(maxInGroup);
		System.out.print(' ');
		for (int len = 2; len <= n; len++) {
			maxInGroup = Integer.MIN_VALUE;
			for (int j = 0; j <= n - len; j++) {
				if (min[j] > in[j + len - 1]) {
					min[j] = in[j + len - 1];
				}
				if (min[j] > maxInGroup)
					maxInGroup = min[j];
			}
			// System.out.println(Arrays.toString(min));
			System.out.print(maxInGroup);
			System.out.print(' ');
		}
		scanner.close();
	}
}
