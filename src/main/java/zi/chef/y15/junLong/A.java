package zi.chef.y15.junLong;
import java.util.Scanner;

public class A {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String args[]) throws Exception {
//		Scanner scanner = new Scanner(new File(FOLDER + "A-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(scanner.nextLine());
			String inStr[] = scanner.nextLine().split(" ");
			long sum = Integer.parseInt(inStr[0]);
			for (int j = 1; j < n; j++) {
				int diff = Integer.parseInt(inStr[j])
						- Integer.parseInt(inStr[j - 1]);
				if (diff > 0)
					sum += diff;
			}
			System.out.println(sum);
		}
		scanner.close();
	}
}