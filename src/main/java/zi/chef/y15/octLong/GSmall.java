package zi.chef.y15.octLong;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//basic test to confirm the understanding of the problem
public class GSmall {

	public static final int[] PRIMES = { 2, 3, 5, 7, 11, 13, 17, 19 };

	static int lcm(int a, int b) {
		int aCopy = a;
		for (int i = 0; i < PRIMES.length; i++) {
			while (a % PRIMES[i] == 0) {
				a /= PRIMES[i];
				if (b % PRIMES[i] == 0)
					b /= PRIMES[i];
			}
		}
		return aCopy * b;
	}

	public static void main(String[] args) throws Exception {
		int[][] lcm = new int[10][];
		lcm[0] = new int[1];
		lcm[0][0] = 1;
		for (int i = 1; i < lcm.length; i++) {
			lcm[i] = new int[i + 1];
			lcm[i][0] = i + 1;
			int no = i;
			for (int j = 1; j <= i; j++) {
				lcm[i][j] = lcm(lcm[i][j - 1], no);
				no--;
			}
			//			System.out.println(Arrays.toString(lcm[i]));
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());
		String[] inStr;
		inStr = reader.readLine().split(" ");
		int n = Integer.parseInt(inStr[0]);
		int k = Integer.parseInt(inStr[1]);
		inStr = reader.readLine().split(" ");
		int a = Integer.parseInt(inStr[0]);
		int b = Integer.parseInt(inStr[1]);
		int m = Integer.parseInt(inStr[2]);
		if (m > 10)
			throw new UnsupportedOperationException();
		String[] cStrs = reader.readLine().split(" ");
		String[] dStrs = reader.readLine().split(" ");
		int ans = lcm[n - 1][k - 1], c, d;
		System.out.println(ans);
		for (int j = 0; j < t - 1; j++) {
			c = Integer.parseInt(cStrs[j]);
			d = Integer.parseInt(dStrs[j]);
			n = 1 + (a * ans + c) % m;
			k = 1 + (b * ans + d) % n;
			ans = lcm[n - 1][k - 1];
			System.out.println(ans);
		}
		reader.close();
	}
}
