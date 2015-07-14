package zi.cfc.r308;
import java.io.File;
import java.util.Scanner;

public class B {

	static final String FOLDER = "/home/bala/temp/6/";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER + "B-sample.in"));
		// Scanner scanner = new Scanner(System.in);
		int in = scanner.nextInt();
		int len = Integer.toString(in).length();
		long ans = 0;
		int start = 9;
		int i;
		for (i = 1; i <= len - 1; i++) {
			ans += i * start;
			start *= 10;
		}
		ans += i * (in - Math.pow(10, len - 1) + 1);
		System.out.println(ans);
		scanner.close();
	}
}
