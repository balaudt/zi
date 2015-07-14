package zi.chef.y15.junLong;
import java.util.Arrays;
import java.util.Scanner;

public class B {
	static final String FOLDER = "C:/ft/33/";

	public static void main(String args[]) throws Exception {
//		 Scanner scanner = new Scanner(new File(FOLDER + "B-gen.in"));
		Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine().trim());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(scanner.nextLine().trim());
			int x[] = new int[n];
			for (int j = 0; j < n; j++) {
				int m = Integer.parseInt(scanner.nextLine().trim());
				String[] inStr = scanner.nextLine().trim().split(" ");
				int maxX = Integer.parseInt(inStr[0]);
				for (int k = 2; k < 2 * m; k += 2) {
					int v = Integer.parseInt(inStr[k]);
					if (v > maxX)
						maxX = v;
				}
				x[j] = maxX;
			}
			int[] sort = Arrays.copyOf(x, n);
			Arrays.sort(sort);
			for (int j = 0; j < n; j++) {
				System.out.print(Arrays.binarySearch(sort, x[j]) + " ");
			}
			System.out.println();
		}
		scanner.close();
	}
}