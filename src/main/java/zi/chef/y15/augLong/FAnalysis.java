package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.apache.commons.lang3.text.StrBuilder;

public class FAnalysis {
	public static void analysis(String[] args) {
		int n = 17, pc = n * (n + 1) / 2, ct = 0;
		System.out.println(pc);

		Map<Integer, Integer> left = new HashMap<>();
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.println(ct + "\t" + i + "\t" + j);
				left.put(j * n + i, ct++);
			}
		}

		ct = 0;
		Map<Integer, Integer> right = new HashMap<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = n - 1; j > i; j--) {
				System.out.println(ct + "\t" + i + "\t" + j);
				right.put(i * n + j, ct++);
			}
		}

		Map<Integer, Integer> result = new TreeMap<>();
		for (Entry<Integer, Integer> leftEntry : left.entrySet()) {
			result.put(leftEntry.getValue(), right.get(leftEntry.getKey()));
			//			System.out.println(leftEntry.getValue() + "\t" + right.get(leftEntry.getKey()));
		}
		System.out.println(result);
	}

	static void generate(int nl, int nu, int kl, int ku, int al, int au) {
		Random random = new Random();
		int n = random.nextInt(nu - nl) + nl;
		int k = random.nextInt(ku - kl) + kl;
		System.out.println(n + " " + k);
		StrBuilder builder = new StrBuilder();
		int diff = au - al;
		for (int i = 0; i < n; i++) {
			builder.append(random.nextInt(diff) + nl).append(' ');
		}
		System.out.println(builder.deleteCharAt(builder.length() - 1).toString());
	}

	public static void main(String[] args) throws Exception {
				analysis(args);
//				execute();
//		generate(10, 20, 3, 10, 50, 100);
	}

	public static void execute() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/21/F-gen-2.in"));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inStr[i]);
		}
		int ans = n;

		int nchoose2 = n * (n - 1) / 2;
		BitSet left = new BitSet(nchoose2);
		BitSet right = new BitSet(nchoose2);

		for (int x = 0; x < n - 1; x++) {
			for (int y = x + 1; y < n; y++) {
				boolean containsBadPair = false;
				for (int l = x; l < y && !containsBadPair; l++) {
					for (int r = l + 1; r <= y; r++) {
						if (a[l] % a[r] == k) {
							containsBadPair = true;
							//							System.out.println("-->" + l + "\t" + r);
							break;
						}
					}
				}
				if (!containsBadPair)
					ans++;
				else {
					left.set(leftManipulate(x, y)[1]);
					right.set(rightManipulate(n, x, y)[1]);
				}
			}
		}
		System.out.println(left);
		System.out.println(right);
		System.out.println(ans);
		reader.close();
	}

	static int[] leftManipulate(int l, int r) {
		int st = r * (r - 1) / 2;
		return new int[] { st, st + l };
	}

	static int[] rightManipulate(int n, int l, int r) {
		l++;
		int st = n * (n - 1) - (n - l) * (n - l + 1);
		st /= 2;
		return new int[] { st, st + n - r - 1 };
	}

}