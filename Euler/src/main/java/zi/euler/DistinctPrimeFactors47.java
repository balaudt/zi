package zi.euler;

import java.util.HashSet;

import org.apache.commons.math3.primes.Primes;

public class DistinctPrimeFactors47 {

	public static void main(String[] args) {
		int n = 647;
		while (n < Integer.MAX_VALUE) {
			boolean isStarter = true;
			for (int i = n; i <= n + 3; i++) {
				if (new HashSet<Integer>(Primes.primeFactors(i)).size() != 4) {
					isStarter = false;
					break;
				}
			}
			if (isStarter) {
				break;
			} else {
				n++;
			}
		}
		System.out.println(n);
	}
}
