package zi.cfc.r306;
import java.io.File;
import java.util.Scanner;

public class A {

	static final String ROOT = "C:/ft/32/";

	public static void main(String[] args) throws Exception {
		 Scanner scanner = new Scanner(new File(ROOT + "A-sample.in"));
//		Scanner scanner = new Scanner(System.in);
		String in = scanner.nextLine();
		if (in.matches(".*AB.*BA.*") || in.matches(".*BA.*AB.*"))
			System.out.println("YES");
		else
			System.out.println("NO");
		scanner.close();
	}
}
