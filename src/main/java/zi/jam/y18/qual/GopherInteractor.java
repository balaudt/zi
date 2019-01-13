package zi.jam.y18.qual;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

/**
 * @author balamurugan
 */
public class GopherInteractor {
	public static void main(String[] args) throws IOException {
		Process exec = Runtime.getRuntime().exec("java -cp /Users/balamurugan/source/playground/zi/target/classes zi.jam.y18.qual.GoGopher");
		PrintStream interPs = new PrintStream(exec.getOutputStream());
		Scanner scanner = new Scanner(exec.getInputStream());
		Random random = new Random();
		int t = random.nextInt(10) + 10;
		System.out.println("t=" + t);
		interPs.println(t);
		interPs.flush();
		for (int it = 0; it < t; it++) {
			int a = random.nextBoolean() ? 20 : 200;
			interPs.println(a);
			interPs.flush();
			int gs = (a == 20) ? 5 : 15;
			boolean filled[][] = new boolean[gs][gs];
			int interactions = 0;
			while (true) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				System.out.println(String.format("P:%d, %d", x, y));
				int r = random.nextInt(9);
				int gx = (x - 1) + (r / 3);
				int gy = (y - 1) + (r % 3);
				filled[gx - 1][gy - 1] = true;
				boolean allFilled = true;
				for (int i = 0; i < filled.length && allFilled; i++) {
					for (int j = 0; j < filled[i].length; j++) {
						if (!filled[i][j]) {
							allFilled = false;
							break;
						}
					}
				}
				if (allFilled) {
					gx = 0;
					gy = 0;
				}
				System.out.println(String.format("J:%d, %d", gx, gy));
				interPs.println(gx + " " + gy);
				interPs.flush();
				if (allFilled) {
					break;
				}
				interactions++;
			}
			System.out.println(String.format("Case #%d: %d", it, interactions));
		}
	}
}
