package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.math3.util.CombinatoricsUtils;

public class BAnalysis {

	public static void main(String[] args) throws Exception {
		test();
	}

	private static final int MAX_N = (int) 60;
	private static final long MAX_A = (long) 1e18;

	static void largeGenerate() throws Exception {
		PrintStream stream = new PrintStream("/Users/balaudt/Temp/feb/B-large.in");
		System.setOut(stream);
		Random random = new Random();
		int n = random.nextInt(MAX_N / 2) + MAX_N / 2;
		int m = random.nextInt(MAX_N / 2) + MAX_N / 2;
		int x = random.nextInt(n / 2) + n / 2;
		System.out.println("1");
		System.out.println(n + " " + x + " " + m);
		StrBuilder builder = new StrBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(RandomUtils.nextLong(MAX_A / 2, MAX_A)).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1);
		System.out.println(builder.toString());
		stream.close();
	}

	static void generate() throws Exception {
		PrintStream stream = new PrintStream("/Users/balaudt/Temp/feb/B-gen.in");
		System.setOut(stream);
		Random random = new Random();
		int n = random.nextInt(6) + 4;
		int m = random.nextInt(6) + 4;
		System.out.println("1");
		System.out.println(n + " " + n + " " + m);
		StrBuilder builder = new StrBuilder();
		for (int i = 0; i < n; i++) {
			builder.append(random.nextInt(10) + 10).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1);
		System.out.println(builder.toString());
		stream.close();
	}

	static void actual() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/B-gen.in"));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int x = Integer.parseInt(inStr[1]);
			int m = Integer.parseInt(inStr[2]);
			inStr = reader.readLine().split(" ");
			long a[] = new long[n];
			for (int j = 0; j < n; j++) {
				a[j] = Integer.parseInt(inStr[j]);
			}
			for (int j = 0; j < m; j++) {
				for (int k = 1; k < x; k++) {
					a[k] += a[k - 1];
				}
			}
			System.out.println(a[x - 1]);
		}
		reader.close();
	}

	static void test() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/B-large.in"));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int x = Integer.parseInt(inStr[1]);
			int m = Integer.parseInt(inStr[2]);
			inStr = reader.readLine().split(" ");
			long a[] = new long[n];
			for (int j = 0; j < n; j++) {
				a[j] = Long.parseLong(inStr[j]);
				a[j] %= P;
			}
			long ans = 0;
			for (int j = x - 1, k = m - 1, l = 0; j >= 0; j--, k++, l++) {
				long bc = CombinatoricsUtils.binomialCoefficient(k, l);
				bc %= P;
				ans += bc * a[j];
				ans %= P;
			}
			System.out.println(ans);
		}
		reader.close();
	}

	static void combForm() {
		int n = 10;
		long[] nc2 = new long[n];
		long[] nc3 = new long[n];
		for (int i = 0; i < nc2.length; i++) {
			nc2[i] = CombinatoricsUtils.binomialCoefficient(i + 2, 2);
			nc3[i] = CombinatoricsUtils.binomialCoefficient(i + 3, 3);
		}
		System.out.println(Arrays.toString(nc2));
		System.out.println(Arrays.toString(nc3));
	}

	static final int P = (int) (1e9 + 7);

	static void combTest() {
		long lastRes = 1;
		BigInteger p = new BigInteger(P + "");
		for (int i = 1; i < 100; i++) {
			long bc = CombinatoricsUtils.binomialCoefficient(10 + i, 10);
			lastRes *= new BigInteger(i + "").modInverse(p).longValue();
			lastRes *= 10 - i + 2;
			lastRes %= P;
			System.out.println(bc + "\t" + bc % P + "\t" + lastRes);
		}
	}

	static void modInv() {
		int n = 100000;
		BigInteger p = new BigInteger(P + "");
		for (int i = 1; i <= n; i++) {
			System.out.println(new BigInteger(i + "").modInverse(p));
		}
	}
}
