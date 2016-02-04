package org.paukov.combinatorics;

import org.apache.commons.math3.util.CombinatoricsUtils;;

public class IAnalysis {

	static final int N = 25;

	public static void main(String[] args) throws Exception {
		for (int n = 3; n <= N; n++) {
			Integer[] array = new Integer[n];
			for (int i = 0; i < array.length; i++) {
				array[i] = i + 1;
			}
			ICombinatoricsVector<Integer> vector = Factory.createVector(array);
			for (int k = 3; k <= (n < 7 ? n : 7); k++) {
				Generator<Integer> generator = Factory.createSimpleCombinationGenerator(vector, k);
				int ans = 0;
				for (ICombinatoricsVector<Integer> comb : generator) {
					int sum = 0;
					for (Integer i : comb) {
						sum += i;
					}
					if (sum % 2 != 0)
						sum++;
					int t = sum / 2;
					boolean feasible = true;
					for (Integer i : comb) {
						if (i >= t) {
							feasible = false;
							break;
						}
					}
					//				System.out.println(feasible + "\t" + sum + "\t" + comb);
					if (feasible)
						ans++;
				}

//				System.out.print(ans+"\t");
				System.out.print(CombinatoricsUtils.binomialCoefficient(n, k)-ans+"\t");
			}
			System.out.println();
		}
	}
}
