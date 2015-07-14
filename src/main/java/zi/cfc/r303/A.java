package zi.cfc.r303;

import java.util.ArrayList;
import java.util.Scanner;

public class A {
	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER_ROOT + "A-sample.in"));
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		ArrayList<Integer> out = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			boolean isGood = true;
			for (int j = 0; j < inStr.length; j++) {
				int res = Integer.parseInt(inStr[j]);
				if (res == 1 || res == 3) {
					isGood = false;
				}
			}
			if (isGood)
				out.add(i + 1);
		}
		System.out.println(out.size());
		for (Integer outNum : out) {
			System.out.print(outNum + " ");
		}
		System.out.println();
		scanner.close();
	}
}
