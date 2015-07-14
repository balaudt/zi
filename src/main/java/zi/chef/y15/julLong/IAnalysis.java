package zi.chef.y15.julLong;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.text.StrBuilder;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class IAnalysis {
	public static long baseAnalysis(int k, int n, int l) {
		//		int k = 6, n = 5, l = 5;
		Integer[] vin_arr = new Integer[k];
		for (int i = 0; i < k; i++) {
			vin_arr[i] = i + 1;
		}
		ICombinatoricsVector<Integer> vin = Factory.createVector(vin_arr);
		Generator<Integer> generator = Factory.createPermutationWithRepetitionGenerator(vin, n);
		Map<Integer, Integer> counter = new HashMap<>(l);
		Set<Integer> lSet = new HashSet<>();
		for (int i = 1; i <= l; i++) {
			lSet.add(i);
			counter.put(i, 0);
		}
		long sum = 0;
		for (ICombinatoricsVector<Integer> vout : generator) {
			counter.replaceAll((Integer key, Integer val) -> {
				return 0;
			});
			vout.getVector().forEach(i -> {
				if (lSet.contains(i)) {
					counter.put(i, counter.get(i) + 1);
				}
			});
			long prod = 1;
			for (int i : counter.values())
				prod *= i;
			sum += prod;
		}
		return sum;
	}

	static long[] answerAsProperFracion(int n, int k, int l) {
		long num = 1;
		for (int i = 0; i < l; i++) {
			num *= (n - i);
		}
		long den = (long) Math.pow(k, l);
		long gcd = ArithmeticUtils.gcd(num, den);
		return new long[] { num / gcd, den / gcd };
	}

	public static void main(String[] args) {
		System.out.println(modInverse(10604499373l, 2003));
	}

	public static void functionOfFraction(String[] args) {
		System.out.println(new StrBuilder().append('k').append('\t').append('n').append('\t').append('l').append('\t').append("num")
				.append('\t').append("den").toString());
		for (int k = 3; k <= 7; k++) {
			for (int n = 3; n <= 7; n++) {
				int lmax = Math.min(k, n);
				for (int l = 1; l <= lmax; l++) {
					long[] ans = answerAsProperFracion(n, k, l);
					System.out.println(new StrBuilder().append(k).append('\t').append(n).append('\t').append(l).append('\t')
							.append(ans[0]).append('\t').append(ans[1]).toString());
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	static long pow(long a, int b, int MOD) {
		long x = 1, y = a;
		while (b > 0) {
			if (b % 2 == 1) {
				x = (x * y);
				if (x > MOD)
					x %= MOD;
			}
			y = (y * y);
			if (y > MOD)
				y %= MOD;
			b /= 2;
		}
		return x;
	}

	static long modInverse(long a, int m) {
		return pow(a, m - 2, m);
	}
}
