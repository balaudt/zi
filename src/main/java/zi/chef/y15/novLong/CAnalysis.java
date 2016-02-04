package zi.chef.y15.novLong;

import java.lang.reflect.Field;

import org.apache.commons.math3.util.ArithmeticUtils;

public class CAnalysis {

	public static void main(String[] args) throws Exception {
		Field field = Class.forName("org.apache.commons.math3.primes.SmallPrimes").getField("PRIMES");
		field.setAccessible(true);
		System.out.println( ((int[])field.get(null)).length);
	}

	static void analysis() {
		int n = 20;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= i; j++) {
				sum += i / ArithmeticUtils.gcd(i, j);
			}
			System.out.print(sum + ",");
		}
	}
}
