package zi.chef.y15.mayLong;

import java.util.Scanner;

public class A {
	public static final String FOLDER_ROOT = "C:/ft/23/";

	public static void main(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER_ROOT + "A-sample.in"));
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			long min = Integer.MAX_VALUE;
			long sum = 0;
			boolean isValid = true;
			for (int j = 0; j < n; j++) {
				long in = scanner.nextLong();
				if (in < 2) {
					isValid = false;
				}
				if (in < min)
					min = in;
				sum += in;
			}
			if (!isValid) {
				System.out.println("-1");
			} else {
				sum -= min;
				sum += 2;
				System.out.println(sum);
			}
		}
		scanner.close();
	}

}
