package zi.chef.y15.julLong;
import java.math.BigInteger;

import org.apache.commons.math3.primes.Primes;

public class BAnalysis {

	public static void main(String[] args) {
		for (int i = 0; i < 512; i++) {
			String str = Integer.toBinaryString(i);
			str = str.replace('0', '4');
			str = str.replace('1', '7');
			System.out.println(str + "\t" + Primes.primeFactors(Integer.parseInt(str)));
		}
		int val = new BigInteger("744444447744").divide(new BigInteger("512")).intValue();
		System.out.println(val);
		System.out.println(Primes.primeFactors(val));
		System.out.println(Primes.primeFactors(7447744));
	}
}
