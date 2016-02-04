package zi.fb.cup15;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class CorrectPriceTest {
	public static void main(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		verify(args);
		System.out.println(System.currentTimeMillis() - ct);
	}

	public static void gen(String[] args) throws Exception {
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/C-gen.in"));
		System.out.println(40);
		Random random = new Random();
		for (int i = 0; i < 40; i++) {
			int p = random.nextInt(10000) + 10000;
			System.out.println(10000 + " " + p);
			StrBuilder builder = new StrBuilder();
			for (int j = 0; j < 10000; j++) {
				builder.append(random.nextInt(p) + 10 + random.nextInt(50)).append(' ');
			}
			builder.deleteCharAt(builder.length() - 1);
			System.out.println(builder.toString());
		}
	}

	public static void verify(String[] args) throws Exception {
		System.setIn(new FileInputStream("/Users/balaudt/Temp/fb/C.in"));
		System.setOut(new PrintStream("/Users/balaudt/Temp/fb/C-cor.out"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		for (int i = 0; i < t; i++) {
			String[] line = reader.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			long p = Integer.parseInt(line[1]);
			long b[] = new long[n];
			line = reader.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				b[j] = Integer.parseInt(line[j]);
			}
			long ans = 0, runningSum[] = new long[n + 1];
			runningSum[0] = 0;
			for (int j = 1; j <= n; j++) {
				runningSum[j] = runningSum[j - 1] + b[j - 1];
			}
			for (int j = 1; j <= n; j++) {
				for (int k = 0; k < j; k++) {
					if (runningSum[j] - runningSum[k] <= p)
						ans++;
				}
			}
			System.out.println(String.format("Case #%d: %d", i + 1, ans));
		}

	}
}
