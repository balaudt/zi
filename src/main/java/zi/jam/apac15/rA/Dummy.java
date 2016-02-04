package zi.jam.apac15.rA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.collections4.ListUtils;

public class Dummy {

	public static void lcs(String[] args) {
		String str = "abaaaaaaaaaaa";
		String[] seqs = new String[] { "bba", "aaa", "aba", "ab", "bab", "bbb", "aab", "baa", "aabaa", "abaa", "aaaa",
				"baba", "bbba", "aaba" };
		int maxLen = Integer.MIN_VALUE;
		for (String seq : seqs) {
			String lcs = ListUtils.longestCommonSubsequence(seq, str);
			if (lcs.length() > maxLen)
				maxLen = lcs.length();
		}
		System.out.println(maxLen);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(
				new FileReader("/Users/balaudt/Dev/PlayGround/xldrx/cloudapp-mp1/input.txt"));
		String delimiters = "[\\t\\,;\\.\\?\\!\\-\\:@\\[\\]\\(\\)\\{\\}\\_\\*/]";
		for (int i = 0; i < 10; i++) {
			String line = reader.readLine();
			System.out.println(line + "\t" + Arrays.toString(line.split(delimiters)));
		}
		reader.close();
	}

	public static void subReady(String[] args) {
		String astr = "46565210361";
		char a[] = astr.toCharArray();
		String bstr = "5601133551";
		char b[] = bstr.toCharArray();
		System.out.println(astr);
		System.out.println(bstr);
		boolean borrow = false;
		StringBuilder builder = new StringBuilder();
		int ai = a.length - 1;
		for (int i = b.length - 1; i >= 0; i--, ai--) {
			int l = a[ai];
			if (borrow)
				l--;
			if (b[i] > l) {
				l += 7;
				borrow = true;
			} else {
				borrow = false;
			}
			builder.append(l - b[i]);
		}
		if (borrow) {
			if (ai >= 0) {
				a[ai]--;
			} else
				builder.append('-');
		}
		while (ai >= 0)
			builder.append(a[ai--]);
		System.out.println(builder.reverse());

		System.out.println(Integer.toString(Integer.parseInt(astr, 7) - Integer.parseInt(bstr, 7), 7));
	}

	public static void multReady(String[] args) {
		Random random = new Random();
		int n = 10;
		int randMax = (int) Math.pow(7, n);
		int bnum = random.nextInt(randMax / 2) + (randMax / 2);
		char[] b = Integer.toString(bnum, 7).toCharArray();
		System.out.println("b:\t" + Arrays.toString(b));
		long anum = (long) bnum * random.nextInt(randMax);
		char[] a = Long.toString(anum, 7).toCharArray();
		System.out.println("a:\t" + Arrays.toString(a));
		for (int i = 0; i < 7; i++) {
			System.out.println(Integer.toString(bnum * i, 7));
		}

		Map<Integer, char[]> basicLkup = new HashMap<>();
		for (int i = 0; i < 49; i++) {
			basicLkup.put(i, Integer.toString(i, 7).toCharArray());
		}
		char[][][] multTable = new char[7][7][];
		//		int[][] multTableInt = new int[7][7];
		for (int i = 0; i < multTable.length; i++) {
			for (int j = 0; j < multTable.length; j++) {
				multTable[i][j] = basicLkup.get(i * j);
				//				multTableInt[i][j] = i * j;
				System.out.print(Arrays.toString(multTable[i][j]) + '\t');
			}
			System.out.println();
		}

		for (int i = 0; i < 7; i++) {
			StringBuilder builder = new StringBuilder();
			int carry = 0, stepMult;
			for (int j = b.length - 1; j >= 0; j--) {
				stepMult = i * (b[j] - '0') + carry;
				builder.append(stepMult % 7);
				carry = stepMult / 7;
			}
			if (carry != 0)
				builder.append(carry);
			System.out.println(builder.reverse());
		}

	}

	public static void base7(String[] args) {
		System.out.println(Integer.toString(Integer.parseInt("23", 7) * Integer.parseInt("10", 7), 7));
		System.out.println(Integer.toString(Integer.parseInt("314151334", 7) % Integer.parseInt("10000", 7), 7));
		System.out.println(Integer.toString(2401, 7));
	}

	static int lcsLen(String x, String y) {
		int M = x.length();
		int N = y.length();

		// opt[i][j] = length of LCS of x[i..M] and y[j..N]
		int[][] opt = new int[M + 1][N + 1];

		// compute length of LCS and all subproblems via dynamic programming
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (x.charAt(i) == y.charAt(j))
					opt[i][j] = opt[i + 1][j + 1] + 1;
				else
					opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
			}
		}

		return opt[0][0];
	}
}
