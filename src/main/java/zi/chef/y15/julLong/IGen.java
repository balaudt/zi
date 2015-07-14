package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;

import org.apache.commons.math3.util.ArithmeticUtils;

public class IGen {
	static final String FOLDER = "/home/bala/temp/8/";

	public static void generate(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "I-gen.in"));
		writer.write("50\n");
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			int n = random.nextInt(14) + 1;
			int k = random.nextInt(14) + 1;
			int l = random.nextInt(Integer.min(n, k));
			if (l == 0)
				l = 1;
			writer.write(n + " " + k + " " + l + " 1\n");
		}
		writer.close();
	}

	static final int P = 2003;

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "I-gen.in"));
		System.setOut(new PrintStream(FOLDER + "I-gen-cor.out"));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int k = Integer.parseInt(inStr[1]);
			int l = Integer.parseInt(inStr[2]);
			long a = answerAsProperFracion(n, k, l)[1];
			long ans = modInverse(a, P);
			if (ans == 101) {
				System.out.println(Arrays.toString(inStr));
				System.out.println(a);
			}
			System.out.println(ans);
		}
		reader.close();
	}

	static long[] answerAsProperFracion(int n, int k, int l) {
		long num = 1;
		for (int i = 0; i < l; i++) {
			num *= (n - i);
		}
		long den = (long) Math.pow(k, l);
		long gcd = ArithmeticUtils.gcd(num, den);
		return new long[] { num / gcd, den / gcd };
	}

	static long pow(long a, long b, int MOD) {
		long x = 1, y = a;
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x * y);
				if (x > MOD)
					x %= MOD;
			}
			y = (y * y);
			if (y > MOD)
				y %= MOD;
			b /= 2;
		}
		return x;
	}

	static long modInverse(long a, int m) {
		return pow(a, m - 2, m);
	}
}
