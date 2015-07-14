package zi.cfc.r303;
import java.util.Scanner;

public class C {

	public static final String FOLDER_ROOT = "C:/ft/24/";

	public static void main(String[] args) throws Exception {
		// Scanner scanner = new Scanner(new File(FOLDER_ROOT + "C-gen.in"));
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		if (n == 1) {
			System.out.println("1");
			scanner.close();
			return;
		}
		String[] inStr = scanner.nextLine().split(" ");
		long lastX = Long.parseLong(inStr[0]), lastH = Long.parseLong(inStr[1]);
		inStr = scanner.nextLine().split(" ");
		long x = Long.parseLong(inStr[0]), h = Long.parseLong(inStr[1]);
		boolean lastRightFall = false, leftFall;
		int count = 1;
		for (int i = 2; i < n; i++) {
			leftFall = false;
			if (!lastRightFall) {
				if (h < x - lastX) {
					count++;
					leftFall = true;
				}
			} else {
				if (h < x - lastX - lastH) {
					leftFall = true;
					count++;
				}
			}
			inStr = scanner.nextLine().split(" ");
			long nextX = Long.parseLong(inStr[0]);
			long nextH = Long.parseLong(inStr[1]);
			lastRightFall = false;
			if (!leftFall) {
				if (x + h < nextX) {
					count++;
					lastRightFall = true;
				}
			}
			lastX = x;
			lastH = h;
			x = nextX;
			h = nextH;
		}
		count++;
		System.out.println(count);
		scanner.close();
	}
}
