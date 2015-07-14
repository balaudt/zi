package zi.chef.y15.junLong;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class F {

	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File(FOLDER + "F-gen.in"));
		long ct = System.currentTimeMillis();
		// Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(scanner.nextLine());
			if (n > 20)
			{
				scanner.close();
				throw new UnsupportedOperationException();
			}
			boolean a[][] = new boolean[n][];
			boolean isPossible = true;
			for (int j = 0; j < n; j++) {
				a[j] = new boolean[j + 1];
				String[] inStr = scanner.nextLine().split(" ");
				for (int k = 0; k <= j; k++) {
					a[j][k] = Byte.parseByte(inStr[k]) == 1;
				}
				if (a[j][j])
					isPossible = false;
			}
			if (!isPossible) {
				System.out.println("-1");
				continue;
			}
			int perm = (int) Math.pow(2, n);
			boolean[] y = new boolean[n];
			boolean[] ans = null;
			boolean xkl;
			int minZero = Integer.MAX_VALUE;
			for (int j = 0; j < perm; j++) {
				char[] cs = Integer.toBinaryString(j).toCharArray();
				for (int k = 0; k < cs.length; k++) {
					y[k] = cs[k] - '0' == 1;
				}
				isPossible = true;
				int zcount = 0;
				for (int k = 0; k < n && isPossible; k++) {
					for (int l = 0; l <= k; l++) {
						xkl = y[k] ^ y[l];
						if (a[k][l] && !xkl) {
							isPossible = false;
							break;
						} else if (!a[k][l] && !xkl) {
							zcount++;
						}
					}
				}
				if (isPossible && zcount < minZero) {
					minZero = zcount;
					ans = Arrays.copyOf(y, n);
				}
			}
			if (minZero < Integer.MAX_VALUE) {
				for (int j = 0; j < ans.length; j++) {
					System.out.print(ans[j] ? "1 " : "0 ");
				}
				System.out.println();
			} else {
				System.out.println("-1");
			}
		}
		System.out.println(System.currentTimeMillis() - ct);
		scanner.close();
	}
}
