package zi.hrank;
import java.io.File;
import java.util.Scanner;

public class D{

	static final String FOLDER = "/home/bala/temp/7/";

	static long[][] mult;

	/*
	 * The order of brute force for the solution is: sum from k=1 to n/2
	 * (n-k+1)*(n-k)*k = left ( binom{n/2}{5} right ) For n=3000,
	 * nchoosek(n,4)>2e15
	 */
	public static void main(String[] args) throws Exception {
		 Scanner scanner = new Scanner(new File(FOLDER + "D-sample.in"));
//		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		String[] inStr = scanner.nextLine().split(" ");
		int[] in = new int[n];
		for (int i = 0; i < inStr.length; i++) {
			in[i] = Integer.parseInt(inStr[i]);
		}
		mult = new long[n][n];
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in.length; j++) {
				mult[i][j] = in[i] * in[j];
			}
		}
		long ans = Long.MIN_VALUE;
		for (int sz = 1; sz <= n / 2; sz++) {
			for (int st = 0; st < n - sz; st++) {
				for (int bst = st + sz; bst < n - sz + 1; bst++) {
					long val = 0;
					for (int i = 0; i < sz; i++) {
						val += mult[st + i][bst + sz - 1 - i];
					}
					if (val > ans) {
						ans = val;
						// System.out.println(sz + "\t" + st + "\t" + bst);
					}
				}
			}
		}
		System.out.println(ans);
		scanner.close();
	}

	/*
	 * private static long getVal(int sz, int st, int bst) { long ans = 0; for
	 * (int i = 0; i < sz; i++) { ans += mult[st + i][bst + sz - 1 - i]; }
	 * return ans; }
	 */
}
