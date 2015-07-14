package zi.chef.y15.julLong;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class EGen {

	static final String FOLDER = "/home/bala/temp/8/";
	static final int P = (int) (1e9 + 7);

	public static void main(String[] args) throws Exception {
		execute(args);
	}

	public static void generate(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FOLDER + "E-gen.in"));
		int n, k;
		n = k = 50;
		writer.write(n + " " + k + "\n");
		StrBuilder builder = new StrBuilder();
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			builder.append(random.nextInt((int) 1e8) + 1000).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1).append('\n');
		writer.write(builder.toString());
		for (int i = 0; i < k; i++) {
			int op = random.nextInt(4) + 1;
			int l = random.nextInt(n) + 1;
			int r = l == n ? l : random.nextInt(n - l) + l;
			if (op == 4) {
				writer.write(op + " " + l + " " + r + "\n");
			} else {
				int v = random.nextInt((int) 1e8) + 1000;
				writer.write(op + " " + l + " " + r + " " + v + "\n");
			}
		}
		writer.close();
	}

	public static void execute(String[] args) throws Exception {
		long ct = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new FileReader(FOLDER + "E-gen.in"));
		System.setOut(new PrintStream(FOLDER + "E-gen-cor.out"));
		//				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int q = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inStr[i]);
		}
		for (int i = 0; i < q; i++) {
			inStr = reader.readLine().split(" ");
			int op = Integer.parseInt(inStr[0]);
			int l = Integer.parseInt(inStr[1]) - 1;
			int r = Integer.parseInt(inStr[2]) - 1;
			if (op < 4) {
				int v = Integer.parseInt(inStr[3]);
				for (int j = l; j <= r; j++) {
					long res = a[j];
					switch (op) {
					case 1:
						res += v;
						res %= P;
						a[j] = (int) res;
						break;
					case 2:
						res *= v;
						res %= P;
						a[j] = (int) res;
						break;
					case 3:
						a[j] = v;
					}
				}
			} else {
				long res = 0;
				for (int j = l; j <= r; j++) {
					res += a[j];
					res %= P;
				}
				System.out.println(res);
			}
		}
//		System.out.println(Arrays.toString(a));
		System.out.println(System.currentTimeMillis()-ct);
		reader.close();
	}
}
