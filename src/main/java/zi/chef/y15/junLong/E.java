package zi.chef.y15.junLong;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class E {

	static final int NLIM = 500;
	static long bc[][] = new long[NLIM + 1][];
	static final int P = 1000000007;
	static {
		bc[0] = new long[] { 1, 1 };
		bc[1] = new long[] { 1, 2, 1 };
		for (int i = 2; i <= NLIM; i++) {
			bc[i] = new long[i + 2];
			bc[i][0] = bc[i][i + 1] = 1;
			for (int j = 1; j <= i; j++) {
				bc[i][j] = (bc[i - 1][j] + bc[i - 1][j - 1]) % P;
			}
		}
	}

	static final String FOLDER = "C:/ft/33/";

	public static void main(String[] args) throws Exception {
//		 long ct = System.currentTimeMillis();
		Scanner scanner = new Scanner(new File(FOLDER + "E-gen.in"));
//		 Scanner scanner = new Scanner(System.in);
		int t = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = scanner.nextLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			if (n > 500)
			{
				scanner.close();
				throw new UnsupportedOperationException();
			}
			int q = Integer.parseInt(inStr[1]);
			String in = scanner.nextLine();
			int len = in.length();
			Map<String, Integer> uniqueStrings = new HashMap<String, Integer>();
			for (int j = 0; j < len; j++) {
				for (int k = j + 1; k < len + 1; k++) {
					String substring = in.substring(j, k);
					if (uniqueStrings.containsKey(substring)) {
						uniqueStrings.put(substring, uniqueStrings.get(substring) + 1);
					} else {
						uniqueStrings.put(substring, 1);
					}
				}
			}
			Collection<Integer> countColl = uniqueStrings.values();
			Integer[] sorted = new Integer[countColl.size()];
			countColl.toArray(sorted);
			uniqueStrings = null;
			Arrays.sort(sorted);

			Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
			int lastVal = sorted[0];
			cache.put(lastVal, 0);
			for (int j = 1; j < sorted.length; j++) {
				if (sorted[j] == lastVal)
					continue;
				else {
					lastVal = sorted[j];
					cache.put(lastVal, j);
				}
			}

			// System.out.println(Arrays.toString(sorted));
			int sum, k, pos;
			long temp;
			for (int j = 0; j < q; j++) {
				k = Integer.parseInt(scanner.nextLine());
				if (sorted[sorted.length - 1] < k) {
					System.out.println("0");
					continue;
				}
				sum = 0;
				pos = Arrays.binarySearch(sorted, k);
				if (pos < 0) {
					pos = -pos;
					if (pos == sorted.length)
						pos--;
				}
				pos = cache.get(sorted[pos]);
				// System.out.println("->" + pos);
				for (int l = pos; l < sorted.length; l++) {
					temp = bc[sorted[l] - 1][k] + sum;
					sum = (int) (temp % P);
				}
				System.out.println(sum);
			}
		}
		scanner.close();
//		 System.out.println(System.currentTimeMillis() - ct);
	}
}
