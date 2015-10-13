package zi.chef.y15.augLong;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

import org.apache.commons.lang3.text.StrBuilder;

public class EGen {
	public static void main(String[] args) throws Exception {
		//		System.setOut(new PrintStream("/home/bala/temp/21/E-gen.in"));
		//		generate(10, 15);
		execute();
	}

	public static void generate(int nl, int nu) {
		Random random = new Random();
		int n = random.nextInt(nu - nl) + nl;
		System.out.println(n + " " + n);
		StrBuilder builder = new StrBuilder();
		int gen[] = new int[n];
		for (int i = 0; i < n; i++) {
			gen[i] = random.nextInt(nu);
			builder.append(gen[i]).append(' ');
		}
		builder.deleteCharAt(builder.length() - 1);
		System.out.println(builder.toString());
		for (int i = 0; i < n; i++) {
			int op = random.nextInt(3);
			builder = new StrBuilder();
			if (op == 0)
				builder.append('>');
			else if (op == 1)
				builder.append('<');
			else
				builder.append('=');
			builder.append(' ');
			builder.append(gen[random.nextInt(n)]).append(' ').append(random.nextBoolean() ? 'D' : 'C');
			System.out.println(builder.toString());
		}
	}

	public static void execute() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("/home/bala/temp/21/E-gen.in"));
		String[] inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int m = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		int a[] = new int[n];
		int max[] = new int[n * (n + 1) / 2];
		int maxInd = 0;
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(inStr[i]);
			max[maxInd++] = a[i];
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				max[maxInd++] = a[maxInd(i, j, a)];
			}
		}
		Arrays.sort(max);
		System.out.println(Arrays.toString(max));

		ArrayList<Num> nums = new ArrayList<>(n);
		for (int i = 0; i < max.length; i++) {
			Num num = new Num(max[i]);
			num.isMaxCountOdd = false;
			while (i < max.length && max[i] == num.num) {
				num.isMaxCountOdd ^= true;
				i++;
			}
			i--;
			nums.add(num);
		}
		System.out.println(nums);

		for (int i = 0; i < m; i++) {
			inStr = reader.readLine().split(" ");
			int lk = Integer.parseInt(inStr[1]);
			boolean isDev = inStr[2].equals("D");
			int j;
			for (j = 0; j < max.length && max[j] < lk; j++)
				;
			if (inStr[0].equals("<")) {
				printWinner(j - 1 % 2 != 0, isDev);
			} else {
				int ct = 0;
				for (; j < max.length && max[j] == lk; j++, ct++)
					;
				if (inStr[0].equals("=")) {
					printWinner(ct % 2 != 0, isDev);
				} else {
					ct = 0;
					for (; j < max.length; j++, ct++)
						;
					printWinner(ct % 2 != 0, isDev);
				}
			}
		}

		reader.close();
	}

	static int maxInd(int l, int h, int[] a) {
		int maxInd = l;
		for (int i = l + 1; i <= h; i++) {
			if (a[i] > a[maxInd])
				maxInd = i;
		}
		return maxInd;
	}

	static void printWinner(boolean isOdd, boolean isDev) {
		if (!isOdd)
			isDev = !isDev;
		if (isDev)
			System.out.print('D');
		else
			System.out.print('C');
	}
}
