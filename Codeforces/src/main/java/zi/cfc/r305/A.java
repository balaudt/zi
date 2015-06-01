package zi.cfc.r305;

import java.util.Scanner;

public class A {

	public static final String FOLDER_ROOT = "C:/ft/26/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER_ROOT + "A-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String inStr = scanner.nextLine();
		int k = scanner.nextInt();
		int len = inStr.length();
		if (len % k != 0) {
			System.out.println("NO");
			scanner.close();
			return;
		}
		int j = len / k;
		boolean isPossible = true;
		for (int i = 0; i < k; i++) {
			String substring = inStr.substring(i * j, i * j + j);
			if (!isPalindrom(substring)) {
				isPossible = false;
				break;
			}
		}
		if (isPossible)
			System.out.println("YES");
		else
			System.out.println("NO");
		scanner.close();
	}

	static boolean isPalindrom(String str) {
		int len = str.length();
		for (int i = 0; i < len / 2; i++) {
			if (str.charAt(i) != str.charAt(len - i - 1))
				return false;
		}
		return true;
	}
}
