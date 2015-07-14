package zi.cfc.r306;
import java.util.Scanner;

public class C {

	static final String ROOT = "C:/ft/32/";

	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(new File(ROOT + "C-sample.in"));
		 Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		boolean isPossible = false;
		for (int i = 0; i <= 125; i++) {
			StringBuilder builder = new StringBuilder(".*");
			char[] cs = Integer.toString(i * 8).toCharArray();
			for (int j = 0; j < cs.length; j++) {
				builder.append(cs[j]).append(".*");
			}
			if (line.matches(builder.toString())) {
				System.out.println("YES");
				System.out.println(i * 8);
				isPossible = true;
				break;
			}
		}
		if (!isPossible) {
			System.out.println("NO");
		}
		scanner.close();
	}
}
