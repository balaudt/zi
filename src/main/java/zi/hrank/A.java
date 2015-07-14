package zi.hrank;
import java.util.Scanner;

public class A {

	static final String FOLDER = "/home/bala/temp/7/";

	public static void main(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER + "A-sample.in"));
		Scanner scanner = new Scanner(System.in);
		String[] inStr = scanner.nextLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		long p = Integer.parseInt(inStr[1]);
		int x = Integer.parseInt(inStr[2]);
		long rating;
		inStr = scanner.nextLine().split(" ");
		long maxRate = Long.MIN_VALUE;
		int maxInt = -1;
		for (int i = 0; i < n; i++) {
			rating = p * Integer.parseInt(inStr[i]);
			p -= x;
			if (rating > maxRate) {
				maxRate = rating;
				maxInt = i;
			}
		}
		System.out.println(maxInt + 1);
		scanner.close();
	}
}
