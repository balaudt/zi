package zi.euler;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.primes.Primes;

public class TruncatablePrimes37 {

	public static void main(String[] args) {
		int truncPrimeCount = 0;
		int currentPrime = 11;
		long sum = 0;
		while (truncPrimeCount < 11) {
			if (isTruncPrime(currentPrime)) {
				sum += currentPrime;
				truncPrimeCount++;
			}
			currentPrime = Primes.nextPrime(currentPrime + 1);
		}
		System.out.println(sum);
	}

	static boolean isTruncPrime(int prime) {
		String primeStr = Integer.toString(prime);
		primeStr = primeStr.substring(1);
		while (StringUtils.isNotBlank(primeStr)) {
			if (!Primes.isPrime(Integer.parseInt(primeStr))) {
				return false;
			}
			primeStr = primeStr.substring(1);
		}
		primeStr = Integer.toString(prime);
		primeStr = primeStr.substring(0, primeStr.length() - 1);
		while (StringUtils.isNotBlank(primeStr)) {
			if (!Primes.isPrime(Integer.parseInt(primeStr))) {
				return false;
			}
			primeStr = primeStr.substring(0, primeStr.length() - 1);
		}
		return true;
	}

}
