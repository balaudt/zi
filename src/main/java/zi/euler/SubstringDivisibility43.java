package zi.euler;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import com.google.common.base.Joiner;

public class SubstringDivisibility43 {

	public static void main(String[] args) {
		ICombinatoricsVector<Integer> vector = Factory
				.createVector(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		Generator<Integer> generator = Factory
				.createPermutationGenerator(vector);
		long ans = 0l;
		int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17 };
		Joiner joiner = Joiner.on("");
		for (ICombinatoricsVector<Integer> perm : generator) {
			boolean flag = true;
			for (int i = 2; i <= 8; i++) {
				if (valAtIndices(perm, new int[] { i - 1, i, i + 1 })
						% primes[i - 2] != 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				ans += Long.parseLong(joiner.join(perm.getVector()));
			}
		}
		System.out.println(ans);
	}

	static int valAtIndices(ICombinatoricsVector<Integer> perm, int[] indices) {
		int len = indices.length;
		int ans = 0;
		for (int i = 0; i < indices.length; i++) {
			ans += new Double(Math.pow(10, len - i - 1)).intValue()
					* perm.getValue(indices[i]);
		}
		return ans;
	}

}
