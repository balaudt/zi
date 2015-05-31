package zi.euler;

import org.apache.commons.math3.primes.Primes;

public class GoldbachConjecture46 {

	public static void main(String[] args) {
		int n = 35;
		boolean canSplit = true;
		while (n < Integer.MAX_VALUE && canSplit) {
			if (Primes.isPrime(n)) {
				n += 2;
				continue;
			}
			canSplit = false;
			int prime = Primes.nextPrime(3);
			while (prime < n) {
				double sqrt = Math.sqrt((n - prime) / 2);
				if (sqrt == Math.floor(sqrt)) {
					canSplit = true;
					break;
				}
				prime = Primes.nextPrime(prime + 1);
			}
			n += 2;
		}
		System.out.println(n - 2);
	}
}
