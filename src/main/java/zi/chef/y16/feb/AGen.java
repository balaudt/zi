package zi.chef.y16.feb;

import java.io.PrintStream;
import java.util.Random;

public class AGen {

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream("/Users/balaudt/Temp/feb/A-gen.in"));
		Random random = new Random();
		int n = random.nextInt(10000) + 10000;
		System.out.println(n);
		System.out.print("0 ");
		for (int i = 1; i < n - 1; i++) {
			System.out.print(random.nextInt(n) + " ");
		}
		System.out.print(random.nextInt(n));
	}
}
