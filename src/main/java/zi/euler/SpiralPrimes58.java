package zi.euler;

import org.apache.commons.math3.primes.Primes;

public class SpiralPrimes58 {

	public static void main(String[] args) {
		Spiral spiral = new Spiral();
		spiral.increaseBy1();
		while (spiral.getPrimeRatio() > 0.1) {
			spiral.increaseBy1();
		}
		System.out.println(spiral.getCurrentIndex());
	}
}

class Spiral {

	private int lastPrimeCount = 0;
	private int size = 1;
	private int lastNumber = 1;
	private int penultimateNumber = -1;

	public Spiral() {
	}

	public void increaseBy1() {
		int n = size * 2 + 1;
		penultimateNumber = lastNumber;
		lastNumber = lastNumber + 4 * (n - 1);
		size++;
	}

	public double getPrimeRatio() {
		int inc = (size - 1) * 2 + 1;
		for (int j = penultimateNumber + inc - 1; j <= lastNumber; j += (inc - 2 + 1)) {
			if (Primes.isPrime(j)) {
//				System.out.println(j + "'");
				lastPrimeCount++;
			} else {
//				System.out.println(j);
			}
		}
		double ans = (double) lastPrimeCount / (2 * inc - 1);
//		System.out.println(ans);
		return ans;
	}

	public int getCurrentIndex() {
		return (size - 1) * 2 + 1;
	}

}
