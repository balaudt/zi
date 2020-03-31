package zi.leet.easy;

import java.util.Arrays;

/**
 * @author balamurugan
 */
public class CatalanNumber {
	public static void main(String[] args) {
		int n = 12;
		int[] memo = new int[n];
		memo[0] = 1;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++)
				memo[i] += memo[j] * memo[i - j - 1];
		}
		System.out.println(Arrays.toString(memo));
	}
}
