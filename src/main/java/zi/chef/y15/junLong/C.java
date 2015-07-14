package zi.chef.y15.junLong;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*import org.paukov.combinatorics.Factory;
 import org.paukov.combinatorics.Generator;
 import org.paukov.combinatorics.ICombinatoricsVector;*/

public class C {

	static final String FOLDER = "C:/ft/33/";

	/*
	 * public static void analysis(String[] args) { for (int n = 0; n < 8; n++)
	 * { Integer[] inVec = new Integer[n + 1]; Map<Integer, Integer> map = new
	 * HashMap<Integer, Integer>(); for (int i = 0; i <= n; i++) { inVec[i] = i;
	 * map.put(i, 0); } Generator<Integer> generator =
	 * Factory.createPermutationWithRepetitionGenerator
	 * (Factory.createVector(inVec), n); int count = 0; for
	 * (ICombinatoricsVector<Integer> vector : generator) { int sum = 0; for
	 * (Integer i : vector) { sum += i; } if (sum <= n) { map.put(sum,
	 * map.get(sum) + 1); count++; } } for (Entry<Integer, Integer> entry :
	 * map.entrySet()) { System.out.print(entry.getValue() + ","); }
	 * System.out.println(); System.out.println(count); }
	 * 
	 * }
	 */

	static final int P = 1000000000;

	static final int NLIM = 4000;
	static long bc[][] = new long[NLIM + 1][];
	static Map<Long, Integer> memo = new HashMap<Long, Integer>();

	public static void main(String[] args) throws Exception {
		bc[0] = new long[] { 1, 1 };
		bc[1] = new long[] { 1, 2, 1 };
		for (int i = 2; i <= NLIM; i++) {
			bc[i] = new long[i + 2];
			bc[i][0] = bc[i][i + 1] = 1;
			for (int j = 1; j <= i; j++) {
				bc[i][j] = (bc[i - 1][j] + bc[i - 1][j - 1]) % P;
			}
		}
//		Scanner scanner = new Scanner(new File(FOLDER + "C-gen.in"));
		 Scanner scanner = new Scanner(System.in);
		// System.out.println(Arrays.deepToString(bc));
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int m = Integer.parseInt(inStr[1]);
//			System.out.println(func(n, m, m));
			 System.out.println(func1(n, m));
		}
		scanner.close();
	}

	static int func(int row, int n, int k) throws Exception {
		if (row == 0)
			return 1;
		long key = ((long) row) << 32 | n << 16 | k;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		int sum = 1; // f(n,0)=1
		for (int i = 1; i <= k; i++) {
			long ans = bc[n + i - 2][n - 1] * func(row - 1, n, i);
			sum = (int) ((sum + ans % P) % P);
		}
		memo.put(key, sum);
		return sum;
	}

	static int func1(int n, int m) {
		long prev[] = new long[m + 1];
		prev[0] = 1;
		for (int k = 1; k <= m; k++) {
			prev[k] = bc[m + k - 2][m - 1];
		}
		for (int x = 2; x <= n; x++) {
			long cur[] = new long[m + 1];
			cur[0] = 1;
			for (int k = 1; k <= m; k++) {
				long sum = 0;
				for (int l = 0; l <= k; l++) {
					sum += prev[l];
					sum %= P;
				}
				cur[k] = bc[m + k - 2][m - 1] * sum;
				cur[k] %= P;
			}
			prev = cur;
		}
		int sum = 0;
		for (int i = 0; i < prev.length; i++) {
			long ans = sum + prev[i];
			sum = (int) (ans % P);
		}
		return sum;
	}
}
