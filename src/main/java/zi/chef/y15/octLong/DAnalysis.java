package zi.chef.y15.octLong;
import java.util.Arrays;
import java.util.Random;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;


public class DAnalysis {

	public static void analysis(String[] args) {
		int n = 10;
		int a[] = new int[n];
		Integer gen[] = new Integer[n];
		Random random = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(n) + 1;
			gen[i] = i;
		}
		System.out.println(Arrays.toString(a));
		for (int i = 2; i <= n; i++) {
			Generator<Integer> generator = Factory.createSimpleCombinationGenerator(Factory.createVector(gen), i);
			long sum = 0;
			for (ICombinatoricsVector<Integer> comb : generator) {
				long prod = 1;
				for (Integer ind : comb) {
					prod *= a[ind];
				}
				sum += prod;
			}
			System.out.println("i=" + i + "\tsum=" + sum);
		}

		long prevSum[] = new long[n - 1];
		long sum = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				prevSum[i] += a[i] * a[j];
			}
			sum += prevSum[i];
		}
		System.out.println("i=2\tsum=" + sum);
		int j;
		for (int i = 3; i <= n; i++) {
			prevSum[0] = sum - prevSum[0];
			sum = 0;
			for (j = 1; j <= n - i + 1; j++) {
				prevSum[j] = prevSum[j - 1] - prevSum[j];
				prevSum[j - 1] *= a[j - 1];
				sum += prevSum[j - 1];
			}
			prevSum[j - 1] *= a[j - 1];
			sum += prevSum[j - 1];
			System.out.println("i=" + i + "\tsum=" + sum);
		}
	}

	public static void main(String[] args) {
		int ct[] = new int[] { 1, 5, 10, 4, 2, 6, 1, 7, 1, 1 };
		int sum = 0;
		for (int i = 0; i < ct.length; i++) {
			sum += ct[i];
		}
		System.out.println(sum + " " + sum + " 10");
		for (int i = 0; i < ct.length; i++) {
			for (int j = 0; j < ct[i]; j++) {
				System.out.print(i + " ");
			}
		}
	}

}
