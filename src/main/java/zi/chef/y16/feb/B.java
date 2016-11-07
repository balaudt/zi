package zi.chef.y16.feb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class B {

	private static final int P = (int) 1e9 + 7;
	private static final int MAX_N = (int) 1e5;

	public static void main(String[] args) throws Exception {
		int[] modInv = new int[MAX_N];
		BigInteger p = new BigInteger(P + "");
		for (int i = 1; i <= MAX_N; i++) {
			modInv[i - 1] = new BigInteger(i + "").modInverse(p).intValue();
		}

		//		BufferedReader reader = new BufferedReader(new FileReader("/Users/balaudt/Temp/feb/B-large.in"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		for (int i = 0; i < t; i++) {
			inStr = reader.readLine().split(" ");
			int n = Integer.parseInt(inStr[0]);
			int x = Integer.parseInt(inStr[1]);
			long m = Long.parseLong(inStr[2]);
			m %= P;
			inStr = reader.readLine().split(" ");
			long a[] = new long[n];
			for (int j = 0; j < n; j++) {
				a[j] = Long.parseLong(inStr[j]);
				a[j] %= P;
			}
			long ans = a[x - 1];
			int j, r;
			long k, lastRes = 1;
			for (j = x - 2, k = m, r = 1; j >= 0; j--, k++, r++) {
				long temp = modInv[r - 1] * k;
				temp %= P;
				lastRes *= temp;
				lastRes %= P;
				ans += a[j] * lastRes;
				ans %= P;
			}
			System.out.println(ans);
		}
		reader.close();
	}
}
