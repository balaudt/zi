package tostream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.apache.commons.lang.text.StrBuilder;

public class FAnalysis {
	static void modPerfAnalysis() {
		Random random = new Random();
		int n = random.nextInt(500) + 10;
		int in[] = new int[n];
		for (int i = 0; i < in.length; i++) {
			in[i] = random.nextInt(2000);
		}
		System.out.println(Arrays.toString(in));
		int lkup[] = new int[2000 / 13 + 1];
		int ct = 0;
		for (int i = 0; i < 2000; i += 13) {
			lkup[ct++] = i;
		}
		System.out.println(Arrays.toString(lkup));
		long t = System.currentTimeMillis();
		for (int i = 0; i < in.length; i++) {
			System.out.print(in[i] % 13 + " ");
		}
		System.out.println();
		System.out.println("**" + (System.currentTimeMillis() - t));

		t = System.currentTimeMillis();
		for (int i = 0; i < in.length; i++) {
			int ip = Arrays.binarySearch(lkup, in[i]);
			if (ip < 0)
				System.out.print(in[i] - lkup[-ip - 2] + " ");
			else
				System.out.print("0 ");
		}
		System.out.println();
		System.out.println("++" + (System.currentTimeMillis() - t));
		System.out.println(2000 - lkup[-Arrays.binarySearch(lkup, 2000) - 2]);
	}

	static void checkPow2() {
		Random random = new Random();
		int n = random.nextInt(20) + 10;
		for (int i = 0; i < n; i++) {
			int pow = random.nextInt(10) + 5;
			int x = random.nextInt((int) 1e5) + 100;
			int y = (int) Math.pow(2, pow);
			System.out.println(x + "\t" + y + "\t" + (x % y == modPow2(x, y)));
		}
	}

	static int modPow2(int x, int y) {
		return x & (y - 1);
	}

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
		//				analysis(args);
				execute();
		//		modPerfAnalysis();
//		checkPow2();
	}

	public static void execute() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("C:/ft/200/F.in"));
		//		generate(10, 20, 3, 10, 50, 100);
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inStr[i]);
		}
		int ans = n;
		for (int x = 0; x < n - 1; x++) {
			for (int y = x + 1; y < n; y++) {
				boolean containsBadPair = false;
				for (int l = x; l < y && !containsBadPair; l++) {
					for (int r = l + 1; r <= y; r++) {
						if (a[l] % a[r] == k) {
							containsBadPair = true;
							System.out.println("-->" + l + "\t" + r);
							break;
						}
					}
				}
				if (!containsBadPair)
					ans++;
				else
					System.out.println(x + "\t" + y);
			}
		}
		System.out.println(ans);
		reader.close();
	}
}
