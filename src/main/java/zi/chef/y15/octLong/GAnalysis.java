package zi.chef.y15.octLong;
import java.util.Arrays;

import org.apache.commons.math3.util.ArithmeticUtils;

public class GAnalysis {

	/*
	 * f(n,k)=(n-k)*nCk
	 * https://oeis.org/A216973
	 */
	public static long[][] funcClosedForm(int t, boolean print) {
		long[][] f = new long[t][];
		f[0] = new long[1];
		f[0][0] = 1;
		for (int i = 1; i < t; i++) {
			f[i] = new long[i + 1];
			f[i][0] = i + 1;
			for (int j = 1; j <= i; j++) {
				float a = f[i - 1][j - 1];
				float b = f[i][j - 1];
				f[i][j] = (int) Math.floor(a * b / (b - a));
			}
		}
		/*for (int i = 0; i < t; i++) {
			System.out.println(Arrays.toString(f[i]));
		}*/

		long facts[] = new long[t + 1];
		facts[0] = 1;
		for (int i = 1; i < facts.length; i++) {
			facts[i] = facts[i - 1] * (i + 1);
		}
		for (int n = 1; n < t; n++) {
			for (int k = 1; k <= n; k++) {
				int an = n + 1;
				f[n][k] = (an - k) * facts[n] / facts[n - k] / facts[k - 1];
			}
		}
		if (print) {
			for (int i = 0; i < t; i++) {
				System.out.println(Arrays.toString(f[i]));
			}
		}
		return f;
	}

	/*
	 * https://oeis.org/A179661
	 */
	public static void lcmClosedForm(long[][] f) {
		long[][] lcm = new long[f.length][];
		for (int i = 0; i < lcm.length; i++) {
			lcm[i] = new long[f[i].length];
			lcm[i][0] = f[i][0];
			for (int j = 1; j < lcm[i].length; j++) {
				lcm[i][j] = ArithmeticUtils.lcm(lcm[i][j - 1], f[i][j]);
			}
			System.out.println(Arrays.toString(lcm[i]));
		}

		for (int i = 1; i < lcm.length; i++) {
			int no = i;
			for (int j = 1; j <= i; j++) {
				lcm[i][j] = ArithmeticUtils.lcm(lcm[i][j - 1], no);
				no--;
			}
			System.out.println(Arrays.toString(lcm[i]));
		}
	}

	public static void flatLcm(int n) {
		int range = n * (n + 1) / 2;
		long[] ans = new long[range];
		ans[0] = 1;
		for (int i = 2; i < range; i++) {
			ans[i] = i - ArithmeticUtils.binomialCoefficient((int) Math.floor(.5 + Math.sqrt(i)), 2);
		}
		System.out.println(Arrays.toString(ans));
	}

	public static void main(String[] args) {
		lcmClosedForm(funcClosedForm(10, true));
	}
}
