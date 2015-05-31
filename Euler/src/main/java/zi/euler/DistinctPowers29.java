package zi.euler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.primes.Primes;

public class DistinctPowers29 {

	public static void main(String[] args) {
		// Map<List<Integer>, List<Pair>> cum = new HashMap<List<Integer>,
		// List<Pair>>();
		Set<List<Pair>> cum = new HashSet<List<Pair>>();
		for (int i = 2; i <= 100; i++) {
			Set<Integer> factors = new HashSet<Integer>(Primes.primeFactors(i));
			List<Pair> pairs = getPairs(i, factors);
			for (int j = 2; j <= 100; j++) {
				ArrayList<Pair> another = new ArrayList<Pair>();
				for (Pair pair : pairs) {
					another.add(new Pair(pair.p, pair.pow * j));
				}
				// cum.put(Arrays.asList(new Integer[] { i, j }), another);
				cum.add(another);
			}
		}
		System.out.println(cum.size());
	}

	static List<Pair> getPairs(int no, Set<Integer> factors) {
		ArrayList<Pair> out = new ArrayList<Pair>();
		for (Integer fact : factors) {
			int count = 0;
			int baseFact = fact;
			while (no % fact == 0) {
				count++;
				fact *= baseFact;
			}
			out.add(new Pair(baseFact, count));
		}
		return out;
	}
}

class Pair {
	int p, pow;

	public Pair(int p, int pow) {
		this.p = p;
		this.pow = pow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + p;
		result = prime * result + pow;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (p != other.p)
			return false;
		if (pow != other.pow)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + p + "^" + pow + "}";
	}
}
