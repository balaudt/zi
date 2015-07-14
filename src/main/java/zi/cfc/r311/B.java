package zi.cfc.r311;

import java.util.Arrays;
import java.util.Scanner;

public class B {

	static final String FOLDER = "C:/ft/39/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "B-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int w = Integer.parseInt(inStr[1]);
		inStr = scanner.nextLine().split(" ");
		int[] a = new int[2 * n];
		for (int i = 0; i < inStr.length; i++) {
			a[i] = Integer.parseInt(inStr[i]);
		}
		Arrays.sort(a);
		double mg = a[0];
		double mb = a[n];
		if (mb >= 2 * mg) {
			mb = 2 * mg;
		} else {
			mg = (double) mb / 2;
		}
		if (3 * n * mg < w)
			System.out.println(3 * n * mg);
		else
			System.out.println(w);
		scanner.close();
	}
}
