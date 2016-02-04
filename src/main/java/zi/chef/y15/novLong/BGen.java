package zi.chef.y15.novLong;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class BGen {

	public static void main(String[] args) throws Exception {
		check();
	}

	static void gen() throws Exception {
		System.setOut(new PrintStream(new FileOutputStream("/Users/balaudt/Temp/B-gen-3.in")));
		Random random = new Random();
		int t = random.nextInt(50) + 50;
		System.out.println(t);
		for (int i = 0; i < t; i++) {
			int a = random.nextInt(1000000000) + 1;
			int d = random.nextInt(1000000000) + 1;
			int r = random.nextInt(1000000) + 10;
			int l = random.nextInt(r - 1) + 1;
			System.out.println(new StrBuilder().append(a).append(' ').append(d).append(' ').append(l).append(' ')
					.append(r).toString());
		}
	}

	static void check() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/B-gen-3.in"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/balaudt/Temp/B-gen-3-cor.out"));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] inStr = reader.readLine().split(" ");
			long a = Integer.parseInt(inStr[0]);
			int d = Integer.parseInt(inStr[1]);
			int l = Integer.parseInt(inStr[2]);
			int r = Integer.parseInt(inStr[3]);
			int ct = 0;
			while (ct < l - 1) {
				a += d;
				ct++;
			}
			long sum = 0;
			while (ct < r) {
//				writer.write(a + "\t" + f(a) + "\n");
				sum += f(a);
				a += d;
				ct++;
			}
			writer.write(sum + "\n");
		}
		reader.close();
		writer.close();
	}

	static int f(long n) {
		long ans = n;
		while (ans >= 10) {
			char[] cs = Long.toString(ans).toCharArray();
			ans = 0;
			for (int i = 0; i < cs.length; i++) {
				ans += cs[i] - '0';
			}
		}
//		System.out.println(ans);
		return (int) ans;
	}

	static int fnative(int n) {
		int ans = n;
		while (ans >= 10) {
			n = ans;
			ans = 0;
			while (n > 0) {
				ans += n % 10;
				n /= 10;
			}
		}
		System.out.println(ans);
		return ans;
	}
}
